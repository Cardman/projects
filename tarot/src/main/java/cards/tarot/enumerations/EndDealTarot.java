package cards.tarot.enumerations;
import code.format.Format;
import code.format.Translatable;

public enum EndDealTarot implements Translatable {
    ATTACK_LOOSE, ATTACK_WIN, ZERO;

    @Override
    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_END,name());
    }
}
