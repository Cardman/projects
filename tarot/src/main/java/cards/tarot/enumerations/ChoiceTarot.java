package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

/** Choix du tarot pour l'entrainement*/
public enum ChoiceTarot implements Displayable {
SAVE_SMALL,HUNT_SMALL,LEAD_SMALL_BOUND;

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_CHOICE, name());
    }
}
