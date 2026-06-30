package coding.challenge.homefeed.adapter.in.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import coding.challenge.homefeed.application.exceptions.InvalidHomefeedRequestException;
import coding.challenge.homefeed.application.exceptions.UnsupportedHomefeedModuleException;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(InvalidHomefeedRequestException.class)
    ProblemDetail handleInvalidHomefeedRequest(InvalidHomefeedRequestException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problem.setTitle("Invalid homefeed request");
        return problem;
    }

    @ExceptionHandler(UnsupportedHomefeedModuleException.class)
    ProblemDetail handleUnsupportedModule(UnsupportedHomefeedModuleException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        problem.setTitle("Homefeed module mapping failed");
        return problem;
    }
}
