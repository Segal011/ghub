/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Indrė
 */
public class Įtaisas {
    private String kodas;
    private String pavadinimas;
    private double kaina;
    private int kiekis;
    
    public Įtaisas(String kodas, String pavadinimas, double kaina) {
        this.kodas = kodas;
        this.pavadinimas = pavadinimas;
        this.kaina = kaina;
        this.kiekis = 0;
    }

    Įtaisas() {
        this.kodas = "";
        this.pavadinimas = "";
        this.kaina = -1;
        this.kiekis = 0;
    }
    
    public String getKodas() {
        return kodas;
    }
    
    public String getPavadinimas() {
        return pavadinimas;
    }
    
    public double getKaina() {
        return kaina;
    }
    
    public int getKiekis(){
        return kiekis;
    }
    
    public void didintiKiekį(int kiekis){
        this.kiekis+=kiekis;
    }
    
    @Override
    public String toString() { 
        return String.format("Kodas: " + kodas + " Pavadinimas: "+  pavadinimas + " Kiekis: " + kiekis + " Suma: "+ kaina * kiekis);
       //         kodas, pavadinimas, kiekis, kaina * kiekis);
    }
}
