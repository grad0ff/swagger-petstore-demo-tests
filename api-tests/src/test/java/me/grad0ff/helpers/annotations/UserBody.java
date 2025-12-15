package me.grad0ff.helpers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import me.grad0ff.helpers.templates.UserBodyTemplate;

/**
 * Аннотация для маркировки параметров тестовых методов, которые должны быть проинициализированы с
 * использованием шаблонов тела пользователя ({@link UserBodyTemplate}). Позволяет указать тип тела (например,
 * {@link BodyType#SHORT} или {@link BodyType#FULL}).
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserBody {

  /**
   * Возвращает тип тела пользователя, который должен быть сгенерирован.
   *
   * @return константа из {@link BodyType}
   */
  BodyType value();

}
