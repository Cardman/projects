package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.*;
import org.junit.Test;

public final class GameBeloteCommonTest extends EquallableBeloteUtil {
    @Test
    public void isSameTeam1Test() {
        RulesBelote rules_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        int taker_ = 3;
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation((byte)taker_, rules_);
        Bytes set_ = new Bytes();
        set_.add((byte) 1);
        set_.add((byte) taker_);
        assertTrue(g_.isSameTeam(set_));
    }
    @Test
    public void isSameTeam2Test() {
        RulesBelote rules_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        int taker_ = 3;
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation((byte)taker_, rules_);
        Bytes set_ = new Bytes();
        set_.add((byte) 2);
        set_.add((byte) taker_);
        assertTrue(!g_.isSameTeam(set_));
    }
    @Test
    public void isSameTeam3Test() {
        RulesBelote rules_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        int taker_ = 3;
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation((byte)taker_, rules_);
        Bytes set_ = new Bytes();
        set_.add((byte) 0);
        set_.add((byte) 2);
        assertTrue(g_.isSameTeam(set_));
    }
    @Test
    public void isSameTeam4Test() {
        RulesBelote rules_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        int taker_ = 3;
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation((byte)taker_, rules_);
        Bytes set_ = new Bytes();
        set_.add((byte) 2);
        set_.add((byte) 1);
        assertTrue(!g_.isSameTeam(set_));
    }
    @Test
    public void cartesMaitresses1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(1, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(2, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_1));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(0, leading_.getVal(Suit.HEART).total());
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(5, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_9));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_1));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_10));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_7));
        assertEq(0, leading_.getVal(Suit.SPADE).total());
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(1, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_1));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(1, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_JACK));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_JACK));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_JACK));
    }
    @Test
    public void couleursNonAtoutAyantNbCartesSupEgTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_JACK);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(h_, GameBeloteCommon.couleurs(), 2);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.SPADE));
        suits_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(hr_,GameBeloteCommon.couleurs(),2);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursAvecNbPointsInfEg1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_JACK);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecNbPointsInfEg(h_,b_,GameBeloteCommon.couleurs(),2);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursAvecLaPlusGrandeFigure1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_JACK);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(4, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursAvecLaPlusGrandeFigure2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_JACK);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursAvecLaPlusGrandeFigure4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void couleursAvecLaPlusGrandeFigure5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursAvecLaPlusPetiteCarteBasse1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(4, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursAvecLaPlusPetiteCarteBasse2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursAvecLaPlusPetiteCarteBasse4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void couleursAvecLaPlusPetiteCarteBasse5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursLesPlusLonguesTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursLesPlusLongues(hr_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursLesPlusCourtesTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursLesPlusCourtes(hr_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursAvecLePlusGrandNbPointsTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(hr_,b_,GameBeloteCommon.couleurs());
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursAvecLePlusPetitNbPointsTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> s_ = GameBeloteCommon.couleurs();
        s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_,s_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(hr_,b_, s_);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursAvecCarteMaitresseTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.HEART_JACK);
        EnumList<Suit> s_ = GameBeloteCommon.couleurs();
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecCarteMaitresse(h_,p_,b_,s_);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void couleursAvecPointsTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursAvecPoints(h_,b_, GameBeloteCommon.couleurs());
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.SPADE));
        suits_ = GameBeloteCommon.couleursAvecPoints(hr_,b_, GameBeloteCommon.couleurs());
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursSansPointTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> suits_ = GameBeloteCommon.couleursSansPoint(hr_,b_, GameBeloteCommon.couleurs());
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
}
