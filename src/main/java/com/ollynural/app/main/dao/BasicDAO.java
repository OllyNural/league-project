package com.ollynural.app.main.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.converters.validation.ValidationOfUsername;
import com.ollynural.app.database.retrievedatabase.DatabaseAccessor;
import com.ollynural.app.database.sendtodatabase.DatabaseSender;
import com.ollynural.app.dto.total.SingleSummonerPlayerDTO;
import com.ollynural.app.dto.summonerBasicDTO.SummonerBasicDTO;
import com.ollynural.app.dto.rankedDTO.SummonerRankedInfoDTO;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.dto.total.UniversitySummonerDTO;
import com.ollynural.app.main.JsonHTTPGetters.RiotURLSender;
import org.apache.log4j.Logger;
import org.omg.SendingContext.RunTime;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class BasicDAO {

    private final DatabaseAccessor databaseAccessor = new DatabaseAccessor();
    private final DatabaseSender databaseSender = new DatabaseSender();
    private final MiddleManClass middleManClass = new MiddleManClass();
    private final RiotURLSender riotURLSender = new RiotURLSender();

    private ObjectMapper mapper = new ObjectMapper();

    final static Logger logger = Logger.getLogger(BasicDAO.class);

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

    public void addUniversityRankingWithSummonerNameAndUniversityCode(String summonerName, String universityCode) throws SQLException {
        logger.info(String.format("Adding university rankings for [%s] with university code of [%s]", summonerName, universityCode));
        String newSummonerName = ValidationOfUsername.validateUsername(summonerName);
        SummonerBasicDTO summonerBasicDTO;
        try {
            summonerBasicDTO = riotURLSender.getSummonerBasicInfoByUsername(newSummonerName);
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
        logger.info("SummonerBasicDTO is: " + summonerBasicDTO.getNonMappedAttributes());

        // Assign summonerId to the ID returned
        Long basicId = summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId();
        logger.info("basicId: " + basicId);
        // Get the one from the database
        SummonerBasicDTO summonerBasicDTOOld = databaseAccessor.returnSummonerBasicDTOFromDatabaseUsingId(basicId);

        // Checking if the summonerName from the call is different to the one in the database
        // This is a problem with the unique key constraint as when you change username, the ID remains the same
        if (summonerBasicDTOOld.getNonMappedAttributes() != null) {
            Object myKey = summonerBasicDTOOld.getNonMappedAttributes().keySet().toArray()[0];
            Long oldSummonerName = summonerBasicDTOOld.getNonMappedAttributes().get(myKey).getId();
            if (!summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId().equals(summonerBasicDTOOld.getNonMappedAttributes().get(myKey).getId()) && summonerBasicDTOOld.getNonMappedAttributes().get(myKey).getId() != null) {
                logger.info("Names were not the same from database and Riot call");
                // This means the username has changed since we last stored it in the database
                // We need to delete data from the basic table with the ID and then re-insert it with the new data
                databaseSender.deleteSummonerBasicInfoForGivenId(basicId);
                databaseSender.insertSummonerBasicInfo(summonerBasicDTO, newSummonerName);
            }
        } else {
            databaseSender.insertSummonerBasicInfo(summonerBasicDTO, newSummonerName);
        }

        // Insert ranked information
        SummonerRankedInfoDTO summonerRankedInfoDTO = riotURLSender.getRankedInfoByID(basicId);
        databaseSender.insertRankedInfo(summonerRankedInfoDTO);

        // Delete any occurrence of the summoner name in the database
        databaseSender.deleteUniversityInfoForGivenName(newSummonerName);
        databaseSender.addSummonerNameAndUniversityCode(basicId, summonerName, universityCode);

    }

    public UniversitySummonerDTO getUniversityRankingsByUniversityCode(String universityCode) throws SQLException {
        logger.info(String.format("Getting university rankings by code [%s]", universityCode));
        UniversitySummonerDTO universitySummonerDTO = databaseAccessor.getAllUniversityRankingsForGivenCode(universityCode);

        // Checks that the ranked information returns is existent, and if so whether it is in date
        for (int i = 0; i < universitySummonerDTO.getSummonerUniversityDTOs().size(); i++) {
            Long rankedSummonerId = universitySummonerDTO.getSummonerUniversityDTOs().get(i).getSummonerRankedInfoDTO().getID();
            Long summonerId = universitySummonerDTO.getSummonerUniversityDTOs().get(i).getID();
            if (rankedSummonerId == null) {
                logger.info("Getting ranked information from RIOT");
                logger.info(riotURLSender.getRankedInfoByID(summonerId));
                universitySummonerDTO.getSummonerUniversityDTOs().get(i).setSummonerRankedInfoDTO(riotURLSender.getRankedInfoByID(summonerId));
            } else {
                logger.info(String.format("Ranked information for [%s] existed but might be an old cache, UPDATING", summonerId));
                // Here you will check the cache
            }
                logger.info(universitySummonerDTO);
        }
        return universitySummonerDTO;
    }

}
