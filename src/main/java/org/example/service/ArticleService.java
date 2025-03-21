package org.example.service;

import org.example.model.Motivation;
import org.example.repository.IArticleRepository;
import org.example.repository.MemoryArticleRepository;

import java.util.List;

public class ArticleService {

    IArticleRepository memoryArticleRepository = new MemoryArticleRepository();

    public int save(Motivation motivation) {
        return memoryArticleRepository.save(motivation);
    }

    public List<Motivation> getAllMotivations() {
        return memoryArticleRepository.getMotivations();
    }

    public Motivation getMotivation(int seq) {
        return memoryArticleRepository.getMotivation(seq);
    }

    public boolean deleteMotivation(int seq) {
        int tmpSeq = seq - 1;
        Motivation motivation = getMotivation(tmpSeq);
        if (motivation == null || !motivation.isExposure()) {
            return false;
        }
        memoryArticleRepository.deleteMotivation(motivation);
        return true;
    }
}
