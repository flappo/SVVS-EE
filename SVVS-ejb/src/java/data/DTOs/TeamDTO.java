/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ITeam;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class TeamDTO extends AbstractTeamDTO<ITeam> implements ITeamDTO {

    public TeamDTO() throws RemoteException {
        super();
    }

    public TeamDTO(ITeam model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(ITeam model)throws RemoteException {
        try {
            extractTeam(model);
        } catch (RemoteException ex) {
            Logger.getLogger(TeamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(TeamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValues() throws RemoteException {
         try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(TeamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    @Override
    public ILeagueDTO getLeague() throws RemoteException{
        return league;
    }

    @Override
    public void setLeague(ILeagueDTO league)throws RemoteException {
        this.league = league;
    }
}
