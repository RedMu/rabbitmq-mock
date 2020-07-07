package com.github.fridujo.rabbitmq.mock;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
public enum Overflow {
    DROP_HEAD("drop-head"), REJECT_PUBLISH("reject-publish"), REJECT_PUBLISH_DLX("reject-publish-dlx");

    private final String stringValue;

    Overflow(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Optional<Overflow> parse(String value) {
        Optional<Overflow> overflow = Arrays.stream(values()).filter(v -> value.equals(v.stringValue)).findFirst();
        if(!overflow.isPresent()) {
            log.error(format("%s is not a valid overflow option so defaulting to drop-head, please use one of drop-head, reject-publish or reject-publish-dlx"));
        }
        return overflow;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
