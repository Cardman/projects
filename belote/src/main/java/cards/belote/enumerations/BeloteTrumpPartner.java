package cards.belote.enumerations;
import code.format.Format;
import code.util.consts.Constants;

public enum BeloteTrumpPartner {
NO_UNDERTRUMP_NO_OVERTRUMP,OVERTRUMP_ONLY,UNDERTRUMP_ONLY,UNDERTRUMP_OVERTRUMP;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_TRUMP_PART,name());
    }
}
