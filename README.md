# Simios
### Validador de DNA

Essa aplicação tem a funcionalidade de catalogar DNAs e testar se um DNA é de um simio (mutante) ou não.
Também é possível obter a proporção de DNAs Simio para cada DNA Humano, através dos DNAs catalogados.

## Tecnologias Utilizadas

* Java 11
* Spring-Boot
* Spring-Fox
* Spring-Data
* Spring-Throttling

## Bancos de Dados
* Postgres (Prod)
* H2 (Dev e test)

## Formas de subir a aplicação

>>Maven -> Dentro da pasta raiz do projeto executar o comando `mvn spring-boot:run `

>>JAR -> Após compilar o projeto (`mvn clean install`) executar o jar gerado na pasta target com o comando `java -jar simios-1.0.0-SNAPSHOT.jar`

## Usando a API

* Para solicitar a validação de um DNA:

>> Exemplo Local: `curl -X POST "http://localhost:8080/dna/simian" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dna\": [ \"ATCC\", \"AGCA\", \"TTCT\", \"AAAA\" ]}"`

>> Exemplo Heroku: `curl -X POST "https://dna-validator.herokuapp.com/dna/simian" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dna\": [ \"ATCC\", \"AGCA\", \"TTCT\", \"AAAA\" ]}"`

* Para solicitar as estatistica dos DNAs catalogados:
>> Exemplo Local: `curl -X GET "http://localhost:8080/dna/stats" -H "accept: application/json"`

>> Exemplo Heroku: `curl -X GET "https://dna-validator.herokuapp.com/dna/stats" -H "accept: application/json"`


## UI Swagger
Para facilitar a utilização do sistema, foi adicionado no projeto uma interface Swagger que pode ser acessada através do [Local](http://localhost:8080/dna/swagger-ui.html) ou [Heroku](https://dna-validator.herokuapp.com/dna/swagger-ui.html).

## URL da API no Heroku
[Heroku](https://dna-validator.herokuapp.com/dna/actuator/health)

## Decisões do desenvolvedor
**Implementação:** Foi optado fazer duas implementações uma que é mais fácil de ser entenda e também de fazer manutenção (SimiosReadableService) e outra mais performatica (SimiosPerformingService). O projeto está utilizando apenas a mais performatica, mas poderia ser mudada a implementação apenas mudando o qualificador da injeção de dependência.
*  Na implementação mais performática foi optado por validar as linhas e as colunas de uma só vez, dentro de um mesmo `for` e depois de validar as diagonais
*  Na implementação mais legivel foi optado por validar as linhas, depois gerar as colunas e validá las, gerar as diagonais e validá las.

**Throttling:** De acordo com uma observação informada no teste, dizendo que "a API pode receber flutuações de tráfego agressivas", foi decido adicionar um framework para fazer **Throttling** via código. Porém o mais indicado é que isso seja feito via infraestrutura e não via código.
