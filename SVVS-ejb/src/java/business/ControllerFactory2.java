/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.controller.team.ITeamController;
import business.controller.team.TeamController;
import business.controller.team.playerToTeam.IPlayerToTeam;
import business.controller.team.playerToTeam.PlayerToTeam;
import business.controller.team.teamTOplayer.ITeamToPlayer;
import business.controller.team.teamTOplayer.TeamToPlayer;
import business.controller.touramentTeam.ITournamentTeamController;
import business.controller.touramentTeam.TournamentTeamController;
import business.controller.tournament.Create.ITournamentCreation;
import business.controller.tournament.Create.TournamentCreation;
import business.controller.tournament.ITournamentController;
import business.controller.tournament.TournamentController;
import business.controller.tournament.edit.ITournamentEdit;
import business.controller.tournament.edit.TournamentEdit;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

/**
 *
 * @author phil
 */
@Stateless
public class ControllerFactory2 implements ControllerFactory2Remote {

    @Override
    public String isOk() throws RemoteException {
        return "Factory2 ok";
    }

    @Override
    public ITeamController getTeamController() throws RemoteException {
        return TeamController.getInstance();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ITeamToPlayer getTeamToPlayer() throws RemoteException {
        return new TeamToPlayer();
    }

    @Override
    public IPlayerToTeam getPlayerToTeam() throws RemoteException {
        return new PlayerToTeam();
    }

    @Override
    public ITournamentTeamController getTournamentTeamController() throws RemoteException {
        return TournamentTeamController.getInstance();
    }


}
