package me.grad0ff.api.controller;

import static io.restassured.RestAssured.given;
import static me.grad0ff.api.endpoint.Endpoints.USER;
import static me.grad0ff.api.endpoint.Endpoints.USER_USERNAME;
import static me.grad0ff.configs.RestAssuredConfig.requestSpec;

import io.restassured.response.Response;

/**
 * Класс для взаимодействия с API пользователя через HTTP-запросы.
 */
public class UserController {

  /**
   * Отправляет POST-запрос для создания нового пользователя.
   *
   * @param body тело запроса, содержащее данные пользователя
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response postUser(Object body) {
    return given()
        .spec(requestSpec())
        .body(body)
        .post(USER);
  }

  /**
   * Отправляет DELETE-запрос для удаления пользователя по имени пользователя.
   *
   * @param username имя пользователя, подлежащего удалению
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response deleteUserUsername(Object username) {
    return given()
        .spec(requestSpec())
        .delete(USER_USERNAME, username);
  }

}
