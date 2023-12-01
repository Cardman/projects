package code.gui;

import code.gui.initialize.DefStringBuffer;
import org.junit.Test;

public final class DefStringBufferTest extends EquallableIntGuiUtil {
    @Test
    public void test() {
        DefStringBuffer d_ = new DefStringBuffer();
        d_.copy("_");
        assertEq("_",d_.paste());
    }
}
