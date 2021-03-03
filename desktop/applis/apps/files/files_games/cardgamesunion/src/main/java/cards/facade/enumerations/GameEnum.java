package cards.facade.enumerations;
import cards.facade.Games;
import code.resources.ResourceFiles;
import code.util.core.StringUtil;

/**Jeux de cartes utilisees*/
public enum GameEnum {
    BELOTE,PRESIDENT,TAROT;

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Games.getConstanteLangue(StringUtil.concat(folderName_, ResourceFiles.SEPARATEUR, _locale, ResourceFiles.SEPARATEUR, fileName_), ResourcesAccess.GAMES,name());
    }
}
