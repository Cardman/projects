package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class FontStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new DfFont(),null,ctx_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctFont0(),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctFont1(),null,ctx_,null,one(new IntStruct(13)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctFont2(),null,ctx_,null,four(new StringStruct("_"),BooleanStruct.of(true),BooleanStruct.of(true),new IntStruct(13)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void family() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct f_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertEq("_",call(new FctFontGetName(),null,ctx_,f_,null,st_));
    }
    @Test
    public void bold1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct f_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertFalse(call(new FctFontIsBold(),null,ctx_,f_,null,st_));
    }
    @Test
    public void bold2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct f_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(false), new IntStruct(13)), st_);
        assertTrue(call(new FctFontIsBold(),null,ctx_,f_,null,st_));
    }
    @Test
    public void italic1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct f_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(false), new IntStruct(13)), st_);
        assertFalse(call(new FctFontIsItalic(),null,ctx_,f_,null,st_));
    }
    @Test
    public void italic2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct f_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertTrue(call(new FctFontIsItalic(),null,ctx_,f_,null,st_));
    }
    @Test
    public void size() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct f_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertEq(13,toLong(call(new FctFontGetSize(),null,ctx_,f_,null,st_)));
    }
    @Test
    public void ref1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(NullStruct.NULL_VALUE));
        c_.randCode();
    }
    @Test
    public void ref2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(17)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(false), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("__"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertTrue(c_.sameReference(c2_));
    }
    @Test
    public void ref7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(17)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(true), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(true), BooleanStruct.of(false), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("__"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        assertFalse(c_.sameReference(c2_));
    }
    @Test
    public void ref11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct c2_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        assertTrue(c_.sameReference(c2_));
    }
    @Test
    public void strWidth1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
//        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        call(new FctImageSetFont(),null,ctx_,img_,one(NullStruct.NULL_VALUE),st_);
        call(new FctFontStringWidth0(stds_.getGuiExecutingBlocks()),null,ctx_,null,two(img_,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void strWidth2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        call(new FctImageSetFont(),null,ctx_,img_,one(c_),st_);
        call(new FctFontStringWidth0(stds_.getGuiExecutingBlocks()),null,ctx_,null,two(img_,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void strWidth3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        call(new FctImageSetFont(),null,ctx_,img_,one(c_),st_);
        call(new FctFontStringWidth0(stds_.getGuiExecutingBlocks()),null,ctx_,null,two(img_,new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void strWidth4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctFont2(), null, ctx_, null, four(new StringStruct("_"), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(13)), st_);
        call(new FctFontStringWidth1(stds_.getGuiExecutingBlocks()),null,ctx_,c_,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void strWidth5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctFontStringWidth0(stds_.getGuiExecutingBlocks()),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
}
