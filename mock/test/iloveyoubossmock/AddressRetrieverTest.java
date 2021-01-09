package iloveyoubossmock;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import org.json.simple.parser.*;
import org.junit.*;
import org.mockito.*;
import utilmock.*;

public class AddressRetrieverTest {

    @Mock
    private Http http;

    @InjectMocks
    private AddressRetriever retriever;

    @Before
    public void createRetriever() {
        retriever = new AddressRetriever();
        MockitoAnnotations.initMocks(this);
    }

    // 正当な座標に対して適切な住所を返す
    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {

        when(http.get(contains("lat=38.000000&lon=-104.000000")))
                .thenReturn(
                            "{\"address\":{"
                            + "\"house_number\":\"324\","
                            + "\"road\":\"North Tejon Street\","
                            + "\"city\":\"Colorado Springs\","
                            + "\"state\":\"Colorado\","
                            + "\"postcode\":\"80903\","
                            + "\"country_code\":\"us\"}"
                            + "}");

        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.houseNumber, equalTo("324"));
        assertThat(address.road, equalTo("North Tejon Street"));
        assertThat(address.city, equalTo("Colorado Springs"));
        assertThat(address.state, equalTo("Colorado"));
        assertThat(address.zip, equalTo("80903"));
    }
}