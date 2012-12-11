/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.messages.jms;

import business.messages.jms.interfaces.ITournamentInviteMessage;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ITournamentDTO;
import data.DTOs.ITrainingTeamDTO;

/**
 *
 * @author uubu
 */
public class TournamentInviteMessage implements ITournamentInviteMessage {

    private ISportsmanDTO sportsman;
    private ITournamentDTO tournament;
    private ITrainingTeamDTO team;
     private String receiver;

    public TournamentInviteMessage(ISportsmanDTO sportsman, ITournamentDTO tournament, ITrainingTeamDTO team) {
        this.sportsman = sportsman;
        this.tournament = tournament;
        this.team = team;
    }

    @Override
    public ISportsmanDTO getSportsman() {
        return this.sportsman;
    }

    @Override
    public ITrainingTeamDTO getTeam() {
        return this.team;
    }

    @Override
    public ITournamentDTO getTournament() {
        return this.tournament;
    }

    @Override
    public String getText() {
        return "Sie wurden mit dem Team " + getTeam() +" zum Wettkampf " + getTournament() + " eingeladen!";
    }
    
    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
