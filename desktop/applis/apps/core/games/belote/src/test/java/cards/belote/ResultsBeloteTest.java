package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.Suit;
import code.maths.Rate;
import code.util.*;
import org.junit.Test;

public final class ResultsBeloteTest extends CommonGameBelote {
    @Test
    public void calculateScoresTest() {
        ResultsBelote res_ = new ResultsBelote();
        res_.getRes().setUser((byte) 0);
        res_.getRes().setScores(new CustList<Longs>());
        res_.getRes().setGlobalResultsPageTitle("");
        res_.getRes().setDetailResultsTitle("");
        res_.getRes().setSigmas(new CustList<Rate>());
        res_.getRes().setSums(new Longs());
        res_.getRes().setNicknames(new StringList());
        res_.getRes().setRenderedPages(new StringMap<String>());
        res_.getRes().setLoc("");
        res_.calculateScores(new Shorts(),GameType.RANDOM,1,0);
        assertEq(0, res_.getRes().getScores().size());
        assertEq(0, res_.getRes().getSums().size());
        assertEq(0, res_.getRes().getSigmas().size());
        assertEq(0, res_.getRes().getNicknames().size());
        assertEq(0, res_.getRes().getRenderedPages().size());
        assertEq(0, res_.getRes().getUser());
        assertEq("", res_.getRes().getLoc());
        assertEq("", res_.getRes().getGlobalResultsPageTitle());
        assertEq("", res_.getRes().getDetailResultsTitle());
    }

    @Test
    public void initialize1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(game_);
        res_.getRes().setUser((byte) 0);
        res_.initialize(new StringList("1","2","3","4"),new CustList<Longs>());
        assertEq(4, res_.getRes().getScores().get(0).size());
    }

    @Test
    public void initialize2Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.finEncherePremierTour();
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(game_);
        res_.getRes().setUser((byte) 0);
        res_.initialize(new StringList("1","2","3","4"),new CustList<Longs>());
        assertEq(4, res_.getRes().getScores().get(0).size());
    }

    @Test
    public void initialize3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(game_);
        res_.getRes().setUser((byte) 0);
        CustList<Longs> scores_ = new CustList<Longs>();
        Longs pr_ = new Longs();
        pr_.add(0L);
        pr_.add(0L);
        pr_.add(0L);
        pr_.add(0L);
        scores_.add(pr_);
        res_.initialize(new StringList("1","2","3","4"), scores_);
        assertEq(4, res_.getRes().getScores().get(0).size());
        assertSame(game_, res_.getGame());
        assertSame(EndGameState.WIN, res_.getEndBeloteGame());
        assertEq(262, res_.getDifferenceScoreTaker());
    }
    GameBelote getSimpleSlamDeal() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_JACK);
        game_.getDistribution().jouer((byte) 1,CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_KING);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_8);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_9);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_1);
        game_.getDistribution().jouer((byte)3,CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_10);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setEntameur();
        game_.setPliEnCours();
        return game_;
    }

    private static DealBelote deal1Slam(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
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
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
}
