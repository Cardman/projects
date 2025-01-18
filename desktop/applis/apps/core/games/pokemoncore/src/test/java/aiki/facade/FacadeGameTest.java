package aiki.facade;

import code.util.core.IndexConstants;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;


public class FacadeGameTest extends InitializationDataBase {

    @Test
    public void setSelectPkToHost1Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost( 1);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost2Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost( 1);
        facadeGame_.setSelectPkToHost( 1);
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, facadeGame_.getFirstSelectPkToHost());
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost3Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost( 1);
        facadeGame_.setSelectPkToHost( 2);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(2, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost4Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost( 1);
        facadeGame_.setSelectPkToHost( 2);
        facadeGame_.setSelectPkToHost( 1);
        assertEq(2, facadeGame_.getFirstSelectPkToHost());
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost5Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost( 1);
        facadeGame_.setSelectPkToHost( 2);
        facadeGame_.setSelectPkToHost( 2);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost6Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost( 1);
        facadeGame_.setSelectPkToHost( 2);
        facadeGame_.setSelectPkToHost( 3);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(3, facadeGame_.getSecondSelectPkToHost());
    }
}
