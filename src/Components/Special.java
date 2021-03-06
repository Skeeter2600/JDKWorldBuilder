package Components;

import java.util.Base64;

public class Special implements WorldElement{

    private String name;
    private String description;
    private String hiddenDescription;
    private final String revealCode;
    private final boolean revealed;
    private String notes;

    private boolean hiddenDescriptionReveal;
    private boolean met;

    public Special(String name, String description, boolean revealed, String revealCode, String notes){
        this.name = name;
        this.description = description;
        this.hiddenDescription = null;

        hiddenDescriptionReveal = true;
        met = false;

        this.revealed = revealed;
        this.revealCode = revealCode;
        this.notes = notes;
    }

    public Special(String name, String description, String hiddenDescription, boolean revealed, String revealCode, String notes){
        this.name = name;
        this.description = description;
        this.hiddenDescription = hiddenDescription;

        hiddenDescriptionReveal = true;
        met = false;

        this.revealed = revealed;
        this.revealCode = revealCode;
        this.notes = notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHiddenDescription(String hiddenDescription) {
        this.hiddenDescription = hiddenDescription;
    }

    public void setHiddenDescriptionReveal(boolean hiddenDescriptionReveal) {
        this.hiddenDescriptionReveal = hiddenDescriptionReveal;
    }

    public void setMet(boolean met) {
        this.met = met;
    }

    /**
     * This will return the special's name
     * @return the name
     */
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This will get the special's description
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    public String getHiddenDescription(){ return hiddenDescription;}

    /**
     * This will reveal the hidden description of a character
     */
    public void revealHiddenDescription(){
        description = description + "\n \n" + hiddenDescription;
        hiddenDescriptionReveal = true;
    }

    /**
     * This will add a new hidden description
     * @param newHiddenDescription the new hidden description
     */
    public void addHiddenDescription(String newHiddenDescription){
        if(hiddenDescription == null){
            hiddenDescription = newHiddenDescription;
        }else{
            hiddenDescription = hiddenDescription + "\n \n" + newHiddenDescription;
        }
        hiddenDescriptionReveal = false;
    }

    /**
     * This will update if an special has been met
     * @return a string signifying the result
     */
    public String meetWorldElement(){
        if(!met){
            met = true;
            return "Met " + name + "!";
        }else{
            return "Already me " + name + "!";
        }
    }

    public boolean getRevealed(){
        return revealed;
    }

    public String getRevealCode(){
        return revealCode;
    }

    public String getNotes() {return notes;}

    public void setNotes(String newNotes) {notes = newNotes;}

    public String writeWorldElement(){
        if(notes.equals("")){
           notes = " ";
        }
        if (hiddenDescription == null) {
            return Base64.getEncoder().encodeToString(("Special _-_ " + name + " _-_ " +
                    description + " _-_ " + revealed + " _-_ " + revealCode + " _-_ " + notes).getBytes());
        } else {
            return Base64.getEncoder().encodeToString(("Special _-_ " + name + " _-_ " + description +
                    " _-_ " + hiddenDescription + " _-_ " + revealed + " _-_ " + revealCode + " _-_ " + notes).getBytes());
        }
    }
}