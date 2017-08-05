package de.cect.blockid.identitystorage.blockchainadapter.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.BlockchainAdapter;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcFetchIdAssertions;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcPersistApplicationState;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcSendIdAssertion;

/**
 * Implementation of {@link BlockchainAdapter}.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class BlockchainAdapterImpl implements BlockchainAdapter {
	@Autowired
	private UcFetchIdAssertions fetchIdAssertions;

	@Autowired
	private UcSendIdAssertion sendIdAssertion;

	@Autowired
	private UcPersistApplicationState persistApplicationState;

	@Override
	public String sendIdAssertion(IdAssertionMessageTo idAssertion) {
		return this.sendIdAssertion.sendIdAssertion(idAssertion);
	}

	@Override
	public List<IdAssertionMessageTo> fetchIdAssertions() {
		return this.fetchIdAssertions.fetchIdAssertions();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cect.blockid.identitystorage.identitystorage.blockchainadapter.logic.api.usecase.
	 * UcPersistApplicationState#persistApplicationState()
	 */
	@Override
	public void persistApplicationState() {
		this.persistApplicationState.persistApplicationState();

	}

}
