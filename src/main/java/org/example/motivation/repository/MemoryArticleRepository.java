package org.example.motivation.repository;

import org.example.motivation.entity.Motivation;

import java.util.LinkedList;
import java.util.List;

public class MemoryArticleRepository implements IArticleRepository {

    List<Motivation> motivations = new LinkedList<>();

    public int save(Motivation motivation) {
        motivations.add(motivation);
        return motivations.get(motivations.size() - 1).getSeq();
    }

    public List<Motivation> getMotivations() {
        return motivations;
    }

    public Motivation getMotivation(int seq) {
        for (Motivation motivation : motivations) {
            if (motivation.getSeq() == seq) {
                return motivation;
            }
        }
        return null;
    }

    public void deleteMotivation(Motivation motivation) {
        motivation.setExposure(false);
    }

    public void modifyMotivation(Motivation motivation, String inputSource, String inputMotivation) {
        motivation.setSource(inputSource);
        motivation.setMotivation(inputMotivation);
    }
}
