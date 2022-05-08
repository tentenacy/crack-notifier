package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.dto.common.CommonCondition;
import com.tenutz.cracknotifier.web.api.dto.crack.CracksResponse;
import com.tenutz.cracknotifier.web.api.service.CrackService;
import com.tenutz.cracknotifier.web.api.service.cloud.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cracks")
@RequiredArgsConstructor
public class CrackApiController {

    private final CrackService crackService;
    private final FileUploadService fileUploadService;

    @GetMapping
    public Page<CracksResponse> cracks(@Validated CommonCondition cond, Pageable pageable) {
        return crackService.cracks(cond, pageable);
    }

    @DeleteMapping("/{crackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String crackId) {
        String filename = crackService.delete(crackId);
        fileUploadService.delete(filename);
    }

}