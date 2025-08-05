package com.xyz.gps.poi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.gps.poi.domain.dto.POIDto;
import com.xyz.gps.poi.services.POIService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/poi", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class POIController {

    private final POIService poiService;

    @GetMapping
    public ResponseEntity<Page<POIDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(poiService.findAll(pageable));
    }
    
}
