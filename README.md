# Spring Boot + Vue.js

## Setup Vue.js & Spring Boot

### Prerequisitos

#### MacOSX

```
brew install node
npm install --global vue-cli
```

#### Linux

```
sudo apt update
sudo apt install node
npm install --global vue-cli
```

#### Windows

```
choco install npm
npm install --global vue-cli
```

## Importação do projeto

* Importe o projeto como maven na sua ide preferida (recomendo a IntelliJ IDEA para Java)

## Organização do projeto

```
projeto
├─┬ backend     → modulo de backend com Spring Boot
│ ├── src
│ └── pom.xml
├─┬ frontend    → modulo de frontend Vue.js
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven pom para os dois modulos
```

## Backend

* Na pasta do backend, abra o arquivo localizado em `src>main>resources>application.properties` e altere as seguintes linhas:

```
* Altere "biblioteca_ufab" para o nome do banco de dados que deseja usar no MySQL:
spring.datasource.url = jdbc:mysql://localhost:3306/biblioteca_ufab?useSSL=false

* Altere as credenciais para o banco de dados que ira usar no projeto:
# Username and password
spring.datasource.username = root
spring.datasource.password = root

```

## Primeira inicialização

* Na pasta principal do projeto execute o seguinte comando para instalar as dependencias e criar o banco de dados:

```
mvn clean install
```

Para executar o projeto utilizando o servidor do próprio Spring Boot execute o comando:

```
mvn --projects backend spring-boot:run
```

OBS: http://localhost:8088/ é o endereço default para o backend do Spring Boot.


* Vá na pasta `frontend` (utilizando o comando `cd frontend`) e execute o seguinte comando para rodar o servidor do Vue.js:

```
npm run dev
```

OBS: http://localhost:8080/api/* é o endereço default para todas as requests no Vue.js.

É isso, agora você tem acesso a todas as rotas http://localhost:8080/api/* para seu frontend.

