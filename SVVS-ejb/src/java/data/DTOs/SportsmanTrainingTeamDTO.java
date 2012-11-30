/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ISportsmanTrainingTeam;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class SportsmanTrainingTeamDTO extends AbstractDTO<ISportsmanTrainingTeam> implements ISportsmanTrainingTeamDTO{

    protected ISportsmanDTO sportsman;
//    protected ITrainingTeamDTO team;
    protected String position;

    public SportsmanTrainingTeamDTO() throws RemoteException {
        super();
    }
    
    
    public SportsmanTrainingTeamDTO(ISportsmanTrainingTeam model) throws RemoteException{
        super();
        if(model == null) return;
        extract(model);
    }
    
    @Override
    public void extract(ISportsmanTrainingTeam model)throws RemoteException{
        try {
            this.id = model.getSportsmanTrainingTeamID();
            this.sportsman = new SportsmanDTO(model.getSportsman());
            this.position = model.getPosition();
            //this.team = new TrainingTeamDTO(model.getTeam());
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanTrainingTeamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ISportsmanDTO getSportsman() throws RemoteException{
        return sportsman;
    }

    public void setSportsman(ISportsmanDTO sportsman) throws RemoteException{
        this.sportsman = sportsman;
    }

//    public ITrainingTeamDTO getTeam() {
//        return team;
//    }
//
//    public void setTeam(ITrainingTeamDTO team) {
//        this.team = team;
//    }

    @Override
    public String getPosition()throws RemoteException {
        return position;
    }

    @Override
    public void setPosition(String position) throws RemoteException{
        this.position = position;
    }
    
    
}
