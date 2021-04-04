# spring-boot-microservice-elk
Springboot | Eureka | Spring Cloud | LogStash | ElastichSearch | Kibana

* Spring Boot 
* Spring Cloud
* Spring Cloud Eureka --> Service Discovery
* Spring Cloud Gateway --> Gateway
* Spring Cloud Config --> Configuration Service Integration with git properties
* Spring Cloud Config --> Configuration Service Integration with git properties
* Spring Cloud BUS amqp --> BUS 
* Spring Cloud Stream Binder RabbitMQ --> Event Driver - Send and Recive Messages
* Spring Cloud ZipKin - Distributed Tracing 
* Spring Security
* Vault --> Manage Secrets and Protect Sensitive Data
* KeyCloak --> Open Source Identity and Access Management
* Oauth2 --> Open standard for access delegation,
* Spring Boot Actuator / RabbitMQ --> Refresh Properties without restart microservice
* Mongo DB
* MySQL

* Vault Commands

vault kv put secret/inventory-service @inventory-service.json  
vault kv put secret/order-service @order-service.json  
vault kv put secret/product-service @product-service.json  

* Docker Commands


docker run -d --name elasticsearch -h elasticsearch --net docker_local -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.12.0  

docker run -d --name kibana -h kibana --net docker_local -p 5601:5601  --link elastichsearch:elastichsearch kibana  

docker run -d --name logstash -h logstash --net docker_local --link elastichsearch:elastichsearch --rm -v "$PWD":/config-dir logstash:7.12.0 -f /conf-dir/logstash.conf  

docker run --name mysql -h mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=261088 -d mysql:latest  

docker run --name zipkin -h zipkin -d -p 9411:9411 openzipkin/zipkin  

docker run -d -h rabbitmq --name rabbitmq -p 15672:15672 rabbitmq:3-management  

docker run -d -p 8180:8180 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:12.0.4  

docker run --name keycloak -d -p 8180:8180 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:12.0.4  
