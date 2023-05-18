FROM openjdk:11

RUN apt-get update
RUN apt-get --assume-yes install jq vim

RUN mkdir /kafka-cli
ADD ./kafka-cli.tgz /kafka-cli

ENV PATH="${PATH}:/kafka-cli/kafka_2.12-3.4.0/bin"

RUN mkdir /kafka-cli/kafka-utils
COPY ./kafka_utils/* /kafka-cli/kafka-utils

ENV PATH="${PATH}:/kafka-cli/kafka-utils"

RUN mkdir /kafka-cli/config
COPY ./config /kafka-cli/config

ENTRYPOINT [ "tail", "-f", "/dev/null" ]