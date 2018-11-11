package cards.consts;
import code.format.Format;
public enum MixCardsChoice {
EACH_DEAL,EACH_LAUNCHING,ONCE_ONLY,NEVER;

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.MIX,name());
    }
}
