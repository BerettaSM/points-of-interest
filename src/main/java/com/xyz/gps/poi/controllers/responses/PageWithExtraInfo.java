package com.xyz.gps.poi.controllers.responses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageWithExtraInfo<T> {

    public record PageDTO(int size, int number, long totalElements, int totalPages) {
    }

    private final List<T> content;
    private final PageDTO page;
    private final Map<String, Object> extra = new HashMap<>();

    public PageWithExtraInfo(Page<T> page) {
        content = page.getContent();
        this.page = new PageDTO(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
    }

    public PageWithExtraInfo<T> addExtraInfo(String key, Object value) {
        extra.put(key, value);
        return this;
    }

}
