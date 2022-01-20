# micronaut
Test about micronaut with the microservice architecture

The following command needs to be run inside the pet-clinic-concierge, pet-clinic-reviews, pet-clinic, and pet-owner folders:  
mvn clean compile jib:dockerBuild  



Application created to test the features given for micronaut to create microservices

**Technologies**

* Java 11 and Maven
* Intellij Idea, Windows 10 Home
* Docker with docker compose
* Postgres and mongoDb
* Prometheus and grafana
* ElasticSearch, Logstash, Kibana
* Micronaut 2
* Junit ,TestContainers
* Kafka
* JWT and keyclock
* Swager
* Consul and zipkin

## Thoughts
Well micronaut is well design for microservices, they cover almost all the functionality to deply microservices , the major disadvantage is a samll
community, documentation (just the official domumentation  and out of that near to zero information) and projects.  


Check the images in docker  
docker images | grep pet-owner    

consul: http://localhost:8500/ui/dc1/services  
kafka: http://localhost:9100/  


pet-owner: http://localhost:32581/api/owners  
pet-clinic: http://localhost:32582/api/vets  
pet-clinic-reviews: http://localhost:32583/api/vet-reviews  
pet-clinic-concierge: http://localhost:32584/api/owners  


prometheus -  http://localhost:32581/prometheus  
metrics - http://localhost:32581/metrics  
