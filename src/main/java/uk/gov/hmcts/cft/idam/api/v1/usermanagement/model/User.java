package uk.gov.hmcts.cft.idam.api.v1.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User
 */
@Getter
@Setter
public class User extends BaseUser {

  @JsonProperty("roles")
  private List<String> roleNames;

}
