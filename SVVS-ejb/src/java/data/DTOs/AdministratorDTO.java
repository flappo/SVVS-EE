/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IAdministrator;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class AdministratorDTO extends AbstractRoleDTO<IAdministrator> implements IAdministratorDTO {

    public AdministratorDTO() throws RemoteException {
        super();
    }
    
    public AdministratorDTO(IAdministrator model) throws RemoteException {
        super();
        if(model == null) return;
        extract(model);
    }

    @Override
    public void extract(IAdministrator model) throws RemoteException {
        try {
            extractRole(model);
            //zusätzliche Entitäten hier dazufügen
        } catch (RemoteException ex) {
            Logger.getLogger(AdministratorDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
}
