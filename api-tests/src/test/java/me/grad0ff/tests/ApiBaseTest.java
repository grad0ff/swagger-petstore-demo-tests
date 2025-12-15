package me.grad0ff.tests;

import me.grad0ff.allure.annotations.Layer;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Базовый класс для API-тестов.
 */
@Layer("API")
public abstract class ApiBaseTest {

  /**
   * Объект для выполнения мягких утверждений. Позволяет собрать все ошибки утверждений и выбросить их вместе
   * в конце теста.
   */
  protected SoftAssertions softly;

  /**
   * Инициализирует объект {@link SoftAssertions} перед каждым тестом.
   */
  @BeforeEach
  void setUp() {
    softly = new SoftAssertions();
  }

  /**
   * Проверяет все накопленные мягкие утверждения и выбрасывает исключение, если одно или несколько из них не
   * прошли.
   */
  @AfterEach
  void tearDown() {
    softly.assertAll();
  }

}
