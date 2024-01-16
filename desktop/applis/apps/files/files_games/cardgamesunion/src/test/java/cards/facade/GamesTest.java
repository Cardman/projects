package cards.facade;

import cards.belote.*;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.MixCardsChoice;
import cards.consts.Role;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.president.*;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.TextAnswerValue;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.stream.core.TechStreams;
import code.util.Bytes;
import code.util.IdList;
import org.junit.Test;

public final class GamesTest extends EquallableCardsFileUtil {
    @Test
    public void games() {
        assertFalse(GameEnum.BELOTE.toString("en").isEmpty());
        assertFalse(GameEnum.PRESIDENT.toString("en").isEmpty());
        assertFalse(GameEnum.TAROT.toString("en").isEmpty());
        assertFalse(GameEnum.BELOTE.toString("fr").isEmpty());
        assertFalse(GameEnum.PRESIDENT.toString("fr").isEmpty());
        assertFalse(GameEnum.TAROT.toString("fr").isEmpty());
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
        n_.getPartiesBelote().add(b_);
        GameTarot t_ = new GameTarot();
        t_.setDeal(new DealTarot());
        n_.getPartiesTarot().add(t_);
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
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
    public void isContentObject1() {
        GameBelote b_ = new GameBelote();
        b_.setDeal(new DealBelote());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterBeloteUtil.setGameBelote(b_)));
    }
    @Test
    public void isContentObject2() {
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterPresidentUtil.setGamePresident(p_)));
    }
    @Test
    public void isContentObject3() {
        GameTarot t_ = new GameTarot();
        t_.setDeal(new DealTarot());
        assertTrue(DocumentReaderCardsUnionUtil.isContentObject(DocumentWriterTarotUtil.setGameTarot(t_)));
    }
    @Test
    public void isContentObject4() {
        assertFalse(DocumentReaderCardsUnionUtil.isContentObject("<_/>"));
    }
    @Test
    public void isContentObject5() {
        assertFalse(DocumentReaderCardsUnionUtil.isContentObject(""));
    }
    @Test
    public void save() {
        Games.setMessages(new ResultsBelote(),"en");
        Games.setMessages(new ResultsBelote(),"fr");
        Games.setMessages(new ResultsPresident(),"en");
        Games.setMessages(new ResultsPresident(),"fr");
        Games.setMessages(new ResultsTarot(),"en");
        Games.setMessages(new ResultsTarot(),"fr");
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
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
    }
    @Test
    public void prog2() {
        Games g_ = new Games();
        g_.jouerPresident(new GamePresident());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertTrue(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
    }
    @Test
    public void prog3() {
        Games g_ = new Games();
        g_.jouerTarot(new GameTarot());
        assertTrue(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertTrue(g_.enCoursDePartieTarot());
    }
    @Test
    public void prog4() {
        Games g_ = new Games();
        assertFalse(g_.enCoursDePartie());
        assertFalse(g_.enCoursDePartieBelote());
        assertFalse(g_.enCoursDePartiePresident());
        assertFalse(g_.enCoursDePartieTarot());
    }
    @Test
    public void toString1() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        assertFalse(Games.toString(h_,"en").isEmpty());
        assertFalse(Games.toString(h_,"fr").isEmpty());
    }
    @Test
    public void toString2() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_1);
        assertFalse(Games.toString(h_,"en").isEmpty());
        assertFalse(Games.toString(h_,"fr").isEmpty());
    }
    @Test
    public void toString3() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.SPADE_1);
        assertFalse(Games.toString(h_,"en").isEmpty());
        assertFalse(Games.toString(h_,"fr").isEmpty());
    }
