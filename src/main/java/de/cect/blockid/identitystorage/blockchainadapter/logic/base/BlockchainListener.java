package de.cect.blockid.identitystorage.blockchainadapter.logic.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.buf.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.RequestCheckTx;
import com.github.jtendermint.jabci.types.Types.RequestCommit;
import com.github.jtendermint.jabci.types.Types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.Types.RequestInfo;
import com.github.jtendermint.jabci.types.Types.RequestQuery;
import com.github.jtendermint.jabci.types.Types.ResponseCheckTx;
import com.github.jtendermint.jabci.types.Types.ResponseCommit;
import com.github.jtendermint.jabci.types.Types.ResponseDeliverTx;
import com.github.jtendermint.jabci.types.Types.ResponseInfo;
import com.github.jtendermint.jabci.types.Types.ResponseQuery;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import de.cect.blockid.identitystorage.blockchainadapter.dataaccess.ApplicationStateRepository;
import de.cect.blockid.identitystorage.blockchainadapter.dataaccess.entities.ApplicationStateEntity;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.IBlockchainListener;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.generated.IdentityAssertionProto.IdentityAssertionMessage;

/**
 * The listener, listening to blockchain events. This is actually the core
 * component for connecting the application logic with the blockchain.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
/*
 * TODO ctinnes it is very important, that the application state is consistently
 * persisted. At the moment, during persistence, there might be commits and new
 * transactions. Somehow, the whole listener must be blocked while persistence
 * (in datamanager) takes place.
 */
public class BlockchainListener implements IBlockchainListener {
	private static final Logger LOG = LoggerFactory.getLogger(BlockchainListener.class);

	private ApplicationStateRepository applicationStateRepository;

	private IdAssertionMessageCache messageCache;

	private List<String> uncomittedAttributes;

	private Long lastBlockHeight;
	@Value("${blockchain.application.state.blockheight.default:0}")
	private Long defaultBlockHeight;

	/**
	 * Constructor for the blockchain listener initializing the cache as well.
	 */
	public BlockchainListener(IdAssertionMessageCache messageCache,
			ApplicationStateRepository applicationStateRepository) {
		this.messageCache = messageCache;
		uncomittedAttributes = new ArrayList<>();
		this.applicationStateRepository = applicationStateRepository;
	}

