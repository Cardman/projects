package code.network;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.network.belote.*;
import cards.network.belote.actions.*;
import cards.network.belote.displaying.*;
import cards.network.belote.unlock.*;
import cards.network.common.before.*;
import cards.network.president.actions.*;
import cards.network.president.displaying.*;
import cards.network.president.unlock.*;
import cards.network.tarot.*;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.*;
import cards.network.tarot.unlock.*;
import cards.network.threads.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;
import org.junit.Test;

public final class NetTest extends EquallableNetworkUtil {

    @Test
    public void longs1() {
        assertTrue(saveLongs(new Longs()).isEmpty());
    }

    @Test
    public void longs2() {
        Longs res_ = saveLongs(Longs.newList(1, 2, -3));
        assertEq(3, res_.size());
        assertEq(1, res_.get(0));
        assertEq(2, res_.get(1));
        assertEq(-3, res_.get(2));
    }

    @Test
    public void longs3() {
        assertTrue(saveLongsList(new CustList<Longs>()).isEmpty());
    }

    @Test
    public void longs4() {
        CustList<Longs> in_ = new CustList<Longs>();
        in_.add(Longs.newList(1, 2, -3));
        in_.add(Longs.newList(-1, 2, 5));
        CustList<Longs> res_ = saveLongsList(in_);
        assertEq(2, res_.size());
        assertEq(3, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(0).get(1));
        assertEq(-3, res_.get(0).get(2));
        assertEq(3, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
        assertEq(5, res_.get(1).get(2));
    }

    @Test
    public void longs5() {
        CustList<Longs> in_ = new CustList<Longs>();
        in_.add(Longs.newList(1));
        in_.add(Longs.newList(-1, 2));
        CustList<Longs> res_ = saveLongsList(in_);
        assertEq(2, res_.size());
        assertEq(1, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
    }

//    @Test
//    public void bytes1() {
//        assertTrue(saveBytes(new Bytes()).isEmpty());
//    }
//
//    @Test
//    public void bytes2() {
//        Bytes res_ = saveBytes(Bytes.newList((byte)1,(byte) 2,(byte) -3));
//        assertEq(3, res_.size());
//        assertEq(1, res_.get(0));
//        assertEq(2, res_.get(1));
//        assertEq(-3, res_.get(2));
//    }

    @Test
    public void bytes3() {
        assertTrue(saveBytesList(new CustList<Ints>()).isEmpty());
    }

    @Test
    public void bytes4() {
        CustList<Ints> in_ = new CustList<Ints>();
        in_.add(Ints.newList(1,2, -3));
        in_.add(Ints.newList(-1, 2, 5));
        CustList<Ints> res_ = saveBytesList(in_);
        assertEq(2, res_.size());
        assertEq(3, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(0).get(1));
        assertEq(-3, res_.get(0).get(2));
        assertEq(3, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
        assertEq(5, res_.get(1).get(2));
    }

    @Test
    public void bytes5() {
        CustList<Ints> in_ = new CustList<Ints>();
        in_.add(Ints.newList(1));
        in_.add(Ints.newList(-1, 2));
        CustList<Ints> res_ = saveBytesList(in_);
        assertEq(2, res_.size());
        assertEq(1, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
    }

    @Test
    public void ints1() {
        assertTrue(saveInts(new Ints()).isEmpty());
    }

    @Test
    public void ints2() {
        Ints res_ = saveInts(Ints.newList(1,2,-3));
        assertEq(3, res_.size());
        assertEq(1, res_.get(0));
        assertEq(2, res_.get(1));
        assertEq(-3, res_.get(2));
    }

    @Test
    public void bools1() {
        assertTrue(saveBools(new CustList<BoolVal>()).isEmpty());
    }

    @Test
    public void bools2() {
        CustList<BoolVal> bv_ = new CustList<BoolVal>();
        bv_.add(BoolVal.TRUE);
        bv_.add(BoolVal.FALSE);
        bv_.add(BoolVal.FALSE);
        bv_.add(BoolVal.TRUE);
        bv_.add(BoolVal.TRUE);
        bv_.add(BoolVal.FALSE);
        CustList<BoolVal> res_ = saveBools(bv_);
        assertEq(6, res_.size());
        assertEq(BoolVal.TRUE, res_.get(0));
        assertEq(BoolVal.FALSE, res_.get(1));
        assertEq(BoolVal.FALSE, res_.get(2));
        assertEq(BoolVal.TRUE, res_.get(3));
        assertEq(BoolVal.TRUE, res_.get(4));
        assertEq(BoolVal.FALSE, res_.get(5));
    }

    @Test
    public void teams1() {
        assertTrue(saveTeams(new CustList<Ints>()).isEmpty());
    }

    @Test
    public void teams2() {
        CustList<Ints> bv_ = new CustList<Ints>();
        bv_.add(Ints.newList(1));
        bv_.add(Ints.newList(-1, 2));
        bv_.add(Ints.newList(4, -2, 0, 6));
        CustList<Ints> res_ = saveTeams(bv_);
        assertEq(3, res_.size());
        assertEq(1,res_.get(0).size());
        assertEq(1,res_.get(0).get(0));
        assertEq(2,res_.get(1).size());
        assertEq(-1,res_.get(1).get(0));
        assertEq(2,res_.get(1).get(1));
        assertEq(4,res_.get(2).size());
        assertEq(4,res_.get(2).get(0));
        assertEq(-2,res_.get(2).get(1));
        assertEq(0,res_.get(2).get(2));
        assertEq(6,res_.get(2).get(3));
    }

    @Test
    public void teams3() {
        CustList<Ints> bv_ = new CustList<Ints>();
        bv_.add(Ints.newList(1));
        bv_.add(Ints.newList());
        bv_.add(Ints.newList());
        bv_.add(Ints.newList(-1, 2));
        CustList<Ints> res_ = saveTeams(bv_);
        assertEq(4, res_.size());
        assertEq(1,res_.get(0).size());
        assertEq(1,res_.get(0).get(0));
        assertEq(0,res_.get(1).size());
        assertEq(0,res_.get(2).size());
        assertEq(2,res_.get(3).size());
        assertEq(-1,res_.get(3).get(0));
        assertEq(2,res_.get(3).get(1));
    }
    @Test
    public void handBelote1() {
        assertTrue(saveHandBelote(new HandBelote()).estVide());
    }
    @Test
    public void handBelote2() {
        HandBelote h_ = saveHandBelote(HandBelote.create(new CardBelote[]{CardBelote.HEART_1,CardBelote.HEART_10}));
        assertEq(2,h_.total());
        assertEq(CardBelote.HEART_1,h_.carte(0));
        assertEq(CardBelote.HEART_10,h_.carte(1));
    }
    @Test
    public void handPresident1() {
        assertTrue(saveHandPresident(new HandPresident()).estVide());
    }
    @Test
    public void handPresident2() {
        HandPresident h_ = saveHandPresident(HandPresident.create(new CardPresident[]{CardPresident.HEART_1,CardPresident.HEART_10}));
        assertEq(2,h_.total());
        assertEq(CardPresident.HEART_1,h_.carte(0));
        assertEq(CardPresident.HEART_10,h_.carte(1));
    }
    @Test
    public void handTarot1() {
        assertTrue(saveHandTarot(new HandTarot()).estVide());
    }
    @Test
    public void handTarot2() {
        HandTarot h_ = saveHandTarot(HandTarot.create(new CardTarot[]{CardTarot.HEART_1,CardTarot.HEART_10}));
        assertEq(2,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
        assertEq(CardTarot.HEART_10,h_.carte(1));
    }
    @Test
    public void handBeloteList1() {
        assertTrue(saveHandBeloteList(new CustList<HandBelote>()).isEmpty());
    }
    @Test
    public void handBeloteList2() {
        CustList<HandBelote> hs_ = saveHandBeloteList(new CustList<HandBelote>(HandBelote.create(new CardBelote[]{CardBelote.HEART_1,CardBelote.HEART_10})));
        assertEq(1,hs_.size());
        HandBelote h_ = hs_.get(0);
        assertEq(2,h_.total());
        assertEq(CardBelote.HEART_1,h_.carte(0));
        assertEq(CardBelote.HEART_10,h_.carte(1));
    }
    @Test
    public void handPresidentList1() {
        assertTrue(saveHandPresidentList(new CustList<HandPresident>()).isEmpty());
    }
    @Test
    public void handPresidentList2() {
        CustList<HandPresident> hs_ = saveHandPresidentList(new CustList<HandPresident>(HandPresident.create(new CardPresident[]{CardPresident.HEART_1,CardPresident.HEART_10})));
        assertEq(1,hs_.size());
        HandPresident h_ = hs_.get(0);
        assertEq(2,h_.total());
        assertEq(CardPresident.HEART_1,h_.carte(0));
        assertEq(CardPresident.HEART_10,h_.carte(1));
    }
    @Test
    public void handTarotList1() {
        assertTrue(saveHandTarotList(new CustList<HandTarot>()).isEmpty());
    }
    @Test
    public void handTarotList2() {
        CustList<HandTarot> hs_ = saveHandTarotList(new CustList<HandTarot>(HandTarot.create(new CardTarot[]{CardTarot.HEART_1,CardTarot.HEART_10})));
        assertEq(1,hs_.size());
        HandTarot h_ = hs_.get(0);
        assertEq(2,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
        assertEq(CardTarot.HEART_10,h_.carte(1));
    }
    @Test
    public void trickBelote1() {
        assertTrue(saveTrickBelote(new TrickBelote()).estVide());
    }
    @Test
    public void trickBelote2() {
        TrickBelote t_ = new TrickBelote();
        t_.setCards(HandBelote.create(new CardBelote[]{CardBelote.HEART_1,CardBelote.HEART_10}));
        TrickBelote h_ = saveTrickBelote(t_);
        assertEq(2,h_.total());
        assertEq(CardBelote.HEART_1,h_.carte(0));
        assertEq(CardBelote.HEART_10,h_.carte(1));
    }
    @Test
    public void trickPresident1() {
        assertTrue(saveTrickPresident(new TrickPresident()).getCards().isEmpty());
    }
    @Test
    public void trickPresident2() {
        TrickPresident t_ = new TrickPresident();
        t_.setCards(new CustList<HandPresident>(HandPresident.create(new CardPresident[]{CardPresident.HEART_1,CardPresident.HEART_10})));
        TrickPresident h_ = saveTrickPresident(t_);
        assertEq(1,h_.total());
        assertEq(2,h_.carte(0).total());
        assertEq(CardPresident.HEART_1,h_.carte(0).carte(0));
        assertEq(CardPresident.HEART_10,h_.carte(0).carte(1));
    }
    @Test
    public void trickTarot1() {
        assertTrue(saveTrickTarot(new TrickTarot()).estVide());
    }
    @Test
    public void trickTarot2() {
        TrickTarot t_ = new TrickTarot();
        t_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_1,CardTarot.HEART_10}));
        TrickTarot h_ = saveTrickTarot(t_);
        assertEq(2,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
        assertEq(CardTarot.HEART_10,h_.carte(1));
    }
    @Test
    public void trickBeloteList1() {
        assertTrue(saveTrickBeloteList(new CustList<TrickBelote>()).isEmpty());
    }
    @Test
    public void trickBeloteList2() {
        TrickBelote t_ = new TrickBelote();
        t_.setCards(HandBelote.create(new CardBelote[]{CardBelote.HEART_1,CardBelote.HEART_10}));
        CustList<TrickBelote> hs_ = saveTrickBeloteList(new CustList<TrickBelote>(t_));
        assertEq(1,hs_.size());
        TrickBelote h_ = hs_.get(0);
        assertEq(2,h_.total());
        assertEq(CardBelote.HEART_1,h_.carte(0));
        assertEq(CardBelote.HEART_10,h_.carte(1));
    }
    @Test
    public void trickPresidentList1() {
        assertTrue(saveTrickPresidentList(new CustList<TrickPresident>()).isEmpty());
    }
    @Test
    public void trickPresidentList2() {
        TrickPresident t_ = new TrickPresident();
        t_.setCards(new CustList<HandPresident>(HandPresident.create(new CardPresident[]{CardPresident.HEART_1,CardPresident.HEART_10})));
        CustList<TrickPresident> hs_ = saveTrickPresidentList(new CustList<TrickPresident>(t_));
        assertEq(1,hs_.size());
        TrickPresident h_ = hs_.get(0);
        assertEq(1,h_.total());
        assertEq(2,h_.carte(0).total());
        assertEq(CardPresident.HEART_1,h_.carte(0).carte(0));
        assertEq(CardPresident.HEART_10,h_.carte(0).carte(1));
    }
    @Test
    public void trickTarotList1() {
        assertTrue(saveTrickTarotList(new CustList<TrickTarot>()).isEmpty());
    }
    @Test
    public void trickTarotList2() {
        TrickTarot t_ = new TrickTarot();
        t_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_1,CardTarot.HEART_10}));
        CustList<TrickTarot> hs_ = saveTrickTarotList(new CustList<TrickTarot>(t_));
        assertEq(1,hs_.size());
        TrickTarot h_ = hs_.get(0);
        assertEq(2,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
        assertEq(CardTarot.HEART_10,h_.carte(1));
    }
    @Test
    public void bidBeloteSuitList1() {
        assertTrue(saveBidBeloteSuitList(new CustList<BidBeloteSuit>()).isEmpty());
    }
    @Test
    public void bidBeloteSuitList2() {
        CustList<BidBeloteSuit> b_ = new CustList<BidBeloteSuit>();
        b_.add(bidSuit(Suit.HEART, 80,BidBelote.SUIT));
        CustList<BidBeloteSuit> s_ = saveBidBeloteSuitList(b_);
        assertEq(1,s_.size());
        assertEq(Suit.HEART,s_.get(0).getSuit());
        assertEq(80,s_.get(0).getPoints());
        assertEq(BidBelote.SUIT,s_.get(0).getBid());
    }
    @Test
    public void bidBeloteSuitList3() {
        CustList<BidBeloteSuit> b_ = new CustList<BidBeloteSuit>();
        b_.add(bidSuit(Suit.UNDEFINED, 0,BidBelote.FOLD));
        CustList<BidBeloteSuit> s_ = saveBidBeloteSuitList(b_);
        assertEq(1,s_.size());
        assertEq(Suit.UNDEFINED,s_.get(0).getSuit());
        assertEq(0,s_.get(0).getPoints());
        assertEq(BidBelote.FOLD,s_.get(0).getBid());
    }
    @Test
    public void playingMap1() {
        assertTrue(savePlayingMap(new IntMap<Playing>()).isEmpty());
    }
    @Test
    public void playingMap2() {
        IntMap<Playing> b_ = new IntMap<Playing>();
        b_.addEntry(2,Playing.SKIPPED);
        b_.addEntry(4,Playing.PASS);
        IntMap<Playing> s_ = savePlayingMap(b_);
        assertEq(2,s_.size());
        assertEq(2,s_.getKey(0));
        assertEq(Playing.SKIPPED,s_.getValue(0));
        assertEq(4,s_.getKey(1));
        assertEq(Playing.PASS,s_.getValue(1));
    }
    @Test
    public void bidTarotList1() {
        assertTrue(saveBidTarotList(new CustList<BidTarot>()).isEmpty());
    }
    @Test
    public void bidTarotList2() {
        CustList<BidTarot> b_ = new CustList<BidTarot>();
        b_.add(BidTarot.SLAM_TAKE);
        CustList<BidTarot> s_ = saveBidTarotList(b_);
        assertEq(1,s_.size());
        assertEq(BidTarot.SLAM_TAKE,s_.get(0));
    }
    @Test
    public void bidTarotList3() {
        CustList<BidTarot> b_ = new CustList<BidTarot>();
        b_.add(BidTarot.FOLD);
        CustList<BidTarot> s_ = saveBidTarotList(b_);
        assertEq(1,s_.size());
        assertEq(BidTarot.FOLD,s_.get(0));
    }
    @Test
    public void bidTarotList4() {
        CustList<BidTarot> b_ = new CustList<BidTarot>();
        b_.add(BidTarot.FOLD);
        b_.add(BidTarot.SLAM_TAKE);
        CustList<BidTarot> s_ = saveBidTarotList(b_);
        assertEq(2,s_.size());
        assertEq(BidTarot.FOLD,s_.get(0));
        assertEq(BidTarot.SLAM_TAKE,s_.get(1));
    }
    @Test
    public void bidTarotList5() {
        CustList<BidTarot> b_ = new CustList<BidTarot>();
        b_.add(BidTarot.SLAM_TAKE);
        b_.add(BidTarot.FOLD);
        CustList<BidTarot> s_ = saveBidTarotList(b_);
        assertEq(2,s_.size());
        assertEq(BidTarot.SLAM_TAKE,s_.get(0));
        assertEq(BidTarot.FOLD,s_.get(1));
    }
    @Test
    public void saveHandfuls1() {
        assertTrue(saveHandfuls(new CustList<Handfuls>()).isEmpty());
    }
    @Test
    public void saveHandfuls2() {
        CustList<Handfuls> t_ = new CustList<Handfuls>();
        t_.add(Handfuls.ONE);
        t_.add(Handfuls.FOUR);
        CustList<Handfuls> h_ = saveHandfuls(t_);
        assertEq(2,h_.size());
        assertEq(Handfuls.ONE,h_.get(0));
        assertEq(Handfuls.FOUR,h_.get(1));
    }
    @Test
    public void saveHandfuls3() {
        CustList<Handfuls> t_ = new CustList<Handfuls>();
        t_.add(Handfuls.NO);
        CustList<Handfuls> h_ = saveHandfuls(t_);
        assertEq(1,h_.size());
        assertEq(Handfuls.NO,h_.get(0));
    }
    @Test
    public void saveMiseres1() {
        assertTrue(saveMiseres(new CustList<Miseres>()).isEmpty());
    }
    @Test
    public void saveMiseres2() {
        CustList<Miseres> t_ = new CustList<Miseres>();
        t_.add(Miseres.POINT);
        t_.add(Miseres.LOW_CARDS);
        CustList<Miseres> h_ = saveMiseres(t_);
        assertEq(2,h_.size());
        assertEq(Miseres.POINT,h_.get(0));
        assertEq(Miseres.LOW_CARDS,h_.get(1));
    }
    @Test
    public void saveHandfulsList1() {
        assertTrue(saveHandfulsList(new IdList<IdList<Handfuls>>()).isEmpty());
    }
    @Test
    public void saveHandfulsList2() {
        IdList<Handfuls> t_ = new IdList<Handfuls>();
        t_.add(Handfuls.ONE);
        t_.add(Handfuls.FOUR);
        IdList<IdList<Handfuls>> l_ = new IdList<IdList<Handfuls>>();
        l_.add(t_);
        CustList<IdList<Handfuls>> hs_ = saveHandfulsList(l_);
        assertEq(1,hs_.size());
        IdList<Handfuls> h_ = hs_.get(0);
        assertEq(2,h_.size());
        assertEq(Handfuls.ONE,h_.get(0));
        assertEq(Handfuls.FOUR,h_.get(1));
    }
    @Test
    public void saveMiseresList1() {
        assertTrue(saveMiseresList(new IdList<IdList<Miseres>>()).isEmpty());
    }
    @Test
    public void saveMiseresList2() {
        IdList<Miseres> t_ = new IdList<Miseres>();
        t_.add(Miseres.POINT);
        t_.add(Miseres.LOW_CARDS);
        IdList<IdList<Miseres>> l_ = new IdList<IdList<Miseres>>();
        l_.add(t_);
        CustList<IdList<Miseres>> hs_ = saveMiseresList(l_);
        assertEq(1,hs_.size());
        IdList<Miseres> h_ = hs_.get(0);
        assertEq(2,h_.size());
        assertEq(Miseres.POINT,h_.get(0));
        assertEq(Miseres.LOW_CARDS,h_.get(1));
    }
    @Test
    public void resultsBelote1() {
        ResultsBelote r_ = new ResultsBelote();
        r_.getRes().setScores(new CustList<Longs>());
        GameBelote g_ = new GameBelote();
        r_.setGame(g_);
        g_.setNumber(2);
        g_.setTricks(new CustList<TrickBelote>());
        g_.setProgressingTrick(new TrickBelote());
        g_.setScores(Longs.newList());
        g_.setDeclaresBeloteRebelote(new CustList<HandBelote>());
        g_.setDeclares(new CustList<DeclareHandBelote>());
        g_.setWonLastTrick(new CustList<BoolVal>());
        g_.setBids(new CustList<BidBeloteSuit>());
        g_.setType(GameType.RANDOM);
        DealBelote deal_ = new DealBelote();
        g_.setDeal(deal_);
        deal_.setDealer(3);
        deal_.setNbDeals(4);
        deal_.getDeal().add(HandBelote.create(new CardBelote[]{}));
        ResultsBelote out_ = saveResultsBelote(r_);
        assertEq(0,out_.getRes().getScores().size());
        assertEq(2,out_.getGame().getNumber());
        assertEq(0,out_.getGame().getProgressingTrick().getCards().getCards().size());
        assertEq(0,out_.getGame().getTricks().size());
        assertEq(0,out_.getGame().getBids().size());
        assertEq(0,out_.getGame().getScores().size());
        assertEq(0,out_.getGame().getDeclares().size());
        assertEq(0,out_.getGame().getDeclaresBeloteRebelote().size());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(0,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(3,out_.getGame().getDeal().getDealer());
        assertEq(4,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsBelote2() {
        ResultsBelote r_ = new ResultsBelote();
        r_.getRes().setScores(new CustList<Longs>(Longs.newList(8)));
        GameBelote g_ = new GameBelote();
        r_.setGame(g_);
        g_.setNumber(2);
        TrickBelote b1_ = new TrickBelote();
        b1_.getCards().ajouter(CardBelote.HEART_1);
        g_.setTricks(new CustList<TrickBelote>(b1_));
        TrickBelote b2_ = new TrickBelote();
        b2_.getCards().ajouter(CardBelote.HEART_10);
        g_.setProgressingTrick(b2_);
        g_.setScores(Longs.newList(3));
        g_.setDeclaresBeloteRebelote(new CustList<HandBelote>(new HandBelote()));
        CustList<DeclareHandBelote> dec_ = new CustList<DeclareHandBelote>();
        DeclareHandBelote dece_ = new DeclareHandBelote();
        dece_.setDeclare(DeclaresBelote.THIRTY);
        dece_.setHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_8}));
        dece_.setPlayer(6);
        dec_.add(dece_);
        g_.setDeclares(dec_);
        g_.setWonLastTrick(new CustList<BoolVal>());
        g_.setBids(new CustList<BidBeloteSuit>());
        g_.setType(GameType.RANDOM);
        DealBelote deal_ = new DealBelote();
        g_.setDeal(deal_);
        deal_.setDealer(4);
        deal_.setNbDeals(5);
        deal_.getDeal().add(HandBelote.create(new CardBelote[]{CardBelote.HEART_9}));
        ResultsBelote out_ = saveResultsBelote(r_);
        assertEq(1,out_.getRes().getScores().size());
        assertEq(1,out_.getRes().getScores().get(0).size());
        assertEq(8,out_.getRes().getScores().get(0).get(0));
        assertEq(2,out_.getGame().getNumber());
        assertEq(1,out_.getGame().getProgressingTrick().getCards().getCards().size());
        assertEq(CardBelote.HEART_10,out_.getGame().getProgressingTrick().getCards().getCards().get(0));
        assertEq(1,out_.getGame().getTricks().size());
        assertEq(1,out_.getGame().getTricks().get(0).getCards().getCards().size());
        assertEq(CardBelote.HEART_1,out_.getGame().getTricks().get(0).getCards().getCards().get(0));
        assertEq(0,out_.getGame().getBids().size());
        assertEq(1,out_.getGame().getScores().size());
        assertEq(3,out_.getGame().getScores().get(0));
        assertEq(1,out_.getGame().getDeclares().size());
        assertEq(1,out_.getGame().getDeclares().get(0).getHand().total());
        assertEq(CardBelote.HEART_8,out_.getGame().getDeclares().get(0).getHand().carte(0));
        assertEq(DeclaresBelote.THIRTY,out_.getGame().getDeclares().get(0).getDeclare());
        assertEq(6,out_.getGame().getDeclares().get(0).getPlayer());
        assertEq(1,out_.getGame().getDeclaresBeloteRebelote().size());
        assertEq(0,out_.getGame().getDeclaresBeloteRebelote().get(0).total());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(1,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardBelote.HEART_9,out_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(4,out_.getGame().getDeal().getDealer());
        assertEq(5,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsBelote3() {
        ResultsBelote r_ = new ResultsBelote();
        r_.getRes().setScores(new CustList<Longs>(Longs.newList()));
        GameBelote g_ = new GameBelote();
        r_.setGame(g_);
        g_.setNumber(2);
        TrickBelote b1_ = new TrickBelote();
        b1_.getCards().ajouter(CardBelote.HEART_1);
        g_.setTricks(new CustList<TrickBelote>(b1_));
        TrickBelote b2_ = new TrickBelote();
        b2_.getCards().ajouter(CardBelote.HEART_10);
        g_.setProgressingTrick(b2_);
        g_.setScores(Longs.newList(3));
        g_.setDeclaresBeloteRebelote(new CustList<HandBelote>(new HandBelote()));
        CustList<DeclareHandBelote> dec_ = new CustList<DeclareHandBelote>();
        DeclareHandBelote dece_ = new DeclareHandBelote();
        dece_.setDeclare(DeclaresBelote.THIRTY);
        dece_.setHand(HandBelote.create(new CardBelote[]{}));
        dece_.setPlayer(6);
        dec_.add(dece_);
        g_.setDeclares(dec_);
        g_.setWonLastTrick(new CustList<BoolVal>());
        g_.setBids(new CustList<BidBeloteSuit>());
        g_.setType(GameType.RANDOM);
        DealBelote deal_ = new DealBelote();
        g_.setDeal(deal_);
        deal_.setDealer(4);
        deal_.setNbDeals(5);
        deal_.getDeal().add(HandBelote.create(new CardBelote[]{CardBelote.HEART_9}));
        ResultsBelote out_ = saveResultsBelote(r_);
        assertEq(1,out_.getRes().getScores().size());
        assertEq(0,out_.getRes().getScores().get(0).size());
        assertEq(2,out_.getGame().getNumber());
        assertEq(1,out_.getGame().getProgressingTrick().getCards().getCards().size());
        assertEq(CardBelote.HEART_10,out_.getGame().getProgressingTrick().getCards().getCards().get(0));
        assertEq(1,out_.getGame().getTricks().size());
        assertEq(1,out_.getGame().getTricks().get(0).getCards().getCards().size());
        assertEq(CardBelote.HEART_1,out_.getGame().getTricks().get(0).getCards().getCards().get(0));
        assertEq(0,out_.getGame().getBids().size());
        assertEq(1,out_.getGame().getScores().size());
        assertEq(3,out_.getGame().getScores().get(0));
        assertEq(1,out_.getGame().getDeclares().size());
        assertEq(0,out_.getGame().getDeclares().get(0).getHand().total());
        assertEq(DeclaresBelote.THIRTY,out_.getGame().getDeclares().get(0).getDeclare());
        assertEq(6,out_.getGame().getDeclares().get(0).getPlayer());
        assertEq(1,out_.getGame().getDeclaresBeloteRebelote().size());
        assertEq(0,out_.getGame().getDeclaresBeloteRebelote().get(0).total());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(1,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardBelote.HEART_9,out_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(4,out_.getGame().getDeal().getDealer());
        assertEq(5,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsPresident1() {
        ResultsPresident r_ = new ResultsPresident();
        r_.getRes().setScores(new CustList<Longs>());
        GamePresident g_ = new GamePresident();
        r_.setGame(g_);
        g_.setNumber(2);
        g_.setTricks(new CustList<TrickPresident>());
        g_.setProgressingTrick(new TrickPresident());
        g_.setScores(Longs.newList());
        g_.setRanks(Ints.newList());
        g_.setSwitchedCards(new CustList<HandPresident>());
        g_.setType(GameType.RANDOM);
        DealPresident deal_ = new DealPresident();
        g_.setDeal(deal_);
        deal_.setDealer(3);
        deal_.setNbDeals(4);
        deal_.getDeal().add(HandPresident.create(new CardPresident[]{}));
        ResultsPresident out_ = saveResultsPresident(r_);
        assertEq(0,out_.getRes().getScores().size());
        assertEq(2,out_.getGame().getNumber());
        assertEq(0,out_.getGame().getProgressingTrick().getCards().size());
        assertEq(0,out_.getGame().getTricks().size());
        assertEq(0,out_.getGame().getScores().size());
        assertEq(0,out_.getGame().getRanks().size());
        assertEq(0,out_.getGame().getSwitchedCards().size());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(0,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(3,out_.getGame().getDeal().getDealer());
        assertEq(4,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsPresident2() {
        ResultsPresident r_ = new ResultsPresident();
        r_.getRes().setScores(new CustList<Longs>(Longs.newList(8)));
        GamePresident g_ = new GamePresident();
        r_.setGame(g_);
        g_.setNumber(2);
        TrickPresident b1_ = new TrickPresident();
        b1_.getCards().add(new HandPresident());
        b1_.getCards().last().ajouter(CardPresident.HEART_1);
        g_.setTricks(new CustList<TrickPresident>(b1_));
        TrickPresident b2_ = new TrickPresident();
        b2_.getCards().add(new HandPresident());
        b2_.getCards().last().ajouter(CardPresident.HEART_10);
        g_.setProgressingTrick(b2_);
        g_.setScores(Longs.newList(3));
        g_.setRanks(Ints.newList(7));
        g_.setSwitchedCards(new CustList<HandPresident>(HandPresident.create(new CardPresident[]{CardPresident.HEART_8})));
        g_.setType(GameType.RANDOM);
        DealPresident deal_ = new DealPresident();
        g_.setDeal(deal_);
        deal_.setDealer(4);
        deal_.setNbDeals(5);
        deal_.getDeal().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_9}));
        ResultsPresident out_ = saveResultsPresident(r_);
        assertEq(1,out_.getRes().getScores().size());
        assertEq(1,out_.getRes().getScores().get(0).size());
        assertEq(8,out_.getRes().getScores().get(0).get(0));
        assertEq(2,out_.getGame().getNumber());
        assertEq(1,out_.getGame().getProgressingTrick().getCards().size());
        assertEq(1,out_.getGame().getProgressingTrick().getCards().get(0).getCards().size());
        assertEq(CardPresident.HEART_10,out_.getGame().getProgressingTrick().getCards().get(0).getCards().get(0));
        assertEq(1,out_.getGame().getTricks().size());
        assertEq(1,out_.getGame().getTricks().get(0).getCards().size());
        assertEq(1,out_.getGame().getTricks().get(0).getCards().get(0).total());
        assertEq(CardPresident.HEART_1,out_.getGame().getTricks().get(0).getCards().get(0).getCards().get(0));
        assertEq(1,out_.getGame().getScores().size());
        assertEq(3,out_.getGame().getScores().get(0));
        assertEq(1,out_.getGame().getRanks().size());
        assertEq(7,out_.getGame().getRanks().get(0));
        assertEq(1,out_.getGame().getSwitchedCards().size());
        assertEq(1,out_.getGame().getSwitchedCards().get(0).total());
        assertEq(CardPresident.HEART_8,out_.getGame().getSwitchedCards().get(0).carte(0));
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(1,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardPresident.HEART_9,out_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(4,out_.getGame().getDeal().getDealer());
        assertEq(5,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsPresident3() {
        ResultsPresident r_ = new ResultsPresident();
        r_.getRes().setScores(new CustList<Longs>(Longs.newList()));
        GamePresident g_ = new GamePresident();
        r_.setGame(g_);
        g_.setNumber(2);
        TrickPresident b1_ = new TrickPresident();
        b1_.getCards().add(new HandPresident());
        g_.setTricks(new CustList<TrickPresident>(b1_));
        TrickPresident b2_ = new TrickPresident();
        b2_.getCards().add(new HandPresident());
        b2_.getCards().last().ajouter(CardPresident.HEART_10);
        g_.setProgressingTrick(b2_);
        g_.setScores(Longs.newList(3));
        g_.setRanks(Ints.newList(7));
        g_.setSwitchedCards(new CustList<HandPresident>(HandPresident.create(new CardPresident[]{})));
        g_.setType(GameType.RANDOM);
        DealPresident deal_ = new DealPresident();
        g_.setDeal(deal_);
        deal_.setDealer(4);
        deal_.setNbDeals(5);
        deal_.getDeal().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_9}));
        ResultsPresident out_ = saveResultsPresident(r_);
        assertEq(1,out_.getRes().getScores().size());
        assertEq(0,out_.getRes().getScores().get(0).size());
        assertEq(2,out_.getGame().getNumber());
        assertEq(1,out_.getGame().getProgressingTrick().getCards().size());
        assertEq(1,out_.getGame().getProgressingTrick().getCards().get(0).getCards().size());
        assertEq(CardPresident.HEART_10,out_.getGame().getProgressingTrick().getCards().get(0).getCards().get(0));
        assertEq(1,out_.getGame().getTricks().size());
        assertEq(1,out_.getGame().getTricks().get(0).getCards().size());
        assertEq(0,out_.getGame().getTricks().get(0).getCards().get(0).total());
        assertEq(1,out_.getGame().getScores().size());
        assertEq(3,out_.getGame().getScores().get(0));
        assertEq(1,out_.getGame().getRanks().size());
        assertEq(7,out_.getGame().getRanks().get(0));
        assertEq(1,out_.getGame().getSwitchedCards().size());
        assertEq(0,out_.getGame().getSwitchedCards().get(0).total());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(1,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardPresident.HEART_9,out_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(4,out_.getGame().getDeal().getDealer());
        assertEq(5,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsTarot1() {
        ResultsTarot r_ = new ResultsTarot();
        r_.getRes().setScores(new CustList<Longs>());
        GameTarot g_ = new GameTarot();
        r_.setGame(g_);
        g_.setNumber(2);
        g_.setTricks(new CustList<TrickTarot>());
        g_.setProgressingTrick(new TrickTarot());
        g_.setScores(Longs.newList());
        g_.setDeclaresMiseres(new CustList<IdList<Miseres>>());
        g_.setDeclaresHandfuls(new CustList<IdList<Handfuls>>());
        g_.setHandfuls(new CustList<HandTarot>());
        g_.setType(GameType.RANDOM);
        g_.setSmallBound(new CustList<BoolVal>());
        g_.setCalledCards(new HandTarot());
        g_.setBids(new IdList<BidTarot>());
        DealTarot deal_ = new DealTarot();
        g_.setDeal(deal_);
        deal_.setDealer(3);
        deal_.setNbDeals(4);
        deal_.getDeal().add(HandTarot.create(new CardTarot[]{}));
        ResultsTarot out_ = saveResultsTarot(r_);
        assertEq(0,out_.getRes().getScores().size());
        assertEq(2,out_.getGame().getNumber());
        assertEq(0,out_.getGame().getProgressingTrick().total());
        assertEq(0,out_.getGame().getTricks().size());
        assertEq(0,out_.getGame().getScores().size());
        assertEq(0,out_.getGame().getBids().size());
        assertEq(0,out_.getGame().getCalledCards().total());
        assertEq(0,out_.getGame().getHandfuls().size());
        assertEq(0,out_.getGame().getDeclaresMiseres().size());
        assertEq(0,out_.getGame().getDeclaresHandfuls().size());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(0,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(3,out_.getGame().getDeal().getDealer());
        assertEq(4,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsTarot2() {
        ResultsTarot r_ = new ResultsTarot();
        r_.getRes().setScores(new CustList<Longs>(Longs.newList(8)));
        GameTarot g_ = new GameTarot();
        r_.setGame(g_);
        g_.setNumber(2);
        TrickTarot b1_ = new TrickTarot();
        b1_.getCards().ajouter(CardTarot.HEART_1);
        g_.setTricks(new CustList<TrickTarot>(b1_));
        TrickTarot b2_ = new TrickTarot();
        b2_.getCards().ajouter(CardTarot.HEART_10);
        g_.setProgressingTrick(b2_);
        g_.setScores(Longs.newList(3));
        CustList<IdList<Miseres>> mis_ = new CustList<IdList<Miseres>>();
        mis_.add(new IdList<Miseres>(Miseres.LOW_CARDS));
        g_.setDeclaresMiseres(mis_);
        CustList<IdList<Handfuls>> hands_ = new CustList<IdList<Handfuls>>();
        hands_.add(new IdList<Handfuls>(Handfuls.ONE));
        g_.setDeclaresHandfuls(hands_);
        g_.setHandfuls(new CustList<HandTarot>(HandTarot.create(new CardTarot[]{CardTarot.HEART_8})));
        g_.setType(GameType.RANDOM);
        g_.setSmallBound(new CustList<BoolVal>());
        g_.setCalledCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_7}));
        g_.setBids(new IdList<BidTarot>());
        DealTarot deal_ = new DealTarot();
        g_.setDeal(deal_);
        deal_.setDealer(4);
        deal_.setNbDeals(5);
        deal_.getDeal().add(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        ResultsTarot out_ = saveResultsTarot(r_);
        assertEq(1,out_.getRes().getScores().size());
        assertEq(1,out_.getRes().getScores().get(0).size());
        assertEq(8,out_.getRes().getScores().get(0).get(0));
        assertEq(2,out_.getGame().getNumber());
        assertEq(1,out_.getGame().getProgressingTrick().total());
        assertEq(CardTarot.HEART_10,out_.getGame().getProgressingTrick().carte(0));
        assertEq(1,out_.getGame().getTricks().size());
        assertEq(1,out_.getGame().getTricks().get(0).total());
        assertEq(CardTarot.HEART_1,out_.getGame().getTricks().get(0).carte(0));
        assertEq(1,out_.getGame().getScores().size());
        assertEq(3,out_.getGame().getScores().get(0));
        assertEq(0,out_.getGame().getBids().size());
        assertEq(1,out_.getGame().getCalledCards().total());
        assertEq(CardTarot.HEART_7,out_.getGame().getCalledCards().carte(0));
        assertEq(1,out_.getGame().getHandfuls().size());
        assertEq(1,out_.getGame().getHandfuls().get(0).total());
        assertEq(CardTarot.HEART_8,out_.getGame().getHandfuls().get(0).carte(0));
        assertEq(1,out_.getGame().getDeclaresMiseres().size());
        assertEq(1,out_.getGame().getDeclaresMiseres().get(0).size());
        assertEq(Miseres.LOW_CARDS, out_.getGame().getDeclaresMiseres().get(0).get(0));
        assertEq(1,out_.getGame().getDeclaresHandfuls().size());
        assertEq(1,out_.getGame().getDeclaresHandfuls().get(0).size());
        assertEq(Handfuls.ONE, out_.getGame().getDeclaresHandfuls().get(0).get(0));
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(1,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardTarot.HEART_9,out_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(4,out_.getGame().getDeal().getDealer());
        assertEq(5,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void resultsTarot3() {
        ResultsTarot r_ = new ResultsTarot();
        r_.getRes().setScores(new CustList<Longs>(Longs.newList()));
        GameTarot g_ = new GameTarot();
        r_.setGame(g_);
        g_.setNumber(2);
        TrickTarot b1_ = new TrickTarot();
        b1_.getCards().ajouter(CardTarot.HEART_1);
        g_.setTricks(new CustList<TrickTarot>(b1_));
        TrickTarot b2_ = new TrickTarot();
        b2_.getCards().ajouter(CardTarot.HEART_10);
        g_.setProgressingTrick(b2_);
        g_.setScores(Longs.newList(3));
        CustList<IdList<Miseres>> mis_ = new CustList<IdList<Miseres>>();
        mis_.add(new IdList<Miseres>());
        g_.setDeclaresMiseres(mis_);
        CustList<IdList<Handfuls>> hands_ = new CustList<IdList<Handfuls>>();
        hands_.add(new IdList<Handfuls>());
        g_.setDeclaresHandfuls(hands_);
        g_.setHandfuls(new CustList<HandTarot>(HandTarot.create(new CardTarot[]{})));
        g_.setType(GameType.RANDOM);
        g_.setSmallBound(new CustList<BoolVal>());
        g_.setCalledCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_7}));
        g_.setBids(new IdList<BidTarot>());
        DealTarot deal_ = new DealTarot();
        g_.setDeal(deal_);
        deal_.setDealer(4);
        deal_.setNbDeals(5);
        deal_.getDeal().add(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        ResultsTarot out_ = saveResultsTarot(r_);
        assertEq(1,out_.getRes().getScores().size());
        assertEq(0,out_.getRes().getScores().get(0).size());
        assertEq(2,out_.getGame().getNumber());
        assertEq(1,out_.getGame().getProgressingTrick().total());
        assertEq(CardTarot.HEART_10,out_.getGame().getProgressingTrick().carte(0));
        assertEq(1,out_.getGame().getTricks().size());
        assertEq(1,out_.getGame().getTricks().get(0).total());
        assertEq(CardTarot.HEART_1,out_.getGame().getTricks().get(0).carte(0));
        assertEq(1,out_.getGame().getScores().size());
        assertEq(3,out_.getGame().getScores().get(0));
        assertEq(0,out_.getGame().getBids().size());
        assertEq(1,out_.getGame().getCalledCards().total());
        assertEq(CardTarot.HEART_7,out_.getGame().getCalledCards().carte(0));
        assertEq(1,out_.getGame().getHandfuls().size());
        assertEq(0,out_.getGame().getHandfuls().get(0).total());
        assertEq(1,out_.getGame().getDeclaresMiseres().size());
        assertEq(0,out_.getGame().getDeclaresMiseres().get(0).size());
        assertEq(1,out_.getGame().getDeclaresHandfuls().size());
        assertEq(0,out_.getGame().getDeclaresHandfuls().get(0).size());
        assertEq(1,out_.getGame().getDeal().getDeal().size());
        assertEq(1,out_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardTarot.HEART_9,out_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(GameType.RANDOM, out_.getGame().getType());
        assertEq(4,out_.getGame().getDeal().getDealer());
        assertEq(5,out_.getGame().getDeal().getNbDeals());
    }
    @Test
    public void tricksBelote1() {
        TricksHandsBelote r_ = new TricksHandsBelote();
        r_.setDistribution(new DealBelote());
        r_.getDistribution().setNbDeals(6);
        r_.getDistribution().setDealer(7);
        r_.setPreneur(5);
        r_.setTricks(new CustList<TrickBelote>());
        r_.setCardsHandsAtInitialState(new CustList<HandBelote>());
        r_.setBid(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        TricksHandsBelote out_ = saveTricksHandsBelote(r_);
        assertEq(0,out_.getTricks().size());
        assertEq(0,out_.getDistribution().getDeal().size());
        assertEq(0,out_.getCardsHandsAtInitialState().size());
        assertEq(5,out_.getPreneur());
        assertEq(6,out_.getDistribution().getNbDeals());
        assertEq(7,out_.getDistribution().getDealer());
        assertEq(Suit.HEART,out_.getBid().getSuit());
        assertEq(80,out_.getBid().getPoints());
        assertEq(BidBelote.SUIT,out_.getBid().getBid());
    }
    @Test
    public void tricksBelote2() {
        TricksHandsBelote r_ = new TricksHandsBelote();
        r_.setDistribution(new DealBelote());
        r_.getDistribution().getDeal().add(HandBelote.create(new CardBelote[]{CardBelote.HEART_10}));
        r_.setTricks(new CustList<TrickBelote>());
        r_.setCardsHandsAtInitialState(new CustList<HandBelote>());
        r_.setBid(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        r_.getCardsHandsAtInitialState().add(HandBelote.create(new CardBelote[]{CardBelote.HEART_1}));
        TricksHandsBelote out_ = saveTricksHandsBelote(r_);
        assertEq(1,out_.getCardsHandsAtInitialState().size());
        assertEq(1,out_.getCardsHandsAtInitialState().get(0).total());
        assertEq(CardBelote.HEART_1,out_.getCardsHandsAtInitialState().get(0).carte(0));
        assertEq(1,out_.getDistribution().getDeal().size());
        assertEq(1,out_.getDistribution().getDeal().get(0).total());
        assertEq(CardBelote.HEART_10,out_.getDistribution().getDeal().get(0).carte(0));
    }
    @Test
    public void tricksBelote3() {
        TricksHandsBelote r_ = new TricksHandsBelote();
        r_.setDistribution(new DealBelote());
        r_.getDistribution().getDeal().add(HandBelote.create(new CardBelote[]{}));
        r_.setTricks(new CustList<TrickBelote>());
        r_.setCardsHandsAtInitialState(new CustList<HandBelote>());
        r_.setBid(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        r_.getCardsHandsAtInitialState().add(HandBelote.create(new CardBelote[]{}));
        TricksHandsBelote out_ = saveTricksHandsBelote(r_);
        assertEq(1,out_.getCardsHandsAtInitialState().size());
        assertEq(0,out_.getCardsHandsAtInitialState().get(0).total());
        assertEq(1,out_.getDistribution().getDeal().size());
        assertEq(0,out_.getDistribution().getDeal().get(0).total());
    }
    @Test
    public void tricksPresident1() {
        TricksHandsPresident r_ = new TricksHandsPresident();
        r_.setDistribution(new DealPresident());
        r_.getDistribution().setNbDeals(6);
        r_.getDistribution().setDealer(7);
        r_.setTricks(new CustList<TrickPresident>());
        r_.setProgressingTrick(new TrickPresident());
        r_.setSwitchedCards(new CustList<HandPresident>());
        r_.setRanks(new Ints());
        r_.setCardsHandsAtInitialState(new CustList<HandPresident>());
        TricksHandsPresident out_ = saveTricksHandsPresident(r_);
        assertEq(0,out_.getCardsHandsAtInitialState().size());
        assertEq(0,out_.getDistribution().getDeal().size());
        assertEq(0,out_.getSwitchedCards().size());
        assertEq(0,out_.getProgressingTrick().getCards().size());
        assertEq(0,out_.getTricks().size());
        assertEq(0,out_.getRanks().size());
        assertEq(6,out_.getDistribution().getNbDeals());
        assertEq(7,out_.getDistribution().getDealer());
    }
    @Test
    public void tricksPresident2() {
        TricksHandsPresident r_ = new TricksHandsPresident();
        r_.setReversed(false);
        r_.setNumberMaxSwitchedCards(10);
        r_.setDistribution(new DealPresident());
        r_.getDistribution().getDeal().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_10}));
        TrickPresident t1_ = new TrickPresident();
        t1_.getCards().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_7}));
        r_.setTricks(new CustList<TrickPresident>(t1_));
        TrickPresident t2_ = new TrickPresident();
        t2_.getCards().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_8}));
        r_.setProgressingTrick(t2_);
        r_.setSwitchedCards(new CustList<HandPresident>());
        r_.getSwitchedCards().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_9}));
        r_.setRanks(Ints.newList(6));
        r_.setCardsHandsAtInitialState(new CustList<HandPresident>());
        r_.getCardsHandsAtInitialState().add(HandPresident.create(new CardPresident[]{CardPresident.HEART_1}));
        TricksHandsPresident out_ = saveTricksHandsPresident(r_);
        assertEq(1,out_.getCardsHandsAtInitialState().size());
        assertEq(1,out_.getCardsHandsAtInitialState().get(0).total());
        assertEq(CardPresident.HEART_1,out_.getCardsHandsAtInitialState().get(0).carte(0));
        assertEq(1,out_.getDistribution().getDeal().size());
        assertEq(1,out_.getDistribution().getDeal().get(0).total());
        assertEq(CardPresident.HEART_10,out_.getDistribution().getDeal().get(0).carte(0));
        assertEq(1,out_.getSwitchedCards().size());
        assertEq(1,out_.getSwitchedCards().get(0).total());
        assertEq(CardPresident.HEART_9,out_.getSwitchedCards().get(0).carte(0));
        assertEq(1,out_.getProgressingTrick().getCards().size());
        assertEq(1,out_.getProgressingTrick().getCards().get(0).total());
        assertEq(CardPresident.HEART_8,out_.getProgressingTrick().getCards().get(0).carte(0));
        assertEq(1,out_.getTricks().size());
        assertEq(1,out_.getTricks().get(0).total());
        assertEq(1,out_.getTricks().get(0).carte(0).total());
        assertEq(CardPresident.HEART_7,out_.getTricks().get(0).carte(0).carte(0));
        assertEq(1,out_.getRanks().size());
        assertEq(6,out_.getRanks().get(0));
        assertEq(10,out_.getNumberMaxSwitchedCards());
        assertFalse(out_.isReversed());
    }
    @Test
    public void tricksPresident3() {
        TricksHandsPresident r_ = new TricksHandsPresident();
        r_.setReversed(true);
        r_.setNumberMaxSwitchedCards(10);
        r_.setDistribution(new DealPresident());
        r_.getDistribution().getDeal().add(HandPresident.create(new CardPresident[]{}));
        TrickPresident t1_ = new TrickPresident();
        t1_.getCards().add(HandPresident.create(new CardPresident[]{}));
        r_.setTricks(new CustList<TrickPresident>(t1_));
        TrickPresident t2_ = new TrickPresident();
        t2_.getCards().add(HandPresident.create(new CardPresident[]{}));
        r_.setProgressingTrick(t2_);
        r_.setSwitchedCards(new CustList<HandPresident>());
        r_.getSwitchedCards().add(HandPresident.create(new CardPresident[]{}));
        r_.setRanks(Ints.newList(6));
        r_.setCardsHandsAtInitialState(new CustList<HandPresident>());
        r_.getCardsHandsAtInitialState().add(HandPresident.create(new CardPresident[]{}));
        TricksHandsPresident out_ = saveTricksHandsPresident(r_);
        assertEq(1,out_.getCardsHandsAtInitialState().size());
        assertEq(0,out_.getCardsHandsAtInitialState().get(0).total());
        assertEq(1,out_.getDistribution().getDeal().size());
        assertEq(0,out_.getDistribution().getDeal().get(0).total());
        assertEq(1,out_.getSwitchedCards().size());
        assertEq(0,out_.getSwitchedCards().get(0).total());
        assertEq(1,out_.getProgressingTrick().getCards().size());
        assertEq(0,out_.getProgressingTrick().getCards().get(0).total());
        assertEq(1,out_.getTricks().size());
        assertEq(1,out_.getTricks().get(0).total());
        assertEq(0,out_.getTricks().get(0).carte(0).total());
        assertEq(1,out_.getRanks().size());
        assertEq(6,out_.getRanks().get(0));
        assertEq(10,out_.getNumberMaxSwitchedCards());
        assertTrue(out_.isReversed());
    }
    @Test
    public void tricksTarot1() {
        TricksHandsTarot r_ = new TricksHandsTarot();
        r_.setPreneur(5);
        r_.setDistribution(new DealTarot());
        r_.getDistribution().setNbDeals(6);
        r_.getDistribution().setDealer(7);
        r_.setTricks(new CustList<TrickTarot>());
        r_.setCardsHandsAtInitialState(new CustList<HandTarot>());
        TricksHandsTarot out_ = saveTricksHandsTarot(r_);
        assertEq(0,out_.getCardsHandsAtInitialState().size());
        assertEq(0,out_.getTricks().size());
        assertEq(0,out_.getDistribution().getDeal().size());
        assertEq(5,out_.getPreneur());
        assertEq(6,out_.getDistribution().getNbDeals());
        assertEq(7,out_.getDistribution().getDealer());
    }
    @Test
    public void tricksTarot2() {
        TricksHandsTarot r_ = new TricksHandsTarot();
        r_.setPreneur(5);
        DealTarot deal_ = new DealTarot();
        deal_.getDeal().add(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        r_.setDistribution(deal_);
        r_.setTricks(new CustList<TrickTarot>());
        TrickTarot t_ = new TrickTarot();
        t_.getCards().ajouter(CardTarot.HEART_10);
        r_.getTricks().add(t_);
        r_.setCardsHandsAtInitialState(new CustList<HandTarot>());
        r_.getCardsHandsAtInitialState().add(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        TricksHandsTarot out_ = saveTricksHandsTarot(r_);
        assertEq(1,out_.getCardsHandsAtInitialState().size());
        assertEq(1,out_.getCardsHandsAtInitialState().get(0).total());
        assertEq(CardTarot.HEART_1,out_.getCardsHandsAtInitialState().get(0).carte(0));
        assertEq(1,out_.getTricks().size());
        assertEq(1,out_.getTricks().get(0).total());
        assertEq(CardTarot.HEART_10,out_.getTricks().get(0).carte(0));
        assertEq(1,out_.getDistribution().getDeal().size());
        assertEq(1,out_.getDistribution().getDeal().get(0).total());
        assertEq(CardTarot.HEART_9,out_.getDistribution().getDeal().get(0).carte(0));
        assertEq(5,out_.getPreneur());
    }
    @Test
    public void tricksTarot3() {
        TricksHandsTarot r_ = new TricksHandsTarot();
        r_.setPreneur(5);
        DealTarot deal_ = new DealTarot();
        deal_.getDeal().add(HandTarot.create(new CardTarot[]{}));
        r_.setDistribution(deal_);
        r_.setTricks(new CustList<TrickTarot>());
        TrickTarot t_ = new TrickTarot();
        r_.getTricks().add(t_);
        r_.setCardsHandsAtInitialState(new CustList<HandTarot>());
        r_.getCardsHandsAtInitialState().add(HandTarot.create(new CardTarot[]{}));
        TricksHandsTarot out_ = saveTricksHandsTarot(r_);
        assertEq(1,out_.getCardsHandsAtInitialState().size());
        assertEq(0,out_.getCardsHandsAtInitialState().get(0).total());
        assertEq(0,out_.getTricks().size());
        assertEq(1,out_.getDistribution().getDeal().size());
        assertEq(0,out_.getDistribution().getDeal().get(0).total());
        assertEq(5,out_.getPreneur());
    }
    @Test
    public void indexArrive1() {
        Games g_ = new Games();
        RulesBelote belote_ = new RulesBelote();
        belote_.setClassicCountPoints(false);
        belote_.setUnderTrumpFoe(false);
        belote_.setAllowedDeclares(new IdMap<DeclaresBelote, BoolVal>());
        belote_.setAllowedBids(new IdMap<BidBelote, BoolVal>());
        belote_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        g_.setRulesBelote(belote_);
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        IndexOfArrivingCards i_ = saveIndexArrive(1, 3, common_, g_, GameEnum.BELOTE);
        assertEq(2,i_.getPlacesPlayers().size());
        assertEq(2,i_.getPlacesPlayers().getKey(0));
        assertEq(3,i_.getPlacesPlayers().getValue(0));
        assertEq(4,i_.getPlacesPlayers().getKey(1));
        assertEq(5,i_.getPlacesPlayers().getValue(1));
        assertEq(2,i_.getReadyPlayers().size());
        assertEq(6,i_.getReadyPlayers().getKey(0));
        assertEq(BoolVal.TRUE,i_.getReadyPlayers().getValue(0));
        assertEq(7,i_.getReadyPlayers().getKey(1));
        assertEq(BoolVal.FALSE,i_.getReadyPlayers().getValue(1));
        assertEq(0,i_.getRulesBelote().getAllowedDeclares().size());
        assertEq(0,i_.getRulesBelote().getAllowedBids().size());
        assertFalse(i_.getRulesBelote().isUnderTrumpFoe());
        assertFalse(i_.getRulesBelote().isClassicCountPoints());
        assertEq(3,i_.getNbPlayers());
    }
    @Test
    public void indexArrive2() {
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        Games g_ = new Games();
        RulesBelote belote_ = new RulesBelote();
        belote_.setClassicCountPoints(true);
        belote_.setUnderTrumpFoe(true);
        IdMap<BidBelote, BoolVal> bid_ = new IdMap<BidBelote, BoolVal>();
        bid_.addEntry(BidBelote.FOLD, BoolVal.FALSE);
        bid_.addEntry(BidBelote.SUIT, BoolVal.TRUE);
        belote_.setAllowedBids(bid_);
        IdMap<DeclaresBelote, BoolVal> decl_ = new IdMap<DeclaresBelote, BoolVal>();
        decl_.addEntry(DeclaresBelote.UNDEFINED, BoolVal.TRUE);
        decl_.addEntry(DeclaresBelote.HUNDRED, BoolVal.FALSE);
        belote_.setAllowedDeclares(decl_);
        belote_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        belote_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        belote_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        g_.setRulesBelote(belote_);
        IndexOfArrivingCards i_ = saveIndexArrive(1, 3, common_, g_, GameEnum.BELOTE);
        assertEq(2,i_.getPlacesPlayers().size());
        assertEq(2,i_.getPlacesPlayers().getKey(0));
        assertEq(3,i_.getPlacesPlayers().getValue(0));
        assertEq(4,i_.getPlacesPlayers().getKey(1));
        assertEq(5,i_.getPlacesPlayers().getValue(1));
        assertEq(2,i_.getReadyPlayers().size());
        assertEq(6,i_.getReadyPlayers().getKey(0));
        assertEq(BoolVal.TRUE,i_.getReadyPlayers().getValue(0));
        assertEq(7,i_.getReadyPlayers().getKey(1));
        assertEq(BoolVal.FALSE,i_.getReadyPlayers().getValue(1));
        assertEq(2,i_.getRulesBelote().getAllowedDeclares().size());
        assertEq(DeclaresBelote.UNDEFINED,i_.getRulesBelote().getAllowedDeclares().getKey(0));
        assertEq(BoolVal.TRUE,i_.getRulesBelote().getAllowedDeclares().getValue(0));
        assertEq(DeclaresBelote.HUNDRED,i_.getRulesBelote().getAllowedDeclares().getKey(1));
        assertEq(BoolVal.FALSE,i_.getRulesBelote().getAllowedDeclares().getValue(1));
        assertEq(2,i_.getRulesBelote().getAllowedBids().size());
        assertEq(BidBelote.FOLD,i_.getRulesBelote().getAllowedBids().getKey(0));
        assertEq(BoolVal.FALSE,i_.getRulesBelote().getAllowedBids().getValue(0));
        assertEq(BidBelote.SUIT,i_.getRulesBelote().getAllowedBids().getKey(1));
        assertEq(BoolVal.TRUE,i_.getRulesBelote().getAllowedBids().getValue(1));
        assertTrue(i_.getRulesBelote().isUnderTrumpFoe());
        assertTrue(i_.getRulesBelote().isClassicCountPoints());
        assertEq(MixCardsChoice.EACH_LAUNCHING, i_.getRulesBelote().getCommon().getMixedCards());
        assertEq(DealingBelote.CLASSIC_1_VS_2_24, i_.getRulesBelote().getDealing());
        assertEq(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP, i_.getRulesBelote().getTrumpPartner());
        assertEq(3,i_.getNbPlayers());
    }
    @Test
    public void indexArrive3() {
        Games g_ = new Games();
        RulesPresident president_ = new RulesPresident();
        president_.setLoosingIfFinishByBestCards(false);
        president_.setHasToPlay(false);
        president_.setPossibleReversing(false);
        president_.setSwitchCards(false);
        president_.setLooserStartsFirst(false);
        president_.setNbStacks(5);
        president_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        president_.setNbPlayers(6);
        g_.setRulesPresident(president_);
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        IndexOfArrivingCards i_ = saveIndexArrive(1, 6, common_, g_, GameEnum.PRESIDENT);
        assertEq(2,i_.getPlacesPlayers().size());
        assertEq(2,i_.getPlacesPlayers().getKey(0));
        assertEq(3,i_.getPlacesPlayers().getValue(0));
        assertEq(4,i_.getPlacesPlayers().getKey(1));
        assertEq(5,i_.getPlacesPlayers().getValue(1));
        assertEq(2,i_.getReadyPlayers().size());
        assertEq(6,i_.getReadyPlayers().getKey(0));
        assertEq(BoolVal.TRUE,i_.getReadyPlayers().getValue(0));
        assertEq(7,i_.getReadyPlayers().getKey(1));
        assertEq(BoolVal.FALSE,i_.getReadyPlayers().getValue(1));
        assertEq(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL, i_.getRulesPresident().getEqualty());
        assertFalse(i_.getRulesPresident().isHasToPlay());
        assertFalse(i_.getRulesPresident().isPossibleReversing());
        assertFalse(i_.getRulesPresident().isSwitchCards());
        assertFalse(i_.getRulesPresident().isLoosingIfFinishByBestCards());
        assertFalse(i_.getRulesPresident().isLooserStartsFirst());
        assertEq(6,i_.getRulesPresident().getNbPlayers());
        assertEq(5,i_.getRulesPresident().getNbStacks());
        assertEq(6,i_.getNbPlayers());
    }
    @Test
    public void indexArrive4() {
        Games g_ = new Games();
        RulesPresident president_ = new RulesPresident();
        president_.setLoosingIfFinishByBestCards(true);
        president_.setHasToPlay(true);
        president_.setPossibleReversing(true);
        president_.setSwitchCards(true);
        president_.setLooserStartsFirst(true);
        president_.setNbStacks(5);
        president_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        president_.setNbPlayers(6);
        g_.setRulesPresident(president_);
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        IndexOfArrivingCards i_ = saveIndexArrive(1, 6, common_, g_, GameEnum.PRESIDENT);
        assertEq(2,i_.getPlacesPlayers().size());
        assertEq(2,i_.getPlacesPlayers().getKey(0));
        assertEq(3,i_.getPlacesPlayers().getValue(0));
        assertEq(4,i_.getPlacesPlayers().getKey(1));
        assertEq(5,i_.getPlacesPlayers().getValue(1));
        assertEq(2,i_.getReadyPlayers().size());
        assertEq(6,i_.getReadyPlayers().getKey(0));
        assertEq(BoolVal.TRUE,i_.getReadyPlayers().getValue(0));
        assertEq(7,i_.getReadyPlayers().getKey(1));
        assertEq(BoolVal.FALSE,i_.getReadyPlayers().getValue(1));
        assertEq(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL, i_.getRulesPresident().getEqualty());
        assertTrue(i_.getRulesPresident().isHasToPlay());
        assertTrue(i_.getRulesPresident().isPossibleReversing());
        assertTrue(i_.getRulesPresident().isSwitchCards());
        assertTrue(i_.getRulesPresident().isLoosingIfFinishByBestCards());
        assertTrue(i_.getRulesPresident().isLooserStartsFirst());
        assertEq(6,i_.getRulesPresident().getNbPlayers());
        assertEq(5,i_.getRulesPresident().getNbStacks());
        assertEq(6,i_.getNbPlayers());
    }
    @Test
    public void indexArrive5() {
        Games g_ = new Games();
        RulesTarot tarot_ = new RulesTarot();
        tarot_.setDiscardAfterCall(false);
        tarot_.setAllowPlayCalledSuit(false);
        tarot_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        tarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        tarot_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        tarot_.setMiseres(new IdList<Miseres>());
        tarot_.setAllowedHandfuls(new IdMap<Handfuls, Integer>());
        tarot_.setAllowedBids(new IdMap<BidTarot, BoolVal>());
        g_.setRulesTarot(tarot_);
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        IndexOfArrivingCards i_ = saveIndexArrive(1, 5, common_, g_, GameEnum.TAROT);
        assertEq(2,i_.getPlacesPlayers().size());
        assertEq(2,i_.getPlacesPlayers().getKey(0));
        assertEq(3,i_.getPlacesPlayers().getValue(0));
        assertEq(4,i_.getPlacesPlayers().getKey(1));
        assertEq(5,i_.getPlacesPlayers().getValue(1));
        assertEq(2,i_.getReadyPlayers().size());
        assertEq(6,i_.getReadyPlayers().getKey(0));
        assertEq(BoolVal.TRUE,i_.getReadyPlayers().getValue(0));
        assertEq(7,i_.getReadyPlayers().getKey(1));
        assertEq(BoolVal.FALSE,i_.getReadyPlayers().getValue(1));
        assertEq(ModeTarot.NORMAL_WITH_ONE_FOR_ONE, i_.getRulesTarot().getMode());
        assertEq(EndDealTarot.ATTACK_WIN, i_.getRulesTarot().getEndDealTarot());
        assertFalse(i_.getRulesTarot().isAllowPlayCalledSuit());
        assertFalse(i_.getRulesTarot().getDiscardAfterCall());
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_CHAR, i_.getRulesTarot().getDealing());
        assertEq(0,i_.getRulesTarot().getAllowedHandfuls().size());
        assertEq(0,i_.getRulesTarot().getAllowedBids().size());
        assertEq(0,i_.getRulesTarot().getMiseres().size());
        assertEq(5,i_.getNbPlayers());
    }
    @Test
    public void indexArrive6() {
        Games g_ = new Games();
        RulesTarot tarot_ = new RulesTarot();
        tarot_.setDiscardAfterCall(true);
        tarot_.setAllowPlayCalledSuit(true);
        tarot_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        tarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        tarot_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        tarot_.setMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        IdMap<Handfuls, Integer> handfuls_ = new IdMap<Handfuls, Integer>();
        handfuls_.addEntry(Handfuls.ONE, 1);
        tarot_.setAllowedHandfuls(handfuls_);
        IdMap<BidTarot, BoolVal> bids_ = new IdMap<BidTarot, BoolVal>();
        bids_.addEntry(BidTarot.FOLD, BoolVal.TRUE);
        bids_.addEntry(BidTarot.SLAM_TAKE, BoolVal.FALSE);
        tarot_.setAllowedBids(bids_);
        g_.setRulesTarot(tarot_);
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        IndexOfArrivingCards i_ = saveIndexArrive(1, 5, common_, g_, GameEnum.TAROT);
        assertEq(2,i_.getPlacesPlayers().size());
        assertEq(2,i_.getPlacesPlayers().getKey(0));
        assertEq(3,i_.getPlacesPlayers().getValue(0));
        assertEq(4,i_.getPlacesPlayers().getKey(1));
        assertEq(5,i_.getPlacesPlayers().getValue(1));
        assertEq(2,i_.getReadyPlayers().size());
        assertEq(6,i_.getReadyPlayers().getKey(0));
        assertEq(BoolVal.TRUE,i_.getReadyPlayers().getValue(0));
        assertEq(7,i_.getReadyPlayers().getKey(1));
        assertEq(BoolVal.FALSE,i_.getReadyPlayers().getValue(1));
        assertEq(ModeTarot.NORMAL_WITH_ONE_FOR_ONE, i_.getRulesTarot().getMode());
        assertEq(EndDealTarot.ATTACK_WIN, i_.getRulesTarot().getEndDealTarot());
        assertTrue(i_.getRulesTarot().isAllowPlayCalledSuit());
        assertTrue(i_.getRulesTarot().getDiscardAfterCall());
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_CHAR, i_.getRulesTarot().getDealing());
        assertEq(1,i_.getRulesTarot().getAllowedHandfuls().size());
        assertEq(Handfuls.ONE,i_.getRulesTarot().getAllowedHandfuls().getKey(0));
        assertEq(1,i_.getRulesTarot().getAllowedHandfuls().getValue(0));
        assertEq(2,i_.getRulesTarot().getAllowedBids().size());
        assertEq(BidTarot.FOLD,i_.getRulesTarot().getAllowedBids().getKey(0));
        assertEq(BoolVal.TRUE,i_.getRulesTarot().getAllowedBids().getValue(0));
        assertEq(BidTarot.SLAM_TAKE,i_.getRulesTarot().getAllowedBids().getKey(1));
        assertEq(BoolVal.FALSE,i_.getRulesTarot().getAllowedBids().getValue(1));
        assertEq(1,i_.getRulesTarot().getMiseres().size());
        assertEq(Miseres.LOW_CARDS,i_.getRulesTarot().getMiseres().get(0));
        assertEq(5,i_.getNbPlayers());
    }
    @Test
    public void chosen1() {
        ChoosenPlace ch_ = saveClientChoosenPlace(1, 2, new IdMap<Integer, Integer>());
        assertEq(1, ch_.getIndex());
        assertEq(2, ch_.getPlace());
        assertEq(0, ch_.getPlacesPlayers().size());
    }
    @Test
    public void chosen2() {
        IdMap<Integer, Integer> id_ = new IdMap<Integer, Integer>();
        id_.addEntry(3,4);
        ChoosenPlace ch_ = saveServerChoosenPlace(1, 2, id_);
        assertEq(1, ch_.getIndex());
        assertEq(2, ch_.getPlace());
        assertEq(1, ch_.getPlacesPlayers().size());
        assertEq(3, ch_.getPlacesPlayers().getKey(0));
        assertEq(4, ch_.getPlacesPlayers().getValue(0));
    }
    @Test
    public void rulesBelote1() {
        RulesBelote belote_ = new RulesBelote();
        belote_.setClassicCountPoints(false);
        belote_.setUnderTrumpFoe(false);
        belote_.setAllowedDeclares(new IdMap<DeclaresBelote, BoolVal>());
        belote_.setAllowedBids(new IdMap<BidBelote, BoolVal>());
        belote_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        RulesBelote i_ = saveClientRulesBelote(belote_);
        assertEq(0,i_.getAllowedDeclares().size());
        assertEq(0,i_.getAllowedBids().size());
        assertFalse(i_.isUnderTrumpFoe());
        assertFalse(i_.isClassicCountPoints());
    }
    @Test
    public void rulesBelote2() {
        RulesBelote belote_ = new RulesBelote();
        belote_.setClassicCountPoints(true);
        belote_.setUnderTrumpFoe(true);
        IdMap<BidBelote, BoolVal> bid_ = new IdMap<BidBelote, BoolVal>();
        bid_.addEntry(BidBelote.FOLD, BoolVal.FALSE);
        bid_.addEntry(BidBelote.SUIT, BoolVal.TRUE);
        belote_.setAllowedBids(bid_);
        IdMap<DeclaresBelote, BoolVal> decl_ = new IdMap<DeclaresBelote, BoolVal>();
        decl_.addEntry(DeclaresBelote.UNDEFINED, BoolVal.TRUE);
        decl_.addEntry(DeclaresBelote.HUNDRED, BoolVal.FALSE);
        belote_.setAllowedDeclares(decl_);
        belote_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        belote_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        belote_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        RulesBelote i_ = saveServerRulesBelote(belote_);
        assertEq(2,i_.getAllowedDeclares().size());
        assertEq(DeclaresBelote.UNDEFINED,i_.getAllowedDeclares().getKey(0));
        assertEq(BoolVal.TRUE,i_.getAllowedDeclares().getValue(0));
        assertEq(DeclaresBelote.HUNDRED,i_.getAllowedDeclares().getKey(1));
        assertEq(BoolVal.FALSE,i_.getAllowedDeclares().getValue(1));
        assertEq(2,i_.getAllowedBids().size());
        assertEq(BidBelote.FOLD,i_.getAllowedBids().getKey(0));
        assertEq(BoolVal.FALSE,i_.getAllowedBids().getValue(0));
        assertEq(BidBelote.SUIT,i_.getAllowedBids().getKey(1));
        assertEq(BoolVal.TRUE,i_.getAllowedBids().getValue(1));
        assertTrue(i_.isUnderTrumpFoe());
        assertTrue(i_.isClassicCountPoints());
        assertEq(MixCardsChoice.EACH_LAUNCHING, i_.getCommon().getMixedCards());
        assertEq(DealingBelote.CLASSIC_1_VS_2_24, i_.getDealing());
        assertEq(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP, i_.getTrumpPartner());
    }
    @Test
    public void rulesPresident1() {
        RulesPresident president_ = new RulesPresident();
        president_.setLoosingIfFinishByBestCards(false);
        president_.setHasToPlay(false);
        president_.setPossibleReversing(false);
        president_.setSwitchCards(false);
        president_.setLooserStartsFirst(false);
        president_.setNbStacks(5);
        president_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        president_.setNbPlayers(6);
        RulesPresident i_ = saveClientRulesPresident(president_);
        assertEq(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL, i_.getEqualty());
        assertFalse(i_.isHasToPlay());
        assertFalse(i_.isPossibleReversing());
        assertFalse(i_.isSwitchCards());
        assertFalse(i_.isLoosingIfFinishByBestCards());
        assertFalse(i_.isLooserStartsFirst());
        assertEq(6,i_.getNbPlayers());
        assertEq(5,i_.getNbStacks());
    }
    @Test
    public void rulesPresident2() {
        RulesPresident president_ = new RulesPresident();
        president_.setLoosingIfFinishByBestCards(true);
        president_.setHasToPlay(true);
        president_.setPossibleReversing(true);
        president_.setSwitchCards(true);
        president_.setLooserStartsFirst(true);
        president_.setNbStacks(5);
        president_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        president_.setNbPlayers(6);
        RulesPresident i_ = saveServerRulesPresident(president_);
        assertEq(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL, i_.getEqualty());
        assertTrue(i_.isHasToPlay());
        assertTrue(i_.isPossibleReversing());
        assertTrue(i_.isSwitchCards());
        assertTrue(i_.isLoosingIfFinishByBestCards());
        assertTrue(i_.isLooserStartsFirst());
        assertEq(6,i_.getNbPlayers());
        assertEq(5,i_.getNbStacks());
    }
    @Test
    public void rulesTarot1() {
        RulesTarot tarot_ = new RulesTarot();
        tarot_.setDiscardAfterCall(false);
        tarot_.setAllowPlayCalledSuit(false);
        tarot_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        tarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        tarot_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        tarot_.setMiseres(new IdList<Miseres>());
        tarot_.setAllowedHandfuls(new IdMap<Handfuls, Integer>());
        tarot_.setAllowedBids(new IdMap<BidTarot, BoolVal>());
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        RulesTarot i_ = saveClientRulesTarot(tarot_);
        assertEq(ModeTarot.NORMAL_WITH_ONE_FOR_ONE, i_.getMode());
        assertEq(EndDealTarot.ATTACK_WIN, i_.getEndDealTarot());
        assertFalse(i_.isAllowPlayCalledSuit());
        assertFalse(i_.getDiscardAfterCall());
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_CHAR, i_.getDealing());
        assertEq(0,i_.getAllowedHandfuls().size());
        assertEq(0,i_.getAllowedBids().size());
        assertEq(0,i_.getMiseres().size());
    }
    @Test
    public void rulesTarot2() {
        RulesTarot tarot_ = new RulesTarot();
        tarot_.setDiscardAfterCall(true);
        tarot_.setAllowPlayCalledSuit(true);
        tarot_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        tarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        tarot_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        tarot_.setMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        IdMap<Handfuls, Integer> handfuls_ = new IdMap<Handfuls, Integer>();
        handfuls_.addEntry(Handfuls.ONE, 1);
        tarot_.setAllowedHandfuls(handfuls_);
        IdMap<BidTarot, BoolVal> bids_ = new IdMap<BidTarot, BoolVal>();
        bids_.addEntry(BidTarot.FOLD, BoolVal.TRUE);
        bids_.addEntry(BidTarot.SLAM_TAKE, BoolVal.FALSE);
        tarot_.setAllowedBids(bids_);
        NetCommon common_ = new NetCommon();
        common_.getPlacesPlayers().addEntry(2,3);
        common_.getPlacesPlayers().addEntry(4,5);
        common_.getReadyPlayers().addEntry(6,BoolVal.TRUE);
        common_.getReadyPlayers().addEntry(7,BoolVal.FALSE);
        RulesTarot i_ = saveServerRulesTarot(tarot_);
        assertEq(ModeTarot.NORMAL_WITH_ONE_FOR_ONE, i_.getMode());
        assertEq(EndDealTarot.ATTACK_WIN, i_.getEndDealTarot());
        assertTrue(i_.isAllowPlayCalledSuit());
        assertTrue(i_.getDiscardAfterCall());
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_CHAR, i_.getDealing());
        assertEq(1,i_.getAllowedHandfuls().size());
        assertEq(Handfuls.ONE,i_.getAllowedHandfuls().getKey(0));
        assertEq(1,i_.getAllowedHandfuls().getValue(0));
        assertEq(2,i_.getAllowedBids().size());
        assertEq(BidTarot.FOLD,i_.getAllowedBids().getKey(0));
        assertEq(BoolVal.TRUE,i_.getAllowedBids().getValue(0));
        assertEq(BidTarot.SLAM_TAKE,i_.getAllowedBids().getKey(1));
        assertEq(BoolVal.FALSE,i_.getAllowedBids().getValue(1));
        assertEq(1,i_.getMiseres().size());
        assertEq(Miseres.LOW_CARDS,i_.getMiseres().get(0));
    }
    @Test
    public void ready1(){
        ReadyCards r_ = saveClientReady(1,false);
        assertEq(1,r_.getIndex());
        assertFalse(r_.getContent().isReady());
    }
    @Test
    public void ready2(){
        ReadyCards r_ = saveClientReady(1,true);
        assertEq(1,r_.getIndex());
        assertTrue(r_.getContent().isReady());
    }
    @Test
    public void ready3(){
        ReadyCards r_ = saveServerReady(1,false);
        assertEq(1,r_.getIndex());
        assertFalse(r_.getContent().isReady());
    }
    @Test
    public void ready4(){
        ReadyCards r_ = saveServerReady(1,true);
        assertEq(1,r_.getIndex());
        assertTrue(r_.getContent().isReady());
    }
    @Test
    public void dealtHandBelote() {
        DealtHandBelote belote_ = new DealtHandBelote();
        belote_.setDeck(HandBelote.create(new CardBelote[]{CardBelote.HEART_1}));
        belote_.setCards(HandBelote.create(new CardBelote[]{CardBelote.HEART_10}));
        belote_.setDealer(2);
        belote_.setAllowedBids(new CustList<BidBeloteSuit>(bidSuit(Suit.HEART,80,BidBelote.OTHER_SUIT)));
        DealtHandBelote i_ = saveDealtHandBelote(belote_);
        assertEq(2,i_.getDealer());
        assertEq(1,i_.getCards().total());
        assertEq(CardBelote.HEART_10,i_.getCards().carte(0));
        assertEq(1,i_.getDeck().total());
        assertEq(CardBelote.HEART_1,i_.getDeck().carte(0));
        assertEq(1,i_.getAllowedBids().size());
        assertEq(80,i_.getAllowedBids().get(0).getPoints());
        assertEq(Suit.HEART,i_.getAllowedBids().get(0).getSuit());
        assertEq(BidBelote.OTHER_SUIT,i_.getAllowedBids().get(0).getBid());
    }
    @Test
    public void dealtHandPresident() {
        DealtHandPresident president_ = new DealtHandPresident();
        president_.setCards(HandPresident.create(new CardPresident[]{CardPresident.HEART_10}));
        president_.setDealer(2);
        IntMap<Playing> st_ = new IntMap<Playing>();
        st_.addEntry(1,Playing.SKIPPED);
        president_.setStatus(st_);
        president_.setMaxCards(3);
        DealtHandPresident i_ = saveDealtHandPresident(president_);
        assertEq(3,i_.getMaxCards());
        assertEq(2,i_.getDealer());
        assertEq(1,i_.getCards().total());
        assertEq(CardPresident.HEART_10,i_.getCards().carte(0));
        assertEq(1,i_.getStatus().size());
        assertEq(1,i_.getStatus().getKey(0));
        assertEq(Playing.SKIPPED,i_.getStatus().getValue(0));
    }
    @Test
    public void dealtHandTarot() {
        DealtHandTarot tarot_ = new DealtHandTarot();
        tarot_.setDog(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        tarot_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        tarot_.setDealer(2);
        IntMap<Playing> st_ = new IntMap<Playing>();
        st_.addEntry(1,Playing.SKIPPED);
        tarot_.setAllowedBids(new IdList<BidTarot>(BidTarot.SLAM_GUARD));
        DealtHandTarot i_ = saveDealtHandTarot(tarot_);
        assertEq(2,i_.getDealer());
        assertEq(1,i_.getCards().total());
        assertEq(CardTarot.HEART_10,i_.getCards().carte(0));
        assertEq(1,i_.getDog().total());
        assertEq(CardTarot.HEART_1,i_.getDog().carte(0));
        assertEq(1,i_.getAllowedBids().size());
        assertEq(BidTarot.SLAM_GUARD,i_.getAllowedBids().get(0));
    }
    @Test
    public void allowBiddingBelote(){
        AllowBiddingBelote a_ = new AllowBiddingBelote();
        a_.setBids(new CustList<BidBeloteSuit>(bidSuit(Suit.HEART,80,BidBelote.OTHER_SUIT)));
        a_.setBid(bidSuit(Suit.SPADE,90,BidBelote.OTHER_SUIT));
        AllowBiddingBelote o_ = saveAllowBiddingBelote(a_);
        assertEq(1,o_.getBids().size());
        assertEq(80,o_.getBids().get(0).getPoints());
        assertEq(Suit.HEART,o_.getBids().get(0).getSuit());
        assertEq(BidBelote.OTHER_SUIT,o_.getBids().get(0).getBid());
        assertEq(90,o_.getBid().getPoints());
        assertEq(Suit.SPADE,o_.getBid().getSuit());
        assertEq(BidBelote.OTHER_SUIT,o_.getBid().getBid());
    }
    @Test
    public void biddingBelote1(){
        BiddingBelote a_ = new BiddingBelote();
        a_.setPlace(2);
        a_.setBidBelote(bidSuit(Suit.SPADE,90,BidBelote.OTHER_SUIT));
        BiddingBelote o_ = saveClientBiddingBelote(a_);
        assertEq(2,o_.getPlace());
        assertEq(90,o_.getBidBelote().getPoints());
        assertEq(Suit.SPADE,o_.getBidBelote().getSuit());
        assertEq(BidBelote.OTHER_SUIT,o_.getBidBelote().getBid());
    }
    @Test
    public void biddingBelote2(){
        BiddingBelote a_ = new BiddingBelote();
        a_.setPlace(2);
        a_.setBidBelote(bidSuit(Suit.SPADE,90,BidBelote.OTHER_SUIT));
        BiddingBelote o_ = saveServerBiddingBelote(a_);
        assertEq(2,o_.getPlace());
        assertEq(90,o_.getBidBelote().getPoints());
        assertEq(Suit.SPADE,o_.getBidBelote().getSuit());
        assertEq(BidBelote.OTHER_SUIT,o_.getBidBelote().getBid());
    }
    @Test
    public void discardPhaseBelote(){
        DiscardPhaseBelote a_ = new DiscardPhaseBelote();
        a_.setDiscard(HandBelote.create(new CardBelote[]{CardBelote.HEART_1}));
        a_.getDiscardPhase().setTakerIndex(6);
        a_.getDiscardPhase().setTaker(7);
        DiscardPhaseBelote o_ = saveDiscardPhaseBelote(a_);
        assertEq(6,o_.getDiscardPhase().getTakerIndex());
        assertEq(7,o_.getDiscardPhase().getTaker());
        assertEq(1,o_.getDiscard().total());
        assertEq(CardBelote.HEART_1,o_.getDiscard().carte(0));
    }
    @Test
    public void discardedCardBelote(){
        DiscardedCardBelote a_ = new DiscardedCardBelote();
        a_.setPlace(2);
        a_.setCard(CardBelote.HEART_1);
        DiscardedCardBelote o_ = saveDiscardedCardBelote(a_);
        assertEq(2,o_.getPlace());
        assertEq(CardBelote.HEART_1,o_.getCard());
    }
    @Test
    public void refreshHandBelote(){
        RefreshHandBelote a_ = new RefreshHandBelote();
        a_.setPlace(2);
        a_.setTakerIndex(3);
        a_.setRefreshedHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_1}));
        RefreshHandBelote o_ = saveRefreshHandBelote(a_);
        assertEq(2,o_.getPlace());
        assertEq(3,o_.getTakerIndex());
        assertEq(1,o_.getRefreshedHand().total());
        assertEq(CardBelote.HEART_1,o_.getRefreshedHand().carte(0));
    }
    @Test
    public void allowDiscarding(){
        AllowDiscarding o_ = saveAllowDiscarding(HandPresident.create(new CardPresident[]{CardPresident.HEART_1}));
        assertEq(1,o_.getReceivedCards().total());
        assertEq(CardPresident.HEART_1,o_.getReceivedCards().carte(0));
    }
    @Test
    public void receivedGivenCards(){
        ReceivedGivenCards o_ = saveReceivedGivenCards(HandPresident.create(new CardPresident[]{CardPresident.HEART_1}),HandPresident.create(new CardPresident[]{CardPresident.HEART_10}),HandPresident.create(new CardPresident[]{CardPresident.HEART_9}));
        assertEq(1,o_.getReceived().total());
        assertEq(CardPresident.HEART_1,o_.getReceived().carte(0));
        assertEq(1,o_.getGiven().total());
        assertEq(CardPresident.HEART_10,o_.getGiven().carte(0));
        assertEq(1,o_.getNewHand().total());
        assertEq(CardPresident.HEART_9,o_.getNewHand().carte(0));
    }
    @Test
    public void discardedCardsPresident(){
        DiscardedCardsPresident o_ = saveDiscardedCardsPresident(2,HandPresident.create(new CardPresident[]{CardPresident.HEART_1}));
        assertEq(2,o_.getPlace());
        assertEq(1,o_.getDiscarded().total());
        assertEq(CardPresident.HEART_1,o_.getDiscarded().carte(0));
    }
    @Test
    public void allowBiddingTarot(){
        AllowBiddingTarot a_ = new AllowBiddingTarot();
        a_.setBids(new IdList<BidTarot>(BidTarot.SLAM_GUARD));
        a_.setMaxBid(BidTarot.SLAM_TAKE);
        AllowBiddingTarot o_ = saveAllowBiddingTarot(a_);
        assertEq(1,o_.getBids().size());
        assertEq(BidTarot.SLAM_GUARD,o_.getBids().get(0));
        assertEq(BidTarot.SLAM_TAKE,o_.getMaxBid());
    }
    @Test
    public void biddingTarot1(){
        BiddingTarot a_ = new BiddingTarot();
        a_.setPlace(2);
        a_.setBid(BidTarot.SLAM_TAKE);
        BiddingTarot o_ = saveClientBiddingTarot(a_);
        assertEq(2,o_.getPlace());
        assertEq(BidTarot.SLAM_TAKE,o_.getBid());
    }
    @Test
    public void biddingTarot2(){
        BiddingTarot a_ = new BiddingTarot();
        a_.setPlace(2);
        a_.setBid(BidTarot.SLAM_TAKE);
        BiddingTarot o_ = saveServerBiddingTarot(a_);
        assertEq(2,o_.getPlace());
        assertEq(BidTarot.SLAM_TAKE,o_.getBid());
    }
    @Test
    public void discardPhaseTarot1(){
        DiscardPhaseTarot a_ = new DiscardPhaseTarot();
        a_.setDiscardCard(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        a_.setCallableCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        a_.setCallAfter(true);
        a_.getDiscardPhase().setTakerIndex(6);
        a_.getDiscardPhase().setTaker(7);
        DiscardPhaseTarot o_ = saveDiscardPhaseTarot(a_);
        assertTrue(o_.isCallAfter());
        assertEq(6,o_.getDiscardPhase().getTakerIndex());
        assertEq(7,o_.getDiscardPhase().getTaker());
        assertEq(1,o_.getDiscardCard().total());
        assertEq(CardTarot.HEART_1,o_.getDiscardCard().carte(0));
        assertEq(1,o_.getCallableCards().total());
        assertEq(CardTarot.HEART_10,o_.getCallableCards().carte(0));
    }
    @Test
    public void discardPhaseTarot2(){
        DiscardPhaseTarot a_ = new DiscardPhaseTarot();
        a_.setDiscardCard(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        a_.setCallableCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        a_.setCallAfter(false);
        a_.getDiscardPhase().setTakerIndex(6);
        a_.getDiscardPhase().setTaker(7);
        DiscardPhaseTarot o_ = saveDiscardPhaseTarot(a_);
        assertFalse(o_.isCallAfter());
        assertEq(6,o_.getDiscardPhase().getTakerIndex());
        assertEq(7,o_.getDiscardPhase().getTaker());
        assertEq(1,o_.getDiscardCard().total());
        assertEq(CardTarot.HEART_1,o_.getDiscardCard().carte(0));
        assertEq(1,o_.getCallableCards().total());
        assertEq(CardTarot.HEART_10,o_.getCallableCards().carte(0));
    }
    @Test
    public void discardedCardTarot(){
        DiscardedCardTarot a_ = new DiscardedCardTarot();
        a_.setPlace(2);
        a_.setCard(CardTarot.HEART_1);
        DiscardedCardTarot o_ = saveDiscardedCardTarot(a_);
        assertEq(2,o_.getPlace());
        assertEq(CardTarot.HEART_1,o_.getCard());
    }
    @Test
    public void callableCards1(){
        CallableCards a_ = new CallableCards();
        a_.setTakerIndex(2);
        a_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        a_.setDiscarding(false);
        CallableCards o_ = saveCallableCards(a_);
        assertFalse(o_.isDiscarding());
        assertEq(2,o_.getTakerIndex());
        assertEq(1,o_.getCards().total());
        assertEq(CardTarot.HEART_1,o_.getCards().carte(0));
    }
    @Test
    public void callableCards2(){
        CallableCards a_ = new CallableCards();
        a_.setTakerIndex(2);
        a_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        a_.setDiscarding(true);
        CallableCards o_ = saveCallableCards(a_);
        assertTrue(o_.isDiscarding());
        assertEq(2,o_.getTakerIndex());
        assertEq(1,o_.getCards().total());
        assertEq(CardTarot.HEART_1,o_.getCards().carte(0));
    }
    @Test
    public void discardCallBefore(){
        CustList<String> o_ = saveDiscardCallBefore(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        assertEq(2,o_.size());
        assertEq(1,o_.get(0).length());
        assertEq(Net.VALIDATE_DISCARD_CALL_BEFORE,o_.get(0).charAt(0));
        HandTarot h_ = Net.importHandTarot(o_.get(1), Net.SEP_1);
        assertEq(1,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
    }
    @Test
    public void discardSimpleCall1(){
        CustList<String> o_ = saveDiscardSimpleCall(CardTarot.WHITE);
        assertEq(1,o_.size());
        assertEq(1,o_.get(0).length());
        assertEq(Net.VALIDATE_DISCARD_SIMPLE_CALL,o_.get(0).charAt(0));
    }
    @Test
    public void discardSimpleCall2(){
        CustList<String> o_ = saveDiscardSimpleCall(CardTarot.HEART_1);
        assertEq(2,o_.size());
        assertEq(1,o_.get(0).length());
        assertEq(Net.VALIDATE_DISCARD_SIMPLE_CALL,o_.get(0).charAt(0));
        HandTarot h_ = Net.importHandTarot(o_.get(1), Net.SEP_1);
        assertEq(1,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
    }
    @Test
    public void discardSlam1(){
        CustList<String> o_ = saveDiscardSlam(CardTarot.WHITE);
        assertEq(1,o_.size());
        assertEq(1,o_.get(0).length());
        assertEq(Net.VALIDATE_DISCARD_SLAM,o_.get(0).charAt(0));
    }
    @Test
    public void discardSlam2(){
        CustList<String> o_ = saveDiscardSlam(CardTarot.HEART_1);
        assertEq(2,o_.size());
        assertEq(1,o_.get(0).length());
        assertEq(Net.VALIDATE_DISCARD_SLAM,o_.get(0).charAt(0));
        HandTarot h_ = Net.importHandTarot(o_.get(1), Net.SEP_1);
        assertEq(1,h_.total());
        assertEq(CardTarot.HEART_1,h_.carte(0));
    }
    @Test
    public void allowPlayingBelote1(){
        AllowPlayingBelote a_ = new AllowPlayingBelote();
        DeclareHandBelote dec_ = new DeclareHandBelote();
        dec_.setDeclare(DeclaresBelote.THIRTY);
        dec_.setPlayer(2);
        dec_.setHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_1}));
        a_.setDeclaration(dec_);
        a_.setBelReb(HandBelote.create(new CardBelote[]{CardBelote.HEART_10}));
        a_.setCards(HandBelote.create(new CardBelote[]{CardBelote.HEART_9}));
        a_.setCurrentBid(bidSuit(Suit.HEART,80,BidBelote.OTHER_SUIT));
        a_.setAllowedBeloteRebelote(false);
        a_.setPossibleBeloteRebelote(false);
        a_.setFirstRoundPlaying(false);
        a_.setTakerIndex(3);
        AllowPlayingBelote o_ = saveAllowPlayingBelote(a_);
        assertFalse(o_.isAllowedBeloteRebelote());
        assertFalse(o_.isPossibleBeloteRebelote());
        assertFalse(o_.isFirstRoundPlaying());
        assertEq(1,o_.getDeclaration().getHand().total());
        assertEq(CardBelote.HEART_1,o_.getDeclaration().getHand().carte(0));
        assertEq(DeclaresBelote.THIRTY,o_.getDeclaration().getDeclare());
        assertEq(2,o_.getDeclaration().getPlayer());
        assertEq(1,o_.getBelReb().total());
        assertEq(CardBelote.HEART_10,o_.getBelReb().carte(0));
        assertEq(1,o_.getCards().total());
        assertEq(CardBelote.HEART_9,o_.getCards().carte(0));
        assertEq(3,o_.getTakerIndex());
        assertEq(80,o_.getCurrentBid().getPoints());
        assertEq(Suit.HEART,o_.getCurrentBid().getSuit());
        assertEq(BidBelote.OTHER_SUIT,o_.getCurrentBid().getBid());
    }
    @Test
    public void allowPlayingBelote2(){
        AllowPlayingBelote a_ = new AllowPlayingBelote();
        DeclareHandBelote dec_ = new DeclareHandBelote();
        dec_.setDeclare(DeclaresBelote.THIRTY);
        dec_.setPlayer(2);
        dec_.setHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_1}));
        a_.setDeclaration(dec_);
        a_.setBelReb(HandBelote.create(new CardBelote[]{CardBelote.HEART_10}));
        a_.setCards(HandBelote.create(new CardBelote[]{CardBelote.HEART_9}));
        a_.setCurrentBid(bidSuit(Suit.HEART,80,BidBelote.OTHER_SUIT));
        a_.setAllowedBeloteRebelote(true);
        a_.setPossibleBeloteRebelote(true);
        a_.setFirstRoundPlaying(true);
        a_.setTakerIndex(3);
        AllowPlayingBelote o_ = saveAllowPlayingBelote(a_);
        assertTrue(o_.isAllowedBeloteRebelote());
        assertTrue(o_.isPossibleBeloteRebelote());
        assertTrue(o_.isFirstRoundPlaying());
        assertEq(1,o_.getDeclaration().getHand().total());
        assertEq(CardBelote.HEART_1,o_.getDeclaration().getHand().carte(0));
        assertEq(DeclaresBelote.THIRTY,o_.getDeclaration().getDeclare());
        assertEq(2,o_.getDeclaration().getPlayer());
        assertEq(1,o_.getBelReb().total());
        assertEq(CardBelote.HEART_10,o_.getBelReb().carte(0));
        assertEq(1,o_.getCards().total());
        assertEq(CardBelote.HEART_9,o_.getCards().carte(0));
        assertEq(3,o_.getTakerIndex());
        assertEq(80,o_.getCurrentBid().getPoints());
        assertEq(Suit.HEART,o_.getCurrentBid().getSuit());
        assertEq(BidBelote.OTHER_SUIT,o_.getCurrentBid().getBid());
    }
    @Test
    public void playingCardBelote1(){
        PlayingCardBelote a_ = new PlayingCardBelote();
        a_.setPlace(2);
        a_.setTakerIndex(3);
        a_.setDeclaring(false);
        a_.setRefreshing(false);
        a_.setDeclaringBeloteRebelote(false);
        a_.setPlayedCard(CardBelote.HEART_1);
        DeclareHandBelote dec_ = new DeclareHandBelote();
        dec_.setDeclare(DeclaresBelote.THIRTY);
        dec_.setPlayer(4);
        dec_.setHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_10}));
        a_.setDeclare(dec_);
        PlayingCardBelote o_ = saveClientPlayingBelote(a_);
        assertFalse(o_.isDeclaring());
        assertFalse(o_.isRefreshing());
        assertFalse(o_.isDeclaringBeloteRebelote());
        assertEq(2,o_.getPlace());
        assertEq(3,o_.getTakerIndex());
        assertEq(CardBelote.HEART_1,o_.getPlayedCard());
        assertEq(1,o_.getDeclare().getHand().total());
        assertEq(CardBelote.HEART_10,o_.getDeclare().getHand().carte(0));
        assertEq(DeclaresBelote.THIRTY,o_.getDeclare().getDeclare());
        assertEq(4,o_.getDeclare().getPlayer());
    }
    @Test
    public void playingCardBelote2(){
        PlayingCardBelote a_ = new PlayingCardBelote();
        a_.setPlace(2);
        a_.setTakerIndex(3);
        a_.setDeclaring(true);
        a_.setRefreshing(true);
        a_.setDeclaringBeloteRebelote(true);
        a_.setPlayedCard(CardBelote.HEART_1);
        DeclareHandBelote dec_ = new DeclareHandBelote();
        dec_.setDeclare(DeclaresBelote.THIRTY);
        dec_.setPlayer(4);
        dec_.setHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_10}));
        a_.setDeclare(dec_);
        PlayingCardBelote o_ = saveServerPlayingBelote(a_);
        assertTrue(o_.isDeclaring());
        assertTrue(o_.isRefreshing());
        assertTrue(o_.isDeclaringBeloteRebelote());
        assertEq(2,o_.getPlace());
        assertEq(3,o_.getTakerIndex());
        assertEq(CardBelote.HEART_1,o_.getPlayedCard());
        assertEq(1,o_.getDeclare().getHand().total());
        assertEq(CardBelote.HEART_10,o_.getDeclare().getHand().carte(0));
        assertEq(DeclaresBelote.THIRTY,o_.getDeclare().getDeclare());
        assertEq(4,o_.getDeclare().getPlayer());
    }
    @Test
    public void allowPlayingPresident1(){
        AllowPlayingPresident a_ = new AllowPlayingPresident();
        a_.setCards(HandPresident.create(new CardPresident[]{CardPresident.HEART_9}));
        a_.setEnabledPass(false);
        a_.setReversed(false);
        a_.setStatus(Playing.SKIPPED);
        AllowPlayingPresident o_ = saveAllowPlayingPresident(a_);
        assertFalse(o_.isEnabledPass());
        assertFalse(o_.isReversed());
        assertEq(1,o_.getCards().total());
        assertEq(CardPresident.HEART_9,o_.getCards().carte(0));
        assertEq(Playing.SKIPPED,o_.getStatus());
    }
    @Test
    public void allowPlayingPresident2(){
        AllowPlayingPresident a_ = new AllowPlayingPresident();
        a_.setCards(HandPresident.create(new CardPresident[]{CardPresident.HEART_9}));
        a_.setEnabledPass(true);
        a_.setReversed(true);
        a_.setStatus(Playing.SKIPPED);
        AllowPlayingPresident o_ = saveAllowPlayingPresident(a_);
        assertTrue(o_.isEnabledPass());
        assertTrue(o_.isReversed());
        assertEq(1,o_.getCards().total());
        assertEq(CardPresident.HEART_9,o_.getCards().carte(0));
        assertEq(Playing.SKIPPED,o_.getStatus());
    }
    @Test
    public void playingCardPresident1(){
        PlayingCardPresident a_ = new PlayingCardPresident();
        a_.setPlace(2);
        a_.setNextPlayer(3);
        a_.setIndex(4);
        a_.setRefreshing(false);
        a_.setReversed(false);
        a_.setPass(false);
        a_.setPlayedCard(CardPresident.HEART_1);
        a_.setPlayedHand(HandPresident.create(new CardPresident[]{CardPresident.HEART_10}));
        IntMap<Playing> st_ = new IntMap<Playing>();
        st_.addEntry(5,Playing.FINISH);
        a_.setStatus(st_);
        PlayingCardPresident o_ = saveClientPlayingPresident(a_);
        assertFalse(o_.isReversed());
        assertFalse(o_.isRefreshing());
        assertFalse(o_.isPass());
        assertEq(2,o_.getPlace());
        assertEq(CardPresident.HEART_1,o_.getPlayedCard());
        assertEq(1,o_.getPlayedHand().total());
        assertEq(CardPresident.HEART_10,o_.getPlayedHand().carte(0));
        assertEq(1,o_.getStatus().size());
        assertEq(5,o_.getStatus().getKey(0));
        assertEq(Playing.FINISH,o_.getStatus().getValue(0));
        assertEq(3,o_.getNextPlayer());
        assertEq(4,o_.getIndex());
    }
    @Test
    public void playingCardPresident2(){
        PlayingCardPresident a_ = new PlayingCardPresident();
        a_.setPlace(2);
        a_.setNextPlayer(3);
        a_.setIndex(4);
        a_.setRefreshing(true);
        a_.setReversed(true);
        a_.setPass(true);
        a_.setPlayedCard(CardPresident.HEART_1);
        a_.setPlayedHand(HandPresident.create(new CardPresident[]{CardPresident.HEART_10}));
        IntMap<Playing> st_ = new IntMap<Playing>();
        st_.addEntry(5,Playing.FINISH);
        a_.setStatus(st_);
        PlayingCardPresident o_ = saveServerPlayingPresident(a_);
        assertTrue(o_.isReversed());
        assertTrue(o_.isRefreshing());
        assertTrue(o_.isPass());
        assertEq(2,o_.getPlace());
        assertEq(CardPresident.HEART_1,o_.getPlayedCard());
        assertEq(1,o_.getPlayedHand().total());
        assertEq(CardPresident.HEART_10,o_.getPlayedHand().carte(0));
        assertEq(1,o_.getStatus().size());
        assertEq(5,o_.getStatus().getKey(0));
        assertEq(Playing.FINISH,o_.getStatus().getValue(0));
        assertEq(3,o_.getNextPlayer());
        assertEq(4,o_.getIndex());
    }
    @Test
    public void allowPlayingTarot1(){
        AllowPlayingTarot a_ = new AllowPlayingTarot();
        a_.setCalledCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        a_.setDiscardedTrumps(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        a_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        a_.setCurrentBid(BidTarot.SLAM_GUARD);
        a_.setFirstRoundPlaying(false);
        a_.setTakerIndex(3);
        AllowPlayingTarot o_ = saveAllowPlayingTarot(a_);
        assertFalse(o_.isFirstRoundPlaying());
        assertEq(1,o_.getCalledCards().total());
        assertEq(CardTarot.HEART_1,o_.getCalledCards().carte(0));
        assertEq(1,o_.getDiscardedTrumps().total());
        assertEq(CardTarot.HEART_10,o_.getDiscardedTrumps().carte(0));
        assertEq(1,o_.getCards().total());
        assertEq(CardTarot.HEART_9,o_.getCards().carte(0));
        assertEq(3,o_.getTakerIndex());
        assertEq(BidTarot.SLAM_GUARD,o_.getCurrentBid());
    }
    @Test
    public void allowPlayingTarot2(){
        AllowPlayingTarot a_ = new AllowPlayingTarot();
        a_.setCalledCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_1}));
        a_.setDiscardedTrumps(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        a_.setCards(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        a_.setCurrentBid(BidTarot.SLAM_GUARD);
        a_.setFirstRoundPlaying(true);
        a_.setTakerIndex(3);
        AllowPlayingTarot o_ = saveAllowPlayingTarot(a_);
        assertTrue(o_.isFirstRoundPlaying());
        assertEq(1,o_.getCalledCards().total());
        assertEq(CardTarot.HEART_1,o_.getCalledCards().carte(0));
        assertEq(1,o_.getDiscardedTrumps().total());
        assertEq(CardTarot.HEART_10,o_.getDiscardedTrumps().carte(0));
        assertEq(1,o_.getCards().total());
        assertEq(CardTarot.HEART_9,o_.getCards().carte(0));
        assertEq(3,o_.getTakerIndex());
        assertEq(BidTarot.SLAM_GUARD,o_.getCurrentBid());
    }
    @Test
    public void playingCardTarot1(){
        PlayingCardTarot a_ = new PlayingCardTarot();
        a_.setPlace(2);
        a_.setTakerIndex(3);
        a_.setCalledCard(false);
        a_.setRefreshing(false);
        a_.setFirstRound(false);
        a_.setPlayedCard(CardTarot.HEART_1);
        a_.setMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        a_.setChoosenHandful(Handfuls.TWO);
        a_.setHandful(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        a_.setExcludedTrumps(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        PlayingCardTarot o_ = saveClientPlayingTarot(a_);
        assertFalse(o_.isCalledCard());
        assertFalse(o_.isRefreshing());
        assertFalse(o_.isFirstRound());
        assertEq(2,o_.getPlace());
        assertEq(3,o_.getTakerIndex());
        assertEq(Handfuls.TWO,o_.getChoosenHandful());
        assertEq(CardTarot.HEART_1,o_.getPlayedCard());
        assertEq(1,o_.getHandful().total());
        assertEq(CardTarot.HEART_10,o_.getHandful().carte(0));
        assertEq(1,o_.getExcludedTrumps().total());
        assertEq(CardTarot.HEART_9,o_.getExcludedTrumps().carte(0));
        assertEq(1,o_.getMiseres().size());
        assertEq(Miseres.LOW_CARDS,o_.getMiseres().get(0));
    }
    @Test
    public void playingCardTarot2(){
        PlayingCardTarot a_ = new PlayingCardTarot();
        a_.setPlace(2);
        a_.setTakerIndex(3);
        a_.setCalledCard(true);
        a_.setRefreshing(true);
        a_.setFirstRound(true);
        a_.setPlayedCard(CardTarot.HEART_1);
        a_.setMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        a_.setChoosenHandful(Handfuls.TWO);
        a_.setHandful(HandTarot.create(new CardTarot[]{CardTarot.HEART_10}));
        a_.setExcludedTrumps(HandTarot.create(new CardTarot[]{CardTarot.HEART_9}));
        PlayingCardTarot o_ = saveServerPlayingTarot(a_);
        assertTrue(o_.isCalledCard());
        assertTrue(o_.isRefreshing());
        assertTrue(o_.isFirstRound());
        assertEq(2,o_.getPlace());
        assertEq(3,o_.getTakerIndex());
        assertEq(Handfuls.TWO,o_.getChoosenHandful());
        assertEq(CardTarot.HEART_1,o_.getPlayedCard());
        assertEq(1,o_.getHandful().total());
        assertEq(CardTarot.HEART_10,o_.getHandful().carte(0));
        assertEq(1,o_.getExcludedTrumps().total());
        assertEq(CardTarot.HEART_9,o_.getExcludedTrumps().carte(0));
        assertEq(1,o_.getMiseres().size());
        assertEq(Miseres.LOW_CARDS,o_.getMiseres().get(0));
    }
    public static Longs saveLongs(Longs _l) {
        return Net.importLongList(parse(Net.exportLongList(_l, Net.SEP_1)),Net.SEP_1);
    }

    public static CustList<Longs> saveLongsList(CustList<Longs> _l) {
        return Net.importLongsList(parse(Net.exportLongsList(_l, Net.SEP_1, Net.SEP_2)),Net.SEP_1, Net.SEP_2);
    }
//    public static Bytes saveBytes(Bytes _l) {
//        return Net.importByteList(parse(Net.exportByteList(_l, Net.SEP_1)),Net.SEP_1);
//    }

    public static CustList<Ints> saveBytesList(CustList<Ints> _l) {
        return Net.importByteLists(parse(Net.exportByteLists(_l, Net.SEP_1, Net.SEP_2)),Net.SEP_1, Net.SEP_2);
    }
    public static Ints saveInts(Ints _l) {
        return Net.importIntList(parse(Net.exportIntList(_l, Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<BoolVal> saveBools(CustList<BoolVal> _l) {
        return Net.importBoolList(parse(Net.exportBoolList(_l)));
    }
    public static CustList<Ints> saveTeams(CustList<Ints> _l) {
        return Net.importTeams(parsePref(Net.exportTeams(_l)));
    }
    public static HandBelote saveHandBelote(HandBelote _l) {
        return Net.importHandBelote(parse(Net.exportHandBelote(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static HandPresident saveHandPresident(HandPresident _l) {
        return Net.importHandPresident(parse(Net.exportHandPresident(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static HandTarot saveHandTarot(HandTarot _l) {
        return Net.importHandTarot(parse(Net.exportHandTarot(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<HandBelote> saveHandBeloteList(CustList<HandBelote> _l) {
        return Net.importHandBeloteList(parse(Net.exportHandBeloteList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static CustList<HandPresident> saveHandPresidentList(CustList<HandPresident> _l) {
        return Net.importHandPresidentList(parse(Net.exportHandPresidentList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static CustList<HandTarot> saveHandTarotList(CustList<HandTarot> _l) {
        return Net.importHandTarotList(parse(Net.exportHandTarotList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static TrickBelote saveTrickBelote(TrickBelote _l) {
        return Net.importTrickBelote(parse(Net.exportTrickBelote(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static TrickPresident saveTrickPresident(TrickPresident _l) {
        return Net.importTrickPresident(parse(Net.exportTrickPresident(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static TrickTarot saveTrickTarot(TrickTarot _l) {
        return Net.importTrickTarot(parse(Net.exportTrickTarot(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<TrickBelote> saveTrickBeloteList(CustList<TrickBelote> _l) {
        return Net.importTrickBeloteList(parse(Net.exportTrickBeloteList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static CustList<TrickPresident> saveTrickPresidentList(CustList<TrickPresident> _l) {
        return Net.importTrickPresidentList(parse(Net.exportTrickPresidentList(_l,Net.SEP_1,Net.SEP_2,Net.SEP_3)),Net.SEP_1,Net.SEP_2,Net.SEP_3);
    }
    public static CustList<TrickTarot> saveTrickTarotList(CustList<TrickTarot> _l) {
        return Net.importTrickTarotList(parse(Net.exportTrickTarotList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static CustList<BidBeloteSuit> saveBidBeloteSuitList(CustList<BidBeloteSuit> _l) {
        return Net.importBidBeloteSuitList(parse(Net.exportBidBeloteSuitList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static IntMap<Playing> savePlayingMap(IntMap<Playing> _l) {
        return Net.importPlayingMap(parse(Net.exportPlayingMap(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static CustList<BidTarot> saveBidTarotList(CustList<BidTarot> _l) {
        return Net.importBidTarotList(parse(Net.exportBidTarotList(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<Handfuls> saveHandfuls(CustList<Handfuls> _l) {
        return Net.importHandfuls(parse(Net.exportHandfuls(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<Miseres> saveMiseres(CustList<Miseres> _l) {
        return Net.importMiseres(parse(Net.exportMiseres(_l,Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<IdList<Handfuls>> saveHandfulsList(CustList<IdList<Handfuls>> _l) {
        return Net.importHandfulsList(parse(Net.exportHandfulsList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static CustList<IdList<Miseres>> saveMiseresList(CustList<IdList<Miseres>> _l) {
        return Net.importMiseresList(parse(Net.exportMiseresList(_l,Net.SEP_1,Net.SEP_2)),Net.SEP_1,Net.SEP_2);
    }
    public static ResultsBelote saveResultsBelote(ResultsBelote _l) {
        return Net.importGameBelote(parseParts(Net.exportGameBelote(_l)));
    }
    public static ResultsPresident saveResultsPresident(ResultsPresident _l) {
        return Net.importGamePresident(parseParts(Net.exportGamePresident(_l)));
    }
    public static ResultsTarot saveResultsTarot(ResultsTarot _l) {
        return Net.importGameTarot(parseParts(Net.exportGameTarot(_l)));
    }
    public static TricksHandsBelote saveTricksHandsBelote(TricksHandsBelote _l) {
        return Net.importTricksHandsBelote(parseParts(Net.exportTricksHandsBelote(_l)));
    }
    public static TricksHandsPresident saveTricksHandsPresident(TricksHandsPresident _l) {
        return Net.importTricksHandsPresident(parseParts(Net.exportTricksHandsPresident(_l)));
    }
    public static TricksHandsTarot saveTricksHandsTarot(TricksHandsTarot _l) {
        return Net.importTricksHandsTarot(parseParts(Net.exportTricksHandsTarot(_l)));
    }
    public static IndexOfArrivingCards saveIndexArrive(int _index, int _nbPlayers, NetCommon _common, Games _instance, GameEnum _choice) {
        return Net.importIndexArrive(parseParts(Net.exportIndexArrive(_index, _nbPlayers, _common, _instance, _choice)));
    }
    public static ChoosenPlace saveClientChoosenPlace(int _index, int _place, AbsMap<Integer,Integer> _map) {
        return Net.importChosenPlace(parseParts(Net.exportClientChosenPlace(_index, _place, _map)));
    }
    public static ChoosenPlace saveServerChoosenPlace(int _index, int _place, AbsMap<Integer,Integer> _map) {
        return Net.importChosenPlace(parseParts(Net.exportServerChosenPlace(_index, _place, _map)));
    }
    public static RulesBelote saveClientRulesBelote(RulesBelote _rules) {
        return Net.importRulesBelote(parseParts(Net.exportClientRulesBelote(_rules).toString()),0);
    }
    public static RulesBelote saveServerRulesBelote(RulesBelote _rules) {
        return Net.importRulesBelote(parseParts(Net.exportServerRulesBelote(_rules).toString()),0);
    }
    public static RulesPresident saveClientRulesPresident(RulesPresident _rules) {
        return Net.importRulesPresident(parseParts(Net.exportClientRulesPresident(_rules).toString()),0);
    }
    public static RulesPresident saveServerRulesPresident(RulesPresident _rules) {
        return Net.importRulesPresident(parseParts(Net.exportServerRulesPresident(_rules).toString()),0);
    }
    public static RulesTarot saveClientRulesTarot(RulesTarot _rules) {
        return Net.importRulesTarot(parseParts(Net.exportClientRulesTarot(_rules).toString()),0);
    }
    public static RulesTarot saveServerRulesTarot(RulesTarot _rules) {
        return Net.importRulesTarot(parseParts(Net.exportServerRulesTarot(_rules).toString()),0);
    }
    public static ReadyCards saveClientReady(int _index, boolean _value) {
        return Net.importReady(parseParts(Net.exportClientReady(_index,_value)));
    }
    public static ReadyCards saveServerReady(int _index, boolean _value) {
        return Net.importReady(parseParts(Net.exportServerReady(_index,_value)));
    }
    public static DealtHandBelote saveDealtHandBelote(DealtHandBelote _rules) {
        return Net.importDealtHandBelote(parseParts(Net.exportDealtHandBelote(_rules)));
    }
    public static DealtHandPresident saveDealtHandPresident(DealtHandPresident _rules) {
        return Net.importDealtHandPresident(parseParts(Net.exportDealtHandPresident(_rules)));
    }
    public static DealtHandTarot saveDealtHandTarot(DealtHandTarot _rules) {
        return Net.importDealtHandTarot(parseParts(Net.exportDealtHandTarot(_rules)));
    }
    public static AllowBiddingBelote saveAllowBiddingBelote(AllowBiddingBelote _rules) {
        return Net.importAllowBiddingBelote(parseParts(Net.exportAllowBiddingBelote(_rules)));
    }
    public static BiddingBelote saveClientBiddingBelote(BiddingBelote _rules) {
        return Net.importBiddingBelote(parseParts(Net.exportClientBiddingBelote(_rules)));
    }
    public static BiddingBelote saveServerBiddingBelote(BiddingBelote _rules) {
        return Net.importBiddingBelote(parseParts(Net.exportServerBiddingBelote(_rules)));
    }
    public static DiscardPhaseBelote saveDiscardPhaseBelote(DiscardPhaseBelote _rules) {
        return Net.importDiscardPhaseBelote(parseParts(Net.exportDiscardPhaseBelote(_rules)));
    }
    public static DiscardedCardBelote saveDiscardedCardBelote(DiscardedCardBelote _rules) {
        return Net.importDiscardedCardBelote(parseParts(Net.exportDiscardedCardBelote(_rules)));
    }
    public static RefreshHandBelote saveRefreshHandBelote(RefreshHandBelote _rules) {
        return Net.importRefreshHandBelote(parseParts(Net.exportRefreshHandBelote(_rules)));
    }
    public static AllowDiscarding saveAllowDiscarding(HandPresident _rules) {
        return Net.importAllowDiscarding(parseParts(Net.exportAllowDiscarding(_rules)));
    }
    public static ReceivedGivenCards saveReceivedGivenCards(HandPresident _received, HandPresident _given, HandPresident _newHand) {
        return Net.importReceivedGivenCards(parseParts(Net.exportReceivedGivenCards(_received, _given, _newHand)));
    }
    public static DiscardedCardsPresident saveDiscardedCardsPresident(int _place,HandPresident _dis) {
        return Net.importDiscardedCardsPresident(parseParts(Net.exportDiscardedCardsPresident(_place, _dis)));
    }
    public static AllowBiddingTarot saveAllowBiddingTarot(AllowBiddingTarot _rules) {
        return Net.importAllowBiddingTarot(parseParts(Net.exportAllowBiddingTarot(_rules)));
    }
    public static BiddingTarot saveClientBiddingTarot(BiddingTarot _rules) {
        return Net.importBiddingTarot(parseParts(Net.exportClientBiddingTarot(_rules)));
    }
    public static BiddingTarot saveServerBiddingTarot(BiddingTarot _rules) {
        return Net.importBiddingTarot(parseParts(Net.exportServerBiddingTarot(_rules)));
    }
    public static DiscardPhaseTarot saveDiscardPhaseTarot(DiscardPhaseTarot _rules) {
        return Net.importDiscardPhaseTarot(parseParts(Net.exportDiscardPhaseTarot(_rules)));
    }
    public static DiscardedCardTarot saveDiscardedCardTarot(DiscardedCardTarot _rules) {
        return Net.importDiscardedCardTarot(parseParts(Net.exportDiscardedCardTarot(_rules)));
    }
    public static CallableCards saveCallableCards(CallableCards _rules) {
        return Net.importCallableCards(parseParts(Net.exportCallableCards(_rules)));
    }
    public static CustList<String> saveDiscardCallBefore(HandTarot _call) {
        return parseParts(Net.exportDiscardCallBefore(_call));
    }
    public static CustList<String> saveDiscardSimpleCall(CardTarot _call) {
        return parseParts(Net.exportDiscardSimpleCall(_call));
    }
    public static CustList<String> saveDiscardSlam(CardTarot _call) {
        return parseParts(Net.exportDiscardSlam(_call));
    }
    public static AllowPlayingBelote saveAllowPlayingBelote(AllowPlayingBelote _rules) {
        return Net.importAllowPlayingBelote(parseParts(Net.exportAllowPlayingBelote(_rules)));
    }
    public static PlayingCardBelote saveClientPlayingBelote(PlayingCardBelote _rules) {
        return Net.importPlayingBelote(parseParts(Net.exportClientPlayingBelote(_rules)));
    }
    public static PlayingCardBelote saveServerPlayingBelote(PlayingCardBelote _rules) {
        return Net.importPlayingBelote(parseParts(Net.exportServerPlayingBelote(_rules)));
    }
    public static AllowPlayingPresident saveAllowPlayingPresident(AllowPlayingPresident _rules) {
        return Net.importAllowPlayingPresident(parseParts(Net.exportAllowPlayingPresident(_rules)));
    }
    public static PlayingCardPresident saveClientPlayingPresident(PlayingCardPresident _rules) {
        return Net.importPlayingPresident(parseParts(Net.exportClientPlayingPresident(_rules)));
    }
    public static PlayingCardPresident saveServerPlayingPresident(PlayingCardPresident _rules) {
        return Net.importPlayingPresident(parseParts(Net.exportServerPlayingPresident(_rules)));
    }
    public static AllowPlayingTarot saveAllowPlayingTarot(AllowPlayingTarot _rules) {
        return Net.importAllowPlayingTarot(parseParts(Net.exportAllowPlayingTarot(_rules)));
    }
    public static PlayingCardTarot saveClientPlayingTarot(PlayingCardTarot _rules) {
        return Net.importPlayingTarot(parseParts(Net.exportClientPlayingTarot(_rules)));
    }
    public static PlayingCardTarot saveServerPlayingTarot(PlayingCardTarot _rules) {
        return Net.importPlayingTarot(parseParts(Net.exportServerPlayingTarot(_rules)));
    }
    private static String parse(String _in) {
        String i_ = Net.SEP_0 + _in;
        return parsePref(i_);
    }

    private static String parsePref(String _in) {
        FirstSeparatorFind cs_ = new FirstSeparatorFind(_in, Net.SEP_0);
        return StringUtil.partsStrQuick(splitter(), _in, index(cs_), _in.length(), 0, cs_).get(0);
    }

    private static CustList<String> parseParts(String _in) {
        FirstSeparatorFind cs_ = new FirstSeparatorFind(_in, Net.SEP_0);
        return StringUtil.partsStrQuick(splitter(), _in, index(cs_), _in.length(), 0, cs_);
    }

    private static int index(FirstSeparatorFind _cs) {
        assertTrue(_cs.isFound());
        return _cs.getIndex();
    }

    private static CustList<IntSplitPartsFields> splitter() {
        CustList<IntSplitPartsFields> window_ = new CustList<IntSplitPartsFields>();
        window_.add(new DefSplitPartsFields());
        return window_;
    }

    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
}
