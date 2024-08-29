package uk.gov.hmcts.cft.idam.api.v1.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * User Registration Request
 */
@Getter
@Setter
public class UserRegistrationRequest {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    @JsonProperty("roles")
    private List<String> roleNames;

}
