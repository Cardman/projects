package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
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
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctThread(stds_.getExecContent().getCustAliases(), ""),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        call(new FctThread(stds_.getExecContent().getCustAliases(), ""),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertNull(((AbsThreadStruct)th_).getThread().getThread());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertSame(s_, (Struct) ((AbsThreadStruct)th_).getThread().getThread());
    }
    @Test
    public void setPriority1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        call(new FctThreadSetPrio(""), null, ctx_, th_, one(new IntStruct(0)),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void setPriority2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        call(new FctThreadSetPrio(""), null, ctx_, th_, one(new IntStruct(11)),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void setPriority3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(true);
        call(new FctThreadSetPrio(""), null, ctx_, th_, one(new IntStruct(7)),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void setPriority4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(false);
        call(new FctThreadSetPrio(""), null, ctx_, th_, one(new IntStruct(7)),st_);
        assertTrue(st_.calls());
    }
    @Test
    public void getPriority() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(false);
        call(new FctThreadSetPrio(""), null, ctx_, th_, one(new IntStruct(7)),st_);
        assertEq(7,toLong(call(new FctThreadGetPrio(),null,ctx_,th_,null,st_)));
    }
    @Test
    public void start1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(true);
        call(new FctThreadStart("", ""), null, ctx_, th_, null,st_);
        assertFalse(st_.calls());
    }
    @Test
    public void start2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(false);
        call(new FctThreadStart("", ""), null, ctx_, th_, null,st_);
        assertTrue(st_.calls());
    }
    @Test
    public void ended1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        call(new FctThreadEnd(), null, ctx_, th_, null,st_);
        assertTrue(call(new FctThreadIsEnded(),null,ctx_,th_,null,st_));
    }
    @Test
    public void ended2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadIsEnded(),null,ctx_,th_,null,st_));
    }
    @Test
    public void runnable1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        ThreadStruct t_ = new ThreadStruct(pr_.getThreadFactory().newThread(), pr_.getThreadFactory().newAtomicBoolean(), NullStruct.NULL_VALUE);
        call(new FctThreadRunnable(stds_.getExecContent().getCustAliases()),null,ctx_,t_,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void runnable2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertSame(s_, call(new FctThreadRunnable(stds_.getExecContent().getCustAliases()), null, ctx_, th_,null,st_));
    }
    @Test
    public void current1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        call(new FctThreadCurrent(stds_.getExecContent().getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void current2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertSame(((RunnableContextEl)ctx_).getThread(), call(new FctThreadCurrent(stds_.getExecContent().getCustAliases()), null, ctx_, null,null,st_));
    }
    @Test
    public void getId() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertEq(1,toLong(call(new FctThreadGetId(""),null,ctx_,th_,null,st_)));
    }
    @Test
    public void thEq1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.LIST);
        st_.setFullStack(new DefaultFullStack(ctx_));
        call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void thEq2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(NullStruct.NULL_VALUE,th_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(th_,NullStruct.NULL_VALUE),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        MockRunnableStruct t_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        Struct thSec_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(t_), st_);
        assertFalse(call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(th_,thSec_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertTrue(call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertTrue(call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(th_,th_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void thEq7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        Struct thSec_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertTrue(call(new FctThreadEq(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(th_,thSec_),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isAlive1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(true);
        assertTrue(call(new FctThreadIsAlive(""), null, ctx_, th_, null, st_));
    }
    @Test
    public void isAlive2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        ((MockThread)((AbsThreadStruct)th_).getThread()).setAlive(false);
        assertFalse(call(new FctThreadIsAlive(""), null, ctx_, th_, null, st_));
    }
    @Test
    public void joinTh1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        assertFalse(call(new FctThreadJoin(""), null, ctx_, th_, null, st_));
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
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        call(new FctThreadJoinOthers(stds_.getExecContent().getCustAliases(), ""), null, ctx_, th_, null, st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void joinOthers2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(s_), st_);
        GuiContextEl r_ = new GuiContextEl(((RunnableContextEl)ctx_).getInterrupt(),NullStruct.NULL_VALUE,ctx_.getExecutionInfos(),new StringList());
        EventStruct.setupThread((RunnableContextEl) ctx_);
        EventStruct.setupThread(r_);
        call(new FctThreadJoinOthers(stds_.getExecContent().getCustAliases(), ""), null, ctx_, th_, null, st_);
        call(new FctThreadJoinOthers(stds_.getExecContent().getCustAliases(), ""), null, ctx_, r_.getThread(), null, st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void sleepTh1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadSleep(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void sleepTh2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadSleep(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.calls());
    }
    @Test
    public void sleepTh3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        assertFalse(call(new FctThreadSleep(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(new IntStruct(-1)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void sleepTh4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.25)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        assertFalse(call(new FctThreadSleep(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(new IntStruct(1)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void sleepTh5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        assertTrue(call(new FctThreadSleep(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(new IntStruct(1)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void print1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint0(stds_.getExecContent().getCustAliases()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void print2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint0(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void print3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint0(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(call(new FctThread(stds_.getExecContent().getCustAliases(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_)),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void print4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        update(pr_);
        ContextEl ctx_ = ctxRunnable(pr_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint1(((LgNamesGui)ctx_.getStandards()).getExecContent().getCustAliases(),((LgNamesGui)ctx_.getStandards()).getExecContent().getExecutingBlocks(),""),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void print5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        update(pr_);
        ContextEl ctx_ = ctxRunnable(pr_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadPrint2(((LgNamesGui)ctx_.getStandards()).getExecContent().getCustAliases(),((LgNamesGui)ctx_.getStandards()).getExecContent().getExecutingBlocks(),""),null,ctx_,null,two(new StringStruct(""),new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void nanos1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadNano(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void nanos2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadNano(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void millis1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadMillis(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void millis2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadMillis(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
    }
    private ContextEl ctxRunnable(MockProgramInfos _p) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockFormat(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,new StringMap<String>()).getContext();
    }
    @Test
    public void launchDbgRun1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new Thread((Runnable)(:void)->{class(Sample);});g.start();g.join();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 88);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(88,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(117,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void launchDbgRun2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new Thread((Runnable)(:void)->{class(Sample);});g.start();g.join();g.join();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 88);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(88,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(117,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void launchDbgRun3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new Thread((Runnable)(:void)->{g.isAlive();});g.join();g.isAlive();g.setPriority(g.getPriority());}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 88);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    private StackCallReturnValue launchDbg(ResultContext _ctx) {
        ExecRootBlock ex_ = _ctx.getContext().getClasses().getClassBody("pkg.Sample");
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ExecClassesUtil.tryInitStaticlyTypes(_ctx.getContext(), _ctx.getForwards().getOptions(), null, new CustomFoundMethod(form_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), new Parameters()), StepDbgActionEnum.DEBUG, false);
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, false);
    }
    private ResultContext ctxResRun(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockDbgRun(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }
    public static ResultContext buildMockDbgRun(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().runnableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();
        _definedLgNames.getExecContent().getCustAliases().thread(_definedLgNames.getContent(), _definedLgNames.getExecContent().getExecutingBlocks());
        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getExecContent().getExecutingBlocks().runnable(_definedLgNames.getExecContent().getCustAliases(),res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }
    public static ResultContext buildMockFormat(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().formatter(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        _definedLgNames.getExecContent().getExecutingBlocks().formatter(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
}
