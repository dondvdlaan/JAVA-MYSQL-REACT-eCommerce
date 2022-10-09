package com.manyroadsdev.server.logic;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.manyroadsdev.server.model.Price;
import com.manyroadsdev.server.model.Product;

import java.io.IOException;

public class GenerateProduct {

    // region 1. Methoden
    //1. JSON Product Objekt erzeugen
public static String mapJSON(){



    //Variabelen
    String json = "";
 /**
    Price priceChair = new Price("99.99","99.99 Eurotjes","€ 99.99",99.99);
    Product chair = new Product("Mooie Stoel","Best in town","./target/stoel.png",1);

    chair.setPrice(priceChair);
    //2. Starten
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    try {
        //String json = mapper.writeValueAsString(car);
        json = ow.writeValueAsString(chair);
        System.out.println("json " + json );
    } catch (JsonMappingException e) {
        e.printStackTrace();
    } catch (JsonGenerationException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

  */
return json;
 }


}
