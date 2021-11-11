package Components;

public interface WorldElement {

    String getName();
    String getDescription();
    String getHiddenDescription();
    void revealHiddenDescription();
    void addHiddenDescription(String newHiddenDescription);
    String meetWorldElement();
    boolean getRevealed();
    String getRevealCode();
    String getNotes();
    void setNotes(String newNotes);
    String writeWorldElement();
}
