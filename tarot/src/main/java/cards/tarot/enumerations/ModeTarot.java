package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum ModeTarot implements Displayable {
NORMAL,NORMAL_WITH_MISERE,NORMAL_WITH_ONE_FOR_ONE,MISERE,ONE_FOR_ONE;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_MODE, name());
    }
    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
}
