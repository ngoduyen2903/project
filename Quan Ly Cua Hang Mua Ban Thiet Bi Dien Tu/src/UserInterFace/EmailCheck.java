/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterFace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Thanh
 */
public class EmailCheck {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern;
    private Matcher matcher;

    public EmailCheck() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    // Class kiểm định dạng email
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

}
