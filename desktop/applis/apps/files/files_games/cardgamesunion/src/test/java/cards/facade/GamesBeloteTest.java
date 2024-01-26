package cards.facade;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import code.scripts.messages.cards.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.Bytes;
import code.util.core.*;
import org.junit.Test;

public final class GamesBeloteTest extends EquallableCardsFileUtil {
    @Test
    public void autoriseBelote1() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.HEART_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.HEART_7)).isEmpty());
    }
    @Test
    public void autoriseBelote2() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_7)).isEmpty());
    }
    @Test
    public void autoriseBelote3() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_7)).isEmpty());
    }
    @Test
    public void autoriseBelote4() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_8);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote5() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_8);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote6() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_8);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote7() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote8() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote9() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote10() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote11() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote12() {
        GameBelote b_ = init();
        b_.getRegles().setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.SPADE_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void autoriseBelote13() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_9);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_7)).isEmpty());
    }
    @Test
    public void autoriseBelote14() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_7)).isEmpty());
    }
    @Test
    public void autoriseBelote15() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_1)).isEmpty());
    }
    @Test
    public void autoriseBelote16() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_7);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_1)).isEmpty());
    }
    @Test
    public void autoriseBelote17() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_7);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_1)).isEmpty());
    }
    @Test
    public void autoriseBelote18() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_7);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.HEART_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.HEART_7)).isEmpty());
    }
    @Test
    public void autoriseBelote19() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_7);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.SPADE_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.SPADE_1)).isEmpty());
    }
    @Test
    public void autoriseBelote20() {
        GameBelote b_ = init();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getDeal().getDeal().get(0).ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().setStarter((byte) 1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_8);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.HEART_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.HEART_7)).isEmpty());
    }
    @Test
    public void autoriseBelote21() {
        GameBelote b_ = init();
        b_.getRegles().setUnderTrumpFoe(true);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        b_.setBid(bid_);
        b_.setPreneur((byte) 0);
        b_.getProgressingTrick().setStarter((byte) 0);
        b_.getDeal().getDeal().get(3).ajouter(CardBelote.SPADE_7);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_1);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.HEART_10);
        b_.getProgressingTrick().getCartes().ajouter(CardBelote.SPADE_1);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "en",CardBelote.DIAMOND_7)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseBelote(b_, "fr",CardBelote.DIAMOND_7)).isEmpty());
    }
    @Test
    public void isSameTeam1() {
        Games gs_ = new Games();
        gs_.jouerBelote(init());
        gs_.partieBelote().setPreneur((byte) 0);
        assertFalse(gs_.isSameTeam(Bytes.newList((byte)0,(byte)1)));
    }
    @Test
    public void isSameTeam2() {
        Games gs_ = new Games();
        gs_.jouerBelote(init());
        gs_.partieBelote().setPreneur((byte) 0);
        assertTrue(gs_.isSameTeam(Bytes.newList((byte)0,(byte)2)));
    }
    private GameBelote init() {
        GameBelote p_ = new GameBelote();
        p_.setDeal(new DealBelote());
        p_.getDeal().getDeal().add(new HandBelote());
        p_.getDeal().getDeal().add(new HandBelote());
        p_.getDeal().getDeal().add(new HandBelote());
        p_.getDeal().getDeal().add(new HandBelote());
        p_.getDeal().getDeal().add(new HandBelote());
        p_.getDeal().derniereMain().ajouter(CardBelote.WHITE);
        return p_;
    }

    private String autoriseBelote(GameBelote _b, String _lg, CardBelote _c) {
        _b.autorise(_c);
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        TranslationsAppli enApp_ = Games.initAppliTr(en_);
        Games.appendCommonCards(enApp_, MessagesCommonCards.en());
        Games.appendCommonFile(enApp_, MessagesCommonFile.en());
        Games.appendGameBelote(enApp_, MessagesBelote.enGame());
        TranslationsAppli frApp_ = Games.initAppliTr(fr_);
        Games.appendCommonCards(frApp_, MessagesCommonCards.fr());
        Games.appendCommonFile(frApp_, MessagesCommonFile.fr());
        Games.appendGameBelote(frApp_, MessagesBelote.frGame());
        Translations trs_ = new Translations();
        trs_.getMapping().addEntry("en",en_);
        trs_.getMapping().addEntry("fr",fr_);
        return Games.autoriseBelote(_b, trs_.getMapping().getVal(_lg));
    }

}
