package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Follow {
    @JsonProperty("followerID")
    String FollowerID;
    @JsonProperty("followedID")
    String FollowedID;

    public Follow(String followerID, String followedID) {
        FollowerID = followerID;
        FollowedID = followedID;
    }

    public String getFollowerID() {
        return FollowerID;
    }

    public String getFollowedID() {
        return FollowedID;
    }

    public void setFollowerID(String followerID) {
        FollowerID = followerID;
    }

    public void setFollowedID(String followedID) {
        FollowedID = followedID;
    }
}
