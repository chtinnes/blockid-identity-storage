package de.cect.blockid.identitystorage.datamanager.logic.api;

import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcGetAllIdentityAssertionsForReceiver;
import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcGetSpecificIdentityAssertion;

/**
 * Interface for accessing the datamanager in the application.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface Datamanager extends UcGetAllIdentityAssertionsForReceiver, UcGetSpecificIdentityAssertion {

}
