package Basic_Classes;

public class Map {

    private final Sector[][] layout;

    /**
     * This will create a new map
     * @param length this is the x component of the grid
     * @param width this is the y component of the grid
     */
    public Map(int length, int width){
        Sector defaultImage = new Sector("JPGs/BadBoi.jpg");
        this.layout = null;
        for(int l=0; l<length; l++){
            for (int w=0; w<width; w++){
                assert false;
                layout[l][w] = defaultImage;
            }
        }
    }

    /**
     * This will add a sector to the map
     * @param sector the image to be added
     * @param x this is the x component of the grid
     * @param y this is the y component of the grid
     * @return true if added
     */
    public boolean addSector(String sector, int x, int y){
        assert layout != null;
        if(!(layout[x][y].equals(new Sector(sector)))){
            layout[x][y] = new Sector(sector);
            return true;
        }
        return false;
    }

    /**
     * This function will reveal a sector on the map
     * @param x this is the x component of the grid
     * @param y this is the y component of the grid
     * @param revealCode the code to reveal the component
     * @return true if revealed
     */
    public boolean revealSector(int x, int y, int revealCode){
        assert layout != null;
        return layout[x][y].reveal(revealCode);
    }

    /**
     * This will get the sector on the map
     * @param x this is the x component of the grid
     * @param y this is the y component of the grid
     * @return the sector
     */
    public Sector getSector(int x, int y){
        assert layout != null;
        return layout[x][y];
    }
}