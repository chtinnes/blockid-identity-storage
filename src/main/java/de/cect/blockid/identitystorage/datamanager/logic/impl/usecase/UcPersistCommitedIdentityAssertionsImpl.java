package de.cect.blockid.identitystorage.datamanager.logic.impl.usecase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.BlockchainAdapter;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.datamanager.dataaccess.api.IdRepository;
import de.cect.blockid.identitystorage.datamanager.dataaccess.api.entities.IdAssertionMessageEntity;
import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcPersistCommitedIdentityAssertions;
import de.cect.blockid.identitystorage.datamanager.logic.base.IdAssertionMessageEntityMapper;

/**
 * Implementation of {@link UcPersistCommitedIdentityAssertions} which fetches
 * the cached identity assertions from the adapter and persists them. This
 * implementation uses a scheduler to trigger itself.
 *
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
@Transactional
public class UcPersistCommitedIdentityAssertionsImpl implements UcPersistCommitedIdentityAssertions {
	private static final Logger LOG = LoggerFactory.getLogger(UcPersistCommitedIdentityAssertionsImpl.class);

	@Autowired
	private BlockchainAdapter blockchainAdapter;

	@Autowired
	private IdRepository idRepository;

	// @Value("${datamanager.scheduler.persistcommited.interval}")
	// private long schedulerInterval;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cect.blockid.identitystorage.datamanager.logic.api.usecase.
	 * UcPersistCommitedIdentityAssertions#persistCommitedIdentityAssertions()
	 */
	@Override
	@Scheduled(fixedRate = 10000)
	/*
	 * TODO ctinnes This rate should be configurable in application.properties
	 * (Problem: It must be a constant value and values from application.properties
	 * are considerd to be variable.)
	 */
	public Integer persistCommitedIdentityAssertions() {
		List<IdAssertionMessageTo> messages = this.blockchainAdapter.fetchIdAssertions();
		messages.forEach(this::persistIdAssertionMessage);
		this.blockchainAdapter.persistApplicationState();
		LOG.debug("Persisted " + messages.size() + " entities");
		return messages.size();
	}

	private void persistIdAssertionMessage(IdAssertionMessageTo message) {
		IdAssertionMessageEntity messageEntity = this.idRepository
				.findByIdAssertionIdentityAssertionReceiverAddressBase64AndIdentityAssertionSenderAddressBase64AndIdAssertionIdentityAttributeName(
						message.getIdentityAssertion().getIdentityAssertionReceiverAddressBase64(),
						message.getIdentityAssertionSenderAddressBase64(),
						message.getIdentityAssertion().getIdentityAttributeName());
		if (messageEntity == null) {
			messageEntity = IdAssertionMessageEntityMapper.mapMessageTransportObjectToEntity(message);
		} else {
			messageEntity = IdAssertionMessageEntityMapper.mergeEntity(message, messageEntity);
			LOG.info("Overriding existing assertion for receiver "
					+ messageEntity.getIdAssertion().getIdentityAssertionReceiverAddressBase64() + ".");
		}
		this.idRepository.save(messageEntity);
	}

}
