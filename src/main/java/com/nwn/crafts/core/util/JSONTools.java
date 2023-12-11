package com.nwn.crafts.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONTools {

    private static final Logger logger = LoggerFactory.getLogger(JSONTools.class);

    private JSONTools() {
    }

    public static String pojoToJSONString(Object object) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }

    public static <T> T jsonStringToPojo(String jsonString, Class<T> classType) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, classType);
    }
}
