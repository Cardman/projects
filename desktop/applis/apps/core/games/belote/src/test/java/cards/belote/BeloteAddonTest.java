package cards.belote;

import cards.belote.enumerations.BeloteResoucesAccess;
import cards.belote.enumerations.BidBelote;
import cards.consts.CoreResourcesAccess;
import cards.consts.Suit;
import code.util.CustList;
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
        assertEq("heart",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":heart", BeloteResoucesAccess.key(b_.getBid())+":suit"));
    }
    @Test
    public void str2() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq("diamond",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":diamond", BeloteResoucesAccess.key(b_.getBid())+":other suit"));
    }
    @Test
    public void str3() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        assertEq("no trump",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":undefined", BeloteResoucesAccess.key(b_.getBid())+":no trump"));
    }
    @Test
    public void str4() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        assertEq("all trump",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":undefined", BeloteResoucesAccess.key(b_.getBid())+":all trump"));
    }
    @Test
    public void str5() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(100);
        assertEq("heart 100",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":heart", BeloteResoucesAccess.key(b_.getBid())+":suit"));
    }
    @Test
    public void str6() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(100);
        assertEq("diamond 100",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":diamond", BeloteResoucesAccess.key(b_.getBid())+":other suit"));
    }
    @Test
    public void str7() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(100);
        assertEq("no trump 100",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":undefined", BeloteResoucesAccess.key(b_.getBid())+":no trump"));
    }
    @Test
    public void str8() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(100);
        assertEq("all trump 100",BidBeloteSuit.toString(b_, CoreResourcesAccess.key(b_.getSuit())+":undefined", BeloteResoucesAccess.key(b_.getBid())+":all trump"));
    }
}
