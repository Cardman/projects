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
//        res_.getRes().setScores(new CustList<Longs>());
        res_.getRes().setGlobalResultsPageTitle("");
        res_.getRes().setDetailResultsTitle("");
        res_.getRes().setSigmas(new CustList<Rate>());
        res_.getRes().setSums(new Longs());
        res_.getRes().setNicknames(new StringList());
        res_.getRes().setRenderedPages(new StringMap<String>());
//        res_.getRes().setLoc("");
        res_.calculateScores(new CustList<Longs>(),new Shorts(),GameType.RANDOM,1,0);
        assertEq(0, res_.getRes().getScores().size());
        assertEq(0, res_.getRes().getSums().size());
        assertEq(0, res_.getRes().getSigmas().size());
        assertEq(0, res_.getRes().getNicknames().size());
        assertEq(0, res_.getRes().getRenderedPages().size());
        assertEq(0, res_.getRes().getUser());
//        assertEq("", res_.getRes().getLoc());
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
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
//        game_.finEncherePremierTour();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
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
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
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
