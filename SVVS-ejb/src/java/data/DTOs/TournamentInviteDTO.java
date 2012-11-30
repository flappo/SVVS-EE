/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ITournamentInvite;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class TournamentInviteDTO extends AbstractDTO<ITournamentInvite> implements ITournamentInviteDTO{

    private ITournamentDTO tournament;
    private ISportsmanDTO sportsman;
    private ITrainingTeamDTO team;
    private boolean accepted;

    public TournamentInviteDTO() throws RemoteException {
        super();
    }
    
    
    
    public TournamentInviteDTO(ITournamentInvite model) throws RemoteException{
        super();
        if(model == null) return;
        extract(model);
    }
    
    @Override
    public void extract(ITournamentInvite model) throws RemoteException{
        try {
            this.id = model.getTournamentInviteID();
            this.tournament = new TournamentDTO(model.getTournament());
            this.sportsman = new SportsmanDTO(model.getSportsman());
            this.team = new TrainingTeamDTO(model.getTeam());
            this.accepted = model.isAccepted();
        } catch (RemoteException ex) {
            Logger.getLogger(TournamentInviteDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public ITournamentDTO getTournament()throws RemoteException {
        return tournament;
    }

    @Override
    public void setTournament(ITournamentDTO tournament)throws RemoteException {
        this.tournament = tournament;
    }

    @Override
    public ISportsmanDTO getSportsman() throws RemoteException{
        return sportsman;
    }

    @Override
    public void setSportsman(ISportsmanDTO sportsman) throws RemoteException{
        this.sportsman = sportsman;
    }

    @Override
    public boolean isAccepted()throws RemoteException {
        return accepted;
    }

    @Override
    public void setAccepted(boolean accepted) throws RemoteException{
        this.accepted = accepted;
    }   

    @Override
    public ITrainingTeamDTO getTeam() throws RemoteException{
        return team;
    }

    @Override
    public void setTeam(ITrainingTeamDTO team) throws RemoteException{
        this.team = team;
    }
    
    
}
