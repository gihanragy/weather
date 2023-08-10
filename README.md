# Weather API

This is a Spring Boot application that provides weather data for a specific city using the OpenWeatherMap API. It has
two main endpoints:

- `/api/weather/{city}` - This endpoint is public and returns the current weather data for a given city.
- `/api/report/{city}` - This endpoint is secured with OAuth2 and generates a PDF weather report for a given city.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

### Prerequisites

- Java 17
- Maven
- An API key from OpenWeatherMap

### Installing

1. Clone the repository:
   ````
   git clone https://github.com/gihanragy/weather.git
   ```
2. Navigate to the project directory:
   ````
   cd weather
   ```
3. Add your OpenWeatherMap API key to the `application.properties` file:
   ````
   weather.api.key=your_api_key
   weather.url=https://open-weather13.p.rapidapi.com/city/%s
   weather.host.url = open-weather13.p.rapidapi.com
   ```
4. Build the project:
   ````
   ./mvnw clean install
   ```
5. Run the application:
   ````
   ./mvnw spring-boot:run
   ```

## Testing

This project includes unit and integration tests. To run the tests, use the following command:

```
./mvnw test
```
## Running Swagger
to test the API using weather APP
http://localhost:8080/swagger-ui/index.html#/


## Built With

- [Spring Boot ↗](https://spring.io/projects/spring-boot) - The web framework used
- [Spring Security ↗](https://spring.io/projects/spring-security) - For securing the application
- [Apache PDFBox ↗](https://pdfbox.apache.org/) - For generating PDF reports
- [OpenWeatherMap API ↗](https://openweathermap.org/api) - For fetching weather data

## Author

Gihan Soliman