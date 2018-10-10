
package Lab2Segalovičiūtė;
import studijosKTU.*;
/**
 *
 * @author Indrė
 */
public class Dialogue {
     static ListKTU<Participant> participantsCandidates = new ListKTU<>();
    static ListKTU<Participant> participants = new ListKTU<>();

    void DialogueWithCustomer() {
        int No;  
        String dialogMeniu = "Pasirinkimas: \n"
            + "1-set participants and candidates of participants lists;\n"
            + "2-check if are not extra pa1"
            + "rticipants in confirmed list;\n"
            + "3-test a participant change;\n"
            + "4-print pasticipants list;\n"
            + "5-print candidates of participants list;\n"
            + "0-baigti skaičiavimus > ";
        while ((No = Ks.giveInt(dialogMeniu, 0, 5)) != 0) {
        switch (No) {
                  case 1:
                     participantsCandidates = Project.makingAList1();
                     participants = Project.makingAList2();
                     break;
                  case 2:
                     Project.testingParticipants(participants, participantsCandidates);
                     break;
                             
                  case 3:
                     Project.changeAParticipant(participants, new Participant("Adoflas Adolfėnas", "Austrija", 20, "v"), new Participant("Paulius Paulenis", "Vengrija", 20, "v"));
                     break;
                  case 4:
                     Project.print(participants, "Participants' list");
                     break;
                  case 5:
                     Project.print(participantsCandidates, "Participants - Candidates' list");
                     break;
               }
            System.out.println();
        }         
    }
   
    public static void main(String[] args) {
        new Dialogue().DialogueWithCustomer();
    }
}
