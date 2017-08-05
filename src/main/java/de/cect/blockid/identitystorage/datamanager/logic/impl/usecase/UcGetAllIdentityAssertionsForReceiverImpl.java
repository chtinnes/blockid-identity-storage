/**
 * 
 */
package de.cect.blockid.identitystorage.datamanager.logic.impl.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.datamanager.dataaccess.api.IdRepository;
import de.cect.blockid.identitystorage.datamanager.logic.api.usecase.UcGetAllIdentityAssertionsForReceiver;
import de.cect.blockid.identitystorage.datamanager.logic.base.IdAssertionMessageEntityMapper;

/**
 * 
 * Implementation of {@link UcGetAllIdentityAssertionsForReceiver}.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class UcGetAllIdentityAssertionsForReceiverImpl implements UcGetAllIdentityAssertionsForReceiver {
	@Autowired
	private IdRepository idRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cect.blockid.identitystorage.identitystorage.datamanager.logic.api.usecase.
	 * UcGetAllIdentityAssertionsForReceiver#getAllIdentityAssertionsForReceiver(
	 * java.lang.String)
	 */
	@Override
	/*
	 * TODO ctinnes think about doing this in a paginated way or argue, why it can
	 * stay like this.
	 */
	public List<IdAssertionMessageTo> getAllIdentityAssertionsForReceiver(String receiverAddressBase64) {
		return IdAssertionMessageEntityMapper.mapMessageEntityToTransportObject(
				this.idRepository.findAllByIdAssertionIdentityAssertionReceiverAddressBase64(receiverAddressBase64));
	}

}
