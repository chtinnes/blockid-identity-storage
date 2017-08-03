package de.cect.blockid.identitystorage.datamanager.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.datamanager.logic.api.Datamanager;
import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcGetAllIdentityAssertionsForReceiver;
import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcGetSpecificIdentityAssertion;

/**
 * Implementation of {@link Datamanager}
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class DatamanagerImpl implements Datamanager {

	@Autowired
	private UcGetAllIdentityAssertionsForReceiver getAllIdentityAssertionsForReceiver;

	@Autowired
	private UcGetSpecificIdentityAssertion getSpecificIdentityAssertion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cect.blockid.identitystorage.datamanager.logic.api.usecase.
	 * UcGetAllIdentityAssertionsForReceiver#getAllIdentityAssertionsForReceiver(
	 * java.lang.String)
	 */
	@Override
	public List<IdAssertionMessageTo> getAllIdentityAssertionsForReceiver(String receiverAddressBase64) {
		return this.getAllIdentityAssertionsForReceiver.getAllIdentityAssertionsForReceiver(receiverAddressBase64);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cect.blockid.identitystorage.datamanager.logic.api.usecase.
	 * UcGetSpecificIdentityAssertion#getAllIdentityAssertionsForReceiver(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public IdAssertionMessageTo getSpecificIdentityAssertion(String receiverAddressBase64, String senderAddressBase64,
			String identityAttributeName) {
		return this.getSpecificIdentityAssertion.getSpecificIdentityAssertion(receiverAddressBase64,
				senderAddressBase64, identityAttributeName);
	}

}
