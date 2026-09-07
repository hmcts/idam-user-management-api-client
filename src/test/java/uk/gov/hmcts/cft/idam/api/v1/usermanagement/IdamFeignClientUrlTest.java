package uk.gov.hmcts.cft.idam.api.v1.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdamFeignClientUrlTest {

    private static final String LEGACY_URL = "https://legacy-idam.example";
    private static final String OVERRIDE_URL = "https://user-management-idam.example";

    @Test
    void shouldUseLegacyUrlByDefault() {
        MockEnvironment environment = new MockEnvironment()
            .withProperty("idam.api.url", LEGACY_URL);

        assertClientUrlsResolveTo(environment, LEGACY_URL);
    }

    @Test
    void shouldUseClientSpecificUrlWhenConfigured() {
        MockEnvironment environment = new MockEnvironment()
            .withProperty("idam.api.url", LEGACY_URL)
            .withProperty("idam.idam-api.url", OVERRIDE_URL);

        assertClientUrlsResolveTo(environment, OVERRIDE_URL);
    }

    private void assertClientUrlsResolveTo(MockEnvironment environment, String expectedUrl) {
        assertEquals(expectedUrl, resolveUrl(environment, IdamV1UserManagementApi.class),
            "User-management client should resolve the expected URL");
        assertEquals(expectedUrl, resolveUrl(environment, IdamV1UserRoleManagementApi.class),
            "User-role-management client should resolve the expected URL");
    }

    private String resolveUrl(MockEnvironment environment, Class<?> clientType) {
        FeignClient feignClient = clientType.getAnnotation(FeignClient.class);
        return environment.resolveRequiredPlaceholders(feignClient.url());
    }
}
