package de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;

/**
 * Usecase for broadcasting a transaction over the blockchain node configured in
 * this adapter.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface UcSendIdAssertion {

	/**
	 * Broadcast a transaction over the blockchain node configured in this adapter.
	 * 
	 * @param idAssertion
	 *            the identity assertion to be broadcasted. Throwing
	 *            {@link RuntimeException} when required fields are not set.
	 * @throws {@link
	 *             RuntimeException} when required fields of the parameter are not
	 *             set.
	 */
	// TODO ctinnes the runtime exception part is still to be implemented
	String sendIdAssertion(IdAssertionMessageTo idAssertion);

}
