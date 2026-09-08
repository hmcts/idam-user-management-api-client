package uk.gov.hmcts.cft.idam.api.v1.usermanagement.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Role Definition.
 */
@Getter
@Setter
public class RoleDefinition {

    private String name;

    /**
     * Creates an empty role definition.
     */
    @SuppressWarnings("PMD.UnnecessaryConstructor")
    public RoleDefinition() {
        // Required for deserialization and documented for Javadoc.
    }

}
