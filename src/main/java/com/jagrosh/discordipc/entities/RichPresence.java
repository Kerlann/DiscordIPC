package com.jagrosh.discordipc.entities;

import org.json.JSONArray;
import org.json.JSONObject;

public class RichPresence {
    private final String state;

    private final String details;

    private final long startTimestamp;

    private final long endTimestamp;

    private final String largeImageKey;

    private final String largeImageText;

    private final String smallImageKey;

    private final String smallImageText;

    private final String partyId;

    private final int partySize;

    private final int partyMax;

    private final String matchSecret;

    private final String joinSecret;

    private final String spectateSecret;

    private final RichPresenceButton[] buttons;

    private final boolean instance;

    public RichPresence(String state, String details, long startTimestamp, long endTimestamp, String largeImageKey, String largeImageText, String smallImageKey, String smallImageText, String partyId, int partySize, int partyMax, String matchSecret, String joinSecret, String spectateSecret, RichPresenceButton[] buttons, boolean instance) {
        this.state = state;
        this.details = details;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.largeImageKey = largeImageKey;
        this.largeImageText = largeImageText;
        this.smallImageKey = smallImageKey;
        this.smallImageText = smallImageText;
        this.partyId = partyId;
        this.partySize = partySize;
        this.partyMax = partyMax;
        this.matchSecret = matchSecret;
        this.joinSecret = joinSecret;
        this.spectateSecret = spectateSecret;
        this.buttons = buttons;
        this.instance = instance;
    }

    public JSONObject toJson() {
        JSONObject timestamps = new JSONObject();
        JSONObject assets = new JSONObject();
        JSONObject party = new JSONObject();
        JSONObject secrets = new JSONObject();
        JSONObject finalObject = new JSONObject();
        JSONArray buttonArray = new JSONArray();
        if (this.startTimestamp > 0L) {
            timestamps.put("start", Long.valueOf(this.startTimestamp));
            if (this.endTimestamp > this.startTimestamp)
                timestamps.put("end", Long.valueOf(this.endTimestamp));
        }
        if (this.largeImageKey != null && !this.largeImageKey.isEmpty()) {
            assets.put("large_image", this.largeImageKey);
            if (this.largeImageText != null && !this.largeImageText.isEmpty())
                assets.put("large_text", this.largeImageText);
        }
        if (this.smallImageKey != null && !this.smallImageKey.isEmpty()) {
            assets.put("small_image", this.smallImageKey);
            if (this.smallImageText != null && !this.smallImageText.isEmpty())
                assets.put("small_text", this.smallImageText);
        }
        if (this.partyId != null) {
            party.put("id", this.partyId);
            JSONArray partyData = new JSONArray();
            if (this.partySize > 0) {
                partyData.put(Integer.valueOf(this.partySize));
                if (this.partyMax >= this.partySize)
                    partyData.put(Integer.valueOf(this.partyMax));
            }
            party.put("size", partyData);
        }
        if (this.joinSecret != null && !this.joinSecret.isEmpty())
            secrets.put("join", this.joinSecret);
        if (this.spectateSecret != null && !this.spectateSecret.isEmpty())
            secrets.put("spectate", this.spectateSecret);
        if (this.matchSecret != null && !this.matchSecret.isEmpty())
            secrets.put("match", this.matchSecret);
        if (this.state != null && !this.state.isEmpty())
            finalObject.put("state", this.state);
        if (this.details != null && !this.details.isEmpty())
            finalObject.put("details", this.details);
        if (this.buttons != null &&
                this.buttons.length > 0 && this.buttons.length < 3)
            for (RichPresenceButton button : this.buttons) {
                JSONObject jsonButton = new JSONObject();
                jsonButton.put("label", button.getLabel());
                jsonButton.put("url", button.getUrl());
                buttonArray.put(jsonButton);
            }
        if (timestamps.has("start"))
            finalObject.put("timestamps", timestamps);
        if (assets.has("large_image"))
            finalObject.put("assets", assets);
        if (party.has("id"))
            finalObject.put("party", party);
        if (secrets.has("join") || secrets.has("spectate") || secrets.has("match"))
            finalObject.put("secrets", secrets);
        if (buttonArray.length() > 0)
            finalObject.put("buttons", buttonArray);
        finalObject.put("instance", Boolean.valueOf(this.instance));
        return finalObject;
    }

    public static class Builder {
        private String state;

        private String details;

        private long startTimestamp;

        private long endTimestamp;

        private String largeImageKey;

        private String largeImageText;

        private String smallImageKey;

        private String smallImageText;

        private String partyId;

        private int partySize;

        private int partyMax;

        private String matchSecret;

        private String joinSecret;

        private String spectateSecret;

        private RichPresenceButton[] buttons;

        private boolean instance;

        public RichPresence build() {
            return new RichPresence(this.state, this.details, this.startTimestamp, this.endTimestamp, this.largeImageKey, this.largeImageText, this.smallImageKey, this.smallImageText, this.partyId, this.partySize, this.partyMax, this.matchSecret, this.joinSecret, this.spectateSecret, this.buttons, this.instance);
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        public Builder setStartTimestamp(long startTimestamp) {
            this.startTimestamp = startTimestamp;
            return this;
        }

        public Builder setEndTimestamp(long endTimestamp) {
            this.endTimestamp = endTimestamp;
            return this;
        }

        public Builder setLargeImage(String largeImageKey, String largeImageText) {
            this.largeImageKey = largeImageKey;
            this.largeImageText = largeImageText;
            return this;
        }

        public Builder setLargeImage(String largeImageKey) {
            return setLargeImage(largeImageKey, null);
        }

        public Builder setSmallImage(String smallImageKey, String smallImageText) {
            this.smallImageKey = smallImageKey;
            this.smallImageText = smallImageText;
            return this;
        }

        public Builder setSmallImage(String smallImageKey) {
            return setSmallImage(smallImageKey, null);
        }

        public Builder setParty(String partyId, int partySize, int partyMax) {
            this.partyId = partyId;
            this.partySize = partySize;
            this.partyMax = partyMax;
            return this;
        }

        public Builder setMatchSecret(String matchSecret) {
            this.matchSecret = matchSecret;
            return this;
        }

        public Builder setJoinSecret(String joinSecret) {
            this.joinSecret = joinSecret;
            return this;
        }

        public Builder setSpectateSecret(String spectateSecret) {
            this.spectateSecret = spectateSecret;
            return this;
        }

        public Builder setButtons(RichPresenceButton[] buttons) {
            this.buttons = buttons;
            return this;
        }

        public Builder setInstance(boolean instance) {
            this.instance = instance;
            return this;
        }
    }
}
