package de.cect.blockid.identitystorage.general.api.to;

/**
 * Object for incoming REST request asking for a specific identity assertions
 * specified by receiver address, sender address and identity assertion name.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public class RequestForSpecificIdentityAssertion {

	private String receiverAddressBase64;

	private String senderAddressBase64;

	private String identityAttributeName;

	/**
	 * @return the senderAddressBase64
	 */
	public String getSenderAddressBase64() {
		return senderAddressBase64;
	}

	/**
	 * @param senderAddressBase64
	 *            the senderAddressBase64 to set
	 */
	public void setSenderAddressBase64(String senderAddressBase64) {
		this.senderAddressBase64 = senderAddressBase64;
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
	 * @return the receiverAddressBase64
	 */
	public String getReceiverAddressBase64() {
		return receiverAddressBase64;
	}

	/**
	 * @param receiverAddressBase64
	 *            the receiverAddressBase64 to set
	 */
	public void setReceiverAddressBase64(String receiverAddressBase64) {
		this.receiverAddressBase64 = receiverAddressBase64;
	}

}
