# microservice-rest-java
A sample demo Microservice created using Spring Boot 2, Spring cloud (Netflix OSS), Java 8 and REST API

### Services
1. customer
2. order


#### Used

* Spring Boot 2
* Java 8
* REST
* Maven Dependency management
* Swagger Documentation
* Pivotal Cloud Foundry Deployment
* Adding spring cloud (Check Distributed System improvements)

##### Distributed System Improvements

* Service Registry and Discovery - (Spring Cloud Netflix Eureka)
* Latency and Fault Tolerance (Circuit Breaker Pattern) - (Spring Cloud Netflix Hystrix)
* API Gateway service (Proxying) - (Spring Cloud Netflix Zuul) 
* Distributed Tracing - (Spring Sleuth, Zipkin open Tracer)

##### Maintain and Monitor with Dashboards

* Service Registry and Discovery - http://localhost:8999/
* Latency and Fault Tolerance (With Circuit Breaker Pattern) - http://localhost:8980/svc-customer/hystrix/
* API Gateway service (Proxying) - http://localhost:8080/api/
* Distributed Tracing - (Zipkin Server) - http://localhost:9411/zipkin/

##### Distributed Tracing Improvements

* Generating TraceId and SpanId for all service calls
* Adding X-B3-TraceId in the Http Header Response
* Integrated with Zipkin Server to view all Trace informations

##### API Gateway service

* Proxying
