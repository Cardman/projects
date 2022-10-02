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
        assertEq("cards.consts.MixCardsChoice.EACH_DEAL",CoreResourcesAccess.key(MixCardsChoice.EACH_DEAL));
        assertEq("cards.consts.Suit.HEART",CoreResourcesAccess.key(Suit.HEART));
    }
    @Test
    public void intersectionJoueurs1() {
        Bytes sorted_ = SortedPlayers.intersectionJoueurs(Bytes.newList((byte)1),Bytes.newList((byte)2));
        assertEq(0, sorted_.size());
    }
    @Test
    public void intersectionJoueurs2() {
        Bytes sorted_ = SortedPlayers.intersectionJoueurs(Bytes.newList((byte)1,(byte)3),Bytes.newList((byte)1,(byte)2));
        assertEq(1, sorted_.size());
        assertEq(1, sorted_.get(0));
    }
    @Test
    public void nextPlayers() {
        Bytes joueursRepartitionInconnue_ = Bytes.newList();
        SortedPlayers.nextPlayers(Bytes.newList((byte)1), joueursRepartitionInconnue_, (byte) 4);
        assertEq(3, joueursRepartitionInconnue_.size());
        assertEq(0, joueursRepartitionInconnue_.get(0));
        assertEq(2, joueursRepartitionInconnue_.get(1));
        assertEq(3, joueursRepartitionInconnue_.get(2));
    }
    @Test
    public void shift() {
        Bytes joueursRepartitionInconnue_ = Bytes.newList((byte)1);
        Bytes joueursRepartitionConnue_ = Bytes.newList((byte)2);
        Bytes joueursRepartitionConnue2_ = Bytes.newList((byte)3);
        SortedPlayers.shift(joueursRepartitionConnue_, joueursRepartitionConnue2_, joueursRepartitionInconnue_);
        assertEq(0, joueursRepartitionInconnue_.size());
        assertEq(1, joueursRepartitionConnue_.size());
        assertEq(3, joueursRepartitionConnue_.get(0));
        assertEq(0, joueursRepartitionConnue2_.size());
    }
    @Test
    public void autresJoueurs() {
        Bytes sorted_ = SortedPlayers.autresJoueurs(Bytes.newList((byte)1,(byte)3), (byte) 5);
        assertEq(3, sorted_.size());
        assertEq(0, sorted_.get(0));
        assertEq(2, sorted_.get(1));
        assertEq(4, sorted_.get(2));
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
    public void aJoue1() {
        assertTrue(new SortedPlayers(4).aJoue(2,4,0));
    }
    @Test
    public void aJoue2() {
        assertTrue(new SortedPlayers(4).aJoue(2,2,1));
    }
    @Test
    public void aJoue3() {
        assertFalse(new SortedPlayers(4).aJoue(3,2,1));
    }
    @Test
    public void aJoue4() {
        assertTrue(new SortedPlayers(4).aJoue(0,2,3));
    }
    @Test
    public void aJoue5() {
        assertFalse(new SortedPlayers(4).aJoue(1,2,3));
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
    public void joueursAyantJoueAvant1() {
        Bytes sorted_ = new SortedPlayers(4).joueursAyantJoueAvant(1,2,2);
        assertEq(2, sorted_.size());
        assertEq(2, sorted_.get(0));
        assertEq(3, sorted_.get(1));
    }
    @Test
    public void joueursAyantJoueAvant2() {
        Bytes sorted_ = new SortedPlayers(4).joueursAyantJoueAvant(1,2,3);
        assertEq(3, sorted_.size());
        assertEq(2, sorted_.get(0));
        assertEq(3, sorted_.get(1));
        assertEq(0, sorted_.get(2));
    }
    @Test
    public void joueursAyantJoueAvant3() {
        Bytes sorted_ = new SortedPlayers(4).joueursAyantJoueAvant(0,2,3);
        assertEq(2, sorted_.size());
        assertEq(2, sorted_.get(0));
        assertEq(3, sorted_.get(1));
    }
    @Test
    public void joueursAyantJoue() {
        Bytes sorted_ = new SortedPlayers(4).joueursAyantJoue(2,3);
        assertEq(3, sorted_.size());
        assertEq(2, sorted_.get(0));
        assertEq(3, sorted_.get(1));
        assertEq(0, sorted_.get(2));
    }
    @Test
    public void index1() {
        assertEq(0, new SortedPlayers(4).index(2,2, 4));
    }
    @Test
    public void index2() {
        assertEq(3, new SortedPlayers(4).index(1,2, 4));
    }
    @Test
    public void index3() {
        assertEq(0, new SortedPlayers(4).index(2,2, 3));
    }
    @Test
    public void index4() {
        assertEq(2, new SortedPlayers(4).index(0,2, 3));
    }
    @Test
    public void leading1() {
        LeadingCards<CardChar> lead_ = new LeadingCards<CardChar>();
        lead_.leading(two(CardChar.EXCUSE,CardChar.QUEEN),two(CardChar.EXCUSE,CardChar.QUEEN),zero(),all());
        CustList<CardChar> list_ = lead_.getList();
        assertEq(1, list_.size());
        assertSame(CardChar.EXCUSE, list_.get(0));
    }
    @Test
    public void leading2() {
        LeadingCards<CardChar> lead_ = new LeadingCards<CardChar>();
        lead_.leading(two(CardChar.KING,CardChar.QUEEN),two(CardChar.KING,CardChar.QUEEN),zero(),all());
        CustList<CardChar> list_ = lead_.getList();
        assertEq(0, list_.size());
    }
    @Test
    public void leading3() {
        LeadingCards<CardChar> lead_ = new LeadingCards<CardChar>();
        lead_.leading(three(CardChar.EXCUSE,CardChar.KING,CardChar.QUEEN),two(CardChar.EXCUSE,CardChar.QUEEN),one(CardChar.KING),all());
        CustList<CardChar> list_ = lead_.getList();
        assertEq(2, list_.size());
        assertSame(CardChar.EXCUSE, list_.get(0));
        assertSame(CardChar.QUEEN, list_.get(1));
    }
    @Test
    public void leading4() {
        LeadingCards<CardChar> lead_ = new LeadingCards<CardChar>();
        lead_.leading(four(CardChar.EXCUSE,CardChar.KING,CardChar.QUEEN,CardChar.UNDEFINED),three(CardChar.EXCUSE,CardChar.QUEEN,CardChar.UNDEFINED),one(CardChar.KING),all());
        CustList<CardChar> list_ = lead_.getList();
        assertEq(3, list_.size());
        assertSame(CardChar.EXCUSE, list_.get(0));
        assertSame(CardChar.QUEEN, list_.get(1));
        assertSame(CardChar.UNDEFINED, list_.get(2));
    }
    @Test
    public void leading5() {
        LeadingCards<CardChar> lead_ = new LeadingCards<CardChar>();
        lead_.leading(all(),three(CardChar.EXCUSE,CardChar.QUEEN,CardChar.UNDEFINED),three(CardChar.KING,CardChar.KNIGHT,CardChar.JACK),all());
        CustList<CardChar> list_ = lead_.getList();
        assertEq(3, list_.size());
        assertSame(CardChar.EXCUSE, list_.get(0));
        assertSame(CardChar.QUEEN, list_.get(1));
        assertSame(CardChar.UNDEFINED, list_.get(2));
    }
    private static CustList<CardChar> zero() {
        return new CustList<CardChar>();
    }
    private static CustList<CardChar> one(CardChar _one) {
        CustList<CardChar> ls_ = zero();
        ls_.add(_one);
        return ls_;
    }
    private static CustList<CardChar> two(CardChar _one, CardChar _two) {
        CustList<CardChar> ls_ = one(_one);
        ls_.add(_two);
        return ls_;
    }
    private static CustList<CardChar> three(CardChar _one, CardChar _two, CardChar _three) {
        CustList<CardChar> ls_ = two(_one,_two);
        ls_.add(_three);
        return ls_;
    }
    private static CustList<CardChar> four(CardChar _one, CardChar _two, CardChar _three, CardChar _four) {
        CustList<CardChar> ls_ = three(_one,_two,_three);
        ls_.add(_four);
        return ls_;
    }
    private static CustList<CardChar> five(CardChar _one, CardChar _two, CardChar _three, CardChar _four, CardChar _five) {
        CustList<CardChar> ls_ = four(_one,_two,_three,_four);
        ls_.add(_five);
        return ls_;
    }
    private static CustList<CardChar> all() {
        CustList<CardChar> ls_ = five(CardChar.EXCUSE,CardChar.KING,CardChar.QUEEN,CardChar.KNIGHT,CardChar.JACK);
        ls_.add(CardChar.UNDEFINED);
        return ls_;
    }
    @Test
    public void eqSuitTest() {
        assertEq(4, Suit.couleursOrdinaires().size());
        assertEq(5, Suit.couleursDefinies().size());
        assertEq(6, Suit.toutesCouleurs().size());
        IdList<Suit> one_ = new IdList<Suit>();
        IdList<Suit> two_ = new IdList<Suit>();
        assertTrue(Suit.equalsSuits(one_,two_));
        one_ = new IdList<Suit>();
        two_ = new IdList<Suit>();
        one_.add(Suit.HEART);
        two_.add(Suit.SPADE);
        assertTrue(!Suit.equalsSuits(one_,two_));
        one_ = new IdList<Suit>();
        two_ = new IdList<Suit>();
        one_.add(Suit.HEART);
        assertTrue(!Suit.equalsSuits(one_,two_));
        one_ = new IdList<Suit>();
        two_ = new IdList<Suit>();
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
        calculateScores2(r_, Shorts.newList((short)1,(short) 2));
        assertEq(1, r_.getScores().size());
        assertEq(2, r_.getScores().get(0).size());
        assertEq(1, r_.getScores().get(0).get(0));
        assertEq(2, r_.getScores().get(0).get(1));
        assertEq(1, r_.getSums().size());
        assertEq(3, r_.getSums().get(0));
        assertEq(1, r_.getSigmas().size());
        assertEq(new Rate(3,2), r_.getSigmas().get(0));
        assertEq(1, r_.getHistory().size());
        assertEq(0, r_.getHistory().get(0).getNumber());
        assertEq(2, r_.getHistory().get(0).getScores().size());
        assertEq(1, r_.getHistory().get(0).getScores().get(0));
        assertEq(2, r_.getHistory().get(0).getScores().get(1));
    }
    @Test
    public void calculateScores2() {
        ResultsGame r_ = new ResultsGame();
        calculateScores2(r_, Shorts.newList((short)1,(short) 2));
        calculateScores2(r_, Shorts.newList((short) 2, (short) 1));
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
        assertEq(2, r_.getHistory().size());
        assertEq(0, r_.getHistory().get(0).getNumber());
        assertEq(2, r_.getHistory().get(0).getScores().size());
        assertEq(1, r_.getHistory().get(0).getScores().get(0));
        assertEq(2, r_.getHistory().get(0).getScores().get(1));
        assertEq(1, r_.getHistory().get(1).getNumber());
        assertEq(2, r_.getHistory().get(1).getScores().size());
        assertEq(3, r_.getHistory().get(1).getScores().get(0));
        assertEq(3, r_.getHistory().get(1).getScores().get(1));
    }
    @Test
    public void calculateScores3() {
        ResultsGame r_ = new ResultsGame();
        calculateScores1(r_, GameType.RANDOM, new CustList<Longs>());
        assertEq(1, r_.getScores().size());
        assertEq(1, r_.getHistory().size());
        assertEq(0, r_.getHistory().get(0).getNumber());
    }
    @Test
    public void calculateScores4() {
        ResultsGame r_ = new ResultsGame();
        calculateScores1(r_, GameType.TRAINING, new CustList<Longs>());
        assertEq(0, r_.getScores().size());
        assertEq(0, r_.getHistory().size());
    }
    @Test
    public void calculateScores5() {
        ResultsGame r_ = new ResultsGame();
        CustList<Longs> pr_ = new CustList<Longs>();
        pr_.add(Longs.newList(0,1,2,3));
        calculateScores1(r_, GameType.RANDOM, pr_);
        assertEq(2, r_.getScores().size());
        assertEq(2, r_.getHistory().size());
        assertEq(0, r_.getHistory().get(0).getNumber());
        assertEq(1, r_.getHistory().get(1).getNumber());
    }
    @Test
    public void calculateScores6() {
        ResultsGame r_ = new ResultsGame();
        CustList<Longs> pr_ = new CustList<Longs>();
        pr_.add(Longs.newList(0,1,2,3));
        calculateScores1(r_, GameType.TRAINING, pr_);
        assertEq(1, r_.getScores().size());
        assertEq(1, r_.getHistory().size());
        assertEq(0, r_.getHistory().get(0).getNumber());
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
        displayingCommon_.validate(new IdList<Suit>(Suit.couleursDefinies().getReverse()));
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
        displayingCommon_.setSuits(new IdList<Suit>(Suit.couleursDefinies().getReverse()));
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

    private void calculateScores1(ResultsGame _r, GameType _type, CustList<Longs> _previous) {
        _r.calculateScores(_previous,Shorts.newList((short)1,(short) 2), _type,0,1);
    }

    private void calculateScores2(ResultsGame _r, Shorts _ls) {
        _r.calculateScores(_ls);
    }

}
