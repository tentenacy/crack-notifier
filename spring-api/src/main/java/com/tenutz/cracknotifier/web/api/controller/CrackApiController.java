package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.service.CrackService;
import com.tenutz.cracknotifier.web.api.service.cloud.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cracks")
@RequiredArgsConstructor
public class CrackApiController {

    private final CrackService crackService;
    private final FileUploadService fileUploadService;

    @DeleteMapping("/{crackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String crackId) {
        String filename = crackService.delete(crackId);
        fileUploadService.delete(filename);
    }

}