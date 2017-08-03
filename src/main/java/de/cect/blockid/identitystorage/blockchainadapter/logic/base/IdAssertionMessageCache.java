package de.cect.blockid.identitystorage.blockchainadapter.logic.base;

import java.util.ArrayList;
import java.util.List;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.generated.IdentityAssertionProto.IdentityAssertionMessage;

/**
 * Very simple in-memory cache implementation for identity assertions.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 */

/*
 * TODO ctinnes adding new values to the cache should be blocked while list is /
 * getting / fetched. Mabe take a concurrent implementation for array lists
 * instead of / this. Also a better (persistend) cache could be used. (E.g. use
 * hazlecast, eh-chache or the allready used postgres).
 */
public class IdAssertionMessageCache {

	private List<IdentityAssertionMessage> idAssertionMessageCache;

	/**
	 * Constructor initializing the cache.
	 */
	public IdAssertionMessageCache() {
		this.idAssertionMessageCache = new ArrayList<>();
	}

	/**
	 * Adds the given message to the cache
	 * 
	 * @param message
	 *            a identitz assertion proto message
	 */
	public void addMessage(IdentityAssertionMessage message) {
		idAssertionMessageCache.add(message);
	}

	/**
	 * Gets all proto messages in the cache and clears the cache.
	 * 
	 * @return a list of proto messages that have been cached.
	 */
	public synchronized List<IdentityAssertionMessage> fetchMessages() {
		List<IdentityAssertionMessage> returnList = new ArrayList<>(idAssertionMessageCache);
		this.idAssertionMessageCache.clear();
		return returnList;
	}

}
