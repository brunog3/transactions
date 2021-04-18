DATABASE_URL=jdbc:postgresql://192.168.1.64:5432/transactions
DATABASE_USER=transactions

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
	@read -p "Enter with database password:" password; \
	mvn -Dflyway.url=${DATABASE_URL} -Dflyway.user=${DATABASE_USER} -Dflyway.password=$$password flyway:migrate
.PHONY: local-migration

initialize-flyway:
	@read -p "Enter with database password:" password; \
	mvn -Dflyway.url=${DATABASE_URL} -Dflyway.user=${DATABASE_USER} -Dflyway.password=$$password flyway:baseline
.PHONY: local-migration

docker-build:
	docker build -t brunoliveira/transactions .

docker-run:
	docker run -p 8080:8080 brunoliveira/transactions