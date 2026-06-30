# Homefeed Service

Kleiner Spring-Boot-Service, der einen mobilen Homefeed als geordnete Liste unterschiedlicher Module ausliefert. Der Service ist als Ports-&-Adapters-Architektur aufgebaut, damit API-Eingang, Use-Case-Logik und Datenquelle sauber getrennt bleiben. Der Service kann beliebig um mehr Module erweitert werden. Außerdem lassen sich neue Adapter an die Ports anschließen und somit von den Fake Daten auf eine echte Datenbank zu gehen. GetHomefeedService muss dafür nicht geändert werden. Ebenso wenig wenn anstatt JSON XML ans Frontend gegeben werde soll. Es lassen sich spezifische DTOs erstellen um z.B. zwischen Android und iOS zu differenzieren. 

## Projekt starten

Voraussetzungen:

- Java 25
- Maven

Starten der Anwendung:
cd ...\homefeed\homefeed
.\mvnw.cmd spring-boot:run

Starten der Tests;
selbes verzeichnis wie oben
.\mvnw.cmd test 


Die Anwendung läuft standardmäßig auf:
http://localhost:8080

## Endpunkte

### `GET /api/homefeed`

Liefert den Homefeed als geordnete Liste von Modulen.

Optionaler Query-Parameter:

userId


Beispiel:

curl "http://localhost:8080/api/homefeed?userId=sarah"


## Bewusste Entscheidungen

### Ports & Adapters

Die Anwendung ist in vier Bereiche getrennt:

- `domain`: fachliche Modelle wie `HomefeedModule`, `GreetingModule`, `SaleBannerModule`, `ProductTeaserModule` und `Product`
- `application`: Use Case und Ports, z. B. `GetHomefeedUseCase`, `LoadHomefeedModulesPort`
- `adapter.in.web`: REST-Controller, API-DTOs, Fehlerbehandlung und Response-Mapping
- `adapter.out.persistence`: aktuelle Fake-Datenquelle als In-Memory-Adapter

Der Use Case hängt dadurch nicht an REST oder einer konkreten Datenbank. Eingangsadapter und Datenadapter können später ausgetauscht werden.

### Modul-Modellierung

Alle Feed-Module implementieren im Domain-Modell das gemeinsame Interface `HomefeedModule`. Konkrete Module haben eigene Records und damit eigene Payloads:

- `GreetingModule`
- `SaleBannerModule`
- `ProductTeaserModule`

Dadurch kann der Use Case eine gemischte, aber typisierte Liste zurückgeben:

List<HomefeedModule>


### Serialisierungsstrategie

Die API verwendet eigene DTOs im Web-Adapter. Jedes DTO besitzt ein explizites `type`-Feld:

- `greeting`
- `sale_banner`
- `product_teaser`

Die Mobile App kann anhand dieses Feldes entscheiden, welches UI-Modul gerendert werden soll. Jackson serialisiert die konkreten DTO automatisch zu JSON.

### Mapping

Das interne Modell (domain) ist unabhängig von der API, weil sie nicht direkt das JSON liefern, sondern indem die Mapper im Web-adapter von Domain zu DTO übersetzen. 


## Tests

- ControllerTest -> Integrationtest für webschicht -> korrektes JSON
- ResponseMapperTest -> testet die Übersetzung Domain -> DTO und die Erweiterbarkeit neuer Modultypen und testet den Application-Service und seine Nutzung der Output-Ports

## Nächste Schritte


- echte Persistenz, z. B. PostgreSQL/JPA
- Personalisierung über einen Recommendation-Service (Zielgruppe/Geschlecht/Alter)
- API-Versionierung für mobile Clients
- OpenAPI/Swagger-Dokumentation
- Spring Boot Actuator für Health Checks und Metriken
- strukturierte Logs, Tracing und Monitoring
- Pagination oder Cursoring, falls der Feed größer wird
- robustere Fehlerbehandlung und fachliche Error-Codes

## Cloud-/Kubernetes-Betrieb

Für einen Betrieb in Kubernetes würde ich den Service als Container-Image bauen und über ein Deployment ausrollen.

Folgende files könnten dazu ergänzt werden: 
- Dockerfile
- .dockerignore
- deployment.yaml
- service.yaml
- ingress.yaml
Dependency: Spring Boot Actuator für Health Checks

Ein CI/CD Flow könnte wie folgt aussehen:

Code push
  -> Tests laufen
  -> JAR wird gebaut
  -> Docker Image wird gebaut
  -> Image wird in Registry gepusht
  -> Kubernetes Manifeste werden angewendet
  -> Pods starten neue Version


Sinnvolle Ergänzungen wären:

- mehrere Replicas für Verfügbarkeit (horizontale Skalierung)
- Readiness- und Liveness-Probes über Spring Boot Actuator
- Ressourcenlimits für CPU und Memory
- Konfiguration über ConfigMaps und Secrets
- Argo CD für die Deployments?
- Umstrukturierung via Helm charts für verschiedene Environments
