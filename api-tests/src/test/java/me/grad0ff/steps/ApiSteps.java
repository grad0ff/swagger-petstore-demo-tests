package me.grad0ff.steps;

import io.qameta.allure.Step;
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

}
