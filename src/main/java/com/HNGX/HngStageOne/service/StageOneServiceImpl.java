package com.HNGX.HngStageOne.service;

import com.HNGX.HngStageOne.dto.request.StageOneRequest;
import com.HNGX.HngStageOne.dto.response.StageOneResponse;
import com.HNGX.HngStageOne.entity.StageOne;
import com.HNGX.HngStageOne.repository.StageOneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class StageOneServiceImpl implements StageOneService{
    private final StageOneRepository stageOneRepository;
    private final ModelMapper modelMapper;
    @Override
    public StageOneResponse createData(StageOneRequest request) {
        boolean isExists = stageOneRepository.existsFirstBySlackNameAndTrack(request.getSlackName(),request.getTrack());
        if(isExists){
            throw new RuntimeException("Slack name already exists");
        }
        StageOne newStageOne = StageOne.builder()
                .currentDay(String.valueOf(LocalDate.now().getDayOfWeek()))
                .track(request.getTrack())
                .slackName(request.getSlackName())
                .githubFileUrl(request.getFileGithubUrl())
                .sourceCodeGithubUrl(request.getSourceCodeGithubUrl())
                .statusCode(HttpStatus.OK.value())
                .utcTime(LocalTime.now(Clock.systemUTC()))
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
