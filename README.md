[![Azure ADO Badge](https://github.com/hmcts/idam-user-management-api-client/actions/workflows/ado_artifacts_build.yml/badge.svg)](https://github.com/hmcts/idam-user-management-api-client/actions/workflows/ado_artifacts_build.yml)


# IdAM User Management API Client
A Java library to simplify making V1 User Management calls to IdAM API for spring boot applications.

## User Guide

The library includes the idam-api V1 user model, and Feign classes for accessing the most commonly used idam-api V1 endpoints.

After importing the library you will need to enable the feign classes using the spring annotation:

```
@EnableFeignClients(basePackages = {"uk.gov.hmcts.cft.idam"})
```

Since the idam-api user management endpoints require a password grant auth token you can also add
the https://github.com/hmcts/idam-legacy-auth-support library to your spring application for token handling.

To override the standard feign error handling you may want to implement your own feign ErrorDecoder and Retryer classes.
An example of doing that is available in https://github.com/hmcts/idam-user-profile-bridge/tree/master/src/main/java/uk/gov/hmcts/cft/idam/api/v2/common/error

You can then use your ErrorDecoder and Retryer implementations by setting them in your application config.

```
spring:
  cloud:
    openfeign:
      client:
        config:
          default:
            errorDecoder: uk.gov.hmcts.cft.idam.api.v2.common.error.SpringWebClientErrorDecoder
            retryer: uk.gov.hmcts.cft.idam.api.v2.common.error.SpringWebClientRetryer
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.