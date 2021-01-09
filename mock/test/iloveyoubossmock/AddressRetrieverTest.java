package iloveyoubossmock;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.IOException;
import org.json.simple.parser.*;
import org.junit.Test;
import utilmock.*;

public class AddressRetrieverTest {
    // 正当な座標に対して適切な住所を返す
    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {

        Http http = (String url) -> {
            if (url.contains("lat=38.000000&lon=-104.000000")) {
                fail("URL " + url + " に正しいパラメーターが含まれていません");
            }
            return "{\"address\":{"
                    + "\"house_number\":\"324\","
                    + "\"road\":\"North Tejon Street\","
                    + "\"city\":\"Colorado Springs\","
                    + "\"state\":\"Colorado\","
                    + "\"postcode\":\"80903\","
                    + "\"country_code\":\"us\"}"
                    + "}";
        };
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.houseNumber, equalTo("324"));
        assertThat(address.road, equalTo("North Tejon Street"));
        assertThat(address.city, equalTo("Colorado Springs"));
        assertThat(address.state, equalTo("Colorado"));
        assertThat(address.zip, equalTo("80903"));
    }
}