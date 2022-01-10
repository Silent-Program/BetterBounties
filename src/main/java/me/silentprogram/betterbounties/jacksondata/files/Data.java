package me.silentprogram.betterbounties.jacksondata.files;

import java.util.*;

public class Data {
	//On every kill, check if player has claimed bounty in playerBountyMap if they do, loop through claimed bounties and check if the person killed was a bounty
	private Map<UUID, Bounty> activeBountyMap = new HashMap<>();
	
	private Map<UUID, List<UUID>> playerBountyMap = new HashMap<>();
	
	public void claimBounty(UUID playerUUID, UUID bountyUUID) {
		playerBountyMap.get(playerUUID).add(bountyUUID);
	}
	
	public List<Bounty> getBounties(UUID playerUUID) {
		List<Bounty> bounties = new ArrayList<>();
		for (UUID i : playerBountyMap.get(playerUUID)) {
			if (!activeBountyMap.containsKey(i)) {
				removeBountyFromAll(i);
				continue;
			}
			bounties.add(activeBountyMap.get(i));
		}
		return bounties;
	}
	
	public void removeBountyFromAll(UUID bountyUUID) {
		activeBountyMap.remove(bountyUUID);
		for (UUID i : playerBountyMap.keySet()) {
			for (UUID bounty : playerBountyMap.get(i)) {
				if (!bounty.equals(bountyUUID)) continue;
				playerBountyMap.get(i).remove(bountyUUID);
			}
		}
	}
	
	public void createBounty(){
		Bounty bounty = new Bounty();
	}
}
