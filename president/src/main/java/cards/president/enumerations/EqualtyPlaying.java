package cards.president.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum EqualtyPlaying implements Displayable {
    FORBIDDEN,
    SKIP_ALWAYS_NEXT,
    SKIP_DIFF_NEXT_STOP,
    NO_SKIP;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.PRESIDENT_EQUAL_PLAY,name());
    }

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

}
