package code.gui.images;

import code.gui.EquallableIntGuiUtil;
import org.junit.Test;

public class MetaRectTest extends EquallableIntGuiUtil {
    @Test
    public void t1() {
        MetaRect cur_ = new MetaRect(0, 0, 1, 1);
        assertEq(1,cur_.getHeight()* cur_.getWidth());
    }
    @Test
    public void t2() {
        MetaRect cur_ = new MetaRect(0, 0, 1, 1);
        assertEq(0,cur_.getPoint().getXcoord()+ cur_.getPoint().getYcoord());
        assertEq(1,cur_.move().getXcoord());
        assertEq(1,cur_.move().getYcoord());
    }
}
