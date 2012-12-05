/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import com.sun.corba.se.impl.corba.CORBAObjectImpl;
import data.models.IModel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author uubu
 */
public abstract class AbstractDTO<V extends IModel> extends PortableRemoteObject /*UnicastRemoteObject*/ /*CORBAObjectImpl*/ implements IDTO<V> {

    public AbstractDTO() throws RemoteException {
        super();
    }
    protected int id;

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public abstract void extract(V model) throws RemoteException;
}
