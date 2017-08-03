package de.cect.blockid.identitystorage.datamanager.logic.api.usecase;

import java.util.List;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;

/**
 * Usecase receiving all persisted identity assertions for a receiver.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface UcGetAllIdentityAssertionsForReceiver {

	/**
	 * Fetches committed identity assertions from the database for a given receiver
	 * 
	 * @param the
	 *            given receiver address in base64 encoding.
	 * @return the persisted identity assertion messages.
	 */
	List<IdAssertionMessageTo> getAllIdentityAssertionsForReceiver(String receiverAddressBase64);

}
