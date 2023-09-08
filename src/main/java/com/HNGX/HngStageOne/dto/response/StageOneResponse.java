package com.HNGX.HngStageOne.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageOneResponse {
    @JsonProperty("slack_name")
    private String slackName;
    @JsonProperty("current_day")
    private String currentDay;
    @JsonProperty("utc_time")
    private String utcTime;
    @JsonProperty("track")
    private String track;
    @JsonProperty("github_file_url")
    private String githubFileUrl;
    @JsonProperty("github_repo_url")
    private String githubRepoUrl;
    @JsonProperty("status_code")
    private int statusCode;
}
