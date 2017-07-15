package cards.president.enumerations;
import code.format.Format;
import code.util.consts.Constants;

public enum EqualtyPlaying {
    FORBIDDEN,
    SKIP_ALWAYS_NEXT,
    SKIP_DIFF_NEXT_STOP,
    NO_SKIP;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.PRESIDENT_EQUAL_PLAY,this);
    }

}
