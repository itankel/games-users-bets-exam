package com.ifat.bdd.final_exam.model.convertor;

import lombok.Data;

@Data
public class SuspiciousActivityAsJava {
    private int userId;
    private int count;

    SuspiciousActivityAsJava(int userId, int count) {
        this.userId = userId;
        this.count = count;
    }

    public static SuspiciousActivityAsJavaBuilder builder() {
        return new SuspiciousActivityAsJavaBuilder();
    }

    public static class SuspiciousActivityAsJavaBuilder {
        private int userId;
        private int count;

        SuspiciousActivityAsJavaBuilder() {
        }

        public SuspiciousActivityAsJavaBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public SuspiciousActivityAsJavaBuilder count(int count) {
            this.count = count;
            return this;
        }

        public SuspiciousActivityAsJava build() {
            return new SuspiciousActivityAsJava(userId, count);
        }

        public String toString() {
            return "SuspiciousActivityAsJava.SuspiciousActivityAsJavaBuilder(userId=" + this.userId + ", count=" + this.count + ")";
        }
    }
}
