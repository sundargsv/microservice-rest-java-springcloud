### Distributed Tracing - Microservices pattern
A sample demo setting up services/ tools for distributed tracing

##### Tools and Services

1. Zipkin
2. Log stash
3. Elasticsearch
4. Kibana


##### Set-up and configuration

* Zipkin (Available in Docker Hub)

     ```bash
     docker pull openzipkin/zipkin
     ```
     ```bash
     docker run -d -p 9411:9411 openzipkin/zipkin
     ```
     
* ELK (Using sebp/elk image)

    ```bash
    git clone https://github.com/sundargsv/docker-elk.git
    ```
    ```text
    cd docker-elk
    docker-compose up
    ```
 
    Validate the ELK setup by accessing the web console on url,
    
    [Logstash](http://localhost:5044)
    TCP: localhost:5000
    
    [Elasticsearch](http://localhost:9200)
    http://localhost:9200
    
    [Kibana](http://localhost:5601)
    http://localhost:5601

###### Elastic search

```text
    curl -XPUT http://localhost:9200/logstash-local?pretty - To create an index
    curl -XGET http://localhost:9200/logstash-local/_count?pretty - To get brief details of index
    curl -XGET http://localhost:9200/logstash-local/_search?pretty=true&q=*:* - To get all documents
    curl  XGET http://localhost:9200/_cat/indices?v - To get all indices
```


