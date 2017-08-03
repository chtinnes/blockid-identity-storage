package de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase;

import java.util.List;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;

/**
 * Usecase for fetching cached transaction that have been commited in the
 * blockchain accessed by this adapter.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface UcFetchIdAssertions {

	/**
	 * Fetches committed transactions cached in the adapter and clears the cache.
	 * Furthermore, the current application state, i.e. current block height is
	 * saved.
	 * 
	 * @return a list of cached identity assertions
	 */
	/*
	 * TODO ctinnes should be done transactional with persistence of the objects in
	 * other components or database. This could either be done by caching the
	 * transactions in a database or by locking a simple in-memory cache before this
	 * method is called.
	 */
	List<IdAssertionMessageTo> fetchIdAssertions();

}
