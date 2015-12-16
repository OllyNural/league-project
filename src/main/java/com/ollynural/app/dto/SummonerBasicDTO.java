package com.ollynural.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;

/**
 * DTO to represent basic summoner call from RIOT's API
 */
public class SummonerBasicDTO {
    @JsonProperty("ID")
    private long Id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("summonerIcon")
    private int summonerIcon;
    @JsonProperty("revisionDate")
    private Long revisionDate;
    @JsonProperty("summonerLevel")
    private int summonerLevel;
    @JsonProperty("update_time")
    private Time cacheTime;

    public Time getTime(){
        return cacheTime;
    }

    public void setId(long ID) {
        this.Id = ID;
    }

    public Long getId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSummonerIcon(int summonerIcon) {
        this.summonerIcon = summonerIcon;
    }

    public int getSummonerIcon() {return summonerIcon;}

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public long getRevisionDate() {return revisionDate;}

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public int getSummonerLevel() {return summonerLevel;}

    public String toString(){
        return "" + Id + name + summonerIcon + revisionDate + summonerLevel;
    }

}
