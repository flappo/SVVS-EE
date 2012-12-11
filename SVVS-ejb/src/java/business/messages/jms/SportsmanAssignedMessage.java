/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.messages.jms;

import business.messages.jms.interfaces.ISportsmanAssignedMessage;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ITrainingTeamDTO;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class SportsmanAssignedMessage implements ISportsmanAssignedMessage{

    private ISportsmanDTO sportsman;
    private ITrainingTeamDTO team;
    private String receiver;
    
    public SportsmanAssignedMessage(ITrainingTeamDTO team, ISportsmanDTO sportsman) {
        
        if(team == null || sportsman == null){
            return;
        }
        
        this.sportsman = sportsman;
        this.team = team;
    }

    
    
    @Override
    public ISportsmanDTO getSportsman() {
        return this.sportsman;
    }

    @Override
    public ITrainingTeamDTO getTeam() {
        return this.team;
    }

    @Override
    public String getText() {
        try {
            return getSportsman().getPerson() + " wurde dem Team " + getTeam() + " zugewiesen!";
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanAssignedMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
}
