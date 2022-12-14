package com.dosi.covid.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CovidState {
    private Date date;
    private String Pays;
    private int Infections;
    private int Deces;
    private int Guerisons;
    private float TauxDeces;
    private float TauxGuerison;
    private float TauxInfection;
}
