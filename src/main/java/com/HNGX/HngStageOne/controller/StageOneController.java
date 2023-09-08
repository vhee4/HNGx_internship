package com.HNGX.HngStageOne.controller;

import com.HNGX.HngStageOne.dto.request.StageOneRequest;
import com.HNGX.HngStageOne.dto.response.StageOneResponse;
import com.HNGX.HngStageOne.service.StageOneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class StageOneController {
    private final StageOneService stageOneService;

    @PostMapping
    public StageOneResponse createData(@RequestBody StageOneRequest request) {
        return stageOneService.createData(request);
    }
    @GetMapping
    public StageOneResponse GetData(@RequestParam(name = "slackName") String slackName,
                                    @RequestParam(name = "track")String track) {
        return stageOneService.GetData(slackName, track);
    }

}
