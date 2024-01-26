package code.scripts.messages.cards;

import cards.consts.EnumCardsExporterUtil;
import cards.consts.Role;
import cards.consts.Suit;
import code.sml.util.TranslationsFile;

public final class MessagesCommonFile {

    public static final String RESULT_PAGE = "2";
    public static final String DET_RESULT_PAGE = "3";

    private MessagesCommonFile() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EnumCardsExporterUtil.SUITS+Suit.TRUMP.getSuitSt(), "Trump");
        e_.add(EnumCardsExporterUtil.SUITS+Suit.HEART.getSuitSt(),"Heart");
        e_.add(EnumCardsExporterUtil.SUITS+Suit.SPADE.getSuitSt(),"Spade");
        e_.add(EnumCardsExporterUtil.SUITS+Suit.DIAMOND.getSuitSt(),"Diamond");
        e_.add(EnumCardsExporterUtil.SUITS+Suit.CLUB.getSuitSt(),"Club");
        e_.add(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(Role.TAKER), "Taker");
        e_.add(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(Role.CALLED_PLAYER), "Called player");
        e_.add(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(Role.DEFENDER), "Defender");
        e_.add(RESULT_PAGE, "Global results");
        e_.add(DET_RESULT_PAGE, "Detail results");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EnumCardsExporterUtil.SUITS+Suit.TRUMP.getSuitSt(),"Atout");
        f_.add(EnumCardsExporterUtil.SUITS+Suit.HEART.getSuitSt(),"Coeur");
        f_.add(EnumCardsExporterUtil.SUITS+Suit.SPADE.getSuitSt(),"Pique");
        f_.add(EnumCardsExporterUtil.SUITS+Suit.DIAMOND.getSuitSt(),"Carreau");
        f_.add(EnumCardsExporterUtil.SUITS+Suit.CLUB.getSuitSt(),"Trèfle");
        f_.add(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(Role.TAKER),"Preneur");
        f_.add(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(Role.CALLED_PLAYER),"Appelé");
        f_.add(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(Role.DEFENDER),"Défenseur");
        f_.add(RESULT_PAGE, "Résultats globaux");
        f_.add(DET_RESULT_PAGE, "Détail des résultats");
        return f_;
    }

}
