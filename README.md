# aperreio-zero-api

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need
  to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Features

Here's a list of features included in this project:

| Name                                                                   | Description                                                                        |
|------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| [Koin](https://start.ktor.io/p/koin)                                   | Provides dependency injection                                                      |
| [Routing](https://start.ktor.io/p/routing)                             | Provides a structured routing DSL                                                  |
| [Content Negotiation](https://start.ktor.io/p/content-negotiation)     | Provides automatic content conversion according to Content-Type and Accept headers |
| [kotlinx.serialization](https://start.ktor.io/p/kotlinx-serialization) | Handles JSON serialization using kotlinx.serialization library                     |
| [Exposed](https://start.ktor.io/p/exposed)                             | Adds Exposed database to your application                                          |
| [Authentication](https://start.ktor.io/p/auth)                         | Provides extension point for handling the Authorization header                     |
| [Authentication JWT](https://start.ktor.io/p/auth-jwt)                 | Handles JSON Web Token (JWT) bearer authentication scheme                          |
| [CORS](https://start.ktor.io/p/cors)                                   | Enables Cross-Origin Resource Sharing (CORS)                                       |
| [Swagger](https://start.ktor.io/p/swagger)                             | Serves Swagger documentation                                                       |

## Building & Running

Docker/Docker compose steps:

| Steps                          | Description                                                          |
|-------------------------------|----------------------------------------------------------------------|
| `run`              | docker compose up --build                                                    |
| `k6 smoke test`                   |    docker run --rm -it -v ./scripts/load.js:/scripts/load.js \ -p 5665:5665 \ -e K6_WEB_DASHBOARD=true \ grafana/k6 run /scripts/smoke.js                              |
| `k6 load test`                   |    docker run --rm -it -v ./scripts/load.js:/scripts/load.js \ -p 5665:5665 \ -e K6_WEB_DASHBOARD=true \ grafana/k6 run /scripts/load.js                              |
| `k6 spike test`                   |    docker run --rm -it -v ./scripts/load.js:/scripts/load.js \ -p 5665:5665 \ -e K6_WEB_DASHBOARD=true \ grafana/k6 run /scripts/spike.js                              |

Services:

| Description                          | Url                                                          |
|-------------------------------|----------------------------------------------------------------------|
| `api`             | http://localhost:8080/                                                  |
| `metrics`                 | http://localhost:8080/metrics |
| `prometheus`                  | http://localhost:9090/                      |
| `grafana` | http://localhost:3001/                                   |
| `grafana prometheus datasource`                         | http://prometheus:9090                                                 |
