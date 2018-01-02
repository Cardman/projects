package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum EndDealTarot implements Displayable{
    ATTACK_LOOSE, ATTACK_WIN, ZERO;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_END,name());
    }
    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
}
