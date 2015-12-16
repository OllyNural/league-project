package com.ollynural.app.dto;

/**
 * Created by Admin on 22/11/2015.
 */
public class SummonerUniversityDTO {

    private Long summonerID;
    private String universityName;

    public Long getID() {
        return summonerID;
    }

    public void getID(Long summonerID) {
        this.summonerID = summonerID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
