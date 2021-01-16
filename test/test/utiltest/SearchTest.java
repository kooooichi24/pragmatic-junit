/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package utiltest;


// text courtesy of Herman Melville (Moby Dick) from 
// http://www.gutenberg.org/cache/epub/2701/pg2701.txt 

import java.io.*;
import java.net.*;
import java.util.*;
import org.junit.*;
import java.util.logging.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static utiltest.ContainsMatches.*;

public class SearchTest {
    private static final String A_TITLE = "1";
    private InputStream stream;

    @Before
    public void turnOffLogging() {
        Search.LOGGER.setLevel(Level.OFF);
    }

    @After
    public void closeResources() throws IOException {
        stream.close();
    }

    // コンテンツ中の文字列を検索し、コンテキストを含む結果を返す
    @Test
    public void returnsMatchesShowingContextWhenSearchStringInContext() {

        stream = streamOn("rest of text here"
                        + "1234567890search term1234567890"
                        + "more rest of text");
        Search search = new Search(stream, "search term", A_TITLE);
        search.setSurroundingCharacterCount(10);

        search.execute();

        assertThat(search.getMatches(), containsMatches(new Match[]{
                new Match(A_TITLE, "search term", "1234567890search term1234567890")
        }));
    }

    // コンテンツ中に文字列がない場合、空の結果を返す
    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent() {

        stream = streamOn("any text");
        Search search = new Search(stream, "text that doesn't match", A_TITLE);

        search.execute();

        assertTrue(search.getMatches().isEmpty());
   }

   // ストリームから読み込めない場合にはerroredはtrueを返す
    @Test
    public void returnsErroredWhenUnableToReadStream() {

        stream = createStreamThrowingErrorWhenRead();
        Search search = new Search(stream, "", "");

        search.execute();

        assertTrue(search.errored());
    }

    // 読み込もうとするとエラーが発生するストリームを生成する
    private InputStream createStreamThrowingErrorWhenRead() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };
    }

    // 読み込みが成功するとerroredはfalseを返す
    @Test
    public void erroredReturnsFalseWhenReadSucceeds() {

        stream = streamOn("");
        Search search = new Search(stream, "", "");

        search.execute();

        assertFalse(search.errored());
    }

    private InputStream streamOn(String pageContent) {
         return new ByteArrayInputStream(pageContent.getBytes());
    }
}
