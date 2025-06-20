# Arquivo: Dockerfile (na raiz do projeto backend)
# Descrição: Define a imagem Docker para a API Java.

# --- ESTÁGIO 1: Build ---
# MODIFICADO: Usamos uma imagem com Maven e OpenJDK 21 (LTS), que é uma imagem garantida.
FROM maven:3.9.8-eclipse-temurin-21-jammy AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código fonte.
COPY src ./src

# Executa o build, criando o arquivo .jar. Pulamos os testes no build do Docker.
RUN mvn package -DskipTests


# --- ESTÁGIO 2: Imagem Final ---
# MODIFICADO: Usamos uma imagem base leve, contendo apenas o Java Runtime 21 (LTS).
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho.
WORKDIR /app

# Copia o .jar gerado no estágio anterior para a imagem final.
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que a aplicação Spring Boot usa.
EXPOSE 8080

# Define o comando para iniciar a aplicação quando o contêiner for executado.
# Ativamos o perfil 'prod' que criamos.
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]