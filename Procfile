pyutils: cd $PY_UTILS_PATH && poetry run python -m bin.grpc_server
postgres: docker-compose -f postgres-docker/docker-compose.yml up
redis: docker-compose -f redis-docker/docker-compose.yml up
life-api: ./gradlew bootRun --args='--spring.profiles.active=dev'

