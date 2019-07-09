package cards.president;

import cards.consts.MixCardsChoice;
import org.junit.Test;

import static cards.president.EquallablePresidentUtil.assertEq;

public final class DealPresidentTest {
    @Test
    public void initDonne1Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.NEVER);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_);
        donne_.initDonne(rules_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.main((byte)0).total());
        assertEq(13,donne_.main((byte)1).total());
        assertEq(13,donne_.main((byte)2).total());
        assertEq(13,donne_.main((byte)3).total());
    }
    @Test
    public void initDonne2Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.EACH_DEAL);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_);
        donne_.initDonne(rules_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.main((byte)0).total());
        assertEq(13,donne_.main((byte)1).total());
        assertEq(13,donne_.main((byte)2).total());
        assertEq(13,donne_.main((byte)3).total());
    }
    @Test
    public void initDonne3Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_);
        donne_.initDonne(rules_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.main((byte)0).total());
        assertEq(13,donne_.main((byte)1).total());
        assertEq(13,donne_.main((byte)2).total());
        assertEq(13,donne_.main((byte)3).total());
    }
    @Test
    public void initDonne4Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        DealPresident donne_=new DealPresident(1,HandPresident.stack(1));
        donne_.setRandomDealer(rules_);
        donne_.initDonne(rules_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.main((byte)0).total());
        assertEq(13,donne_.main((byte)1).total());
        assertEq(13,donne_.main((byte)2).total());
        assertEq(13,donne_.main((byte)3).total());
    }
    @Test
    public void initDonne5Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.ONCE_ONLY);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_);
        donne_.initDonne(rules_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.main((byte)0).total());
        assertEq(13,donne_.main((byte)1).total());
        assertEq(13,donne_.main((byte)2).total());
        assertEq(13,donne_.main((byte)3).total());
    }
    @Test
    public void initDonne6Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.ONCE_ONLY);
        DealPresident donne_=new DealPresident(1,HandPresident.stack(1));
        donne_.setRandomDealer(rules_);
        donne_.initDonne(rules_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.main((byte)0).total());
        assertEq(13,donne_.main((byte)1).total());
        assertEq(13,donne_.main((byte)2).total());
        assertEq(13,donne_.main((byte)3).total());
    }
    @Test
    public void initDonne7Test() {
        RulesPresident rules_ = new RulesPresident(5);
        rules_.setMixedCards(MixCardsChoice.NEVER);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setDealer((byte) 4);
        donne_.initDonne(rules_);
        assertEq(5,donne_.nombreDeMains());
        assertEq(11,donne_.main((byte)0).total());
        assertEq(11,donne_.main((byte)1).total());
        assertEq(10,donne_.main((byte)2).total());
        assertEq(10,donne_.main((byte)3).total());
        assertEq(10,donne_.main((byte)4).total());
    }
}
