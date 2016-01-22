package com.ollynural.app.main.dao;


import com.ollynural.app.converters.validation.ValidationOfUsername;
import com.ollynural.app.database.retrievedatabase.DatabaseAccessor;
import com.ollynural.app.database.sendtodatabase.DatabaseSender;
import com.ollynural.app.dto.total.SingleSummonerPlayerDTO;
import com.ollynural.app.dto.summonerBasicDTO.SummonerBasicDTO;
import com.ollynural.app.dto.rankedDTO.SummonerRankedInfoDTO;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.main.JsonHTTPGetters.RiotURLSender;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class BasicDAO {

    private final DatabaseAccessor databaseAccessor = new DatabaseAccessor();
    private final DatabaseSender databaseSender = new DatabaseSender();
    private final MiddleManClass middleManClass = new MiddleManClass();
    private final RiotURLSender riotURLSender = new RiotURLSender();

    final static Logger logger = Logger.getLogger(BasicDAO.class);

    public int checkAndUpdateSummonerNameAndInformation(String username, String universityName) throws Exception {

        SummonerBasicDTO summonerBasicDTO = databaseAccessor.returnSummonerDTOFromDatabaseUsingName(username);
        SummonerRankedInfoDTO summonerRankedInfoDTO = databaseAccessor.getSummonerRankedInfoForGivenId(summonerBasicDTO.getSingleSummonerBasicDTO().getId());
        SummonerUniversityDTO summonerUniversityDTO = databaseAccessor.returnUniversityDTOFromID(summonerBasicDTO.getSingleSummonerBasicDTO().getId());

        Long basicID = summonerBasicDTO.getSingleSummonerBasicDTO().getId();
        Long rankedID = summonerRankedInfoDTO.getID();
        Long universityID = summonerUniversityDTO.getID();

        if (basicID != null && rankedID != null && universityID != null) {
            System.out.println("The IDs already exist in all databases");
            return 0;
        }

        if (summonerBasicDTO.getSingleSummonerBasicDTO().getId() == null) {
            middleManClass.getAndStoreBasicRankedAndUniversityInfo(username, universityName);
            System.out.println("ID does not exist in database");
            System.out.println("Inserting ID and information into basic summoner info, ranked info and university info");
            return 2;
        } else {

            if (summonerRankedInfoDTO.getID() == null) {
                middleManClass.checkAndInsertRankingInfo(summonerBasicDTO.getSingleSummonerBasicDTO().getId());
                System.out.println("Basic info exists in database.");
                System.out.println("Inserting Ranked info into database");
            }

            if (summonerUniversityDTO.getID() == null) {
                middleManClass.checkAndInsertUniversityInfo(summonerBasicDTO.getSingleSummonerBasicDTO().getId(), universityName);
                System.out.println("Basic info and ranked info exist in database");
                System.out.println("Inserting university info into database");
            }
            return 1;
        }
    }

    public SingleSummonerPlayerDTO getOrRetrieveBasicAndRankedSummonerInformation(String summonerName) throws IOException, ParseException, SQLException {

        String newSummonerName = ValidationOfUsername.validateUsername(summonerName);

        SingleSummonerPlayerDTO singleSummonerPlayerDTO = new SingleSummonerPlayerDTO();
        SummonerRankedInfoDTO summonerRankedInfoDTO;
        SummonerUniversityDTO summonerUniversityDTO;
        SummonerBasicDTO summonerBasicDTO = new SummonerBasicDTO();

        Long rankedId;
        Long universityId;
        Long basicId;

        // Send get request to get basic information from the name and assign to main DTO
        // We do this to get the most recent basic info to check for any name changes - Will make more efficient in the future.
        summonerBasicDTO = riotURLSender.getSummonerBasicInfoByUsername(newSummonerName);

        singleSummonerPlayerDTO.setSummonerBasicDTO(summonerBasicDTO);
        logger.info("SummonerBasicDTO is: " + summonerBasicDTO.getNonMappedAttributes());

        // Assign summonerId to the ID returned
        basicId = summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId();

        logger.info("basicId: " + basicId);

        // Get the one from the database
        SummonerBasicDTO summonerBasicDTOOld = databaseAccessor.returnSummonerBasicDTOFromDatabaseUsingId(basicId);

        // Checking if the summonerName from the call is different to the one in the database
        // This is a problem with the unique key constraint as when you change username, the ID remains the same
        if (summonerBasicDTOOld.getNonMappedAttributes() != null) {
            logger.info(summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId());

            Object myKey = summonerBasicDTOOld.getNonMappedAttributes().keySet().toArray()[0];
            Long oldSummonerName = summonerBasicDTOOld.getNonMappedAttributes().get(myKey).getId();
            logger.info(oldSummonerName);

            if (summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId().equals(summonerBasicDTOOld.getNonMappedAttributes().get(myKey).getId())) {
                // This means the username has changed since we last stored it in the database
                // We need to delete data from the basic table with the ID and then re-insert it with the new data
                databaseSender.deleteSummonerBasicInfoForGivenId(basicId);
                databaseSender.insertSummonerBasicInfo(summonerBasicDTO, newSummonerName);
            } else {
                // Enter information into database
                databaseSender.insertSummonerBasicInfo(summonerBasicDTO, newSummonerName);
            }
        } else {
            databaseSender.insertSummonerBasicInfo(summonerBasicDTO, newSummonerName);
        }
        singleSummonerPlayerDTO.setSummonerBasicDTO(summonerBasicDTO);

        summonerRankedInfoDTO = databaseAccessor.getSummonerRankedInfoForGivenId(basicId);
        rankedId = summonerRankedInfoDTO.getID();
        logger.info("rankedId that existed: " + rankedId);

        summonerUniversityDTO = databaseAccessor.returnUniversityDTOFromID(basicId);
        universityId = summonerRankedInfoDTO.getID();
        logger.info("universityId that existed: " + universityId);

        if (rankedId != null) {
            // Set the values as the database values, and return what is already in the database
            singleSummonerPlayerDTO.setSummonerBasicDTO(summonerBasicDTO);
            singleSummonerPlayerDTO.setSummonerRankedInfoDTO(summonerRankedInfoDTO);
            logger.info("IDs already all exist in the database");
        }

        if (rankedId == null || rankedId == 0) {
            // Send get request to get ranked information from the name and assign to main DTO
            summonerRankedInfoDTO = riotURLSender.getRankedInfoByID(basicId);
            logger.info("SummonerRankedInfoDTO is: " + summonerRankedInfoDTO.getIntegerSummonerRankedInfoDTOEntryMap());
            singleSummonerPlayerDTO.setSummonerRankedInfoDTO(summonerRankedInfoDTO);
            // Enter information into database
            databaseSender.insertRankedInfo(summonerRankedInfoDTO);
        } else {
            logger.info("SummonerRankedInfoDTO is: " + summonerRankedInfoDTO.getIntegerSummonerRankedInfoDTOEntryMap());
            System.out.println("This is just here for validation and future caching abilities");
        }

        if (universityId == null || universityId == 0) {
            summonerUniversityDTO.setUniversityName("None");
            logger.info("SummonerUniversityDTO is: " + summonerUniversityDTO.getUniversityName());
        } else {
            logger.info("SummonerUniversityDTO is: null");
            System.out.println("This is just here for validation");
        }

        logger.info("GOT TO THE END");
        return singleSummonerPlayerDTO;


    }

    public SummonerUniversityDTO getUniversityRankingsByUniversityCode(String universityCode) {
        // Do a search on database returning a list of all summoner id
    }
}
