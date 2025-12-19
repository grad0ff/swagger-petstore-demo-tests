package me.grad0ff.tests.user;

import static me.grad0ff.allure.annotations.CodeAuthor.A_GRADOV;
import static me.grad0ff.helpers.annotations.BodyType.SHORT;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import me.grad0ff.allure.annotations.Author;
import me.grad0ff.api.constants.StatusCode;
import me.grad0ff.api.controller.UserController;
import me.grad0ff.api.dto.UserDto;
import me.grad0ff.helpers.annotations.UserBody;
import me.grad0ff.helpers.providers.UserExtension;
import me.grad0ff.steps.AllureBasicSteps;
import me.grad0ff.tests.ApiBaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Feature("user")
@Story("/user")
@Tags({@Tag("api"), @Tag("user")})
@Author(A_GRADOV)
@ExtendWith(UserExtension.class)
public class UserTests extends ApiBaseTest {

  private final UserController controller = new UserController();

  @Test
  @AllureId("1")
  @DisplayName("POST. 200 - OK. Создать пользователя")
  @Description("Проверяет успешное создание пользователя")
  void postUser(@UserBody(SHORT) UserDto user) {
    AllureBasicSteps.actionStep();
    Response response = Allure.step(
        "Создать пользователя",
        () -> controller.postUser(user)
    );

    AllureBasicSteps.assertionStep();
    Allure.step(
        "Проверить создание пользователя:",
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
                    .usingRecursiveComparison()
                    .comparingOnlyFields("username", "password")
                    .isEqualTo(user);
                softly.assertThat(actual.id())
                    .isInstanceOf(Long.class);
              }
          );
        }
    );
  }

}
