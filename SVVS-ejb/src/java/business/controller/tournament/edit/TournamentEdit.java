/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.edit;

import business.AController;
import business.controller.tournament.TournamentController;
import business.controller.tournament.edit.States.ITournamentEditState;
import business.controller.tournament.edit.States.TournamentEditLoadState;
import data.DAOs.MatchDAO;
import data.DAOs.TeamDAO;
import data.DAOs.TournamentDAO;
import data.DTOs.MatchDTO;
import data.DTOs.TournamentDTO;
import data.hibernate.HibernateUtil;
import data.DTOs.IMatchDTO;
import data.DTOs.ITeamDTO;
import data.DTOs.ITournamentDTO;
import data.models.IMatch;
import data.models.ITeam;
import data.models.ITournament;
import data.models.Match;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * First loadtourment second addmatches the others do not need an order
 *
 * @author phil
 */
public class TournamentEdit extends AController implements ITournamentEdit {

    ITournamentEditState _curState;

    public ITournamentEditState getCurState() {
        return _curState;
    }

    public void setCurState(ITournamentEditState _curState)throws RemoteException{
        this._curState = _curState;
    }

    public TournamentEdit() throws RemoteException {
        super();
        setCurState(new TournamentEditLoadState(this));
    }

    @Override
    public LinkedList<ITournamentDTO> loadTournaments() throws RemoteException {
        return TournamentController.getInstance().loadTournaments();
    }

    @Override
    public LinkedList<ITeamDTO> loadTeams(String sport) throws RemoteException {
        return TournamentController.getInstance().loadTeams(sport);
    }

    @Override
    public ITournamentDTO loadTournament(int ID) throws RemoteException {
        return _curState.loadTournament(ID);
    }

    @Override
    public IMatchDTO AddMatch(int TournamentID, String location, Date date, String Team1Name,
            String Team2Name, Integer goalsTeam1, Integer goalsTeam2) throws RemoteException {

        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
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
        
        IMatchDTO createdMatch = new MatchDTO(match);
        tx.commit();
        
        return createdMatch;

        //_curState.AddMatch(TournamentID, location, date, Team1Name, Team2Name, goalsTeam1, goalsTeam2);
    }

    @Override
    public ITournamentDTO EditTournament(int ID, String name, String location, Date date, BigDecimal fee, boolean finished, List<String> TeamNames) throws RemoteException {
        //_curState.EditTournament(ID, name, location, fee, TeamNames);

        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();

        ITournament tournament = TournamentController.getInstance().loadTournament(ID);
        tournament.setDate(date);
        tournament.setName(name);
        tournament.setLocation(location);
        tournament.setFee(fee);
        tournament.setFinished(finished);

        //List<ITeam> teams = tournament.getTeams();
        //für jedes  team in der stringliste werden alle teams durchgegangen ob der name darin vorhanden ist
        /*for (String team : TeamNames) {
         for (ITeam it : TeamDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
         if (it.getName().equals(team)) {
         teams.add(it);
         }
         }
         } */

        //tournament.setTeams(teams);

        TournamentDAO.getInstance().update(HibernateUtil.getCurrentSession(), tournament);

        TournamentDTO editedTournament = new TournamentDTO(tournament);
        tx.commit();
       
        return editedTournament;

        //_editor.setCurState(new TournamentEditAddMatchState(_editor));
    }

    @Override
    public IMatchDTO EditMatch(int MatchID, String location, Date date, String Team1Name, String Team2Name, Integer goalsTeam1, Integer goalsTeam2) throws RemoteException {
        List<IMatch> matches = MatchDAO.getInstance().getAll(HibernateUtil.getCurrentSession());
        
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
        IMatch match = null;
        for (IMatch im : matches) {
            if (im.getMatchID() == MatchID) {
                match = im;
            }
        }

        ITournament itour = match.getTournament();
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

        MatchDAO.getInstance().update(s, match);
        
        MatchDTO editedMatch = new MatchDTO(match);
        tx.commit();
        
        return editedMatch;
    }
}
