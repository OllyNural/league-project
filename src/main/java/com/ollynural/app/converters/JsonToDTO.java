package com.ollynural.app.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.dto.SummonerBasicDTO;
import com.ollynural.app.dto.leagueentrydto.SummonerRankedInfoDTOEntry;

import java.io.IOException;

/**
 * Converts json from GET request to the DTO assosiated with that object
 */
public class JsonToDTO {

    private SummonerBasicDTO summonerBasicDTO = new SummonerBasicDTO();
    private SummonerRankedInfoDTOEntry summonerRankedInfoDTOEntry = new SummonerRankedInfoDTOEntry();

    public SummonerBasicDTO convertBasicInformationStringToJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            summonerBasicDTO = mapper.readValue(json, SummonerBasicDTO.class);

//            summonerBasicDTO.setId(json.getJSONObject(username).getLong("id"));
//            summonerBasicDTO.setName(json.getJSONObject(username).getString("name"));
//            summonerBasicDTO.setSummonerIcon(json.getJSONObject(username).getInt("profileIconId"));
//            summonerBasicDTO.setRevisionDate(json.getJSONObject(username).getLong("revisionDate"));
//            summonerBasicDTO.setSummonerLevel(json.getJSONObject(username).getInt("summonerLevel"));

            //String loudScreaming = json.getJSONObject("LabelData").getString("slogan");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summonerBasicDTO;
    }

    public SummonerRankedInfoDTOEntry convertRankedInformationStringToJson(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            summonerRankedInfoDTOEntry = mapper.readValue(json, SummonerRankedInfoDTOEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summonerRankedInfoDTOEntry;
    }

}
