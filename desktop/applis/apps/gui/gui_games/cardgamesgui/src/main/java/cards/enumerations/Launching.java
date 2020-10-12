package cards.enumerations;
import cards.facade.Games;

public enum Launching {
    WELCOME;

    public String toString(String _locale) {
        return Games.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.LAUNCH,name());
    }
}
