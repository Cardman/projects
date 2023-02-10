package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DefaultFileEscapedCalc;
import code.expressionlanguage.common.FileMetrics;
import org.junit.Test;

public final class StatusBarLciTest extends EquallableElAdvUtil {
    @Test
    public void formatLci1() {
        FileBlock fb_ = new FileBlock(0, false, "", new DefaultFileEscapedCalc());
        fb_.metrics("_\n__\n_");
        FileMetrics m_ = fb_.getMetrics(4);
        assertEq(":2,1,2",EditorCaretListener.formatLci(m_,2,""));
    }
    @Test
    public void formatLci2() {
        FileBlock fb_ = new FileBlock(0, false, "", new DefaultFileEscapedCalc());
        fb_.metrics("_\n__\n_");
        FileMetrics m_ = fb_.getMetrics(4);
        assertEq(":2,1,2:1",EditorCaretListener.formatLci(m_,2,"_"));
    }
}
