package com.delivery.user.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("유저 회원가입 유효성 검사 테스트")
class UserSignUpValidatorTest {

    @DisplayName("1가지 조건만 만족한 경우 예외 발생")
    @Test
    public void validatePasswordPattern1_Test() {
        String password = "sadsaasdfsafdsafdsa";
        assertThrows(RuntimeException.class,
                () -> UserSignUpValidator
                        .validatePasswordPattern(password));
    }

    @DisplayName("2가지 조건만 만족한 경우 예외 발생")
    @Test
    public void validatePasswordPattern2_Test() {
        String password = "123543dsaasdfsafdsafdsa";
        assertThrows(RuntimeException.class,
                () -> UserSignUpValidator
                        .validatePasswordPattern(password));
    }

    @DisplayName("3가지 조건만 만족한 경우 정상")
    @Test
    public void validatePasswordPattern3_Test() {
        String password = "123543dsaasdfsafdsafdsa!!";
        UserSignUpValidator.validatePasswordPattern(password);

        assert true;
    }

    @DisplayName("4가지 조건 만족한 경우 정상")
    @Test
    public void validatePasswordPattern4_Test() {
        String password = "Aa123543dsaasdfsafdsafdsa!@$_Dsfaf";
        UserSignUpValidator.validatePasswordPattern(password);

        assert true;
    }
}