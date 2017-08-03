package de.cect.blockid.identitystorage.blockchainadapter.logic.impl.usecase;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.dataaccess.ApplicationStateRepository;
import de.cect.blockid.identitystorage.blockchainadapter.dataaccess.entities.ApplicationStateEntity;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.IBlockchainListener;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcPersistApplicationState;

/**
 * Implementation of {@link UcPersistApplicationState}.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class UcPersistApplicationStateImpl implements UcPersistApplicationState {
	private static final Logger LOG = LoggerFactory.getLogger(UcPersistApplicationStateImpl.class);
	@Autowired
	private ApplicationStateRepository applicationStateRepository;

	@Autowired
	private IBlockchainListener blockchainListener;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.
	 * UcPersistApplicationState#persistApplicationState()
	 */
	@Override
	public void persistApplicationState() {
		long lastBlockHeight = this.blockchainListener.getLastBlockHeight();
		ApplicationStateEntity applicationState;

		LOG.debug("New application state will be persisted for block height " + lastBlockHeight);
		if (this.applicationStateRepository.count() == 0) {
			LOG.info("No application state persisted yet. New application state will be persisted for block height "
					+ lastBlockHeight);
			applicationState = new ApplicationStateEntity();
		} else if (this.applicationStateRepository.count() != 1) {
			LOG.error(
					"Application state not as expected. Only one application state entry is expected in the database.");
			throw new IllegalStateException("Application state not as expected");
		} else {
			applicationState = this.applicationStateRepository.findAll().iterator().next();
		}

		applicationState.setLastBlockHeight(lastBlockHeight);
		applicationState.setLastModified(Instant.now());
		this.applicationStateRepository.save(applicationState);
	}

}
