package cards.enumerations;
import cards.facade.Games;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.core.StringUtil;

public enum Launching {
    WELCOME;
//
//    public static String getConstanteLangue(String _file, String _group, String _nomConstante) {
//        String fichier_ = MessagesCardsAll.ms().getVal(_file);
////        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(Format.concatParts(_group,_nomConstante), fichier_);
//    }

    public String toString(TranslationsLg _locale) {
        return StringUtil.nullToEmpty(Games.getMenus(Games.getAppliTr(_locale)).getMapping().getVal(MessagesGuiCards.WELCOME));
    }
}
