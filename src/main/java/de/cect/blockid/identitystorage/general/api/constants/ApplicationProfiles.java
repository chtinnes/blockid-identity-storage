package de.cect.blockid.identitystorage.general.api.constants;

/**
 * This class comprises constants for the profiles (spring profiles) that are
 * used by this application. Multiple spring profiles can be active at once.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
public final class ApplicationProfiles {

	/**
	 * The default application profile.
	 */
	public static final String DEFAULT = "default";
	/**
	 * The test profile, which allows for additional test specific functionality. In
	 * production, this profile should not be switched on.
	 */
	public static final String TEST = "test";

	/**
	 * Dummy constructor.
	 */
	private ApplicationProfiles() {
	}

}
