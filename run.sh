mvn clean package && docker kill $(docker ps -q); docker system prune -a --force && docker build -t dkb/shorty . &&  docker-compose -f docker/docker-compose.yml up