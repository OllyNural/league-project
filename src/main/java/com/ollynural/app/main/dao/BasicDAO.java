package com.ollynural.app.main.dao;


import com.ollynural.app.database.retrievedatabase.DatabaseAccessor;
import com.ollynural.app.database.sendtodatabase.DatabaseSender;
import com.ollynural.app.dto.SingleSummonerPlayerDTO;
import com.ollynural.app.dto.SummonerBasicDTO;
import com.ollynural.app.dto.SummonerRankedInfoDTO;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.main.JsonHTTPGetters.RiotURLSender;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class BasicDAO {

    private final DatabaseAccessor databaseAccessor = new DatabaseAccessor();
    private final DatabaseSender databaseSender = new DatabaseSender();
    private final MiddleManClass middleManClass = new MiddleManClass();
    private final RiotURLSender riotURLSender = new RiotURLSender();

    private java.sql.Date sqlDate;
    private java.util.Date date;

    public int checkAndUpdateSummonerNameAndInformation(String username, String universityName) throws Exception {

        SummonerBasicDTO summonerBasicDTO = databaseAccessor.returnSummonerDTOFromDatabaseUsingName(username);
        SummonerRankedInfoDTO summonerRankedInfoDTO = databaseAccessor.checkSummonerRankingExistsForGivenId(summonerBasicDTO.getId());
        SummonerUniversityDTO summonerUniversityDTO = databaseAccessor.returnUniversityDTOFromID(summonerBasicDTO.getId());

        Long basicID = summonerBasicDTO.getId();
        Long rankedID = summonerRankedInfoDTO.getID();
        Long universityID = summonerUniversityDTO.getID();

        if (basicID != null && rankedID != null && universityID != null) {
            System.out.println("The IDs already exist in all databases");
            return 0;
        }

        if (summonerBasicDTO.getId() == null) {
            middleManClass.getAndStoreBasicRankedAndUniversityInfo(username, universityName);
            System.out.println("ID does not exist in database");
            System.out.println("Inserting ID and information into basic summoner info, ranked info and university info");
            return 2;
        } else {

            if (summonerRankedInfoDTO.getID() == null) {
                middleManClass.checkAndInsertRankingInfo(summonerBasicDTO.getId());
                System.out.println("Basic info exists in database.");
                System.out.println("Inserting Ranked info into database");
            }

            if (summonerUniversityDTO.getID() == null) {
                middleManClass.checkAndInsertUniversityInfo(summonerBasicDTO.getId(), universityName);
                System.out.println("Basic info and ranked info exist in database");
                System.out.println("Inserting university info into database");
            }
            return 1;
        }
    }

    public SingleSummonerPlayerDTO getOrRetrieveBasicAndRankedSummonerInformation(String summonerName) throws IOException, ParseException, SQLException {

        long summonerId;

        SingleSummonerPlayerDTO singleSummonerPlayerDTO = new SingleSummonerPlayerDTO();
        SummonerBasicDTO summonerBasicDTO = databaseAccessor.returnSummonerDTOFromDatabaseUsingName(summonerName);
        SummonerRankedInfoDTO summonerRankedInfoDTO = databaseAccessor.checkSummonerRankingExistsForGivenId(summonerBasicDTO.getId());
        SummonerUniversityDTO summonerUniversityDTO = databaseAccessor.returnUniversityDTOFromID(summonerBasicDTO.getId());

        Long basicId = summonerBasicDTO.getId();
        Long rankedId = summonerRankedInfoDTO.getID();
        Long universityId = summonerUniversityDTO.getID();

        if (basicId != null && rankedId != null && universityId != null) {


            // Check caching on each one individually


            // Set the values as the database values, and return what is already in the database
            singleSummonerPlayerDTO.setSummonerBasicDTO(summonerBasicDTO);
            singleSummonerPlayerDTO.setSummonerRankedInfoDTO(summonerRankedInfoDTO);
            singleSummonerPlayerDTO.setSummonerUniversityDTO(summonerUniversityDTO);
            System.out.println("The IDs already exist in all databases");
        }

        if (basicId != null) {
            System.out.println("This is just here for validation and future caching abilities");
        } else {
            // Send get request to get basic information from the name and assign to main DTO
            summonerBasicDTO = riotURLSender.getSummonerBasicInfoByUsername(summonerName);
            singleSummonerPlayerDTO.setSummonerBasicDTO(summonerBasicDTO);
            // Enter information into database
            databaseSender.insertSummonerBasicInfo(summonerBasicDTO);
        }

        // Assign summonerId to the ID returned
        summonerId = summonerBasicDTO.getId();

        if (rankedId != null) {
            System.out.println("This is just here for validation and future caching abilities");
        } else {
            // Send get request to get ranked information from the name and assign to main DTO
            summonerRankedInfoDTO = riotURLSender.getRankedInfoByID(summonerId);
            singleSummonerPlayerDTO.setSummonerRankedInfoDTO(summonerRankedInfoDTO);
            // Enter information into database
            databaseSender.insertRankedInfo(summonerRankedInfoDTO);
        }

        if (universityId != null) {
            System.out.println("This is just here for validation");
        } else {
            summonerUniversityDTO.setUniversityName("None");
        }

        return singleSummonerPlayerDTO;


    }

}
