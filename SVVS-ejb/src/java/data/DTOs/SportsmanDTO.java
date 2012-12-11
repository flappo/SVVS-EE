/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ISportsman;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class SportsmanDTO extends AbstractRoleDTO<ISportsman> implements ISportsmanDTO {

    public SportsmanDTO() throws RemoteException {
        super();
    }

    public SportsmanDTO(ISportsman model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(ISportsman model)throws RemoteException {
        try {
            extractRole(model);
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        try {
            return person.getLastname() + " " + person.getFirstname();
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValues() throws RemoteException {
        try {
            return person.getLastname() + " " + person.getFirstname();
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ISportsmanDTO)) {
            return false;
        }
        try {
            if (((ISportsmanDTO) obj).getId() == this.id) {
                return true;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

 
}
