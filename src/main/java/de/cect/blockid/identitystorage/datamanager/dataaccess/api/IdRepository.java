package de.cect.blockid.identitystorage.datamanager.dataaccess.api;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.cect.blockid.identitystorage.datamanager.dataaccess.api.entities.IdAssertionMessageEntity;

/**
 * A spring data repository to access the id database.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
/*
 * TODO ctinnes there should be a unique constraint for sender, receiver and
 * assertion name in the database.
 */
public interface IdRepository extends CrudRepository<IdAssertionMessageEntity, Long> {
	/**
	 * Find all persisted entities.
	 * 
	 * @return the list of all entities.
	 */
	List<IdAssertionMessageEntity> findAll();

	/**
	 * Find all entities for a given receiver address and attribute name.
	 * 
	 * @param identityAssertionReceiverAddressBase64
	 *            the receiver address base64 encoded.
	 * @return the list of identity assertions for the given receiver.
	 */
	@Query("select am from IdAssertionMessageEntity am where am.idAssertion.identityAssertionReceiverAddressBase64 = ?1")
	List<IdAssertionMessageEntity> findAllByIdAssertionIdentityAssertionReceiverAddressBase64(
			String identityAssertionReceiverAddressBase64);

	/**
	 * Find the identity assertion for the given receiver, sender and attribute
	 * name.
	 * 
	 * @param identityAssertionReceiverAddressBase64
	 * @param identityAssertionSenderAddressBase64
	 * @param identityAttributeName
	 * @return the identity assertion entity or null.
	 */
	@Query("select am from IdAssertionMessageEntity am where am.idAssertion.identityAssertionReceiverAddressBase64 = ?1 and am.idAssertion.identityAttributeName = ?3 and am.identityAssertionSenderAddressBase64 = ?2")
	IdAssertionMessageEntity findByIdAssertionIdentityAssertionReceiverAddressBase64AndIdentityAssertionSenderAddressBase64AndIdAssertionIdentityAttributeName(
			String identityAssertionReceiverAddressBase64, String identityAssertionSenderAddressBase64,
			String identityAttributeName);
}