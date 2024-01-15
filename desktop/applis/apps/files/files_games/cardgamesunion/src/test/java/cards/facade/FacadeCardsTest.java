package cards.facade;

import cards.belote.RulesBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.MixCardsChoice;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.RulesTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.mock.MockProgramInfos;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FacadeCardsTest extends EquallableCardsFileUtil {
    @Test
    public void init1() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        RulesBelote rb_ = new RulesBelote();
        rb_.getAllowedDeclares().clear();
        f_.setReglesBelote(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("_",FacadeCards.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(rb_),pr_.getStreams());
        f_.init("_", pr_,"");
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("_",FacadeCards.RULES_BELOTE)).lastModified());
        assertTrue(f_.getReglesBelote().isValidRules());
    }
    @Test
    public void init2() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        RulesPresident rb_ = new RulesPresident();
        rb_.setNbStacks(-1);
        f_.setReglesPresident(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("_",FacadeCards.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(rb_),pr_.getStreams());
        f_.init("_", pr_,"");
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("_",FacadeCards.RULES_PRESIDENT)).lastModified());
        assertTrue(f_.getReglesPresident().isValidRules());
    }
    @Test
    public void init3() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        RulesTarot rb_ = new RulesTarot();
        rb_.getAllowedBids().clear();
        f_.setReglesTarot(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("_",FacadeCards.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(rb_),pr_.getStreams());
        f_.init("_", pr_,"");
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("_",FacadeCards.RULES_TAROT)).lastModified());
        assertTrue(f_.getReglesTarot().isValidRules());
    }
    @Test
    public void init4() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        Nicknames rb_ = new Nicknames();
        rb_.getPseudosBelote().clear();
        rb_.getPseudosPresident().clear();
        rb_.getPseudosTarot().clear();
        f_.setPseudosJoueurs(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("_",FacadeCards.PLAYERS), DocumentWriterCardsUnionUtil.setNicknames(rb_),pr_.getStreams());
        f_.init("_", pr_,"");
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("_",FacadeCards.PLAYERS)).lastModified());
        assertTrue(f_.getPseudosJoueurs().isValidNicknames());
    }
    @Test
    public void init5() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        Nicknames rb_ = new Nicknames("");
        f_.setPseudosJoueurs(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("_",FacadeCards.PLAYERS), DocumentWriterCardsUnionUtil.setNicknames(rb_),pr_.getStreams());
        f_.init("_", pr_,"");
        assertEq(5,pr_.getFileCoreStream().newFile(StringUtil.concat("_",FacadeCards.PLAYERS)).lastModified());
        assertTrue(f_.getPseudosJoueurs().isValidNicknames());
    }
    @Test
    public void retrieveLines1() {
        StringList ls_ = FacadeCards.retrieveLines(null);
        assertEq(3, ls_.size());
        assertEq("0",ls_.get(0));
        assertEq("0",ls_.get(1));
        assertEq("0",ls_.get(2));
    }
    @Test
    public void retrieveLines2() {
        StringList ls_ = FacadeCards.retrieveLines("");
        assertEq(3, ls_.size());
        assertEq("0",ls_.get(0));
        assertEq("0",ls_.get(1));
        assertEq("0",ls_.get(2));
    }
    @Test
    public void retrieveLines3() {
        StringList ls_ = FacadeCards.retrieveLines("8\n5\n7");
        assertEq(3, ls_.size());
        assertEq("8",ls_.get(0));
        assertEq("5",ls_.get(1));
        assertEq("7",ls_.get(2));
    }
    @Test
    public void changerNombreDePartiesEnQuittant1() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = StringUtil.concat("_/",FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,FacadeCards.DECK_FILE);
        StreamTextFile.saveTextFile(fileName_,"8\n5\n7",pr_.getStreams());
        f_.getReglesBelote().getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        f_.getReglesPresident().getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        f_.getReglesTarot().getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        f_.changerNombreDePartiesEnQuittant("_/",pr_);
        assertEq("8\n5\n7",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void changerNombreDePartiesEnQuittant2() {
        FacadeCards f_ = new FacadeCards();
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = StringUtil.concat("_/",FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,FacadeCards.DECK_FILE);
        StreamTextFile.saveTextFile(fileName_,"8\n5\n7",pr_.getStreams());
        f_.getReglesBelote().getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        f_.getReglesPresident().getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        f_.getReglesTarot().getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        f_.changerNombreDePartiesEnQuittant("_/",pr_);
        assertEq("0\n0\n0",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
}
