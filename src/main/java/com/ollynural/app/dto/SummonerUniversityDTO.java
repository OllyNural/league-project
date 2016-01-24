package com.ollynural.app.dto;

/**
 * Created by Admin on 22/11/2015.
 */
public class SummonerUniversityDTO {

    @Override
    public String toString() {
        return "SummonerUniversityDTO{" +
                "summonerID=" + summonerID +
                ", summonerName='" + summonerName + '\'' +
                ", universityName='" + universityName + '\'' +
                '}';
    }

    private Long summonerID;
    private String summonerName;
    private String universityName;

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Long getID() {
        return summonerID;
    }

    public void setID(Long summonerID) {
        this.summonerID = summonerID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
