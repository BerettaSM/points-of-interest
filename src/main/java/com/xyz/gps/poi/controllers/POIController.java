package com.xyz.gps.poi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xyz.gps.poi.domain.dto.POIDto;
import com.xyz.gps.poi.services.POIService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/poi", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class POIController {

    private final POIService poiService;

    @GetMapping
    public ResponseEntity<Page<POIDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(poiService.findAll(pageable));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<POIDto> findBySlug(@PathVariable String slug) {
        return poiService.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<POIDto> save(@Valid @RequestBody POIDto dto) {
        dto = poiService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{slug}")
                .buildAndExpand(dto.getSlug())
                .toUri();
        dto.setSlug(null);
        return ResponseEntity.created(uri).body(dto);
    }

}
