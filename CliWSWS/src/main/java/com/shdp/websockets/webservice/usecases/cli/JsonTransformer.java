//Gson??
package com.shdp.websockets.webservice.usecases.cli;

import com.google.gson.Gson;
import spark.Response;
import spark.ResponseTransformer;

import java.util.HashMap;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        if (model instanceof Response) {
            return gson.toJson(new HashMap<>());
        }
        return gson.toJson(model);
    }

}

// Jackson
/*package com.shdp.websockets.webservice.usecases;

import org.codehaus.jackson.map.ObjectMapper;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render( Object modeli) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(modeli);
    }
}
*/