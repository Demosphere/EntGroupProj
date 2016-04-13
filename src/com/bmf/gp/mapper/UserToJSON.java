package com.bmf.gp.mapper;


import com.bmf.gp.entity.UsersEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/**
 * Created by Brendon on 4/12/2016.
 */
public class UserToJSON {
    ObjectMapper mapper = new ObjectMapper();
    Logger logger = Logger.getLogger(this.getClass());

    public String createJSONFromUser(UsersEntity user) throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(user);
        //logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
        return jsonString;
    }
}
