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
public class Pirkėjas {
    private  String vardas;
    String kodas;
    int kiekis;
    private double suma;
    
    public Pirkėjas(String vardas, String kodas, int kiekis) {
        this.vardas = vardas;
        this.kodas = kodas;
        this.kiekis = kiekis;
        this.suma = 0;
    }
  
    
    public String GetVardas() {
        return vardas;
    }
    
    public int getKiekis() {
        return this.kiekis;
    }
    
    public String getKodas() {
        return  this.kodas;
    }
    
    public void papildytiSumą(double kaina, int kiekis){
        suma += kiekis*kaina;
    }
    
//    public boolean tasPatspirkėjas(String ieškomoVardas)
//    {
//        if(vardas.compareTo)
//    }
//    
    @Override
    public String toString() { 
        return String.format("Vardas: " + vardas +" Kiekis: " + kiekis + " Suma: " + String.format("%.2f", suma));
               // vardas, kiekiai.get(0), suma);
          //     "kodas:%-7s amžius:%3d suma:%9.2f"
    }   

    
}


