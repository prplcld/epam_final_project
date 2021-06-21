package by.silebin.final_project.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HashUtil {
    private static final int iterations = 10;
    private static final int saltLen = 8;
    private static final int desiredKeyLen = 32;

    public static String hash(String password) {
        byte[] salt;
        try {
            salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
            return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("unable to encode password");
    }

    public static boolean check(String pass, String hash){
        String[] saltAndHash = hash.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalStateException("The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = hash(pass, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    private static String hash(String password, byte[] salt) {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen));
            return Base64.encodeBase64String(key.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("unable to encode password");
    }
}
