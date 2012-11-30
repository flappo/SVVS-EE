/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.IDTO;
import data.models.IModel;
import data.models.ISport;
import data.models.ITeam;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import javax.rmi.PortableRemoteObject;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public abstract class AbstractDAO<V extends IModel, X extends IDTO> extends PortableRemoteObject implements IDAOs<V, X> {

    private String table;

    public AbstractDAO() throws RemoteException {
        super();
    }

    public AbstractDAO(String table) throws RemoteException {
        super();
        this.table = table;
    }

    protected String getTable()  throws RemoteException{
        return table;
    }

    @Override
    public List<X> getAllDTO(Session s)  throws RemoteException{
        List<X> dto = new LinkedList<X>();

        for (V model : getAll(s)) {
            dto.add(extractDTO(model));
        }
        return dto;
    }

    @Override
    public List<V> getAll(Session s) throws RemoteException {
        Query query = s.createQuery("FROM " + getTable() + "");
        return query.list();
    }

    @Override
    public void add(Session s, V model) throws RemoteException {
        s.saveOrUpdate(model);
    }

    @Override
    public void remove(Session s, V model) throws RemoteException {
        s.delete(model);
    }

    @Override
    public void remove(Session s, List<V> models) throws RemoteException {
        for (V model : models) {
            s.delete(model);
        }
    }

    @Override
    public void update(Session s, V model) throws RemoteException {
        s.update(model);
    }

    @Override
    public abstract V create() throws RemoteException;
    //public abstract X createDTO(); 
}
