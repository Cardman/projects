package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class ThreadStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctThread(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        call(new FctThread(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertNull(((ThreadStruct)th_).getThread().getThread());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertSame(s_, (Struct) ((ThreadStruct)th_).getThread().getThread());
    }
}
