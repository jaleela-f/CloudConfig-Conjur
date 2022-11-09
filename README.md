# Conjur Spring Cloud Config Plugin

The Conjur Spring Cloud Config plugin provides client-sid support for externalized configuration of secrets in a distributed system. The plugin can be 
integrated with existing and new Spring Boot Applications to retrieve secrets from Conjur with no code change. The Authentication parameters to connect to
the Conjur Vault are maintained as externalized properties in the Spring Cloud Config Server.

# Benefits of storing application secrets in CyberArk's vault

* Provides one central location to store and retrieve secrets for applications across all environments.
* Supports the management of static and dynamic secrets such as username and password for remote applications and resources.
* Provides credentials for external services like MySQL, PostgreSQL, Apache Cassandra, Couchbase, MongoDB, Consul, AWS, and more.

<b> Note for Kubernetes users:</b> Customers and users intending to run their Spring Boot based application in Kubernetes are encouraged to follow an alternative to the plugin solution described in this readme. Cyberark offers a Kubernetes native feature 'Push To File' described here. The documentation illustrates a process to assemble spring-boot application.properties files dynamically and avoids the need for any Java code changes in order to draw secrets directly from Conjur.

# Features

* With no code change, the existing application can integrate the Spring Cloud Config Conjur Plugin to the existing application
* Externalize the properties using the Spring cloud Config Server
* API authentication
* Retrieve secrets from the CyberArk Vault and initialize the application properties using @Value annotation

# Limitations

The Spring Cloud Config Conjur plugin does not support creating, updating or removing secrets

# Technical Requirement
--------------------------------------------
| <b> Technology            | <b> Version   |
|---------------------------|---------------|
| Java                      |    11+        |
| Conjur OSS                |    1.9+       |
| Conjur Enterprise         |    12.5+      |
| Conjur SDK (Java)         |    4.0.0      |
| Conjur API (Java)         |    5.1        |
|Spring Cloud Config Server |               |
|Spring Boot                |               |
---------------------------------------------

# Prerequisites
  
  The following are the prerequisites to run the Spring Cloud Config Conjur plugin.
  
  ## Conjur Setup
  
  Conjur (OSS or Enterprise) and the Conjur CLI are installed in the environment and running in the background. If you haven't yet done so, follow the     instructions for installing [OSS](https://www.conjur.org/get-started/quick-start/oss-environment/) and [Enterprise]https://docs.cyberark.com/Product-Doc/OnlineHelp/AAM-DAP/10.9/en/Content/Get%20Started/install-enterprise.htm
  
  ##Spring Cloud Config Server
 
  Spring Cloud Config provides server-side and client-side support for externalized configuration in a distributed system.
    * Config Server provides a central place to manage external properties for applicaiton across all environments. Best 
    * Can be integrated with any application running any language including Spring applicaiton as Spring Environment provides mapping to the \n
      property sources.
    * Configuration can be managed across the enviornment while migrating from dev to test and to production, to make sure that applicaitons have\n
      everything they need to run when they migrate.
    * Default storage is the git ,so it easily supports labelled versions of configuration environment as well as being accessible to a wide range
      of tooling for managing the content
    * Support for configuration file security through the encryption/decryption mechanism
  
  ## Spring Cloud Config Server Setup
  
  
  
  
  
  
  
  
  
  
  

