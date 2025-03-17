package cards.facade;

import cards.consts.*;
import cards.president.*;
import cards.president.enumerations.*;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.util.*;
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
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_1, 1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_1, 1, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autorisePresident2() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.NO_SKIP);
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_3, 1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_3, 1, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autorisePresident3() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.FORBIDDEN);
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_1, 1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_1, 1, StringUtil.FR)).isEmpty());
    }
    @Test
    public void autorisePresident4() {
        GamePresident p_ = init();
        p_.getDeal().getDeal().add(new HandPresident());
        HandPresident t_ = new HandPresident();
        t_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(t_);
        p_.getRules().setEqualty(EqualtyPlaying.FORBIDDEN);
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_3, 1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_3, 1, StringUtil.FR)).isEmpty());
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
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.DIAMOND_4, 1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.DIAMOND_4, 1, StringUtil.FR)).isEmpty());
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
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 1, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 1, StringUtil.FR)).isEmpty());
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
        p_.getLastStatus().put(0,Playing.CAN_PLAY);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 2, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 2, StringUtil.FR)).isEmpty());
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
        p_.getLastStatus().put(0,Playing.SKIPPED);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 2, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 2, StringUtil.FR)).isEmpty());
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
        p_.getLastStatus().put(0,Playing.PASS);
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 2, StringUtil.EN)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ autorisePresident(p_, CardPresident.HEART_5, 2, StringUtil.FR)).isEmpty());
    }
    @Test
    public void canPassMess() {
        GamePresident p_ = init();
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_4);
        p_.getProgressingTrick().getCards().add(h_);
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(en_), MessagesCommonCards.en());
        MessagesCardGames.appendGamePresident(MessagesCardGames.getAppliTr(en_), MessagesPresident.enGame());
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(fr_), MessagesCommonCards.fr());
        MessagesCardGames.appendGamePresident(MessagesCardGames.getAppliTr(fr_), MessagesPresident.frGame());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.canPassMess(p_, en_)).isEmpty());
        assertFalse(StringUtil.nullToEmpty(" "+ Games.canPassMess(p_, fr_)).isEmpty());
    }
    @Test
    public void isSameTeam1() {
        Games gs_ = new Games();
        gs_.jouerPresident(init());
        assertTrue(gs_.isSameTeam(Ints.newList(0)));
    }
    @Test
    public void isSameTeam2() {
        Games gs_ = new Games();
        gs_.jouerPresident(init());
        assertFalse(gs_.isSameTeam(Ints.newList(1,2)));
    }

    private StringBuilder autorisePresident(GamePresident _p, CardPresident _card, int _nb, String _loc) {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        TranslationsAppli enApp_ = MessagesCardGames.initAppliTr(en_);
        MessagesCardGames.appendCommonCards(enApp_, MessagesCommonCards.en());
        MessagesCardGames.appendCommonFile(enApp_, MessagesCommonFile.en());
        MessagesCardGames.appendGamePresident(enApp_, MessagesPresident.enGame());
        TranslationsAppli frApp_ = MessagesCardGames.initAppliTr(fr_);
        MessagesCardGames.appendCommonCards(frApp_, MessagesCommonCards.fr());
        MessagesCardGames.appendCommonFile(frApp_, MessagesCommonFile.fr());
        MessagesCardGames.appendGamePresident(frApp_, MessagesPresident.frGame());
        Translations trs_ = new Translations();
        trs_.getMapping().addEntry(StringUtil.EN,en_);
        trs_.getMapping().addEntry(StringUtil.FR,fr_);
        return Games.autorisePresident(_p, _card, _nb, trs_.getMapping().getVal(_loc));
    }

    private GamePresident init() {
        DealPresident d_ = new DealPresident(new CustList<HandPresident>(),1);
        d_.getDeal().add(new HandPresident());
        d_.getDeal().add(new HandPresident());
        d_.getDeal().add(new HandPresident());
        d_.getDeal().add(new HandPresident());
        GamePresident p_ = new GamePresident(GameType.EDIT, d_,new RulesPresident(),new Ints());
        p_.setDeal(d_);
        return p_;
    }
}
