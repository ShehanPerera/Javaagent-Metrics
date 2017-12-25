# Java-Agent-Byte-Buddy-for-measure-execution-time
This is a sample agent that can used metrics of a program. I used this for get metrics of netty-server.

<b>How to use WSO2-Performance-Common</b>

1.Download wso2-performance-common form github

  `git clone https://github.com/wso2/performance-common.git`

2.Go to the directory and install the package

  `mvn clean install`
 
 3.Open netty-http-echo-service from intellij idea and run it.
 
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

  1.Download Javaagent-Metrics from hithub 
  
    `git clone https://github.com/ShehanPerera/Java-Agent-Byte-Buddy-for-measure-execution-time-.git`
  
  2. Copy all files from 'Java-Agent-Byte-Buddy-for-measure-execution-time-/src/main/java/com/github/shehanperera/javaagent'
  to 'performance-common/components/netty-http-echo-service/src/main/java/org/wso2/performance/common/netty/echo'
  
  3. Add dependecy to netty-server pom
  
    `<dependency>
            <groupId>org.wso2.carbon.config</groupId>
            <artifactId>org.wso2.carbon.config</artifactId>
            <version>2.1.4</version>
        </dependency>
        <dependency>
            <groupId>net.bytebuddy</groupId>
            <artifactId>byte-buddy</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.wso2.carbon.metrics</groupId>
            <artifactId>org.wso2.carbon.metrics.core</artifactId>
            <version>2.3.6-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>2.12.0</version>
        </dependency>`

4. Add this code for EchoHttpServer
    
    ` static {
        Agent.premain("", ByteBuddyAgent.install());
    }`
 
 5. Run netty-server and goto `jconsole` and we can get metrics data from it 
  
  ![screenshot from 2017-12-25 12-40-51](https://user-images.githubusercontent.com/29086284/34335244-49d51d5a-e973-11e7-80f6-1c45788aba9c.png)

