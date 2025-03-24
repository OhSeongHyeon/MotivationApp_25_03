package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Container {
    private static BufferedReader br;

    // 공유 자원을 모아두는 공간을 초기화
    public static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // 공유 자원을 모아두는 공간 자원 해제
    public static void close() {
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedReader getBufferedReader() {
        return br;
    }
}
