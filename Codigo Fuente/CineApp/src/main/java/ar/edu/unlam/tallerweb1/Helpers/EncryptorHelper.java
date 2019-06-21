package ar.edu.unlam.tallerweb1.Helpers;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorHelper {

    public static String encryptToSha1(String input) throws NoSuchAlgorithmException {
        return encryptTo(input, "SHA-1");
    }

    private static String encryptTo(String input, String digestInstance) throws NoSuchAlgorithmException {
        MessageDigest msdDigest = MessageDigest.getInstance(digestInstance);
        msdDigest.update(input.getBytes(StandardCharsets.UTF_8), 0, input.length());
        return DatatypeConverter.printHexBinary(msdDigest.digest());
    }

    public static String encryptToMd5(String input) throws NoSuchAlgorithmException {
        return encryptTo(input, "MD5");
    }
}
