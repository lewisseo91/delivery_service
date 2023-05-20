package com.delivery.user.validator;

import com.delivery.user.domain.User;
import com.delivery.user.dto.UserSignUpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class UserSignUpValidator {
    private static final long MIN_CONDITION_COUNTS = 3;

    private static final Pattern CHAR_LEN_MORE_12_REGEX = Pattern.compile("^.{12,}$");

    private static final Pattern UPPER_CASE_REGEX = Pattern.compile(".*[A-Z].*");

    private static final Pattern LOWER_CASE_REGEX = Pattern.compile(".*[a-z].*");

    private static final Pattern ANY_NUMBER_REGEX = Pattern.compile(".*[0-9].*");

    private static final Pattern ANY_SPECIAL_CHARACTER_REGEX = Pattern.compile(".*[`~!@#$%^&*()_|+\\-=?;:'\",.<>\\{\\}\\[\\]\\\\\\/].*");

    private static final List<Pattern> REGEX_PATTERNS = List.of(
            UPPER_CASE_REGEX,
            LOWER_CASE_REGEX,
            ANY_NUMBER_REGEX,
            ANY_SPECIAL_CHARACTER_REGEX);

    public static void validate(UserSignUpRequest userSignUpRequest) {
        validateExistPassword(userSignUpRequest);
        validatePasswordLength(userSignUpRequest.getPassword());
        validatePasswordPattern(userSignUpRequest.getPassword());
    }

    private static void validateExistPassword(UserSignUpRequest userSignUpRequest) {
        if (Objects.isNull(userSignUpRequest.getPassword())) {
            throw new RuntimeException();
        }
    }

    public static void validatePasswordLength(String password) {
        if (!CHAR_LEN_MORE_12_REGEX.matcher(password).matches()) {
            throw new RuntimeException();
        }
    }

    public static void validatePasswordPattern(String password) {
        long matchedPatternCounts = REGEX_PATTERNS.stream()
                .filter(pattern -> pattern.matcher(password).matches())
                .count();

        System.out.println(matchedPatternCounts);

        if (matchedPatternCounts < MIN_CONDITION_COUNTS) {
            throw new RuntimeException();
        }

    }
}
