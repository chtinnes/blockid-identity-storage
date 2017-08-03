package de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase;

/**
 * Use case for the persistence of the application state, e.g. the current
 * blockchain block height.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface UcPersistApplicationState {
	/**
	 * Method for persisting the application state. This should be called within a
	 * transaction.
	 */
	void persistApplicationState();
}
