package uk.gov.hmcts.cft.idam.api.v1.usermanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * V1 Api Error Response.
 */
@Getter
@Setter
public class V1ApiErrorResponse {

    private Integer status;
    private String errorMessage;
    private List<String> errorMessages;

    /**
     * Creates an empty API error response.
     */
    @SuppressWarnings("PMD.UnnecessaryConstructor")
    public V1ApiErrorResponse() {
        // Required for deserialization and documented for Javadoc.
    }

}
