# quarkus-camel-amq

## AMQ7 and quarkus

Artemis broker

```
oc new-project amqp --display-name="AMQP" --description="AMQP"
oc new-app amq-broker-73-basic -p AMQ_PASSWORD=admin -p AMQ_USER=admin
```

Run application locally

```
oc port-forward $(oc get pods -lapplication=broker -o name) 5672
cd /home/mike/git/quarkus-examples/camel-amq
mvn compile quarkus:dev
```

Source to image application build

```
oc new-build --name=quarkus-todo-app quay.io/eformat/quarkus-native-s2i:graalvm-19.0.2~https://github.com/eformat/quarkus-camel-amq
```
