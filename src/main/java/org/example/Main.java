package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 동시성 고려안됨
 * 목표1 CRUD 구현
 * 목표2 유지보수성 용이를 위한 폴더 구조화
 */
public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            new App(br).run();
        }
    }
}