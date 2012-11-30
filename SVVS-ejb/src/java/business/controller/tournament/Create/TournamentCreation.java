/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.Create;

import business.AController;
import business.controller.tournament.Create.States.ITournamentCreateState;
import business.controller.tournament.Create.States.TournamentCreateLoadSportState;
import business.controller.tournament.TournamentController;
import data.DAOs.LeagueDAO;
import data.DAOs.SportDAO;
import data.DAOs.TeamDAO;
import data.DAOs.TournamentDAO;
import data.DAOs.TournamentInviteDAO;
import data.DAOs.TrainingTeamDAO;
import data.DTOs.TeamDTO;
import data.DTOs.TournamentDTO;
import data.hibernate.HibernateUtil;
import data.DTOs.IDepartmentDTO;
import data.DTOs.ILeagueDTO;
import data.DTOs.ISportDTO;
import data.DTOs.ITeamDTO;
import data.DTOs.ITournamentDTO;
import data.models.ILeague;
import data.models.ISport;
import data.models.ITeam;
import data.models.ITournamentInvite;
import data.models.ITrainingTeam;
import data.models.Sport;
import data.models.Tournament;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

/**
 * Context für den Use Case neues Tournament, GUI sollte interface von dem
 * kriegen
 *
 * First LoadSport Second LoadTeams Third CreateTournament
 *
 * @author phil
 */
public class TournamentCreation extends AController implements ITournamentCreation {

    ITournamentCreateState _curState;

    public ITournamentCreateState getCurState() {
        return _curState;
    }

    public void setCurState(ITournamentCreateState curState) throws RemoteException {
        this._curState = curState;
    }

    public TournamentCreation() throws RemoteException {
        super();
        setCurState(new TournamentCreateLoadSportState(this));
    }

    @Override
    public LinkedList<ISportDTO> loadSport(List<IDepartmentDTO> department) throws RemoteException {
        //return _curState.loadSport();
        return TournamentController.getInstance().loadSport(department);
    }

    @Override
    public LinkedList<ITeamDTO> loadTeams(String sport) throws RemoteException {
        //return _curState.loadTeams(sport);
        return TournamentController.getInstance().loadTeams(sport);
    }
    
    public LinkedList<ILeagueDTO> loadLeagues() throws RemoteException {
        return new LinkedList<ILeagueDTO>(LeagueDAO.getInstance().getAllDTO(HibernateUtil.getCurrentSession()));
    }

    @Override
    public LinkedList<ITeamDTO> loadTeamsWithLeague(String leaguename) throws RemoteException {
        LinkedList<ITeamDTO> teams = new LinkedList<ITeamDTO>();
        for (ITeam it : TeamDAO.getInstance().getByLeague(HibernateUtil.getCurrentSession(), getLeagueWithName(leaguename))) {
            teams.add(new TeamDTO(it));
        }
        return teams;
    }

    @Override
    public LinkedList<ITeamDTO> loadTeamsBySportAndLeague(String sportname, String leaguename) throws RemoteException {
        ISport sport = SportDAO.getInstance().getByName(HibernateUtil.getCurrentSession(), sportname);
        LinkedList<ITeamDTO> teams = new LinkedList<ITeamDTO>();
        for (ITeam it : TeamDAO.getInstance().getBySportAndLeague(HibernateUtil.getCurrentSession(), sport, getLeagueWithName(leaguename))) {
            teams.add(new TeamDTO(it));
        }
        return teams;
    }

    @Override
    public ILeague getLeagueWithName(String leaguename) throws RemoteException {
        List<ILeague> league = LeagueDAO.getInstance().getAll(HibernateUtil.getCurrentSession());
        for (ILeague iL : league) {
            if (iL.getName().equals(leaguename)) {
                return iL;
            }
        }
        return null;
    }

    @Override
    public ITournamentDTO CreateTournament(String name, String location, String date, BigDecimal fee, String sportname, List<String> TeamNames) throws RemoteException {
        //_curState.CreateTournament(name, location, fee, sportname, TeamNames);

        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();

        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setDate(Date.valueOf(date));
        tournament.setLocation(location);
        tournament.setFee(fee);
        ISport sport = SportDAO.getInstance().getByName(s, sportname);
        tournament.setSport(sport);
        
        LinkedList<ITeam> teams = new LinkedList<ITeam>();
        //für jedes  team in der stringliste werden alle teams durchgegangen ob der name darin vorhanden ist
        for (String team : TeamNames) {
            for (ITeam it : TeamDAO.getInstance().getBySport(s, sport)) {
                if (it.getName().equals(team)) {
                    teams.add(it);
                }
            }
        }
        tournament.setTeams(teams);

        ITournamentDTO tournamentDTO = new TournamentDTO(tournament);
        TournamentDAO.getInstance().add(s, tournament);

        for(ITeam team:tournament.getTeams()){
            ITrainingTeam tteam = null;
            try{
                tteam = TrainingTeamDAO.getInstance().getById(s, team.getTeamID());
            }catch(Exception e){
                e.printStackTrace();
            }
            
            if(tteam != null){
                ITournamentInvite ti = TournamentInviteDAO.getInstance().create();
                ti.setTournament(tournament);
                ti.setTeam(tteam);
                TournamentInviteDAO.getInstance().add(s, ti);
            }
            
        }
        
        tx.commit();

        return tournamentDTO;
    }
}
