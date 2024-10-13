package com.example.cnd.common.utils;

import com.example.cnd.common.base.ErrorResponse;
import com.example.cnd.common.enums.MessageError;
import com.example.cnd.exception.GeneralException;
import com.example.cnd.exception.NotificationException;
import com.example.cnd.exception.ServerErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * File: com.example.cnd.common.utils
 * Description:  <div style="font-weight: bold; color: #0073e6;"> WebClientUtil </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/9/2023</span>
 **/
public class WebClientUtil {
    /**
     * Error Handling Filter
     *
     * @return Filter
     */
    private static ExchangeFilterFunction errorHandlingFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            final HttpStatusCode httpStatusCode = clientResponse.statusCode();
            final HttpStatus httpStatus = HttpStatus.resolve(httpStatusCode.value());
            // Handle error if http status code is error
            if (httpStatusCode.isError()) {
                // Check user-defined error
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorStr -> {
                            // Check user-defined error
                            ErrorResponse errorResponse = JsonUtil.parseToJsonObject(errorStr, ErrorResponse.class);
                            if (!CommonUtil.isNullOrEmpty(errorStr) && errorResponse == null) {
                                // Unexpected error
                                throw new ServerErrorException(MessageError.findErrorById(MessageError.E_400));

                            }
                            GeneralException exception = new NotificationException(errorResponse);
                            exception.setHttpStatus(httpStatus);
                            return Mono.error(exception);
                        });
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    /**
     * Call with method GET
     *
     * @param uri           URI
     * @param responseClazz Class of response data
     * @param <T>           Data type
     * @return Data of class
     */
    public static <T> T get(String uri, Class<T> responseClazz) {
        return WebClient.builder()
                .filter(errorHandlingFilter())
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseClazz)
                .block();
    }

    /**
     * Call with method POST
     *
     * @param <Req>         Data type of request
     * @param <Res>         Data type of response
     * @param uri           URI
     * @param responseClazz Class of response data
     * @return Data of class
     */
    public static <Req, Res> Res post(String uri, Req request, Class<Res> responseClazz) {
        final Class<?> requestClazz = request != null ? request.getClass() : null;
        return WebClient.builder()
                .filter(errorHandlingFilter())
                .build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.justOrEmpty(request), requestClazz)
                .retrieve()
                .bodyToMono(responseClazz)
                .block();
    }

    /**
     * Call with method PUT
     *
     * @param <Req>         Data type of request
     * @param <Res>         Data type of response
     * @param uri           URI
     * @param responseClazz Class of response data
     * @return Data of class
     */
    public static <Req, Res> Res put(String uri, Req request, Class<Res> responseClazz) {
        final Class<?> requestClazz = request != null ? request.getClass() : null;
        return WebClient.builder()
                .filter(errorHandlingFilter())
                .build()
                .put()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.justOrEmpty(request), requestClazz)
                .retrieve()
                .bodyToMono(responseClazz)
                .block();
    }

    /**
     * Call with method DELETE
     *
     * @param <Req>         Data type of request
     * @param <Res>         Data type of response
     * @param uri           URI
     * @param responseClazz Class of response data
     * @return Data of class
     */
    public static <Req, Res> Res delete(String uri, Class<Res> responseClazz, Req request) {
        final Class<?> requestClazz = request != null ? request.getClass() : null;
        return WebClient.builder()
                .filter(errorHandlingFilter())
                .build()
                .delete()
                .uri(uri, request)
                .retrieve()
                .bodyToMono(responseClazz)
                .block();
    }
}
