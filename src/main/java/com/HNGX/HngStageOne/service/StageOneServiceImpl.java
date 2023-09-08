package com.HNGX.HngStageOne.service;

import com.HNGX.HngStageOne.dto.response.StageOneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class StageOneServiceImpl implements StageOneService{

    private final static String GITHUB_FILE_URL = "https://github.com/vhee4/HNGx_internship/blob/master/src/main/java/com/HNGX/HngStageOne/HngStageOneApplication.java";
    private final static String GITHUB_REPO_URL = "https://github.com/vhee4/HNGx_internship.git";

    @Override
    public StageOneResponse GetData(String slackName, String track) {LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDateTime = localDateTime.format(formatter);

        String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        String capitalizedDayOfWeek = dayOfWeek.substring(0, 1) + dayOfWeek.substring(1).toLowerCase();

        return StageOneResponse.builder()
                .currentDay(capitalizedDayOfWeek)
                .track(track)
                .utcTime(formattedDateTime)
                .slackName(slackName)
                .statusCode(HttpStatus.OK.value())
                .githubFileUrl(GITHUB_FILE_URL)
                .githubRepoUrl(GITHUB_REPO_URL)
                .build();
    }
}
