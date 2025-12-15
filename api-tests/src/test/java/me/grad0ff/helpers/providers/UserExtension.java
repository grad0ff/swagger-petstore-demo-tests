package me.grad0ff.helpers.providers;

import java.util.ArrayList;
import java.util.List;
import me.grad0ff.api.controller.UserController;
import me.grad0ff.api.dto.UserDto;
import me.grad0ff.helpers.annotations.BodyType;
import me.grad0ff.helpers.annotations.UserBody;
import me.grad0ff.helpers.templates.UserBodyTemplate;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver;

/**
 * JUnit 5 Extension для автоматического создания и удаления пользователей в рамках тестов. Работает с
 * параметрами тестовых методов, аннотированными {@link UserBody}, инициализируя их сгенерированными данными
 * ({@link UserBodyTemplate}), и удаляет созданных пользователей после выполнения всех тестов в классе.
 */
public class UserExtension extends TypeBasedParameterResolver<UserDto> implements AfterAllCallback {

  private static final List<String> userNames = new ArrayList<>();
  private final UserController controller = new UserController();

  /**
   * Разрешает параметр тестового метода типа {@link UserDto}, аннотированного {@link UserBody}. Создает
   * объект {@link UserDto} на основе указанного типа тела.
   *
   * @param parameterContext контекст параметра
   * @param extensionContext контекст расширения
   * @return объект {@link UserDto} с заполненными данными
   * @throws ParameterResolutionException если аннотация {@link UserBody} не найдена
   */
  @Override
  public UserDto resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    BodyType bodyType = parameterContext.findAnnotation(UserBody.class)
        .orElseThrow(() -> new ParameterResolutionException("Annotation UserBody not found"))
        .value();
    UserDto body = switch (bodyType) {
      case SHORT -> UserBodyTemplate.shortBody();
      case FULL -> UserBodyTemplate.fullBody();
    };
    userNames.add(body.username());
    return body;
  }

  /**
   * Удаляет всех пользователей, имена которых были добавлены в список {@link #userNames}, после выполнения
   * всех тестов в текущем тестовом классе.
   *
   * @param context контекст расширения
   */
  @Override
  public void afterAll(ExtensionContext context) {
    userNames.forEach(controller::deleteUserUsername);
  }

}
