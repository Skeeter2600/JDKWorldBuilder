package Work_Classes;

import Components.City;
import Components.NPC;
import Basic_Classes.Password;
import Components.Special;
import Components.WorldElement;

import java.io.*;
import java.util.*;

public class FileProcessor {

    private final File mainFile;
    private HashSet<WorldElement> components;
    private boolean admin;
    private final Password password;

    public FileProcessor(File mainFile, boolean admin, Password password) {
        this.mainFile = mainFile;
        this.components = new HashSet<>();
        this.admin = admin;
        this.password = password;
    }

    public String getName(){
        return mainFile.getName();
    }

    /**
     * getNPCList returns an ArrayList consisting of every NPC in the file
     * 
     * @return ArrayList of NPCs
     */
    public HashSet<WorldElement> getSelectedList(String type) {
    	HashSet<WorldElement> outcomeList = new HashSet<>();

    	if (type.equals("NPC")) {
            for (WorldElement o : components) {
                if (o.getClass() == NPC.class) {
                    outcomeList.add(o);
                    System.out.println("added NPC to outcomeList");
                }
            }
        }
        if (type.equals("City")) {
            for (WorldElement o : components) {
                if (o.getClass() == City.class) {
                    outcomeList.add(o);
                    System.out.println("added City to outcomeList");
                }
            }
        }
        if (type.equals("Special")) {
            for (WorldElement o : components) {
                if (o.getClass() == Special.class) {
                    outcomeList.add(o);
                    System.out.println("added Special to outcomeList");
                }
            }
        }
        return outcomeList;
    }

    /**
     * This function will read and decode the file provided and
     * return the system in a HashSet List
     * @param file the file to be processes
     * @return The HashList of Objects
     */
    public HashSet<WorldElement> readFile(File file) {
        BufferedReader reader;
        components = new HashSet<>();
        try {
            reader = new BufferedReader(new
                    FileReader(file));
            boolean tempAdmin = admin;
            admin = true;
            String line = reader.readLine();
            for (int lineReader = 0; line != null; lineReader++) {

                if(lineReader == 0){
                    byte[] decodedBytes = Base64.getDecoder().decode(line);
                    line = new String(decodedBytes);
                    components.add(new Password(line));
                }
                else {
                    if (!line.equals("")) {
                        String outcome = addComponent(line);
                        if (outcome.equals("Not a valid object type.")) {
                            System.out.println("An error occurred when building " + line.split(" _-_ ")[1] +
                                    ". Please make this element again.");
                        }
                    }
                }
                line = reader.readLine();
            }
            admin = tempAdmin;
            System.out.println("World successfully built!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return components;
    }

    /**
     * This function will add a components to the group of
     * other components
     *
     * @param line the component to be added
     * @return true if added, false if exists already
     */
    public String addComponent(String line) {

        // if not in admin mode, don't edit
        if (!admin) return "not admin";
        String revealCode;

        // decode the string
        byte[] decodedBytes = Base64.getDecoder().decode(line);
        line = new String(decodedBytes);
        String[] splitLine = line.split(" _-_ ");
        // check the string
        switch (splitLine[0]) {
            case ("City"):
                // get name
                String cityName = splitLine[1];

                // get population
                String population = splitLine[2];

                // get trades
                String trades = splitLine[3];

                // get NPCs
                String[] tempNPC = splitLine[4].split(" ... ");
                List<String> residents = new ArrayList<>();
                Collections.addAll(residents, tempNPC);
                
                // get the song
                String song = splitLine[5];

                // get the aesthetic description
                String aesthetic = splitLine[6];

                // get the specials
                String[] specialsTemp = splitLine[7].split(" ... ");
                List<String> specials = new ArrayList<>();
                Collections.addAll(specials, specialsTemp);

                boolean revealed = splitLine[8].equals("true");

                revealCode = splitLine[9];

                String notes = splitLine[10];
                
                // add to the components
                components.add(new City(cityName, population, trades , residents, song, aesthetic, specials, revealed, revealCode, notes));
                return cityName + " added";

            case ("NPC"):
                // get name
                String NPCName = splitLine[1];

                // get occupation
                String occupation = splitLine[2];

                // get description
                String description = splitLine[3];

                revealed = splitLine[4].equals("true");

                revealCode = splitLine[5];

                String NPCNotes = splitLine[6];

                if (splitLine.length == 8) {
                    String hiddenDescription = splitLine[5];
                    revealCode = splitLine[6];
                    NPCNotes = splitLine[7];
                    assert false;
                    components.add(new NPC(NPCName, occupation, description, hiddenDescription, revealed, revealCode, NPCNotes));
                } else {
                    // add to the components
                    assert false;
                    components.add(new NPC(NPCName, occupation, description, revealed, revealCode,NPCNotes));
                }
                return NPCName + " added";

            case ("Special"):
                // get name
                String specialName = splitLine[1];

                // get description
                String specialDescription = splitLine[2];

                revealed = splitLine[3].equals("true");

                revealCode = line.split(" _-_")[4];

                String SpecialNotes = splitLine[5];

                if (splitLine.length == 7) {
                    String hiddenDescription = splitLine[3];
                    revealed = splitLine[4].equals("true");
                    revealCode = splitLine[5];
                    SpecialNotes = splitLine[6];
                    assert false;
                    components.add(new Special(specialName, specialDescription, hiddenDescription, revealed, revealCode, SpecialNotes));
                } else {
                    // add to the components
                    assert false;
                    components.add(new Special(specialName, specialDescription, revealed, revealCode, SpecialNotes));
                }
                return specialName + " added";

            default:
                assert false;
        }
        return "Not a valid object type.";
    }

    /**
     * This function will add a component to the list of objects
     * while the system is running
     * @param addedComponent the component to be added
     */
    public void addComponent(WorldElement addedComponent){
        if (addedComponent.getClass() == NPC.class || addedComponent.getClass() == City.class
                || addedComponent.getClass() == Special.class) {
            components.add(addedComponent);
        }
        else { System.out.println(addedComponent + "not a valid system"); }
    }

    public void updateComponent(WorldElement component){
        components.remove(component);
        components.add(component);
    }

    /**
     * This function will write the list of components back
     * to the chosen file upon close
     */
    public void writeFile() {
        String encodedString;
        try (Writer writer = new FileWriter(mainFile)) {
            encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
            writer.write(encodedString);
            writer.flush();
            for (WorldElement part : components) {
                if(part.getClass() != Password.class) {
                    writer.write("\n");
                    writer.write(part.writeWorldElement());
                    System.out.println("Saved World Element");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}