docker-compose -f kafka/compose/kafka.yml up -d --build

docker exec -it kafka-cli bash

	in the container:

	. create_topic first-topic
	. create_topic second-topic
	. create_topic third-topic

	exit the container 

. ./start-service java-service1
. ./start-service java-service2

python kafka/producer/producer.py
