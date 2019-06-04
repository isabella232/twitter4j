package twitter4j;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://developer.twitter.com/en/docs/basics/response-codes
 */
public enum TwitterExceptionErrorCode {
  INVALID_COORDINATE(3, Optional.of(HttpResponseCode.BAD_REQUEST)),
  MISSING_LOCATION(13, Optional.of(HttpResponseCode.NOT_FOUND)),
  MISSING_USER_SEARCH(17, Optional.of(HttpResponseCode.NOT_FOUND)),
  MISSING_QUERY_PARAMETERS(25, Optional.of(HttpResponseCode.BAD_REQUEST)),
  INVALID_AUTHENTICATION_DATA(32, Optional.of(HttpResponseCode.UNAUTHORIZED)),
  MISSING_RESOURCE(34, Optional.of(HttpResponseCode.NOT_FOUND)),
  INVALID_SPAM_REPORT(36, Optional.of(HttpResponseCode.FORBIDDEN)),
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
  INVALID_DIRECT_MESSAGE_REQUEST(150, Optional.of(HttpResponseCode.FORBIDDEN)),
  FAILED_DIRECT_MESSAGE(151, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_FOLLOW_DUPLICATE(160, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_FOLLOW_LIMIT(161, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_STATUS_VIEW(179, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_STATUS_LIMIT(185, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_STATUS_LENGTH(186, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_STATUS_DUPLICATE(187, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_URL_PARAMETER(195, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_SPAM_REPORT_LIMIT(205, Optional.of(HttpResponseCode.FORBIDDEN)),
  MISSING_AUTHENTICATION_DATA(215, Optional.of(HttpResponseCode.BAD_REQUEST)),
  FORBIDDEN_ACCESS_DENIED(220, Optional.of(HttpResponseCode.FORBIDDEN)),
  FORBIDDEN_AUTOMATED(226, Optional.empty()),
  FORBIDDEN_VERIFY_LOGIN(231, Optional.empty()),
  INVALID_ENDPOINT_RETIRED(251, Optional.empty()),
  FORBIDDEN_WRITE_ACCESS_DENIED(261, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_MUTE_REQUEST(271, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_UNMUTE_REQUEST(272, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_MULTIPLE_ANIMATED_GIF(323, Optional.of(HttpResponseCode.BAD_REQUEST)),
  INVALID_MEDIA_ID(324, Optional.of(HttpResponseCode.BAD_REQUEST)),
  MISSING_MEDIA_ID(325, Optional.of(HttpResponseCode.BAD_REQUEST)),
  FORBIDDEN_TEMPORARY_LOCK(326, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_RETWEET_DUPLICATE(327, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_DIRECT_MESSAGE_LENGTH(354, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_REPLY_TARGET(385, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_ATTACHMENT_TYPE_QUANTITY(386, Optional.of(HttpResponseCode.FORBIDDEN)),
  INVALID_URL(407, Optional.of(HttpResponseCode.BAD_REQUEST));

  private static final Map<Integer, TwitterExceptionErrorCode> TWITTER_EXCEPTION_ERROR_CODES_BY_INTEGER_ERROR_CODE = Arrays.stream(values())
      .collect(Collectors.toMap(TwitterExceptionErrorCode::getCode, Function.identity()));

  private final int code;
  private final Optional<Integer> associatedStatusCode;

  TwitterExceptionErrorCode(int code, Optional<Integer> associatedStatusCode) {
    this.code = code;
    this.associatedStatusCode = associatedStatusCode;
  }

  public int getCode() {
    return code;
  }

  public Optional<Integer> getAssociatedStatusCode() {
    return associatedStatusCode;
  }

  public static Optional<TwitterExceptionErrorCode> tryParseFromErrorCode(int errorCode) {
    return Optional.ofNullable(TWITTER_EXCEPTION_ERROR_CODES_BY_INTEGER_ERROR_CODE.get(errorCode));
  }

  public static TwitterExceptionErrorCode parseFromErrorCode(int errorCode) {
    return tryParseFromErrorCode(errorCode)
        .orElseThrow(() -> new IllegalArgumentException(String.format("%s is not a valid Twitter error code", errorCode)));
  }
}
