/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.messages.jms;

import business.messages.jms.interfaces.ISportsmanCreatedMessage;
import data.DTOs.ISportsmanDTO;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class SportsmanCreatedMessage implements ISportsmanCreatedMessage {

    private ISportsmanDTO sportsman;
             private String receiver;
    
    public SportsmanCreatedMessage(ISportsmanDTO sportsman) {
        this.sportsman = sportsman;
    }

    @Override
    public ISportsmanDTO getSportsman() {
        return this.sportsman;
    }

    @Override
    public String getText() {
        try {
            return getSportsman().getPerson() + " wurde der Abteilung " + getSportsman().getDepartment() + " und der Sportart " + getSportsman().getSport() + " zugewiesen!";
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanCreatedMessage.class.getName()).log(Level.SEVERE, null, ex);
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
