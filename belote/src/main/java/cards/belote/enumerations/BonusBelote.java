package cards.belote.enumerations;
import code.format.Format;

public enum BonusBelote {
    LAST_TRICK(10);
    private final int points;

    BonusBelote(int _points) {
        points = _points;
    }

    public int getPoints() {
        return points;
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_BONUS,name());
    }

}
