package me.grad0ff.configs;

import static me.grad0ff.configs.ProjectConfig.CFG;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

/**
 * Класс для предоставления предварительно сконфигурированной спецификации запроса Rest-Assured.
 */
public class RestAssuredConfig {

  /**
   * Возвращает предварительно сконфигурированную спецификацию запроса Rest-Assured.
   *
   * @return объект {@link RequestSpecification} с базовыми настройками
   */
  public static RequestSpecification requestSpec() {
    return Holder.requestSpec;
  }

  private static class Holder {

    private static final Logger logger;
    private static final AllureRestAssured allureRestAssured;
    private static final PrintStream logStream;
    private static final RequestSpecification requestSpec = getRequestSpecBuilder().build();

    static {
      logger = LogManager.getLogger(RestAssuredConfig.class);
      allureRestAssured = new AllureRestAssured()
          .setRequestTemplate("custom-http-request.ftl")
          .setResponseTemplate("custom-http-response.ftl");
      logStream = IoBuilder.forLogger(logger)
          .buildPrintStream();
    }

    private static RequestSpecBuilder getRequestSpecBuilder() {
      return new RequestSpecBuilder()
          .setContentType(ContentType.JSON)
          .setAccept(ContentType.JSON)
          .setBaseUri(CFG.baseUri())
          .setBasePath(CFG.basePath())
          .setPort(CFG.port())
          .addFilter(allureRestAssured)
          .addFilter(new RequestLoggingFilter(LogDetail.ALL, logStream))
          .addFilter(new ResponseLoggingFilter(LogDetail.ALL, true, logStream));
    }

  }

}
