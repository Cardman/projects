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

public final class ImageStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        assertEq(2, ((ImageStruct)img_).getImage().getWidth());
        assertEq(3, ((ImageStruct)img_).getImage().getHeight());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertEq(2, ((ImageStruct)img_).getImage().getWidth());
        assertEq(3, ((ImageStruct)img_).getImage().getHeight());
    }
    @Test
    public void width() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertEq(2, toLong(call(new FctImageGetWidth(),null,ctx_,img_,null,st_)));
    }
    @Test
    public void height() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertEq(3, toLong(call(new FctImageGetHeight(),null,ctx_,img_,null,st_)));
    }
    @Test
    public void isWithAlpha1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        assertTrue(call(new FctImageIsWithAlpha(),null,ctx_,img_,null,st_));
    }
    @Test
    public void isWithAlpha2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertFalse(call(new FctImageIsWithAlpha(),null,ctx_,img_,null,st_));
    }
    @Test
    public void pixel1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(img_);
        call(new FctImageSet(),null,ctx_,img_,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void pixel2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctImageSet(),null,ctx_,img_,three(new IntStruct(-1),new IntStruct(2),new IntStruct(25)),st_);
        call(new FctImageSet(),null,ctx_,img_,three(new IntStruct(2),new IntStruct(2),new IntStruct(25)),st_);
        call(new FctImageSet(),null,ctx_,img_,three(new IntStruct(1),new IntStruct(-1),new IntStruct(25)),st_);
        call(new FctImageSet(),null,ctx_,img_,three(new IntStruct(1),new IntStruct(3),new IntStruct(25)),st_);
        call(new FctImageSet(),null,ctx_,img_,three(new IntStruct(1),new IntStruct(2),new IntStruct(29)),st_);
        assertEq(-1,toLong(call(new FctImageGet(),null,ctx_,img_,two(new IntStruct(-1),new IntStruct(2)),st_)));
        assertEq(-1,toLong(call(new FctImageGet(),null,ctx_,img_,two(new IntStruct(2),new IntStruct(2)),st_)));
        assertEq(-1,toLong(call(new FctImageGet(),null,ctx_,img_,two(new IntStruct(1),new IntStruct(-1)),st_)));
        assertEq(-1,toLong(call(new FctImageGet(),null,ctx_,img_,two(new IntStruct(1),new IntStruct(3)),st_)));
        assertEq(29,toLong(call(new FctImageGet(),null,ctx_,img_,two(new IntStruct(1),new IntStruct(2)),st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void eq1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertTrue(call(new FctImageEq(),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_));
    }
    @Test
    public void eq2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertFalse(call(new FctImageEq(),null,ctx_,null,two(img_,NullStruct.NULL_VALUE),st_));
    }
    @Test
    public void eq3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertFalse(call(new FctImageEq(),null,ctx_,null,two(NullStruct.NULL_VALUE,img_),st_));
    }
    @Test
    public void eq4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        Struct img2_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertTrue(call(new FctImageEq(),null,ctx_,null,two(img_,img2_),st_));
    }
    @Test
    public void eq5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        Struct img2_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctImageSet(),null,ctx_,img_,three(new IntStruct(1),new IntStruct(2),new IntStruct(29)),st_);
        assertFalse(call(new FctImageEq(),null,ctx_,null,two(img_,img2_),st_));
    }
    @Test
    public void eq6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        Struct img2_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(4), BooleanStruct.of(false)), st_);
        assertFalse(call(new FctImageEq(),null,ctx_,null,two(img_,img2_),st_));
    }
    @Test
    public void eq7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        Struct img2_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(4), new IntStruct(3), BooleanStruct.of(false)), st_);
        assertFalse(call(new FctImageEq(),null,ctx_,null,two(img_,img2_),st_));
    }
    @Test
    public void dispose() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        ArrayStruct a_ = new ArrayStruct(1,"");
        a_.set(0,new IntStruct(0));
        call(new FctImageDrawPolygon(),null,ctx_,img_,two(NullStruct.NULL_VALUE,new ArrayStruct(0,"")),st_);
        call(new FctImageDrawPolygon(),null,ctx_,img_,two(new ArrayStruct(0,""),NullStruct.NULL_VALUE),st_);
        call(new FctImageDrawPolygon(),null,ctx_,img_,two(new ArrayStruct(1,""),new ArrayStruct(2,"")),st_);
        call(new FctImageDrawPolygon(),null,ctx_,img_,two(a_,a_),st_);
        call(new FctImageFillPolygon(),null,ctx_,img_,two(NullStruct.NULL_VALUE,new ArrayStruct(0,"")),st_);
        call(new FctImageFillPolygon(),null,ctx_,img_,two(new ArrayStruct(0,""),NullStruct.NULL_VALUE),st_);
        call(new FctImageFillPolygon(),null,ctx_,img_,two(new ArrayStruct(1,""),new ArrayStruct(2,"")),st_);
        call(new FctImageFillPolygon(),null,ctx_,img_,two(a_,a_),st_);
        call(new FctImageSetColor(),null,ctx_,img_,one(new ColorStruct(0)),st_);
        call(new FctImageSetColor(),null,ctx_,img_,one(NullStruct.NULL_VALUE),st_);
        call(new FctImageGetColor(),null,ctx_,img_,null,st_);
        call(new FctImageSetFont(),null,ctx_,img_,one(new FontStruct()),st_);
        call(new FctImageSetFont(),null,ctx_,img_,one(NullStruct.NULL_VALUE),st_);
        call(new FctImageGetFont(),null,ctx_,img_,null,st_);
        call(new FctImageDraw0(),null,ctx_,img_,three(NullStruct.NULL_VALUE,new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageDraw0(),null,ctx_,img_,three(new ImageStruct(pr_.getImageFactory(), 1,1,false),new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageDraw1(),null,ctx_,img_,three(NullStruct.NULL_VALUE,new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageDraw1(),null,ctx_,img_,three(new StringStruct(""),new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageDrawRect(),null,ctx_,img_,four(new IntStruct(1),new IntStruct(1),new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageFillRect(),null,ctx_,img_,four(new IntStruct(1),new IntStruct(1),new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageDrawOval(),null,ctx_,img_,four(new IntStruct(1),new IntStruct(1),new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageFillOval(),null,ctx_,img_,four(new IntStruct(1),new IntStruct(1),new IntStruct(1),new IntStruct(1)),st_);
        call(new FctImageDrawLine(),null,ctx_,img_,four(new IntStruct(1),new IntStruct(1),new IntStruct(1),new IntStruct(1)),st_);
        StackCall st2_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        st2_.getInitializingTypeInfos().getSensibleFields().add(img_);
        call(new FctImageDrawPolygon(),null,ctx_,img_,null,st2_);
        StackCall st3_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        st3_.getInitializingTypeInfos().getSensibleFields().add(img_);
        call(new FctImageFillPolygon(),null,ctx_,img_,null,st3_);
        StackCall st4_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        st4_.getInitializingTypeInfos().getSensibleFields().add(img_);
        call(new FctImageDispose(),null,ctx_,img_,null,st_);
        call(new FctImageDispose(),null,ctx_,img_,null,st4_);
        assertFalse(st_.isFailInit());
        assertTrue(st2_.isFailInit());
        assertTrue(st3_.isFailInit());
        assertTrue(st4_.isFailInit());
        StackCall st5_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        st5_.getInitializingTypeInfos().getSensibleFields().add(img_);
        call(new FctImageSetColor(),null,ctx_,img_,one(NullStruct.NULL_VALUE),st5_);
        call(new FctImageSetFont(),null,ctx_,img_,one(NullStruct.NULL_VALUE),st5_);
        call(new FctImageDraw0(),null,ctx_,img_,three(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        call(new FctImageDraw1(),null,ctx_,img_,three(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        call(new FctImageDrawRect(),null,ctx_,img_,four(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        call(new FctImageFillRect(),null,ctx_,img_,four(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        call(new FctImageDrawOval(),null,ctx_,img_,four(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        call(new FctImageFillOval(),null,ctx_,img_,four(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        call(new FctImageDrawLine(),null,ctx_,img_,four(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st5_);
        assertTrue(st5_.isFailInit());
    }
    @Test
    public void ret1() {
        assertSame(NullStruct.NULL_VALUE,GuiExecutingBlocks.getReturned(null));
    }
    @Test
    public void ret2() {
        assertEq("_",GuiExecutingBlocks.getReturned("_"));
    }
}
