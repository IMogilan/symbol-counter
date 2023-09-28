package com.mogilan.symbolcounter.service.impl;

import com.mogilan.symbolcounter.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    public static final String WHITESPACE_KEY = "whitespace";

    @Override
    public String countCharacterFrequency(String text) {
        Objects.requireNonNull(text);

        var characterFrequencyMap = getNonWhitespaceFrequencyMap(text);
        var whitespaceFrequency = getWhitespaceFrequency(text);
        populateMapWithWhitespaceFrequency(characterFrequencyMap, whitespaceFrequency);
        var sortedLinkedMap = sortMapByValue(characterFrequencyMap);
        return mapToString(sortedLinkedMap);
    }

    private Map<String, Integer> getNonWhitespaceFrequencyMap(String text) {
        Map<String, Integer> nonWhitespaceFrequencyMap = new HashMap<>();
        text.chars()
                .mapToObj(value -> (char) (value))
                .filter(character -> !Character.isWhitespace(character))
                .forEach(character -> incrementAndPutCurrentCharacterFrequency(nonWhitespaceFrequencyMap, character));
        return nonWhitespaceFrequencyMap;
    }

    private void incrementAndPutCurrentCharacterFrequency(Map<String, Integer> map, char currentCharacter) {
        var currentString = Character.toString(currentCharacter);
        var currentFrequency = map.get(currentString);
        if (currentFrequency != null) {
            map.put(currentString, ++currentFrequency);
        } else {
            map.put(currentString, 1);
        }
    }

    private int getWhitespaceFrequency(String text) {
        return (int) text.chars()
                .mapToObj(value -> (char) (value))
                .filter(Character::isWhitespace)
                .count();
    }

    private void populateMapWithWhitespaceFrequency(Map<String, Integer> map, int whitespaceFrequency) {
        if (whitespaceFrequency > 0) {
            map.put(WHITESPACE_KEY, whitespaceFrequency);
        }
    }

    private Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private String mapToString(Map<String, Integer> sortedMap) {
        var stringBuilder = new StringBuilder();
        sortedMap.forEach((key, value) -> {
            var newFrequency = String.format("\"%s\": %d, ", key, value);
            stringBuilder.append(newFrequency);
        });
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }
}
