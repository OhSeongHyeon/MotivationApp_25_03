package org.example;

import org.example.motivation.controller.MotivationController;
import org.example.motivation.entity.Motivation;
import org.example.motivation.service.ArticleService;
import org.example.system.MyUtils;
import org.example.system.controller.SystemController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

public class App {

    private final BufferedReader br;

    private final SystemController systemController;
    private final MotivationController motivationController;

    public App(BufferedReader br) {
        this.br = br;
        systemController = new SystemController();
        motivationController = new MotivationController(br);
    }

    public void run() throws IOException {
        System.out.println("== motivation 실행 ==");
        System.out.println("Help: cmds - add, list, delete, modify");

        while (true) {
            String cmd = br.readLine().trim();

            if ("exit".equals(cmd)) {
                systemController.exit();
                break;
            } else if (cmd.isEmpty()) {
                System.out.println("명령어가 입력되지 않음.");
                continue;
            }

            if (cmd.equals("add")) {
                motivationController.add();
            } else if (cmd.equals("list")) {
                motivationController.list();
            } else if (cmd.startsWith("delete")) {
                motivationController.delete(cmd);
            } else if (cmd.startsWith("modify")) {
                motivationController.modify(cmd);
            } else {
                System.out.println("알 수 없는 명령어.");
            }

        }

    }
}
