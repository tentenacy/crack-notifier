package com.tenutz.cracknotifier.web.client.dto.ml;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PredictionMLResponse {

    @NotNull
    private Float accuracy;
}
