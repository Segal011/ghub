/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Segalovičiūtė;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.Timekeeper;

/**
 *
 * @author Indrė
 */
public class Greitaveika {
    
    Participant[] participants;
    
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer>linkedList = new LinkedList<>();
    ListKTU<Participant> aSeries = new ListKTU<>();
    Random rand = new Random();  // atsitiktinių generatorius
   // int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000};
    static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};
    void generateParticipants(int kiekis){
       String[][] am = { // galimų automobilių markių ir jų modelių masyvas
          { "Paulius Paulenis", "Saulius Saulėis", "Simona Simonytė", "Vengrija"},
          {"Kazys Kazelis", "Stasė Staselė", "Ispanija"},
          { "Petras Petraitis", "Stasė Staselė", "Lenkija"},
          { "Andrius Andrelis", "Miglė Miglelė", "Sima Simaitė", "Lietuva"},
       };
       
        participants= new Participant[kiekis];
        rand.setSeed(2017);
        List<String> list = new ArrayList<>();
        list.add("m");
        list.add("v");
        String random = list.get(new Random().nextInt(list.size()));
        for(int i=0;i<kiekis;i++){
            int ma = rand.nextInt(am.length);        // markės indeksas  0..
            int mo = rand.nextInt(am[ma].length-2);// modelio indeksas 1..
            participants[i]= new Participant(am[ma][mo], am[ma][am[ma].length-1], 
                18 + rand.nextInt(12),      
                list.get(new Random().nextInt(list.size())));
        }
        Collections.shuffle(Arrays.asList(participants));
        aSeries.clear();
        for(Participant a: participants) aSeries.add(a);
    }
    
    void genIntegerData(int kiekis) {
        rand.setSeed(2000);
        for (int i = 0; i < kiekis; i++) {
            arrayList.add(rand.nextInt());
            linkedList.add(rand.nextInt());
        }
    }
    
    void paprastasTyrimas(int elementsCount){
// Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        generateParticipants(elementsCount);
        ListKTU<Participant> aSeries2 = aSeries.clone();
        ListKTU<Participant> aSeries3 = aSeries.clone();
        ListKTU<Participant> aSeries4 = aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sortSystem();
        long t3=System.nanoTime();
        aSeries2.sortSystem(Participant.byAge);
        long t4=System.nanoTime();
        aSeries3.sortBuble();
        long t5=System.nanoTime();
        aSeries4.sortBuble(Participant.byAge);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementsCount,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    
    void sisteminisTyrimas(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
           generateParticipants(kiekis);
           ListKTU<Participant> aSeries2 = aSeries.clone();
           ListKTU<Participant> aSeries3 = aSeries.clone();
           ListKTU<Participant> aSeries4 = aSeries.clone();

    //  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sortSystem();
            tk.finish("SysBeCom");
            aSeries2.sortSystem(Participant.byAge);
            tk.finish("SysSuCom");
            aSeries3.sortBuble();
            tk.finish("BurBeCom");
            aSeries4.sortBuble(Participant.byAge);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
   
    void paprastasTyrimasMath(int elementsCount){
        long t0=System.nanoTime();
        generateParticipants(elementsCount);
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
        for (Participant k : participants) {
            Math.sqrt(k.getAge());
        }
        long t3=System.nanoTime();
        for (Participant k : participants) {
            Math.sin(k.getAge());
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementsCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);
    }
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    
    void sisteminisTyrimasMath(){
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
           generateParticipants(kiekis);

            tk.start();
            for (Participant k : participants) {
                Math.sqrt(k.getAge());
            }
            tk.finish("Math.sqrt");
            for (Participant k : participants) {
                Math.sin(k.getAge());
            }
            tk.finish("Math.sin");
            tk.seriesFinish();
        }
    }
    
     void paprastasTyrimasIndex(int elementsCount){
        long t0=System.nanoTime();
        genIntegerData(elementsCount);
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
        for (int i = 0; i <  elementsCount; i++) {
           arrayList.lastIndexOf(i);
        }
        long t3=System.nanoTime();
        for (int i = 0; i <  elementsCount; i++) {
           linkedList.lastIndexOf(i);
        }
        long t4=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementsCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);
    }
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    void sisteminisTyrimasIndex(){
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
            genIntegerData(kiekis);
            tk.start();
            for (int i = 0; i <  kiekis; i++) {
                arrayList.lastIndexOf(i);
            }
            tk.finish("ArrayList");
            for (int i = 0; i <  kiekis; i++) {
                linkedList.lastIndexOf(i);
            }
            tk.finish("LinkedList");
            tk.seriesFinish();
        }
    }
    
    void tyrimoPasirinkimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateParticipants(20);
        for(Participant a: aSeries) Ks.oun(a);
        
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n: tiriamiKiekiai) 
            paprastasTyrimas(n);
        sisteminisTyrimas();
        
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Math.sqrt(x)");
        Ks.oun("4 - Math.sin(x)");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : tiriamiKiekiai) {
            paprastasTyrimasMath(n);
        }
        sisteminisTyrimasMath();
        
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Integer[] get");
        Ks.oun("4 - ArrayList<Integer> get");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : tiriamiKiekiai) {
            paprastasTyrimasIndex(n);
        }
        sisteminisTyrimasIndex();
    }
    
    
   public static void main(String[] args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new Greitaveika().tyrimoPasirinkimas();
   }    
}