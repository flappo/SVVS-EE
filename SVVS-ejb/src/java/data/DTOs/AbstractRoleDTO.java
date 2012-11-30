/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IRole;
import java.rmi.RemoteException;

/**
 *
 * @author uubu
 */
public abstract class AbstractRoleDTO<V extends IRole> extends AbstractDTO<V> {

    public AbstractRoleDTO() throws RemoteException {
        super();
    }
    
    protected IPersonDTO person;
    protected IRoleRightsDTO roleRight;
    protected IDepartmentDTO department;
    protected ISportDTO sport;

    public void extractRole(V model) throws RemoteException {
        if (model == null){
            return;
        }
        this.id = model.getRoleID();
        this.person = new PersonDTO(model.getPerson());
        this.roleRight = new RoleRightsDTO(model.getRoleRight());
        this.department = new DepartmentDTO(model.getDepartment());
        this.sport = new SportDTO(model.getSport());
        
    }
    
    public IPersonDTO getPerson() throws RemoteException {
        return person;
    }

    public void setPerson(IPersonDTO person)throws RemoteException  {
        this.person = person;
    }

    public IRoleRightsDTO getRoleRight() throws RemoteException {
        return roleRight;
    }

    public void setRoleRight(IRoleRightsDTO roleRight) throws RemoteException {
        this.roleRight = roleRight;
    }

    public IDepartmentDTO getDepartment() throws RemoteException {
        return department;
    }

    public void setDepartment(IDepartmentDTO department) throws RemoteException {
        this.department = department;
    }

    public ISportDTO getSport() throws RemoteException {
        return sport;
    }

    public void setSport(ISportDTO sport)throws RemoteException  {
        this.sport = sport;
    }
}
