/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



//Detalės. Internetinėje parduotuvėje pirkėjai užsisakinėja robotų gamybai reikalingus
//įtaisus. Suraskite populiariausią įtaisą, kiek tokių įtaisų parduota ir už kokią sumą. Sudarykite tik
//vienos rūšies įtaisus pirkusių pirkėjų sąrašą, nupirktų įtaisų skaičių ir už juos sumokėtų pinigų sumą.
//Duomenys:
// tekstiniame faile U24a.txt yra informacija apie parduotuvėje parduodamus įtaisus: įtaiso
//kodas, įtaiso pavadinimas, įtaiso kaina;
// tekstiniame faile U24b.txt yra informacija apie pirkėjus: pirkėjo pavardė, vardas, pirkto įtaiso
//kodas, pirktų įtaisų kiekis.
//Į kitą rinkinį atrinkite įtaisus, kurių parduota ne mažiau kaip n vienetų ir kurių vieneto kaina ne didesnė
//kaip k litų (n ir k įvedami klaviatūra).


package javaapplication1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parduotuvė {
    static List<Įtaisas> įtaisai = new ArrayList<Įtaisas>();
    static List<Pirkėjas> pirkėjai = new ArrayList<>();
    static List<Pirkėjas> atrinktiPirkėjai = new ArrayList<>();
    static List<Įtaisas> atrinktiĮtaisai = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        įtaisai();
        pirkėjai();
        atrinktiPirkėjai();
        populiariausias();
        atrinktiĮtaisai();
        System.out.println("Programa baigia darbą");
    }
    
    //Įrašomi įtaisų duomenys
    public static void įtaisai(){
        įtaisai.add(new Įtaisas("1", "Pirmas", 1.99));
        įtaisai.add(new Įtaisas("2", "Antras", 2.99));
        įtaisai.add(new Įtaisas("3", "Trečias", 3.99));
        įtaisai.add(new Įtaisas("4", "Ketvirtas", 4.99));
    }
    
    //Įrašomi prikėjų duomenys
    public static void pirkėjai(){
        pirkėjai.add(new Pirkėjas("Petras", "1", 2));
        pirkėjai.add(new Pirkėjas("Petras", "4", 3));
        pirkėjai.add(new Pirkėjas("Saulius", "3", 7));
        pirkėjai.add(new Pirkėjas("Kazys", "4", 5));
        pirkėjai.add(new Pirkėjas("Saulius", "3", 20));
        pirkėjai.add(new Pirkėjas("Andrius", "2", 1));
            
        //pirkėjams priskiriams prekės, bei įtaisams - kiekiai
        pirkėjai.forEach((Pirkėjas pi) -> {
            for(Įtaisas įt: įtaisai)
                if(pi.getKodas().compareTo(įt.getKodas()) == 0){
                    pi.papildytiSumą(įt.getKaina(), pi.getKiekis()); 
                    įt.didintiKiekį(pi.getKiekis()); }
        });
    }
    
    //Atrenkami pirkėjai, kurie pirko tik 1 įtaisą
    public static void atrinktiPirkėjai(){
        //daroma pirkėjų kopija
        pirkėjai.forEach((pi) -> {
            atrinktiPirkėjai.add(pi);
        });
         
        // iš nukopijuoto sąrašo pašalinami pirkėjai, kurei kartojasi
        for(Pirkėjas p : pirkėjai){
            for(Pirkėjas pi : atrinktiPirkėjai)
                if(p.GetVardas().compareTo(pi.GetVardas()) == 0 && !pi.equals(p))
                {
                    atrinktiPirkėjai.removeIf(pir -> pir.GetVardas().compareTo(p.GetVardas()) == 0 );
                    break;
                }
        }
        
        //spausdinamas atrinktų pirkėjų sąrašas
        System.out.println("Atrinktų pirkėjų, kurie pirko tik po vieną prekę, sąrašas: ");
        for(Pirkėjas pir: atrinktiPirkėjai){
            System.out.println(pir.toString());
        }
        System.out.println();
    }
    
    //ieškomas populiariausias įtaisas
    public static void populiariausias(){
        Įtaisas pop = new Įtaisas();
        for(Įtaisas įt: įtaisai)
            if(pop.getKiekis() < įt.getKiekis())
                pop = įt;
        System.out.println("Populiariausias įtaisas: " + pop.toString());
        System.out.println();
    }
    
    //atrenkami įtaisai, kurie atitinka įvestus parametrus
    public static void atrinktiĮtaisai(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Įveskite minimalų kiekį");
        int n = sc.nextInt();
        System.out.println("Įveskite maksimalią kainą");
        double k = sc.nextDouble();
        System.out.println("Įtaisų sąrašas, kurie atitinka įvestus duomenis: ");
         for(Įtaisas įt : įtaisai)
             if(įt.getKiekis() >= n && įt.getKaina() <= k){
            boolean add = atrinktiĮtaisai.add(įt);
                 System.out.println(įt.toString());}
    }
}
