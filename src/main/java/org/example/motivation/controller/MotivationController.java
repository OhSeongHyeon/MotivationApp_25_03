package org.example.motivation.controller;

import org.example.Container;
import org.example.motivation.entity.Motivation;
import org.example.motivation.service.MotivationService;
import org.example.system.MyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class MotivationController {

    private final BufferedReader br;

    private final MotivationService motivationService;

    public MotivationController() {
        this.br = Container.getBufferedReader();
        motivationService = new MotivationService();
        insertDummy(10);
    }

    private void insertDummy(int n) {
        String[] sStr = {"Julius Caesar", "Yoda", "William Shakespeare", "Albert Einstein", "John Lennon"};
        String[] mStr = {
                "Veni, vidi, vici.",
                "Do or do not. There is no try.",
                "To be, or not to be, that is the question.",
                "In the middle of difficulty lies opportunity.",
                "Life is what happens when you're busy making other plans."
        };

        for (int i = 0; i < n; i++) {
            Motivation param = new Motivation();
            param.setSource(sStr[i % sStr.length]);
            param.setMotivation(mStr[i % mStr.length]);
            motivationService.save(param);
        }
    }

    public void add() throws IOException {
        System.out.print("motivation: ");
        String inputMotivation = br.readLine();

        System.out.print("source: ");
        String inputSource = br.readLine();

        Motivation param = new Motivation();
        param.setSource(inputSource);
        param.setMotivation(inputMotivation);

        int resSeq = motivationService.save(param);
        System.out.printf("%d번 motivation이 등록됨\n", resSeq + 1);
    }

    public void list() {
        String hLine = "=".repeat(20);
        System.out.println(hLine);
        System.out.println("번호 / source / motivation");
        StringBuilder sb = new StringBuilder();

        motivationService.getAllMotivations()
                .stream()
                .filter(Motivation::isExposure)
                .sorted(Collections.reverseOrder())
                .map(line -> {
                    return String.format("%4d / %-20s / %s\n"
                            , line.getSeq() + 1
                            , line.getSource()
                            , line.getMotivation()
                    );
                })
                .forEach(sb::append);

        sb.setLength(sb.length() - 1);

        System.out.println(sb);
        System.out.println(hLine);
    }

    public void delete(String cmd) throws IOException {
        int targetSeq = getTargetSeq(cmd);
        if (targetSeq == -1) {
            System.out.print("삭제할 대상의 번호: ");
            targetSeq = Integer.parseInt(br.readLine());
        }
        boolean isDeleted = motivationService.deleteMotivation(targetSeq);
        System.out.printf(isDeleted ? "%d번 삭제 성공\n" : "%d번은 존재하지 않음\n", targetSeq);
    }

    public void modify(String cmd) throws IOException {
        int targetSeq = getTargetSeq(cmd);
        if (targetSeq == -1) {
            System.out.print("수정할 대상의 번호: ");
            targetSeq = Integer.parseInt(br.readLine());
        }

        Optional<Motivation> motivation = motivationService.getMotivation(targetSeq);
        if (motivation.isEmpty() || !motivation.get().isExposure()) {
            System.out.printf("%d번은 존재하지 않음\n", targetSeq);
            return;
        }

        System.out.print("source: ");
        String inputSource = br.readLine();

        System.out.print("motivation: ");
        String inputMotivation = br.readLine();

        boolean isModified = motivationService.modifyMotivation(targetSeq, inputSource, inputMotivation);
        System.out.printf(isModified ? "%d번 수정 성공\n" : "%d번 수정 실패\n", targetSeq);
    }

    public int getTargetSeq(String cmd) {
        String[] cmdArgs = cmd.split("\\s|\\?");
        int cmdArg1 = -1; // 명령어 파라미터, 두번째 파라미터 입력 안한 경우 -1

        if (cmdArgs.length > 1) {
            if (MyUtils.isNumber(cmdArgs[1])) {
                cmdArg1 = Integer.parseInt(cmdArgs[1]);
            } else { // id=1, id=2 이런식
                String[] cmdArg1Sp = cmdArgs[1].split("=");
                cmdArg1 = Integer.parseInt(cmdArg1Sp[1]);
            }
        }

        return cmdArg1;
    }
}
