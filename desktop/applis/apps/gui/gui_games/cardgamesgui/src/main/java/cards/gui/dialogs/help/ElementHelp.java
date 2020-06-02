package cards.gui.dialogs.help;


public class ElementHelp {
    private String nom;

    private String file = "";

    public ElementHelp(String _pnom) {
        nom = _pnom;
    }

    public void ajouterInfo(String _file) {
        file = _file;
    }

    public String nom() {
        return nom;
    }

    public String getFile() {
        return file;
    }
}
