package ar.edu.unlam.tallerweb1.Helpers;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Random;

public class TokenHelper {

    private static final String source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom secureRnd = new SecureRandom();
    private static final byte[] bytesArray = new byte[256];


    public static String getSecureRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(source.charAt(secureRnd.nextInt(source.length())));
        return sb.toString();
    }

    public static String getAlphaNumericStringUsingMathRandom(int n) {
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to source variable length
            int index
                    = (int)(source.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(source
                    .charAt(index));
        }

        return sb.toString();
    }

    private static String getRandomStringUsingByteArray() {
        new Random().nextBytes(bytesArray);
        return new String(bytesArray, Charset.forName("UTF-8"));
    }

    public static String getAlphaNumericStringUsingCharSet(int n) {


        String randomString
                = getRandomStringUsingByteArray();

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

    public static String getAlphaNumericStringUsingRegex(int n) {

        String randomString
                = getRandomStringUsingByteArray();

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

    public static String getRandomWebToken() {
        secureRnd.nextBytes(bytesArray);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytesArray);
    }
}
