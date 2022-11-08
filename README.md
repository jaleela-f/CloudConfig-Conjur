# Conjur Spring Cloud Config Plugin
## The Conjur Spring Cloud Config plugin provides client-sid support for externalized configuration of secrets in a distributed system. The plugin can be 
integrated with existing and new Spring Boot Applications to retrieve secrets from Conjur with no code change. The Authentication parameters to connect to
the Conjur Vault are maintained as externalized properties in the Spring Cloud Config Server.

#Benefits of storing application secrets in CyberArk's vault
Provides one central location to store and retrieve secrets for applications across all environments.
Supports the management of static and dynamic secrets such as username and password for remote applications and resources.
Provides credentials for external services like MySQL, PostgreSQL, Apache Cassandra, Couchbase, MongoDB, Consul, AWS, and more.
Note for Kubernetes users: Customers and users intending to run their Spring Boot based application in Kubernetes are encouraged to follow an alternative to the plugin solution described in this readme. Cyberark offers a Kubernetes native feature 'Push To File' described here. The documentation illustrates a process to assemble spring-boot application.properties files dynamically and avoids the need for any Java code changes in order to draw secrets directly from Conjur.





he secrets can be @autowired to the existing
application using the @Value annotation. The plugin will first look for the default value, if not present will retrieve the secrets from Conjur vault and 
autowire to the @Value annotated properties. 




The Conjur Spring Boot Plugin provides client-side support for externalized configuration of secrets in a distributed system. You can \n
integrate the plugin with exisiting and new Spring Boot applications to retrieve secrets from Conjur. Using the Spring Boot Plugin, you can retrieve application credentials and secrets stored in\n
Conjur with minimal code changes to the existing Spring Boot application code.

