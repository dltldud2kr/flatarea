package com.example.flatarea.member.exception;

public class MemberStopUserException extends RuntimeException {
    public MemberStopUserException(String error){
        super(error);
    }
}
