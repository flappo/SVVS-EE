/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.edit.States;

import business.AController;
import business.controller.tournament.TournamentController;
import business.controller.tournament.edit.TournamentEdit;
import data.DTOs.TournamentDTO;
import data.DTOs.ITournamentDTO;
import data.models.ITournament;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author phil
 */
public class TournamentEditLoadState extends AController implements ITournamentEditState {

    TournamentEdit _editor;

    public TournamentEditLoadState(TournamentEdit editor)throws RemoteException {
        super();
        _editor = editor;
    }

    /**
     * Return a requested tournament if found
     *
     * @param ID
     * @return Tournament or null
     */
    @Override
    public ITournamentDTO loadTournament(int ID) throws RemoteException{
        /*LinkedList<ITournament> tournaments = TournamentController.getInstance().loadTournaments();
         for (ITournament iT : tournaments) {
         if (iT.getTournamentID() == ID) {
         _editor.setCurState(new TournamentEditAddMatchState(_editor));
         return iT;
         }
         }
         return null;*/
        ITournament itour = TournamentController.getInstance().loadTournament(ID);
        if ((itour) != null) {
            _editor.setCurState(new TournamentEditState(_editor));
        }
        return new TournamentDTO(itour);
    }

    @Override
    public void AddMatch(int TournamentID, String location, Date date, String Team1Name, String Team2Name, Integer goalsTeam1, Integer goalsTeam2)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void EditTournament(int ID, String name, String location, BigDecimal fee, boolean finished, List<String> TeamNames) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
