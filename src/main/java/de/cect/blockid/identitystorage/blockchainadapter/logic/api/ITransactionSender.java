package de.cect.blockid.identitystorage.blockchainadapter.logic.api;

/**
 * A transaction sender is used to transmit transactions to tendermint node.
 * This is done by sending a rest request to the node.
 * 
 * @author ctinnes
 *
 */
public interface ITransactionSender {

	/**
	 * Requests a transaction with the given content from the tendermint node.
	 * 
	 * @param asciiRepresentation
	 *            of the bytes to be sent.
	 * @return the answer received by the node
	 */
	String sendTransaction(String asciiRepresentation);

	/**
	 * Requests a transaction with the given content from the tendermint node.
	 * 
	 * @param hexRepresentation
	 *            of the bytes to be send
	 * @return the answer received by the node
	 */
	String sendTransactionHex(String hexRepresentation);
}
