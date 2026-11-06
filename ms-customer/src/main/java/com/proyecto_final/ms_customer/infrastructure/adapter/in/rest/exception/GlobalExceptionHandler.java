package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.exception;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import com.proyecto_final.ms_customer.domain.exception.CustomerNotFoundException;
import com.proyecto_final.ms_customer.domain.exception.DuplicateCustomerException;
import com.proyecto_final.ms_customer.domain.exception.InvalidDocumentTypeException;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.response.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

/**
 * Traduce las excepciones del dominio y de validación a respuestas HTTP con formato uniforme.
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Clock clock;

    /**
     * Cliente inexistente: 404.
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(CustomerNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), List.of());
    }

    /**
     * Cliente duplicado: 409.
     */
    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicate(DuplicateCustomerException exception) {
        return buildResponse(HttpStatus.CONFLICT, exception.getMessage(), List.of());
    }

    /**
     * Documento no admitido para el tipo de cliente: 422.
     */
    @ExceptionHandler(InvalidDocumentTypeException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidDocumentType(InvalidDocumentTypeException exception) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage(), List.of());
    }

    /**
     * Fallo de las validaciones del DTO (@Valid): 400 con el detalle de cada campo.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(WebExchangeBindException exception) {
        List<String> details = exception.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();
        log.warn("Request validation failed: {}", details);
        return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", details);
    }

    /**
     * Cuerpo ilegible (JSON mal formado o valor de enum inexistente): 400.
     */
    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorResponseDto> handleUnreadableBody(ServerWebInputException exception) {
        log.warn("Unreadable request body: {}", exception.getReason());
        return buildResponse(HttpStatus.BAD_REQUEST, "Malformed request body", List.of());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatus(ResponseStatusException exception) {
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
        log.warn("Request rejected with status {}: {}", status.value(), exception.getReason());
        return buildResponse(status, exception.getReason(), List.of());
    }

    /**
     * Cualquier otro error no contemplado: 500 sin exponer detalles internos.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpected(Exception exception) {
        log.error("Unexpected error", exception);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", List.of());
    }

    /**
     * Construye la respuesta de error uniforme (evita repetir el armado en cada handler).
     */
    private ResponseEntity<ErrorResponseDto> buildResponse(
            HttpStatus status, String message, List<String> details) {
        ErrorResponseDto body = new ErrorResponseDto(
                Instant.now(clock), status.value(), status.getReasonPhrase(), message, details);
        return ResponseEntity.status(status).body(body);
    }
}