	@Override
	public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx req) {
		byte[] tx = req.getTx().toByteArray();
		LOG.debug("got deliver tx, with " + HexUtils.toHexString(tx));
		if (tx.length == 0) {
			return ResponseDeliverTx.newBuilder().setCode(CodeType.BadNonce).setLog("transaction is empty").build();
		}
		IdentityAssertionMessage message;
		try {
			message = IdentityAssertionMessage.parseFrom(tx);
			uncomittedAttributes.remove(message.getIdentityAssertion().getReceiverAdressBase64Bytes()
					+ message.getIdentityAssertion().getIdentityAttributeName());
			messageCache.addMessage(message);
			LOG.debug("TX has been cached: " + message.toString());
			return ResponseDeliverTx.newBuilder().setCode(CodeType.OK).build();
		} catch (InvalidProtocolBufferException e) {
			LOG.error("Error while parsing identity assertion message.", e);
			return ResponseDeliverTx.newBuilder().setCode(CodeType.BadNonce)
					.setLog("Transaction could not been parsed.").build();
		}

	}

	@Override
	// TODO ctinnes check Signature but discuss first if this should happen here.
	public ResponseCheckTx requestCheckTx(RequestCheckTx req) {
		LOG.debug("Got checkTx.");

		byte[] tx = req.getTx().toByteArray();
		IdentityAssertionMessage message;
		try {
			message = IdentityAssertionMessage.parseFrom(tx);
		} catch (InvalidProtocolBufferException e) {
			LOG.error("Error while parsing identity assertion message.", e);
			return ResponseCheckTx.newBuilder().setCode(CodeType.BadNonce).setLog("Transaction could not been parsed.")
					.build();
		}

		if (!uncomittedAttributes.contains(message.getIdentityAssertion().getReceiverAdressBase64Bytes()
				+ message.getIdentityAssertion().getIdentityAttributeName())) {
			LOG.debug("Sending OK.");
			// Add attribute name for receiver, since there should not be more than one
			// identity assertion for one attribute per round this definition should be
			// extended a little bit.
			uncomittedAttributes.add(message.getIdentityAssertion().getReceiverAdressBase64Bytes()
					+ message.getIdentityAssertion().getIdentityAttributeName());
			return ResponseCheckTx.newBuilder().setCode(CodeType.OK).build();
		}

		LOG.info("Check Tx was not OK. Sending Code BadNonce");
		return ResponseCheckTx.newBuilder().setCode(CodeType.BadNonce)
				.setLog("There has already been an uncommited assertion for receiver "
						+ message.getIdentityAssertion().getReceiverAdressBase64() + " for the same attribute name.")
				.build();
	}

	@Override
	public ResponseCommit requestCommit(RequestCommit requestCommit) {
		if (this.lastBlockHeight != null) {
			// Nothing to do in that case
		} else if (this.lastBlockHeight == null && this.applicationStateRepository.count() == 1) {
			Iterable<ApplicationStateEntity> applicationState = this.applicationStateRepository.findAll();
			lastBlockHeight = applicationState.iterator().next().getLastBlockHeight();
		} else {
			LOG.warn("Unexpected application state in database. Using default block height " + defaultBlockHeight);
			lastBlockHeight = defaultBlockHeight;
		}
		lastBlockHeight++;
		// TODO ctinnes this is a dummy commit only there should be a well defined
		// application state which is hashed at this point
		return ResponseCommit.newBuilder().setCode(CodeType.OK)
				.setData(ByteString.copyFrom(Long.toHexString(lastBlockHeight).getBytes())).build();
	}

	@Override
	public ResponseQuery requestQuery(RequestQuery req) {
		// final String query = new String(req.getData().toByteArray(),
		// Charset.forName("UTF-8"));
		// switch (query) {
		// case "hash":
		// return ResponseQuery.newBuilder().setCode(CodeType.OK)
		// .setValue(ByteString.copyFrom(("" +
		// hashCount).getBytes(Charset.forName("UTF-8")))).build();
		// case "tx":
		// return ResponseQuery.newBuilder().setCode(CodeType.OK)
		// .setValue(ByteString.copyFrom(("" +
		// txCount).getBytes(Charset.forName("UTF-8")))).build();
		// default:
		// return ResponseQuery.newBuilder().setCode(CodeType.BadNonce)
		// .setLog("Invalid query path. Expected hash or tx, got " + query).build();
		// }
		return ResponseQuery.newBuilder().setCode(CodeType.BadNonce).setLog("No queries allowed").build();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.jtendermint.jabci.api.IInfo#requestInfo(com.github.jtendermint.
	 * jabci.types.Types.RequestInfo)
	 */
	@Override
	// TODO ctinnes Last App Hash has to be returned as well
	public ResponseInfo requestInfo(RequestInfo req) {
		if (this.applicationStateRepository.count() == 1) {
			ApplicationStateEntity applicationState = this.applicationStateRepository.findAll().iterator().next();
			LOG.debug("Application info requested. Persisted application state has block height "
					+ applicationState.getLastBlockHeight());
			return ResponseInfo.newBuilder().setLastBlockHeight(applicationState.getLastBlockHeight()).build();
		} else {
			LOG.warn("Unexpected application state in database. Answering with default block height "
					+ defaultBlockHeight);
			return ResponseInfo.newBuilder().setLastBlockHeight(defaultBlockHeight).build();
		}

	}

	/**
	 * Getter for the last block height handled by the application or default block
	 * height, if last block height has not been set.
	 * 
	 * @return the lastBlockHeight or defaultBlockHeight if not set.
	 */
	@Override
	public long getLastBlockHeight() {
		if (lastBlockHeight != null) {
			return lastBlockHeight;
		} else {
			return defaultBlockHeight;
		}

	}

}
