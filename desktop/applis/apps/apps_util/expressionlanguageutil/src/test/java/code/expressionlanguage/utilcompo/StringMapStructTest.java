package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class StringMapStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        assertEq(0, NumParsers.convertToNumber(m_.size()).intStruct());
    }
    @Test
    public void putFail1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void putFail2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(m_,InitPhase.READ_ONLY_OTHERS);
        call(new FctTastrPut(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void putFail3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two( new StringStruct(""), NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void replaceFail1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrReplace(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void replaceFail2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(m_,InitPhase.READ_ONLY_OTHERS);
        call(new FctTastrReplace(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void replaceFail3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrReplace(),null,ctx_,m_,two( new StringStruct(""), NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void replace1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrReplace(),null,ctx_,m_,two(new StringStruct(""), new StringStruct("")),st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrGet() ,null,ctx_,m_,one(new StringStruct("")),st_));
    }
    @Test
    public void replace2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("");
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_));
        StringStruct sec_ = new StringStruct("");
        assertSame(value_,call(new FctTastrReplace(),null,ctx_,m_, two(new StringStruct(""), sec_),st_));
        assertSame(sec_,call(new FctTastrGet(),null,ctx_,m_, one(new StringStruct("")),st_));
    }

    @Test
    public void putIfAbsentFail1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPutAbs(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void putIfAbsentFail2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(m_,InitPhase.READ_ONLY_OTHERS);
        call(new FctTastrPutAbs(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void putIfAbsentFail3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPutAbs(),null,ctx_,m_,two( new StringStruct(""), NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void putIfAbsent1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("");
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrPutAbs(),null,ctx_,m_,two(new StringStruct(""), value_),st_));
        assertSame(value_,call(new FctTastrGet() ,null,ctx_,m_,one(new StringStruct("")),st_));
    }
    @Test
    public void putIfAbsent2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("");
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_));
        StringStruct sec_ = new StringStruct("");
        assertSame(value_,call(new FctTastrPutAbs(),null,ctx_,m_, two(new StringStruct(""), sec_),st_));
        assertSame(value_,call(new FctTastrGet(),null,ctx_,m_, one(new StringStruct("")),st_));
    }

    @Test
    public void getFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrGet(),null,ctx_,m_,one( NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void removeFail1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrRemove(),null,ctx_,m_,one( NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void removeFail2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(m_,InitPhase.READ_ONLY_OTHERS);
        call(new FctTastrRemove(),null,ctx_,m_,one( NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrRemove(),null,ctx_,m_,one(new StringStruct("")),st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrGet(),null,ctx_,m_,one(new StringStruct("")),st_));
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("");
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_));
        assertSame(value_,call(new FctTastrRemove(),null,ctx_,m_,one(new StringStruct("")),st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrGet(),null,ctx_,m_, one(new StringStruct("")),st_));
    }

    @Test
    public void containsValueFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrHasValue(),null,ctx_,m_,one( NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void containsValue1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        assertFalse(call(new FctTastrHasValue(),null,ctx_,m_,one( new StringStruct("_")),st_));
    }

    @Test
    public void containsValue2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        assertTrue(call(new FctTastrHasValue(),null,ctx_,m_,one( new StringStruct("")),st_));
    }

    @Test
    public void containsKeyFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrHasKey(),null,ctx_,m_,one( NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void containsKey1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        assertFalse(call(new FctTastrHasKey(),null,ctx_,m_,one( new StringStruct("_")),st_));
    }

    @Test
    public void containsKey2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        assertTrue(call(new FctTastrHasKey(),null,ctx_,m_,one( new StringStruct("")),st_));
    }

    @Test
    public void pairs() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        Struct pairs_ = call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        assertEq(1, ((ArrayStruct)pairs_).getLength());
    }

    @Test
    public void keys() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        Struct pairs_ = call(new FctTastrKeys0(), null, ctx_, m_, null, st_);
        assertEq(1, ((ArrayStruct)pairs_).getLength());
    }

    @Test
    public void values() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        Struct pairs_ = call(new FctTastrValues(), null, ctx_, m_, null, st_);
        assertEq(1, ((ArrayStruct)pairs_).getLength());
    }

    @Test
    public void keysFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrKeys1(),null,ctx_,m_,one( NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void keys1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        Struct pairs_ = call(new FctTastrKeys1(), null, ctx_, m_, one(new StringStruct("_")), st_);
        assertEq(0, ((ArrayStruct)pairs_).getLength());
    }

    @Test
    public void keys2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        Struct pairs_ = call(new FctTastrKeys1(), null, ctx_, m_, one(new StringStruct("")), st_);
        assertEq(1, ((ArrayStruct)pairs_).getLength());
    }

    @Test
    public void clearFail() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(m_);
        call(new FctTastrClear(),null,ctx_,m_,null,st_);
        assertTrue(st_.isFailInit());
        assertFalse(call(new FctTastrIsEmpty(),null,ctx_,m_,null,st_));
    }
    @Test
    public void clear() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        call(new FctTastrClear(),null,ctx_,m_,null,st_);
        assertTrue(call(new FctTastrIsEmpty(),null,ctx_,m_,null,st_));
    }

    @Test
    public void putAllFail1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPutAll(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void putAllFail2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(m_,InitPhase.READ_ONLY_OTHERS);
        call(new FctTastrPutAll(),null,ctx_,m_,two( NullStruct.NULL_VALUE, NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void putAllFail3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTastrPutAll(),null,ctx_,m_,two( new StringStruct(""), NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void putAll() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        StringMapStruct a_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        call(new FctTastrPut(),null,ctx_,a_,two(new StringStruct(""),new StringStruct("")),st_);
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""),new StringStruct("")),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTastrPutAll(),null,ctx_,m_,two(new StringStruct(""), a_),st_));
        assertEq(1,NumParsers.convertToNumber(call(new FctTastrSize() ,null,ctx_,m_,null,st_)).intStruct());
    }
}
