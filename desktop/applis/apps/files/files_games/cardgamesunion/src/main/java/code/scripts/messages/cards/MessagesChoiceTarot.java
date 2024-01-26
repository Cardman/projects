package code.scripts.messages.cards;

import cards.tarot.enumerations.TarotCardsExporterUtil;
import code.sml.util.TranslationsFile;

public final class MessagesChoiceTarot {
    private MessagesChoiceTarot() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TarotCardsExporterUtil.SAVE_SMALL,"Save the trump Ace");
        e_.add(TarotCardsExporterUtil.HUNT_SMALL,"Hunt the trump Ace");
        e_.add(TarotCardsExporterUtil.LEAD_SMALL_BOUND,"Lead the trump Ace to the bound");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TarotCardsExporterUtil.SAVE_SMALL,"Petit à sauver");
        f_.add(TarotCardsExporterUtil.HUNT_SMALL,"Petit à chasser");
        f_.add(TarotCardsExporterUtil.LEAD_SMALL_BOUND,"Petit à emmener au bout");
        return f_;
    }

}
