package com.elsprage.users.common.exception;

public class WrongCredentialsException extends AuthenticationException{
    public WrongCredentialsException(){
        super("Wrong credentials");
    }
}
