/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.controller.JMS.IMessageController;
import business.controller.JMS.MessageController;
import business.controller.country.CountryController;
import business.controller.country.CountryControllerRemote;
import business.controller.departments.DepartmentController;
import business.controller.departments.IDepartmentController;
import business.controller.person.AuthentificationController;
import business.controller.person.IAuthentificationController;
import business.controller.person.IPersonController;
import business.controller.person.PersonController;
import business.controller.person.create.IPersonCreation;
import business.controller.person.create.PersonCreation;
import business.controller.person.delete.IPersonDelete;
import business.controller.person.delete.PersonDelete;
import business.controller.person.edit.IPersonEdit;
import business.controller.person.edit.PersonEdit;
import business.controller.sport.ISportController;
import business.controller.sport.SportController;
import business.controller.team.ITeamController;
import business.controller.team.TeamController;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

/**
 *
 * @author phil
 */
@Stateless
public class ControllerFactory implements ControllerFactoryRemote {

    /* {
     //Remove before deploy
     String policy = ControllerFactory.class.getProtectionDomain().getClassLoader().getResource("client.policy").getFile();
     System.setProperty("java.security.policy", policy);
     System.setSecurityManager(new SecurityManager());
     }*/
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String isOk() throws RemoteException {
        return "works";
    }

    @Override
    public ISportController getSportController() throws RemoteException {
        return new SportController();
    }

    @Override
    public CountryControllerRemote getCountryController() throws RemoteException {
        return new CountryController();
    }

    @Override
    public IDepartmentController getDepartmentController() throws RemoteException {
        return DepartmentController.getInstance();
    }

    @Override
    public IPersonController getPersonController() throws RemoteException {
        return PersonController.getInstance();
    }

    @Override
    public IAuthentificationController getAuthentificationController() throws RemoteException {
        return new AuthentificationController();
    }

    @Override
    public IPersonCreation getPersonCreation() throws RemoteException {
        return new PersonCreation();
    }

    @Override
    public IPersonDelete getPersonDelete() throws RemoteException {
        return new PersonDelete();
    }

    @Override
    public IPersonEdit getPersonEdit() throws RemoteException {
        return new PersonEdit();
    }

    @Override
    public IMessageController getMessageController() throws RemoteException, Exception {
        return MessageController.getInstance();
    }
}
