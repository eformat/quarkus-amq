# quarkus-amq

- application.properties - sets the amq broker url

## AMQ7 and quarkus

Run application locally

```
docker-compose up
mvn compile quarkus:dev
```

Build native image locally

```
mvn package -Pnative -Dnative-image.docker-build=true
```

### OpenShift

Artemis broker

```
oc new-project amqp --display-name="AMQP" --description="AMQP"
oc new-app amq-broker-73-basic -p AMQ_PASSWORD=admin -p AMQ_USER=admin
```

Port forward to local if running app locally

```
oc port-forward $(oc get pods -lapplication=broker -o name) 5672
```

Source to image application build on openshift

```
oc new-build --name=quarkus-amq quay.io/eformat/quarkus-native-s2i-ubi:latest~https://github.com/eformat/quarkus-amq
oc new-app quarkus-amq
```
