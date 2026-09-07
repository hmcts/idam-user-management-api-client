package uk.gov.hmcts.cft.idam.api.v1.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.User;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.UserRegistrationRequest;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.UserWithRoleIds;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class IdamV1UserManagementApiTest {


    // Implementation to test default methods.
    /* default */ IdamV1UserManagementApi underTest = new IdamV1UserManagementApi() {
        @Override
        public void registration(UserRegistrationRequest request) {

        }

        @Override
        public void deleteUserByUserId(String userId) {

        }

        @Override
        public User getUserByUserId(String userId) {
            return null;
        }

        @Override
        public UserWithRoleIds updateUserDetails(String userId, User user) {
            return null;
        }

        @Override
        public void removeSSOFromUser(String userId) {

        }

        @Override
        public List<User> searchUsers(String query, Integer size, Integer page) {
            if (query.contains("nousers")) {
                return Collections.emptyList();
            } else if (query.contains("twousers")) {
                return List.of(new User(), new User());
            } else {
                return List.of(new User());
            }
        }
    };

    @Test
    void testGetUserByEmailForOneUser() {
        User result = underTest.getUserByEmail("oneuser@test.local");
        assertNotNull(result, "Expected one user for the email address");
    }

    @Test
    void testGetUserByEmailForNoUsers() {
        User result = underTest.getUserByEmail("nousers@test.local");
        assertNull(result, "Expected no user for the email address");
    }

    @Test
    void testGetUserByEmailForMultipleUsers() {
        try {
            underTest.getUserByEmail("twousers@test.local");
        } catch (HttpStatusCodeException hsce) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, hsce.getStatusCode(),
                "Multiple email matches should produce an internal server error");
        }

    }

    @Test
    void testGetUserBySsoIdForOneUser() {
        User result = underTest.getUserBySsoId("oneuserssoid");
        assertNotNull(result, "Expected one user for the SSO ID");
    }

    @Test
    void testGetUserBySsoIdForNoUsers() {
        User result = underTest.getUserBySsoId("nousersssoid");
        assertNull(result, "Expected no user for the SSO ID");
    }

    @Test
    void testGetUserBySsoIdForMultipleUsers() {
        try {
            underTest.getUserBySsoId("twousersssoid");
        } catch (HttpStatusCodeException hsce) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, hsce.getStatusCode(),
                "Multiple SSO ID matches should produce an internal server error");
        }

    }
}
