package com.example.cnd.exception;

import com.example.cnd.common.enums.MessageError;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.ServiceUnavailableException;

/**
 * File: com.example.cnd.exception
 * Description:  <div style="font-weight: bold; color: #0073e6;"> CommonExceptionHandler </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * BadRequestException
     *
     * @param ex BadRequestException
     * @return HTTP [BAD_REQUEST」ErrorResponse
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

        BadRequestException exception = new BadRequestException(MessageError.findErrorById(MessageError.E_400));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * UnprocessableEntityException処理
     *
     * @param ex UnprocessableEntityException
     * @return HTTPレスポンスが「UNPROCESSABLE_CONTENT」のErrorResponse
     */
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handleUnprocessableEntityException(UnprocessableEntityException ex) {

        UnprocessableEntityException exception = new UnprocessableEntityException(
                MessageError.findErrorById(MessageError.E_422));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * UnauthorizedException
     *
     * @param ex UnauthorizedException
     * @return HTTP「UNAUTHORIZED」ErrorResponse
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {

        UnauthorizedException exception = new UnauthorizedException(MessageError.findErrorById(MessageError.E_401));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }


    /**
     * ForbiddenException
     *
     * @param ex ForbiddenException
     * @return HTTP「FORBIDDEN」ErrorResponse
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        ForbiddenException exception = new ForbiddenException(MessageError.findErrorById(MessageError.E_403));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * NotFoundException
     *
     * @param ex NotFoundException
     * @return HTTP「NOT_FOUND」ErrorResponse
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        NotFoundException exception = new NotFoundException(MessageError.findErrorById(MessageError.E_404));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * ServerErrorException
     *
     * @param ex ServerErrorException
     * @return HTTP「INTERNAL_SERVER_ERROR」ErrorResponse
     */
    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<Object> handleServerErrorException(ServerErrorException ex) {

        ServerErrorException exception = new ServerErrorException(MessageError.findErrorById(MessageError.E_500));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * CannotCreateTransactionException処理
     *
     * @param ex ServerErrorException
     * @return HTTP「INTERNAL_SERVER_ERROR」ErrorResponse
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServerErrorException(ServiceUnavailableException ex) {
        ServerErrorException exception = new ServerErrorException(MessageError.findErrorById(MessageError.E_500));
        return new ResponseEntity<>(exception.getError(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Exception
     *
     * @param ex Exception
     * @return HTTP「INTERNAL_SERVER_ERROR」ErrorResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> unexpectedException(Exception ex) {
        ServerErrorException exception = new ServerErrorException(MessageError.findErrorById(MessageError.E_500));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * HttpMessageNotReadableException
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders headers
     * @param status  HttpStatus http
     * @param request WebRequest
     * @return HTTP「BAD_REQUEST」ErrorResponse
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        UnprocessableEntityException exception = new UnprocessableEntityException(
                MessageError.findErrorById(MessageError.E_422));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }

    /**
     * TypeMismatchException
     *
     * @param ex      TypeMismatchException
     * @param headers HttpHeaders headers
     * @param status  HttpStatus http
     * @param request WebRequest
     * @return HTTP「BAD_REQUEST」ErrorResponse
     */
    @Override
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        UnprocessableEntityException exception = new UnprocessableEntityException(
                MessageError.findErrorById(MessageError.E_422));
        return new ResponseEntity<>(exception.getError(), exception.getHttpStatus());
    }
}
