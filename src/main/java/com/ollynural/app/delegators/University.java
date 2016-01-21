package com.ollynural.app.delegators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.dto.SummonerUniversityDTO;
import com.ollynural.app.dto.total.SingleSummonerPlayerDTO;
import com.ollynural.app.main.dao.BasicDAO;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Spring Help
 */
@Path("/university")
public class University {

    private ObjectMapper mapper = new ObjectMapper();

    final static Logger logger = Logger.getLogger(University.class);

    @GET
    @Path("/{universitycode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnSingleSummoner(@PathParam("universitycode") String universityCode) throws Exception {
        BasicDAO basicDAO = new BasicDAO();
        SummonerUniversityDTO summonerUniversityDTO = basicDAO.getUniversityRankingsByUniversityCode(universityCode);
        String json = mapper.writeValueAsString(summonerUniversityDTO);
        return Response.status(200).entity(json).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void submitSummonerWithUniversityToDatabase() {

    }

}
