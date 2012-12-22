package business.webservice;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import business.controller.tournament.TournamentController;
import data.DTOs.IMatchDTO;
import data.DTOs.ITournamentDTO;
import data.DTOs.TournamentDTO;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author phil
 */

@WebService(name="SVVSService")
@Stateless
public class TournamentService {
    
    public List<String> loadTournaments() {
        List<String> tournaments = new LinkedList<String>();

        try {
            List<ITournamentDTO> tdto = TournamentController.getInstance().loadTournaments();
            String tempTour;
            for (ITournamentDTO iTDTO : tdto) {
                tempTour = iTDTO.getId() + ";" + iTDTO.getName() + ";" + iTDTO.getLocation() + ";" + iTDTO.getDate() + "\n";
                tournaments.add(tempTour);
            }

        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return tournaments;
    }

    public List<String> loadTournament(int id) {
        List<String> matches = new LinkedList<String>();

        try {
            ITournamentDTO itdto = new TournamentDTO(TournamentController.getInstance().loadTournament(id));
            String tempMatch;

            for (IMatchDTO imdto : itdto.getMatches()) {
                tempMatch = imdto.getTeam1().getName() + ";"
                        + imdto.getTeam2().getName() + ";"
                        + imdto.getGoalsTeam1() + ";"
                        + imdto.getGoalsTeam2() + "\n";
                matches.add(tempMatch);
            }

        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return matches;
    }

    public boolean isFinished(int id) {
        ITournamentDTO itdto = null;
        try {
            itdto = new TournamentDTO(TournamentController.getInstance().loadTournament(id));
            return itdto.isFinished();
        } catch (RemoteException re) {
            re.printStackTrace();
        }

        return false;

    }
}
