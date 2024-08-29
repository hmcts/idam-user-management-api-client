package uk.gov.hmcts.cft.idam.api.v1.usermanagement.util;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpStatusCodeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class V1ApiErrorResponseHelperTest {

    @Test
    void testInternalServerError() {
        HttpStatusCodeException result = V1ApiErrorResponseHelper.internalServerError("test-message");
        String errorResponse = result.getResponseBodyAsString();
        assertEquals(
                "{\"status\":500,\"errorMessage\":\"test-message\",\"errorMessages\":null}",
                errorResponse);
    }
}