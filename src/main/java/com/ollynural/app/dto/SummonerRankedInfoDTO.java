package com.ollynural.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ollynural.app.dto.leagueentrydto.SummonerRankedInfoDTOEntry;

import java.util.Date;

/**
 * DTO to represent Database information for ranked_info table
 */
public class SummonerRankedInfoDTO {

    @JsonProperty("summonerID")
    private Long summonerId;

    @JsonProperty("ranked_information_for_summoner")
    private SummonerRankedInfoDTOEntry rankedInformationForSummoner;

    @JsonProperty("update_time")
    private Date updateTime;

    public Long getID() {
        return summonerId;
    }

    public void setID(Long summonerID) {
        this.summonerId = summonerID;
    }

    public SummonerRankedInfoDTOEntry getRankedInformationForSummoner() {
        return rankedInformationForSummoner;
    }

    public void setRankedInformationForSummoner(SummonerRankedInfoDTOEntry ranked_information_for_summoner) {
        this.rankedInformationForSummoner = ranked_information_for_summoner;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date update_time) {
        this.updateTime = update_time;
    }

}
