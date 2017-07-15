package cards.gui.panels;
/**
    */

public class Carpet {

    private CarpetBelote tapisBelote;

    private CarpetPresident tapisPresident;

    private CarpetTarot tapisTarot;

    public void setTapisTarot(CarpetTarot _tapisTarot) {
        supprimerTapis();
        tapisTarot = _tapisTarot;
    }

    public void setTapisPresident(CarpetPresident _tapisPresident) {
        supprimerTapis();
        tapisPresident = _tapisPresident;
    }

    public void setTapisBelote(CarpetBelote _tapisBelote) {
        supprimerTapis();
        tapisBelote = _tapisBelote;
    }

    private void supprimerTapis() {
        tapisBelote = null;
        tapisPresident = null;
        tapisTarot = null;
    }

    public CarpetTarot getTapisTarot() {
        return tapisTarot;
    }

    public CarpetPresident getTapisPresident() {
        return tapisPresident;
    }

    public CarpetBelote getTapisBelote() {
        return tapisBelote;
    }
}
