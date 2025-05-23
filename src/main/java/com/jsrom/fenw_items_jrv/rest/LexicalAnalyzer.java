package com.jsrom.fenw_items_jrv.rest;

import java.util.function.Function;
import java.util.stream.Stream;

import com.jsrom.fenw_items_jrv.services.exceptions.BadRequestException;


public class LexicalAnalyzer {
    private static final String ASSIGNMENT = ":";
    private static final String BOOKMARK = ";";
    private static final int KEY_INDEX = 1;

    public String extractWithAssure(String q, String key) {
        String[] token = Stream.of(q.split(BOOKMARK))
                .filter(item -> item.startsWith(key))
                .map(item -> item.split(ASSIGNMENT))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Key not found: " + key));
        return token.length > KEY_INDEX ? token[KEY_INDEX] : "";
    }

    public <T> T extractWithAssure(String q, String key, Function<String, T> convert) {
        String value = this.extractWithAssure(q, key);
        try {
            return convert.apply(value);
        } catch (Exception e) {
            throw new BadRequestException("q: incorrect type: " + value);
        }
    }
}
