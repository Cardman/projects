package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;

/** Choix du tarot pour l'entrainement*/
public enum ChoiceTarot {
SAVE_SMALL,HUNT_SMALL,LEAD_SMALL_BOUND;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }
    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_CHOICE, name());
    }
}
