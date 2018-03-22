package twitter4j;

/**
 * Tweet MediaType is a string enum value which identifies a media usecase.
 * This identifier is used to enforce usecase specific constraints (e.g. filesize, video duration) and enable advanced features.
 * Example Use Case: https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-init#parameters
 *
 * Possible Values: https://twittercommunity.com/t/media-category-values/64781/7
 *
 * @since HubSpot/Twitter4J 4.0.8
 */
public enum TweetMediaType {
  IMAGE("tweet_image"),
  IMAGE_GIF("tweet_gif"),
  VIDEO("tweet_video");

  private final String twitterKey;

  TweetMediaType(String twitterKey) {
    this.twitterKey = twitterKey;
  }

  public String getTwitterKey() {
    return this.twitterKey;
  }
}
