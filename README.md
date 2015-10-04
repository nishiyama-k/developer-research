# developer-research

this is sample project of spring boot.

## Development environment

- Windows7
- Java8
- Spring boot 1.2.6
- Spring Tool Suite [here](http://spring.io/tools) 3.7.0
- npm 2.14.4
- maven 3.3.1

## Quick start

- Clone the repo 'git clone https://github.com/nishiyama-k/developer-research.git'
- npm install (if you want to download node-module)
- mvn install

you can find target/developer-research-0.0.1-SNAPSHOT.jar

- java -jar target/developer-research-0.0.1-SNAPSHOT.jar
- access http://localhost:8080/

## Open eclipse

- mvn eclipse:eclipse
- File -> Import and select 'Exisiting Projects into Workspace'
- input root path and click Browse
- select developer-research

## Characters

- Full responsive desing
- Server side validation is easy to maintenance

## TODO

- Many literal strings are injected directly sources. Should be transfered in any storage.
- Storage function is just mock, keep them in cache only.
- Gruntfile.js is duty, you can use only grunt:less.

