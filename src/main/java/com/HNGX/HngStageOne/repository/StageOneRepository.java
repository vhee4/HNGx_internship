package com.HNGX.HngStageOne.repository;

import com.HNGX.HngStageOne.dto.response.StageOneResponse;
import com.HNGX.HngStageOne.entity.StageOne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StageOneRepository extends JpaRepository<StageOne,Long> {
    Optional<StageOne> findFirstBySlackNameAndTrack(String slackName, String track);
    boolean existsFirstBySlackNameAndTrack(String slackName, String track);
}
