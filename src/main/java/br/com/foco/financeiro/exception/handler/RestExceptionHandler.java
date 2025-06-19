package br.com.foco.financeiro.exception.handler;


import br.com.foco.financeiro.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Manipulador para a nossa exceção customizada RecursoNaoEncontradoException.
     * @return Um ResponseEntity com status 404 e um corpo de erro padronizado.
     */
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO.Builder()
                .withTimestamp(Instant.now())
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withError(HttpStatus.NOT_FOUND.getReasonPhrase())
                .withMessage(ex.getMessage())
                .withPath(request.getRequestURI())
                .build();
        System.err.println(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Manipulador genérico para qualquer outra exceção não tratada.
     * @return Um ResponseEntity com status 500 e um corpo de erro padronizado.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO.Builder()
                .withTimestamp(Instant.now())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .withMessage("Ocorreu um erro inesperado no servidor.")
                .withPath(request.getRequestURI())
                .build();
        // TODO: Implementar Log4j
        // É uma boa prática logar a exceção original aqui
        // log.error("Erro não tratado:", ex);
        System.err.println(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}