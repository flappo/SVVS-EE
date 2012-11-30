/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.Create.States;

import business.AController;
import business.controller.tournament.Create.TournamentCreation;
import business.controller.tournament.TournamentController;
import data.DTOs.IDepartmentDTO;
import data.DTOs.ISportDTO;
import data.DTOs.ITeamDTO;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author phil
 */
public class TournamentCreateLoadTeamsState extends AController implements ITournamentCreateState {

    TournamentCreation _creator;

    public TournamentCreateLoadTeamsState(TournamentCreation creator)throws RemoteException{
        super();
        _creator = creator;
    }

    @Override
    public LinkedList<ISportDTO> loadSport(List<IDepartmentDTO> department) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ITeamDTO> loadTeams(String sport) throws RemoteException{
        /*LinkedList<ITeam> teams = new LinkedList<ITeam>();
        for (ITeam iTeam : TeamDAO.getInstance().getBySport(HibernateUtil.getCurrentSession(), sport)) {
            teams.add(iTeam);
        }
        _creator.setCurState(new TournamentCreateState(_creator));
        return teams;*/
        _creator.setCurState(new TournamentCreateState(_creator));
        return TournamentController.getInstance().loadTeams(sport);
    }

    @Override
    public void CreateTournament(String name, String location, BigDecimal fee, String sportname, List<String> TeamNames) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
