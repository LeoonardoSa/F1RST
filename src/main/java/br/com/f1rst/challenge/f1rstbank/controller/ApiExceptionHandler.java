package br.com.f1rst.challenge.f1rstbank.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.f1rst.challenge.f1rstbank.controller.exceptions.ContaNaoEncontradaException;
import br.com.f1rst.challenge.f1rstbank.controller.exceptions.SaldoInsuficienteException;
import br.com.f1rst.challenge.f1rstbank.controller.view.ErrorView;
import br.com.f1rst.challenge.f1rstbank.controller.view.ValidationErrorView;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handle(SaldoInsuficienteException e) {
        ErrorView view = new ErrorView();

        view.addError(e.getMessage());


        return ResponseEntity.badRequest().body(view);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<?> handle(ContaNaoEncontradaException e) {
        ErrorView view = new ErrorView();

        view.addError(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(view);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        Map<String, List<String>> fieldErrors = e.getBindingResult().getFieldErrors()
                .stream().collect(
                        Collectors.groupingBy(FieldError::getField,
                                Collectors.mapping(
                                        DefaultMessageSourceResolvable::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );


        List<String> globalErrors = e.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ValidationErrorView view = new ValidationErrorView(fieldErrors, globalErrors);

        return ResponseEntity.badRequest().body(view);
    }


}
