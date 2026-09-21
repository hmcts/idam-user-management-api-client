package uk.gov.hmcts.cft.idam.api.v1.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
    classes = FeignClientContextTest.TestApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    properties = "idam.api.url=http://localhost"
)
class FeignClientContextTest {

    @Autowired
    private IdamV1UserManagementApi userManagementApi;

    @Autowired
    private IdamV1UserRoleManagementApi userRoleManagementApi;

    @Test
    void shouldCreateFeignClients() {
        assertTrue(userManagementApi != null && userRoleManagementApi != null,
            "Both Feign clients should be created");
    }

    @SpringBootConfiguration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    @EnableFeignClients(clients = {
        IdamV1UserManagementApi.class,
        IdamV1UserRoleManagementApi.class
    })
    static class TestApplication {
    }
}
