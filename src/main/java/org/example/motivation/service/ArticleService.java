package org.example.motivation.service;

import org.example.motivation.entity.Motivation;
import org.example.motivation.repository.IArticleRepository;
import org.example.motivation.repository.MemoryArticleRepository;

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
        int target = seq - 1;
        return memoryArticleRepository.getMotivation(target);
    }

    public boolean deleteMotivation(int seq) {
        Motivation motivation = getMotivation(seq);
        if (motivation == null || !motivation.isExposure()) {
            return false;
        }
        memoryArticleRepository.deleteMotivation(motivation);
        return true;
    }

    public boolean modifyMotivation(int seq, String inputSource, String inputMotivation) {
        Motivation motivation = getMotivation(seq);
        if (motivation == null || !motivation.isExposure()) {
            return false;
        }
        if (inputSource == null || inputSource.isEmpty()) {
            inputSource = motivation.getSource();
        }
        if (inputMotivation == null || inputMotivation.isEmpty()) {
            inputMotivation = motivation.getMotivation();
        }
        memoryArticleRepository.modifyMotivation(motivation, inputSource, inputMotivation);
        return true;
    }

}
