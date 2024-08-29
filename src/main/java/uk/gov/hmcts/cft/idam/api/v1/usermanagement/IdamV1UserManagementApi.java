package uk.gov.hmcts.cft.idam.api.v1.usermanagement;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.User;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.UserRegistrationRequest;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.UserWithRoleIds;
import uk.gov.hmcts.cft.idam.api.v1.usermanagement.util.V1ApiErrorResponseHelper;

import java.util.List;

/**
 * Idam V1 User Management Api.
 */
@FeignClient(name = "idamv1usermanagement", url = "${idam.api.url}")
public interface IdamV1UserManagementApi {

    /**
     * Requires create-user scope and principal can manage requested roles.
     * @param request User Registration Request
     */
    @PostMapping("/api/v1/users/registration")
    void registration(@RequestBody UserRegistrationRequest request);

    /**
     * Requires manage-user scope and principal can manage subject's roles.
     * @param userId user id
     */
    @DeleteMapping("/api/v1/users/{userId}")
    void deleteUserByUserId(@PathVariable String userId);

    /**
     * Requires manage-user scope.
     * @param userId user id
     * @return user
     */
    @GetMapping("/api/v1/users/{userId}")
    User getUserByUserId(@PathVariable String userId);

    /**
     * Requires manage-user scope.
     * @param userId user id
     * @param user user
     * @return user
     */
    @PatchMapping("/api/v1/users/{userId}")
    UserWithRoleIds updateUserDetails(@PathVariable String userId, @RequestBody User user);

    /**
     * Requires manage-user scope.
     * @param userId user id
     */
    @DeleteMapping("/api/v1/users/{userId}/sso")
    void removeSSOFromUser(@PathVariable String userId);

    /**
     * Requires search-user scope.
     * @param email email
     * @return user for email
     */
    default User getUserByEmail(String email) {
        List<User> result = searchUsers("email:" + email, 1, 0);
        if (result.size() == 1) {
            return result.get(0);
        } else if (result.size() > 1) {
            throw V1ApiErrorResponseHelper.internalServerError("Multiple users found for email");
        }
        return null;
    }

    /**
     * Requires search-user scope.
     * @param ssoId sso id
     * @return user for sso id
     */
    default User getUserBySsoId(String ssoId) {
        List<User> result = searchUsers("ssoId:" + ssoId, 1, 0);
        if (result.size() == 1) {
            return result.get(0);
        } else if (result.size() > 1) {
            throw V1ApiErrorResponseHelper.internalServerError("Multiple users found for ssoId");
        }
        return null;
    }

    /**
     * Requires search-user scope.
     * @param query query
     * @param size pagination size
     * @param page pagination page
     * @return list of users
     */
    @GetMapping("/api/v1/users")
    List<User> searchUsers(@RequestParam String query, @RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page);
}
