package com.example.vt_labs_1.exceptions;

public class NonRealisticData extends Exception{
    public NonRealisticData(String errorMessage){
        super(errorMessage);
    }
}
