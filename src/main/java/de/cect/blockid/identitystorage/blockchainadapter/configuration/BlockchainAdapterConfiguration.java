package de.cect.blockid.identitystorage.blockchainadapter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jtendermint.jabci.socket.TSocket;

import de.cect.blockid.identitystorage.blockchainadapter.dataaccess.ApplicationStateRepository;
import de.cect.blockid.identitystorage.blockchainadapter.logic.api.IBlockchainListener;
import de.cect.blockid.identitystorage.blockchainadapter.logic.base.BlockchainListener;
import de.cect.blockid.identitystorage.blockchainadapter.logic.base.IdAssertionMessageCache;

/**
 * Configuration for the blockchain adapter.
 * 
 * @author ctinnes
 *
 */
@Configuration
@EntityScan("de.cect.blockid.identitystorage.blockchainadapter")
public class BlockchainAdapterConfiguration {

	@Autowired
	private ApplicationStateRepository applicationStateRepository;

	@Value("${blockchain.node.port}")
	private int port;

	@Bean
	public IdAssertionMessageCache messageCache() {
		return new IdAssertionMessageCache();
	}

	@Bean
	public IBlockchainListener counterListener() {
		return new BlockchainListener(messageCache(), this.applicationStateRepository);
	}

	@Bean
	public TSocket blockchainSocket() {
		TSocket socket = new TSocket();
		socket.registerListener(counterListener());
		new Thread(new Runnable() {
			public void run() {
				socket.start(port);
			}
		}).start();
		return socket;
	}

}
