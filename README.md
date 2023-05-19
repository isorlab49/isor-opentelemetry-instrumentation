# Opentelemetry automatic instrumentation with Kubernetes

## How to run

**Start up the kafka infrastructure in docker**

docker-compose -f kafka/compose/kafka.yml up -d --build

**Create required topics in kafka**

The kafka cluster is set to not enable automatic topic creation, thus it is required to create them by hand.

1) Log in the kafka cli container:

docker exec -it kafka-cli bash

2) in the container:

- . create_topic first-topic
- . create_topic second-topic
- . create_topic third-topic

3) exit the container 

**Start cert manager in kubernetes**

. ./cert-manager.sh apply

**Start OpenTelemetry operator in kubernetes**

. ./otel-op.sh

**Start OpenTelemetry collector**

kubectl apply -f otel-coll.yaml

**Start OpenTelemetry instrumentation service in kubernetes**

kubectl apply -f otel-inst.yaml

**Start java services**

. ./start-service java-service1
. ./start-service java-service2

**Start up dummy message producer**

python kafka/producer/producer.py

### Configuration notes

In kafka/compose/kafka.yml KAFKA_ADVERTISED_LISTENERS has to be updated with localhost's ip.

In both java services the bootstrap servers have to be updated with the localhost's actual ip.

After applying the otel-coll.yaml update the otel-inst.yaml's spec.exporter.endpoint to the otel-collector's SIMPLEST_COLLECTOR_PORT_4317_TCP_ADDR.
You can find it in docker desktop container inspect.

## Check kubernetes logs

kubectl logs deployment.apps/java-service1
kubectl logs deployment.apps/java-service2

## Sources

Automatic instrumentation with kubernetes operator:
https://github.com/open-telemetry/opentelemetry-operator/blob/main/README.md
