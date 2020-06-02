package cards.enumerations;
import code.format.Format;

public enum Launching {
    WELCOME;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.LAUNCH,name());
    }
}
