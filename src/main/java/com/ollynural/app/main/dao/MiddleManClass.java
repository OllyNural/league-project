package com.ollynural.app.main.dao;

import com.ollynural.app.converters.JsonToDTO;
import com.ollynural.app.database.retrievedatabase.DatabaseAccessor;
import com.ollynural.app.database.sendtodatabase.DatabaseSender;
import com.ollynural.app.dto.SummonerBasicDTO;
import com.ollynural.app.dto.SummonerRankedInfoDTO;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.main.JsonHTTPGetters.RiotURLSender;

import java.sql.SQLException;

/**
 * Class that handles the middle-manning of retrieving, storing and checking information
 * from database and RIOT's API
 */
public class MiddleManClass {

    private final DatabaseAccessor databaseAccessor = new DatabaseAccessor();
    private final DatabaseSender databaseSender = new DatabaseSender();
    private final RiotURLSender riotURLSender = new RiotURLSender();
    private final JsonToDTO jsonToDTO = new JsonToDTO();

    public void getAndStoreBasicRankedAndUniversityInfo(String username, String universityName) throws Exception {
        try {
            // All the stuff for the summoner_basic DTO
            SummonerBasicDTO summonerBasicDTO = riotURLSender.getSummonerBasicInfoByUsername(username);
            databaseSender.insertSummonerBasicInfo(summonerBasicDTO);

            //All the stuff for the ranked_information DTO
            long summonerID = summonerBasicDTO.getId();
            databaseSender.insertRankedIdAlone(summonerID);

            //All the stuff for the university_info DTO
            databaseSender.insertUniversityIDAndName(summonerID, universityName);

            System.out.println("Successfully inserted basic information into basic_summoner_info table, ID into ranked_info table and all into university_info");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkSummonerNameStart(String username) throws SQLException, ClassNotFoundException {
        SummonerBasicDTO summonerBasicDTO = databaseAccessor.returnSummonerDTOFromDatabaseUsingName(username);
        if(summonerBasicDTO.getName() == ""){
            return false;
        }
        else return true;
    }

    public void checkAndInsertRankingInfo(Long summonerID) {
        try{
            SummonerRankedInfoDTO summonerRankedInfoDTO = databaseAccessor.checkSummonerRankingExistsForGivenId(summonerID);
            if(summonerRankedInfoDTO.getID() == null){
                databaseSender.insertRankedIdAlone(summonerID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAndInsertUniversityInfo(Long summonerID, String universityName) {
        try{
            SummonerUniversityDTO summonerUniversityDTO = databaseAccessor.returnUniversityDTOFromID(summonerID);
            if(summonerUniversityDTO.getID() == null){
                databaseSender.insertUniversityIDAndName(summonerID, universityName);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }



}
