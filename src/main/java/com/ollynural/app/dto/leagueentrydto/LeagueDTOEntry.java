package com.ollynural.app.dto.leagueentrydto;

/**
 * Created by Admin on 15/11/2015.
 */
public class LeagueDTOEntry {

    private String divison;
    private boolean isFreshBlood;
    private boolean isHotStreak;
    private boolean isInactive;
    private boolean isVeteran;
    private int leaguePoints;
    private int losses;
    private MiniSeriesDTOEntry[] miniSeriesDTOEntries;
    private String playerOrTeamId;
    private String playerOrTeamName;
    private int wins;

    public String getDivison() {
        return divison;
    }

    public void setDivison(String divison) {
        this.divison = divison;
    }

    public boolean isFreshBlood() {
        return isFreshBlood;
    }

    public void setIsFreshBlood(boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    public boolean isHotStreak() {
        return isHotStreak;
    }

    public void setIsHotStreak(boolean isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    public boolean isInactive() {
        return isInactive;
    }

    public void setIsInactive(boolean isInactive) {
        this.isInactive = isInactive;
    }

    public boolean isVeteran() {
        return isVeteran;
    }

    public void setIsVeteran(boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public MiniSeriesDTOEntry[] getMiniSeriesDTOEntries() {
        return miniSeriesDTOEntries;
    }

    public void setMiniSeriesDTOEntries(MiniSeriesDTOEntry[] miniSeriesDTOEntries) {
        this.miniSeriesDTOEntries = miniSeriesDTOEntries;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

}
