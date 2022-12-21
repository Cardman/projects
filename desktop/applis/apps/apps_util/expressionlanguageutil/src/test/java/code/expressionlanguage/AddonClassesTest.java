package code.expressionlanguage;

import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.*;

public final class AddonClassesTest extends EquallableElUtUtil {
    @Test
    public void executorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        assertEq(stds_.getCustAliases().getAliasExecutorService(),new ExecutorServiceStruct(pr_.getThreadFactory()).getClassName(stds_.newContext(opt_, getForwards(stds_, opt_))));
    }
    @Test
    public void futureStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(pr_.getThreadFactory(),2);
        ArgumentListCall list_ = one(new MockRunnableStruct(""));
        assertEq(stds_.getCustAliases().getAliasFuture(),call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null).getClassName(stds_.newContext(opt_, getForwards(stds_, opt_))));
    }
}
