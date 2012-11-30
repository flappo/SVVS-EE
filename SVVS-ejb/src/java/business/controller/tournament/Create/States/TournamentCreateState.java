/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.Create.States;

import business.AController;
import business.controller.tournament.Create.TournamentCreation;
import data.DAOs.SportDAO;
import data.DAOs.TeamDAO;
import data.DAOs.TournamentDAO;
import data.hibernate.HibernateUtil;
import data.DTOs.IDepartmentDTO;
import data.DTOs.ISportDTO;
import data.DTOs.ITeamDTO;
import data.models.ISport;
import data.models.ITeam;
import data.models.Sport;
import data.models.Tournament;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author phil
 */
class TournamentCreateState extends AController implements ITournamentCreateState {

    TournamentCreation _creator;

    public TournamentCreateState(TournamentCreation creator)throws RemoteException {
        super();
        _creator = creator;
    }

    @Override
    public LinkedList<ISportDTO> loadSport(List<IDepartmentDTO> department) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ITeamDTO> loadTeams(String sport) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void CreateTournament(String name, String location, BigDecimal fee,
            String sportname, List<String> TeamNames) throws RemoteException{
        
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setLocation(location);
        tournament.setFee(fee);
        Sport sport = (Sport) SportDAO.getInstance().getByName(s, sportname);
        tournament.setSport(sport);

        LinkedList<ITeam> teams = new LinkedList<ITeam>();
        //für jedes  team in der stringliste werden alle teams durchgegangen ob der name darin vorhanden ist
        for (String team : TeamNames) {
            for (ITeam it : TeamDAO.getInstance().getBySport(HibernateUtil.getCurrentSession(), sport)) {
                if (it.getName().equals(team)) {
                    teams.add(it);
                }
            }
        }

        tournament.setTeams(teams);

        TournamentDAO.getInstance().add(s, tournament);
        tx.commit();
        
    }
}
