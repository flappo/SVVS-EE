/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IDepartment;
import data.models.ISport;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * not everything has throws RemoteException
 * @author uubu
 */
public class DepartmentDTO extends AbstractDTO<IDepartment> implements IDepartmentDTO {

    protected String name;
    protected List<ISportDTO> sports;

    public DepartmentDTO() throws RemoteException {
        super();
    }

    public DepartmentDTO(IDepartment model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        sports = new LinkedList<ISportDTO>();
        extract(model);
    }

    @Override
    public void extract(IDepartment model) throws RemoteException{
        this.id = model.getDepartmentID();
        this.name = model.getName();
        for (ISport sp : model.getSports()) {
            try {
                sports.add(new SportDTO(sp));
            } catch (RemoteException ex) {
                Logger.getLogger(DepartmentDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    public String toString() {
        try {
            return this.getName();
        } catch (RemoteException ex) {
            Logger.getLogger(DepartmentDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValues() throws RemoteException {
        return this.getName();
    }

    
    @Override
    public List<ISportDTO> getSports()throws RemoteException {
        return sports;
    }

    @Override
    public void setSports(List<ISportDTO> sports) throws RemoteException{
        this.sports = sports;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DepartmentDTO other = (DepartmentDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
