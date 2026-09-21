# Project guidance

## PMD rules

The PMD configuration in `config/pmd/ruleset.xml` is based on the
[HMCTS Spring Boot template](https://github.com/hmcts/template-spring-boot/blob/master/skeleton/config/pmd/ruleset.xml).

When changing the PMD configuration:

- Compare it with the current HMCTS template.
- Preserve or deliberately review the local deviations documented in the ruleset.
- Document any new deviation or extension both here and next to the relevant rule.
- Run `./gradlew pmdMain` to validate the resulting configuration.

The current local deviations and extensions are:

- `UnitTestContainsTooManyAsserts` remains enabled.
- `LawOfDemeter` also suppresses static-property-access findings and does not set
  the template's `trustRadius` property to `2`.
- The `errorprone.xml` category is not enabled.
- `ShortClassName` is configured locally with a minimum length of `4`.
