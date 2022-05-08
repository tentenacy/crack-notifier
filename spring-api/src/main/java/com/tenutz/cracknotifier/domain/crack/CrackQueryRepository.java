package com.tenutz.cracknotifier.domain.crack;

import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.web.api.dto.common.CommonCondition;
import com.tenutz.cracknotifier.web.api.dto.crack.CracksResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrackQueryRepository {
    public Page<CracksResponse> cracks(CommonCondition cond, Pageable pageable, Robot robot);
}
