package code.gui;

import code.gui.images.AbstractImage;
import code.util.CustList;
import org.junit.Test;

public class CustCellRenderTest extends EquallableIntGuiUtil {
    @Test
    public void t1() {
        CustCellRenderSample sample_ = new CustCellRenderSample();
        sample_.setList(new CustList<String>(""));
        assertEq("",sample_.get(0));
    }
    @Test
    public void t2() {
        CustCellRenderSample sample_ = new CustCellRenderSample();
        sample_.setCurrent(null);
        sample_.getListGr();
        sample_.setList(new CustList<String>(""));
        assertEq("",sample_.get(0));
    }
    @Test
    public void t3() {
        CustCellRenderSample sample_ = new CustCellRenderSample();
        sample_.paintComponent((AbstractImage)null);
        sample_.setList(new CustList<String>(""));
        assertEq("",sample_.get(0));
    }
    @Test
    public void t4() {
        CustCellRenderSample sample_ = new CustCellRenderSample();
        sample_.paintComponent((AbsPreparedLabel)null);
        sample_.setList(new CustList<String>(""));
        assertEq("",sample_.get(0));
    }
    @Test
    public void t5() {
        ColorsGroupList g_ = new ColorsGroupList(1,2,3,4);
        assertEq(1,g_.getBg());
        assertEq(2,g_.getFg());
        assertEq(3,g_.getBgSel());
        assertEq(4,g_.getFgSel());
    }
}
