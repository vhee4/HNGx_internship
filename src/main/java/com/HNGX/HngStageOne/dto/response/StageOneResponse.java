package com.HNGX.HngStageOne.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageOneResponse {
    private String slackName;
    private String currentDay;
    private LocalTime utcTime;
    private String track;
    private String githubFileUrl;
    private String githubRepoUrl;
    private int statusCode;
}
