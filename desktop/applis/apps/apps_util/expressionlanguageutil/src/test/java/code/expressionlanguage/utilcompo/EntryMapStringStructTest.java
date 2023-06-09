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

public final class EntryMapStringStructTest extends EquallableElUtUtil {
    @Test
    public void key() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        assertTrue(new StringStruct("").sameReference(call(new FctTrenteKey(),null,ctx_,entry_,null,st_)));
    }
    @Test
    public void value1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        assertTrue(new StringStruct("_").sameReference(call(new FctTrenteValue0(),null,ctx_,entry_,null,st_)));
    }
    @Test
    public void valueFail1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        st_.setFullStack(new DefaultFullStack(ctx_));
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(m_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        call(new FctTrenteValue1(),null,ctx_,entry_,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void valueFail2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        call(new FctTrenteValue1(),null,ctx_,entry_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void value2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        assertTrue(new StringStruct("_").sameReference(call(new FctTrenteValue1(),null,ctx_,entry_,one(new StringStruct("__")),st_)));
        assertTrue(new StringStruct("__").sameReference(call(new FctTrenteValue0(),null,ctx_,entry_,null,st_)));
    }
    @Test
    public void owner() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        assertSame(m_,call(new FctTrenteOwner(),null,ctx_,entry_,null,st_));
    }
}
