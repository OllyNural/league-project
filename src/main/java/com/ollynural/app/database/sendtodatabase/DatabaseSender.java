package com.ollynural.app.database.sendtodatabase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.dto.rankedDTO.leagueentrydto.SummonerRankedInfoDTOEntry;
import com.ollynural.app.dto.summonerBasicDTO.SummonerBasicDTO;
import com.ollynural.app.dto.rankedDTO.SummonerRankedInfoDTO;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Admin on 14/11/2015.
 * Class to send requests to the Database to insert/update data
 */
public class DatabaseSender {

    //Properties here until i get properties file working properly
    private String TABLE_BASIC_INFO = "league_database_schema.basic_summoner_info";
    private String TABLE_RANKED_INFO = "league_database_schema.ranked_info";
    private String TABLE_UNIVERISTY_INFO = "league_database_schema.university_info";
    private String DATABASE_SCHEMA = "jdbc:mysql://localhost:3306/league_database_schema";
    private String USERNAME = "admin";
    private String PASSWORD = "admin_pass";

    private Properties prop = new Properties();
    private InputStream input = null;
    private ObjectMapper mapper = new ObjectMapper();

    final static Logger logger = Logger.getLogger(DatabaseSender.class);

    public void insertSummonerBasicInfo(SummonerBasicDTO summonerBasicDTO, String newSummonerName) throws IOException, SQLException {

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
            //String TABLE_BASIC_SUMMONER_INFO = prop.getProperty("TABLE_BASIC_SUMMONER_INFO");

            stmt = null;
            String query = "INSERT INTO " + TABLE_BASIC_INFO + " (summonerID, summoner_name, summoner_icon, revision_date, summoner_level)" +
                    "VALUES ( ? , ? , ? , ? , ? );";
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("Where is your MySQL Driver?!?");
                    e.printStackTrace();
                }
                Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);
                stmt = conn.prepareStatement(query);

                logger.info("Id or name is: " + summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId());

                // Set the variables from the DTO into the query
                stmt.setLong(1, summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getId());
                stmt.setString(2, summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getName());
                stmt.setLong(3, summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getProfileIconId());
                stmt.setLong(4, summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getRevisionDate());
                stmt.setInt(5, summonerBasicDTO.getNonMappedAttributes().get(newSummonerName).getSummonerLevel());

                stmt.executeUpdate();

                System.out.println("Full Summoner Information in basic_summoner_info table was stored");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Database error with connection: " + e);
            e.printStackTrace();
        }

    }

    public void insertRankedInfo(SummonerRankedInfoDTO summonerRankedInfoDTO) throws SQLException {

        // Gets the ID from the RankedDTO to put into database
        Map.Entry<String, List<SummonerRankedInfoDTOEntry>> entry = summonerRankedInfoDTO.getIntegerSummonerRankedInfoDTOEntryMap().entrySet().iterator().next();
        Integer summonerId = Integer.parseInt(entry.getKey());

        Map<String, List<SummonerRankedInfoDTOEntry>> summonerRankedInfoDTOEntry = summonerRankedInfoDTO.getIntegerSummonerRankedInfoDTOEntryMap();

        String filename = "config.properties";
        input = getClass().getClassLoader().getResourceAsStream(filename);
        if (input == null) {
            System.out.println("Sorry, unable to find " + filename);
        }

        String URL = prop.getProperty("URL");
        String USER = prop.getProperty("USER");
        String PASS = prop.getProperty("PASS");
        String CLASS = prop.getProperty("CLASS");
        //String TABLE_RANKED_INFO = prop.getProperty("TABLE_RANKED_INFO");

        // Prepares the SQL Statement
        PreparedStatement stmt = null;
        String query = "INSERT INTO " + TABLE_RANKED_INFO + " (summonerID, ranked_information_for_summoner)" +
                "VALUES ( ? , ? );";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL Driver?!?");
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, summonerId);
            logger.info(summonerRankedInfoDTOEntry);
            String summonerRankedInfoString = mapper.writeValueAsString(summonerRankedInfoDTOEntry);
            logger.info(summonerRankedInfoString);
            stmt.setString(2, summonerRankedInfoString);

            stmt.executeUpdate();

            logger.info("Summoner ID in Ranked Information table was stored!");
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
        //String TABLE_RANKED_INFO = prop.getProperty("TABLE_RANKED_INFO");

        PreparedStatement stmt = null;
        String query = "INSERT INTO " + TABLE_RANKED_INFO + " (summonerID)" +
                "VALUES ( ? );";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL Driver?!?");
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);
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

    public void deleteSummonerBasicInfoForGivenId(Long basicId) {
        logger.info("Deleting summonerBasicInfo from database using: " + basicId);
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
            //String TABLE_BASIC_SUMMONER_INFO = prop.getProperty("TABLE_BASIC_SUMMONER_INFO");

            stmt = null;
            String query = "DELETE FROM " + TABLE_BASIC_INFO + " WHERE summonerID = ?";
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("Where is your MySQL Driver?!?");
                    e.printStackTrace();
                }
                Connection conn = DriverManager.getConnection(DATABASE_SCHEMA, USERNAME, PASSWORD);
                stmt = conn.prepareStatement(query);

                stmt.setLong(1, basicId);

                stmt.executeUpdate();
                logger.info("Basic Info was deleted for: " + basicId);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Database error with connection: " + e);
            e.printStackTrace();
        }
    }
}
