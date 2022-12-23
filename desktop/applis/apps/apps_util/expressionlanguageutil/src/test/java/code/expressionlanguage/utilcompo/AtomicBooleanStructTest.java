package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class AtomicBooleanStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean0(newFileInfos(pr_), ""),null, null, null, null, null);
        assertFalse(v_.getInstance().get());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ArgumentListCall args_ = one(BooleanStruct.of(false));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, args_, null);
        assertFalse(v_.getInstance().get());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ArgumentListCall args_ = one(BooleanStruct.of(true));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, args_, null);
        assertTrue(v_.getInstance().get());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new DfAtomicBoolean(newFileInfos(pr_), ""),null, null, null, null);
        assertFalse(v_.getInstance().get());
    }
    @Test
    public void set1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicBooleanSet(),null,null,v_,one(BooleanStruct.of(true)), st_);
        assertTrue(v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void set2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, one(BooleanStruct.of(true)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicBooleanSet(),null,null,v_,one(BooleanStruct.of(false)), st_);
        assertFalse(v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void setFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.READ_ONLY_OTHERS);
        call(new FctAtomicBooleanSet(),null,null,v_,one(BooleanStruct.of(true)), st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void get1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicBooleanSet(),null,null,v_,one(BooleanStruct.of(true)), st_);
        assertTrue(call(new FctAtomicBooleanGet(),null,null,v_,null,st_));
    }
    @Test
    public void get2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, one(BooleanStruct.of(true)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicBooleanSet(),null,null,v_,one(BooleanStruct.of(false)), st_);
        assertFalse(call(new FctAtomicBooleanGet(),null,null,v_,null,st_));
    }
    @Test
    public void getSet1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertFalse(call(new FctAtomicBooleanGetSet(),null,null,v_,one(BooleanStruct.of(true)), st_));
        assertTrue(call(new FctAtomicBooleanGet(),null,null,v_,null,st_));
    }
    @Test
    public void getSet2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, one(BooleanStruct.of(true)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertTrue(call(new FctAtomicBooleanGetSet(),null,null,v_,one(BooleanStruct.of(false)), st_));
        assertFalse(call(new FctAtomicBooleanGet(),null,null,v_,null,st_));
    }
    @Test
    public void compareAndSet1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, one(BooleanStruct.of(true)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertFalse(call(new FctAtomicBooleanCompare(),null,null,v_,two(BooleanStruct.of(false),BooleanStruct.of(true)), st_));
        assertTrue(call(new FctAtomicBooleanGet(),null,null,v_,null,st_));
    }
    @Test
    public void compareAndSet2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, one(BooleanStruct.of(true)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertTrue(call(new FctAtomicBooleanCompare(),null,null,v_,two(BooleanStruct.of(true),BooleanStruct.of(false)), st_));
        assertFalse(call(new FctAtomicBooleanGet(),null,null,v_,null,st_));
    }
    @Test
    public void lazy1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicBooleanLazy(),null,null,v_,one(BooleanStruct.of(true)), st_);
        assertTrue(v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void lazy2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = (AtomicBooleanStruct) call(new FctAtomicBoolean1(newFileInfos(pr_), ""),null, null, null, one(BooleanStruct.of(true)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicBooleanLazy(),null,null,v_,one(BooleanStruct.of(false)), st_);
        assertFalse(v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void cl() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicBooleanStruct v_ = new AtomicBooleanStruct(pr_.getThreadFactory().newAtomicBoolean(),"");
        assertEq("", v_.getClassName(null));
    }
}
