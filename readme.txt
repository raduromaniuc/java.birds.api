Overview:
- used the OpenAPI specification for the API docs.
    After you start the app, the docs will be at the following url:
    http://localhost:8080/swagger-ui/index.html?configUrl=/docs/swagger-config
- used the JPA Specification API for quickly searching entities based on a variety of combinations.
- went for the classic controller-service-repository layers for a quick and simple API.

Opinionated observations:
- I decided to use pagination for all findAll query types as from what I've seen in practice we should never query for literally all existing entities.
- Used http status 204 so that devs won't get confused for NOT_FOUND (I know that from a principle viewpoint, NOT_FOUND
should be just fine, but I saw that most devs get confused that the whole API might not be available, so I switched in practice to 204
instead of 404).
- Used actual physical deletion instead of soft deletion for a quicker implementation.
- Didn't put auditing and logging since it wasn't expressly requested. I am aware that those might be considered non-functional requirements
which should be present, but unfortunately I don't have much spare time.
In my actual job I would have put those functionalities inside a Bird/SightingOrchestratorService to adhere to the single-responsibility principle.