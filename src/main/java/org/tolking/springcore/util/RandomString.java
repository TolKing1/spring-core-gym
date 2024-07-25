package org.tolking.springcore.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomString {
    private String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public String getAlphaNumericString(int stringLength) {
        StringBuilder sb = new StringBuilder(stringLength);

        for (int i = 0; i < stringLength; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());

            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}