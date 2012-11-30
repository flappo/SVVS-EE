/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IRole;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class RoleDTO extends AbstractRoleDTO<IRole> implements IRoleDTO {

    public RoleDTO() throws RemoteException {
        super();
    }

    public RoleDTO(IRole model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(IRole model) throws RemoteException{
        try {
            extractRole(model);
        } catch (RemoteException ex) {
            Logger.getLogger(RoleDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
