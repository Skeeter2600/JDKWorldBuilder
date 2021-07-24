package Work_Classes;

import Pages.City;
import Pages.NPC;
import Basic_Classes.Password;
import Pages.Special;

import java.io.*;
import java.util.*;

public class FileProcessor {

    private final File mainFile;
    private final HashSet<Object> components;
    private boolean admin;
    private final Password password;

    public FileProcessor(File mainFile, boolean admin, Password password) {
        this.mainFile = mainFile;
        this.components = new HashSet<>();
        this.admin = admin;
        this.password = password;
    }

    public HashSet<Object> readFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
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
                    String outcome = addComponent(line);
                    if (outcome.equals("Not a valid object type.")) {
                        System.out.println("An error occurred when building " + line.split(" _-_ ")[1] +
                                ". Please make this element again.");
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
                components.add(new Error("Bad line"));
        }
        return "Not a valid object type.";
    }

    public void writeFile() {
        String encodedString;
        try (Writer writer = new FileWriter(mainFile)) {
            NPC tempNPC = new NPC("Paul Blart", "checker", "fat", true,"12345","");
            City tempCity = new City("testville", "1.3 mil.", "Pharmaceuticals", null, "never gonna give you up",
                    "ballsy", null, false, "12345", "");
            Special tempSpecial = new Special("Capitol Building", "tester", true, "12345","");
            encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
            writer.write(encodedString);
            writer.flush();
            for (Object part : components) {
                // write an NPC
                if (part.getClass() == tempNPC.getClass()) {
                    writer.write("\n");
                	writer.write(((NPC) part).writeNPC());
                	System.out.println("Saved NPC");
                }
                // write a city
                else if (part.getClass() == tempCity.getClass()) {
                	writer.write("\n");
                	writer.write(((City) part).writeCity());
                	System.out.println("Saved City");
                }
                // write a Special
                else if (part.getClass() == tempSpecial.getClass()) {
                	writer.write("\n");
                	writer.write(((Special) part).writeSpecial());
                	System.out.println("Saved Special");
                } else {
                    System.out.println(part.toString() + "not a valid system");
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}