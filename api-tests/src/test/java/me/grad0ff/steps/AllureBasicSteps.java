package me.grad0ff.steps;

import io.qameta.allure.Allure;
import lombok.experimental.UtilityClass;

/**
 * Класс для добавления стандартных шагов (Arrange, Action, Assertion) в отчет Allure.
 */
@UtilityClass
public class AllureBasicSteps {

  private static final String ARRANGE = "ARRANGE_STEPS";
  private static final String ACTION = "ACTION_STEPS";
  private static final String ASSERTION = "ASSERTION_STEPS";

  /**
   * Добавляет шаг подготовки теста (Arrange) в отчет Allure.
   */
  public static void arrangeStep() {
    addStep(ARRANGE);
  }

  /**
   * Добавляет шаг действия (Action) в отчет Allure.
   */
  public static void actionStep() {
    addStep(ACTION);
  }

  /**
   * Добавляет шаг проверки (Assertion) в отчет Allure.
   */
  public static void assertionStep() {
    addStep(ASSERTION);
  }

  private void addStep(String step) {
    Allure.step(step);
  }

}
