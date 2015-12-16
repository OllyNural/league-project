package com.ollynural.app.dto;

/**
 * Created by Admin on 05/12/2015.
 */
public class SingleSummonerPlayerDTO {

    private SummonerBasicDTO summonerBasicDTO;
    private SummonerRankedInfoDTO summonerRankedInfoDTO;
    private SummonerUniversityDTO summonerUniversityDTO;

    public SummonerBasicDTO getSummonerBasicDTO() {
        return summonerBasicDTO;
    }

    public void setSummonerBasicDTO(SummonerBasicDTO summonerBasicDTO) {
        this.summonerBasicDTO = summonerBasicDTO;
    }

    public SummonerRankedInfoDTO getSummonerRankedInfoDTO() {
        return summonerRankedInfoDTO;
    }

    public void setSummonerRankedInfoDTO(SummonerRankedInfoDTO summonerRankedInfoDTO) {
        this.summonerRankedInfoDTO = summonerRankedInfoDTO;
    }

    public SummonerUniversityDTO getSummonerUniversityDTO() {
        return summonerUniversityDTO;
    }

    public void setSummonerUniversityDTO(SummonerUniversityDTO summonerUniversityDTO) {
        this.summonerUniversityDTO = summonerUniversityDTO;
    }


}
