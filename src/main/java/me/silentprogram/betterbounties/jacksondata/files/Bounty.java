package me.silentprogram.betterbounties.jacksondata.files;

import java.util.UUID;

public class Bounty {
	private UUID playerUUID;
	private int prize;
	private String issuer = "Console";
	private boolean isConsole = true;
	
	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public int getPrize() {
		return prize;
	}
	
	public void setPrize(int prize) {
		this.prize = prize;
	}
	
	public UUID getPlayerUUID() {
		return playerUUID;
	}
	
	public void setPlayerUUID(UUID playerUUID) {
		this.playerUUID = playerUUID;
	}
	
	
}
