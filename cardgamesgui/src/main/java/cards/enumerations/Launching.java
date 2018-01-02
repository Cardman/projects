package cards.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum Launching implements Displayable {
    WELCOME;

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.LAUNCH,name());
    }
}
