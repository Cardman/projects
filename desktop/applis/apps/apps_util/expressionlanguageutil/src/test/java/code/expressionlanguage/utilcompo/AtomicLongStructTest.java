package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class AtomicLongStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong0(newFileInfos(pr_), ""),null, null, null, null, null);
        assertEq(0,v_.getInstance().get());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ArgumentListCall args_ = one(new IntStruct(-1));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, args_, null);
        assertEq(-1,v_.getInstance().get());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        ArgumentListCall args_ = one(new IntStruct(1));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, args_, null);
        assertEq(1,v_.getInstance().get());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new DfAtomicLong(newFileInfos(pr_), ""),null, null, null, null);
        assertEq(0,v_.getInstance().get());
    }
    @Test
    public void set1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicLongSet(),null,null,v_,one(new IntStruct(1)), st_);
        assertEq(1,v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void set2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicLongSet(),null,null,v_,one(new IntStruct(0)), st_);
        assertEq(0,v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void setFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.READ_ONLY_OTHERS);
        call(new FctAtomicLongSet(),null,null,v_,one(new IntStruct(1)), st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void get1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicLongSet(),null,null,v_,one(new IntStruct(1)), st_);
        assertEq(1, asValue(new FctAtomicLongGet(), v_, null, st_));
    }

    @Test
    public void get2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicLongSet(),null,null,v_,one(new IntStruct(0)), st_);
        assertEq(0, asValue(new FctAtomicLongGet(), v_, null, st_));
    }
    @Test
    public void getSet1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(0, asValue(new FctAtomicLongGetSet(), v_, one(new IntStruct(1)), st_));
        assertEq(1, asValue(new FctAtomicLongGet(), v_, null, st_));
    }
    @Test
    public void getSet2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(1, asValue(new FctAtomicLongGetSet(), v_, one(new IntStruct(0)), st_));
        assertEq(0, asValue(new FctAtomicLongGet(), v_, null, st_));
    }
    @Test
    public void compareAndSet1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertFalse(call(new FctAtomicLongCompare(),null,null,v_,two(new IntStruct(0),new IntStruct(1)), st_));
        assertEq(1, asValue(new FctAtomicLongGet(),v_,null,st_));
    }
    @Test
    public void compareAndSet2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertTrue(call(new FctAtomicLongCompare(),null,null,v_,two(new IntStruct(1),new IntStruct(0)), st_));
        assertEq(0, asValue(new FctAtomicLongGet(),v_,null,st_));
    }
    @Test
    public void lazy1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong0(newFileInfos(pr_), ""),null, null, null, null, null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicLongLazy(),null,null,v_,one(new IntStruct(1)), st_);
        assertEq(1,v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void lazy2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        call(new FctAtomicLongLazy(),null,null,v_,one(new IntStruct(0)), st_);
        assertEq(0,v_.getInstance().get());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void getAndAdd() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(1,asValue(new FctAtomicLongGetAdd(),v_,one(new IntStruct(2)), st_));
        assertEq(3,asValue(new FctAtomicLongGet(),v_,null, st_));
    }
    @Test
    public void addAndGet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(1)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(3,asValue(new FctAtomicLongAddGet(),v_,one(new IntStruct(2)), st_));
        assertEq(3,asValue(new FctAtomicLongGet(),v_,null, st_));
    }
    @Test
    public void getAndIncrement() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(2)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(2,asValue(new FctAtomicLongGetInc(),v_,one(new IntStruct(2)), st_));
        assertEq(3,asValue(new FctAtomicLongGet(),v_,null, st_));
    }
    @Test
    public void incrementAndGet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(2)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(3,asValue(new FctAtomicLongIncGet(),v_,one(new IntStruct(2)), st_));
        assertEq(3,asValue(new FctAtomicLongGet(),v_,null, st_));
    }
    @Test
    public void getAndDecrement() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(-2)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(-2,asValue(new FctAtomicLongGetDec(),v_,one(new IntStruct(-2)), st_));
        assertEq(-3,asValue(new FctAtomicLongGet(),v_,null, st_));
    }
    @Test
    public void decrementAndGet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AtomicLongStruct v_ = (AtomicLongStruct) call(new FctAtomicLong1(newFileInfos(pr_), ""),null, null, null, one(new IntStruct(-2)), null);
        StackCall st_ = stack(v_, InitPhase.NOTHING);
        assertEq(-3,asValue(new FctAtomicLongDecGet(),v_,one(new IntStruct(-2)), st_));
        assertEq(-3,asValue(new FctAtomicLongGet(),v_,null, st_));
    }
    private int asValue(StdCaller _c, AtomicLongStruct _v, ArgumentListCall _args, StackCall _st) {
        return ((LongStruct) call(_c, null, null, _v, _args, _st)).intStruct();
    }

}
