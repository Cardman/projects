package code.network;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.GameType;
import cards.consts.Suit;
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

    @Test
    public void bytes1() {
        assertTrue(saveBytes(new Bytes()).isEmpty());
    }

    @Test
    public void bytes2() {
        Bytes res_ = saveBytes(Bytes.newList((byte)1,(byte) 2,(byte) -3));
        assertEq(3, res_.size());
        assertEq(1, res_.get(0));
        assertEq(2, res_.get(1));
        assertEq(-3, res_.get(2));
    }

    @Test
    public void bytes3() {
        assertTrue(saveBytesList(new CustList<Bytes>()).isEmpty());
    }

    @Test
    public void bytes4() {
        CustList<Bytes> in_ = new CustList<Bytes>();
        in_.add(Bytes.newList((byte)1,(byte) 2,(byte) -3));
        in_.add(Bytes.newList((byte)-1,(byte) 2,(byte) 5));
        CustList<Bytes> res_ = saveBytesList(in_);
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
        CustList<Bytes> in_ = new CustList<Bytes>();
        in_.add(Bytes.newList((byte)1));
        in_.add(Bytes.newList((byte)-1,(byte) 2));
        CustList<Bytes> res_ = saveBytesList(in_);
        assertEq(2, res_.size());
        assertEq(1, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
    }

    @Test
    public void shorts1() {
        assertTrue(saveShorts(new Shorts()).isEmpty());
    }

    @Test
    public void shorts2() {
        Shorts res_ = saveShorts(Shorts.newList((short) 1,(short) 2,(short) -3));
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
        assertTrue(saveTeams(new CustList<Bytes>()).isEmpty());
    }

    @Test
    public void teams2() {
        CustList<Bytes> bv_ = new CustList<Bytes>();
        bv_.add(Bytes.newList((byte)1));
        bv_.add(Bytes.newList((byte)-1,(byte) 2));
        bv_.add(Bytes.newList((byte)4,(byte) -2, (byte) 0, (byte) 6));
        CustList<Bytes> res_ = saveTeams(bv_);
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
        CustList<Bytes> bv_ = new CustList<Bytes>();
        bv_.add(Bytes.newList((byte)1));
        bv_.add(Bytes.newList());
        bv_.add(Bytes.newList());
        bv_.add(Bytes.newList((byte)-1,(byte) 2));
        CustList<Bytes> res_ = saveTeams(bv_);
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
        assertTrue(savePlayingMap(new ByteMap<Playing>()).isEmpty());
    }
    @Test
    public void playingMap2() {
        ByteMap<Playing> b_ = new ByteMap<Playing>();
        b_.addEntry((byte)2,Playing.SKIPPED);
        b_.addEntry((byte)4,Playing.PASS);
        ByteMap<Playing> s_ = savePlayingMap(b_);
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
        g_.setScores(Shorts.newList());
        g_.setDeclaresBeloteRebelote(new CustList<HandBelote>());
        g_.setDeclares(new CustList<DeclareHandBelote>());
        g_.setWonLastTrick(new CustList<BoolVal>());
        g_.setBids(new CustList<BidBeloteSuit>());
        g_.setType(GameType.RANDOM);
        DealBelote deal_ = new DealBelote();
        g_.setDeal(deal_);
        deal_.setDealer((byte) 3);
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
        g_.setScores(Shorts.newList((short) 3));
        g_.setDeclaresBeloteRebelote(new CustList<HandBelote>(new HandBelote()));
        CustList<DeclareHandBelote> dec_ = new CustList<DeclareHandBelote>();
        DeclareHandBelote dece_ = new DeclareHandBelote();
        dece_.setDeclare(DeclaresBelote.THIRTY);
        dece_.setHand(HandBelote.create(new CardBelote[]{CardBelote.HEART_8}));
        dece_.setPlayer((byte) 6);
        dec_.add(dece_);
        g_.setDeclares(dec_);
        g_.setWonLastTrick(new CustList<BoolVal>());
        g_.setBids(new CustList<BidBeloteSuit>());
        g_.setType(GameType.RANDOM);
        DealBelote deal_ = new DealBelote();
        g_.setDeal(deal_);
        deal_.setDealer((byte) 4);
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
    public void resultsPresident1() {
        ResultsPresident r_ = new ResultsPresident();
        r_.getRes().setScores(new CustList<Longs>());
        GamePresident g_ = new GamePresident();
        r_.setGame(g_);
        g_.setNumber(2);
        g_.setTricks(new CustList<TrickPresident>());
        g_.setProgressingTrick(new TrickPresident());
        g_.setScores(Shorts.newList());
        g_.setRanks(Bytes.newList());
        g_.setSwitchedCards(new CustList<HandPresident>());
        g_.setType(GameType.RANDOM);
        DealPresident deal_ = new DealPresident();
        g_.setDeal(deal_);
        deal_.setDealer((byte) 3);
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
        g_.setScores(Shorts.newList((short) 3));
        g_.setRanks(Bytes.newList((byte) 7));
        g_.setSwitchedCards(new CustList<HandPresident>(HandPresident.create(new CardPresident[]{CardPresident.HEART_8})));
        g_.setType(GameType.RANDOM);
        DealPresident deal_ = new DealPresident();
        g_.setDeal(deal_);
        deal_.setDealer((byte) 4);
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
    public void resultsTarot1() {
        ResultsTarot r_ = new ResultsTarot();
        r_.getRes().setScores(new CustList<Longs>());
        GameTarot g_ = new GameTarot();
        r_.setGame(g_);
        g_.setNumber(2);
        g_.setTricks(new CustList<TrickTarot>());
        g_.setProgressingTrick(new TrickTarot());
        g_.setScores(Shorts.newList());
        g_.setDeclaresMiseres(new CustList<IdList<Miseres>>());
        g_.setDeclaresHandfuls(new CustList<IdList<Handfuls>>());
        g_.setHandfuls(new CustList<HandTarot>());
        g_.setType(GameType.RANDOM);
        g_.setSmallBound(new CustList<BoolVal>());
        g_.setCalledCards(new HandTarot());
        g_.setBids(new IdList<BidTarot>());
        DealTarot deal_ = new DealTarot();
        g_.setDeal(deal_);
        deal_.setDealer((byte) 3);
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
        g_.setScores(Shorts.newList((short) 3));
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
        deal_.setDealer((byte) 4);
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
    public static Longs saveLongs(Longs _l) {
        return Net.importLongList(parse(Net.exportLongList(_l, Net.SEP_1)),Net.SEP_1);
    }

    public static CustList<Longs> saveLongsList(CustList<Longs> _l) {
        return Net.importLongsList(parse(Net.exportLongsList(_l, Net.SEP_1, Net.SEP_2)),Net.SEP_1, Net.SEP_2);
    }
    public static Bytes saveBytes(Bytes _l) {
        return Net.importByteList(parse(Net.exportByteList(_l, Net.SEP_1)),Net.SEP_1);
    }

    public static CustList<Bytes> saveBytesList(CustList<Bytes> _l) {
        return Net.importByteLists(parse(Net.exportByteLists(_l, Net.SEP_1, Net.SEP_2)),Net.SEP_1, Net.SEP_2);
    }
    public static Shorts saveShorts(Shorts _l) {
        return Net.importShortList(parse(Net.exportShortList(_l, Net.SEP_1)),Net.SEP_1);
    }
    public static CustList<BoolVal> saveBools(CustList<BoolVal> _l) {
        return Net.importBoolList(parse(Net.exportBoolList(_l)));
    }
    public static CustList<Bytes> saveTeams(CustList<Bytes> _l) {
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
    public static ByteMap<Playing> savePlayingMap(ByteMap<Playing> _l) {
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
