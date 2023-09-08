package com.HNGX.HngStageOne.controller;

import com.HNGX.HngStageOne.dto.request.StageOneRequest;
import com.HNGX.HngStageOne.dto.response.StageOneResponse;
import com.HNGX.HngStageOne.entity.StageOne;
import com.HNGX.HngStageOne.service.StageOneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class StageOneController {
    private final StageOneService stageOneService;
    private final static String GITHUB_FILE_URL = "https://github.com/vhee4/HNGx_internship/blob/master/src/main/java/com/HNGX/HngStageOne/HngStageOneApplication.java";
    private final static String GITHUB_REPO_URL = "https://github.com/vhee4/HNGx_internship.git";

    @PostMapping
    public StageOneResponse createData(@RequestBody StageOneRequest request) {
        return stageOneService.createData(request);
    }
//    @GetMapping
//    public StageOneResponse GetData(@RequestParam(name = "slack_name") String slackName,
//                                    @RequestParam(name = "track")String track) {
//        return stageOneService.GetData(slackName, track);
//    }
    @GetMapping
    public StageOneResponse GetDataResponse(@RequestParam(name = "slack_name") String slackName,
                                            @RequestParam(name = "track")String track) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDateTime = localDateTime.format(formatter);

        String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        String capitalizedDayOfWeek = dayOfWeek.substring(0, 1) + dayOfWeek.substring(1).toLowerCase();
        StageOneResponse response = StageOneResponse.builder()
                .currentDay(capitalizedDayOfWeek)
                .track(track)
                .slackName(slackName)
                .githubFileUrl(GITHUB_FILE_URL)
                .githubRepoUrl(GITHUB_REPO_URL)
                .statusCode(HttpStatus.OK.value())
                .utcTime(formattedDateTime)
                .build();
        return response;
    }

}
