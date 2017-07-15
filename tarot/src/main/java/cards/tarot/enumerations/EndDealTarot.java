package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;

public enum EndDealTarot {
    ATTACK_LOOSE, ATTACK_WIN, ZERO;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_END,this);
    }
}
