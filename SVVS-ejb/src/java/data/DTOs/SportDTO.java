/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ISport;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class SportDTO extends AbstractDTO<ISport> implements ISportDTO {

    private String name;
    private int maxPlayers;
    // private List<ITeamDTO> teams;

    public SportDTO() throws RemoteException {
        super();
    }

    public SportDTO(ISport model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(ISport model)throws RemoteException {
        this.id = model.getSportID();
        this.name = model.getName();

//        for (ITeam team : model.getTeams()) {
//            //teams.add(new TeamDTO(team));
//        }
    }

    @Override
    public String getName()throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException{
        this.name = name;
    }

    @Override
    public int getMaxPlayers() throws RemoteException{
        return maxPlayers;
    }

    @Override
    public void setMaxPlayers(int maxPlayers)throws RemoteException {
        this.maxPlayers = maxPlayers;
    }

//    @Override
//    public List<ITeamDTO> getTeams() {
//        return teams;
//    }
//
//    @Override
//    public void setTeams(List<ITeamDTO> teams) {
//        this.teams = teams;
//    }
    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(SportDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (((ISportDTO) obj).getName().equals(this.getName())) {

                return true;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SportDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
