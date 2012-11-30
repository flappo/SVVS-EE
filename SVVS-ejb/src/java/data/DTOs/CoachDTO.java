/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ICoach;
import data.models.ITeam;
import data.models.ITrainingTeam;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class CoachDTO extends AbstractRoleDTO<ICoach> implements ICoachDTO {

    public CoachDTO() throws RemoteException {
        super();
    }

    //private List<ITrainingTeamDTO> teams;
    public CoachDTO(ICoach model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        //teams = new LinkedList<>();
        extract(model);
    }

    @Override
    public void extract(ICoach model)throws RemoteException  {
        try {
            extractRole(model);

            //for(ITeam team : model.getTeams()){
            //  teams.add(new TrainingTeamDTO((ITrainingTeam)team));
            //}
        } catch (RemoteException ex) {
            Logger.getLogger(CoachDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    @Override
//    public List<ITrainingTeamDTO> getTeams() {
//        return teams;
//    }
//
//    @Override
//    public void setTeams(List<ITrainingTeamDTO> teams) {
//        this.teams = teams;
//    }
}
