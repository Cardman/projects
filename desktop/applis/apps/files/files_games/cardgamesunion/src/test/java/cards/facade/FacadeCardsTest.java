package cards.facade;

import cards.belote.RulesBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.MixCardsChoice;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.RulesTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockProgramInfos;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FacadeCardsTest extends EquallableCardsFileUtil {
    @Test
    public void install1() {
        MockProgramInfos pr_ = prTmp("_/",1, 2);
        String tempFolder_ = StreamFolderFile.getTempFolder(pr_,"_/");
        FacadeCards.install(tempFolder_,pr_);
        assertEq(11,pr_.getFileCoreStream().newFile(FacadeCards.beloteStack(tempFolder_)).lastModified());
        assertEq(13,pr_.getFileCoreStream().newFile(FacadeCards.tarotStack(tempFolder_)).lastModified());
        assertEq(15,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,1)).lastModified());
        assertEq(17,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,2)).lastModified());
        assertEq(19,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,3)).lastModified());
        assertEq(21,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,4)).lastModified());
        assertEq(23,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,5)).lastModified());
        assertEq(25,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,6)).lastModified());
        assertEq(27,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,7)).lastModified());
        assertEq(29,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,8)).lastModified());
        assertEq(31,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,9)).lastModified());
        assertEq(33,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,10)).lastModified());
        assertEq(35,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,11)).lastModified());
        assertEq(37,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,12)).lastModified());
        assertEq(39,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,13)).lastModified());
        assertEq(41,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,14)).lastModified());
        assertEq(43,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,15)).lastModified());
        assertEq(45,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,16)).lastModified());
        assertEq(47,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,17)).lastModified());
        assertEq(49,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,18)).lastModified());
        assertEq(51,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,19)).lastModified());
        assertEq(53,pr_.getFileCoreStream().newFile(FacadeCards.stack(tempFolder_)).lastModified());
    }
    @Test
    public void install2() {
        MockProgramInfos pr_ = prTmp("_/",1, 2);
        String tempFolder_ = StreamFolderFile.getTempFolder(pr_,"_/");
        FacadeCards.install(tempFolder_,pr_);
        FacadeCards.install(tempFolder_,pr_);
        assertEq(11,pr_.getFileCoreStream().newFile(FacadeCards.beloteStack(tempFolder_)).lastModified());
        assertEq(13,pr_.getFileCoreStream().newFile(FacadeCards.tarotStack(tempFolder_)).lastModified());
        assertEq(15,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,1)).lastModified());
        assertEq(17,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,2)).lastModified());
        assertEq(19,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,3)).lastModified());
        assertEq(21,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,4)).lastModified());
        assertEq(23,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,5)).lastModified());
        assertEq(25,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,6)).lastModified());
        assertEq(27,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,7)).lastModified());
        assertEq(29,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,8)).lastModified());
        assertEq(31,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,9)).lastModified());
        assertEq(33,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,10)).lastModified());
        assertEq(35,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,11)).lastModified());
        assertEq(37,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,12)).lastModified());
        assertEq(39,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,13)).lastModified());
        assertEq(41,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,14)).lastModified());
        assertEq(43,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,15)).lastModified());
        assertEq(45,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,16)).lastModified());
        assertEq(47,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,17)).lastModified());
        assertEq(49,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,18)).lastModified());
        assertEq(51,pr_.getFileCoreStream().newFile(FacadeCards.presidentStack(tempFolder_,19)).lastModified());
        assertEq(53,pr_.getFileCoreStream().newFile(FacadeCards.stack(tempFolder_)).lastModified());
    }
    @Test
    public void init1() {
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
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
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
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
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
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
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
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
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
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
        assertEq("0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0",ls_.get(1));
        assertEq("0",ls_.get(2));
    }
    @Test
    public void retrieveLines2() {
        StringList ls_ = FacadeCards.retrieveLines("");
        assertEq(3, ls_.size());
        assertEq("0",ls_.get(0));
        assertEq("0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0",ls_.get(1));
        assertEq("0",ls_.get(2));
    }
    @Test
    public void retrieveLines3() {
        StringList ls_ = FacadeCards.retrieveLines("8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n7");
        assertEq(3, ls_.size());
        assertEq("8",ls_.get(0));
        assertEq("5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5",ls_.get(1));
        assertEq("7",ls_.get(2));
    }
    @Test
    public void changerNombreDePartiesEnQuittant1() {
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n7",pr_.getStreams());
        f_.getReglesBelote().getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        f_.getReglesPresident().getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        f_.getReglesTarot().getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        f_.changerNombreDePartiesEnQuittant("_/",pr_);
        assertEq("8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n7",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void changerNombreDePartiesEnQuittant2() {
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards f_ = facade(pr_);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n7",pr_.getStreams());
        f_.getReglesBelote().getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        f_.getReglesPresident().getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        f_.getReglesTarot().getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        f_.changerNombreDePartiesEnQuittant("_/",pr_);
        assertEq("0\n" +
                "0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0\n" +
                "0",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void changerNombreDeParties1() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n7",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.BELOTE,8,"_/",pr_,0);
        assertEq("9\n" +
                "5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n" +
                "7",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void changerNombreDeParties2() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n9",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.TAROT,9,"_/",pr_,0);
        assertEq("8\n" +
                "5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n" +
                "10",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void changerNombreDeParties3() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n9",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.PRESIDENT,5,"_/",pr_,4);
        assertEq("8\n" +
                "5_5_5_6_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n" +
                "9",StreamTextFile.contentsOfFile(fileName_,pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void chargerNombreDeParties1() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n7",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.BELOTE,8,"_/",pr_,0);
        assertEq(9,FacadeCards.chargerNombreDeParties(GameEnum.BELOTE,"_/",pr_,0));
    }
    @Test
    public void chargerNombreDeParties2() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n9",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.TAROT,9,"_/",pr_,0);
        assertEq(10,FacadeCards.chargerNombreDeParties(GameEnum.TAROT,"_/",pr_,0));
    }
    @Test
    public void chargerNombreDeParties3() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n9",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.PRESIDENT,5,"_/",pr_,4);
        assertEq(6,FacadeCards.chargerNombreDeParties(GameEnum.PRESIDENT,"_/",pr_,4));
    }
    @Test
    public void chargerNombreDeParties4() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n9",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.PRESIDENT,5,"_/",pr_,4);
        assertEq(0,FacadeCards.chargerNombreDeParties(GameEnum.PRESIDENT,"_/",pr_,RulesPresident.getNbMaxStacksPlayers()+1));
    }
    @Test
    public void chargerNombreDeParties5() {
        MockProgramInfos pr_ = pr(1, 2);
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER+StreamTextFile.SEPARATEUR).mkdirs();
        String fileName_ = FacadeCards.stack("_/");
        StreamTextFile.saveTextFile(fileName_,"8\n5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5_5\n9",pr_.getStreams());
        FacadeCards.changerNombreDeParties(GameEnum.PRESIDENT,5,"_/",pr_,4);
        assertEq(5,FacadeCards.chargerNombreDeParties(GameEnum.PRESIDENT,"_/",pr_,RulesPresident.getNbMaxStacksPlayers()));
    }
    @Test
    public void chargerNombreDeParties6() {
        MockProgramInfos pr_ = pr(1, 2);
        assertEq(0,FacadeCards.chargerNombreDeParties(GameEnum.BELOTE,"_/",pr_,0));
    }
    @Test
    public void chargerNombreDeParties7() {
        MockProgramInfos pr_ = pr(1, 2);
        String fileName_ = FacadeCards.stack("_/");
        pr_.getFileCoreStream().newFile("_/"+FacadeCards.DECK_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(fileName_,"",pr_.getStreams());
        assertEq(0,FacadeCards.chargerNombreDeParties(GameEnum.TAROT,"_/",pr_,0));
    }
    private FacadeCards facade(AbstractProgramInfos _pr) {
        DefNicknamesCrud def_ = new DefNicknamesCrud(_pr);
        def_.setTempFolder("_");
        return new FacadeCards(def_);
    }
}
