DATABASE_URL=jdbc:postgresql://localhost:5432/postgres
DATABASE_USER=postgres
DATABASE_PASSWORD=postgres

build:
	mvn clean install
.PHONY: build

test:
	mvn test
.PHONY: test

run:
	mvn spring-boot run
.PHONY: run

local-migration:
	mvn -Dflyway.url=${DATABASE_URL} -Dflyway.user=${DATABASE_USER} -Dflyway.password=${DATABASE_PASSWORD} flyway:migrate
.PHONY: local-migration

docker-build:
	docker build -t brunoliveira/transactions .

docker-run:
	docker-compose --file environment/docker-compose.yml up

start-database:
	docker-compose --file environment/docker-compose.db.yml up -d

stop-database:
	docker-compose --file environment/docker-compose.db.yml down