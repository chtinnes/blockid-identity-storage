package de.cect.blockid.identitystorage.datamanager.logic.base;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionMessageTo;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.to.IdAssertionTo;
import de.cect.blockid.identitystorage.datamanager.dataaccess.api.entities.IdAssertionEntity;
import de.cect.blockid.identitystorage.datamanager.dataaccess.api.entities.IdAssertionMessageEntity;

/**
 * Mapper for Identity Assertion types.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
// TODO ctinnes use proper mapping framework(e.g. Dozer) and use also for other
// mappings.
public final class IdAssertionMessageEntityMapper {
	/**
	 * The private dummy constructor to hide the public one.
	 */
	private IdAssertionMessageEntityMapper() {
	}

	/**
	 * Mapping the identity transport object to the corresponding entity.
	 * 
	 * @param message
	 *            transport object
	 * @return entity object
	 */
	public static IdAssertionMessageEntity mapMessageTransportObjectToEntity(IdAssertionMessageTo message) {
		if (message == null || message.getIdentityAssertion() == null) {
			return null;
		}
		IdAssertionMessageEntity messageEntity = new IdAssertionMessageEntity();
		IdAssertionEntity assertionEntity = new IdAssertionEntity();
		BeanUtils.copyProperties(message.getIdentityAssertion(), assertionEntity);
		BeanUtils.copyProperties(message, messageEntity);
		messageEntity.setIdAssertion(assertionEntity);
		return messageEntity;
	}

	/**
	 * Mapping the identity entity object to the corresponding transport object.
	 * 
	 * @param messageEntity
	 *            the entity object.
	 * @return transport object
	 */
	public static IdAssertionMessageTo mapMessageEntityToTransportObject(IdAssertionMessageEntity messageEntity) {
		if (messageEntity == null || messageEntity.getIdAssertion() == null) {
			return null;
		}
		IdAssertionMessageTo messageTo = new IdAssertionMessageTo();
		IdAssertionTo assertion = new IdAssertionTo();
		BeanUtils.copyProperties(messageEntity.getIdAssertion(), assertion);
		BeanUtils.copyProperties(messageEntity, messageTo);
		messageTo.setIdentityAssertion(assertion);
		return messageTo;
	}

	/**
	 * Merges the values of the given source transport object into the given entity.
	 * 
	 * @param sourceTo
	 *            the source transport object.
	 * @param targetEntity
	 *            the target entity.
	 * @return the targetEntity with the fields from the sourceTo merged.
	 */
	public static IdAssertionMessageEntity mergeEntity(IdAssertionMessageTo sourceTo,
			IdAssertionMessageEntity targetEntity) {
		if (sourceTo == null || targetEntity == null) {
			return null;
		}
		IdAssertionEntity assertion = new IdAssertionEntity();
		BeanUtils.copyProperties(sourceTo.getIdentityAssertion(), assertion);
		BeanUtils.copyProperties(sourceTo, targetEntity);
		targetEntity.setIdAssertion(assertion);
		return targetEntity;
	}

	/**
	 * Mapping the list of identity entities to the corresponding transport object
	 * list.
	 * 
	 * 
	 * @param messageEntites
	 *            the given entities.
	 * @return a list of corresponding transport objects.
	 */
	public static List<IdAssertionMessageTo> mapMessageEntityToTransportObject(
			List<IdAssertionMessageEntity> messageEntites) {
		return messageEntites.stream().map(IdAssertionMessageEntityMapper::mapMessageEntityToTransportObject)
				.collect(Collectors.toList());
	}

	/**
	 * Mapping the list of identity transport objects to the corresponding entity
	 * list.
	 * 
	 * 
	 * @param messageTos
	 *            the given transport objects.
	 * @return a list of corresponding entity objects.
	 */
	public static List<IdAssertionMessageEntity> mapMessageTransportObjectToEntity(
			List<IdAssertionMessageTo> messageTos) {
		return messageTos.stream().map(IdAssertionMessageEntityMapper::mapMessageTransportObjectToEntity)
				.collect(Collectors.toList());
	}

}
