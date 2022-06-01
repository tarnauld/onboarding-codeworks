#!/bin/bash
if [[ $# -ne 2 ]]
then
	echo "Usage ./start.sh VAULT_ADDR VAULT_TOKEN"
else
    export VAULT_ADDR=$1
    export VAULT_TOKEN=$2
    vault login $VAULT_TOKEN &> /dev/null
    export OKTA_ISSUER=`vault kv get -field=OKTA_ISSUER kv/onboarding-codeworks`
    export OKTA_CLIENT_ID=`vault kv get -field=OKTA_CLIENT_ID kv/onboarding-codeworks`
    export OKTA_CLIENT_SECRET=`vault kv get -field=OKTA_CLIENT_SECRET kv/onboarding-codeworks`
    export POSTGRES_USER=`vault kv get -field=POSTGRES_USER kv/onboarding-codeworks`
    export POSTGRES_PW=`vault kv get -field=POSTGRES_PW kv/onboarding-codeworks`
    docker-compose build
    docker-compose up
fi