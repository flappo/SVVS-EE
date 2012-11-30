/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.role.EditPersonRole.state;

import business.AController;
import business.controller.role.EditPersonRole.EditPersonRole;
import business.controller.role.EditPersonRole.IEditPersonRole;
import business.controller.role.RoleController;
import data.DTOs.IDepartmentDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.IRoleDTO;
import data.DTOs.IRoleRightsDTO;
import data.DTOs.ISportDTO;
import data.models.IPerson;
import data.models.IRole;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author phil
 */
public class EditPersonRoleLoadState  extends AController implements IEditPersonRoleState{

     EditPersonRole _editor;
    public EditPersonRoleLoadState(EditPersonRole editor)throws RemoteException {
        super();
        _editor = editor;
    }

    
    @Override
    public List<IRoleRightsDTO> loadRoleRightsOfPerson(IPersonDTO person) throws RemoteException {
        _editor.setState(new EditPersonRoleState(_editor));
        return RoleController.getInstance().loadRoleRightsOfPerson(person);
    }

    @Override
    public void EditPersonRole(IPersonDTO person, List<IRoleRightsDTO> roles, IDepartmentDTO department, ISportDTO sport) throws  RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IRoleRightsDTO> loadRoleRights() throws RemoteException {
        _editor.setState(new EditPersonRoleState(_editor));
        return RoleController.getInstance().loadRoleRights();
    }

    
}
