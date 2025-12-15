package me.grad0ff.helpers.templates;

import java.util.Objects;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import me.grad0ff.api.dto.UserDto;
import me.grad0ff.utils.Faker;

/**
 * Класс для генерации тел запросов ({@link UserDto}) с тестовыми данными. Предоставляет методы для создания
 * кратких и полных тел.
 */
@UtilityClass
public class UserBodyTemplate {

  /**
   * Создает краткое тело запроса {@link UserDto} с минимальным набором данных. Если поля не заданы в билдере,
   * используются случайные значения.
   *
   * @return объект {@link UserDto} с заполненными полями username и password
   */
  public static UserDto shortBody() {
    return ShortBody.builder()
        .build()
        .body();
  }

  /**
   * Создает полное тело запроса {@link UserDto} с расширенным набором данных. Если поля не заданы в билдере,
   * используются случайные значения.
   *
   * @return объект {@link UserDto} с заполненными всеми полями
   */
  public static UserDto fullBody() {
    return FullBody.builder()
        .build()
        .body();
  }

  /**
   * Внутренний класс для построения краткого тела {@link UserDto}. Содержит базовые поля и общую логику
   * генерации.
   */
  @SuperBuilder
  public static class ShortBody {

    private String username;
    private String password;

    /**
     * Генерирует объект {@link UserDto} на основе полей текущего билдера. Если поля не были установлены,
     * используются случайные значения.
     *
     * @return объект {@link UserDto}
     */
    public UserDto body() {
      return common().build();
    }

    UserDto.UserDtoBuilder<?, ?> common() {
      username = Objects.nonNull(username) ? username : Faker.instance().name().username();
      password = Objects.nonNull(password) ? password : Faker.instance().name().username();

      return UserDto.builder()
          .username(username)
          .password(password);
    }

  }

  /**
   * Внутренний класс для построения полного тела {@link UserDto}. Расширяет {@link ShortBody}, добавляя
   * дополнительные поля.
   */
  @SuperBuilder
  public static class FullBody extends ShortBody {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer userStatus;

    /**
     * Генерирует объект {@link UserDto} на основе полей текущего билдера. Если поля не были установлены,
     * используются случайные значения.
     *
     * @return объект {@link UserDto}
     */
    public UserDto body() {
      id = Objects.nonNull(id) ? id : Faker.instance().number().randomNumber();
      firstName = Objects.nonNull(firstName) ? firstName : Faker.instance().name().firstName();
      lastName = Objects.nonNull(lastName) ? lastName : Faker.instance().name().lastName();
      email = Objects.nonNull(email) ? email : Faker.instance().internet().emailAddress();
      phone = Objects.nonNull(phone) ? phone : Faker.instance().phoneNumber().phoneNumber();
      userStatus = Objects.nonNull(userStatus) ? userStatus : 1;

      return common()
          .id(id.intValue())
          .firstName(firstName)
          .lastName(lastName)
          .email(email)
          .phone(phone)
          .userStatus(userStatus)
          .build();
    }

  }

}
