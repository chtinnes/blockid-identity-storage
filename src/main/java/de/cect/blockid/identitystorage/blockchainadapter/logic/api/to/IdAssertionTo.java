
package de.cect.blockid.identitystorage.blockchainadapter.logic.api.to;

/**
 * Object representing an identity assertion, where the attribute value might be
 * symmetrically encrypted by a secret, whereas the secret itself is
 * asymmetrically encrypted by the receivers public key and contained in the
 * assertion. This ensures, that sensitive information can be kept secret and
 * only be exposed if necessary. The secret itself is optional, where it is
 * assumed that in this case the attribute value is encrypted by a default key.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public class IdAssertionTo {

	private String identityAssertionReceiverAddressBase64;
	private String identityAttributeName;
	private String identityAttributeValueEncBase64;
	private String identityAssertionSecretEncBase64;

	/**
	 * @return the identityAssertionReceiverAddressBase64
	 */
	public String getIdentityAssertionReceiverAddressBase64() {
		return identityAssertionReceiverAddressBase64;
	}

	/**
	 * @param identityAssertionReceiverAddressBase64
	 *            the identityAssertionReceiverAddressBase64 to set
	 */
	public void setIdentityAssertionReceiverAddressBase64(String identityAssertionReceiverAddressBase64) {
		this.identityAssertionReceiverAddressBase64 = identityAssertionReceiverAddressBase64;
	}

	/**
	 * @return the identityAttributeName
	 */
	public String getIdentityAttributeName() {
		return identityAttributeName;
	}

	/**
	 * @param identityAttributeName
	 *            the identityAttributeName to set
	 */
	public void setIdentityAttributeName(String identityAttributeName) {
		this.identityAttributeName = identityAttributeName;
	}

	/**
	 * @return the identityAttributeValueEncBase64
	 */
	public String getIdentityAttributeValueEncBase64() {
		return identityAttributeValueEncBase64;
	}

	/**
	 * @param identityAttributeValueEncBase64
	 *            the identityAttributeValueEncBase64 to set
	 */
	public void setIdentityAttributeValueEncBase64(String identityAttributeValueEncBase64) {
		this.identityAttributeValueEncBase64 = identityAttributeValueEncBase64;
	}

	/**
	 * Getter for the optional secret.
	 * 
	 * @return the identityAssertionSecretEncBase64 or null or empty string if not
	 *         set.
	 */
	public String getIdentityAssertionSecretEncBase64() {
		return identityAssertionSecretEncBase64;
	}

	/**
	 * Setter for the optional identity assertion secret.
	 * 
	 * @param identityAssertionSecretEncBase64
	 *            the identityAssertionSecretEncBase64 to set or null or empty
	 *            string if attribute value is encrypted by default key.
	 */
	public void setIdentityAssertionSecretEncBase64(String identityAssertionSecretEncBase64) {
		this.identityAssertionSecretEncBase64 = identityAssertionSecretEncBase64;
	}

}
