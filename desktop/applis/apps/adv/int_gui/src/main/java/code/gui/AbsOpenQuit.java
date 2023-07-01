package code.gui;

public interface AbsOpenQuit extends AbsGroupFrame {
    String getApplicationName();
    void changeLanguage(String _language);
    void quit();
}
