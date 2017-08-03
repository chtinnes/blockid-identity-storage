package de.cect.blockid.identitystorage.blockchainadapter;

/**
 * Component which serves as an adapter between the Tendermint blockchain node
 * and the application. 1.) It defines a socket for the Node to connect to and
 * defines how to handle the various blockchain events.<br>
 * 2.) It furthermore defines how the application specific messages, i.e.
 * identity assertions, are broadcasted to the blockchain.<br>
 * 3.) It caches commited transactions wich can then be fetched by a data
 * manager. <br>
 * 3.) It is responsible for handling the application state. This is in general
 * the height of the blockchain which has already been handled by the
 * application. In case of an application crash, only transactions since the
 * last known application state have to be replayed.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */