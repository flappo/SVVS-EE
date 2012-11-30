/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.role.EditPersonRole.state;

import business.AController;
import business.controller.role.EditPersonRole.IEditPersonRole;
import business.controller.role.RoleController;
import data.DTOs.IDepartmentDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.IRoleRightsDTO;
import data.DTOs.ISportDTO;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author phil
 */
public class EditPersonRoleState extends AController implements IEditPersonRoleState {

    IEditPersonRole _editor;

    public EditPersonRoleState(IEditPersonRole editor) throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public List<IRoleRightsDTO> loadRoleRightsOfPerson(IPersonDTO person) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void EditPersonRole(IPersonDTO person, List<IRoleRightsDTO> roles, IDepartmentDTO department, ISportDTO sport) throws  RemoteException{
        RoleController.getInstance().EditPersonRole(person, roles, department, sport);
    }

    @Override
    public List<IRoleRightsDTO> loadRoleRights() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
