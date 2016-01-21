package com.ollynural.app.dto.total;

import java.util.ArrayList;

/**
 * Created by Admin on 21/01/2016.
 */
public class UniversitySummonerDTO {

    @Override
    public String toString() {
        return "UniversitySummonerDTO{" +
                "singleSummonerPlayerDTOs=" + singleSummonerPlayerDTOs +
                '}';
    }

    private ArrayList<SingleSummonerPlayerDTO> singleSummonerPlayerDTOs;

    public ArrayList<SingleSummonerPlayerDTO> getSingleSummonerPlayerDTOs() {
        return singleSummonerPlayerDTOs;
    }

    public void setSingleSummonerPlayerDTOs(ArrayList<SingleSummonerPlayerDTO> singleSummonerPlayerDTOs) {
        this.singleSummonerPlayerDTOs = singleSummonerPlayerDTOs;
    }

}
