package cards.facade;

import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.enumerations.DealingTarot;
import code.util.Bytes;
import code.util.core.*;
import org.junit.Test;

public final class GamesPresidentTest extends EquallableCardsFileUtil {
    @Test
    public void autorisePresident1() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.NO_SKIP);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_1,(byte) 1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_1,(byte) 1,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident2() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.NO_SKIP);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_3,(byte) 1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_3,(byte) 1,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident3() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.FORBIDDEN);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_1,(byte) 1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_1,(byte) 1,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident4() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.FORBIDDEN);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_3,(byte) 1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_3,(byte) 1,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident5() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.DIAMOND_4);
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.HEART_5);
        HandPresident h1_ = new HandPresident();
        h1_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h1_);
        HandPresident h2_ = new HandPresident();
        h2_.ajouter(CardPresident.SPADE_4);
        p_.getProgressingTrick().getCards().add(h2_);
        p_.getRules().setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.DIAMOND_4,(byte) 1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.DIAMOND_4,(byte) 1,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident6() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.DIAMOND_4);
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.HEART_5);
        HandPresident h1_ = new HandPresident();
        h1_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h1_);
        HandPresident h2_ = new HandPresident();
        h2_.ajouter(CardPresident.SPADE_4);
        p_.getProgressingTrick().getCards().add(h2_);
        p_.getRules().setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 1,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 1,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident7() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.DIAMOND_4);
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.HEART_5);
        HandPresident h1_ = new HandPresident();
        h1_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h1_);
        HandPresident h2_ = new HandPresident();
        h2_.ajouter(CardPresident.SPADE_4);
        p_.getProgressingTrick().getCards().add(h2_);
        p_.getRules().setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 2,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 2,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident8() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.DIAMOND_4);
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.HEART_5);
        HandPresident h1_ = new HandPresident();
        h1_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h1_);
        HandPresident h2_ = new HandPresident();
        h2_.ajouter(CardPresident.SPADE_4);
        p_.getProgressingTrick().getCards().add(h2_);
        p_.getRules().setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        p_.getPassOrFinish().add(BoolVal.FALSE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 2,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 2,"fr")).isEmpty());
    }
    @Test
    public void autorisePresident9() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.DIAMOND_4);
        p_.getDeal().getDeal().get(0).ajouter(CardPresident.HEART_5);
        HandPresident h1_ = new HandPresident();
        h1_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h1_);
        HandPresident h2_ = new HandPresident();
        h2_.ajouter(CardPresident.SPADE_4);
        p_.getProgressingTrick().getCards().add(h2_);
        p_.getRules().setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        p_.getPassOrFinish().add(BoolVal.TRUE);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 2,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.autorisePresident(p_,(byte) 0, CardPresident.HEART_5,(byte) 2,"fr")).isEmpty());
    }
    @Test
    public void canPassMess() {
        GamePresident p_ = init();
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h_);
        assertFalse(StringUtil.nullToEmpty(" "+ Games.canPassMess(p_,"en")).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.canPassMess(p_,"fr")).isEmpty());
    }
    @Test
    public void isSameTeam1() {
        Games gs_ = new Games();
        gs_.jouerPresident(init());
        assertTrue(gs_.isSameTeam(Bytes.newList((byte)0)));
    }
    @Test
    public void isSameTeam2() {
        Games gs_ = new Games();
        gs_.jouerPresident(init());
        assertFalse(gs_.isSameTeam(Bytes.newList((byte)1,(byte)2)));
    }
    private GamePresident init() {
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        return p_;
    }
}
