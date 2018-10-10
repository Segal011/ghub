
package Lab2Segalovičiūtė;
import studijosKTU.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;
/*
/**
 *
 * @author Indrė
 */
public class Participant implements KTUable<Participant> {
    
    public String nameSurname;
    private String country;
    private int age;  
    private String gender; 
    private boolean came;
    
    public Participant(){}
    
    public Participant(String nameSurname, String country, int age, String gender){
        this.nameSurname = nameSurname;
        this.country = country;
        this.age = age;
        this.gender = gender;
        
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }
    
    public String getGender() {
        return gender;
    }

    public boolean isCame() {
        return came;
    }
    
    public void setCame(boolean came) {
        this.came = came;
    }

    @Override
    public String toString() {
        return String.format("%-21s %-8s %-3s %-3s %-6s", nameSurname, country, age, gender, came);
    }

    @Override
    public KTUable create(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parse(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Participant p) {
        return this.nameSurname.compareTo(p.nameSurname);
    }
   
    
    public boolean equals(Participant p) {
        return this.nameSurname.compareTo(p.nameSurname) == 0;
    }
    
    public final static Comparator byAge = new Comparator() {
       // sarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
       @Override
       public int compare(Object o1, Object o2) {
          double k1 = ((Participant) o1).getAge();
          double k2 = ((Participant) o2).getAge();
          // didėjanti tvarka, pradedant nuo mažiausios
          if(k1<k2) return -1;
          if(k1>k2) return 1;
          return 0;
       }
    };
}
