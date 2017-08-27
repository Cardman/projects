package aiki.facade;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.util.CustList;

@SuppressWarnings("static-method")
public class FacadeGameTest extends InitializationDataBase {

    @Test
    public void setSelectPkToHost1Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost((short) 1);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost2Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 1);
        assertEq(CustList.INDEX_NOT_FOUND_ELT, facadeGame_.getFirstSelectPkToHost());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost3Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 2);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(2, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost4Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 2);
        facadeGame_.setSelectPkToHost((short) 1);
        assertEq(2, facadeGame_.getFirstSelectPkToHost());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost5Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 2);
        facadeGame_.setSelectPkToHost((short) 2);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, facadeGame_.getSecondSelectPkToHost());
    }

    @Test
    public void setSelectPkToHost6Test() {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 2);
        facadeGame_.setSelectPkToHost((short) 3);
        assertEq(1, facadeGame_.getFirstSelectPkToHost());
        assertEq(3, facadeGame_.getSecondSelectPkToHost());
    }
}
