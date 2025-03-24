package org.example.motivation.entity;

public class Motivation implements Comparable<Motivation> {
    private int seq; //해당글의 게시된 글번호 (리스트 추가 삭제하더라도 이건 안바꿈)
    private String source; //동기부여 글의 출처
    private String motivation; //동기부여 글 내용
    private boolean isExposure; //삭제명령은 데이터는 보존하고 숨기기로함

    public Motivation() {
    }

    public Motivation(int seq, String source, String motivation, boolean isExposure) {
        this.seq = seq;
        this.source = source;
        this.motivation = motivation;
        this.isExposure = isExposure;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public boolean isExposure() {
        return isExposure;
    }

    public void setExposure(boolean isExposure) {
        this.isExposure = isExposure;
    }

    @Override
    public int compareTo(Motivation o) {
        if (this.seq > o.seq) {
            return 1;
        } else if (this.seq == o.seq) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Motivation{" +
                "seq=" + seq +
                ", source='" + source + '\'' +
                ", motivation='" + motivation + '\'' +
                ", isExposure=" + isExposure +
                '}';
    }
}
