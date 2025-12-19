package me.grad0ff.steps;

import io.qameta.allure.Step;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import me.grad0ff.api.controller.UserController;
import me.grad0ff.api.dto.UserDto;

/**
 * Вспомогательные API шаги для тестов.
 */
public class ApiSteps {

  private final UserController userController = new UserController();

  /**
   * Выполняет шаг по созданию пользователя через API.
   *
   * @param user объект {@link UserDto}, содержащий данные нового пользователя
   * @return объект {@link UserDto}, представляющий созданного пользователя
   */
  @Step("Вспомогательный шаг API - Создать пользователя")
  public UserDto createUser(UserDto user) {
    return userController.postUser(user)
        .as(UserDto.class);
  }

  @Step("Вспомогательный шаг API - Создать несколько пользователей")
  public List<UserDto> createUsers(List<UserDto> users) {
    return userController.postUserCreateWithList(Collections.singletonList(users))
        .jsonPath()
        .getList("", UserDto.class);
  }

  @Step("Вспомогательный шаг API - Получить данные пользователя c username={username}")
  public UserDto getUser(String username) {
    return userController.getUserUsername(username)
        .as(UserDto.class);
  }

  @Step("Вспомогательный шаг API - Обновить данные пользователя c username={username}")
  public UserDto updateUser(String username, UserDto user) {
    return userController.putUserUsername(username, user)
        .as(UserDto.class);
  }

  @Step("Вспомогательный шаг API - Удалить пользователя c username={username}")
  public void deleteUser(String username) {
    userController.deleteUserUsername(username);
  }

  @Step("Вспомогательный шаг API - Авторизоваться под пользователем с username={username}")
  public String login(String username, String password) {
    Map<String, Object> queryParams = Map.of(username, password);
    return userController.getUserLogin(queryParams)
        .getBody()
        .asString();
  }
  @Step("Вспомогательный шаг API - Выйти из аккаунта текущего пользователя")
  public String logout() {
    return userController.getUserLogout()
        .getBody()
        .asString();
  }

}
