package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.EquallableElUtUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class AtomicRefStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef0(""),null, gene(pr_), null, null, null);
        assertSame(NullStruct.NULL_VALUE,v_.getInstance().get());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ArgumentListCall args_ = one(new StringStruct("_"));
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef1(""),null, gene(pr_), null, args_, null);
        assertEq("_",v_.getInstance().get());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicRefStruct v_ = (AtomicRefStruct) call(new DfAtomicRef(""),null, gene(pr_), null, null);
        assertSame(NullStruct.NULL_VALUE,v_.getInstance().get());
    }
    @Test
    public void set1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ContextEl ctx_ = gene(pr_);
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef0(""),null, ctx_, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicRefSet(),null,ctx_,v_,one(new StringStruct("_")), st_);
        assertEq("_",v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void setFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ContextEl ctx_ = gene(pr_);
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef0(""),null, ctx_, null, null, null);
        StackCall st_ = stack(v_, InitPhase.READ_ONLY_OTHERS);
        call(new FctAtomicRefSet(),null,ctx_,v_,one(new StringStruct("_")), st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void get1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ContextEl ctx_ = gene(pr_);
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef0(""),null, ctx_, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicRefSet(),null,ctx_,v_,one(new StringStruct("_")), st_);
        assertEq("_",call(new FctAtomicRefGet(),null,ctx_,v_,null,st_));
    }
    @Test
    public void getSet1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ContextEl ctx_ = gene(pr_);
        ArgumentListCall args_ = one(new StringStruct("_"));
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef1(""),null, ctx_, null, args_, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq("_",call(new FctAtomicRefGetSet(),null,ctx_,v_,one(new StringStruct("__")), st_));
        assertEq("__",call(new FctAtomicRefGet(),null,ctx_,v_,null,st_));
    }
//    @Test
//    public void compareAndSet1() {
//        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
//        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef1(""),null, null, null, one(RefStruct.of(true)), null);
//        StackCall st_ = stack(v_, InitPhase.NOTHING);
//        assertFalse(call(new FctAtomicRefCompare(),null,null,v_,two(RefStruct.of(false),RefStruct.of(true)), st_));
//        assertTrue(call(new FctAtomicRefGet(),null,null,v_,null,st_));
//    }
//    @Test
//    public void compareAndSet2() {
//        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
//        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef1(""),null, null, null, one(RefStruct.of(true)), null);
//        StackCall st_ = stack(v_, InitPhase.NOTHING);
//        assertTrue(call(new FctAtomicRefCompare(),null,null,v_,two(RefStruct.of(true),RefStruct.of(false)), st_));
//        assertFalse(call(new FctAtomicRefGet(),null,null,v_,null,st_));
//    }
    @Test
    public void lazy1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ContextEl ctx_ = gene(pr_);
        AtomicRefStruct v_ = (AtomicRefStruct) call(new FctAtomicRef0(""),null, ctx_, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicRefLazy(),null,null,v_,one(new StringStruct("_")), st_);
        assertEq("_",v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void cl() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicRefStruct v_ = new AtomicRefStruct(gene(pr_).getCaller().newAtObj(),"");
        assertEq("", v_.getClassName(null));
    }
    private static ContextEl gene(MockProgramInfos _pr) {
        LgNamesGui stds_ = newLgNamesGuiSample(_pr, null);
        Options opt_ = new Options();
        return gene(stds_,opt_);
    }
}
