curl -XPOST  -H "Content-Type: application/json" -d '{"identityAssertionSenderAddressBase64":"alice","identityAssertionSignatureBase64":"signatur","identityAssertion":{"identityAssertionReceiverAddressBase64":"eve","identityAttributeName":"eyecolor","identityAttributeValueEncBase64":"green","identityAssertionSecretEncBase64":"PIN"}}' http://localhost:8080/blockid/v1/sendIdentityAssertion
curl -XPOST  -H "Content-Type: application/json" -d '{"receiverAddressBase64": "bob"}' http://localhost:8080/blockid/v1/getAllForReceiver

