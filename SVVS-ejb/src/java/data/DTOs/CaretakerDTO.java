/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ICaretaker;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class CaretakerDTO extends AbstractRoleDTO<ICaretaker> implements ICaretakerDTO{

    public CaretakerDTO() throws RemoteException {
        super();
    }
    
    public CaretakerDTO(ICaretaker model) throws RemoteException{
        super();
        if(model == null) return;
        extract(model);
    }

    @Override
    public void extract(ICaretaker model) throws RemoteException {
        try {
            extractRole(model);
        } catch (RemoteException ex) {
            Logger.getLogger(CaretakerDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
