FROM openjdk:11

RUN apt-get update
RUN apt-get --assume-yes install jq vim

RUN mkdir /kafka-cli
ADD https://downloads.apache.org/kafka/3.4.0/kafka_2.12-3.4.0.tgz /kafka-cli
RUN tar -xzf /kafka-cli/kafka_2.12-3.4.0.tgz -C /kafka-cli && rm /kafka-cli/kafka_2.12-3.4.0.tgz

ENV PATH="${PATH}:/kafka-cli/kafka_2.12-3.4.0/bin"

RUN mkdir /kafka-cli/kafka-utils
COPY ./kafka_utils/* /kafka-cli/kafka-utils

ENV PATH="${PATH}:/kafka-cli/kafka-utils"

RUN mkdir /kafka-cli/config
COPY ./config /kafka-cli/config

ENTRYPOINT [ "tail", "-f", "/dev/null" ]
