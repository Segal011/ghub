
package Lab2Segalovičiūtė;
import studijosKTU.*;
/**
 *
 * @author Indrė
 */
public class Project {
    
    static ListKTU<Participant> makingAList1()
    {
        ListKTU<Participant> participantsCandidates = new ListKTU<>();
        participantsCandidates.add(participantsCandidates.size(), new Participant("Paulius Paulenis", "Vengrija", 20, "v"));
        participantsCandidates.add(participantsCandidates.size(), new Participant("Saulius Saulėis", "Ispanija", 23, "v"));
        participantsCandidates.add(participantsCandidates.size(), new Participant("Simona Simonytė", "Lenkija", 19, "m"));
        participantsCandidates.add(participantsCandidates.size(), new Participant("Vytas Vytenis", "Lenkija", 26, "v"));
        participantsCandidates.add(participantsCandidates.size(), new Participant("Ieva Ievute", "Lenkija", 22, "m"));
        participantsCandidates.add(participantsCandidates.size(), new Participant("Karolina Karolinytė", "Ispanija", 21, "m"));
        return participantsCandidates;
    }
    
    static ListKTU<Participant> makingAList2()
    {
        ListKTU<Participant> participants = new ListKTU<>();
        participants.addFirst(new Participant("Paulius Paulenis", "Vengrija", 20, "v"));
        participants.addFirst(new Participant("Saulius Saulėis", "Ispanija", 23, "v"));
        participants.addFirst(new Participant("Vytas Vytenis", "Lenkija", 26, "v"));
        participants.addFirst(new Participant("Ieva Ievute", "Lenkija", 22, "m"));
        participants.addFirst(new Participant("Karolina Karolinytė", "Ispanija", 21, "m"));
        return participants;
    }
    
    static void testingParticipants(ListKTU<Participant> participants, ListKTU<Participant> participantsCandidates){
        System.out.println();
        if(participantsCandidates.containsAll(participants))
            System.out.println("Pašalinių žmonių nerasta");
        else{
            for(Participant part: participants){
                if(!participantsCandidates.contains(part))
                {
                    int index = participantsCandidates.lastIndexOf(part);
                    if(index != -1){
                    participants.remove(index);
                    } 
                }
            }
            System.out.println("Pašaliniai žmonės rasti ir pašalinti iš sąrašo");
        }
        setArrivedParticipants(participants, participantsCandidates);
        System.out.println();
    }
    
    static void setArrivedParticipants(ListKTU<Participant> participants, ListKTU<Participant> participantsCandidates){
        for(Participant part: participants){
            part.setCame(true);
        }
        for(Participant part: participantsCandidates){
            if(participants.contains(part)){
                part.setCame(true);
            }
        }
    }
    
    static void changeAParticipant(ListKTU<Participant> participants, Participant partToChange, Participant part){       
        int index = -1;
        for(Participant p: participants){
            index++;
            if(part.equals(p)){
                break;
            }
        }
        if(index == -1){
            System.out.println();
            System.out.println("Toks dalyvis neegziztuoja, kurį norima pakeisti");
            System.out.println();
            return;
        }
        partToChange.setCame(true);
        part.setCame(false);
        participants.set(index, partToChange);
        
        System.out.println();
        System.out.println(partToChange.nameSurname + " was changed to " + part.nameSurname);
        System.out.println();
    }
    
    public static void print(ListKTU<Participant> participants, String title){
        System.out.println();
        System.out.println(title);
        for(Participant part: participants){
            System.out.println(part.toString());
        }
        System.out.println();
    }
   
    public static void main(String... args) {
       ListKTU<Participant> participantsCandidates = Project.makingAList1();
                    ListKTU<Participant> participants = Project.makingAList2();
                     Project.testingParticipants(participants, participantsCandidates);
                     Project.changeAParticipant(participants, new Participant("Adoflas Adolfėnas", "Austrija", 20, "v"), new Participant("Paulius Paulenis", "Vengrija", 20, "v"));
                     Project.print(participants, "Participants' list");
                     Project.print(participantsCandidates, "Participants - Candidates' list");
    }
}
