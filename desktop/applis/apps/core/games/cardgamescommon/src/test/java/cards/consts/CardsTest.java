package cards.consts;

import code.maths.Rate;
import code.util.*;
import org.junit.Test;

public final class CardsTest extends EquallableCardsUtil {
    @Test
    public void test() {
        ResultsGame r_ = new ResultsGame();
        r_.setScores(new CustList<Longs>());
        r_.setSigmas(new CustList<Rate>());
        r_.setSums(new Longs());
        r_.setDetailResultsTitle(CoreResourcesAccess.CHARS);
        assertNotNull(r_.getDetailResultsTitle());
        r_.setGlobalResultsPageTitle(CoreResourcesAccess.MIX);
        assertNotNull(r_.getGlobalResultsPageTitle());
        r_.setRenderedPages(new StringMap<String>());
        r_.setGeneral("");
        assertNotNull(r_.getGeneral());
        r_.setSpecific("");
        assertNotNull(r_.getSpecific());
        r_.setLoc("");
        assertNotNull(r_.getLoc());
        r_.setUser((byte) 0);
        assertEq(0, r_.getUser());
        r_.setNicknames(new StringList());
        RulesCommon rc_ = new RulesCommon(new RulesCommon());
        rc_.setSpecific("_");
        rc_.setGeneral("_");
        rc_.setNbDeals(1);
        rc_.setMixedCards(MixCardsChoice.NEVER);
        assertEq("_", rc_.getGeneral());
        assertEq("_", rc_.getSpecific());
        assertEq(1, rc_.getNbDeals());
        assertSame(MixCardsChoice.NEVER,rc_.getMixedCards());
        assertEq(0, r_.getNicknames().size());
        assertNotNull(r_.getRenderedPages());
        assertSame(Role.TAKER,EnumCardsRetrieverUtil.toRole("TAKER"));
        assertSame(Role.CALLED_PLAYER,EnumCardsRetrieverUtil.toRole("CALLED_PLAYER"));
        assertSame(Role.DEFENDER,EnumCardsRetrieverUtil.toRole("DEFENDER"));
        assertSame(PossibleTrickWinner.TEAM,EnumCardsRetrieverUtil.toPossibleTrickWinner("TEAM"));
        assertSame(PossibleTrickWinner.FOE_TEAM,EnumCardsRetrieverUtil.toPossibleTrickWinner("FOE_TEAM"));
        assertSame(PossibleTrickWinner.UNKNOWN,EnumCardsRetrieverUtil.toPossibleTrickWinner("UNKNOWN"));
        assertSame(EndGameState.WIN,EnumCardsRetrieverUtil.toEndGameState("WIN"));
        assertSame(EndGameState.EQUALLITY,EnumCardsRetrieverUtil.toEndGameState("EQUALLITY"));
        assertSame(EndGameState.LOOSE,EnumCardsRetrieverUtil.toEndGameState("LOOSE"));
        assertSame(MixCardsChoice.EACH_DEAL,EnumCardsRetrieverUtil.toMixCardsChoice("EACH_DEAL"));
        assertSame(MixCardsChoice.ONCE_ONLY,EnumCardsRetrieverUtil.toMixCardsChoice("ONCE_ONLY"));
        assertSame(MixCardsChoice.EACH_LAUNCHING,EnumCardsRetrieverUtil.toMixCardsChoice("EACH_LAUNCHING"));
        assertSame(MixCardsChoice.NEVER,EnumCardsRetrieverUtil.toMixCardsChoice("NEVER"));
        assertSame(MixCardsChoice.NEVER,EnumCardsRetrieverUtil.toMixCardsChoice(""));
        assertSame(Order.TRUMP,EnumCardsRetrieverUtil.toOrder("TRUMP"));
        assertSame(Order.SUIT,EnumCardsRetrieverUtil.toOrder("SUIT"));
        assertSame(Order.NOTHING,EnumCardsRetrieverUtil.toOrder("NOTHING"));
        assertSame(Order.NOTHING,EnumCardsRetrieverUtil.toOrder(""));
        assertSame(GameType.EDIT,EnumCardsRetrieverUtil.toGameType("EDIT"));
        assertSame(GameType.TRAINING,EnumCardsRetrieverUtil.toGameType("TRAINING"));
        assertSame(GameType.RANDOM,EnumCardsRetrieverUtil.toGameType("RANDOM"));
        assertSame(GameType.RANDOM,EnumCardsRetrieverUtil.toGameType(""));
        assertSame(Suit.TRUMP,EnumCardsRetrieverUtil.toSuit("TRUMP"));
        assertSame(Suit.HEART,EnumCardsRetrieverUtil.toSuit("HEART"));
        assertSame(Suit.SPADE,EnumCardsRetrieverUtil.toSuit("SPADE"));
        assertSame(Suit.DIAMOND,EnumCardsRetrieverUtil.toSuit("DIAMOND"));
        assertSame(Suit.CLUB,EnumCardsRetrieverUtil.toSuit("CLUB"));
        assertSame(Suit.UNDEFINED,EnumCardsRetrieverUtil.toSuit("UNDEFINED"));
        assertSame(Suit.UNDEFINED,EnumCardsRetrieverUtil.toSuit(""));
        assertEq("EACH_DEAL",EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_DEAL));
        assertEq("ONCE_ONLY",EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.ONCE_ONLY));
        assertEq("EACH_LAUNCHING",EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING));
        assertEq("NEVER",EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.NEVER));
        assertEq("NEVER",EnumCardsExporterUtil.fromMixCardsChoice(null));
        assertEq("TRUMP",EnumCardsExporterUtil.fromOrder(Order.TRUMP));
        assertEq("SUIT",EnumCardsExporterUtil.fromOrder(Order.SUIT));
        assertEq("NOTHING",EnumCardsExporterUtil.fromOrder(Order.NOTHING));
        assertEq("NOTHING",EnumCardsExporterUtil.fromOrder(null));
        assertEq("EDIT",EnumCardsExporterUtil.fromGameType(GameType.EDIT));
        assertEq("TRAINING",EnumCardsExporterUtil.fromGameType(GameType.TRAINING));
        assertEq("RANDOM",EnumCardsExporterUtil.fromGameType(GameType.RANDOM));
        assertEq("RANDOM",EnumCardsExporterUtil.fromGameType(null));
        assertEq("TRUMP",EnumCardsExporterUtil.fromSuit(Suit.TRUMP));
        assertEq("HEART",EnumCardsExporterUtil.fromSuit(Suit.HEART));
        assertEq("SPADE",EnumCardsExporterUtil.fromSuit(Suit.SPADE));
        assertEq("DIAMOND",EnumCardsExporterUtil.fromSuit(Suit.DIAMOND));
        assertEq("CLUB",EnumCardsExporterUtil.fromSuit(Suit.CLUB));
        assertEq("UNDEFINED",EnumCardsExporterUtil.fromSuit(Suit.UNDEFINED));
        assertEq("UNDEFINED",EnumCardsExporterUtil.fromSuit(null));
        assertTrue(EnumCardsRetrieverUtil.toBool(Hypothesis.SURE));
        assertFalse(EnumCardsRetrieverUtil.toBool(Hypothesis.POSSIBLE));
    }
    @Test
    public void getSortedPlayers() {
        Bytes sorted_ = new SortedPlayers(4).getSortedPlayers(2);
        assertEq(4, sorted_.size());
        assertEq(2, sorted_.get(0));
        assertEq(3, sorted_.get(1));
        assertEq(0, sorted_.get(2));
        assertEq(1, sorted_.get(3));
    }
    @Test
    public void getSortedPlayersAfter() {
        Bytes sorted_ = new SortedPlayers(4).getSortedPlayersAfter(2);
        assertEq(4, sorted_.size());
        assertEq(3, sorted_.get(0));
        assertEq(0, sorted_.get(1));
        assertEq(1, sorted_.get(2));
        assertEq(2, sorted_.get(3));
    }
    @Test
    public void eqSuitTest() {
        assertEq(4, Suit.couleursOrdinaires().size());
        assertEq(5, Suit.couleursDefinies().size());
        assertEq(6, Suit.toutesCouleurs().size());
        EnumList<Suit> one_ = new EnumList<Suit>();
        EnumList<Suit> two_ = new EnumList<Suit>();
        assertTrue(Suit.equalsSuits(one_,two_));
        one_ = new EnumList<Suit>();
        two_ = new EnumList<Suit>();
        one_.add(Suit.HEART);
        two_.add(Suit.SPADE);
        assertTrue(!Suit.equalsSuits(one_,two_));
        one_ = new EnumList<Suit>();
        two_ = new EnumList<Suit>();
        one_.add(Suit.HEART);
        assertTrue(!Suit.equalsSuits(one_,two_));
        one_ = new EnumList<Suit>();
        two_ = new EnumList<Suit>();
        one_.add(Suit.HEART);
        two_.add(Suit.HEART);
        assertTrue(Suit.equalsSuits(one_,two_));
    }
    @Test
    public void hasToCalculateScores1Test() {
        assertTrue(ResultsGame.hasToCalculateScores(GameType.EDIT,0,0));
    }
    @Test
    public void hasToCalculateScores2Test() {
        assertTrue(!ResultsGame.hasToCalculateScores(GameType.EDIT,1,0));
    }
    @Test
    public void hasToCalculateScores3Test() {
        assertTrue(ResultsGame.hasToCalculateScores(GameType.RANDOM,0,0));
    }
    @Test
    public void hasToCalculateScores4Test() {
        assertTrue(!ResultsGame.hasToCalculateScores(GameType.RANDOM,1,0));
    }

    @Test
    public void calculateScores1() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2));
        assertEq(1, r_.getScores().size());
        assertEq(2, r_.getScores().get(0).size());
        assertEq(1, r_.getScores().get(0).get(0));
        assertEq(2, r_.getScores().get(0).get(1));
        assertEq(1, r_.getSums().size());
        assertEq(3, r_.getSums().get(0));
        assertEq(1, r_.getSigmas().size());
        assertEq(new Rate(3,2), r_.getSigmas().get(0));
    }
    @Test
    public void calculateScores2() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2));
        r_.calculateScores(Shorts.newList((short)2,(short) 1));
        assertEq(2, r_.getScores().size());
        assertEq(2, r_.getScores().get(0).size());
        assertEq(1, r_.getScores().get(0).get(0));
        assertEq(2, r_.getScores().get(0).get(1));
        assertEq(2, r_.getScores().get(1).size());
        assertEq(3, r_.getScores().get(1).get(0));
        assertEq(3, r_.getScores().get(1).get(1));
        assertEq(2, r_.getSums().size());
        assertEq(3, r_.getSums().get(0));
        assertEq(6, r_.getSums().get(1));
        assertEq(2, r_.getSigmas().size());
        assertEq(new Rate(3,2), r_.getSigmas().get(0));
        assertEq(Rate.zero(), r_.getSigmas().get(1));
    }
    @Test
    public void calculateScores3() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2),GameType.RANDOM,0,1);
        assertEq(1, r_.getScores().size());
    }
    @Test
    public void calculateScores4() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2),GameType.TRAINING,0,1);
        assertEq(0, r_.getScores().size());
    }
    @Test
    public void validate1(){
        DisplayingCommon displayingCommon_ = new DisplayingCommon(new DisplayingCommon());
        displayingCommon_.setClockwise(true);
        displayingCommon_.setDecreasing(true);
        displayingCommon_.setSuits(Suit.couleursDefinies());
        displayingCommon_.validate(Suit.couleursDefinies());
        assertEq(5,displayingCommon_.getSuits().size());
        assertSame(Suit.TRUMP,displayingCommon_.getSuits().get(0));
        assertSame(Suit.HEART,displayingCommon_.getSuits().get(1));
        assertSame(Suit.SPADE,displayingCommon_.getSuits().get(2));
        assertSame(Suit.DIAMOND,displayingCommon_.getSuits().get(3));
        assertSame(Suit.CLUB,displayingCommon_.getSuits().get(4));
        assertTrue(displayingCommon_.isClockwise());
        assertTrue(displayingCommon_.isDecreasing());
    }
    @Test
    public void validate2(){
        DisplayingCommon displayingCommon_ = new DisplayingCommon(new DisplayingCommon());
        displayingCommon_.setClockwise(true);
        displayingCommon_.setDecreasing(true);
        displayingCommon_.setSuits(Suit.couleursDefinies());
        displayingCommon_.validate(Suit.couleursOrdinaires());
        assertEq(4,displayingCommon_.getSuits().size());
        assertSame(Suit.HEART,displayingCommon_.getSuits().get(0));
        assertSame(Suit.SPADE,displayingCommon_.getSuits().get(1));
        assertSame(Suit.DIAMOND,displayingCommon_.getSuits().get(2));
        assertSame(Suit.CLUB,displayingCommon_.getSuits().get(3));
        assertTrue(displayingCommon_.isClockwise());
        assertTrue(displayingCommon_.isDecreasing());
    }
    @Test
    public void validate3(){
        DisplayingCommon displayingCommon_ = new DisplayingCommon(new DisplayingCommon());
        displayingCommon_.setClockwise(true);
        displayingCommon_.setDecreasing(true);
        displayingCommon_.setSuits(Suit.couleursDefinies());
        displayingCommon_.validate(new EnumList<Suit>(Suit.couleursDefinies().getReverse()));
        assertEq(5,displayingCommon_.getSuits().size());
        assertSame(Suit.TRUMP,displayingCommon_.getSuits().get(0));
        assertSame(Suit.HEART,displayingCommon_.getSuits().get(1));
        assertSame(Suit.SPADE,displayingCommon_.getSuits().get(2));
        assertSame(Suit.DIAMOND,displayingCommon_.getSuits().get(3));
        assertSame(Suit.CLUB,displayingCommon_.getSuits().get(4));
        assertTrue(displayingCommon_.isClockwise());
        assertTrue(displayingCommon_.isDecreasing());
    }
    @Test
    public void validate4(){
        DisplayingCommon displayingCommon_ = new DisplayingCommon(new DisplayingCommon());
        displayingCommon_.setClockwise(true);
        displayingCommon_.setDecreasing(true);
        displayingCommon_.setSuits(new EnumList<Suit>(Suit.couleursDefinies().getReverse()));
        displayingCommon_.validate(Suit.couleursDefinies());
        assertEq(5,displayingCommon_.getSuits().size());
        assertSame(Suit.CLUB,displayingCommon_.getSuits().get(0));
        assertSame(Suit.DIAMOND,displayingCommon_.getSuits().get(1));
        assertSame(Suit.SPADE,displayingCommon_.getSuits().get(2));
        assertSame(Suit.HEART,displayingCommon_.getSuits().get(3));
        assertSame(Suit.TRUMP,displayingCommon_.getSuits().get(4));
        assertTrue(displayingCommon_.isClockwise());
        assertTrue(displayingCommon_.isDecreasing());
    }
}
