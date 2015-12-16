package com.ollynural.app.database.retrievedatabase;


import com.ollynural.app.converters.JsonToDTO;
import com.ollynural.app.dto.SummonerBasicDTO;
import com.ollynural.app.dto.SummonerRankedInfoDTO;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.dto.leagueentrydto.SummonerRankedInfoDTOEntry;

import java.io.FileInputStream;
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

    private SummonerBasicDTO summonerBasicDTO = new SummonerBasicDTO();
    private SummonerRankedInfoDTO summonerRankedInfoDTO = new SummonerRankedInfoDTO();
    private SummonerUniversityDTO summonerUniversityDTO = new SummonerUniversityDTO();
    private JsonToDTO jsonToDTO = new JsonToDTO();

    private Properties prop = new Properties();
    private InputStream input = null;

    public SummonerBasicDTO returnSummonerDTOFromDatabaseUsingName(String username) {
        // Database setup
        PreparedStatement stmt = null;
        try {

            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
            }

            //input = new FileInputStream("/com/ollynural/app/resources/config.properties");
            prop.load(input);


            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            String TABLE_UNIVERSITY_INFO = prop.getProperty("TABLE_BASIC_INFO");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Can't find SQL Driver");
                e.printStackTrace();
            }


            String query = "SELECT * FROM" + TABLE_UNIVERSITY_INFO + " WHERE summonerName = ? ";


            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //Set all the values into a DTO
                summonerBasicDTO.setId(rs.getInt("summonerID"));
                summonerBasicDTO.setName(rs.getString("summoner_name"));
                summonerBasicDTO.setSummonerIcon(rs.getInt("summoner_icon"));
                summonerBasicDTO.setRevisionDate(rs.getInt("revision_date"));
                summonerBasicDTO.setSummonerLevel(rs.getInt("summoner_level"));
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
        return summonerBasicDTO;
    }

    public SummonerRankedInfoDTO checkSummonerRankingExistsForGivenId(Long summonerID) {
        // Database Setup
        try {
            input = new FileInputStream("/config.properties");
            prop.load(input);
            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            String TABLE_RANKED_INFO = prop.getProperty("TABLE_RANKED_INFO");

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL Driver?!?");
                e.printStackTrace();
            }


            PreparedStatement stmt;
            String query = "SELECT * FROM " + TABLE_RANKED_INFO + " WHERE summonerID = ? ";

            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            stmt = conn.prepareStatement(query);
            stmt.setLong(1, summonerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                summonerRankedInfoDTO.setUpdateTime(rs.getDate("update_time"));

                SummonerRankedInfoDTOEntry summonerRankedInfoDTOEntry = jsonToDTO.convertRankedInformationStringToJson(rs.getString("ranked_information_for_summoner"));
                summonerRankedInfoDTO.setRankedInformationForSummoner(summonerRankedInfoDTOEntry);

                summonerRankedInfoDTO.setID(rs.getLong("summonerID"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return summonerRankedInfoDTO;
    }

    public SummonerUniversityDTO returnUniversityDTOFromID(Long summonerID){
        // Database Setup
        try {
            input = new FileInputStream("/Project/org/ollynural/project/resources/config.properties");
            prop.load(input);
            String URL = prop.getProperty("URL");
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String CLASS = prop.getProperty("CLASS");
            String TABLE_UNIVERSITY_INFO = prop.getProperty("TABLE_UNIVERSITY_INFO");
            Class.forName("com.mysql.jdbc.Driver");

            PreparedStatement stmt;
            String query = "SELECT * FROM " + TABLE_UNIVERSITY_INFO + " WHERE summonerID = ? ";

            Connection conn = DriverManager.getConnection(URL, USER, PASS);

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
