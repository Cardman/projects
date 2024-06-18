package code.network;

import cards.belote.*;
import cards.belote.enumerations.*;
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
}
