package de.cect.blockid.identitystorage.datamanager.logic.api.usecase;

/**
 * Usecase for persiting commited data. This usecase can for example be
 * scheduled or exposed via a service.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface UcPersistCommitedIdentityAssertions {

	/**
	 * Fetches commited identity assertions from a blockchain adapter and persists
	 * the data.
	 * 
	 * @return the number of persisted entities.
	 */
	Integer persistCommitedIdentityAssertions();

}
