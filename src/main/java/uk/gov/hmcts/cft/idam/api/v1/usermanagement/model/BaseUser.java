package uk.gov.hmcts.cft.idam.api.v1.usermanagement.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Base user details.
 */
@Getter
@Setter
public class BaseUser {

    private String id;
    private String forename;
    private String surname;
    private String email;
    private Boolean active;
    private Boolean locked;
    private Boolean pending;
    private Boolean stale;
    private String pwdAccountLockedTime;
    private String ssoProvider;
    private String ssoId;
    private String lastModified;
    private String createDate;

    /**
     * Default constructor.
     */
    protected BaseUser() {
        // Avoid instantiating base user class.
    }

}
