package cards.belote.enumerations;
import code.format.Format;

public enum DeclaresBeloteRebelote {
    BELOTE_REBELOTE(20);
    private final int points;

    DeclaresBeloteRebelote(int _points) {
        points = _points;
    }

    public int getPoints() {
        return points;
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_DECLARES_BEL_REB, name());
    }
}

