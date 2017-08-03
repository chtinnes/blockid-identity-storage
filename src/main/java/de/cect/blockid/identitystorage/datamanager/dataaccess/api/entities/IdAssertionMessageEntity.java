package de.cect.blockid.identitystorage.datamanager.dataaccess.api.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity object for Identity Assertion Messages which is used for persistence
 * of the identity data.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
// TODO ctinnes add constraints/validation
@Entity
public class IdAssertionMessageEntity implements Serializable {
	/**
	 * Default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	@NotNull
	private IdAssertionEntity idAssertion;

	@NotNull
	private String identityAssertionSenderAddressBase64;

	@NotNull
	private String identityAssertionSignatureBase64;

	/**
	 * Default constructor.
	 */
	public IdAssertionMessageEntity() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idAssertion
	 */
	public IdAssertionEntity getIdAssertion() {
		return idAssertion;
	}

	/**
	 * @param idAssertion
	 *            the idAssertion to set
	 */
	public void setIdAssertion(IdAssertionEntity idAssertion) {
		this.idAssertion = idAssertion;
	}

	/**
	 * @return the identityAssertionSenderAddressBase64
	 */
	public String getIdentityAssertionSenderAddressBase64() {
		return identityAssertionSenderAddressBase64;
	}

	/**
	 * @param identityAssertionSenderAddressBase64
	 *            the identityAssertionSenderAddressBase64 to set
	 */
	public void setIdentityAssertionSenderAddressBase64(String identityAssertionSenderAddressBase64) {
		this.identityAssertionSenderAddressBase64 = identityAssertionSenderAddressBase64;
	}

	/**
	 * @return the identityAssertionSignatureBase64
	 */
	public String getIdentityAssertionSignatureBase64() {
		return identityAssertionSignatureBase64;
	}

	/**
	 * @param identityAssertionSignatureBase64
	 *            the identityAssertionSignatureBase64 to set
	 */
	public void setIdentityAssertionSignatureBase64(String identityAssertionSignatureBase64) {
		this.identityAssertionSignatureBase64 = identityAssertionSignatureBase64;
	}

}
