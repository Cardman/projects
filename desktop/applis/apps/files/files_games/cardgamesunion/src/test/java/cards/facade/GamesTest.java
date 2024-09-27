package cards.facade;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.*;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.president.*;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.solitaire.*;
import cards.solitaire.sml.DocumentWriterSolitaireUtil;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.scripts.messages.cards.*;
import code.sml.util.TranslationsLg;
import code.stream.core.TechStreams;
import code.util.Bytes;
import code.util.CustList;
import code.util.IdList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class GamesTest extends EquallableCardsFileUtil {
    @Test
    public void games() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendGamesNames(MessagesCardGames.initAppliTr(en_), MessagesGamesGames.en());
        MessagesCardGames.appendGamesNames(MessagesCardGames.initAppliTr(fr_), MessagesGamesGames.fr());
        assertFalse(GameEnum.BELOTE.toString(en_).isEmpty());
        assertFalse(GameEnum.PRESIDENT.toString(en_).isEmpty());
        assertFalse(GameEnum.TAROT.toString(en_).isEmpty());
        assertFalse(GameEnum.BELOTE.toString(fr_).isEmpty());
        assertFalse(GameEnum.PRESIDENT.toString(fr_).isEmpty());
        assertFalse(GameEnum.TAROT.toString(fr_).isEmpty());
    }
    @Test
    public void test1() {
        assertEq(GameEnum.NONE, GameEnum.getStatusTypeByName("_"));
    }
    @Test
    public void test2() {
        assertEq(GameEnum.NONE,GameEnum.getStatusTypeByName(""));
    }
    @Test
    public void text3() {
        SoftParams s_ = new SoftParams(new SoftParams());
        s_.setDelaiAttenteCartes(-1);
        s_.setDelaiAttenteContrats(-1);
        s_.setDelaiAttentePlis(-1);
        s_.setDelays();
        s_.setDelaiAttenteCartes(2);
        s_.setDelaiAttenteContrats(3);
        s_.setDelaiAttentePlis(4);
        s_.setDelays();
        IdList<GameEnum> lc_ = new IdList<GameEnum>();
        lc_.add(GameEnum.TAROT);
        lc_.add(GameEnum.PRESIDENT);
        lc_.add(GameEnum.BELOTE);
        s_.setLancement(lc_);
        s_.setAttentePlisClic(true);
        s_.setJeuCarteClic(true);
        s_.setSaveHomeFolder(true);
        SoftParams o_ = save(s_);
        assertEq(2,o_.getDelaiAttenteCartes());
        assertEq(3,o_.getDelaiAttenteContrats());
        assertEq(4,o_.getDelaiAttentePlis());
        assertEq(3,o_.getLancement().size());
        assertEq(GameEnum.TAROT,o_.getLancement().get(0));
        assertEq(GameEnum.PRESIDENT,o_.getLancement().get(1));
        assertEq(GameEnum.BELOTE,o_.getLancement().get(2));
        assertTrue(o_.getAttentePlisClic());
        assertTrue(o_.getJeuCarteClic());
        assertTrue(o_.isSaveHomeFolder());
    }
    @Test
    public void text4() {
        SoftParams s_ = new SoftParams();
        s_.setDelaiAttenteCartes(2);
        s_.setDelaiAttenteContrats(3);
        s_.setDelaiAttentePlis(4);
        IdList<GameEnum> lc_ = new IdList<GameEnum>();
        lc_.add(GameEnum.TAROT);
        lc_.add(GameEnum.PRESIDENT);
        lc_.add(GameEnum.BELOTE);
        s_.setLancement(lc_);
        s_.setAttentePlisClic(false);
        s_.setJeuCarteClic(false);
        s_.setSaveHomeFolder(false);
        SoftParams o_ = save(s_);
        assertEq(2,o_.getDelaiAttenteCartes());
        assertEq(3,o_.getDelaiAttenteContrats());
        assertEq(4,o_.getDelaiAttentePlis());
        assertEq(3,o_.getLancement().size());
        assertEq(GameEnum.TAROT,o_.getLancement().get(0));
        assertEq(GameEnum.PRESIDENT,o_.getLancement().get(1));
        assertEq(GameEnum.BELOTE,o_.getLancement().get(2));
        assertFalse(o_.getAttentePlisClic());
        assertFalse(o_.getJeuCarteClic());
        assertFalse(o_.isSaveHomeFolder());
    }
    @Test
    public void test5() {
        Nicknames n_ = new Nicknames();
        n_.setPseudo("AMOUREUX");
        Nicknames o_ = save(n_);
        assertEq("AMOUREUX",o_.getPseudo());
    }
    @Test
    public void test6() {
        Games n_ = new Games();
        GameBelote b_ = new GameBelote();
        b_.setDeal(new DealBelote());
        b_.setType(GameType.RANDOM);
        n_.getPartiesBelote().add(b_);
        GameTarot t_ = new GameTarot();
        t_.setDeal(new DealTarot());
        t_.setType(GameType.RANDOM);
        n_.getPartiesTarot().add(t_);
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        p_.setType(GameType.RANDOM);
        n_.getPartiesPresident().add(p_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        Games o_ = save(n_);
        assertEq(1,o_.getPartiesBelote().size());
        assertEq(1,o_.getPartiesPresident().size());
        assertEq(1,o_.getPartiesTarot().size());
    }
    @Test
    public void test7() {
        assertEq(SolitaireType.CLASSIC,GameEnum.getStatusTypeByName(GameEnum.CLASSIC.getNumber()).getSolitaireType());
    }
    @Test
    public void test8() {
        assertEq(SolitaireType.FREECELL,GameEnum.getStatusTypeByName(GameEnum.FREECELL.getNumber()).getSolitaireType());
    }
    @Test
    public void test9() {
        assertEq(SolitaireType.SPIDER,GameEnum.getStatusTypeByName(GameEnum.SPIDER.getNumber()).getSolitaireType());
    }
    @Test
    public void isContentObject1() {
        GameBelote b_ = new GameBelote();
        b_.setType(GameType.RANDOM);
        b_.setDeal(new DealBelote());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterBeloteUtil.setGameBelote(b_)));
    }
    @Test
    public void isContentObject2() {
        GamePresident p_ = new GamePresident();
        p_.setType(GameType.RANDOM);
        p_.setDeal(new DealPresident());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterPresidentUtil.setGamePresident(p_)));
    }
    @Test
    public void isContentObject3() {
        GameTarot t_ = new GameTarot();
        t_.setType(GameType.RANDOM);
        t_.setDeal(new DealTarot());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterTarotUtil.setGameTarot(t_)));
    }
    @Test
    public void isContentObject4() {
        DealClassic s_ = new DealClassic();
        s_.setHandsBegin(new CustList<HandSolitaire>());
        s_.setActions(new CustList<ActionSolitaire>());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterSolitaireUtil.setGameSolitaire(s_)));
    }
    @Test
    public void isContentObject5() {
        DealFreeCell s_ = new DealFreeCell();
        s_.setHandsBegin(new CustList<HandSolitaire>());
        s_.setActions(new CustList<ActionSolitaire>());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterSolitaireUtil.setGameSolitaire(s_)));
    }
    @Test
    public void isContentObject6() {
        DealSpider s_ = new DealSpider();
        s_.setHandsBegin(new CustList<HandSolitaire>());
        s_.setActions(new CustList<ActionSolitaire>());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterSolitaireUtil.setGameSolitaire(s_)));
    }
    @Test
    public void isContentObject7() {
        assertFalse(DocumentReaderCardsUnionUtil.isContentObject("<_/>"));
    }
    @Test
    public void isContentObject8() {
        assertFalse(DocumentReaderCardsUnionUtil.isContentObject(""));
    }
    @Test
    public void save() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonFile(MessagesCardGames.initAppliTr(en_), MessagesCommonFile.en());
        MessagesCardGames.appendCommonFile(MessagesCardGames.initAppliTr(fr_), MessagesCommonFile.fr());
        Games.setMessages(new ResultsGame(),en_);
        Games.setMessages(new ResultsGame(),fr_);
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new MockRand(new DefaultGenerator(new CustomSeedGene(dbs(0.75)))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        new SoftParams().sauvegarder("_", tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
    @Test
    public void prog1() {
        Games g_ = new Games();
        g_.jouerBelote(new GameBelote());
        assertTrue(g_.enCoursDePartie());
        assertTrue(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
        assertFalse(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void prog2() {
        Games g_ = new Games();
        g_.jouerPresident(new GamePresident());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertTrue(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
        assertFalse(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void prog3() {
        Games g_ = new Games();
        g_.jouerTarot(new GameTarot());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertTrue(g_.enCoursDePartieTarot());
        assertFalse(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void prog4() {
        Games g_ = new Games();
        g_.jouerSolitaire(new DealClassic());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
        assertTrue(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void prog5() {
        Games g_ = new Games();
        g_.jouerSolitaire(new DealFreeCell());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
        assertTrue(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void prog6() {
        Games g_ = new Games();
        g_.jouerSolitaire(new DealSpider());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
        assertTrue(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void prog7() {
        Games g_ = new Games();
        assertFalse(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
        assertFalse(g_.enCoursDePartieSolitaire());
    }
    @Test
    public void toString1() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(en_), MessagesCommonCards.en());
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(fr_), MessagesCommonCards.fr());
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        assertFalse(Games.toString(h_,en_).isEmpty());
        assertFalse(Games.toString(h_,fr_).isEmpty());
    }
    @Test
    public void toString2() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(en_), MessagesCommonCards.en());
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(fr_), MessagesCommonCards.fr());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_1);
        assertFalse(Games.toString(h_,en_).isEmpty());
        assertFalse(Games.toString(h_,fr_).isEmpty());
    }
    @Test
    public void toString3() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(en_), MessagesCommonCards.en());
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(fr_), MessagesCommonCards.fr());
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.SPADE_1);
        assertFalse(Games.toString(h_,en_).isEmpty());
        assertFalse(Games.toString(h_,fr_).isEmpty());
    }
    @Test
    public void toString4() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(en_), MessagesCommonCards.en());
        MessagesCardGames.appendCommonCards(MessagesCardGames.initAppliTr(fr_), MessagesCommonCards.fr());
        assertFalse(Games.toCardString(0,en_).isEmpty());
        assertFalse(Games.toCardString(0,fr_).isEmpty());
    }
//    @Test
//    public void toString4() {
//        assertFalse(Games.getSymbol(CardBelote.HEART_1,StringUtil.EN).isEmpty());
//        assertFalse(Games.getSymbol(CardBelote.HEART_1,StringUtil.FR).isEmpty());
//    }
//    @Test
//    public void toString5() {
//        assertFalse(Games.getSymbol(CardPresident.HEART_1,StringUtil.EN).isEmpty());
//        assertFalse(Games.getSymbol(CardPresident.HEART_1,StringUtil.FR).isEmpty());
//    }
//    @Test
//    public void toString6() {
//        assertFalse(Games.getSymbol(CardTarot.HEART_1,StringUtil.EN).isEmpty());
//        assertFalse(Games.getSymbol(CardTarot.HEART_1,StringUtil.FR).isEmpty());
//    }
//    @Test
//    public void toString7() {
//        assertFalse(Games.getSymbol(CardBelote.HEART_QUEEN,StringUtil.EN).isEmpty());
//        assertFalse(Games.getSymbol(CardBelote.HEART_QUEEN,StringUtil.FR).isEmpty());
//    }
//    @Test
//    public void toString8() {
//        assertFalse(Games.getSymbol(CardPresident.HEART_QUEEN,StringUtil.EN).isEmpty());
//        assertFalse(Games.getSymbol(CardPresident.HEART_QUEEN,StringUtil.FR).isEmpty());
//    }
//    @Test
//    public void toString9() {
//        assertFalse(Games.getSymbol(CardTarot.HEART_QUEEN,StringUtil.EN).isEmpty());
//        assertFalse(Games.getSymbol(CardTarot.HEART_QUEEN,StringUtil.FR).isEmpty());
//    }
    @Test
    public void toString10() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(en_), MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(fr_), MessagesBelote.fr());
        assertFalse(Games.toString(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP,en_).isEmpty());
        assertFalse(Games.toString(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP,fr_).isEmpty());
    }
    @Test
    public void toString11() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonFile(MessagesCardGames.initAppliTr(en_), MessagesCommonFile.en());
        MessagesCardGames.appendCommonFile(MessagesCardGames.initAppliTr(fr_), MessagesCommonFile.fr());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.getAppliTr(en_),MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.getAppliTr(fr_),MessagesBelote.fr());
        assertFalse(Games.toString(new BidBeloteSuit(), en_).isEmpty());
        assertFalse(Games.toString(new BidBeloteSuit(), fr_).isEmpty());
    }
    @Test
    public void toString12() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(en_), MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(fr_), MessagesBelote.fr());
        assertFalse(Games.toString(BidBelote.FOLD,en_).isEmpty());
        assertFalse(Games.toString(BidBelote.FOLD,fr_).isEmpty());
    }
    @Test
    public void toString13() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(en_), MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(fr_), MessagesBelote.fr());
        assertFalse(Games.toString(DeclaresBelote.HUNDRED, en_).isEmpty());
        assertFalse(Games.toString(DeclaresBelote.HUNDRED,fr_).isEmpty());
    }
    @Test
    public void toString14() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(en_), MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(fr_), MessagesBelote.fr());
        assertFalse(Games.toStringBeloteReb(en_).isEmpty());
        assertFalse(Games.toStringBeloteReb(fr_).isEmpty());
    }
    @Test
    public void toString15() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(en_), MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(fr_), MessagesBelote.fr());
        assertFalse(Games.toStringBonusBelote(en_).isEmpty());
        assertFalse(Games.toStringBonusBelote(fr_).isEmpty());
    }
    @Test
    public void toString16() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonPresident(MessagesCardGames.initAppliTr(en_), MessagesPresident.en());
        MessagesCardGames.appendCommonPresident(MessagesCardGames.initAppliTr(fr_), MessagesPresident.fr());
        assertFalse(Games.toString(Playing.CAN_PLAY,en_).isEmpty());
        assertFalse(Games.toString(Playing.CAN_PLAY,fr_).isEmpty());
    }
    @Test
    public void toString17() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonPresident(MessagesCardGames.initAppliTr(en_), MessagesPresident.en());
        MessagesCardGames.appendCommonPresident(MessagesCardGames.initAppliTr(fr_), MessagesPresident.fr());
        assertFalse(Games.toString(EqualtyPlaying.FORBIDDEN, en_).isEmpty());
        assertFalse(Games.toString(EqualtyPlaying.FORBIDDEN,fr_).isEmpty());
    }
    @Test
    public void toString18() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(ModeTarot.NORMAL, en_).isEmpty());
        assertFalse(Games.toString(ModeTarot.NORMAL,fr_).isEmpty());
    }
    @Test
    public void toString19() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonChTarot(MessagesCardGames.initAppliTr(en_), MessagesChoiceTarot.en());
        MessagesCardGames.appendCommonChTarot(MessagesCardGames.initAppliTr(fr_), MessagesChoiceTarot.fr());
        assertFalse(Games.toString(ChoiceTarot.HUNT_SMALL, en_).isEmpty());
        assertFalse(Games.toString(ChoiceTarot.HUNT_SMALL,fr_).isEmpty());
        assertFalse(Games.toString(ChoiceTarot.SAVE_SMALL, en_).isEmpty());
        assertFalse(Games.toString(ChoiceTarot.SAVE_SMALL,fr_).isEmpty());
        assertFalse(Games.toString(ChoiceTarot.LEAD_SMALL_BOUND, en_).isEmpty());
        assertFalse(Games.toString(ChoiceTarot.LEAD_SMALL_BOUND,fr_).isEmpty());
    }
    @Test
    public void toString20() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(BidTarot.SLAM, en_).isEmpty());
        assertFalse(Games.toString(BidTarot.SLAM,fr_).isEmpty());
    }
    @Test
    public void toString21() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(Handfuls.FOUR, en_).isEmpty());
        assertFalse(Games.toString(Handfuls.FOUR,fr_).isEmpty());
    }
    @Test
    public void toString22() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(Miseres.LOW_CARDS, en_).isEmpty());
        assertFalse(Games.toString(Miseres.LOW_CARDS,fr_).isEmpty());
    }
    @Test
    public void toString23() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL, en_).isEmpty());
        assertFalse(Games.toString(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL,fr_).isEmpty());
    }
    @Test
    public void toString24() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(EndDealTarot.ATTACK_WIN, en_).isEmpty());
        assertFalse(Games.toString(EndDealTarot.ATTACK_WIN,fr_).isEmpty());
    }
    @Test
    public void toString25() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(en_), MessagesTarot.en());
        MessagesCardGames.appendCommonTarot(MessagesCardGames.initAppliTr(fr_), MessagesTarot.fr());
        assertFalse(Games.toString(BonusTarot.SLAM, en_).isEmpty());
        assertFalse(Games.toString(BonusTarot.SLAM,fr_).isEmpty());
        assertFalse(Games.toString(BonusTarot.SMALL_BOUND, en_).isEmpty());
        assertFalse(Games.toString(BonusTarot.SMALL_BOUND,fr_).isEmpty());
    }
    @Test
    public void toString26() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonFile(MessagesCardGames.initAppliTr(en_), MessagesCommonFile.en());
        MessagesCardGames.appendCommonFile(MessagesCardGames.initAppliTr(fr_), MessagesCommonFile.fr());
        assertFalse(Games.toString(Role.TAKER, en_).isEmpty());
        assertFalse(Games.toString(Role.TAKER,fr_).isEmpty());
    }
    @Test
    public void toString27() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonMix(MessagesCardGames.initAppliTr(en_), MessagesCommonMix.en());
        MessagesCardGames.appendCommonMix(MessagesCardGames.initAppliTr(fr_), MessagesCommonMix.fr());
        assertFalse(Games.toString(MixCardsChoice.EACH_LAUNCHING, en_).isEmpty());
        assertFalse(Games.toString(MixCardsChoice.EACH_LAUNCHING,fr_).isEmpty());
    }
    @Test
    public void toString28() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(en_), MessagesBelote.en());
        MessagesCardGames.appendCommonBelote(MessagesCardGames.initAppliTr(fr_), MessagesBelote.fr());
        assertFalse(Games.toString(DealingBelote.COINCHE_2_VS_2,en_).isEmpty());
        assertFalse(Games.toString(DealingBelote.COINCHE_2_VS_2,fr_).isEmpty());
    }
    @Test
    public void isSameTeam() {
        assertTrue(new Games().isSameTeam(Bytes.newList()));
    }
    @Test
    public void saveAll1() {
        Games n_ = new Games();
        GameBelote b_ = new GameBelote();
        b_.setType(GameType.RANDOM);
        b_.setDeal(new DealBelote());
        n_.getPartiesBelote().add(b_);
        GameTarot t_ = new GameTarot();
        t_.setType(GameType.RANDOM);
        t_.setDeal(new DealTarot());
        n_.getPartiesTarot().add(t_);
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        p_.setType(GameType.RANDOM);
        n_.getPartiesPresident().add(p_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
    }
    @Test
    public void saveAll2() {
        Games n_ = new Games();
        CardGamesStream s_ = saveFile(n_);
        assertFalse(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
    }
    @Test
    public void saveBelote() {
        Games n_ = new Games();
        GameBelote b_ = new GameBelote();
        b_.setDeal(new DealBelote());
        b_.setType(GameType.RANDOM);
        n_.getPartiesBelote().add(b_);
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
        s_.getCardGamesCrud().belote("_");
    }
    @Test
    public void savePresident() {
        Games n_ = new Games();
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        p_.setType(GameType.RANDOM);
        n_.getPartiesPresident().add(p_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
        s_.getCardGamesCrud().president("_");
    }
    @Test
    public void saveTarot() {
        Games n_ = new Games();
        GameTarot t_ = new GameTarot();
        t_.setDeal(new DealTarot());
        t_.setType(GameType.RANDOM);
        n_.getPartiesTarot().add(t_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
        s_.getCardGamesCrud().tarot("_");
    }
    @Test
    public void saveSolitaireClassic() {
        Games n_ = new Games();
        DealClassic d_ = new DealClassic();
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.setActions(new CustList<ActionSolitaire>());
        n_.getPartiesSolitaire().add(d_);
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
        s_.getCardGamesCrud().solitaire("_");
    }
    @Test
    public void saveSolitaireFreeCell() {
        Games n_ = new Games();
        DealFreeCell d_ = new DealFreeCell();
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.setActions(new CustList<ActionSolitaire>());
        n_.getPartiesSolitaire().add(d_);
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
        s_.getCardGamesCrud().solitaire("_");
    }
    @Test
    public void saveSolitaireSpider() {
        Games n_ = new Games();
        DealSpider d_ = new DealSpider();
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.setActions(new CustList<ActionSolitaire>());
        n_.getPartiesSolitaire().add(d_);
        CardGamesStream s_ = saveFile(n_);
        assertTrue(s_.getCardGamesCrud().getProgramInfos().getFileCoreStream().newFile("_").exists());
        s_.getCardGamesCrud().solitaire("_");
    }
    @Test
    public void messagesLoad() {
        MockProgramInfos pr_ = pr(0,0);
        update(pr_);
        TranslationsLg en_ = pr_.getTranslations().getMapping().getVal(StringUtil.EN);
        TranslationsLg fr_ = pr_.getTranslations().getMapping().getVal(StringUtil.FR);
        assertFalse(MessagesCardGames.getAppliTr(en_).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getEditorTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogBeloteTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogPresidentTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogTarotTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogNicknameTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogSoftTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogDisplayTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogHelpTr(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getMainGame(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getSimuGame(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getMenus(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getMulti(MessagesCardGames.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getAppliTr(fr_).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getEditorTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogBeloteTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogPresidentTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogTarotTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogNicknameTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogSoftTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogDisplayTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getDialogHelpTr(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getMainGame(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getSimuGame(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getMenus(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesCardGames.getMulti(MessagesCardGames.getAppliTr(fr_)).getMapping().isEmpty());
    }

    private CardGamesStream saveFile(Games _n) {
        MockProgramInfos pr_ = pr(1, 2);
        CardGamesStream cs_ = new CardGamesStream(pr_,"_/");
        _n.sauvegarderPartieEnCours(cs_,"_");
        return cs_;
    }

}
