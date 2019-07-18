package ar.edu.unlam.tallerweb1.Helpers;

import java.util.Base64;

public class EncoDecoderHelper {

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();
    private static final Base64.Decoder base64Decoder = Base64.getDecoder();

    public static String base64Encode(String input) {
        return base64Encoder.encodeToString(input.getBytes());
    }

    public static String base64EncodeWithoutPadding(String input) {
        return base64Encoder.withoutPadding().encodeToString(input.getBytes());
    }

    public static String base64Decode(String input) {
        return new String(base64Decoder.decode(input));
    }
}
