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

    Create the 2 files with the configuration below:
    
    (02-beats-input.conf)
    
    ```text
    input {
      tcp {
        port => 5044
        ssl => false
      }
    }
    ```
    
    (30-output.conf)
    
    ```text
    filter {
      json {
        source => "message"
      }
    }
    output {
      elasticsearch {
        hosts => ["localhost:9200"]
        manage_template => false
        index => "logstash-local"
      }
    }
    ```
    
    Then create a `DockerFile` as below, using the configurations created above
    
    (DockerFile)
    
    ```dockerfile
    FROM sebp/elk
    
    # overwrite existing file
    RUN rm /etc/logstash/conf.d/30-output.conf
    COPY 30-output.conf /etc/logstash/conf.d/30-output.conf
    
    RUN rm /etc/logstash/conf.d/02-beats-input.conf
    COPY 02-beats-input.conf /etc/logstash/conf.d/02-beats-input.conf
    ```
  
    ```bash
    docker build -f docker/Dockerfile -t elk:latest .
    ```
    ```bash
    docker run -d -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --name elk elk:latest
    ```
    
    The `docker run`, command starts up Kibana on port 5601, ElasticSearch on port 9200 and LogStash on port 5044.
    
    Validate the ELK setup by accessing the web console on url,
    
    [Logstash](http://localhost:5044)
    http://localhost:5044
    
    [Elasticsearch](http://localhost:9200)
    http://localhost:9200
    
    [Kibana](http://localhost:5601)
    http://localhost:5601




