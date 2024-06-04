package code.gui;

import code.formathtml.render.*;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.core.StringUtil;
import org.junit.Test;

public final class TaskPaintingLabelTest extends EquallableGuiDocUtil {
    @Test
    public void test1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        TaskPaintingLabel t_ = new TaskPaintingLabel(new ProgressingWebDialog(pr_));
        t_.run();
        t_.getDialog().setTitle("0 s");
        assertEq("0 s",t_.getDialog().getTitle());
    }
}
