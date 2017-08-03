package de.cect.blockid.identitystorage.blockchainadapter.dataaccess.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity for the persistence of the application state.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Entity
public class ApplicationStateEntity implements Serializable {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private Long lastBlockHeight;

	@NotNull
	private Instant lastModified;

	/**
	 * @return the lastModified
	 */
	public Instant getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified
	 *            the lastModified to set
	 */
	public void setLastModified(Instant lastModified) {
		this.lastModified = lastModified;
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
	 * @return the lastBlockHeight
	 */
	public Long getLastBlockHeight() {
		return lastBlockHeight;
	}

	/**
	 * @param lastBlockHeight
	 *            the lastBlockHeight to set
	 */
	public void setLastBlockHeight(Long lastBlockHeight) {
		this.lastBlockHeight = lastBlockHeight;
	}

}
