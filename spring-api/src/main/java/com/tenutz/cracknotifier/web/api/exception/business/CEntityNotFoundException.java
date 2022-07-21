package com.tenutz.cracknotifier.web.api.exception.business;

import com.tenutz.cracknotifier.web.api.dto.common.ErrorCode;

public class CEntityNotFoundException extends CBusinessException {
    public CEntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class CUserNotFoundException extends CEntityNotFoundException {
        public CUserNotFoundException() {
            super(ErrorCode.USER_NOT_FOUND);
        }
    }

    public static class CRobotNotFoundException extends CEntityNotFoundException {
        public CRobotNotFoundException() {
            super(ErrorCode.ROBOT_NOT_FOUND);
        }
    }
    public static class CCrackNotFoundException extends CEntityNotFoundException {
        public CCrackNotFoundException() {
            super(ErrorCode.CRACK_NOT_FOUND);
        }
    }
    public static class CRobotDrivingInformationNotFoundException extends CEntityNotFoundException {
        public CRobotDrivingInformationNotFoundException() {
            super(ErrorCode.ROBOT_DRIVING_INFORMATION_NOT_FOUND);
        }
    }
}
