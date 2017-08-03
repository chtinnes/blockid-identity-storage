package de.cect.blockid.identitystorage.blockchainadapter.logic.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.ITransactionSender;

/**
 * Implementation of {@link ITransactionSender}
 * 
 * @author ctinnes
 *
 */
@Component
public class TransactionSender implements ITransactionSender {
	private final static String PARAMETER_TX = "tx";
	private final static String DECLARE_HEX = "0x";

	@Autowired
	private RestTemplate restClient;

	@Value("${blockchain.node.rpc.port}")
	private int port;

	@Value("${blockchain.node.url}")
	private String url;

	@Value("${blockchain.node.endpoint.broadcasttx}")
	private String broadcastTxEndpoint;

	@Override
	public String sendTransaction(String asciiRepresentation) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(PARAMETER_TX, "\\\"" + asciiRepresentation + "\\\"");
		String response = restClient.getForObject(url + ":" + port + broadcastTxEndpoint, String.class, parameters);
		return response;
	}

	@Override
	public String sendTransactionHex(String hexRepresentation) {
		String response = restClient.getForObject(
				url + ":" + port + broadcastTxEndpoint + "?" + PARAMETER_TX + "=" + DECLARE_HEX + hexRepresentation,
				String.class);
		return response;
	}

}
