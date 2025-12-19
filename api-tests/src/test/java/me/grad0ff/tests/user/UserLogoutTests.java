package me.grad0ff.tests.user;

import static me.grad0ff.allure.annotations.CodeAuthor.A_GRADOV;
import static me.grad0ff.api.constants.ResourceLockType.USER_SESSION;
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
import me.grad0ff.steps.ApiSteps;
import me.grad0ff.tests.ApiBaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.ResourceLock;

@Feature("user")
@Story("/user/logout")
@Tags({@Tag("api"), @Tag("user")})
@Author(A_GRADOV)
@ResourceLock(USER_SESSION)
@ExtendWith(UserExtension.class)
public class UserLogoutTests extends ApiBaseTest {

  private final ApiSteps apiSteps = new ApiSteps();
  private final UserController controller = new UserController();

  @Test
  @AllureId("106")
  @DisplayName("GET. 200 - OK. Выйти из аккаунта текущего пользователя")
  @Description("Проверяет успешный выход из аккаунта текущего пользователя")
  void getUserLogout(@UserBody(SHORT) UserDto user) {
    AllureBasicSteps.arrangeStep();
    apiSteps.createUser(user);
    apiSteps.login(user.username(), user.password());

    Response response = Allure.step(
        "Выйти из аккаунта",
        controller::getUserLogout
    );

    AllureBasicSteps.assertionStep();
    Allure.step(
        "Проверить выход из аккаунта:",
        () -> {
          Allure.step(
              "- проверить код ответа",
              () -> Assertions.assertThat(response.getStatusCode())
                  .isEqualTo(StatusCode.OK)
          );
          Allure.step(
              "- проверить тело ответа",
              () -> {
                var msgText = "User logged out";
                softly.assertThat(response.getBody().asString())
                    .isEqualTo(msgText);
              }
          );
        }
    );
  }

}
