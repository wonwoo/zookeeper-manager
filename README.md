# zookeeper-manager

### zookeeper-manager
zookeeper web ui 

run zookeeper 
```
/zkServer.sh start
```

### install 

```
$ clone git https://github.com/wonwoo/zookeeper-manager.git
$ mvn clean install 
$ java -jar target/zookeeper-manager.jar
```

### config
default config [application.properties](https://github.com/wonwoo/zookeeper-manager/blob/master/src/main/resources/application.properties)

```
server.port=18080

spring.security.user.name=user
spring.security.user.password=123
```

### version 

| branch                 | zookeeper            |
| ---------------------- | ---------------------|
| 3.4                    | 3.4.x                |
| master                 | 3.5.x                |
