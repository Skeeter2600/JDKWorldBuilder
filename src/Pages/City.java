package Pages;

import java.util.Base64;
import java.util.List;

public class City{

    private final String name;
    private int population;
    private String song;
    private final String aesthetic;
    private boolean revealed;
    private final String revealCode;
    private String notes;

    private final String[] trade;
    private final List<NPC> residents;
    private final List<Special> specials;

    /**
     * This will create a new instance of a city using
     * provided inputs
     */
    public City(String name, int population, String[] trade, List<NPC> residents,
                String song, String aesthetic, List<Special> specials, boolean revealed, String revealCode, String notes){
        this.name = name;
        this.population = population;
        this.trade = trade;
        this.residents = residents;
        this.song = song;
        this.aesthetic = aesthetic;
        this.specials = specials;
        this.revealed = revealed;
        this.revealCode = revealCode;
        this.notes = notes;
    }

    /**
     * This function will get the name of the city
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * This function will get the population of the city
     * @return the population
     */
    public int getPopulation(){
        return population;
    }

    /**
     * This function will update the population
     * @param newPopulation the new population value
     */
    public void setPopulation(int newPopulation){
        population = newPopulation;
    }

    /**
     * This function will return the trades of the city
     * @return the trades of the city
     */
    public String[] getTrade(){
        return trade;
    }

//    /**
//     * This function will add a trade to the city
//     */
//    public void addTrade(String newTrade){ trade.;
//    }

//    /**
//     * This function will remove a trade from the city
//     */
//    public void removeTrade(String oldTrade){
//        trade.remove(oldTrade);
//    }

    /**
     * Get the song for the city
     * @return the song
     */
    public String getSong(){
        return song;
    }

    /**
     * Set a new song for the city
     */
    public void setSong(String newSong){
        song = newSong;
    }

    /**
     * Get teh aesthetic for a city
     * @return the aesthetic description
     */
    public String getAesthetic(){
        return aesthetic;
    }

    /**
     * This will get a list of all the NPCs living in the city
     * @return the list of NPCs
     */
    public List<NPC> getResidents(){
        return residents;
    }

    /**
     * This will add a new resident to the city's list
     * @param newResident the resident to be added
     */
    public void addResident(NPC newResident){
        residents.add(newResident);
    }

    /**
     * This will remove a resident from the city
     * @param oldResident the resident to be removed
     */
    public void removeResident(NPC oldResident){
        residents.remove(oldResident);
    }

    public List<Special> getSpecials(){
        return specials;
    }

    /**
     * This will add a new special to the city's list
     * @param newSpecial the resident to be added
     */
    public void addSpecial(Special newSpecial){
        specials.add(newSpecial);
    }

    /**
     * This will remove a resident from the city
     * @param oldSpecial the resident to be removed
     */
    public void removeSpecial(Special oldSpecial){
        specials.remove(oldSpecial);
    }

    public void meetCity(){
        revealed = true;
    }

    public boolean getRevealed(){
        return revealed;
    }

    public String getRevealCode(){
        return revealCode;
    }

    public String getNotes() { return notes; }

    public void setNotes(String newNotes) { this.notes = newNotes; }

    public String writeCity(){
        return Base64.getEncoder().encodeToString(("City _-_ " + name + " _-_ " + population +
                " _-_ " + trade + " _-_ " + residents + " _-_ " + song + " _-_ " + aesthetic + " _-_ " +
                specials + " _-_ " + revealed + " _-_ " + revealCode + " _-_ " + notes).getBytes());
    }
}
