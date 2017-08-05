package de.cect.blockid.identitystorage.blockchainadapter.logic.impl.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.generated.IdentityAssertionProto.IdentityAssertionMessage;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionTo;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.usecase.UcFetchIdAssertions;
import de.cect.blockid.identitystorage.blockchainadapter.logic.base.IdAssertionMessageCache;

/**
 * Implementation of {@link UcFetchIdAssertions}.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Component
public class UcFetchIdAssertionsImpl implements UcFetchIdAssertions {

	@Autowired
	private IdAssertionMessageCache messageCache;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cect.blockid.identitystorage.identitystorage.blockchainadapter.logic.api.usecase.UcFetchIdAssertions#
	 * fetchIdAssertions()
	 */
	@Override
	public List<IdAssertionMessageTo> fetchIdAssertions() {
		List<IdentityAssertionMessage> messages = this.messageCache.fetchMessages();

		return messages.stream().map(proto -> mapFromProtoToTransportObject(proto)).collect(Collectors.toList());
	}

	/**
	 * Simple mapper from the proto object to the transport object.
	 * 
	 * @param messageProto
	 *            the proto message
	 * @return the transport object.
	 */
	private IdAssertionMessageTo mapFromProtoToTransportObject(IdentityAssertionMessage messageProto) {
		IdAssertionMessageTo messageTo = new IdAssertionMessageTo();
		IdAssertionTo identityAssertion = new IdAssertionTo();
		identityAssertion.setIdentityAssertionReceiverAddressBase64(
				messageProto.getIdentityAssertion().getReceiverAdressBase64());
		identityAssertion.setIdentityAttributeName(messageProto.getIdentityAssertion().getIdentityAttributeName());
		identityAssertion.setIdentityAssertionSecretEncBase64(
				messageProto.getIdentityAssertion().getIdentitiyMarkSecretBase64());
		identityAssertion.setIdentityAttributeValueEncBase64(
				messageProto.getIdentityAssertion().getIdentityAttributeValueBase64());
		messageTo.setIdentityAssertion(identityAssertion);
		messageTo.setIdentityAssertionSenderAddressBase64(messageProto.getSenderAdressBase64());
		messageTo.setIdentityAssertionSignatureBase64(messageProto.getMessageSignatureBase64());
		return messageTo;
	}

}
