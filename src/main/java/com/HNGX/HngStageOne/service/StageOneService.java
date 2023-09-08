package com.HNGX.HngStageOne.service;

import com.HNGX.HngStageOne.dto.request.StageOneRequest;
import com.HNGX.HngStageOne.dto.response.StageOneResponse;

public interface StageOneService {
    StageOneResponse createData(StageOneRequest request);
    StageOneResponse GetData(String slackName,String track);
}
