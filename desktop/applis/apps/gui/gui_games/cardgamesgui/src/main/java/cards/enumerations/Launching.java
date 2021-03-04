package cards.enumerations;
import cards.facade.Games;
import code.util.core.StringUtil;

public enum Launching {
    WELCOME;

    public String toString(String _locale) {
        return Games.getConstanteLangue(StringUtil.concat(ResourcesAccess.NOM_DOSSIER, "/", _locale, "/", ResourcesAccess.NOM_FICHIER), ResourcesAccess.LAUNCH,name());
    }
}
