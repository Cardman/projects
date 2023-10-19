package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class ThreadSetStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        Struct arr_ = call(new FctThreadSetSnap(), null, ctx_, thSet_, null, st_);
        assertEq(0, ((ArrayStruct)arr_).getLength());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(call(new FctThread(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_)),st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(NullStruct.NULL_VALUE),st_);
        Struct arr_ = call(new FctThreadSetSnap(), null, ctx_, thSet_, null, st_);
        assertEq(1, ((ArrayStruct)arr_).getLength());
        assertFalse(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        st_.setFullStack(new DefaultFullStack(ctx_));
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(thSet_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(call(new FctThread(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_)),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new DfThreadSet(new MockInterceptor()), null, ctx_, null, st_);
        Struct arr_ = call(new FctThreadSetSnap(), null, ctx_, thSet_, null, st_);
        assertEq(0, ((ArrayStruct)arr_).getLength());
        assertTrue(st_.calls());
    }
    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(th_),st_);
        call(new FctThreadSetRemove(),null,ctx_,thSet_,one(th_),st_);
        Struct arr_ = call(new FctThreadSetSnap(), null, ctx_, thSet_, null, st_);
        assertEq(0, ((ArrayStruct)arr_).getLength());
        assertTrue(st_.calls());
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(th_),st_);
        call(new FctThreadSetRemove(),null,ctx_,thSet_,one(NullStruct.NULL_VALUE),st_);
        Struct arr_ = call(new FctThreadSetSnap(), null, ctx_, thSet_, null, st_);
        assertEq(1, ((ArrayStruct)arr_).getLength());
        assertTrue(st_.calls());
    }
    @Test
    public void remove3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        st_.setFullStack(new DefaultFullStack(ctx_));
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(thSet_);
        call(new FctThreadSetRemove(),null,ctx_,thSet_,one(call(new FctThread(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_)),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void contains1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        Struct sec_ = call(new FctThread(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(th_),st_);
        assertFalse(call(new FctThreadSetContains(),null,ctx_,thSet_,one(sec_),st_));
    }
    @Test
    public void contains2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(th_),st_);
        assertTrue(call(new FctThreadSetContains(),null,ctx_,thSet_,one(th_),st_));
    }
    @Test
    public void contains3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct thSet_ = call(new FctThreadSet(new MockInterceptor()), null, ctx_, null, null, st_);
        Struct th_ = call(new FctThread(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctThreadSetAdd(),null,ctx_,thSet_,one(th_),st_);
        assertFalse(call(new FctThreadSetContains(),null,ctx_,thSet_,one(NullStruct.NULL_VALUE),st_));
    }
    @Test
    public void all1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        ThreadSetStruct arr_ = (ThreadSetStruct) call(new FctThreadSetAll(stds_.getExecContent().getCustAliases()), null, ctx_, null, null, st_);
        assertEq(1, arr_.toSnapshotArray(ctx_,st_).getLength());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void all2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        EventStruct.setupThread((RunnableContextEl) ctx_);
        call(new FctThreadSetAll(stds_.getExecContent().getCustAliases()), null, ctx_, null, null, st_);
        assertTrue(st_.isFailInit());
    }
}
