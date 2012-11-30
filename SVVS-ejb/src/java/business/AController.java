/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author phil
 */
public class AController extends PortableRemoteObject implements IController{

    public AController() throws RemoteException {
        super();
    }
}
