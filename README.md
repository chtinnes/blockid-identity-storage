# BlockId Identity Storage
## What is BlockId
BlockId is a set of applications which can be used together to create a **distributed identity provider** which stores identities based on the Tendermint Blockchain.

BlockId currently consists of 3 repos:
1. Identity Storage based on Tendermint at https://github.com/chtinnes/blockid-identity-storage/. This handles the connection to a blockchain node and stores so called identity assertions.
2. Identity Manager at https://github.com/chtinnes/blockid-identity-manager/. This handles handles creation, encryption, decryption and verification of identity assertions.
3. Deployment artifacts at https://github.com/chtinnes/blockid-deployment/

BlockId is a very lighweight and uses so called identity assertions, so that someone can only expose as less details as necessary about his identity.
These assertions are of the form:
[Someone] has [attribute] of value [attribute_value] - signed by [someone else].

Since the identity assertions are broadcasted to everyone in the distributed network the attribute_value is encrypted and can only be decrypted by the identity receiver(*someone* in the above terminology).
If the receiver needs to show this identity assertion to someone else he can reference the assertion and give him the one-time key used to encrypt the attribute_value.

The following Component Diagram shows the main componenets, their relationships and interfaces.
![Component Diagram should appear here](https://user-images.githubusercontent.com/17828327/29003245-878a89e8-7ab3-11e7-8bee-3eb2a111307b.png)

## BlockId Identity Storage
This repository contains the application which handles storage and broadcasting of the identity assertions.

It is using the following technologies:

1. SpringBoot and Spring Framework for Applications (https://projects.spring.io/spring-boot/).
2. Hibernate and Spring Data JPA as an ORM Framework (http://hibernate.org/).
3. PostgreSQL as database in identity storage (https://www.postgresql.org/) (It is very easy to replace this choice).
4. JDK 1.8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
5. Protocol Buffers for serializing the assertion messages (https://developers.google.com/protocol-buffers/). 
6. Tendermint and ABCI as "Blockchain Consensus Engine" (https://tendermint.com/docs/guides/using-tendermint).

### What does this Identity Storage?

This Application sets up a server for the blockchain node to connect to. It defines what a correct Identity Assertion Message must look like, it handles serializing and broadcasting of these messages.
Identity Assertion Messages that have been commited in the network (i.e. there has been some kind of consensus about them) are stored in a relational database.
The Application provides a REST Api to fetch identity assertions from the database or to broadcast messages to the blockchain network.

## Remarks on the used technologies
### Relational database

The database can very easily replaced by a different SQL database technology. You just need to replace the driver dependency in the pom.
You can also use in-memory database for a first getting started, since the are setup automatically at boot time by spring boot, so you don't need to fight with database setup.
For a short turorial on Spring and relational data access see [this Spring Tutorial](https://spring.io/guides/gs/relational-data-access/).

For setting up a PostgreSQL database, I can recommend [this docker image](https://docs.docker.com/samples/postgres/), which allows for a very quick database setup.

### Googles Protocol Buffers

This technology allows for a binary serilaization of objects, which is not platform specific (Works with C++, Python, Java, Go,...). The format of the objects is defined in so called proto files
and is easy to understand. The platform specific code for serialization and deserialization is automatically generated from the given proto file.
The technology has been choosen for two main reasons:

1. It is platfrom agnostic, which is very important in distributed environments, where you have no guarantee that all peers commit on one application technology to use.
2. It produces very efficient serialization, so it reduces the traffic and blockchain size compared to JSON or XML.

### Sprint Boot

It is very easy to built microservice applications in Spring Boot. Spring Boot is very well documented and provides utilties and autoconfiguration for things one uses very often in web application development.
Since we like to built microservice environments or SOA environments, a good framework to built the "small" applications needs to be choosen.
Since Spring Boot Applications can be started as simple *java jars*, spring boot applications are very easy to deploy and don't need a heavy weight application container.

### Tendermint 

Tendermint provides a blockchain application, which uses a three phase consenus protocol. It comes with a very easy interface, which connects it to applications and allows applications to define what transactions to vote for or not to vote for.
Some Blockchain implementations define this logic into the "Blockchain" and give it away from the application, which uses the blockchain data.
As for stored procedures in database, I think there should be a very strong argumentation why you give application logic out of your application or even distribute it. This is very error prone and hard to maintain and does not follow the separation of concerns paradigm.
Since Tendermint just provides the consensus enging but leaves the definition of "What is actually correct" to each application, we have choosen Tendermint as the Identity Storages *Blockchain Consensus Engine*. 

A little drawback is, that as of now Tendermint is not really well documented (in the sense to easy understand what the application is doing and how to use it).
Anyway, there is a good [overview](https://blog.cosmos.network/tendermint-in-a-nutshell-1baf4fd946ba), which helps to get a first understanding on how Tendermint works.

## Deployment of the block-id application.

For how to deploy the block-id application see [blockid-deployment](https://github.com/chtinnes/blockid-deployment/).
That Repository contains deployment artifacts to deploy the application on various environments.
