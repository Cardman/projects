package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
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
    @Test
    public void setPriority1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        call(new FctThreadSetPrio(), null, ctx_, th_, one(new IntStruct(0)),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void setPriority2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        call(new FctThreadSetPrio(), null, ctx_, th_, one(new IntStruct(11)),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void setPriority3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(true);
        call(new FctThreadSetPrio(), null, ctx_, th_, one(new IntStruct(7)),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void setPriority4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(false);
        call(new FctThreadSetPrio(), null, ctx_, th_, one(new IntStruct(7)),st_);
        assertTrue(st_.calls());
    }
    @Test
    public void getPriority() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(false);
        call(new FctThreadSetPrio(), null, ctx_, th_, one(new IntStruct(7)),st_);
        assertEq(7,toLong(call(new FctThreadGetPrio(),null,ctx_,th_,null,st_)));
    }
    @Test
    public void start1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(true);
        call(new FctThreadStart(""), null, ctx_, th_, null,st_);
        assertFalse(st_.calls());
    }
    @Test
    public void start2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(false);
        call(new FctThreadStart(""), null, ctx_, th_, null,st_);
        assertTrue(st_.calls());
    }
    @Test
    public void ended1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        call(new FctThreadEnd(), null, ctx_, th_, null,st_);
        assertTrue(call(new FctThreadIsEnded(),null,ctx_,th_,null,st_));
    }
    @Test
    public void ended2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadIsEnded(),null,ctx_,th_,null,st_));
    }
    @Test
    public void runnable1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        ThreadStruct t_ = new ThreadStruct(pr_.getThreadFactory().newThread(), pr_.getThreadFactory().newAtomicBoolean(), NullStruct.NULL_VALUE);
        call(new FctThreadRunnable(stds_.getCustAliases()),null,ctx_,t_,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void runnable2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertSame(s_, call(new FctThreadRunnable(stds_.getCustAliases()), null, ctx_, th_,null,st_));
    }
    @Test
    public void current1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        call(new FctThreadCurrent(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void current2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        assertSame(((RunnableContextEl)ctx_).getThread(), call(new FctThreadCurrent(stds_.getCustAliases()), null, ctx_, null,null,st_));
    }
    @Test
    public void getId() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertEq(1,toLong(call(new FctThreadGetId(),null,ctx_,th_,null,st_)));
    }
    @Test
    public void thEq1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void thEq2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,two(NullStruct.NULL_VALUE,th_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,two(th_,NullStruct.NULL_VALUE),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        MockRunnableStruct t_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        Struct thSec_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(t_), st_);
        assertFalse(call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,two(th_,thSec_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        assertTrue(call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertTrue(call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,two(th_,th_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        Struct thSec_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertTrue(call(new FctThreadEq(stds_.getCustAliases()),null,ctx_,null,two(th_,thSec_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isAlive1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(true);
        assertTrue(call(new FctThreadIsAlive(), null, ctx_, th_, null, st_));
    }
    @Test
    public void isAlive2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        ((MockThread)((ThreadStruct)th_).getThread()).setAlive(false);
        assertFalse(call(new FctThreadIsAlive(), null, ctx_, th_, null, st_));
    }
    @Test
    public void joinTh1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadJoin(), null, ctx_, th_, null, st_));
    }
    @Test
    public void state1() {
        assertSame(NullStruct.NULL_VALUE,value(FctThreadJoin.toResult(ThState.INTERRUPTED)));
    }
    @Test
    public void state2() {
        assertTrue(value(FctThreadJoin.toResult(ThState.ALIVE)));
    }
    @Test
    public void state3() {
        assertFalse(value(FctThreadJoin.toResult(ThState.ENDED)));
    }
    @Test
    public void joinOthers1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        call(new FctThreadJoinOthers(stds_.getCustAliases()), null, ctx_, th_, null, st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void joinOthers2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getCustAliases()), null, ctx_, null, one(s_), st_);
        GuiContextEl r_ = new GuiContextEl(NullStruct.NULL_VALUE,ctx_.getExecutionInfos(),new StringList());
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        RunnableStruct.setupThread(r_);
        call(new FctThreadJoinOthers(stds_.getCustAliases()), null, ctx_, th_, null, st_);
        call(new FctThreadJoinOthers(stds_.getCustAliases()), null, ctx_, r_.getThread(), null, st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void sleepTh1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadSleep(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void sleepTh2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadSleep(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void sleepTh3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        assertFalse(call(new FctThreadSleep(stds_.getCustAliases()),null,ctx_,null,one(new IntStruct(-1)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void sleepTh4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.25)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        assertFalse(call(new FctThreadSleep(stds_.getCustAliases()),null,ctx_,null,one(new IntStruct(1)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void sleepTh5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        assertTrue(call(new FctThreadSleep(stds_.getCustAliases()),null,ctx_,null,one(new IntStruct(1)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void print1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint0(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void print2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint0(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void print3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint0(stds_.getCustAliases()),null,ctx_,null,one(call(new FctThread(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_)),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void print4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_,new StringMap<String>()).getContext();
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint1(stds_.getCustAliases(),stds_.getExecutingBlocks(),""),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void print5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        Options opt_ = new Options();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_,new StringMap<String>()).getContext();
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint2(stds_.getCustAliases(),stds_.getExecutingBlocks(),""),null,ctx_,null,two(new StringStruct(""),new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void nanos1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadNano(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void nanos2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadNano(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void millis1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadMillis(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void millis2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadMillis(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
    }
}
