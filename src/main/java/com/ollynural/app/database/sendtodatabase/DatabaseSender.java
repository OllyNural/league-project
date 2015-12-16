package com.ollynural.app.database.sendtodatabase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.dto.SummonerBasicDTO;
import com.ollynural.app.dto.SummonerRankedInfoDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Admin on 14/11/2015.
 * Class to send requests to the Database to insert/update data
 */
public class DatabaseSender {

    private Properties prop = new Properties();
    private InputStream input = null;
    private ObjectMapper mapper = new ObjectMapper();


    public void insertSummonerBasicInfo(SummonerBasicDTO summonerBasicDTO) throws IOException, SQLException {

        // Gets properties from properties file
        input = new FileInputStream("/Project/org/ollynural/project/resources/config.properties");
        prop.load(input);
        String URL = prop.getProperty("URL");
        String USER = prop.getProperty("USER");
        String PASS = prop.getProperty("PASS");
        String CLASS = prop.getProperty("CLASS");
        String TABLE_BASIC_SUMMONER_INFO = prop.getProperty("TABLE_BASIC_SUMMONER_INFO");

        PreparedStatement stmt = null;
        String query = "INSERT INTO " + TABLE_BASIC_SUMMONER_INFO + " (summonerID, summoner_name, university_name, summoner_icon, revision_date, summoner_level)" +
                "VALUES ( ? , ? , ? , ? , ? );";
        try {
            try {
                Class.forName(CLASS);
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL Driver?!?");
                e.printStackTrace();
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.prepareStatement(query);

            // Set the variables from the DTO into the query
            stmt.setLong(1, summonerBasicDTO.getId());
            stmt.setString(2, summonerBasicDTO.getName());
            stmt.setLong(4, summonerBasicDTO.getSummonerIcon());
            stmt.setLong(5, summonerBasicDTO.getRevisionDate());
            stmt.setInt(6, summonerBasicDTO.getSummonerLevel());

            stmt.executeUpdate();

            System.out.println("Full Summoner Information in basic_summoner_info table was stored");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void insertRankedInfo(SummonerRankedInfoDTO summonerRankedInfoDTO) throws SQLException {
        try {
            // Gets the properties from the properties file
            input = new FileInputStream("/Project/org/ollynural/project/resources/config.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String URL = prop.getProperty("URL");
        String USER = prop.getProperty("USER");
        String PASS = prop.getProperty("PASS");
        String CLASS = prop.getProperty("CLASS");
        String TABLE_RANKED_INFO = prop.getProperty("TABLE_RANKED_INFO");

        // Prepares the SQL Statement
        PreparedStatement stmt = null;
        String query = "INSERT INTO " + TABLE_RANKED_INFO + " (summonerID, ranked_information_for_summoner)" +
                "VALUES ( ? , ? );";

        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL Driver?!?");
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, summonerRankedInfoDTO.getID());
            String summonerRankedInfoString = mapper.writeValueAsString(summonerRankedInfoDTO);
            stmt.setString(2, summonerRankedInfoString);

            stmt.executeUpdate();

            System.out.println("Summoner ID in Ranked Information table was stored!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void insertRankedIdAlone(long summonerID) throws SQLException {
        try {
            // Gets the properties from the properties file
            input = new FileInputStream("/Project/org/ollynural/project/resources/config.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String URL = prop.getProperty("URL");
        String USER = prop.getProperty("USER");
        String PASS = prop.getProperty("PASS");
        String CLASS = prop.getProperty("CLASS");
        String TABLE_RANKED_INFO = prop.getProperty("TABLE_RANKED_INFO");

        PreparedStatement stmt = null;
        String query = "INSERT INTO " + TABLE_RANKED_INFO + " (summonerID)" +
                "VALUES ( ? );";

        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL Driver?!?");
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.prepareStatement(query);

            // Set the variables from the DTO into the query
            stmt.setLong(1, summonerID);

            stmt.executeUpdate();

            System.out.println("Summoner ID in Ranked Information table was stored!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void insertUniversityIDAndName(Long summonerID, String universityName) throws IOException, SQLException {
        // Gets properties from properties file
        input = new FileInputStream("/Project/org/ollynural/project/resources/config.properties");
        prop.load(input);///
        String URL = prop.getProperty("URL");
        String USER = prop.getProperty("USER");
        String PASS = prop.getProperty("PASS");
        String CLASS = prop.getProperty("CLASS");
        String TABLE_UNIVERSITY_INFO = prop.getProperty("TABLE_UNIVERSITY_INFO");

        PreparedStatement stmt = null;
        String query = "INSERT INTO " + TABLE_UNIVERSITY_INFO + " (summonerID, university_code)" +
                "VALUES ( ? , ? );";
        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL Driver?!?");
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.prepareStatement(query);

            // Set the variables from the DTO into the query
            stmt.setLong(1, summonerID);
            stmt.setString(1, universityName);

            stmt.executeUpdate();

            System.out.println("Summoner ID and University  table was stored!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
