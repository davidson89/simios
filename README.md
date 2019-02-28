# Simios
Validador de DNA

Essa aplicação tem a funcionalidade de catalogar DNAs e testar se um DNA é de um simio (mutante) ou não

#Tecnologias Utilizadas

* Java 11
* Spring-Boot
* Spring-Fox
* Spring-Data

#Bancos de Dados
* Postgres (Prod)
* H2 (Dev e test)

#Formas de subir a aplicação

>>Maven -> Dentro da pasta raiz do projeto executar o comando `mvn spring-boot:run `

>>JAR -> Após compilar o projeto (`mvn clean install`) executar o jar gerado na pasta target com o comando `java -jar simios-1.0.0-SNAPSHOT.jar`

#Usando a API

* Para solicitar a validação de um DNA:

>> Exemplo: `curl -X POST "http://localhost:8080/dna/simian" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dna\": [ \"ATCC\", \"AGCA\", \"TTCT\", \"AAAA\" ]}"`

* Para solicitar as estatistica dos DNAs catalogados:
>> Exemplo: `curl -X GET "http://localhost:8080/dna/stats" -H "accept: application/json"`


#UI
Para facilitar a utilização do sistema, foi adicionado no projeto uma interface Swagger que pode ser acessada através do [link](http://localhost:8080/swagger-ui.html).

#URL da API no Heroku
[Heroku](http://localhost:8080/dna/actuator/health)

