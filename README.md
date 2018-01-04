# Java-Agent-Byte-Buddy-Metrics
This is a sample agent that can used metrics of a program. I used this for get metrics of netty-server.

<b>How to use WSO2-Performance-Common</b>

1.Download wso2-performance-common form github

  `git clone https://github.com/wso2/performance-common.git`

2.Go to the directory and install the package

  `mvn clean install`
 
3.Run Netty-server

  3.1 Go to performance-common/distribution/target and extract performance-common-distribution-0.1.0-SNAPSHOT.tar.gz

  3.2 Then go to performance-common-distribution-0.1.0-SNAPSHOT/netty-service and run netty-server

  `./netty-start.sh`
 
 4.Sending Request
 
   `curl -v http://localhost:8688 -d ‘Request’`

5.Output

   `* Rebuilt URL to: http://localhost:8688/`
   
   `* Trying 127.0.0.1…`
   
   `* Connected to localhost (127.0.0.1) port 8688 (#0)`
   
   `> POST / HTTP/1.1`
   
   `> Host: localhost:8688`
   
   `> User-Agent: curl/7.47.0`
   
   `> Accept: */*`
   
   `> Content-Length: 7`
   
   `> Content-Type: application/x-www-form-urlencoded`
   
   `>` 
   
   `* upload completely sent off: 7 out of 7 bytes`
   
   `< HTTP/1.1 200 OK`
   
   `< content-type: application/x-www-form-urlencoded`
   
   `< content-length: 7`
   
   `< connection: keep-alive`
   
   `<` 
   
   `* Connection #0 to host localhost left intact`
    
    `Request`
    
<b>Add Javaagent-Metrics for netty server</b>

  1.    Download Javaagent-Metrics from hithub

`git clone https://github.com/ShehanPerera/Javaagent-Metrics.git`

2. Go to Javaagent-Metrics directory and install it

`mvn clean install`

3.Go to target folder and copy javaagent-metrics-1.01.0-SNAPSHOT.jar file and paste to performance-common/distribution/target/performance-common-distribution-0.1.0-SNAPSHOT folder.

4.Change netty-start.sh file and add javaagent

  `....`

`echo "Starting Netty"`
`nohup java -Xms4g -Xmx4g -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:$gc_log_file -javaagent:javaagent-metrics-`           `1.01.0-SNAPSHOT.jar \`
  ` -jar $service_name-0.1.0-SNAPSHOT.jar --worker-threads 2000 --sleep-time $sleep_time > netty.out 2>&1 &`

`.....`
 
 5. Run netty-server and goto `jconsole` and we can get metrics data from it 
  
  ![screenshot from 2017-12-25 12-40-51](https://user-images.githubusercontent.com/29086284/34335244-49d51d5a-e973-11e7-80f6-1c45788aba9c.png)

