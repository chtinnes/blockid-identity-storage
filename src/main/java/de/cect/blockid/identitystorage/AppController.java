package de.cect.blockid.identitystorage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.BlockchainAdapter;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionTo;
import de.cect.blockid.identitystorage.datamanager.logic.api.Datamanager;
import de.cect.blockid.identitystorage.general.api.constants.ApplicationConstants;
import de.cect.blockid.identitystorage.general.api.constants.ApplicationProfiles;
import de.cect.blockid.identitystorage.general.api.to.RequestForIdentityReceiver;
import de.cect.blockid.identitystorage.general.api.to.RequestForSpecificIdentityAssertion;

/**
 * Basic controller for exposing application functionality.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@RestController
@RequestMapping(path = ApplicationConstants.REST_ROOT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
/*
 * TODO ctinnes Validation should take place here, at least for incoming
 * requests. Request and Response object should have more user-friendly names.
 * This can be done by use of jackson annotations.
 */
// TODO ctinnes proper response object for the method sendIdentityAssertion
public class AppController {
	private static final Logger LOG = LoggerFactory.getLogger(AppController.class);
	@Autowired
	private BlockchainAdapter blockchainAdapter;

	@Autowired
	private Datamanager datamanager;

	@RequestMapping("/version")
	public String getVersion() {
		return ApplicationConstants.APP_VERSION;
	}

	@RequestMapping("/sendDummy")
	@Profile(ApplicationProfiles.TEST)
	public String sendDummy() {
		IdAssertionMessageTo messageTo = new IdAssertionMessageTo();
		IdAssertionTo assertion = new IdAssertionTo();
		assertion.setIdentityAssertionReceiverAddressBase64("bob");
		assertion.setIdentityAssertionSecretEncBase64("PIN");
		assertion.setIdentityAttributeName("eyecolor");
		assertion.setIdentityAttributeValueEncBase64("green");
		messageTo.setIdentityAssertion(assertion);
		messageTo.setIdentityAssertionSenderAddressBase64("alice");
		messageTo.setIdentityAssertionSignatureBase64("signatur");

		return this.blockchainAdapter.sendIdAssertion(messageTo);
	}

	@RequestMapping(path = "/getAssertion", method = RequestMethod.POST)
	public IdAssertionMessageTo getSpecificIdentityAssertion(@RequestBody RequestForSpecificIdentityAssertion request) {
		return this.datamanager.getSpecificIdentityAssertion(request.getReceiverAddressBase64(),
				request.getSenderAddressBase64(), request.getIdentityAttributeName());
	}

	@RequestMapping(path = "/getAllForReceiver", method = RequestMethod.POST)
	public List<IdAssertionMessageTo> getAllForReceiver(@RequestBody RequestForIdentityReceiver request) {
		return this.datamanager.getAllIdentityAssertionsForReceiver(request.getReceiverAddressBase64());
	}

	@RequestMapping(path = "/sendIdentityAssertion", method = RequestMethod.POST)
	public String sendIdentityAssertion(@RequestBody IdAssertionMessageTo identityAssertion) {
		LOG.debug("Incoming identity assertion for receiver "
				+ identityAssertion.getIdentityAssertion().getIdentityAssertionReceiverAddressBase64());
		return this.blockchainAdapter.sendIdAssertion(identityAssertion);
	}

}
