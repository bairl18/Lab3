package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author Linnea Bair, Kelson Sipe
 * @version 9/16/15
 *
 */
public class SoccerDatabase implements SoccerDB {

    private Hashtable<String, SoccerPlayer> stats = new Hashtable<>();

    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {

        if(stats.containsKey(firstName + lastName)) {

            return false;

        }
        else{

            SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);

            stats.put(firstName + lastName, player);

            return true;

        }
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.remove(firstName + lastName);
            return true;

        }

        else{

            return false;

        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            return stats.get(firstName + lastName);

        }

        else{

            return null;

        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpGoals();

            return true;

        }

        else{

            return false;

        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpAssists();

            return true;

        }

        else{

            return false;

        }

    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpShots();

            return true;

        }

        else{

            return false;

        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpSaves();

            return true;

        }

        else{

            return false;

        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpFouls();

            return true;

        }

        else{

            return false;

        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpYellowCards();

            return true;

        }

        else{

            return false;

        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {

        if(stats.containsKey(firstName + lastName)) {

            stats.get(firstName + lastName).bumpRedCards();

            return true;

        }

        else{

            return false;

        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {

        int numPlayers = 0;


       if (teamName == null) {

            for (int i = 0; i < (stats.keySet()).toArray().length; i++) {

               numPlayers++;

            }

            return numPlayers;

        }

        else {

           Collection<SoccerPlayer> soccerPlayers = stats.values();

            for (SoccerPlayer sp: soccerPlayers) {

                if (sp.getTeamName().equals(teamName)){

                    numPlayers++;
                }
            }

            return numPlayers;

        }


	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        Collection<SoccerPlayer> soccerPlayers = stats.values();

        if (teamName == null){

            int count = 0;

            for (SoccerPlayer sp: soccerPlayers){

                if(count == idx){

                    return sp;

                }
                else{

                    count++;

                }
            }
        }

        else {

            int count = -1;

            for (SoccerPlayer sp: soccerPlayers){

                if(sp.getTeamName().equals(teamName)){

                    count++;

                    if(count == idx){

                        return sp;

                    }
                }
            }
        }

        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {

        Collection<SoccerPlayer> soccerPlayers = stats.values();



        try {

            PrintWriter writer = new PrintWriter(file);

            for (SoccerPlayer sp : soccerPlayers) {

                writer.println(logString(sp.getFirstName()));
                writer.println(logString(sp.getLastName()));
                writer.println(logString(sp.getTeamName()));
                writer.println(logString(sp.getUniform()+""));
                writer.println(logString(sp.getGoals() + ""));
                writer.println(logString(sp.getAssists() + ""));
                writer.println(logString(sp.getShots() + ""));
                writer.println(logString(sp.getSaves() + ""));
                writer.println(logString(sp.getFouls() + ""));
                writer.println(logString(sp.getYellowCards() + ""));
                writer.println(logString(sp.getRedCards() + ""));

            }

            writer.close();

            return true;

        }

        catch(java.io.FileNotFoundException ex) {

            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
