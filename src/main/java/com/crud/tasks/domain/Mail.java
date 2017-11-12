package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Mail {
    @NonNull
    private String mailTo;
    private String toCc;
    @NonNull
    private String subject;
    @NonNull
    private String message;
}
