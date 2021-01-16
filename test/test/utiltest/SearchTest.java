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

    // コンテンツ中の文字列を検索し、コンテキストを含む結果を返す
    @Test
    public void returnsMatchesShowingContextWhenSearchStringInContext()
            throws IOException {

        InputStream stream =
                streamOn("There are certain queer times and occasions "
                        + "in this strange mixed affair we call life when a man "
                        + "takes this whole universe for a vast practical joke, "
                        + "though the wit thereof he but dimly discerns, and more "
                        + "than suspects that the joke is at nobody's expense but "
                        + "his own.");

        Search search = new Search(stream, "practical joke", A_TITLE);
        Search.LOGGER.setLevel(Level.OFF);
        search.setSurroundingCharacterCount(10);
        search.execute();
        assertFalse(search.errored());
        assertThat(search.getMatches(), containsMatches(new Match[]{
                new Match(A_TITLE, "practical joke", "or a vast practical joke, though t")
        }));
        stream.close();
    }

    // コンテンツ中に文字列がない場合、空の結果を返す
    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent()
            throws MalformedURLException, IOException {

        URLConnection connection =
                new URL("http://bit.ly/15sYPA7").openConnection();
        InputStream inputStream = connection.getInputStream();
        Search search = new Search(inputStream, "smelt", A_TITLE);
        search.execute();
        assertTrue(search.getMatches().isEmpty());
        inputStream.close();
   }

   private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
   }
}
