package cards.facade.enumerations;
import cards.facade.Games;

/**Jeux de cartes utilisees*/
public enum GameEnum {
    BELOTE,PRESIDENT,TAROT;

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Games.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.GAMES,name());
    }
}
