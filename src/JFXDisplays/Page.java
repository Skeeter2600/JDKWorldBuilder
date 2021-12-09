package JFXDisplays;

public interface Page {

    void loadPage();
    void reloadPage();
    void loadLast();
    Page getPrevious();
}
