package br.com.textoit.teste.movielist;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieListApplicationTests {

	@LocalServerPort
	int porta;

	@BeforeEach
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = porta;
	}

	@Test
	public void deve_trazer_filmes_de_acordo_dados_obtidos() {

		given().log().all()
				.header("Content-Type", "application/json")
		.when()
				.get("/filme/intervalos")
		.then().log().body()
				.statusCode(HttpStatus.OK.value())
				.contentType(ContentType.JSON)
				.body("min[0].producer", equalTo("Bo Derek"))
				.body("min[0].interval", equalTo(6))
				.body("min[0].previousWin", equalTo(1984))
				.body("min[0].followingWin", equalTo(1990))
				.body("max[0].producer", equalTo("Bo Derek"))
				.body("max[0].interval", equalTo(6))
				.body("max[0].previousWin", equalTo(1984))
				.body("max[0].followingWin", equalTo(1990))
		;

	}

}
