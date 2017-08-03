package de.cect.blockid.identitystorage.blockchainadapter.dataaccess;

import org.springframework.data.repository.CrudRepository;

import de.cect.blockid.identitystorage.blockchainadapter.dataaccess.entities.ApplicationStateEntity;

/**
 * Repository for persistence of application state, i.e. last block height,...
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public interface ApplicationStateRepository extends CrudRepository<ApplicationStateEntity, Long> {
}
