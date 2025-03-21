package org.example.repository;

import org.example.model.Motivation;

import java.util.List;

public interface IArticleRepository {

    int save(Motivation motivation);

    List<Motivation> getMotivations();

    Motivation getMotivation(int seq);

    boolean deleteMotivation(Motivation motivation);
}
