package za.co.iqb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.mail.internet.InternetAddress;

@Slf4j
public final class ValidationUtil {

    protected ValidationUtil() {
        super();
    }

    public static boolean validTelephone(final String input) {
        return isNumeric(input);
    }

    public static boolean isValidEmail(final String email) {
        if (StringUtils.isBlank(email)) {
            return true;
        }
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception e) {
            log.error("Failed to validate email [" + email + "]", e);
            result = false;
        }
        return result;
    }

    public static boolean isNumeric(final String input) {
        if (StringUtils.isBlank(input)) {
            return true;
        }
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }
}
