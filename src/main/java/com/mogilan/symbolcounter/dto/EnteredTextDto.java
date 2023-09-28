package com.mogilan.symbolcounter.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnteredTextDto {
    public static final String TEXT_PATTERN = "[\\wа-яА-я\\s\\p{Punct}]*";

    @Size(min = 1, message = "{validation.min.size}")
    @Pattern(regexp = TEXT_PATTERN, message = "{validation.pattern}")
    String value;
}
