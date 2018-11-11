package cards.tarot.enumerations;
import code.format.Format;

/** Choix du tarot pour l'entrainement*/
public enum ChoiceTarot {
SAVE_SMALL,HUNT_SMALL,LEAD_SMALL_BOUND;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_CHOICE, name());
    }
}
