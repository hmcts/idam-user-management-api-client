package uk.gov.hmcts.cft.idam.api.v1.usermanagement;

import uk.gov.hmcts.cft.idam.api.v1.usermanagement.model.RoleDefinition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Idam V1 User Role Management Api.
 */
@FeignClient(name = "idamv1userrolemanagement", url = "${idam.api.url}")
public interface IdamV1UserRoleManagementApi {

    /**
     * Requires manage-user scope.
     * @param userId user id
     * @param roleName role name
     */
    @DeleteMapping("/api/v1/users/{userId}/roles/{roleName}")
    void denyRoleToUser(@PathVariable String userId, @PathVariable String roleName);

    /**
     * Requires manage-user scope.
     * @param userId user id
     * @param roleNames list of role names
     */
    default void grantRolesToUserByRoleName(String userId, List<String> roleNames) {
        grantRolesToUser(userId, roleNames.stream().map(name -> {
                    RoleDefinition rd = new RoleDefinition();
                    rd.setName(name);
                    return rd;
                }).collect(Collectors.toList()));
    }

    /**
     * Requires manage-user scope.
     * @param userId user id
     * @param request list of role definitions
     */
    @PostMapping("/api/v1/users/{userId}/roles")
    void grantRolesToUser(@PathVariable String userId, @RequestBody List<RoleDefinition> request);

    /**
     * Requires manage-user scope.
     * @param userId user id
     * @param request list of role definitions
     */
    @PutMapping("/api/v1/users/{userId}/roles")
    void replacesRolesGrants(@PathVariable String userId, @RequestBody List<RoleDefinition> request);

}
