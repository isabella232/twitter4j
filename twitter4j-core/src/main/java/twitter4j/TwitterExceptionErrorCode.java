package twitter4j;

import java.util.Optional;

/**
 * https://developer.twitter.com/en/docs/basics/response-codes
 */
public enum TwitterExceptionErrorCode {
  INVALID_COORDINATE(3, Optional.of(HttpResponseCode.BAD_REQUEST)),
  MISSING_LOCATION(13, Optional.of(HttpResponseCode.NOT_FOUND)),
  MISSING_USER_SEARCH(17, Optional.of(HttpResponseCode.NOT_FOUND)),
  INVALID_AUTHENTICATION_DATA(32, Optional.of(HttpResponseCode.UNAUTHORIZED)),
  MISSING_PAGE(34, Optional.of(HttpResponseCode.NOT_FOUND)),
  FORBIDDEN_SPAM_REPORT(36, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_ATTACHMENT_URL(44, Optional.of(HttpResponseCode.BAD_REQUEST)),
  MISSING_USER(50, Optional.of(HttpResponseCode.NOT_FOUND)),
  FORBIDDEN_USER_SUSPENDED(63, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_ACCOUNT_SUSPENDED(64, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_API_VERSION(68, Optional.empty()),
  FORBIDDEN_ACTION(87, Optional.empty()),
  FORBIDDEN_RATE_LIMIT(88, Optional.empty()),
  INVALID_TOKEN(89, Optional.empty()),
  FORBIDDEN_SSL_REQUIRED(92, Optional.empty()),
  FORBIDDEN_DIRECT_MESSAGE_ACCESS(93, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_OAUTH_CREDENTIALS(99, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_ACCOUNT_LONG_VALUE(120, Optional.of(HttpResponseCode.FORBIDDEN)),
  FAILED_OVER_CAPACITY(130, Optional.of(HttpResponseCode.SERVICE_UNAVAILABLE)),
  FAILED_UNKNOWN(131, Optional.of(HttpResponseCode.INTERNAL_SERVER_ERROR)),
  INVALID_AUTHENTICATION_TIMESTAMP(135, Optional.of(HttpResponseCode.UNAUTHORIZED)),
  MISSING_STATUS(144, Optional.of(HttpResponseCode.NOT_FOUND)),
  FORBIDDEN_DIRECT_MESSAGE_RELATIONSHIP(150, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_DIRECT_MESSAGE_MISC(151, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_FOLLOW_DUPLICATE(160, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_FOLLOW_LIMIT(161, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_STATUS_VIEW(179, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_STATUS_LIMIT(185, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_STATUS_LENGTH(186, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_STATUS_DUPLICATE(187, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_SPAM_REPORT_LIMIT(205, Optional.of(HttpResponseCode.FORBIDDEN)),
  MISSING_AUTHENTICATION_DATA(215, Optional.of(HttpResponseCode.BAD_REQUEST));

  private final int code;
  private final Optional<Integer> associatedResponseCode;

  TwitterExceptionErrorCode(int code, Optional<Integer> associatedResponseCode) {
    this.code = code;
    this.associatedResponseCode = associatedResponseCode;
  }

  private int getCode() {
    return code;
  }

  private Optional<Integer> getAssociatedResponseCode() {
    return associatedResponseCode;
  }

}
