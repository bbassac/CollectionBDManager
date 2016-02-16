package lioncorps.collectionbdmanager.utils;

import android.app.Activity;

import com.github.kevinsawicki.http.HttpRequest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;

import lioncorps.collectionbdmanager.R;
import lioncorps.collectionbdmanager.bean.Collection;


/**
 * Created by b.bassac on 29/12/2014.
 */
public class CollectionProvider {

    static public Collection getEntireCollection(Activity activity) {
        // Getting JSON from URL
        String stringJson = null;
        Collection listBD = null;
        try {
            stringJson = WSProvider.getJSONFromUrl(activity, activity.getString(R.string.url));
            JsonParser jp = new JsonFactory().createJsonParser(new ByteArrayInputStream(stringJson.getBytes("UTF-8")));
            //Jacksonize to bean
            listBD = new ObjectMapper().readValue(jp, Collection.class);
        } catch (Exception e) {
            listBD = new Collection();
        }
        return listBD;
    }

    private static class WSProvider {


        public static String getJSONFromUrl(Activity activity, String url) throws CustomException {
            String json;
            // defaultHttpClient
            try {

                HttpRequest httpRequest = HttpRequest.get(url);
                json = httpRequest.body();

            } catch (HttpRequest.HttpRequestException e) {
                json="{}";
            }

            return json;

        }


    }
}
