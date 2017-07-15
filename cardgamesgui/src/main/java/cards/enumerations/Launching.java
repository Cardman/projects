package cards.enumerations;
import code.format.Format;
import code.util.consts.Constants;

public enum Launching {
    WELCOME;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.LAUNCH,this);
    }
}
