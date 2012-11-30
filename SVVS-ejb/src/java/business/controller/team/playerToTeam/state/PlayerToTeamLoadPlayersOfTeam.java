/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.team.playerToTeam.state;

import business.AController;
import business.controller.team.TeamController;
import business.controller.team.playerToTeam.PlayerToTeam;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ISportsmanTrainingTeamDTO;
import data.DTOs.ITrainingTeamDTO;
import data.models.ISportsman;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author phil
 */
public class PlayerToTeamLoadPlayersOfTeam extends AController implements IPlayerToTeamState {

    PlayerToTeam _context;
    public PlayerToTeamLoadPlayersOfTeam(PlayerToTeam context) throws RemoteException {
    super();
        _context = context;
    }
    
    @Override
    public LinkedList<ITrainingTeamDTO> loadTeam() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ITrainingTeamDTO> loadTeams(String sportname) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ISportsmanTrainingTeamDTO> loadPlayersOfTeam(String TeamName) throws RemoteException {
        _context.setState(new PlayerToTeamLoadSportmanState(_context));
        return TeamController.getInstance().loadPlayersOfTeam(TeamName);
    }

    @Override
    public LinkedList<ISportsmanDTO> loadSportsman(String sportname) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AddPlayerToTeam(int TrainingTeamID, int SportsmanID, String position) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
