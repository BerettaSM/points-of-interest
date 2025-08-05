package com.xyz.gps.poi.domain.dto;

import com.xyz.gps.poi.domain.entities.POI;
import com.xyz.gps.poi.domain.model.Coordinate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class POIDto {

    private Long id;

    @NotBlank(message = "Name cannot be null or empty.")
    @Size(min = 3, message = "Name must have at least 3 characters.")
    private String name;

    @NotNull(message = "Coordinate 'x' must not be null.")
    @PositiveOrZero(message = "Coordinate 'x' must be a positive value.")
    private Long x;

    @NotNull(message = "Coordinate 'y' must not be null.")
    @PositiveOrZero(message = "Coordinate 'y' must be a positive value.")
    private Long y;

    public POIDto(POI poi) {
        id = poi.getId();
        name = poi.getName();
        x = poi.getCoords().getX();
        y = poi.getCoords().getY();
    }

    public POI toEntity() {
        return new POI(null, name, new Coordinate(x, y));
    }

    public static POIDto from(POI poi) {
        return new POIDto(poi);
    }
    
}
