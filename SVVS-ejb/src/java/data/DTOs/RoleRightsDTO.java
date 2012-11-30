/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IRoleRights;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class RoleRightsDTO extends AbstractDTO<IRoleRights> implements IRoleRightsDTO {

    protected String name;
    protected Long right;

    public RoleRightsDTO() throws RemoteException {
        super();
    }

    public RoleRightsDTO(IRoleRights model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(IRoleRights model) throws RemoteException{

        this.id = model.getRoleRightsID();
        this.name = model.getName();
        this.right = model.getRight();
    }

    @Override
    public String getName() throws RemoteException{
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException{
        this.name = name;
    }

    @Override
    public Long getRight()throws RemoteException {
        return right;
    }

    @Override
    public void setRight(Long right) throws RemoteException{
        this.right = right;
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(RoleRightsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
