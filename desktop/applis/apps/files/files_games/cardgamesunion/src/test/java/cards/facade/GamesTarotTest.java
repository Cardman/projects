package cards.facade;

import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.scripts.messages.cards.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.Ints;
import code.util.core.*;
import org.junit.Test;

public final class GamesTarotTest extends EquallableCardsFileUtil {
    @Test
    public void autoriseMessEcartDe1() {
        GameTarot t_ = init();
        t_.setPreneur(0);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD, CardTarot.TRUMP_2, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD, CardTarot.TRUMP_2, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe2() {
        GameTarot t_ = init();
        t_.setPreneur(0);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.KING, CardTarot.HEART_KING, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.KING, CardTarot.HEART_KING, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe3() {
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.KING, CardTarot.HEART_KING, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.KING, CardTarot.HEART_KING, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe4() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.TRUMP_21);
        t_.setPreneur(0);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_1);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_2);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER, CardTarot.TRUMP_21, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER, CardTarot.TRUMP_21, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe5() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.TRUMP_20);
        t_.setPreneur(0);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_1);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_2);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.NOTHING, CardTarot.TRUMP_20, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.NOTHING, CardTarot.TRUMP_20, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe6() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.TRUMP_1);
        t_.setPreneur(0);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_1);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_2);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER, CardTarot.TRUMP_1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER, CardTarot.TRUMP_1, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe7() {
        GameTarot t_ = init();
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TOO_MUCH, CardTarot.TRUMP_1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseMessEcartDe(ReasonDiscard.TOO_MUCH, CardTarot.TRUMP_1, StringUtil.FR)).isEmpty());
    }
    @Test
    public void isValidHandfulMessage1() {
        GameTarot t_ = init();
        t_.getRegles().getAllowedHandfuls().clear();
        t_.getRegles().getAllowedHandfuls().addEntry(Handfuls.ONE, 2);
        HandTarot i_ = new HandTarot();
        i_.ajouter(CardTarot.TRUMP_2);
        i_.ajouter(CardTarot.TRUMP_3);
        HandTarot e_ = new HandTarot();
        e_.ajouter(CardTarot.EXCUSE);
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.FR)).isEmpty());
    }
    @Test
    public void isValidHandfulMessage2() {
        GameTarot t_ = init();
        t_.getRegles().getAllowedHandfuls().clear();
        t_.getRegles().getAllowedHandfuls().addEntry(Handfuls.ONE, 2);
        HandTarot i_ = new HandTarot();
        i_.ajouter(CardTarot.TRUMP_2);
        i_.ajouter(CardTarot.EXCUSE);
        HandTarot e_ = new HandTarot();
        e_.ajouter(CardTarot.TRUMP_3);
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.FR)).isEmpty());
    }
    @Test
    public void isValidHandfulMessage3() {
        GameTarot t_ = init();
        t_.getRegles().getAllowedHandfuls().clear();
        t_.getRegles().getAllowedHandfuls().addEntry(Handfuls.ONE, 2);
        HandTarot i_ = new HandTarot();
        i_.ajouter(CardTarot.TRUMP_2);
        i_.ajouter(CardTarot.EXCUSE);
        HandTarot e_ = new HandTarot();
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.FR)).isEmpty());
    }
    @Test
    public void isValidHandfulMessage4() {
        GameTarot t_ = init();
        t_.getRegles().getAllowedHandfuls().clear();
        t_.getRegles().getAllowedHandfuls().addEntry(Handfuls.ONE, 2);
        HandTarot i_ = new HandTarot();
        i_.ajouter(CardTarot.TRUMP_2);
        i_.ajouter(CardTarot.TRUMP_3);
        i_.ajouter(CardTarot.TRUMP_4);
        HandTarot e_ = new HandTarot();
        e_.ajouter(CardTarot.EXCUSE);
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.FR)).isEmpty());
    }
    @Test
    public void isValidHandfulMessage5() {
        GameTarot t_ = init();
        t_.getRegles().getAllowedHandfuls().clear();
        t_.getRegles().getAllowedHandfuls().addEntry(Handfuls.ONE, 2);
        HandTarot i_ = new HandTarot();
        i_.ajouter(CardTarot.TRUMP_2);
        HandTarot e_ = new HandTarot();
        e_.ajouter(CardTarot.EXCUSE);
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ isValidHandfulMessage(t_, Handfuls.ONE, i_, e_, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autoriseTarot1() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getDeal().hand(0).ajouter(CardTarot.HEART_8);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot2() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand(1).ajouter(CardTarot.HEART_8);
        t_.getDeal().hand(1).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.HEART_8)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.HEART_8)).isEmpty());
    }
    @Test
    public void autoriseTarot3() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand(1).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot4() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand(2).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.TRUMP_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.TRUMP_1)).isEmpty());
    }
    @Test
    public void autoriseTarot5() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand(1).ajouter(CardTarot.TRUMP_4);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.TRUMP_2)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.TRUMP_2)).isEmpty());
    }
    @Test
    public void autoriseTarot6() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand(2).ajouter(CardTarot.TRUMP_4);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.TRUMP_2)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.TRUMP_2)).isEmpty());
    }
    @Test
    public void autoriseTarot7() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand(1).ajouter(CardTarot.TRUMP_2);
        t_.getDeal().hand(1).ajouter(CardTarot.TRUMP_4);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.TRUMP_2)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.TRUMP_2)).isEmpty());
    }
    @Test
    public void autoriseTarot8() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getDeal().hand(1).ajouter(CardTarot.HEART_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot9() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getDeal().hand(1).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot10() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getRegles().setAllowPlayCalledSuit(false);
        t_.getCalledCards().supprimerCartes();
        t_.getCalledCards().ajouter(CardTarot.HEART_KING);
        t_.getProgressingTrick().setStarter(0);
        t_.getDeal().hand(0).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.HEART_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.HEART_1)).isEmpty());
    }
    @Test
    public void autoriseTarot11() {
        GameTarot t_ = init();
        //conf(t_);
        t_.getCalledCards().supprimerCartes();
        t_.getCalledCards().ajouter(CardTarot.HEART_KING);
        t_.getProgressingTrick().setStarter(0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_2);
        t_.getDeal().hand(0).ajouter(CardTarot.TRUMP_1);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.EN,CardTarot.TRUMP_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, StringUtil.FR,CardTarot.TRUMP_1)).isEmpty());
    }
    @Test
    public void isSameTeam1() {
        Games gs_ = new Games();
        gs_.jouerTarot(init());
        gs_.partieTarot().getRules().setDealing(DealingTarot.DEAL_1_VS_4);
        gs_.partieTarot().setPreneur(0);
        assertFalse(gs_.isSameTeam(Ints.newList(0,1)));
    }
    @Test
    public void isSameTeam2() {
        Games gs_ = new Games();
        gs_.jouerTarot(init());
        gs_.partieTarot().getRules().setDealing(DealingTarot.DEAL_1_VS_4);
        gs_.partieTarot().setPreneur(0);
        assertTrue(gs_.isSameTeam(Ints.newList(1,2)));
    }

    private String autoriseTarot(GameTarot _t, String _lg, CardTarot _c) {
        _t.autorise(_c);
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        TranslationsAppli enApp_ = MessagesCardGames.initAppliTr(en_);
        MessagesCardGames.appendCommonCards(enApp_, MessagesCommonCards.en());
        MessagesCardGames.appendCommonFile(enApp_, MessagesCommonFile.en());
        MessagesCardGames.appendGameTarot(enApp_, MessagesTarot.enGame());
        TranslationsAppli frApp_ = MessagesCardGames.initAppliTr(fr_);
        MessagesCardGames.appendCommonCards(frApp_, MessagesCommonCards.fr());
        MessagesCardGames.appendCommonFile(frApp_, MessagesCommonFile.fr());
        MessagesCardGames.appendGameTarot(frApp_, MessagesTarot.frGame());
        Translations trs_ = new Translations();
        trs_.getMapping().addEntry(StringUtil.EN,en_);
        trs_.getMapping().addEntry(StringUtil.FR,fr_);
        return Games.autoriseTarot(_t, trs_.getMapping().getVal(_lg));
    }

    private StringBuilder autoriseMessEcartDe(ReasonDiscard _reason, CardTarot _card, String _loc) {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        TranslationsAppli enApp_ = MessagesCardGames.initAppliTr(en_);
        MessagesCardGames.appendCommonCards(enApp_, MessagesCommonCards.en());
        MessagesCardGames.appendCommonFile(enApp_, MessagesCommonFile.en());
        MessagesCardGames.appendGameTarot(enApp_, MessagesTarot.enGame());
        TranslationsAppli frApp_ = MessagesCardGames.initAppliTr(fr_);
        MessagesCardGames.appendCommonCards(frApp_, MessagesCommonCards.fr());
        MessagesCardGames.appendCommonFile(frApp_, MessagesCommonFile.fr());
        MessagesCardGames.appendGameTarot(frApp_, MessagesTarot.frGame());
        Translations trs_ = new Translations();
        trs_.getMapping().addEntry(StringUtil.EN,en_);
        trs_.getMapping().addEntry(StringUtil.FR,fr_);
        return Games.autoriseMessEcartDe(_reason, _card, trs_.getMapping().getVal(_loc));
    }

    private String isValidHandfulMessage(GameTarot _t, Handfuls _h, HandTarot _i, HandTarot _e, String _loc) {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        TranslationsAppli enApp_ = MessagesCardGames.initAppliTr(en_);
        MessagesCardGames.appendCommonCards(enApp_, MessagesCommonCards.en());
        MessagesCardGames.appendCommonFile(enApp_, MessagesCommonFile.en());
        MessagesCardGames.appendGameTarot(enApp_, MessagesTarot.enGame());
        TranslationsAppli frApp_ = MessagesCardGames.initAppliTr(fr_);
        MessagesCardGames.appendCommonCards(frApp_, MessagesCommonCards.fr());
        MessagesCardGames.appendCommonFile(frApp_, MessagesCommonFile.fr());
        MessagesCardGames.appendGameTarot(frApp_, MessagesTarot.frGame());
        Translations trs_ = new Translations();
        trs_.getMapping().addEntry(StringUtil.EN,en_);
        trs_.getMapping().addEntry(StringUtil.FR,fr_);
        return Games.isValidHandfulMessage(_t.getRegles(), _h, _i, _e, trs_.getMapping().getVal(_loc));
    }

//    private void conf(GameTarot _t) {
//        CustList<BoolVal> bs_ = new CustList<BoolVal>();
//        bs_.add(BoolVal.FALSE);
//        bs_.add(BoolVal.FALSE);
//        bs_.add(BoolVal.FALSE);
//        bs_.add(BoolVal.FALSE);
//        bs_.add(BoolVal.FALSE);
//        _t.getConfidence().add(bs_);
//        _t.getConfidence().add(bs_);
//        _t.getConfidence().add(bs_);
//        _t.getConfidence().add(bs_);
//        _t.getConfidence().add(bs_);
//        _t.initEquipeDetermineeSansPreneur();
//    }

    private GameTarot init() {
        GameTarot p_ = new GameTarot();
        p_.setDeal(new DealTarot());
        p_.getDeal().getDeal().add(new HandTarot());
        p_.getDeal().getDeal().add(new HandTarot());
        p_.getDeal().getDeal().add(new HandTarot());
        p_.getDeal().getDeal().add(new HandTarot());
        p_.getDeal().getDeal().add(new HandTarot());
        p_.getDeal().getDeal().add(new HandTarot());
        return p_;
    }
}
