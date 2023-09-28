package com.mogilan.symbolcounter.controller;

import com.mogilan.symbolcounter.dto.EnteredTextDto;
import com.mogilan.symbolcounter.dto.ResultDto;
import com.mogilan.symbolcounter.service.CharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService service;

    @PostMapping("/character/countFrequency")
    public ResponseEntity<?> countCharacterFrequency(@Valid @RequestBody EnteredTextDto text, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrorMap(bindingResult));
        }
        var charFrequencyMap = service.countCharacterFrequency(text.getValue());
        var resultDto = new ResultDto(charFrequencyMap);
        return ResponseEntity.ok(resultDto);
    }

    private Map<String, String> getErrorMap(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (k1, k2) -> k1
                ));
    }

}
