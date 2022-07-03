package code.gui.images;

import code.gui.EquallableIntGuiUtil;
import org.junit.Test;

public class MetaFontTest extends EquallableIntGuiUtil {
    @Test
    public void t1() {
        MetaFont cur_ = new MetaFont("", 1, 1);
        assertEq("",cur_.getFontFamily());
        assertEq(1, cur_.getFont()* cur_.getRealSize());
    }
}
