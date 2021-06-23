package com.skillhunt.exceptions;

import com.skillhunt.exceptions.helper.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityException extends RuntimeException{
    private ErrorCode code;
    private String message;

    public EntityException(ErrorCode code, String message) {
        super(code.getKey());
        this.code = code;
        this.message = message;
    }
}
