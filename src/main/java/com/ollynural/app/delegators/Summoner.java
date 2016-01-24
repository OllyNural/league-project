package com.ollynural.app.delegators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ollynural.app.dto.total.SingleSummonerPlayerDTO;
import com.ollynural.app.main.dao.BasicDAO;

import org.apache.log4j.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Spring Help
 */
@Path("/summoner")
public class Summoner {

    private ObjectMapper mapper = new ObjectMapper();

    final static Logger logger = Logger.getLogger(Summoner.class);

    @GET
    @Path("/{summonerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnSingleSummoner(@PathParam("summonerName") String summonerName) throws Exception {
        logger.info("START PROCESS ---------------------------");
        BasicDAO dao = new BasicDAO();
        SingleSummonerPlayerDTO singleSummonerPlayerDTO = new SingleSummonerPlayerDTO();
        try {
            singleSummonerPlayerDTO = dao.getOrRetrieveBasicAndRankedSummonerInformation(summonerName);
            logger.info("Successfully done stuff");
            singleSummonerPlayerDTO.setUsernameExists(true);
        } catch (RuntimeException e) {
            logger.error("Username doesn't exist");
            singleSummonerPlayerDTO.setUsernameExists(false);
        }
        logger.info("END PROCESS -----------------------------");
        String json = mapper.writeValueAsString(singleSummonerPlayerDTO);
        return Response.status(200).entity(json).build();
    }

}
