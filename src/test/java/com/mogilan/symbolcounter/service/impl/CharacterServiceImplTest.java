package com.mogilan.symbolcounter.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CharacterServiceImplTest {

    @Autowired
    private CharacterServiceImpl characterService;

    @ParameterizedTest
    @MethodSource("getArgumentsForCountFrequencyOfCharactersTest")
    void countFrequencyOfCharactersSuccess(String text, String expectingResult) {
        var actualResult = characterService.countCharacterFrequency(text);
        assertThat(actualResult).isEqualTo(expectingResult);
    }

    @ParameterizedTest
    @NullSource
    void countFrequencyOfCharactersShouldThrowExceptionIfTextIsNull(String text) {
        Assertions.assertThrows(NullPointerException.class, () -> characterService.countCharacterFrequency(text));

    }

    static Stream<Arguments> getArgumentsForCountFrequencyOfCharactersTest() {
        return Stream.of(
                Arguments.of("aaaaabcccc", "\"a\": 5, \"c\": 4, \"b\": 1"),
                Arguments.of("abbccc", "\"c\": 3, \"b\": 2, \"a\": 1"),
                Arguments.of("a b b c c c", "\"whitespace\": 5, \"c\": 3, \"b\": 2, \"a\": 1"),
                Arguments.of("a+b-b=c*c/c",
                        "\"c\": 3, \"b\": 2, \"a\": 1, \"*\": 1, \"+\": 1, \"-\": 1, \"=\": 1, \"/\": 1"),
                Arguments.of("Hello! I'm Ilya Mogilan. I hope you liked the way this app is developed:)",
                        "\"whitespace\": 13, \"e\": 7, \"l\": 6, \"o\": 5, \"a\": 4, \"i\": 4, \"p\": 4, \"I\": 3, \"d\": 3, \"h\": 3, \"y\": 3, \"s\": 2, \"t\": 2, \"H\": 1, \"M\": 1, \"!\": 1, \"'\": 1, \"g\": 1, \")\": 1, \"k\": 1, \"m\": 1, \"n\": 1, \".\": 1, \"u\": 1, \"v\": 1, \"w\": 1, \":\": 1")
        );
    }
}