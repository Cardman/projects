package code.gui.images;

import code.gui.EquallableIntGuiUtil;
import org.junit.Test;

public class MetaPointTest extends EquallableIntGuiUtil {
    @Test
    public void dim() {
        MetaPoint cur_ = new MetaPoint(5, 6);
        assertEq(30, cur_.getXcoord()*cur_.getYcoord());
    }
}
