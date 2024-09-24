package cards.sml;

import cards.solitaire.*;
import cards.solitaire.sml.*;
import code.sml.*;
import code.sml.core.*;
import code.util.*;
import org.junit.Test;

public final class DocumentSolitaireTest extends EquallableCardsSerialUtil {
    @Test
    public void s1() {
        DealClassic g_ = new DealClassic();
        CustList<HandSolitaire> hs_ = new CustList<HandSolitaire>();
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.HEART_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.SPADE_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.DIAMOND_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.CLUB_1}));
        g_.setHandsBegin(hs_);
        CustList<ActionSolitaire> as_ = new CustList<ActionSolitaire>();
        ActionSolitaire a_ = new ActionSolitaire();
        a_.setFromIndex(4);
        a_.setCardIndex(5);
        a_.setToIndex(6);
        as_.add(a_);
        g_.setActions(as_);
        AbsDealSolitaire o_ = saveGameSolitaire(g_);
        assertEq(4,o_.getHandsBegin().size());
        assertEq(1,o_.getHandsBegin().get(0).total());
        assertEq(CardSolitaire.HEART_1,o_.getHandsBegin().get(0).carte(0));
        assertEq(1,o_.getHandsBegin().get(1).total());
        assertEq(CardSolitaire.SPADE_1,o_.getHandsBegin().get(1).carte(0));
        assertEq(1,o_.getHandsBegin().get(2).total());
        assertEq(CardSolitaire.DIAMOND_1,o_.getHandsBegin().get(2).carte(0));
        assertEq(1,o_.getHandsBegin().get(3).total());
        assertEq(CardSolitaire.CLUB_1,o_.getHandsBegin().get(3).carte(0));
        assertEq(1,o_.getActions().size());
        assertEq(4,o_.getActions().get(0).getFromIndex());
        assertEq(5,o_.getActions().get(0).getCardIndex());
        assertEq(6,o_.getActions().get(0).getToIndex());
    }
    @Test
    public void s2() {
        DealFreeCell g_ = new DealFreeCell();
        CustList<HandSolitaire> hs_ = new CustList<HandSolitaire>();
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.HEART_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.SPADE_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.DIAMOND_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.CLUB_1}));
        g_.setHandsBegin(hs_);
        CustList<ActionSolitaire> as_ = new CustList<ActionSolitaire>();
        ActionSolitaire a_ = new ActionSolitaire();
        a_.setFromIndex(4);
        a_.setCardIndex(5);
        a_.setToIndex(6);
        as_.add(a_);
        g_.setActions(as_);
        AbsDealSolitaire o_ = saveGameSolitaire(g_);
        assertEq(4,o_.getHandsBegin().size());
        assertEq(1,o_.getHandsBegin().get(0).total());
        assertEq(CardSolitaire.HEART_1,o_.getHandsBegin().get(0).carte(0));
        assertEq(1,o_.getHandsBegin().get(1).total());
        assertEq(CardSolitaire.SPADE_1,o_.getHandsBegin().get(1).carte(0));
        assertEq(1,o_.getHandsBegin().get(2).total());
        assertEq(CardSolitaire.DIAMOND_1,o_.getHandsBegin().get(2).carte(0));
        assertEq(1,o_.getHandsBegin().get(3).total());
        assertEq(CardSolitaire.CLUB_1,o_.getHandsBegin().get(3).carte(0));
        assertEq(1,o_.getActions().size());
        assertEq(4,o_.getActions().get(0).getFromIndex());
        assertEq(5,o_.getActions().get(0).getCardIndex());
        assertEq(6,o_.getActions().get(0).getToIndex());
    }
    @Test
    public void s3() {
        DealSpider g_ = new DealSpider();
        CustList<HandSolitaire> hs_ = new CustList<HandSolitaire>();
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.HEART_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.SPADE_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.DIAMOND_1}));
        hs_.add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.CLUB_1}));
        g_.setHandsBegin(hs_);
        CustList<ActionSolitaire> as_ = new CustList<ActionSolitaire>();
        ActionSolitaire a_ = new ActionSolitaire();
        a_.setFromIndex(4);
        a_.setCardIndex(5);
        a_.setToIndex(6);
        as_.add(a_);
        g_.setActions(as_);
        AbsDealSolitaire o_ = saveGameSolitaire(g_);
        assertEq(4,o_.getHandsBegin().size());
        assertEq(1,o_.getHandsBegin().get(0).total());
        assertEq(CardSolitaire.HEART_1,o_.getHandsBegin().get(0).carte(0));
        assertEq(1,o_.getHandsBegin().get(1).total());
        assertEq(CardSolitaire.SPADE_1,o_.getHandsBegin().get(1).carte(0));
        assertEq(1,o_.getHandsBegin().get(2).total());
        assertEq(CardSolitaire.DIAMOND_1,o_.getHandsBegin().get(2).carte(0));
        assertEq(1,o_.getHandsBegin().get(3).total());
        assertEq(CardSolitaire.CLUB_1,o_.getHandsBegin().get(3).carte(0));
        assertEq(1,o_.getActions().size());
        assertEq(4,o_.getActions().get(0).getFromIndex());
        assertEq(5,o_.getActions().get(0).getCardIndex());
        assertEq(6,o_.getActions().get(0).getToIndex());
    }
    @Test
    public void s4() {
        FullDocument d_ = DocumentBuilder.newXmlDocument(4);
        d_.appendChild(d_.createElement(DocumentWriterCoreUtil.ANON_TAG));
        assertNull(DocumentReaderSolitaireUtil.getGameSolitaire(d_));
    }
}
