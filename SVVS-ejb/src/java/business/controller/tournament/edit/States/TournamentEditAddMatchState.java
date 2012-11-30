/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.edit.States;

import business.AController;
import business.controller.tournament.TournamentController;
import business.controller.tournament.edit.TournamentEdit;
import data.DAOs.MatchDAO;
import data.DAOs.TournamentDAO;
import data.hibernate.HibernateUtil;
import data.DTOs.ITournamentDTO;
import data.models.ITeam;
import data.models.ITournament;
import data.models.Match;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author phil
 */
class TournamentEditAddMatchState extends AController implements ITournamentEditState {

    TournamentEdit _editor;

    public TournamentEditAddMatchState(TournamentEdit editor)throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public ITournamentDTO loadTournament(int ID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AddMatch(int TournamentID, String location, Date date, String Team1Name, String Team2Name, Integer goalsTeam1, Integer goalsTeam2) throws RemoteException {
        Match match = new Match();
        ITournament itour = TournamentController.getInstance().loadTournament(TournamentID);
        match.setTournament(itour);
        match.setLocation(location);

        match.setDate(date);
        match.setGoalsTeam1(goalsTeam1);
        match.setGoalsTeam2(goalsTeam2);

        for (ITeam team : itour.getTeams()) {
            if (team.getName().equals(Team1Name)) {
                match.setTeam1(team);
            }
        }

        for (ITeam team : itour.getTeams()) {
            if (team.getName().equals(Team2Name)) {
                match.setTeam2(team);
            }
        }

        MatchDAO.getInstance().add(HibernateUtil.getCurrentSession(), match);

        itour.addMatch(match);

        TournamentDAO.getInstance().update(HibernateUtil.getCurrentSession(), itour);
    }

    @Override
    public void EditTournament(int ID, String name, String location, BigDecimal fee, boolean finished, List<String> TeamNames) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
