package me.grad0ff.tests;

import static me.grad0ff.allure.annotations.CodeAuthor.A_GRADOV;
import static me.grad0ff.helpers.annotations.BodyType.FULL;
import static me.grad0ff.helpers.annotations.BodyType.SHORT;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import me.grad0ff.allure.annotations.Author;
import me.grad0ff.api.controller.UserController;
import me.grad0ff.api.dto.UserDto;
import me.grad0ff.api.enums.StatusCode;
import me.grad0ff.helpers.annotations.UserBody;
import me.grad0ff.helpers.providers.UserExtension;
import me.grad0ff.steps.AllureBasicSteps;
import me.grad0ff.steps.ApiSteps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Feature("user")
@Story("/user/username")
@Tags({@Tag("api"), @Tag("user")})
@Author(A_GRADOV)
@ExtendWith(UserExtension.class)
public class UserUserNameTests extends ApiBaseTest {

  private final ApiSteps apiSteps = new ApiSteps();
  private final UserController controller = new UserController();

  @Test
  @AllureId("37")
  @DisplayName("GET. 200 - OK. Получить данные пользователя по имени")
  @Description("Проверяет успешное получение данных пользователя по имени")
  void getUserUsername(@UserBody(FULL) UserDto user) {
    AllureBasicSteps.arrangeStep();
    UserDto expected = apiSteps.createUser(user);

    AllureBasicSteps.actionStep();
    Response response = Allure.step(
        "Получить данные пользователя",
        () -> controller.getUserUsername(user.username())
    );

    AllureBasicSteps.assertionStep();
    Allure.step(
        "Проверить получение данных пользователя:",
        () -> {
          Allure.step(
              "- проверить код ответа",
              () -> Assertions.assertThat(response.getStatusCode())
                  .isEqualTo(StatusCode.OK)
          );
          Allure.step(
              "- проверить поля тела ответа",
              () -> {
                var actual = response.as(UserDto.class);
                softly.assertThat(actual)
                    .isEqualTo(expected);
              }
          );
        }
    );
  }

  @Test
  @AllureId("38")
  @DisplayName("PUT. 200 - OK. Обновить данные пользователя")
  @Description("Проверяет успешное обновление данных пользователя")
  void putUserUsername(@UserBody(SHORT) UserDto user, @UserBody(FULL) UserDto updUser) {
    AllureBasicSteps.arrangeStep();
    apiSteps.createUser(user);

    AllureBasicSteps.actionStep();
    Response response = Allure.step(
        "Обновить данные пользователя",
        () -> controller.putUserUsername(user.username(), updUser)
    );

    AllureBasicSteps.assertionStep();
    Allure.step(
        "Проверить обновление данных пользователя:",
        () -> {
          Allure.step(
              "- проверить код ответа",
              () -> Assertions.assertThat(response.getStatusCode())
                  .isEqualTo(StatusCode.OK)
          );
          Allure.step(
              "- проверить поля тела ответа",
              () -> {
                var actual = response.as(UserDto.class);
                softly.assertThat(actual)
                    .isEqualTo(updUser);
              }
          );
        }
    );
  }

  @Test
  @AllureId("39")
  @DisplayName("DELETE. 200 - OK. Удалить пользователя")
  @Description("Проверяет успешное удаление пользователя")
  void deleteUserUsername(@UserBody(SHORT) UserDto user) {
    AllureBasicSteps.arrangeStep();
    apiSteps.createUser(user);

    AllureBasicSteps.actionStep();
    Response response = Allure.step(
        "Удалить пользователя",
        () -> controller.deleteUserUsername(user.username())
    );

    AllureBasicSteps.assertionStep();
    Allure.step(
        "Проверить удаление пользователя:",
        () -> {
          Allure.step(
              "- проверить код ответа",
              () -> Assertions.assertThat(response.getStatusCode())
                  .isEqualTo(StatusCode.OK)
          );
          Allure.step(
              "- проверить отсутствие тела ответа",
              () -> softly.assertThat(response.getBody().asString())
                  .isEmpty()
          );
        }
    );
  }

}
