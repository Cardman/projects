package cards.belote;

import cards.belote.enumerations.BeloteCardsExporterUtil;
import cards.belote.enumerations.BidBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class BeloteAddonTest extends EquallableBeloteUtil {
    @Test
    public void test() {
        DealBelote deal_ = new DealBelote();
        deal_.setDeal(new CustList<HandBelote>());
        assertEq(0,deal_.getDeal().size());
    }
    @Test
    public void str1() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        assertEq("heart",BidBeloteSuit.toString(b_, suit(b_, "heart"), bid(b_, "suit")));
    }
    @Test
    public void str2() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq("diamond",BidBeloteSuit.toString(b_, suit(b_, "diamond"), bid(b_, "other suit")));
    }
    @Test
    public void str3() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        assertEq("no trump",BidBeloteSuit.toString(b_, suit(b_, "undefined"), bid(b_, "no trump")));
    }
    @Test
    public void str4() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        assertEq("all trump",BidBeloteSuit.toString(b_, suit(b_, "undefined"), bid(b_, "all trump")));
    }
    @Test
    public void str5() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(100);
        assertEq("heart 100",BidBeloteSuit.toString(b_, suit(b_, "heart"), bid(b_, "suit")));
    }
    @Test
    public void str6() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(100);
        assertEq("diamond 100",BidBeloteSuit.toString(b_, suit(b_, "diamond"), bid(b_, "other suit")));
    }
    @Test
    public void str7() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(100);
        assertEq("no trump 100",BidBeloteSuit.toString(b_, suit(b_, "undefined"), bid(b_, "no trump")));
    }
    @Test
    public void str8() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(100);
        assertEq("all trump 100",BidBeloteSuit.toString(b_, suit(b_, "undefined"), bid(b_, "all trump")));
    }

    private StringMap<String> bid(BidBeloteSuit _b, String _v) {
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry(BeloteCardsExporterUtil.fromBidBelote(_b.getBid()), _v);
        return s_;
    }

    private StringMap<String> suit(BidBeloteSuit _b, String _v) {
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry(_b.getSuit().getSuitSt(), _v);
        return s_;
    }
}
