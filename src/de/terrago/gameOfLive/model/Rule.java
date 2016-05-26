package de.terrago.gameOfLive.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rule implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Integer> spawn = new ArrayList<Integer>();
	private List<Integer> stayAlife = new ArrayList<Integer>();

	public Rule(List<Integer> stayAlife, List<Integer> spawn) {
		this.stayAlife = stayAlife;
		this.spawn = spawn;
	}

	public Rule(String stRule) {
		String stayAlife = stRule.substring(0, stRule.indexOf('/', 0));
		String spawn = stRule.substring(stRule.indexOf('/', 0) + 1, stRule.length());
		setRules(stayAlife, spawn);
	}

	public Rule(String stayAlife, String spawn) {
		setRules(stayAlife, spawn);
	}

	public boolean getIsALife(boolean isAlife, Integer numberOfNeighbors) {
		if (isAlife && stayAlife.contains(numberOfNeighbors))
			return true;
		if (!isAlife && spawn.contains(numberOfNeighbors))
			return true;
		return false;
	}

	public List<Integer> getSpawn() {
		return spawn;
	}

	public List<Integer> getStayAlife() {
		return stayAlife;
	}

	private void setRules(String stayAlife, String spawn) {
		char[] chStayAlife = stayAlife.toCharArray();
		for (char c : chStayAlife) {
			Integer i = Integer.parseInt(String.valueOf(c));
			this.stayAlife.add(i);
		}
		char[] chSpawn = spawn.toCharArray();
		for (char c : chSpawn) {
			Integer i = Integer.parseInt(String.valueOf(c));
			this.spawn.add(i);
		}
	}

	public void setSpawn(List<Integer> spawn) {
		this.spawn = spawn;
	}

	public void setStayAlife(List<Integer> stayAlife) {
		this.stayAlife = stayAlife;
	}

	@Override
	public String toString() {
		String result = "";
		for (Integer i : stayAlife)
			result = result.concat(i.toString());
		result = result.concat("/");
		for (Integer i : spawn)
			result = result.concat(i.toString());
		return result;
	}

}
