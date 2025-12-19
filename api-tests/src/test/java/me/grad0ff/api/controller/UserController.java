package me.grad0ff.api.controller;

import static io.restassured.RestAssured.given;
import static me.grad0ff.api.endpoint.Endpoints.USER;
import static me.grad0ff.api.endpoint.Endpoints.USER_CREATE_WITH_LIST;
import static me.grad0ff.api.endpoint.Endpoints.USER_LOGIN;
import static me.grad0ff.api.endpoint.Endpoints.USER_LOGOUT;
import static me.grad0ff.api.endpoint.Endpoints.USER_USERNAME;
import static me.grad0ff.configs.RestAssuredConfig.requestSpec;

import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс для взаимодействия с API пользователя через HTTP-запросы.
 */
@Slf4j
public class UserController {

  /**
   * Отправляет POST-запрос для создания нового пользователя.
   *
   * @param body тело запроса, содержащее данные создаваемого пользователя
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response postUser(Object body) {
    log.debug("Создать пользователя");
    return given()
        .spec(requestSpec())
        .body(body)
        .post(USER);
  }

  /**
   * Отправляет POST-запрос для создания нескольких пользователей.
   *
   * @param body тело запроса, содержащее список с данными создаваемых пользователей
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response postUserCreateWithList(List<Object> body) {
    log.debug("Создать несколько пользователей");
    return given()
        .spec(requestSpec())
        .body(body)
        .post(USER_CREATE_WITH_LIST);
  }

  /**
   * Отправляет GET-запрос для получения пользователя по его имени.
   *
   * @param username имя искомого пользователя
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response getUserUsername(Object username) {
    log.debug("Получить пользователя с username={}", username);
    return given()
        .spec(requestSpec())
        .get(USER_USERNAME, username);
  }

  /**
   * Отправляет PUT-запрос для обновления данных пользователя.
   *
   * @param username имя пользователя, данные которого нужно обновить
   * @param body     тело запроса, содержащее обновленные данные пользователя
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response putUserUsername(Object username, Object body) {
    log.debug("Обновить данные пользователя c username={}", username);
    return given()
        .spec(requestSpec())
        .body(body)
        .put(USER_USERNAME, username);
  }

  /**
   * Отправляет DELETE-запрос для удаления пользователя по его имени.
   *
   * @param username имя пользователя, подлежащего удалению
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response deleteUserUsername(Object username) {
    log.debug("Удалить пользователя c username={}", username);

    return given()
        .spec(requestSpec())
        .delete(USER_USERNAME, username);
  }

  /**
   * Отправляет GET-запрос для входа пользователя в систему.
   *
   * @param queryParams параметры запроса, содержащие учетные данные (например, username и password)
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response getUserLogin(Map<String, Object> queryParams) {
    log.debug("Авторизоваться под пользователем c username={}", queryParams.getOrDefault("username", ""));
    return given()
        .spec(requestSpec())
        .queryParams(queryParams)
        .get(USER_LOGIN);
  }

  /**
   * Отправляет GET-запрос для выхода текущего пользователя из системы.
   *
   * @return объект {@link Response}, представляющий ответ сервера
   */
  public Response getUserLogout() {
    log.debug("Выйти из аккаунта текущего пользователя");
    return given()
        .spec(requestSpec())
        .get(USER_LOGOUT);
  }

}

