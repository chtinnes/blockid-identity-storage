package de.cect.blockid.identitystorage.datamanager.logic.api.usecase;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;

/**
 * Usecase receiving a specific identity assertion specified by receiver
 * address, sender address and identity assertion name.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface UcGetSpecificIdentityAssertion {

	/**
	 * Fetches a specific identity assertions from the database for a given
	 * receiver, sender and attribute name. This should be uniquely identified by
	 * the given parameters.
	 * 
	 * @param the
	 *            given receiver address in base64 encoding.
	 * @param the
	 *            given sender address in base64 encoding.
	 * @param the
	 *            identity attribute name.
	 * @return the persisted identity assertion messages.
	 * 
	 */
	IdAssertionMessageTo getSpecificIdentityAssertion(String receiverAddressBase64, String senderAddressBase64,
			String identityAttributeName);

}
