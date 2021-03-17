import com.practice.api.CountryApiClient;
import com.practice.api.model.Country;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ApiTest {

    @Test
    public void getCountries() throws Exception{
        try {
            List<Country> countries = CountryApiClient.getCountryApi().getCountries().
                    execute().
                    body();
            Assert.assertNotNull(countries);
        }catch (Exception e){

        }
    }
}
