package io.swagger.petstore.core;

import java.security.SecureRandom;
import java.sql.Timestamp;

public class AbstractMethods {
    public String getRandomWord(int length, String alphabet) {
        SecureRandom RND = new SecureRandom();
        StringBuilder sb = new StringBuilder(Math.max(length, 10));
        for (int i = 0; i < length; i++) {
            int len = alphabet.length();
            int random = RND.nextInt(len);
            char c = alphabet.charAt(random);
            sb.append(c);
        }
        return sb.toString();
    }

    public long randomNumbers() {
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

}
