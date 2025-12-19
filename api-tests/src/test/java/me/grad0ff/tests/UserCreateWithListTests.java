package me.grad0ff.tests;

import static me.grad0ff.allure.annotations.CodeAuthor.A_GRADOV;
import static me.grad0ff.helpers.annotations.BodyType.SHORT;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import java.util.List;
import me.grad0ff.allure.annotations.Author;
import me.grad0ff.api.controller.UserController;
import me.grad0ff.api.dto.UserDto;
import me.grad0ff.api.constants.StatusCode;
import me.grad0ff.helpers.annotations.UserBody;
import me.grad0ff.helpers.providers.UserExtension;
import me.grad0ff.steps.AllureBasicSteps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Feature("user")
@Story("/user/createWithList")
@Tags({@Tag("api"), @Tag("user")})
@Author(A_GRADOV)
@ExtendWith(UserExtension.class)
public class UserCreateWithListTests extends ApiBaseTest {

  private final UserController controller = new UserController();

  @Test
  @AllureId("72")
  @DisplayName("POST. 200 - OK. Создать нескольких пользователей")
  @Description("Проверяет успешное создание нескольких пользователей")
  void postUserCreateWithList(@UserBody(SHORT) UserDto firstUser, @UserBody(SHORT) UserDto secondUser) {
    AllureBasicSteps.actionStep();
    List<Object> users = List.of(firstUser, secondUser);
    Response response = Allure.step(
        "Создать пользователей",
        () -> controller.postUserCreateWithList(users)
    );

    AllureBasicSteps.assertionStep();
    Allure.step(
        "Проверить создание пользователей:",
        () -> {
          Allure.step(
              "- проверить код ответа",
              () -> Assertions.assertThat(response.getStatusCode())
                  .isEqualTo(StatusCode.OK)
          );
          Allure.step(
              "- проверить поля тела ответа",
              () -> {
                var actual = response.jsonPath().getList("", UserDto.class);
                softly.assertThat(actual)
                    .usingRecursiveComparison()
                    .comparingOnlyFields("username", "password")
                    .isEqualTo(users);
              }
          );
        }
    );
  }

}
