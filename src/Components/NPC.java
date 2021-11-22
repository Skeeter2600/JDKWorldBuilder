package Components;

import java.util.Base64;

public class NPC implements WorldElement{

    private String name;
    private final String occupation;
    private String description;
    private String hiddenDescription;
    private final String revealCode;
    private boolean revealed;
    private String notes;

    private boolean hiddenDescriptionReveal;

    /**
     * This creates a new instance of an Classes.NPC (no hidden description)
     * @param name name of Classes.NPC
     * @param occupation their position
     * @param description description of Classes.NPC
     */
    public NPC(String name, String occupation, String description, boolean revealed, String revealCode, String notes){
        this.name = name;
        this.occupation = occupation;
        this.description = description;

        hiddenDescriptionReveal = true;
        this.revealCode = revealCode;
        this.revealed = revealed;

        this.notes = notes;
    }

    /**
     *  This creates a new instance of an Classes.NPC (w/ hidden description)
     * @param name name of Classes.NPC
     * @param occupation their position
     * @param description description of Classes.NPC
     * @param hiddenDescription description added to main.main later on
     */
    public NPC(String name, String occupation, String description, String hiddenDescription, boolean revealed, String revealCode, String notes){
        this.name = name;
        this.occupation = occupation;
        this.description = description;
        this.hiddenDescription = hiddenDescription;
        hiddenDescriptionReveal = false;

        this.revealed = revealed;
        this.revealCode = revealCode;
        this.notes = notes;
    }

    /**
     * This will return the NPC's name
     * @return the name
     */
    public String getName(){
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    /**
     * This will get the NPC's occupation
     * @return the occupation
     */
    public String getOccupation(){
        return occupation;
    }

    /**
     * This will get the NPC's description
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    @Override
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public String getHiddenDescription(){
        return hiddenDescription;
    }

    @Override
    public void setHiddenDescription(String newHiddenDescription) {
        hiddenDescription = newHiddenDescription;
    }

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
     * This will return if the NPC has been met
     * @return true if met, false if not
     */
    public boolean getRevealed(){
        return revealed;
    }

    /**
     * This will update if an Classes.NPC has been met
     * @return a string signifying the result
     */
    public String meetWorldElement(){
        if(!revealed){
            revealed = true;
            return "Met " + name + "!";
        }else{
            return "Already me " + name + "!";
        }
    }

    public String getRevealCode(){
        return revealCode;
    }

    public String getNotes() { return notes;}

    public void setNotes(String newNotes) { notes = newNotes;}

    public String writeWorldElement(){
        String encodedString;
        if(notes.equals("")){
           notes = " ";
        }
        if (hiddenDescription == null) {
            encodedString = Base64.getEncoder().encodeToString(("NPC _-_ " + name + " _-_ " + occupation +
                    " _-_ " + description + " _-_ " + revealed + " _-_ " + revealCode + " _-_ " + notes).getBytes());
        } else {
            encodedString = Base64.getEncoder().encodeToString(("NPC _-_ " + name + " _-_ " + occupation +
                    " _-_ " + description  + " _-_ " + hiddenDescription  + " _-_ " + revealed + " _-_ " + revealCode + " _-_ " + notes).getBytes());
        }
        return encodedString;
    }
}