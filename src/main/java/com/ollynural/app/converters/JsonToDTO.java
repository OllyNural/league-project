package com.ollynural.app.converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ollynural.app.dto.rankedDTO.SummonerRankedInfoDTO;
import com.ollynural.app.dto.summonerBasicDTO.SummonerBasicDTO;
import com.ollynural.app.dto.rankedDTO.leagueentrydto.SummonerRankedInfoDTOEntry;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts json from GET request to the DTO assosiated with that object
 */
public class JsonToDTO {

    final static Logger logger = Logger.getLogger(JsonToDTO.class);

    private SummonerBasicDTO summonerBasicDTO = new SummonerBasicDTO();
    private SummonerRankedInfoDTO summonerRankedInfoDTO = new SummonerRankedInfoDTO();

    public SummonerBasicDTO convertBasicInformationStringToJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            summonerBasicDTO = mapper.readValue(json, SummonerBasicDTO.class);

//            summonerBasicDTO.setId(json.getJSONObject(username).getLong("id"));
//            summonerBasicDTO.setName(json.getJSONObject(username).getString("name"));
//            summonerBasicDTO.setProfileIconId(json.getJSONObject(username).getInt("profileIconId"));
//            summonerBasicDTO.setRevisionDate(json.getJSONObject(username).getLong("revisionDate"));
//            summonerBasicDTO.setSummonerLevel(json.getJSONObject(username).getInt("summonerLevel"));

            //String loudScreaming = json.getJSONObject("LabelData").getString("slogan");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summonerBasicDTO;
    }

    public SummonerRankedInfoDTO convertRankedInformationStringToJson(String json){
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory factory = mapper.getTypeFactory();
        // type of key of response map
        JavaType stringType = factory.constructType(String.class);
        // type of value of response map
        JavaType listOfDtosType = factory.constructCollectionType(ArrayList.class, SummonerRankedInfoDTOEntry.class);
        // create type of map
        JavaType responseType = factory.constructMapType(HashMap.class, stringType, listOfDtosType);
        try {
            assert json != null;
            Map<String, List<SummonerRankedInfoDTOEntry>> response = new ObjectMapper().readValue(json, responseType);
            summonerRankedInfoDTO.setIntegerSummonerRankedInfoDTOEntryMap(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summonerRankedInfoDTO;
    }

}
