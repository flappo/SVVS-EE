/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IModel;
import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author uubu
 */
public abstract class AbstractDTO<V extends IModel> extends PortableRemoteObject implements IDTO<V> {

    public AbstractDTO() throws RemoteException {
        super();
    }
    protected int id;

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public abstract void extract(V model)throws RemoteException ;
}
