package Basic_Classes;

import Components.WorldElement;

public class Password implements WorldElement {

    private final String password;

    public Password(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String getName() { return null; }

    @Override
    public String getDescription() { return null; }

    @Override
    public String getHiddenDescription() { return null; }

    @Override
    public void revealHiddenDescription() { }

    @Override
    public void addHiddenDescription(String newHiddenDescription) { }

    @Override
    public String meetWorldElement() { return null; }

    @Override
    public boolean getRevealed() { return false; }

    @Override
    public String getRevealCode() { return null; }

    @Override
    public String getNotes() { return null; }

    @Override
    public void setNotes(String newNotes) { }

    @Override
    public String writeWorldElement() { return null; }
}
