package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Auxiliary class
 *
 * Runtime: 1 ms, faster than 98.40% of Java online submissions.
 * Memory Usage: 39 MB, less than 83.80% of Java online submissions.
 */
class Codec {

  // **** Codec class member(s) ****
  HashMap<String, String> hm = null;
  char map[] = null;


  /**
   * Constructor.
   */
  public Codec() {
    this.hm     = new HashMap<>();
    this.map    = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  }


  /**
   * Encodes a URL to a shortened URL.
   * Uses a base 62 encoding.
   * O(n)
   */
  public String encode(String longUrl) {

    // **** sanity check(s) ****
    if (longUrl.equals(""))
      return "";

    System.out.println("HASH CODE:" + longUrl.hashCode() );

    // **** initialization ****
    StringBuilder sb    = new StringBuilder();
    int sum             = 0;

    // **** sum characters in string - O(n) ****
    for (char c : longUrl.toCharArray())
      sum += c;

    System.out.println("SUM: " + sum);

    // **** convert the sum to a base 62 number ****
    int count = 0;
    while (sum > 0) {
      sb.append(map[sum % 62]);
      sum /= 62;
      System.out.println("sum: " + sum);
      count++;
    }
    System.out.println("COUNT : " + count);

    // **** string builder to string ****
    // String shortUrl = sb.reverse().toString();
    String shortUrl = sb.toString();

    // **** put URLs into hash map ****
    this.hm.put(shortUrl, longUrl);

    // **** return short URL ****
    return shortUrl;
  }


  /**
   * Decodes a shortened URL to its original URL.
   */
  public String decode(String shortUrl) {

    // **** sanity check(s) ****
    if (shortUrl.equals(""))
      return "";

    // **** return longUrl ****
    return this.hm.get(shortUrl);
  }
}


/**
 * LeetCode 535. Encode and Decode TinyURL
 * https://leetcode.com/problems/encode-and-decode-tinyurl/
 */
public class EncodeDecodeTinyURL {

  /**
   * Test scafolding
   *
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    String longUrl = "http://salesforce.com/v2/cdisc/atrribute=schema,version$2";


    // ???? display URL ????
    System.out.println("longUrl: " + longUrl);

    // **** instantiate the codec object ****
    Codec codec = new Codec();

    // **** convert longUrl -> shortUrl ****
    String shortUrl = codec.encode(longUrl);

    // ???? ????
    System.out.println("shortUrl: " + shortUrl );

    // **** convert shortUrl -> longUrl ****
    longUrl = codec.decode(shortUrl);

    // ???? ????
    System.out.println("longUrl: " + longUrl);

//    // **** call methods in the way le/et/code will do ****
//    System.out.println( "main <<< longUrl ==>"
//        + codec.decode(codec.encode(longUrl)) + "<==");
  }
}