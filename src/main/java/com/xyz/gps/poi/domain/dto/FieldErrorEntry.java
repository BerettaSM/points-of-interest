package com.xyz.gps.poi.domain.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class FieldErrorEntry {

    private final String field;
    private final Set<String> messages = new HashSet<>();

    public FieldErrorEntry(Map.Entry<String, List<String>> entry) {
        field = entry.getKey();
        messages.addAll(entry.getValue());
    }

}
