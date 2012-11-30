/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IRight;
import java.rmi.RemoteException;

/**
 * not everything has throws RemoteException
 * @author uubu
 */
public class RightDTO extends AbstractDTO<IRight> implements IRightDTO {

    protected long value;
    protected String name;

    public RightDTO() throws RemoteException {
        super();
    }

    public RightDTO(IRight model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(IRight model) throws RemoteException{
        this.id = model.getRightID();
        this.value = model.getValue();
        this.name = model.getName();
    }

    @Override
    public long getValue() throws RemoteException{
        return value;
    }

    @Override
    public void setValue(long value)throws RemoteException {
        this.value = value;
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
    public boolean equals(Object obj) {

        if (obj instanceof Long) {

            Long check = ((Long) obj) & this.value;
            if (check > 0) {
                return true;
            }
            return false;

        } else {
            return false;
        }

    }
}
