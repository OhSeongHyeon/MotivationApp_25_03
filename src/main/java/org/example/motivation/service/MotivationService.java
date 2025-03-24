package org.example.motivation.service;

import org.example.motivation.entity.Motivation;
import org.example.motivation.repository.IMotivationRepository;
import org.example.motivation.repository.MemoryMotivationRepository;

import java.util.List;
import java.util.Optional;

public class MotivationService {

    private final IMotivationRepository motivationRepository;

    public MotivationService() {
        this.motivationRepository = new MemoryMotivationRepository();
    }

    public int save(Motivation motivation) {
        return motivationRepository.save(motivation);
    }

    public List<Motivation> getAllMotivations() {
        return motivationRepository.getMotivations();
    }

    public Optional<Motivation> getMotivation(int seq) {
        int target = seq - 1;
        return motivationRepository.getMotivation(target);
    }

    public boolean deleteMotivation(int seq) {
        Optional<Motivation> motivation = getMotivation(seq);
        if (motivation.isEmpty() || !motivation.get().isExposure()) {
            return false;
        }
        motivationRepository.deleteMotivation(motivation.get());
        return true;
    }

    public boolean modifyMotivation(int seq, String inputSource, String inputMotivation) {
        Optional<Motivation> motivation = getMotivation(seq);
        if (motivation.isEmpty() || !motivation.get().isExposure()) {
            return false;
        }
        if (inputSource == null || inputSource.isEmpty()) {
            inputSource = motivation.get().getSource();
        }
        if (inputMotivation == null || inputMotivation.isEmpty()) {
            inputMotivation = motivation.get().getMotivation();
        }
        motivationRepository.modifyMotivation(motivation.get(), inputSource, inputMotivation);
        return true;
    }

}
