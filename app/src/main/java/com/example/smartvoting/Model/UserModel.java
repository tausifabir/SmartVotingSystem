package com.example.smartvoting.Model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String userName;
    private String userPhone;
    private String userNid;
    private String candidatePosition;
    private String candidateName;
    private String VotingStartTime;
    private String VotingEndTime;
    private int totalVote;


    // for register new User
    public UserModel(String userName, String userNid) {
        this.userName = userName;
        this.userNid = userNid;
    }

    // for register new User
    public UserModel(String userName, String userPhone, String userNid) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userNid = userNid;
    }

    // for creating Voting Event and Candidate
    public UserModel(String candidatePosition, String candidateName, String votingStartTime, String votingEndTime) {
        this.candidatePosition = candidatePosition;
        this.candidateName = candidateName;
        VotingStartTime = votingStartTime;
        VotingEndTime = votingEndTime;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserNid() {
        return userNid;
    }

    public void setUserNid(String userNid) {
        this.userNid = userNid;
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
}
