package cards.president;

import cards.consts.MixCardsChoice;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.DefaultGenerator;
import org.junit.Test;

public final class DealPresidentTest extends EquallablePresidentUtil {
    @Test
    public void initDonne1Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealPresident donne_ = dealRandPlayer(rules_, 0);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne2Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
        DealPresident donne_ = dealRandPlayer(rules_, 0);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }

    @Test
    public void initDonne3Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        DealPresident donne_ = dealRandPlayer(rules_, 0);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne4Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        DealPresident donne_ = dealRandPlayer(rules_, 1);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne5Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        DealPresident donne_ = dealRandPlayer(rules_, 0);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }
    @Test
    public void initDonne6Test() {
        RulesPresident rules_ = new RulesPresident();
        rules_.getCommon().setMixedCards(MixCardsChoice.ONCE_ONLY);
        DealPresident donne_ = dealRandPlayer(rules_, 1);
        assertEq(4,donne_.nombreDeMains());
        assertEq(13,donne_.hand((byte)0).total());
        assertEq(13,donne_.hand((byte)1).total());
        assertEq(13,donne_.hand((byte)2).total());
        assertEq(13,donne_.hand((byte)3).total());
    }

    @Test
    public void initDonne7Test() {
        RulesPresident rules_ = new RulesPresident(5);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealPresident donne_ = dealFixPlayer(rules_, 0, 4);
        assertEq(5,donne_.nombreDeMains());
        assertEq(11,donne_.hand((byte)0).total());
        assertEq(11,donne_.hand((byte)1).total());
        assertEq(10,donne_.hand((byte)2).total());
        assertEq(10,donne_.hand((byte)3).total());
        assertEq(10,donne_.hand((byte)4).total());
    }

    private DealPresident dealRandPlayer(RulesPresident _r, int _nb) {
        DealPresident donne_=new DealPresident(_nb);
        AbstractGenerator gene_ = DefaultGenerator.oneElt();
//        donne_.setRandomDealer(_r, gene_);
        donne_.initDonne(_r, gene_,HandPresident.stack(1));
        return donne_;
    }

    private DealPresident dealFixPlayer(RulesPresident _r, int _nb, int _d) {
        DealPresident donne_=new DealPresident(_nb);
        donne_.setDealer((byte) _d);
        donne_.initDonne(_r, DefaultGenerator.oneElt(),HandPresident.stack(1));
        return donne_;
    }
}
