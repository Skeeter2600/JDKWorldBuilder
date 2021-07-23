package Pages;

import java.util.Base64;

public class Special{

    private final String name;
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

    /**
     * This will return the special's name
     * @return the name
     */
    public String getName(){
        return name;
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
     * This will return if the special has been met
     * @return true if met, false if not
     */
    public boolean getMet(){
        return met;
    }

    /**
     * This will update if an special has been met
     * @return a string signifying the result
     */
    public String meetSpecial(){
        if(met = false){
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

    public String writeSpecial(){
        String encodedString;
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