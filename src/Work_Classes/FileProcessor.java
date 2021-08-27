package Work_Classes;

import Pages.City;
import Pages.NPC;
import Basic_Classes.Password;
import Pages.Special;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileProcessor {

    private final File mainFile;
    private HashSet<Object> components;
    private boolean admin;
    private final Password password;

    public FileProcessor(File mainFile, boolean admin, Password password) {
        this.mainFile = mainFile;
        this.components = new HashSet<>();
        this.admin = admin;
        this.password = password;
    }

    /**
     * getNPCList returns an ArrayList consisting of every NPC in the file
     * 
     * @return ArrayList of NPCs
     */
    public ArrayList<NPC> getNPCList() {
    	ArrayList<NPC> NPCList = new ArrayList<NPC>();
    	HashSet<Object> ObjectList = readFile(mainFile);
    	NPC tempNPC = new NPC("Paul Blart", "checker", "fat", true,"12345","");
    	
    	for (Object o : ObjectList) {
    		if (o.getClass() == tempNPC.getClass()) {
    			NPCList.add((NPC) o);
    			System.out.println("added NPC to NPCList");
    		}
    	}
    	return NPCList;
    }
    
    /**
     * getCityList returns an ArrayList consisting of every City in the file
     * 
     * @return ArrayList of Cities
     */
    public ArrayList<City> getCityList() {
    	ArrayList<City> cityList = new ArrayList<City>();
    	HashSet<Object> ObjectList = readFile(mainFile);

    	City tempCity = new City("Paul Blart", "checker", "fat", null, null, null, null, true,"12345","");
    	for (Object o : ObjectList) {
    		if (o.getClass() == tempCity.getClass()) {
    			cityList.add((City) o);
    			System.out.println("added City to cityList");
    		}
    	}
    	return cityList;
    }
    
    /**
     * getSpecialList returns an ArrayList consisting of every Special in the file
     * 
     * @return ArrayList of Specials
     */
    public ArrayList<Special> getSpecialList() {
    	ArrayList<Special> specialList = new ArrayList<Special>();
    	HashSet<Object> ObjectList = readFile(mainFile);
    	System.out.println(ObjectList.toString());
    	Special tempSpecial = new Special("Paul Blart", "checker", "fat", true,"12345","");
    	
    	for (Object o : ObjectList) {
    		if (o.getClass() == tempSpecial.getClass()) {
    			specialList.add((Special) o);
    			System.out.println("added Special to specialList");
    		}
    	}
    	return specialList;
    }

    /**
     * This function will read and decode the file provided and
     * return the system in a HashSet List
     * @param file the file to be processes
     * @return The HashList of Objects
     */
    public HashSet<Object> readFile(File file) {
        BufferedReader reader;
        components = new HashSet<Object>();
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
     * This function will zip a specified directory
     * @param directory the directory of the to be zipped file
     * @return true if succeeded, false if failed
     * @throws FileNotFoundException if file isn't found
     */
    public boolean zipFile(File directory) throws FileNotFoundException {
        String sourceFile = directory.getName();
        String name = sourceFile.substring(0,sourceFile.length()-4);
        String userHomeFolder = System.getProperty("user.home");
        FileOutputStream fos = new FileOutputStream(userHomeFolder + "/" + name+ ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);

        try {
            zipFileWorking(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
        } catch (IOException e) { return false; }
        return true;
    }

    /**
     * This function will zip a file and send it to the desktop
     * @param fileToZip the file to be zipped
     * @param fileName the name of the zip file
     * @param zipOut the destination of the file
     * @throws IOException if files don't exist
     */
    private static void zipFileWorking(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            assert children != null;
            for (File childFile : children) {
                zipFileWorking(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    /**
     * This function is used to extract the contents
     * of a provided zip file
     * @param zipFile the file to be unzipped
     * @return true if succeeded, false if fail
     */
    public boolean unzipFile(File zipFile) throws IOException {
        String name = zipFile.getName();
        File destDir = new File(name);
        boolean directoryMake = destDir.mkdirs();
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = new File(name + "/" + zipEntry.getName());
            if (zipEntry.getName().equals("maps")) {
                directoryMake  = newFile.mkdirs();
            } else if (zipEntry.getName().equals("images")){
                directoryMake  = newFile.mkdirs();
            }else{
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) { return false; }
                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) { fos.write(buffer, 0, len); }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return true;
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

    /**
     * This function will add a component to the list of objects
     * while the system is running
     * @param addedComponent the component to be added
     */
    public void addComponent(Object addedComponent){
        NPC tempNPC = new NPC("Paul Blart", "checker", "fat", true,"12345","");
        City tempCity = new City("testville", "1.3 mil.", "Pharmaceuticals", null, "never gonna give you up",
                "dope", null, false, "12345", "");
        Special tempSpecial = new Special("Capitol Building", "tester", true, "12345","");
        if (addedComponent.getClass() == tempNPC.getClass() || addedComponent.getClass() == tempCity.getClass()
                || addedComponent.getClass() == tempSpecial.getClass()) {
            components.add(addedComponent);
        }
        else { System.out.println(addedComponent.toString() + "not a valid system"); }
    }

    /**
     * This function will write the list of components back
     * to the chosen file upon close
     */
    public void writeFile() {
        String encodedString;
        try (Writer writer = new FileWriter(mainFile)) {
            NPC tempNPC = new NPC("Paul Blart", "checker", "fat", true,"12345","");
            City tempCity = new City("testville", "1.3 mil.", "Pharmaceuticals", null, "never gonna give you up",
                    "ballsy", null, false, "12345", "");
            Special tempSpecial = new Special("Capitol Building", "tester", true, "12345","");
            Password tempPassword = new Password("testtest");
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
                } else if (part.getClass() == tempPassword.getClass()){
                    System.out.println("Password Skipped");
                } else {
                    System.out.println(part.toString() + "Not a valid class");
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}