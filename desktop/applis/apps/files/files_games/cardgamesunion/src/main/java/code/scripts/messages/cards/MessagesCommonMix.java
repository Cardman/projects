package code.scripts.messages.cards;

import cards.consts.*;
import code.sml.util.*;

public final class MessagesCommonMix {

    private MessagesCommonMix() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_DEAL),"at each deal");
        e_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING),"at each launching");
        e_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.ONCE_ONLY),"once");
        e_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.NEVER),"never");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_DEAL),"à chaque partie");
        f_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING),"à chaque lancement");
        f_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.ONCE_ONLY),"une seule fois");
        f_.add(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.NEVER),"jamais");
        return f_;
    }
}
