package me.grad0ff.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@Accessors(fluent = true)
public class UserDto {

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("password")
  private String password;

  @JsonProperty("userStatus")
  private int userStatus;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("id")
  private long id;

  @JsonProperty("email")
  private String email;

  @JsonProperty("username")
  private String username;

}
