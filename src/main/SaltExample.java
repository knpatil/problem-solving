package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SaltExample {

    public static void main(String[] args) {
        String passwordToHash = "password";
        byte[] salt = createSalt();

        String securePassword = getSecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
    }


    // Method to generate the hash.
    // It takes a password and the Salt as input arguments
    private static String getSecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());

            System.out.println("****** Bytes=" + Arrays.toString(bytes));

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("****** String=" + sb.toString());
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // Method to generate a Salt
    private static byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

}
