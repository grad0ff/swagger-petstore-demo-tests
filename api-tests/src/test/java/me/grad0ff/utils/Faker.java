package me.grad0ff.utils;

/**
 * Обертка вокруг библиотеки Java Faker для предоставления синглтон-доступа к экземпляру Faker. Наследуется от
 * {@link com.github.javafaker.Faker} для обеспечения совместимости.
 */
public class Faker extends com.github.javafaker.Faker {

  private static final Faker faker = new Faker();

  /**
   * Возвращает статический экземпляр Faker.
   *
   * @return синглтон-экземпляр {@link Faker}
   */
  public static Faker instance() {
    return faker;
  }

}
