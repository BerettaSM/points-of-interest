package com.xyz.gps.poi.controllers.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SearchByProximityCriteria {

    @Positive(message = "radius should be a positive value.")
    private Long radius = 10L;

    @NotNull(message = "Coord 'x' must be passed.")
    private Long x;

    @NotNull(message = "Coord 'y' must be passed.")
    private Long y;

}
