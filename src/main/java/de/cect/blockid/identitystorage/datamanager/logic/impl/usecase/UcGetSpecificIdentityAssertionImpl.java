package de.cect.blockid.identitystorage.datamanager.logic.impl.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.datamanager.dataaccess.api.IdRepository;
import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcGetSpecificIdentityAssertion;
import de.cect.blockid.identitystorage.datamanager.logic.base.IdAssertionMessageEntityMapper;

/**
 * Implementation of {@link UcGetSpecificIdentityAssertion}.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class UcGetSpecificIdentityAssertionImpl implements UcGetSpecificIdentityAssertion {
	@Autowired
	private IdRepository idRepository;

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
		return IdAssertionMessageEntityMapper.mapMessageEntityToTransportObject(this.idRepository
				.findByIdAssertionIdentityAssertionReceiverAddressBase64AndIdentityAssertionSenderAddressBase64AndIdAssertionIdentityAttributeName(
						receiverAddressBase64, senderAddressBase64, identityAttributeName));
	}

}
