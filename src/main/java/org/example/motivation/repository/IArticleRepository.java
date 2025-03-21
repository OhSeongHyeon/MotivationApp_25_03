package org.example.motivation.repository;

import org.example.motivation.entity.Motivation;

import java.util.List;

public interface IArticleRepository {

    int save(Motivation motivation);

    List<Motivation> getMotivations();

    Motivation getMotivation(int seq);

    void deleteMotivation(Motivation motivation);

    void modifyMotivation(Motivation motivation, String inputSource, String inputMotivation);
}
