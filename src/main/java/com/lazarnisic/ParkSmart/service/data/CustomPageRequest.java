package com.lazarnisic.ParkSmart.service.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageRequest {

    @Schema(description = "Page number", defaultValue = "0", example = "0")
    private int page = 0;

    @Schema(description = "Page size", defaultValue = "10", example = "10")
    private int size = 10;

    @Schema(description = "Sorting fields", defaultValue = "name", example = "name")
    private String[] sort = new String[]{"name"};

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(sort));
    }
}
