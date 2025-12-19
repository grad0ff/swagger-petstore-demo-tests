package me.grad0ff.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.parallel.ResourceLock;

/**
 * Класс, содержащий константы для {@link ResourceLock}.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceLockType {

  public static final String USER_SESSION = "USER_SESSION";

}
