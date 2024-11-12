# Sample Spring Cloud Config Server with HashiCorp Vault secrets storage
This is a project intended to utilize Spring Cloud Config functionality via Git configs & Vault storage 

## Table of contents
- [Data protection](#data-protection)
- [Vault storage](#vault-storage)
- [Git config](#git-config)
- [Local deployment](#local-deployment)

### Data protection
To achieve data privacy - config server provides opportunity to encrypt & decrypt data using symmetric algorithm -
`encryption.key` must be specified in such a case at `application.yml` properties (sidenote: subject to be also externalized at Vault)

`Content-Type: text/plain` is mandatory when data contains url-unsafe symbols - to encrypt the value correctly and ensure that decryption result will produce the string that was passed initially

```shell
curl -X "POST" "http://localhost:8888/config-server/encrypt" \
  -H "Content-Type: text/plain" \
  -d "kkllk+ a"
```

```sh
curl -X "POST" "http://localhost:8888/config-server/decrypt" \
  -H "Content-Type: text/plain" \
  -d "9e5fcc9ab837f4c1d3ad5e1e90d3eeb9f07531b8859a37695c097baa943177bc"
```

### Vault storage
External secrets can be persisted at Vault secrets storage

This project utilizes authentication via token, issued by Vault (pre-hardcoded in dev mode)

It's important to pay attention to Vault kv backend, since it affects retrieval strategy (`/data` path part when  Vault `kv=2`)

Spring profile in such a case should contain `vault` one

```shell
vault kv put secret/spring-config-server foo=bar baz=bam
vault kv put secret/integration foo=my-integration-bar
```

### Git config
Externalized configuration from Git can be achieved both by local & external Git repositories

For local repository file url should be specified: `spring.cloud.config.server.git.uri=file://<path/to/configs/directory>`. It's important to have initialized git repository at that directory

For remote repository ssh key/rsa key should be used in order to have rights for config server to clone repository

Spring profile in such a case should contain `git` one

### Local deployment
Local deployment utilizes service image composed by Dockerfile with the related vault image located in the same network

All necessary startup files are localed at `infrastructure` directory:
- `configs` - git repository mounted to `config-server` container 
- `docker-compose.yml` - compose yaml describing services deployed - `config-server` & `vault`
- `init-vault.sh` - init script mounted to `vault` container to put predefined secrets to Vault via entrypoint
- `vault.hcl` - an alternative to describe Vault configuration comparing to environment variables & startup script (left unused since UI is inaccessible in such a case)
