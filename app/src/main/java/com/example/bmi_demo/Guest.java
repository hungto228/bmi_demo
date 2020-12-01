package com.example.bmi_demo;


public class Guest {
    private int GuestId;
    private String GuestName;
    private String GuestBirth;
    private String GuestGender;
    private String GuestMoblie;

    public Guest() {
    }

    public Guest(int guestId, String guestName, String guestBirth, String guestGender, String guestMoblie) {
        GuestId = guestId;
        GuestName = guestName;
        GuestBirth = guestBirth;
        GuestGender = guestGender;
        GuestMoblie = guestMoblie;
    }

    public int getGuestId() {
        return GuestId;
    }

    public void setGuestId(int guestId) {
        GuestId = guestId;
    }

    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getGuestBirth() {
        return GuestBirth;
    }

    public void setGuestBirth(String guestBirth) {
        GuestBirth = guestBirth;
    }

    public String getGuestGender() {
        return GuestGender;
    }

    public void setGuestGender(String guestGender) {
        GuestGender = guestGender;
    }

    public String getGuestMoblie() {
        return GuestMoblie;
    }

    public void setGuestMoblie(String guestMoblie) {
        GuestMoblie = guestMoblie;
    }
}
