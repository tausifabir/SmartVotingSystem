package com.example.smartvoting.Model;

import java.util.ArrayList;
import java.util.List;

public class EventModel {

    private int userId;
    private int eventId;
    private String candidatePosition;
    private String candidateName;
    private String VotingStartTime;
    private String VotingEndTime;
    private int totalVote;


    public static List<UserModel> eventModelList = new ArrayList<>();


    public EventModel(int eventId, String candidatePosition, String candidateName, String votingStartTime, String votingEndTime) {
        this.eventId = eventId;
        this.candidatePosition = candidatePosition;
        this.candidateName = candidateName;
        VotingStartTime = votingStartTime;
        VotingEndTime = votingEndTime;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCandidatePosition() {
        return candidatePosition;
    }

    public void setCandidatePosition(String candidatePosition) {
        this.candidatePosition = candidatePosition;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getVotingStartTime() {
        return VotingStartTime;
    }

    public void setVotingStartTime(String votingStartTime) {
        VotingStartTime = votingStartTime;
    }

    public String getVotingEndTime() {
        return VotingEndTime;
    }

    public void setVotingEndTime(String votingEndTime) {
        VotingEndTime = votingEndTime;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public static List<UserModel> getEventModelList() {
        return eventModelList;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", candidatePosition='" + candidatePosition + '\'' +
                ", candidateName='" + candidateName + '\'' +
                ", VotingStartTime='" + VotingStartTime + '\'' +
                ", VotingEndTime='" + VotingEndTime + '\'' +
                ", totalVote=" + totalVote +
                '}';
    }
}
