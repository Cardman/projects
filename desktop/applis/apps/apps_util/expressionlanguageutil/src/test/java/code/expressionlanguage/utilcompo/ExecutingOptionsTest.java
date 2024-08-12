package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("log=0>1"));
        assertEq("0",exec_.getLogFolder());
        assertEq("1",exec_.getMainThread());
    }
    @Test
    public void setupOptionals3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("args="));
        assertTrue(exec_.isHasArg());
        assertEq(1,exec_.getArgs().size());
        assertEq("",exec_.getArgs().get(0));
    }
    @Test
    public void setupOptionals6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("invokeDirect="));
        assertTrue(exec_.isInvokeDirect());
    }
    @Test
    public void setupOptionals8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        int tabWidth_ = opt_.getTabWidth();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("tabWidth=-1"));
        assertEq(tabWidth_,opt_.getTabWidth());
    }
    @Test
    public void setupOptionals9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("tabWidth=1"));
        assertEq(1,opt_.getTabWidth());
    }
    @Test
    public void setupOptionals10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        String tabWidth_ = exec_.getOutputZip();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("out="));
        assertEq(tabWidth_,exec_.getOutputZip());
    }
    @Test
    public void setupOptionals11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("out=0"));
        assertEq("0",exec_.getOutputZip());
    }
    @Test
    public void setupOptionals12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("out=0/"));
        assertEq("0",exec_.getOutputZip());
    }
    @Test
    public void setupOptionals13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        String tabWidth_ = exec_.getFiles();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("files="));
        assertEq(tabWidth_,exec_.getFiles());
    }
    @Test
    public void setupOptionals14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("files=0/"));
        assertEq("0",exec_.getFiles());
    }
    @Test
    public void setupOptionals15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        String tabWidth_ = exec_.getResources();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("res="));
        assertEq(tabWidth_,exec_.getResources());
    }
    @Test
    public void setupOptionals16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("res=0/"));
        assertEq("0",exec_.getResources());
    }
    @Test
    public void setupOptionals17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("impl="));
        assertTrue(opt_.getOptionsReport().isDisplayImplicit());
    }
    @Test
    public void setupOptionals23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("seed=0.5"));
        assertEq("0.5",opt_.getSeedElts());
    }
    @Test
    public void setupOptionals24() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("cover=0"));
        assertEq("0",exec_.getCoverFolder());
        assertTrue(opt_.isCovering());
        assertTrue(exec_.isCovering());
    }
    @Test
    public void setupOptionals28() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        String errFolder_ = exec_.getErrorsFolder();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("err="));
        assertEq(errFolder_,exec_.getErrorsFolder());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals29() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("err=0"));
        assertEq("0",exec_.getErrorsFolder());
        assertTrue(opt_.isGettingErrors());
    }
    @Test
    public void setupOptionals30() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("files=0"));
        assertEq("0",exec_.getFiles());
    }
    @Test
    public void setupOptionals31() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
        Options opt_ = new Options();
        ExecutingOptions.setupOptionals(0,opt_,exec_, lines("res=0"));
        assertEq("0",exec_.getResources());
    }
    @Test
    public void setupOptionals32() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_, new MockInterceptor()));
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
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setListGenerator(new CdmFactory(pr_,new MockInterceptor()));
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
        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasTextFieldAuto(stds_.getGuiAliases().getAliasTextFieldAuto());
        stds_.getGuiAliases().setAliasTextFieldAddDocument(stds_.getGuiAliases().getAliasTextFieldAddDocument());
        EventStruct.setupThread((RunnableContextEl) ctx_);
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
        AbsButton b_ = pr_.getCompoFactory().newPlainButton("");
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void invokeLater2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void invokeLater3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ((RunnableContextEl)ctx_).getExecutingOptions().setInvokeDirect(true);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct list_ = new MockRunnableStruct("");
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(list_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        ((MockThreadFactory)pr_.getThreadFactory()).getAllThreads().get(0).join();
        assertTrue(list_.isStarted());
    }
    @Test
    public void invokeLater4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ((RunnableContextEl)ctx_).getExecutingOptions().setInvokeDirect(false);
        StackCall st_ = stack(ctx_);
        MockRunnableStruct list_ = new MockRunnableStruct("");
        call(new FctCompoInvokeLater(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(list_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(0,((MockThreadFactory)pr_.getThreadFactory()).getAllThreads().size());
        assertTrue(list_.isStarted());
    }
    @Test
    public void buffer1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctCompoBuffer0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void buffer2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctCompoBuffer1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void buffer3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertFalse(call(new FctCompoBuffer1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq("",call(new FctCompoBuffer0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,null,st_));
    }
    @Test
    public void buffer4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertTrue(call(new FctCompoBuffer1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,one(new StringStruct("_")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq("_",call(new FctCompoBuffer0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,null,st_));
    }
    @Test
    public void selectDbg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Component.invokeLater(()->{Sample.id(2);});}public static int id(int i){return i;}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 128);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(6,dbg_.getStack().nbPages());
        assertEq(128,dbg_.getStack().getCall(5).getTraceIndex());
        assertEq(83,dbg_.getStack().getCall(4).getTraceIndex());
        assertEq(59,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void selectDbg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Component.invokeLater(null);}public static int id(int i){return i;}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    private StackCallReturnValue launchDbg(ResultContext _ctx) {
        ExecRootBlock ex_ = _ctx.getContext().getClasses().getClassBody("pkg.Sample");
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ExecClassesUtil.tryInitStaticlyTypes(_ctx.getContext(), _ctx.getForwards().getOptions(), null, new CustomFoundMethod(form_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), new Parameters()), StepDbgActionEnum.DEBUG, false);
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, false);
    }
    private ResultContext ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().runnableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass component_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasComponent(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        component_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StringList params_ = new StringList(_definedLgNames.getExecContent().getCustAliases().getAliasRunnable());
        StandardMethod method_ = new StandardMethod(_definedLgNames.getGuiAliases().getAliasComponentInvokeLater(), params_, _definedLgNames.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList("a"), new FctCompoInvokeLater(_definedLgNames.getExecContent().getCustAliases(),_definedLgNames.getGuiExecutingBlocks(), ""));
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getGuiAliases().getAliasComponent(), component_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getExecContent().getExecutingBlocks().runnable(stds_.getExecContent().getCustAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
    private static StringList lines(String..._lines) {
        return new StringList(_lines);
    }

}
