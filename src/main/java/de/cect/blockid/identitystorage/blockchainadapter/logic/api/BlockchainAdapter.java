package de.cect.blockid.identitystorage.blockchainadapter.logic.api;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcFetchIdAssertions;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcPersistApplicationState;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcSendIdAssertion;

/**
 * Interface for accessing the blockchain adapter in the application.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface BlockchainAdapter extends UcFetchIdAssertions, UcSendIdAssertion, UcPersistApplicationState {
}
