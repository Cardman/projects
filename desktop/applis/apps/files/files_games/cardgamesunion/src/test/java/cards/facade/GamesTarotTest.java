package cards.facade;

import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.util.Bytes;
import code.util.CustList;
import code.util.core.*;
import org.junit.Test;

public final class GamesTarotTest extends EquallableCardsFileUtil {
    @Test
    public void autoriseMessEcartDe1() {
        GameTarot t_ = init();
        t_.setPreneur((byte) 0);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD,CardTarot.TRUMP_2,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD,CardTarot.TRUMP_2,"fr")).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe2() {
        GameTarot t_ = init();
        t_.setPreneur((byte) 0);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.KING,CardTarot.HEART_KING,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.KING,CardTarot.HEART_KING,"fr")).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe3() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_KING);
        t_.setPreneur((byte) 0);
        t_.autoriseEcartDe(CardTarot.HEART_1);
        t_.autoriseEcartDe(CardTarot.HEART_2);
        t_.autoriseEcartDe(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.KING,CardTarot.HEART_KING,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.KING,CardTarot.HEART_KING,"fr")).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe4() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.TRUMP_21);
        t_.setPreneur((byte) 0);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_1);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_2);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER,CardTarot.TRUMP_21,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER,CardTarot.TRUMP_21,"fr")).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe5() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.TRUMP_20);
        t_.setPreneur((byte) 0);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_1);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_2);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.NOTHING,CardTarot.TRUMP_20,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.NOTHING,CardTarot.TRUMP_20,"fr")).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe6() {
        GameTarot t_ = init();
        t_.getRegles().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.HEART_8);
        t_.getDeal().getDeal().get(0).ajouter(CardTarot.TRUMP_1);
        t_.setPreneur((byte) 0);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_1);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_2);
        t_.getDeal().derniereMain().ajouter(CardTarot.HEART_3);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER,CardTarot.TRUMP_1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TRUMP_CARD_OULDER,CardTarot.TRUMP_1,"fr")).isEmpty());
    }
    @Test
    public void autoriseMessEcartDe7() {
        GameTarot t_ = init();
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TOO_MUCH,CardTarot.TRUMP_1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autoriseMessEcartDe(ReasonDiscard.TOO_MUCH,CardTarot.TRUMP_1,"fr")).isEmpty());
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
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"fr")).isEmpty());
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
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"fr")).isEmpty());
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
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"fr")).isEmpty());
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
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"fr")).isEmpty());
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
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.isValidHandfulMessage(t_,Handfuls.ONE,i_,e_,"fr")).isEmpty());
    }
    @Test
    public void autoriseTarot1() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getDeal().hand((byte) 0).ajouter(CardTarot.HEART_8);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot2() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.HEART_8);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.HEART_8)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.HEART_8)).isEmpty());
    }
    @Test
    public void autoriseTarot3() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot4() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand((byte) 2).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.TRUMP_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.TRUMP_1)).isEmpty());
    }
    @Test
    public void autoriseTarot5() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.TRUMP_4);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.TRUMP_2)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.TRUMP_2)).isEmpty());
    }
    @Test
    public void autoriseTarot6() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand((byte) 2).ajouter(CardTarot.TRUMP_4);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.TRUMP_2)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.TRUMP_2)).isEmpty());
    }
    @Test
    public void autoriseTarot7() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_3);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.TRUMP_2);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.TRUMP_4);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.TRUMP_2)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.TRUMP_2)).isEmpty());
    }
    @Test
    public void autoriseTarot8() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.HEART_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot9() {
        GameTarot t_ = init();
        conf(t_);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.HEART_8);
        t_.getDeal().hand((byte) 1).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.DIAMOND_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.DIAMOND_1)).isEmpty());
    }
    @Test
    public void autoriseTarot10() {
        GameTarot t_ = init();
        conf(t_);
        t_.getRegles().setAllowPlayCalledSuit(false);
        t_.getCalledCards().supprimerCartes();
        t_.getCalledCards().ajouter(CardTarot.HEART_KING);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getDeal().hand((byte) 0).ajouter(CardTarot.TRUMP_2);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.HEART_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.HEART_1)).isEmpty());
    }
    @Test
    public void autoriseTarot11() {
        GameTarot t_ = init();
        conf(t_);
        t_.getCalledCards().supprimerCartes();
        t_.getCalledCards().ajouter(CardTarot.HEART_KING);
        t_.getProgressingTrick().setStarter((byte) 0);
        t_.getProgressingTrick().getCards().ajouter(CardTarot.TRUMP_2);
        t_.getDeal().hand((byte) 0).ajouter(CardTarot.TRUMP_1);
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "en",CardTarot.TRUMP_1)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autoriseTarot(t_, "fr",CardTarot.TRUMP_1)).isEmpty());
    }
    @Test
    public void isSameTeam1() {
        Games gs_ = new Games();
        gs_.jouerTarot(init());
        gs_.partieTarot().getRules().setDealing(DealingTarot.DEAL_1_VS_4);
        gs_.partieTarot().setPreneur((byte) 0);
        assertFalse(gs_.isSameTeam(Bytes.newList((byte)0,(byte)1)));
    }
    @Test
    public void isSameTeam2() {
        Games gs_ = new Games();
        gs_.jouerTarot(init());
        gs_.partieTarot().getRules().setDealing(DealingTarot.DEAL_1_VS_4);
        gs_.partieTarot().setPreneur((byte) 0);
        assertTrue(gs_.isSameTeam(Bytes.newList((byte)1,(byte)2)));
    }

    private String autoriseTarot(GameTarot _t, String _lg, CardTarot _c) {
        _t.autorise(_c);
        return Games.autoriseTarot(_t, _lg);
    }

    private void conf(GameTarot _t) {
        CustList<BoolVal> bs_ = new CustList<BoolVal>();
        bs_.add(BoolVal.FALSE);
        bs_.add(BoolVal.FALSE);
        bs_.add(BoolVal.FALSE);
        bs_.add(BoolVal.FALSE);
        bs_.add(BoolVal.FALSE);
        _t.getConfidence().add(bs_);
        _t.getConfidence().add(bs_);
        _t.getConfidence().add(bs_);
        _t.getConfidence().add(bs_);
        _t.getConfidence().add(bs_);
        _t.initEquipeDetermineeSansPreneur();
    }

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
