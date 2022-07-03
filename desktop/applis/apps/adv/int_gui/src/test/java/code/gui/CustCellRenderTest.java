package code.gui;

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
        sample_.setCurrent(new GraphicListSample());
        sample_.fwd();
        assertEq("",sample_.get(0));
    }
    @Test
    public void t3() {
        CustCellRenderSample sample_ = new CustCellRenderSample();
        sample_.paintComponent(new ImageSample());
        sample_.setList(new CustList<String>(""));
        assertEq("",sample_.get(0));
    }
    @Test
    public void t4() {
        CustCellRenderSample sample_ = new CustCellRenderSample();
        sample_.paintComponent(new PreparedLabelSample());
        sample_.setList(new CustList<String>(""));
        assertEq("",sample_.get(0));
    }
}
