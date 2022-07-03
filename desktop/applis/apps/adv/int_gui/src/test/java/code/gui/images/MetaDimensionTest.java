package code.gui.images;

import code.gui.EquallableIntGuiUtil;
import org.junit.Test;

public class MetaDimensionTest extends EquallableIntGuiUtil {
    @Test
    public void dim() {
        MetaDimension cur_ = new MetaDimension(new MetaDimension(5, 6));
        assertEq(30, cur_.getHeight()*cur_.getWidth());
    }
}
