package com.cryptocodes.thorrentnext;

import com.cryptocodes.thorrentnext.tools.TitleParser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTests {
    @Test
    public void correctTitleParse_isCorrect() {
        String unparsedTitle = "Back to the Future (1985) Bluray SCNSrC x265";
        String unparsedTitle2 = "Back to the Future 2 (1989) Bluray SCNSrC x265";
        String unparsedTitle3 = "Back to the Future 3 (1991) Bluray SCNSrC x265";
        assertEquals("Back to the Future", TitleParser.parse(unparsedTitle));
        assertEquals("Back to the Future 2", TitleParser.parse(unparsedTitle2));
        assertEquals("Back to the Future 3", TitleParser.parse(unparsedTitle3));
    }
}
