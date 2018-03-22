package twitter4j.examples.tweets;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Testing the batch lookup status call with a single ID as input.
 */
public class LookupStatus {

  /**
   * Usage: java twitter4j.examples.tweets.LookupStatus [status id]
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: java twitter4j.examples.tweets.LookupStatus [status id]");
      System.exit(-1);
    }
    try {
      Twitter twitter = new TwitterFactory().getInstance();
      ResponseList<Status> responseList = twitter.lookup(Long.parseLong(args[0]));
      for (Status status : responseList) {
        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
      }
      System.exit(0);
    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to show status: " + te.getMessage());
      System.exit(-1);
    }
  }
}
