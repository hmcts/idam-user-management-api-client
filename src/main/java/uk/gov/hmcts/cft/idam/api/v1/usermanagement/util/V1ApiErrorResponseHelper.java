package uk.gov.hmcts.cft.idam.api.v1.usermanagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.V1ApiErrorResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * V1 Api Error Response utility class.
 */
public final class V1ApiErrorResponseHelper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private V1ApiErrorResponseHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Creates internal server exception.
     * @param message error message
     * @return internal server exception
     */
    public static HttpStatusCodeException internalServerError(String message) {
        V1ApiErrorResponse errorResponse = new V1ApiErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setErrorMessage(message);
        return HttpServerErrorException.create(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                null,
                toBytes(errorResponse),
                UTF_8);
    }

    private static byte[] toBytes(Object object) {
        try {
            if (object != null) {
                return OBJECT_MAPPER.writeValueAsBytes(object);
            }
        } catch (JsonProcessingException e) {
            return new byte[0];
        }
        return new byte[0];
    }

}
