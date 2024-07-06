package Main.java.com.bouncy_mehdich.sever.Models;

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

    public void setFollowerID(String followerID) {
        FollowerID = followerID;
    }

    public String getFollowedID() {
        return FollowedID;
    }

    public void setFollowedID(String followedID) {
        FollowedID = followedID;
    }
}
