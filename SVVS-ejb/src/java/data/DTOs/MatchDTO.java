/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IMatch;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class MatchDTO extends AbstractDTO<IMatch> implements IMatchDTO {

    private ITournamentDTO tournament;
    private String location;
    private String date;
    private ITeamDTO team1;
    private ITeamDTO team2;
    private Integer goalsTeam1;
    private Integer goalsTeam2;

    public MatchDTO() throws RemoteException {
        super();
    }

    public MatchDTO(IMatch model) throws RemoteException {
        super();
        if (model == null) {
            return;
        }
        extract(model);
    }

    @Override
    public void extract(IMatch model) throws RemoteException{
        try {
            this.id = model.getMatchID();
            this.location = model.getLocation();
            this.date = model.getDate().toString();
            this.team1 = new TeamDTO(model.getTeam1());
            this.team2 = new TeamDTO(model.getTeam2());
            this.goalsTeam1 = model.getGoalsTeam1();
            this.goalsTeam2 = model.getGoalsTeam2();
        } catch (RemoteException ex) {
            Logger.getLogger(MatchDTO.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getLocation() throws RemoteException{
        return location;
    }

    @Override
    public void setLocation(String location) throws RemoteException{
        this.location = location;
    }

    @Override
    public String getDate() throws RemoteException{
        return date;
    }

    @Override
    public void setDate(String date) throws RemoteException{
        this.date = date;
    }

    @Override
    public ITeamDTO getTeam1() throws RemoteException{
        return team1;
    }

    @Override
    public void setTeam1(ITeamDTO team1)throws RemoteException {
        this.team1 = team1;
    }

    @Override
    public ITeamDTO getTeam2() throws RemoteException{
        return team2;
    }

    @Override
    public void setTeam2(ITeamDTO team2)throws RemoteException {
        this.team2 = team2;
    }

    @Override
    public Integer getGoalsTeam1() throws RemoteException{
        return goalsTeam1;
    }

    @Override
    public void setGoalsTeam1(Integer goalsTeam1) throws RemoteException{
        this.goalsTeam1 = goalsTeam1;
    }

    @Override
    public Integer getGoalsTeam2()throws RemoteException {
        return goalsTeam2;
    }

    @Override
    public void setGoalsTeam2(Integer goalsTeam2) throws RemoteException{
        this.goalsTeam2 = goalsTeam2;
    }
}
