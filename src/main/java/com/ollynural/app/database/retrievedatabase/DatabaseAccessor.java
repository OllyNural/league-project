package com.ollynural.app.database.retrievedatabase;


import com.ollynural.app.converters.JsonToDTO;
import com.ollynural.app.dto.summonerBasicDTO.SingleSummonerBasicDTO;
import com.ollynural.app.dto.summonerBasicDTO.SummonerBasicDTO;
import com.ollynural.app.dto.rankedDTO.SummonerRankedInfoDTO;
import com.ollynural.app.dto.SummonerUniversityDTO;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
/**
 * Created by Admin on 14/11/2015.
 * Class to send requests to the Database
 */
public class DatabaseAccessor {


    //Properties here until i get properties file working properly
    private String TABLE_BASIC_INFO = "league_database_schema.basic_summoner_info";
    private String TABLE_RANKED_INFO = "league_database_schema.ranked_info";
    private String TABLE_UNIVERSITY_INFO = "league_database_schema.university_info";
    private String DATABASE_SCHEMA = "jdbc:mysql://localhost:3306/league_database_schema";
    private String USERNAME = "admin";
    private String PASSWORD = "admin_pass";

    private SummonerRankedInfoDTO summonerRankedInfoDTO = new SummonerRankedInfoDTO();
    private SummonerUniversityDTO summonerUniversityDTO = new SummonerUniversityDTO();
    private JsonToDTO jsonToDTO = new JsonToDTO();

    final static Logger logger = Logger.getLogger(DatabaseAccessor.class);

    private Properties prop = new Properties();
    private InputStream input = null;

    public SummonerBasicDTO returnSummonerDTOFromDatabaseUsingName(String newUsername) {

        SummonerBasicDTO summonerBasicDTO = new SummonerBasicDTO();
        SingleSummonerBasicDTO singleSummonerBasicDTO = new SingleSummonerBasicDTO();

        // Database setup
        logger.info("Returning Summoner DTO from database using Summoner Name");
        PreparedStatement stmt = null;
        try {

            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
            }

            prop.load(input);

            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            //String TABLE_UNIVERSITY_INFO = prop.getProperty("DATABASE_SCHEMA_NAME") + "." + prop.getProperty("TABLE_BASIC_SUMMONER_INFO");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                logger.error("Error getting database driver", e);
            }

            String query = "SELECT * FROM " + TABLE_BASIC_INFO + " WHERE summoner_name = ? ";

            Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);

            stmt = conn.prepareStatement(query);
            stmt.setString(1, newUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //Set all the values into a DTO
                summonerBasicDTO = new SummonerBasicDTO();

                singleSummonerBasicDTO.setId(rs.getInt("summonerID"));
                singleSummonerBasicDTO.setName(rs.getString("summoner_name"));
                singleSummonerBasicDTO.setProfileIconId(rs.getInt("summoner_icon"));
                singleSummonerBasicDTO.setRevisionDate(rs.getLong("revision_date"));
                singleSummonerBasicDTO.setSummonerLevel(rs.getInt("summoner_level"));
                summonerBasicDTO.setNonMappedAttributes(newUsername, singleSummonerBasicDTO);

            }
        } catch (Exception e) {
            System.out.println("Database error with connection: " + e);
            e.printStackTrace();
        }
        //Close stmt and connection
        try{
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Basic DTO that was in database: " + summonerBasicDTO.getNonMappedAttributes());
        return summonerBasicDTO;
    }

    public SummonerBasicDTO returnSummonerBasicDTOFromDatabaseUsingId(Long basicId) {

        SummonerBasicDTO summonerBasicDTO = new SummonerBasicDTO();
        SingleSummonerBasicDTO singleSummonerBasicDTO = new SingleSummonerBasicDTO();

        // Database setup
        logger.info("Returning Summoner DTO from database using Summoner ID");
        PreparedStatement stmt = null;
        try {

            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
            }

            prop.load(input);

            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            //String TABLE_UNIVERSITY_INFO = prop.getProperty("DATABASE_SCHEMA_NAME") + "." + prop.getProperty("TABLE_BASIC_SUMMONER_INFO");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                logger.error("Error getting database driver", e);
            }

            String query = "SELECT * FROM " + TABLE_BASIC_INFO + " WHERE summonerID = ? ";

            Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);

            stmt = conn.prepareStatement(query);
            stmt.setLong(1, basicId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //Set all the values into a DTO
                summonerBasicDTO = new SummonerBasicDTO();

                singleSummonerBasicDTO.setId(rs.getInt("summonerID"));
                singleSummonerBasicDTO.setName(rs.getString("summoner_name"));
                singleSummonerBasicDTO.setProfileIconId(rs.getInt("summoner_icon"));
                singleSummonerBasicDTO.setRevisionDate(rs.getLong("revision_date"));
                singleSummonerBasicDTO.setSummonerLevel(rs.getInt("summoner_level"));
                summonerBasicDTO.setNonMappedAttributes(rs.getString("summoner_name"), singleSummonerBasicDTO);

            }
        } catch (Exception e) {
            System.out.println("Database error with connection: " + e);
            e.printStackTrace();
        }
        //Close stmt and connection
        try{
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Basic DTO that was in database: " + summonerBasicDTO.getNonMappedAttributes());
        return summonerBasicDTO;
    }

    public SummonerRankedInfoDTO getSummonerRankedInfoForGivenId(Long summonerID) {
        // Database Setup
        logger.info("Checking Summoner Ranking Exists for given Summoner ID");
        try {
            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
            }
            prop.load(input);
            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            //String TABLE_RANKED_INFO = prop.getProperty("TABLE_RANKED_INFO");

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                logger.error("Error getting database driver", e);
            }

            PreparedStatement stmt;
            String query = "SELECT * FROM " + TABLE_RANKED_INFO + " WHERE summonerID = ? ";

            Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);

            stmt = conn.prepareStatement(query);
            stmt.setLong(1, summonerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                summonerRankedInfoDTO.setUpdateTime(rs.getDate("update_time"));
                summonerRankedInfoDTO = jsonToDTO.convertRankedInformationStringToJson(rs.getString("ranked_information_for_summoner"));
                summonerRankedInfoDTO.setID(rs.getLong("summonerID"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return summonerRankedInfoDTO;
    }

    public SummonerUniversityDTO returnUniversityDTOFromID(Long summonerID){
        // Database Setup
        logger.info("Returning University DTO from given Summoner ID");
        try {
            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
            }
            prop.load(input);
            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            //String TABLE_UNIVERSITY_INFO = prop.getProperty("TABLE_UNIVERSITY_INFO");

            Class.forName("com.mysql.jdbc.Driver");

            PreparedStatement stmt;
            String query = "SELECT * FROM " + TABLE_UNIVERSITY_INFO + " WHERE summonerID = ? ";

            Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);

            stmt = conn.prepareStatement(query);
            stmt.setLong(1, summonerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                summonerUniversityDTO.getID(rs.getLong("summonerID"));
                summonerUniversityDTO.setUniversityName(rs.getString("university_code"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return summonerUniversityDTO;
    }
}
