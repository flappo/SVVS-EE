/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.controller.role.EditPersonRole.EditPersonRole;
import business.controller.role.EditPersonRole.IEditPersonRole;
import business.controller.role.IRoleController;
import business.controller.role.RoleController;
import business.controller.tournament.Create.ITournamentCreation;
import business.controller.tournament.Create.TournamentCreation;
import business.controller.tournament.ITournamentController;
import business.controller.tournament.TournamentController;
import business.controller.tournament.edit.ITournamentEdit;
import business.controller.tournament.edit.TournamentEdit;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

/**
 *
 * @author phil
 */
@Stateless
public class ControllerFactory3 implements ControllerFactory3Remote {

    @Override
    public String isOk() {
        return "Factory3 ok";
    }

    @Override
    public ITournamentController getTournamentController() throws RemoteException {
        return TournamentController.getInstance();
    }

    @Override
    public ITournamentEdit getTournamentEdit() throws RemoteException {
        return new TournamentEdit();
    }

    @Override
    public ITournamentCreation getTournamentCreation() throws RemoteException {
        return new TournamentCreation();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public IRoleController getRoleController() throws RemoteException {
        return RoleController.getInstance();
    }

    @Override
    public IEditPersonRole getEditPersonRole() throws RemoteException {
        return new EditPersonRole();
    }
}
