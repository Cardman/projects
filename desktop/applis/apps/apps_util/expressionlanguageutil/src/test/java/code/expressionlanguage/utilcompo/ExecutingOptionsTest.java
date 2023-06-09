package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.*;
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

public final class ExecutingOptionsTest extends EquallableElUtUtil {
    @Test
    public void lines1() {
        StringList l_ = ExecutingOptions.lines(";\n\n");
        assertEq(1,l_.size());
        assertEq(";",l_.get(0));
    }
    @Test
    public void defComments1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        update(pr_);
        CustList<CommentDelimiters> l_ = CustAliases.defComments(FileInfos.EN,pr_.getTranslations(),FileInfos.EN);
        assertEq(4,l_.size());
    }
    @Test
    public void defComments2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        update(pr_);
        CustList<CommentDelimiters> l_ = CustAliases.defComments("",pr_.getTranslations(),FileInfos.EN);
        assertEq(4,l_.size());
    }
    @Test
    public void setupOptionals1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String logFolder_ = exec_.getLogFolder();
        String mainThread_ = exec_.getMainThread();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("log=_"));
        assertEq(logFolder_,exec_.getLogFolder());
        assertEq(mainThread_,exec_.getMainThread());
    }
    @Test
    public void setupOptionals2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("log=0>1"));
        assertEq("0",exec_.getLogFolder());
        assertEq("1",exec_.getMainThread());
    }
    @Test
    public void setupOptionals3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("lgs=0,,1"));
        assertEq(2,exec_.getLgs().size());
        assertEq("0",exec_.getLgs().get(0));
        assertEq("1",exec_.getLgs().get(1));
        exec_.setLg("");
        assertEq("",exec_.getLg());
        exec_.setAccess("");
        assertEq("",exec_.getAccess());
    }
    @Test
    public void setupOptionals4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("comments=\\\\=,=\\\\;\\\\-,-\\\\"));
        assertEq(2,opt_.getComments().size());
        assertEq("\\=",opt_.getComments().get(0).getBegin());
        assertEq("=\\",opt_.getComments().get(0).getEnd().get(0));
        assertEq("\\-",opt_.getComments().get(1).getBegin());
        assertEq("-\\",opt_.getComments().get(1).getEnd().get(0));
    }
    @Test
    public void setupOptionals5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("args="));
        assertTrue(exec_.isHasArg());
        assertEq(1,exec_.getArgs().size());
        assertEq("",exec_.getArgs().get(0));
    }
    @Test
    public void setupOptionals6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("args=a\\ b c"));
        assertTrue(exec_.isHasArg());
        assertEq(2,exec_.getArgs().size());
        assertEq("a b",exec_.getArgs().get(0));
        assertEq("c",exec_.getArgs().get(1));
    }
    @Test
    public void setupOptionals7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("invokeDirect="));
        assertTrue(exec_.isInvokeDirect());
    }
    @Test
    public void setupOptionals8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        int tabWidth_ = opt_.getTabWidth();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("tabWidth=-1"));
        assertEq(tabWidth_,opt_.getTabWidth());
    }
    @Test
    public void setupOptionals9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("tabWidth=1"));
        assertEq(1,opt_.getTabWidth());
    }
    @Test
    public void setupOptionals10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String tabWidth_ = exec_.getOutputZip();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("out="));
        assertEq(tabWidth_,exec_.getOutputZip());
    }
    @Test
    public void setupOptionals11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("out=0"));
        assertEq("0",exec_.getOutputZip());
    }
    @Test
    public void setupOptionals12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("out=0/"));
        assertEq("0",exec_.getOutputZip());
    }
    @Test
    public void setupOptionals13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String tabWidth_ = exec_.getFiles();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("files="));
        assertEq(tabWidth_,exec_.getFiles());
    }
    @Test
    public void setupOptionals14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("files=0/"));
        assertEq("0",exec_.getFiles());
    }
    @Test
    public void setupOptionals15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String tabWidth_ = exec_.getResources();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("res="));
        assertEq(tabWidth_,exec_.getResources());
    }
    @Test
    public void setupOptionals16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("res=0/"));
        assertEq("0",exec_.getResources());
    }
    @Test
    public void setupOptionals17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("keyWords=Key","keyWords=If=0,KeyElse=","keyWords=1,","keyWords=KeyElseIf=","keyWords=2,","keyWords=KeyElsIf","keyWords==3","keyWords=,","keyWords=KeyElIf","keyWords==4"));
        assertEq(5,exec_.getKeyWords().size());
        assertEq("0",exec_.getKeyWords().getVal("KeyIf"));
        assertEq("1",exec_.getKeyWords().getVal("KeyElse"));
        assertEq("2",exec_.getKeyWords().getVal("KeyElseIf"));
        assertEq("3",exec_.getKeyWords().getVal("KeyElsIf"));
        assertEq("4",exec_.getKeyWords().getVal("KeyElIf"));
    }
    @Test
    public void setupOptionals18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("aliases=Key","aliases=If=0,KeyElse=","aliases=1,","aliases=KeyElseIf=","aliases=2,","aliases=KeyElsIf","aliases==3","aliases=,","aliases=KeyElIf","aliases==4"));
        assertEq(5,exec_.getAliases().size());
        assertEq("0",exec_.getAliases().getVal("KeyIf"));
        assertEq("1",exec_.getAliases().getVal("KeyElse"));
        assertEq("2",exec_.getAliases().getVal("KeyElseIf"));
        assertEq("3",exec_.getAliases().getVal("KeyElsIf"));
        assertEq("4",exec_.getAliases().getVal("KeyElIf"));
    }
    @Test
    public void setupOptionals19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("messages=Key","messages=If=0,KeyElse=","messages=1,","messages=KeyElseIf=","messages=2,","messages=KeyElsIf","messages==3","messages=,","messages=KeyElIf","messages==4"));
        assertEq(5,exec_.getMessages().size());
        assertEq("0",exec_.getMessages().getVal("KeyIf"));
        assertEq("1",exec_.getMessages().getVal("KeyElse"));
        assertEq("2",exec_.getMessages().getVal("KeyElseIf"));
        assertEq("3",exec_.getMessages().getVal("KeyElsIf"));
        assertEq("4",exec_.getMessages().getVal("KeyElIf"));
    }
    @Test
    public void setupOptionals20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("warn=","warn=0,","warn=1,","warn=","warn=2,","warn=","warn=3","warn=,","warn=","warn=4"));
        assertEq(5,exec_.getWarns().size());
        assertEq("0",exec_.getWarns().get(0));
        assertEq("1",exec_.getWarns().get(1));
        assertEq("2",exec_.getWarns().get(2));
        assertEq("3",exec_.getWarns().get(3));
        assertEq("4",exec_.getWarns().get(4));
    }

    @Test
    public void setupOptionals21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("classes=","classes=0 ","classes=1 ","classes=","classes=2 ","classes=","classes=3","classes= ","classes=","classes=4"));
        assertEq(5,opt_.getTypesInit().size());
        assertEq("0",opt_.getTypesInit().get(0));
        assertEq("1",opt_.getTypesInit().get(1));
        assertEq("2",opt_.getTypesInit().get(2));
        assertEq("3",opt_.getTypesInit().get(3));
        assertEq("4",opt_.getTypesInit().get(4));
    }
    @Test
    public void setupOptionals22() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("impl="));
        assertTrue(opt_.getOptionsReport().isDisplayImplicit());
    }
    @Test
    public void setupOptionals23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("seed=0.5"));
        assertEq("0.5",opt_.getSeedElts());
    }
    @Test
    public void setupOptionals24() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String srcFolder_ = exec_.getSrcFolder();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("src="));
        assertEq(srcFolder_,exec_.getSrcFolder());
        assertTrue(opt_.isCovering());
        assertTrue(exec_.isCovering());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals25() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("src=0"));
        assertEq("0",exec_.getSrcFolder());
        assertTrue(opt_.isCovering());
        assertTrue(exec_.isCovering());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals26() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String coverFolder_ = exec_.getCoverFolder();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("cover="));
        assertEq(coverFolder_,exec_.getCoverFolder());
        assertTrue(opt_.isCovering());
        assertTrue(exec_.isCovering());
    }
    @Test
    public void setupOptionals27() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("cover=0"));
        assertEq("0",exec_.getCoverFolder());
        assertTrue(opt_.isCovering());
        assertTrue(exec_.isCovering());
    }
    @Test
    public void setupOptionals28() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        String errFolder_ = exec_.getErrorsFolder();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("err="));
        assertEq(errFolder_,exec_.getErrorsFolder());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals29() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("err=0"));
        assertEq("0",exec_.getErrorsFolder());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals30() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("files=0"));
        assertEq("0",exec_.getFiles());
    }
    @Test
    public void setupOptionals31() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("res=0"));
        assertEq("0",exec_.getResources());
    }
    @Test
    public void setupOptionals32() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("src=0/"));
        assertEq("0",exec_.getSrcFolder());
        assertTrue(opt_.isCovering());
        assertTrue(exec_.isCovering());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals33() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_, new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines());
        assertFalse(opt_.isCovering());
        assertFalse(exec_.isCovering());
        assertFalse(opt_.isGettingErrors());
        assertFalse(opt_.getOptionsReport().isDisplayImplicit());
        assertFalse(opt_.getOptionsReport().isDisplayImplicitLabel());
        assertFalse(exec_.isHasArg());
        assertFalse(exec_.isInvokeDirect());
        assertEq(0,exec_.getMessages().size());
        assertEq(0,exec_.getWarns().size());
        assertEq(0,exec_.getKeyWords().size());
        assertEq(0,exec_.getAliases().size());
        assertEq(0,exec_.getArgs().size());
        assertEq(0,opt_.getTypesInit().size());
    }
    @Test
    public void setupOptionals34() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("impl_label="));
        assertTrue(opt_.getOptionsReport().isDisplayImplicitLabel());
    }
    @Test
    public void argsSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arg_ = new ArrayStruct(1, "");
        arg_.set(0,new StringStruct("arg"));
        call(new FctArgsSet(),null,ctx_,null,one(arg_),st_);
        StringList a_ = ((RunnableContextEl) ctx_).getArgs();
        assertEq(1,a_.size());
        assertEq("arg",a_.get(0));
    }
    @Test
    public void argsGet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arg_ = new ArrayStruct(1, "");
        arg_.set(0,new StringStruct("arg"));
        call(new FctArgsSet(),null,ctx_,null,one(arg_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctArgsGet(),null,ctx_,null,null,st_);
        assertEq(1,a_.getLength());
        assertEq("arg",a_.get(0));
    }
    @Test
    public void coverage() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasTextFieldAuto(stds_.getGuiAliases().getAliasTextFieldAuto());
        stds_.getGuiAliases().setAliasTextFieldAddDocument(stds_.getGuiAliases().getAliasTextFieldAddDocument());
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        Struct arr_ = call(new FctCoverage(""), null, ctx_, null, null, st_);
        assertEq(3,((ArrayStruct)arr_).getLength());
        assertEq("src/sample.txt.html", call(new FctEntryTextName(),null,ctx_,((ArrayStruct) arr_).get(0),null,st_));
        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{}</span></pre></body></html>", call(new FctEntryTextValue(),null,ctx_,((ArrayStruct) arr_).get(0),null,st_));
        assertEq(6,toLong(call(new FctEntryTextTime0(),null,ctx_,((ArrayStruct) arr_).get(0),null,st_)));
    }
    @Test
    public void interrupt1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctInterrupt(),null,ctx_,null,null,st_);
        assertTrue(((RunnableContextEl)ctx_).getInterrupt().get());
    }
    @Test
    public void interrupt2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        AbsPlainButton b_ = pr_.getCompoFactory().newPlainButton("");
        b_.setEnabled(false);
        stds_.getGuiExecutingBlocks().setStop(b_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctInterrupt(),null,ctx_,null,null,st_);
        assertTrue(((RunnableContextEl)ctx_).getInterrupt().get());
        assertTrue(b_.isEnabled());
    }
    @Test
    public void invokeLater1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void invokeLater2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void invokeLater3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ((RunnableContextEl)ctx_).getExecutingOptions().setInvokeDirect(true);
        StackCall st_ = stack(ctx_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(list_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void invokeLater4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ((RunnableContextEl)ctx_).getExecutingOptions().setInvokeDirect(false);
        StackCall st_ = stack(ctx_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(list_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    private static StringList lines(String..._lines) {
        return new StringList(_lines);
    }

}
