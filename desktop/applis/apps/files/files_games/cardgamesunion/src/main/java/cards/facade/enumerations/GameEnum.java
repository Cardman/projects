package cards.facade.enumerations;
import cards.facade.Games;
import code.util.core.StringUtil;

/**Jeux de cartes utilisees*/
public enum GameEnum {
    BELOTE,PRESIDENT,TAROT;

    public String toString(String _locale) {
        String folderName_ = CardsResourcesAccess.NOM_DOSSIER;
        String fileName_ = CardsResourcesAccess.NOM_FICHIER;
        return Games.getConstanteLangue(StringUtil.concat(folderName_, "/", _locale, "/", fileName_), CardsResourcesAccess.GAMES,name());
    }
}
