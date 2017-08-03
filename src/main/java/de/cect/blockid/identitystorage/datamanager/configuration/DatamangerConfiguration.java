package de.cect.blockid.identitystorage.datamanager.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuration for the data manager.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@EnableScheduling
@EntityScan("de.cect.blockid.identitystorage.datamanager")
@Configuration
public class DatamangerConfiguration {

}
