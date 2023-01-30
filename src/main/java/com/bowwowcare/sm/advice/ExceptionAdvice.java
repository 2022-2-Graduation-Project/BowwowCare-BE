package com.bowwowcare.sm.advice;

import com.bowwowcare.sm.advice.exception.AuthenticationEntryPointException;
import com.bowwowcare.sm.advice.exception.InvalidRefreshTokenException;
import com.bowwowcare.sm.advice.exception.LoginFailureException;
import com.bowwowcare.sm.advice.exception.UserEmailAlreadyExistsException;
import com.bowwowcare.sm.advice.result.Result;
import com.bowwowcare.sm.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userEmailAlreadyExistsException() {
        return responseService.getFailureResult(-101, "이미 존재하는 이메일입니다.");
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result loginFailureException() {
        return responseService.getFailureResult(-102, "아이디 혹은 비밀번호가 틀립니다.");
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result authenticationEntryPointException() {
        return responseService.getFailureResult(-102, "인증이 필요합니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result accessDeniedException() {
        return responseService.getFailureResult(-103, "권한이 필요합니다.");
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result invalidRefreshTokenException() {
        return responseService.getFailureResult(-104, "Refresh Token이 유효하지 않습니다.");
    }
}
