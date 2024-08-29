package uk.gov.hmcts.cft.idam.api.v1.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * User with role ids held in the JSON roles attribute.
 */
public class UserWithRoleIds extends BaseUser {

    @JsonProperty("roles")
    private List<String> roleIds;

}
