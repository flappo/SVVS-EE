/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ICoach;
import data.models.ISportsmanTrainingTeam;
import data.models.ITrainingTeam;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class TrainingTeamDTO extends AbstractTeamDTO<ITrainingTeam> implements ITrainingTeamDTO {

    protected IDepartmentDTO department;
    protected List<ISportsmanTrainingTeamDTO> sportsmen;
    protected List<ICoachDTO> coaches;

    public TrainingTeamDTO() throws RemoteException {
        super();
    }

    @Override
    public void extract(ITrainingTeam model)throws RemoteException {
        try {
            if (model == null) {
                return;
            }

            coaches = new LinkedList<ICoachDTO>();
            sportsmen = new LinkedList<ISportsmanTrainingTeamDTO>();

            extractTeam(model);
            this.department = new DepartmentDTO(model.getDepartment());


            for (ISportsmanTrainingTeam stt : model.getSportsmen()) {
                sportsmen.add(new SportsmanTrainingTeamDTO(stt));
            }

            for (ICoach coach : model.getCoaches()) {
                coaches.add(new CoachDTO(coach));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TrainingTeamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TrainingTeamDTO(ITrainingTeam model) throws RemoteException {
//        sportsmen = new LinkedList<>();
//        coaches = new LinkedList<>();
        super();
        extract(model);
    }

    @Override
    public IDepartmentDTO getDepartment() throws RemoteException{
        return department;
    }

    @Override
    public void setDepartment(IDepartmentDTO department) throws RemoteException{
        this.department = department;
    }

    @Override
    public String getName() throws RemoteException {
        return super.getName();
    }

    @Override
    public List<ISportsmanTrainingTeamDTO> getSportsmen() throws RemoteException{
        return sportsmen;
    }

    @Override
    public void setSportsmen(List<ISportsmanTrainingTeamDTO> sportsmen) throws RemoteException{
        this.sportsmen = sportsmen;
    }

    @Override
    public List<ICoachDTO> getCoaches() throws RemoteException{
        return coaches;
    }

    @Override
    public void setCoaches(List<ICoachDTO> coaches) throws RemoteException{
        this.coaches = coaches;
    }

    @Override
    public ISportDTO getSport() throws RemoteException{
        return sport;
    }

    @Override
    public void setSport(ISportDTO sport)throws RemoteException {
        this.sport = sport;
    }

    @Override
    public ILeagueDTO getLeague() throws RemoteException{
        return league;
    }

    @Override
    public void setLeague(ILeagueDTO league) throws RemoteException{
        this.league = league;
    }
}
