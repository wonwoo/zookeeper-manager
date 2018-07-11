# zookeeper-manager
[![Build Status](https://semaphoreci.com/api/v1/wonwoo/zookeeper-manager/branches/master/badge.svg)](https://semaphoreci.com/wonwoo/zookeeper-manager)
[![Coverage Status](https://coveralls.io/repos/github/wonwoo/zookeeper-manager/badge.svg?branch=master)](https://coveralls.io/github/wonwoo/zookeeper-manager?branch=master)

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

zookeeper config 

```
zookeeper.connect-string=...
zookeeper.admin.uri=...
zookeeper.base-sleep-time-ms=...
zookeeper.block-until-connected-unit=...
zookeeper.block-until-connected-wait=...
zookeeper.max-retries=...
zookeeper.max-sleep-ms=...

```


### version 

| branch                 | zookeeper            |
| ---------------------- | ---------------------|
| 3.4                    | 3.4.x                |
| master                 | 3.5.x                |
