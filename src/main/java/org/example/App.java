package org.example;

import org.example.model.Motivation;
import org.example.service.ArticleService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

public class App {

    private final BufferedReader br;
    private int seq = 0;
    ArticleService articleService = new ArticleService();

    public App(BufferedReader br) {
        this.br = br;

        insertDummass(10);

    }

    private void insertDummass(int n) {
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
            param.setSeq(seq++);
            param.setSource(sStr[i % sStr.length]);
            param.setMotivation(mStr[i % mStr.length]);
            param.setExposure(true);
            articleService.save(param);
        }
    }

    public void run() throws IOException {
        System.out.println("== motivation 실행 ==");
        System.out.println("Help: cmds - add, list, delete");

        while (true) {
            String cmd = br.readLine().trim();
            if ("add".equals(cmd)) {
                System.out.print("motivation: ");
                String inputMotivation = br.readLine();

                System.out.print("source: ");
                String inputSource = br.readLine();

                Motivation param = new Motivation();
                param.setSeq(seq);
                param.setSource(inputSource);
                param.setMotivation(inputMotivation);
                param.setExposure(true);

                seq += 1;

                articleService.save(param);

                System.out.printf("%d번 motivation이 등록됨\n", seq);
            } else if ("list".equals(cmd)) {
                String hLine = "=".repeat(20);
                System.out.println(hLine);
                System.out.println("번호 / source / motivation");
                StringBuilder sb = new StringBuilder();

                articleService.getAllMotivations()
                        .stream()
                        .filter(Motivation::isExposure)
                        .sorted(Collections.reverseOrder())
                        .map(line -> {
                            return String.format("%4d / %-20s / %s\n", line.getSeq() + 1, line.getSource(), line.getMotivation());
                        })
                        .forEach(sb::append);

                sb.setLength(sb.length() - 1);

                System.out.println(sb);
                System.out.println(hLine);
            } else if ("delete".equals(cmd)) {
                System.out.print("삭제할 대상의 번호: ");
                int targetSeq = Integer.parseInt(br.readLine());
                boolean isDeleted = articleService.deleteMotivation(targetSeq);
                System.out.printf(isDeleted ? "%d번 삭제 성공\n" : "%d번 삭제 실패\n", targetSeq);
            }


            if ("exit".equals(cmd)) {
                System.out.println("프로그램 종료");
                break;
            }
        }


    }
}
