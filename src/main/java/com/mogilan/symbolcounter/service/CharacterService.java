package com.mogilan.symbolcounter.service;

public interface CharacterService {

    /**
     * Returns a string representation of a map containing the frequency of each
     * character used in the entered text. All values are sorted in descending
     * order of frequency of character use. All whitespace characters are grouped
     * into one value "whitespaces".
     *
     * <p>Examples:</p>
     * <blockquote><pre>
     *countCharacterFrequency ("abbccc") returns "c": 3, "b": 2, "a": 1
     *countCharacterFrequency ("a b b c c c") returns "whitespace": 5, "c": 3, "b": 2, "a": 1
     *countCharacterFrequency ("a+b-b=c*c/c") returns "c": 3, "b": 2, "a": 1, "*": 1, "+": 1, "-": 1, "=": 1, "/": 1
     * </pre></blockquote>
     *
     * @param  text - the String containing any characters. Whitespace characters also but in this case they will be grouped into one value "whitespaces"
     *
     * @throws  NullPointerException  if text is null.
     *
     * @return  A string representation of a map containing the frequency of each
     * character used in the entered text. All whitespace characters are grouped into one value "whitespaces".
     */
    String countCharacterFrequency(String text);

}
