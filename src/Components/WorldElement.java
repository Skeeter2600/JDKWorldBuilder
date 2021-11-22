package Components;

public interface WorldElement {

    String getName();
    void setName(String newName);
    String getDescription();
    void setDescription(String newDescription);
    String getHiddenDescription();
    void setHiddenDescription(String newHiddenDescription);
    void revealHiddenDescription();
    void addHiddenDescription(String newHiddenDescription);
    String meetWorldElement();
    boolean getRevealed();
    String getRevealCode();
    String getNotes();
    void setNotes(String newNotes);
    String writeWorldElement();
}
