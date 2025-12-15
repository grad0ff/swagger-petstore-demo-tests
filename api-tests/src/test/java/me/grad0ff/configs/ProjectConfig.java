package me.grad0ff.configs;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * Класс с конфигурационными параметрами проекта.
 */
@LoadPolicy(LoadType.FIRST)
@Sources({
    "classpath:project.properties",
    "system.properties"
})
public interface ProjectConfig extends Config {

  ProjectConfig CFG = ConfigFactory.create(ProjectConfig.class);

  @Key("base.url")
  String baseUri();

  @Key("base.path")
  String basePath();

  @Key("port")
  int port();

}
