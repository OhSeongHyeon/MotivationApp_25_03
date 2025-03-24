package org.example.motivation.repository;

import org.example.motivation.entity.Motivation;

import java.util.List;
import java.util.Optional;

public interface IMotivationRepository {

    int save(Motivation motivation);

    List<Motivation> getMotivations();

    Optional<Motivation> getMotivation(int seq);

    void deleteMotivation(Motivation motivation);

    void modifyMotivation(Motivation motivation, String inputSource, String inputMotivation);
}
