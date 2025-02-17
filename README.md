# BPM IBM BAMOE 8.0.x SAMPLE

## Indice

- [Requisiti](#requisiti)
- [Setup](#setup)
- [Esecuzione](#esecuzione)

### Requisiti

- Java 11
- Maven 3.8.x o superiore
- IDE (VSCode preferibile)
- Podman/Docker

### Setup

Il repository contiene tutte le configurazioni necessarie per essere importato in un `workspace` con l'IDE `Visual Studio Code` e sono disponibili i `tasks` e il `launcher` per eseguire il progetto.

E' comunque possibile utilizzare anche altri IDE come `IntelliJ`, `Eclipse`, ma sarà necessario eseguire degli step aggiuntivi per la fase di esecuzione.

### Esecuzione

#### Visual Studio Code

In caso di utilizzo di questo IDE sarà sufficiente:

- Installare il KJAR:

    ```shell
    cd business-application-kjar
    mvn clean install --file business-application-kjar/pom.xml
    ```

- Eseguire il `launcher` dell'applicazione `business-application-service` che in automatico compilerà l'applicazione e avvierà l'ambiente `Docker/Podman` con il profilo `postgres`.

#### Altri IDE

In caso di utilizzo di altri IDE è necessario eseguire questi step:

- Installare il KJAR:

    ```shell
    cd business-application-kjar
    mvn clean install --file pom.xml
    ```

- Compilare con maven l'applicazione:

    ```shell
    cd business-application-service
    mvn clean compile --file pom.xml
    ```

- Avviare l'ambiente `Docker/Podman`:

    ```shell
    cd business-application-service
    podman-compose|docker-compose \
        --file ./collections/compose.yaml up \
        --force-recreate \
        --remove-orphans \
        --detach
    ```

- Avviare l'applicazione avendo aggiungendo questi argomenti alla vm `-Dspring.profiles.active=postgres`