//    @Test
//    public void toString4() {
//        assertFalse(Games.getSymbol(CardBelote.HEART_1,"en").isEmpty());
//        assertFalse(Games.getSymbol(CardBelote.HEART_1,"fr").isEmpty());
//    }
//    @Test
//    public void toString5() {
//        assertFalse(Games.getSymbol(CardPresident.HEART_1,"en").isEmpty());
//        assertFalse(Games.getSymbol(CardPresident.HEART_1,"fr").isEmpty());
//    }
//    @Test
//    public void toString6() {
//        assertFalse(Games.getSymbol(CardTarot.HEART_1,"en").isEmpty());
//        assertFalse(Games.getSymbol(CardTarot.HEART_1,"fr").isEmpty());
//    }
//    @Test
//    public void toString7() {
//        assertFalse(Games.getSymbol(CardBelote.HEART_QUEEN,"en").isEmpty());
//        assertFalse(Games.getSymbol(CardBelote.HEART_QUEEN,"fr").isEmpty());
//    }
//    @Test
//    public void toString8() {
//        assertFalse(Games.getSymbol(CardPresident.HEART_QUEEN,"en").isEmpty());
//        assertFalse(Games.getSymbol(CardPresident.HEART_QUEEN,"fr").isEmpty());
//    }
//    @Test
//    public void toString9() {
//        assertFalse(Games.getSymbol(CardTarot.HEART_QUEEN,"en").isEmpty());
//        assertFalse(Games.getSymbol(CardTarot.HEART_QUEEN,"fr").isEmpty());
//    }
    @Test
    public void toString10() {
        assertFalse(Games.toString(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP,"en").isEmpty());
        assertFalse(Games.toString(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP,"fr").isEmpty());
    }
    @Test
    public void toString11() {
        assertFalse(Games.toString(new BidBeloteSuit(),"en").isEmpty());
        assertFalse(Games.toString(new BidBeloteSuit(),"fr").isEmpty());
    }
    @Test
    public void toString12() {
        assertFalse(Games.toString(BidBelote.FOLD,"en").isEmpty());
        assertFalse(Games.toString(BidBelote.FOLD,"fr").isEmpty());
    }
    @Test
    public void toString13() {
        assertFalse(Games.toString(DeclaresBelote.HUNDRED, "en").isEmpty());
        assertFalse(Games.toString(DeclaresBelote.HUNDRED,"fr").isEmpty());
    }
    @Test
    public void toString14() {
        assertFalse(Games.toStringBeloteReb("en").isEmpty());
        assertFalse(Games.toStringBeloteReb("fr").isEmpty());
    }
    @Test
    public void toString15() {
        assertFalse(Games.toStringBonusBelote("en").isEmpty());
        assertFalse(Games.toStringBonusBelote("fr").isEmpty());
    }
    @Test
    public void toString16() {
        assertFalse(Games.toString(Playing.CAN_PLAY,"en").isEmpty());
        assertFalse(Games.toString(Playing.CAN_PLAY,"fr").isEmpty());
    }
    @Test
    public void toString17() {
        assertFalse(Games.toString(EqualtyPlaying.FORBIDDEN, "en").isEmpty());
        assertFalse(Games.toString(EqualtyPlaying.FORBIDDEN,"fr").isEmpty());
    }
    @Test
    public void toString18() {
        assertFalse(Games.toString(ModeTarot.NORMAL, "en").isEmpty());
        assertFalse(Games.toString(ModeTarot.NORMAL,"fr").isEmpty());
    }
    @Test
    public void toString19() {
        assertFalse(Games.toString(ChoiceTarot.HUNT_SMALL, "en").isEmpty());
        assertFalse(Games.toString(ChoiceTarot.HUNT_SMALL,"fr").isEmpty());
    }
    @Test
    public void toString20() {
        assertFalse(Games.toString(BidTarot.SLAM, "en").isEmpty());
        assertFalse(Games.toString(BidTarot.SLAM,"fr").isEmpty());
    }
    @Test
    public void toString21() {
        assertFalse(Games.toString(Handfuls.FOUR, "en").isEmpty());
        assertFalse(Games.toString(Handfuls.FOUR,"fr").isEmpty());
    }
    @Test
    public void toString22() {
        assertFalse(Games.toString(Miseres.LOW_CARDS, "en").isEmpty());
        assertFalse(Games.toString(Miseres.LOW_CARDS,"fr").isEmpty());
    }
    @Test
    public void toString23() {
        assertFalse(Games.toString(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL, "en").isEmpty());
        assertFalse(Games.toString(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL,"fr").isEmpty());
    }
    @Test
    public void toString24() {
        assertFalse(Games.toString(EndDealTarot.ATTACK_WIN, "en").isEmpty());
        assertFalse(Games.toString(EndDealTarot.ATTACK_WIN,"fr").isEmpty());
    }
    @Test
    public void toString25() {
        assertFalse(Games.toString(BonusTarot.SLAM, "en").isEmpty());
        assertFalse(Games.toString(BonusTarot.SLAM,"fr").isEmpty());
    }
    @Test
    public void toString26() {
        assertFalse(Games.toString(Role.TAKER, "en").isEmpty());
        assertFalse(Games.toString(Role.TAKER,"fr").isEmpty());
    }
    @Test
    public void toString27() {
        assertFalse(Games.toString(MixCardsChoice.EACH_LAUNCHING, "en").isEmpty());
        assertFalse(Games.toString(MixCardsChoice.EACH_LAUNCHING,"fr").isEmpty());
    }
    @Test
    public void isSameTeam() {
        assertTrue(new Games().isSameTeam(Bytes.newList()));
    }
    @Test
    public void saveAll1() {
        Games n_ = new Games();
        GameBelote b_ = new GameBelote();
        b_.setDeal(new DealBelote());
        n_.getPartiesBelote().add(b_);
        GameTarot t_ = new GameTarot();
        t_.setDeal(new DealTarot());
        n_.getPartiesTarot().add(t_);
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        n_.getPartiesPresident().add(p_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        n_.sauvegarderPartieEnCours("_",tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
    @Test
    public void saveAll2() {
        Games n_ = new Games();
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        n_.sauvegarderPartieEnCours("_",tech_);
        assertFalse(new MockFileCoreStream(set_).newFile("_").exists());
    }
    @Test
    public void saveBelote() {
        Games n_ = new Games();
        GameBelote b_ = new GameBelote();
        b_.setDeal(new DealBelote());
        n_.getPartiesBelote().add(b_);
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        n_.sauvegarderPartieEnCours("_",tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
    @Test
    public void savePresident() {
        Games n_ = new Games();
        GamePresident p_ = new GamePresident();
        p_.setDeal(new DealPresident());
        n_.getPartiesPresident().add(p_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        n_.sauvegarderPartieEnCours("_",tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
    @Test
    public void saveTarot() {
        Games n_ = new Games();
        GameTarot t_ = new GameTarot();
        t_.setDeal(new DealTarot());
        n_.getPartiesTarot().add(t_);
        n_.setRulesBelote(new RulesBelote());
        n_.setRulesPresident(new RulesPresident());
        n_.setRulesTarot(new RulesTarot());
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        n_.sauvegarderPartieEnCours("_",tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
    @Test
    public void messagesLoad() {
        MockProgramInfos pr_ = pr(0,0);
        update(pr_);
        assertFalse(Games.getAppliTr(pr_.getTranslations().getMapping().getVal("en")).getMapping().isEmpty());
        assertFalse(Games.getAppliTr(pr_.getTranslations().getMapping().getVal("fr")).getMapping().isEmpty());
    }
}
