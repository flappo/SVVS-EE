/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ILeague;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class LeagueDTO extends AbstractDTO<ILeague> implements ILeagueDTO {

    private String name;
    //  private List<ITeamDTO> teams;

    public LeagueDTO() throws RemoteException {
        super();
    }

    public LeagueDTO(ILeague model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(ILeague model) throws RemoteException{
        this.id = model.getLeagueID();
        this.name = model.getName();

    }

    @Override
    public String getName() throws RemoteException{
        return name;
    }

    @Override
    public void setName(String name)throws RemoteException {
        this.name = name;
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(LeagueDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
