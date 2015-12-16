package com.ollynural.app.dto.leagueentrydto;

/**
 * DTO to represent ranked information call from RIOT's API
 */
public class SummonerRankedInfoDTOEntry {

    private LeagueDTOEntry[] leagueDTOEntries;
    private String name;
    private String paricipantId;
    private String queue;
    private String tier;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParicipantId() {
        return paricipantId;
    }

    public void setParicipantId(String paricipantId) {
        this.paricipantId = paricipantId;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public LeagueDTOEntry[] getLeagueDTOEntries() {
        return leagueDTOEntries;
    }

    public void setLeagueDTOEntries(LeagueDTOEntry[] leagueDTOEntries) {
        this.leagueDTOEntries = leagueDTOEntries;
    }


}
