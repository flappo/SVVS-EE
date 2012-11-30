/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IManager;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class ManagerDTO extends AbstractRoleDTO<IManager> implements IManagerDTO{

    public ManagerDTO() throws RemoteException {
        super();
    }

    
    
    public ManagerDTO(IManager model) throws RemoteException{
        super();
        if(model == null) return;
        extract(model);
    }
    
    @Override
    public void extract(IManager model) throws RemoteException{
        try {
            extractRole(model);
        } catch (RemoteException ex) {
            Logger.getLogger(ManagerDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
