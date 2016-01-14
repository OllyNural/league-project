package com.ollynural.app.delegators;

import com.ollynural.app.dto.total.SingleSummonerPlayerDTO;
import com.ollynural.app.main.dao.BasicDAO;

import org.apache.log4j.Logger;

import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Help
 */
@Controller
public class Player {

    final static Logger logger = Logger.getLogger(Player.class);

    @RequestMapping(value="/player", method = RequestMethod.GET)
	private String handleServerResponse(@RequestParam("summonerName") String summonerName,
                                              Model model) {
        //ModelAndView modelAndView = new ModelAndView();
        try {
            logger.info("START PROCESS ---------------------------");
            BasicDAO dao = new BasicDAO();
            SingleSummonerPlayerDTO singleSummonerPlayerDTO = new SingleSummonerPlayerDTO();
            try {
                singleSummonerPlayerDTO = dao.getOrRetrieveBasicAndRankedSummonerInformation(summonerName);
                logger.info("Successfully done stuff");
                singleSummonerPlayerDTO.setUsernameExists(true);
                //modelAndView = new ModelAndView("/WEB-INF/JSP/pages/player");
                model.addAttribute("exists", singleSummonerPlayerDTO.isUsernameExists());
                model.addAttribute("summonerId", singleSummonerPlayerDTO.getSummonerRankedInfoDTO().getID());
                model.addAttribute("basicInformation", singleSummonerPlayerDTO.getSummonerBasicDTO());
                model.addAttribute("rankedInformation", singleSummonerPlayerDTO.getSummonerRankedInfoDTO());
                model.addAttribute("universityInformation", singleSummonerPlayerDTO.getSummonerUniversityDTO());
                model.addAttribute("summonerName", summonerName);
            } catch(RuntimeException e) {
                logger.error("Username doesn't exist");
                //modelAndView = new ModelAndView("error");
                singleSummonerPlayerDTO.setUsernameExists(false);
                model.addAttribute("exists", singleSummonerPlayerDTO.isUsernameExists());
                model.addAttribute("data", singleSummonerPlayerDTO);
                model.addAttribute("summonerName", summonerName);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        logger.info("END PROCESS -----------------------------");
        return "playerView";
	}

}
