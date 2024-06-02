

bin/kafka-topics.sh --create --topic transactions \
--bootstrap-server 127.0.0.1:9092 \
--partitions 1 --replication-factor 1 --config retention.ms=86400000

bin/kafka-topics.sh --create --topic transactions-aggregation \
--bootstrap-server 127.0.0.1:9092 \
--partitions 1 --replication-factor 1 --config retention.ms=86400000

bin/kafka-topics.sh --delete --topic transactions --bootstrap-server 127.0.0.1:9092

bin/kafka-topics.sh --delete --topic transactions-aggregation --bootstrap-server 127.0.0.1:9092
