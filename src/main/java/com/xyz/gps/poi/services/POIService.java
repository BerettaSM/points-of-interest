package com.xyz.gps.poi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.gps.poi.domain.dto.POIDto;
import com.xyz.gps.poi.domain.entities.POI;
import com.xyz.gps.poi.repositories.POIRepository;
import com.xyz.gps.poi.util.SlugUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class POIService {

    private final POIRepository poiRepository;

    @Transactional(readOnly = true)
    public Page<POIDto> findAll(Pageable pageable) {
        return poiRepository.findAll(pageable)
                .map(POIDto::from);
    }

    @Transactional(readOnly = true)
    public Optional<POIDto> findBySlug(String slug) {
        return poiRepository.findBySlug(slug)
                .map(POIDto::from);
    }

    @Transactional
    public POIDto save(POIDto dto) {
        POI poi = dto.toEntity();
        poi.setSlug(SlugUtils.toSlug(dto.getName()));
        dto = POIDto.from(poiRepository.save(poi));
        dto.setSlug(poi.getSlug());
        return dto;
    }

}
