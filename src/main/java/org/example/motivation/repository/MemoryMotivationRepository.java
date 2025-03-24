package org.example.motivation.repository;

import org.example.motivation.entity.Motivation;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryMotivationRepository implements IMotivationRepository {

    private final AtomicInteger seq = new AtomicInteger(0);
    private final List<Motivation> motivations = new LinkedList<>();

    public int save(Motivation motivation) {
        motivation.setSeq(seq.getAndIncrement());
        motivation.setExposure(true);
        motivations.add(motivation);
        return motivations.get(motivations.size() - 1).getSeq();
    }

    public List<Motivation> getMotivations() {
        return motivations;
    }

    public Optional<Motivation> getMotivation(int seq) {
        for (Motivation motivation : motivations) {
            if (motivation.getSeq() == seq) {
                return Optional.of(motivation);
            }
        }
        return Optional.empty();
    }

    public void deleteMotivation(Motivation motivation) {
        motivation.setExposure(false);
    }

    public void modifyMotivation(Motivation motivation, String inputSource, String inputMotivation) {
        motivation.setSource(inputSource);
        motivation.setMotivation(inputMotivation);
    }
}
