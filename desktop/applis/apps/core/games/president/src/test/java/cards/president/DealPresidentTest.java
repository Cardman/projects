package cards.president;

import cards.consts.MixCardsChoice;
import code.maths.montecarlo.DefaultGenerator;
import org.junit.Test;

public final class DealPresidentTest extends EquallablePresidentUtil {
    @Test
    public void initDonne1Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.NEVER);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_, new DefaultGenerator());
        initDonneLoc(rules_, donne_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne2Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.EACH_DEAL);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_, new DefaultGenerator());
        initDonneLoc(rules_, donne_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }

    private static void initDonneLoc(RulesPresident _rules, DealPresident _donne) {
        _donne.initDonne(_rules, new DefaultGenerator());
    }

    @Test
    public void initDonne3Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_, new DefaultGenerator());
        initDonneLoc(rules_, donne_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne4Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        DealPresident donne_=new DealPresident(1,HandPresident.stack(1));
        donne_.setRandomDealer(rules_, new DefaultGenerator());
        initDonneLoc(rules_, donne_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne5Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.ONCE_ONLY);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setRandomDealer(rules_, new DefaultGenerator());
        initDonneLoc(rules_, donne_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne6Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.setMixedCards(MixCardsChoice.ONCE_ONLY);
        DealPresident donne_=new DealPresident(1,HandPresident.stack(1));
        donne_.setRandomDealer(rules_, new DefaultGenerator());
        initDonneLoc(rules_, donne_);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne7Test() {
        RulesPresident rules_ = new RulesPresident(5);
        rules_.setMixedCards(MixCardsChoice.NEVER);
        DealPresident donne_=new DealPresident(0,HandPresident.stack(1));
        donne_.setDealer((byte) 4);
        initDonneLoc(rules_, donne_);
        assertEq(5,donne_.nombreDeMains());
        assertEq(11,donne_.hand((byte)0).total());
        assertEq(11,donne_.hand((byte)1).total());
        assertEq(10,donne_.hand((byte)2).total());
        assertEq(10,donne_.hand((byte)3).total());
        assertEq(10,donne_.hand((byte)4).total());
    }
}
