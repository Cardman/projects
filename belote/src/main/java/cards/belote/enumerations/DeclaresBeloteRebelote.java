package cards.belote.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum DeclaresBeloteRebelote implements Displayable {
    BELOTE_REBELOTE(20);
    private final int points;

    DeclaresBeloteRebelote(int _points) {
        points = _points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_DECLARES_BEL_REB, name());
    }
}

