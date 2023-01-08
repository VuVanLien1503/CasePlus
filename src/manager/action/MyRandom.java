package manager.action;

import java.util.Random;

public class MyRandom {
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    String alphaUpperCase = alpha.toUpperCase();
    String digits = "0123456789";
    String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    Random generator = new Random();
    public String randomAlphaNumeric() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    public  int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}
