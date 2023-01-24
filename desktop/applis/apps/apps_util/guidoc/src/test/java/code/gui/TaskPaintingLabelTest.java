package code.gui;

import code.formathtml.render.*;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.core.StringUtil;
import org.junit.Test;

public final class TaskPaintingLabelTest extends EquallableGuiDocUtil {
    @Test
    public void test1() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        TaskPaintingLabel t_ = new TaskPaintingLabel(new ProgressingWebDialog(pr_));
        t_.run();
        t_.getDialog().setTitle("0 s");
        assertEq("0 s",t_.getDialog().getTitle());
    }
}
