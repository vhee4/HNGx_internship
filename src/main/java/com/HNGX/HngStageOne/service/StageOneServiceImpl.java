package com.HNGX.HngStageOne.service;

import com.HNGX.HngStageOne.dto.request.StageOneRequest;
import com.HNGX.HngStageOne.dto.response.StageOneResponse;
import com.HNGX.HngStageOne.entity.StageOne;
import com.HNGX.HngStageOne.repository.StageOneRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class StageOneServiceImpl implements StageOneService{
    private final StageOneRepository stageOneRepository;
    private final ModelMapper modelMapper;

    private final static String GITHUB_FILE_URL = "https://github.com/vhee4/HNGx_internship/blob/master/src/main/java/com/HNGX/HngStageOne/HngStageOneApplication.java";
    private final static String GITHUB_REPO_URL = "https://github.com/vhee4/HNGx_internship.git";
    @Override
    public StageOneResponse createData(StageOneRequest request) {
        boolean isExists = stageOneRepository.existsFirstBySlackNameAndTrack(request.getSlackName(),request.getTrack());
        if(isExists){
            throw new RuntimeException("Slack name already exists");
        }

        LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDateTime = localDateTime.format(formatter);

        String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        String capitalizedDayOfWeek = dayOfWeek.substring(0, 1) + dayOfWeek.substring(1).toLowerCase();
        StageOne newStageOne = StageOne.builder()
                .currentDay(capitalizedDayOfWeek)
                .track(request.getTrack())
                .slackName(request.getSlackName())
                .githubFileUrl(GITHUB_FILE_URL)
                .githubRepoUrl(GITHUB_REPO_URL)
                .statusCode(HttpStatus.OK.value())
                .utcTime(formattedDateTime)
                .build();
        StageOne savedStageOne = stageOneRepository.save(newStageOne);
        return modelMapper.map(savedStageOne,StageOneResponse.class);
    }

    @Override
    public StageOneResponse GetData(String slackName, String track) {
        StageOne stageOne = stageOneRepository.findFirstBySlackNameAndTrack(slackName,track)
                .orElseThrow(()->new RuntimeException("Slack name or Track not found"));
        return modelMapper.map(stageOne,StageOneResponse.class);
    }



}
