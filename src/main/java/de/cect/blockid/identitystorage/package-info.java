package de.cect.blockid.identitystorage;

/**
 * This applications handles a connection to a Tendermint Blockchain node,
 * defines the messages that contain the so called identity assertions. It
 * furthermore persists identity assertions in a database and provides a REST
 * API to broadcast identity assertions or fetch already stored identity
 * assertions.
 * 
 * The application can be seen as an identity storage based on a tendermint
 * blockchain.
 * 
 * Managing Identities, including encryption, decryption, signatures,... should
 * be handled by another application. This is a way to decouple the identity
 * storage from its management. This makes sense, since one could also use an
 * identity management with a central database and not with a blockchain based
 * identity storage.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */