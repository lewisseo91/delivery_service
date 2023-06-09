package com.delivery.user.validator;

import com.delivery.user.dto.UserInfoDto;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserAuthorizationValidator {
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

    private final PasswordEncoder passwordEncoder;

    public void validatePassword(UserSignUpRequest userSignUpRequest) {
        validateExistPassword(userSignUpRequest);
        validatePasswordLength(userSignUpRequest.getPassword());
        validatePasswordPattern(userSignUpRequest.getPassword());
    }

    private void validateExistPassword(UserSignUpRequest userSignUpRequest) {
        if (Objects.isNull(userSignUpRequest.getPassword())) {
            throw new PasswordNotExistException("요청한 비밀번호가 존재하지 않습니다.");
        }
    }

    public void validatePasswordLength(String password) {
        if (!CHAR_LEN_MORE_12_REGEX.matcher(password).matches()) {
            throw new PasswordMinLengthException("비밀번호의 길이를 확인 해 주세요.");
        }
    }

    public void validatePasswordPattern(String password) {
        long matchedPatternCounts = REGEX_PATTERNS.stream()
                .filter(pattern -> pattern.matcher(password).matches())
                .count();

        if (matchedPatternCounts < MIN_CONDITION_COUNTS) {
            throw new PasswordUnmatchedConditionException("비밀번호에 필요한 조건이 충족되지 않았습니다.");
        }

    }

    public void validateNonExistUser(UserInfoDto user) {
        if (Objects.nonNull(user)) {
            throw new UserNotExistException("이미 가입되어 있는 유저입니다.");
        }
    }

    public void validateLogin(UserInfoDto user, String requestedPassword) {
        if (Objects.isNull(user)) {
            throw new UserNotSignedUpException("가입되어 있지 않은 유저입니다.");
        }

        if (!passwordEncoder.matches(requestedPassword, user.getPassword())) {
            throw new PasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
        }
    }
}
