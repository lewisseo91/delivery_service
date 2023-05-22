package com.delivery.user.validator;

import com.delivery.user.exception.PasswordUnmatchedConditionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("유저 회원가입 유효성 검사 테스트")
@SpringBootTest
class UserAuthorizationValidatorTest {

    @Autowired
    private UserAuthorizationValidator userAuthorizationValidator;

    @DisplayName("1가지 조건만 만족한 경우 예외 발생")
    @Test
    public void validatePasswordPattern1_Test() {
        String password = "sadsaasdfsafdsafdsa";
        assertThrows(PasswordUnmatchedConditionException.class,
                () -> userAuthorizationValidator
                        .validatePasswordPattern(password));
    }

    @DisplayName("2가지 조건만 만족한 경우 예외 발생")
    @Test
    public void validatePasswordPattern2_Test() {
        String password = "123543dsaasdfsafdsafdsa";
        assertThrows(PasswordUnmatchedConditionException.class,
                () -> userAuthorizationValidator
                        .validatePasswordPattern(password));
    }

    @DisplayName("3가지 조건만 만족한 경우 정상")
    @Test
    public void validatePasswordPattern3_Test() {
        String password = "123543dsaasdfsafdsafdsa!!";
        userAuthorizationValidator.validatePasswordPattern(password);

        assert true;
    }

    @DisplayName("4가지 조건 만족한 경우 정상")
    @Test
    public void validatePasswordPattern4_Test() {
        String password = "Aa123543dsaasdfsafdsafdsa!@$_Dsfaf";
        userAuthorizationValidator.validatePasswordPattern(password);

        assert true;
    }
}