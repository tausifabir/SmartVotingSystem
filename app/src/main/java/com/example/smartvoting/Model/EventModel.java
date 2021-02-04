package com.example.smartvoting.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class EventModel {

    private int userId;
    private int eventId;
    private String candidatePosition;
    private String candidateName;
    private String votingCode;
    private String votingStartTime;
    private String votingEndTime;
    private int candidate_countedVote = 0;
    private Context context;

    public EventModel(Context context) {
        this.context = context;
    }

    private int totalVote = 0;


    public static List<UserModel> eventModelList = new ArrayList<>();


    public EventModel(int eventId, String candidatePosition, String candidateName, String votingCode, String votingStartTime, String votingEndTime) {
        this.eventId = eventId;
        this.candidatePosition = candidatePosition;
        this.candidateName = candidateName;
        this.votingCode = votingCode;
        this.votingStartTime = votingStartTime;
        this.votingEndTime = votingEndTime;
    }

    public EventModel(String candidatePosition, String candidateName, String votingCode, String votingStartTime, String votingEndTime) {
        this.candidatePosition = candidatePosition;
        this.candidateName = candidateName;
        this.votingCode = votingCode;
        this.votingStartTime = votingStartTime;
        this.votingEndTime = votingEndTime;

    }

    public EventModel(int eventId, int candidate_countedVote) {
        this.eventId = eventId;
        this.candidate_countedVote = candidate_countedVote;

    }

    public EventModel(int eventId, String candidatePosition, String candidateName, String votingCode, String votingStartTime, String votingEndTime, int candidate_countedVote) {
        this.eventId = eventId;
        this.candidatePosition = candidatePosition;
        this.candidateName = candidateName;
        this.votingCode = votingCode;
        this.votingStartTime = votingStartTime;
        this.votingEndTime = votingEndTime;
        this.candidate_countedVote = candidate_countedVote;
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


    public String getVotingCode() {
        return votingCode;
    }

    public void setVotingCode(String votingCode) {
        this.votingCode = votingCode;
    }

    public String getVotingStartTime() {
        return votingStartTime;
    }

    public void setVotingStartTime(String votingStartTime) {
        this.votingStartTime = votingStartTime;
    }

    public String getVotingEndTime() {
        return votingEndTime;
    }

    public void setVotingEndTime(String votingEndTime) {
        this.votingEndTime = votingEndTime;
    }

    public int getCandidate_countedVote() {
        return candidate_countedVote;
    }

    public void setCandidate_countedVote(int candidate_countedVote) {
        this.candidate_countedVote = candidate_countedVote;
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
                ", VotingStartTime='" + votingStartTime + '\'' +
                ", VotingEndTime='" + votingEndTime + '\'' +
                ", totalVote=" + candidate_countedVote +
                '}';
    }
}
