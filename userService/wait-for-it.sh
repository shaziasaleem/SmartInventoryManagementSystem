#!/usr/bin/env bash
until nc -z kafka 9092; do
  echo "Waiting for Kafka..."
  sleep 1
done
exec "$@"
