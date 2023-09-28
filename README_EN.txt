1.APP DESCRIPTION.
This App returns a string representation of a map containing the frequency of each character
used in the entered text. All values are sorted in descending order of frequency of character use
in the entered text. All whitespace characters are grouped into one value "whitespaces".

2. HOW TO USE.
2.1. USING ANY BROWSER
     This app has it own user interface. After running "main" method in the SymbolCounterApplication class
     the functionality of this application can be accessed using any browser at the URL
     http://localhost:8080. The main functionality is located in the “Let's get started” section
     at the end of the home page.
2.2. USING REST API
     After running "main" method in SymbolCounterApplication class this app functionality could be accessed by:
     - clients: Postman, etc.,
     - method: "POST",
     - URL: http://localhost:8080/character/countFrequency
     - body: raw
     - content-type: application/json
     - JSON : { "value": "typeHereAnyText"}

3.PARAMETERS.
In browser (in textarea of section "Let's get started") or in the field "value" of JSON object
you can enter any words, phrases or sentences using:
- any Latin or Cyrillic characters ("a"-"z", "A"-"Z", "а"-"я", "А"-"Я"),
- any digit characters ("0"-"9"),
- or any punctuation characters ("_!"#$%&'()*+,-./:;<=>?@[]^_`{|}~").

4.RETURN.
4.1. WHEN USING A BROWSER.
The result will be a string representation of a map containing the frequency of each
character used in the entered text. All values will be sorted in descending order of frequency of
character use in the entered text. All whitespace characters will be grouped into one value "whitespaces".
           Examples:
        (a) Input:  "abbccc"
            Output: "c": 3, "b": 2, "a": 1
        (b) Input:  "a b b c c c"
            Output: "whitespace": 5, "c": 3, "b": 2, "a": 1
        (c) Input:  "a+b-b=c*c/c"
            Output: "c": 3, "b": 2, "a": 1, "*": 1, "+": 1, "-": 1, "=": 1, "/": 1
4.2. WHEN USING A REST API
Returns JSON object with single field "characterFrequency" that contains a string representation of a map
containing the frequency of each character used in the entered text. All whitespace characters are grouped
into one value "whitespaces".

NOTE: To ensure correct output of the string, all quote characters are escaped.

            Examples:
        (a) Input:  {
                        "value": "abbccc"
                    }
            Output: {
                        "characterFrequency": "\"c\": 3, \"b\": 2, \"a\": 1"
                    }
        (b) Input:  {
                        "value": "a b b c c c"
                    }
            Output: {
                        "characterFrequency": "\"whitespace\": 5, \"c\": 3, \"b\": 2, \"a\": 1"
                    }
        (c) Input:  {
                        "value": "a+b-b=c*c/c"
                    }
            Output: {
                        "characterFrequency": "\"c\": 3, \"b\": 2, \"a\": 1, \"*\": 1, \"+\": 1, \"-\": 1, \"=\": 1, \"/\": 1"
                    }

5. ERRORS
If
 (a) text size will be less than 1 character, or
 (b) text will contain characters not specified in section "3.PARAMETERS" (for example Chinese characters),
the application will return as response:
5.1 when using a browser: appropriate warning notice describing the reason for the error;
5.2 when using REST API:
- status: Bad Request
- status code: 400
- content-type: application/json
- JSON: {
            "value": "Error message"
        }
