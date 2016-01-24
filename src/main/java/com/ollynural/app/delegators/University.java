package com.ollynural.app.delegators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.dto.total.SingleSummonerPlayerDTO;
import com.ollynural.app.dto.total.UniversitySummonerDTO;
import com.ollynural.app.main.dao.BasicDAO;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


/**
 * Spring Help
 */
@Path("/university")
public class University {

    private ObjectMapper mapper = new ObjectMapper();
    BasicDAO basicDAO = new BasicDAO();

    final static Logger logger = Logger.getLogger(University.class);

    @GET
    @Path("/{universitycode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnSingleSummoner(
            @PathParam("universitycode") String universityCode) throws Exception {
        if(!universityCode.equals("") || universityCode != null) {
            UniversitySummonerDTO universityArrayDTO = basicDAO.getUniversityRankingsByUniversityCode(universityCode);
            String json = mapper.writeValueAsString(universityArrayDTO);
            return Response.status(200).entity(json).build();
        } else {
            logger.info("No code provided");
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/{summonerName}/{universityCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitSummonerWithUniversityToDatabase(
            @PathParam("summonerName") String summonerName,
            @PathParam("universityCode") String universityCode) {
        try {
            logger.info("Adding [" + summonerName + "] to the university database");
            basicDAO.addUniversityRankingWithSummonerNameAndUniversityCode(summonerName, universityCode);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(500).entity("RIOT URL Failure").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity("SQL Failure").build();
        }
        return Response.status(200).entity("not-stuff").build();
    }

}
