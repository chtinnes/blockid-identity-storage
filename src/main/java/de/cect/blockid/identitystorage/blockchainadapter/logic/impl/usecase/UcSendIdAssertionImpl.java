/**
 * 
 */
package de.cect.blockid.identitystorage.blockchainadapter.logic.impl.usecase;

import javax.validation.ValidationException;

import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.ITransactionSender;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.generated.IdentityAssertionProto.IdentityAssertionMessage;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcSendIdAssertion;

/**
 * Implementation of {@link UcSendIdAssertion}.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class UcSendIdAssertionImpl implements UcSendIdAssertion {
	@Autowired
	private ITransactionSender transactionSender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cect.blockid.identitystorage.identitystorage.blockchainadapter.logic.api.usecase.
	 * UcSendIdAssertion#
	 * sendIdAssertion(de.cect.blockid.identitystorage.identitystorage.blockchainadapter.logic
	 * .api.to. IdAssertionMessageTo)
	 */
	@Override
	public String sendIdAssertion(IdAssertionMessageTo idAssertion) {
		IdentityAssertionMessage message = mapTransportObjectToProto(idAssertion);

		/*
		 * TODO ctinnes hex string is actually two byte per byte. Should be done more
		 * efficient at this point in the future.
		 */
		return this.transactionSender.sendTransactionHex(HexUtils.toHexString(message.toByteArray()));

	}

	/**
	 * Simple mapper function mapping the transport object to the protobuf message
	 * 
	 * @param idAssertion
	 *            the transport object.
	 * @return the proto message
	 * @throws validation
	 *             exception if necessary fields are not provided.
	 */
	private IdentityAssertionMessage mapTransportObjectToProto(IdAssertionMessageTo idAssertion) {
		IdentityAssertionMessage.Builder messageBuilder = IdentityAssertionMessage.newBuilder();
		messageBuilder
				.setIdentityAssertion(IdentityAssertionMessage.IdentityAssertion.newBuilder()
						.setIdentitiyMarkSecretBase64(
								idAssertion.getIdentityAssertion().getIdentityAssertionSecretEncBase64())
						.setIdentityAttributeName(idAssertion.getIdentityAssertion().getIdentityAttributeName())
						.setIdentityAttributeValueBase64(
								idAssertion.getIdentityAssertion().getIdentityAttributeValueEncBase64())
						.setReceiverAdressBase64(
								idAssertion.getIdentityAssertion().getIdentityAssertionReceiverAddressBase64()))
				.setSenderAdressBase64(idAssertion.getIdentityAssertionSenderAddressBase64())
				.setMessageSignatureBase64(idAssertion.getIdentityAssertionSignatureBase64());
		if (messageBuilder.isInitialized()) {
			return messageBuilder.build();
		}
		throw new ValidationException();
	}

}
