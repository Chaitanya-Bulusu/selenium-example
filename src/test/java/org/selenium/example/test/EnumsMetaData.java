package org.selenium.example.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnumsMetaData {
    public enum Browsers {
        CHROME,
        EDGE,
        FIREFOX
    }

    public static Double convertAmountTONumber(String amt) {
        try {
            Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
            Matcher matcher = pattern.matcher(amt);
            StringBuilder stringBuilder = new StringBuilder();
            while (matcher.find()) {
                stringBuilder.append(matcher.group());
            }
            return Double.parseDouble(stringBuilder.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return Double.valueOf(5000);
        }
    }
}
