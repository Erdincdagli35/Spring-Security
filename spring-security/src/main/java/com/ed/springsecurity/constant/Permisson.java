package com.ed.springsecurity.constant;

public enum Permisson {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permisson;

    Permisson(String permisson){
        this.permisson=permisson;
    }

    public String getPermisson() {
        return permisson;
    }
}

