package com.ollynural.app.main.JsonHTTPGetters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.converters.validation.ValidationOfUsername;
import com.ollynural.app.dto.SummonerBasicDTO;
import com.ollynural.app.dto.SummonerRankedInfoDTO;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

public class RiotURLSender {

	private final ValidationOfUsername validationOfUsername = new ValidationOfUsername();
    ObjectMapper mapper = new ObjectMapper();

//	static InputStream input = null;
//	static Properties prop = new Properties();

	public SummonerBasicDTO getSummonerBasicInfoByUsername(String username) throws IOException, ParseException {
		
//		input = new FileInputStream("/Project/src/org/ollynural/project/resources/config.properties");
//		prop.load(input);
//		String APIKey = prop.getProperty("APIKey");

		String newUsername = validationOfUsername.validateUsername(username);
		
		String APIKey = "896349ec-4ce2-49ed-be1a-b480bf0c7d49";
		JSONObject json = null;
		
		try {
			URL url = new URL("https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/" + newUsername +"?api_key=" + APIKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream()), "UTF-8"));

			String output;
			StringBuilder sb = new StringBuilder();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}
			
	        json = new JSONObject(sb.toString());
	        
	        System.out.println(json);
			conn.disconnect();

		  } catch (IOException e) {
			e.printStackTrace();
		  }

		String jsonString = json != null ? json.toString() : null;
        return mapper.readValue(jsonString, SummonerBasicDTO.class);
	}

	public SummonerRankedInfoDTO getRankedInfoByID(long summonerId) {

		String APIKey = "896349ec-4ce2-49ed-be1a-b480bf0c7d49";
		JSONObject json = null;

		try{
			URL url = new URL("https://euw.api.pvp.net/api/lol/euw/v1.4/v2.5/league/by-summoner/" + summonerId + "/entry?api_key=" + APIKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if(conn.getResponseCode() != 200){
				throw new RuntimeException("Failed : HTTP Code : " +
					conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream()), "UTF-8"));

			String output;
			StringBuilder sb = new StringBuilder();
			System.out.print("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}

			json = new JSONObject(sb.toString());
			System.out.println(json);
			conn.disconnect();
        } catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }

        if(json == null){
			throw new RuntimeException("Json is null");
		}

        String jsonString = json.toString();
        SummonerRankedInfoDTO summonerRankedInfoDTO = null;
        try {
            summonerRankedInfoDTO = mapper.readValue(jsonString, SummonerRankedInfoDTO.class);
        } catch(IOException e){
            e.printStackTrace();
        }
        return summonerRankedInfoDTO ;

	}
}