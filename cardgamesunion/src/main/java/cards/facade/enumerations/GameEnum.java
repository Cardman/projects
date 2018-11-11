package cards.facade.enumerations;
import code.format.Format;

/**Jeux de cartes utilisees*/
public enum GameEnum {
    BELOTE,PRESIDENT,TAROT;

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.GAMES,name());
    }
}
