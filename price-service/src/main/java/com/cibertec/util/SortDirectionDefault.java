package com.cibertec.util;

import org.springframework.data.domain.Sort;

public class SortDirectionDefault {
    public static Sort.Direction getSortDirection(String direction) {
        if (direction == null) {
            return Sort.Direction.ASC;
        }
        if (direction.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
