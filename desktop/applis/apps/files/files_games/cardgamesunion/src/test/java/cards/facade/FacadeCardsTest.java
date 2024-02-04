package cards.facade;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.belote.sml.*;
import cards.consts.*;
import cards.facade.enumerations.*;
import cards.facade.sml.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.util.TranslationsLg;
import code.stream.*;
import code.util.*;
import code.util.core.*;
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
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        nicknames(pr_);
        FacadeCards f_ = facade(pr_);
        RulesBelote rb_ = new RulesBelote();
        rb_.getAllowedDeclares().clear();
        f_.setReglesBelote(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("/_/",FacadeCards.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(rb_),pr_.getStreams());
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.RULES_BELOTE)).lastModified());
        f_.init("/_/", pr_);
        assertEq(9,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.RULES_BELOTE)).lastModified());
        assertTrue(f_.getReglesBelote().isValidRules());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        nicknames(pr_);
        FacadeCards f_ = facade(pr_);
        RulesPresident rb_ = new RulesPresident();
        rb_.setNbStacks(-1);
        f_.setReglesPresident(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("/_/",FacadeCards.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(rb_),pr_.getStreams());
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.RULES_PRESIDENT)).lastModified());
        f_.init("/_/", pr_);
        assertEq(9,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.RULES_PRESIDENT)).lastModified());
        assertTrue(f_.getReglesPresident().isValidRules());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        nicknames(pr_);
        FacadeCards f_ = facade(pr_);
        RulesTarot rb_ = new RulesTarot();
        rb_.getAllowedBids().clear();
        f_.setReglesTarot(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("/_/",FacadeCards.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(rb_),pr_.getStreams());
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.RULES_TAROT)).lastModified());
        f_.init("/_/", pr_);
        assertEq(9,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.RULES_TAROT)).lastModified());
        assertTrue(f_.getReglesTarot().isValidRules());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        FacadeCards f_ = facade(pr_);
        Nicknames rb_ = new Nicknames(nicknames(pr_));
        rb_.getPseudosBelote().clear();
        rb_.getPseudosPresident().clear();
        rb_.getPseudosTarot().clear();
        f_.setPseudosJoueurs(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("/_/",FacadeCards.PLAYERS), DocumentWriterCardsUnionUtil.setNicknames(rb_),pr_.getStreams());
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.PLAYERS)).lastModified());
        f_.init("/_/", pr_);
        assertEq(9,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.PLAYERS)).lastModified());
        assertTrue(f_.getPseudosJoueurs().isValidNicknames());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        TranslationsLg lg_ = nicknames(pr_);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        FacadeCards f_ = facade(pr_);
        Nicknames rb_ = new Nicknames(lg_);
        f_.setPseudosJoueurs(rb_);
        StreamTextFile.saveTextFile(StringUtil.concat("/_/",FacadeCards.PLAYERS), DocumentWriterCardsUnionUtil.setNicknames(rb_),pr_.getStreams());
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.PLAYERS)).lastModified());
        f_.init("/_/", pr_);
        assertEq(7,pr_.getFileCoreStream().newFile(StringUtil.concat("/_/",FacadeCards.PLAYERS)).lastModified());
        assertTrue(f_.getPseudosJoueurs().isValidNicknames());
    }
    @Test
    public void init6() {
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        pr_.getFileCoreStream().newFile(StringUtil.concat("/_/", FacadeCards.DECK_FOLDER,"/")).mkdirs();
        FacadeCards f_ = facade(pr_);
        AbsCardGamesCrud crud_ = f_.getNicknamesCrud().getCardGamesCrud();
        crud_.belote(HandBelote.pileBase());
        crud_.president(2,HandPresident.stack(2));
        crud_.tarot(HandTarot.pileBase());
        assertEq(32, crud_.belote().total());
        assertEq(104, crud_.president(2).total());
        assertEq(78, crud_.tarot().total());
    }
    @Test
    public void init7() {
        MockProgramInfos pr_ = prTmp("/_/",1, 2);
        pr_.getFileCoreStream().newFile("/_/").mkdirs();
        pr_.getFileCoreStream().newFile(StringUtil.concat("/_/", FacadeCards.DECK_FOLDER,"/")).mkdirs();
        FacadeCards f_ = facade(pr_);
        AbsCardGamesCrud crud_ = f_.getNicknamesCrud().getCardGamesCrud();
        crud_.belote(new HandBelote());
        crud_.president(2,new HandPresident());
        crud_.tarot(new HandTarot());
        assertEq(32, crud_.belote().total());
        assertEq(104, crud_.president(2).total());
        assertEq(78, crud_.tarot().total());
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
    @Test
    public void ia() {
        IntArtCardGames ia_ = new IntArtCardGames();
        MockGameBelote b_ = new MockGameBelote();
        b_.getCards().add(CardBelote.SPADE_1);
        ia_.setBelote(b_);
        assertEq(CardBelote.SPADE_1,ia_.getBelote().strategieJeuCarteUnique(null));
        MockGamePresident p_ = new MockGamePresident();
        p_.getCards().add(new HandPresident());
        ia_.setPresident(p_);
        assertEq(0,ia_.getPresident().playedCards(null).total());
        MockGameTarot t_ = new MockGameTarot();
        t_.getCards().add(CardTarot.SPADE_1);
        ia_.setTarot(t_);
        assertEq(CardTarot.SPADE_1,ia_.getTarot().changerConfianceJeuCarteUnique(null));
    }
    @Test
    public void load1() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        fg_.getNicknamesCrud().getCardGamesCrud().belote(DocumentWriterBeloteUtil.TYPE_GAME_BELOTE,game_);
        assertEq(1,fg_.load(DocumentWriterBeloteUtil.TYPE_GAME_BELOTE).getPartiesBelote().size());
    }
    @Test
    public void load2() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        game_.ajouterContrat(bid_);
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        fg_.getNicknamesCrud().getCardGamesCrud().belote(DocumentWriterBeloteUtil.TYPE_GAME_BELOTE,game_);
        Games load_ = fg_.load(DocumentWriterBeloteUtil.TYPE_GAME_BELOTE);
        assertEq(0, load_.getPartiesBelote().size());
        assertEq(0, load_.getPartiesPresident().size());
        assertEq(0, load_.getPartiesTarot().size());
    }
    @Test
    public void load3() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        fg_.getNicknamesCrud().getCardGamesCrud().president(DocumentWriterPresidentUtil.TYPE_GAME_PRESIDENT,game_);
        assertEq(1,fg_.load(DocumentWriterPresidentUtil.TYPE_GAME_PRESIDENT).getPartiesPresident().size());
    }
    @Test
    public void load4() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        //
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        fg_.getNicknamesCrud().getCardGamesCrud().president(DocumentWriterPresidentUtil.TYPE_GAME_PRESIDENT,game_);
        Games load_ = fg_.load(DocumentWriterPresidentUtil.TYPE_GAME_PRESIDENT);
        assertEq(0, load_.getPartiesBelote().size());
        assertEq(0, load_.getPartiesPresident().size());
        assertEq(0, load_.getPartiesTarot().size());
    }
    @Test
    public void load5() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        fg_.getNicknamesCrud().getCardGamesCrud().tarot(DocumentWriterTarotUtil.TYPE_GAME_TAROT,game_);
        assertEq(1,fg_.load(DocumentWriterTarotUtil.TYPE_GAME_TAROT).getPartiesTarot().size());
    }
    @Test
    public void load6() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.TAKE, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        fg_.getNicknamesCrud().getCardGamesCrud().tarot(DocumentWriterTarotUtil.TYPE_GAME_TAROT,game_);
        Games load_ = fg_.load(DocumentWriterTarotUtil.TYPE_GAME_TAROT);
        assertEq(0, load_.getPartiesBelote().size());
        assertEq(0, load_.getPartiesPresident().size());
        assertEq(0, load_.getPartiesTarot().size());
    }
    @Test
    public void load7() {
        MockProgramInfos pr_ = pr(1, 2);
        FacadeCards fg_ = facade(pr_);
        Games load_ = fg_.load("");
        assertEq(0, load_.getPartiesBelote().size());
        assertEq(0, load_.getPartiesPresident().size());
        assertEq(0, load_.getPartiesTarot().size());
    }
    private FacadeCards facade(AbstractProgramInfos _pr) {
        CardGamesStream cs_ = new CardGamesStream(_pr,"/_/");
        return new FacadeCards(cs_);
    }

    private static void initDonneLoc(RulesBelote _rules, DealBelote _deal) {
        _deal.initDonne(_rules, new DisplayingBelote(), DefaultGenerator.oneElt());
    }

    private static void initDonneLoc(RulesTarot _rules, DealTarot _deal) {
        _deal.initDonne(_rules, DefaultGenerator.oneElt());
    }
    private static DealBelote deal1Classic(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }


    static CustList<HandPresident> deal1() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }

    private static DealTarot deal1(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

}
