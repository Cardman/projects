package cards.belote.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum BeloteTrumpPartner implements Displayable {
NO_UNDERTRUMP_NO_OVERTRUMP,OVERTRUMP_ONLY,UNDERTRUMP_ONLY,UNDERTRUMP_OVERTRUMP;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_TRUMP_PART,name());
    }

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
}
