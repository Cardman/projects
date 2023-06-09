package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ColorStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor0(), null, ctx_, null, one(new IntStruct(8)), st_);
        assertEq(-16777208, ((ColorStruct)c_).getColor());
        assertEq(-16777208, c_.randCode());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor0(), null, ctx_, null, one(new IntStruct(8+256*256*256)), st_);
        assertEq(-16777208, ((ColorStruct)c_).getColor());
        assertEq(-16777208, c_.randCode());
    }

    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor1(), null, ctx_, null, two(new IntStruct(8),BooleanStruct.of(false)), st_);
        assertEq(-16777208, ((ColorStruct)c_).getColor());
        assertEq(-16777208, c_.randCode());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor1(), null, ctx_, null, two(new IntStruct(8),BooleanStruct.of(true)), st_);
        assertEq(8, ((ColorStruct)c_).getColor());
        assertEq(8, c_.randCode());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor2(), null, ctx_, null, three(new IntStruct(0),new IntStruct(0),new IntStruct(8)), st_);
        assertEq(-16777208, ((ColorStruct)c_).getColor());
        assertEq(-16777208, c_.randCode());
    }
    @Test
    public void init6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(0),new IntStruct(0),new IntStruct(8),new IntStruct(255)), st_);
        assertEq(-16777208, ((ColorStruct)c_).getColor());
        assertEq(-16777208, c_.randCode());
    }
    @Test
    public void red() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(31)), st_);
        assertEq(37,toLong(call(new FctColorRed(),null,ctx_,c_,null,st_)));
    }
    @Test
    public void green() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(31)), st_);
        assertEq(17,toLong(call(new FctColorGreen(),null,ctx_,c_,null,st_)));
    }
    @Test
    public void blue() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(31)), st_);
        assertEq(23,toLong(call(new FctColorBlue(),null,ctx_,c_,null,st_)));
    }
    @Test
    public void alpha() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(31)), st_);
        assertEq(31,toLong(call(new FctColorAlpha(),null,ctx_,c_,null,st_)));
    }
    @Test
    public void transparent1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(0)), st_);
        assertTrue(call(new FctColorTransparent(),null,ctx_,c_,null,st_));
    }
    @Test
    public void transparent2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(255)), st_);
        assertFalse(call(new FctColorTransparent(),null,ctx_,c_,null,st_));
    }
    @Test
    public void ref1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(255)), st_);
        assertFalse(c_.sameReference(NullStruct.NULL_VALUE));
    }
    @Test
    public void ref2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(255)), st_);
        Struct c2_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(23),new IntStruct(17),new IntStruct(255)), st_);
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
        Struct c_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(255)), st_);
        Struct c2_ = call(new FctColor3(), null, ctx_, null, four(new IntStruct(37),new IntStruct(17),new IntStruct(23),new IntStruct(255)), st_);
        assertTrue(c_.sameReference(c2_));
    }
}
