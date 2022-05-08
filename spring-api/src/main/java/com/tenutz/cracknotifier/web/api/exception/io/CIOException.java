package com.tenutz.cracknotifier.web.api.exception.io;

import com.tenutz.cracknotifier.web.api.dto.common.ErrorCode;
import lombok.Getter;

@Getter
public class CIOException extends RuntimeException {

    private ErrorCode errorCode;

    public CIOException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class CMLCommunicationException extends CIOException {
        public CMLCommunicationException() {
            super(ErrorCode.ML_COMMUNICATION_ERROR);
        }
    }

    public static class CCloudCommunicationException extends CIOException {

        public CCloudCommunicationException() {
            super(ErrorCode.CLOUD_COMMUNICATION_ERROR);
        }
    }

    public static class CFileConvertFailedException extends CIOException {

        public CFileConvertFailedException() {
            super(ErrorCode.FILE_CONVERT_FAILED);
        }
    }

    public static class CInvalidFileFormatException extends CIOException {

        public CInvalidFileFormatException() {
            super(ErrorCode.INVALID_FILE_FORMAT);
        }
    }

}
