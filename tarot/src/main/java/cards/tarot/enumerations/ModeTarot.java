package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;

public enum ModeTarot {
NORMAL,NORMAL_WITH_MISERE,NORMAL_WITH_ONE_FOR_ONE,MISERE,ONE_FOR_ONE;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_MODE, this);
    }
}
