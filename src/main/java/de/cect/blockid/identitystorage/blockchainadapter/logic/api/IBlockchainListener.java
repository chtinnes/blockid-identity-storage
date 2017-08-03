package de.cect.blockid.identitystorage.blockchainadapter.logic.api;

import com.github.jtendermint.jabci.api.ICheckTx;
import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.api.IInfo;
import com.github.jtendermint.jabci.api.IQuery;

public interface IBlockchainListener extends IDeliverTx, ICheckTx, ICommit, IQuery, IInfo {

	/**
	 * Getter for the last block height, which is part of the application state.
	 * 
	 * @return the lastBlockHeight of commited transactions.
	 */
	long getLastBlockHeight();

}
