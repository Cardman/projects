package code.expressionlanguage.guicompos;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecClassBlock;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.FctCompoRepaint;
import code.expressionlanguage.guicompos.stds.FctImage;
import code.expressionlanguage.guicompos.stds.FctImageLabel1;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.gui.CdmFactory;
import code.gui.EquallableElUtUtil;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockProgramInfos;
import code.sml.util.TranslationsFile;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.IntMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class GuiAliasesTest extends EquallableElUtUtil {

//    @Test
//    public void cst1() {
//        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
//        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
//        stds_.getGuiAliases().setAliasConfirm("_");
//        stds_.getGuiAliases().setAliasConfirmFieldOk("_");
//        assertEq(OtherConfirmDialog.OK_OPTION,toLong(new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
//    }
//
//    @Test
//    public void cst2() {
//        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
//        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
//        stds_.getGuiAliases().setAliasConfirm("_");
//        stds_.getGuiAliases().setAliasConfirmFieldOk("");
//        stds_.getGuiAliases().setAliasConfirmFieldYes("_");
//        assertEq(OtherConfirmDialog.YES_OPTION,toLong(new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
//    }
//
//    @Test
//    public void cst3() {
//        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
//        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
//        stds_.getGuiAliases().setAliasConfirm("_");
//        stds_.getGuiAliases().setAliasConfirmFieldOk("");
//        stds_.getGuiAliases().setAliasConfirmFieldYes("");
//        stds_.getGuiAliases().setAliasConfirmFieldNo("_");
//        assertEq(OtherConfirmDialog.NO_OPTION,toLong(new AdvancedExecConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
//    }
//
//    @Test
//    public void cst4() {
//        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
//        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
//        stds_.getGuiAliases().setAliasConfirm("_");
//        stds_.getGuiAliases().setAliasConfirmFieldOk("");
//        stds_.getGuiAliases().setAliasConfirmFieldYes("");
//        stds_.getGuiAliases().setAliasConfirmFieldNo("");
//        stds_.getGuiAliases().setAliasConfirmFieldCancel("_");
//        assertEq(OtherConfirmDialog.CANCEL_OPTION,toLong(new AdvancedExecConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
//    }

    @Test
    public void cst5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("_");
        assertEq(PanelBorderStruct.NORTH,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }

    @Test
    public void cst6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("_");
        assertEq(PanelBorderStruct.SOUTH,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }

    @Test
    public void cst7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("_");
        assertEq(PanelBorderStruct.WEST,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }

    @Test
    public void cst8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("");
        stds_.getGuiAliases().setAliasPanelBorderEast("_");
        assertEq(PanelBorderStruct.EAST,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }

    @Test
    public void cst9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("");
        stds_.getGuiAliases().setAliasPanelBorderEast("");
        stds_.getGuiAliases().setAliasPanelBorderCenter("_");
        assertEq(PanelBorderStruct.CENTER,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void cst10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("");
        stds_.getGuiAliases().setAliasPanelBorderEast("");
        stds_.getGuiAliases().setAliasPanelBorderCenter("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeFirst("_");
        assertEq(PanelBorderStruct.BEFORE_FIRST_LINE,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void cst11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("");
        stds_.getGuiAliases().setAliasPanelBorderEast("");
        stds_.getGuiAliases().setAliasPanelBorderCenter("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeFirst("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeLineBegins("_");
        assertEq(PanelBorderStruct.BEFORE_LINE_BEGINS,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void cst12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("");
        stds_.getGuiAliases().setAliasPanelBorderEast("");
        stds_.getGuiAliases().setAliasPanelBorderCenter("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeFirst("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeLineBegins("");
        stds_.getGuiAliases().setAliasPanelBorderAfterLineEnds("_");
        assertEq(PanelBorderStruct.AFTER_LINE_ENDS,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void cst13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("");
        stds_.getGuiAliases().setAliasPanelBorderSouth("");
        stds_.getGuiAliases().setAliasPanelBorderWest("");
        stds_.getGuiAliases().setAliasPanelBorderEast("");
        stds_.getGuiAliases().setAliasPanelBorderCenter("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeFirst("");
        stds_.getGuiAliases().setAliasPanelBorderBeforeLineBegins("");
        stds_.getGuiAliases().setAliasPanelBorderAfterLineEnds("");
        stds_.getGuiAliases().setAliasPanelBorderAfterLast("_");
        assertEq(PanelBorderStruct.AFTER_LAST_LINE,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void cst14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("");
        stds_.getNbAlias().setAliasBoolean("1");
        stds_.getNbAlias().setAliasByte("2");
        stds_.getNbAlias().setAliasCharacter("3");
        stds_.getNbAlias().setAliasShort("4");
        stds_.getNbAlias().setAliasInteger("5");
        stds_.getNbAlias().setAliasLong("6");
        stds_.getNbAlias().setAliasFloat("7");
        stds_.getNbAlias().setAliasDouble("8");
        stds_.getNbAlias().setAliasMinValueField("1");
        stds_.getNbAlias().setAliasMaxValueField("2");
        stds_.getNbAlias().setAliasMinusInfinityField("3");
        stds_.getNbAlias().setAliasPlusInfinityField("4");
        stds_.getNbAlias().setAliasNanField("5");
        stds_.getNbAlias().setAliasAlpha("6");
        stds_.getNbAlias().setAliasAlphaHex("7");
        assertEq(Double.MAX_VALUE,new AdvancedExecConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void str1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        assertEq("",stds_.getStringOfObject(ctx_,ev_).getInstance());
    }
    @Test
    public void str2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ExecEnumBlock ex_ = new ExecEnumBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC);
        ctx_.getClasses().getClassesBodies().addEntry("",ex_);
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_, ""), "__", -1);
        assertEq("__",stds_.getStringOfObject(ctx_,ev_).getInstance());
    }
    @Test
    public void str3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getNbAlias().setAliasInteger("_");
        assertEq(stds_.getNbAlias().getAliasInteger(),stds_.getStringOfObject(ctx_,new IntStruct(1)).getInstance());
    }
    @Test
    public void str4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSampleLight(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        assertEq("",stds_.getStringOfObject(ctx_,ev_).getInstance());
    }
    @Test
    public void str5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSampleLight(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ExecEnumBlock ex_ = new ExecEnumBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC);
        ctx_.getClasses().getClassesBodies().addEntry("",ex_);
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_, ""), "__", -1);
        assertEq("__",stds_.getStringOfObject(ctx_,ev_).getInstance());
    }
    @Test
    public void str6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSampleLight(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getNbAlias().setAliasInteger("_");
        assertEq(stds_.getNbAlias().getAliasInteger(),stds_.getStringOfObject(ctx_,new IntStruct(1)).getInstance());
    }
    @Test
    public void str7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSampleLight(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        ExecEnumBlock ex_ = new ExecEnumBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC);
        ExecInnerElementBlock exInn_ = new ExecInnerElementBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC,new ExecElementContent(new AnaElementContent(new EnumBlock(0,"","",new OffsetAccessInfo(0,AccessEnum.PUBLIC),"",new IntMap<String>(),0),new OffsetStringInfo(0,""),new OffsetStringInfo(0,""),new OffsetStringInfo(0,""))),0);
        ctx_.getClasses().getClassesBodies().addEntry("",exInn_);
        ctx_.getClasses().getClassesBodies().addEntry("_",ex_);
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_, ""), "__", -1);
        assertEq("__",stds_.getStringOfObject(ctx_,ev_).getInstance());
    }
    @Test
    public void paint() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        update(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxPaint(pr_, files_);
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(((LgNamesGui)ctx_.getStandards()).getExecContent().getCustAliases(), ((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoRepaint(((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks(), ""),null,ctx_,i_,null,st_);
        assertFalse(st_.isFailInit());
    }

    @Test
    public void success() {
        AnalyzedPageEl a_ = buildTmp();
        assertTrue(a_.isEmptyStdError());
    }
    @Test
    public void print() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        MathAdvAliases.en(en_);
        StringMap<String> mapp_ = TranslationsFile.extractMap(en_);
        StringMap<String> keys_ = TranslationsFile.extractKeys(en_);
        stds_.getExecContent().getCustAliases().getMathAdvAliases().build(mapp_,new StringMap<String>(), keys_);
        stds_.getCoreNames().setObjType(new StandardClass("_._",new CustList<CstFieldInfo>(),new CustList<StandardConstructor>(),new CustList<StandardMethod>(),"", MethodModifier.NORMAL));
        stds_.getContent().getCharSeq().setAliasString("");
        Options opt_ = new Options();
        ContextEl c_ = new AdvContextGenerator(new ConcreteBoolean()).geneWith(getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        assertFalse(StringUtil.nullToEmpty(stds_.getStrAlias().getAliasStringSegment()+"_").isEmpty());
        assertFalse(StringUtil.nullToEmpty(stds_.getAliasLgInt()+"_").isEmpty());
        assertFalse(StringUtil.nullToEmpty(stds_.getAliasRate()+"_").isEmpty());
        ((InterruptibleContextEl)c_).stopJoinSleep();
        assertTrue(c_.callsOrException(st_));
    }
    private ContextEl ctxPaint(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(), StringUtil.EN);
        Options opt_ = new Options();
        return buildMockPaint(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }
    public static ResultContext buildMockPaint(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        String header_ = _definedKw.getKeyWordPublic() +" "+ _definedKw.getKeyWordAbstract()+" "+ _definedKw.getKeyWordFinal()+" "+ _definedKw.getKeyWordClass()+" "+ _definedLgNames.getGuiAliases().getAliasPaint() +" {\n";
        s_.addEntry("0", header_+_definedLgNames.getGuiAliases().paintMethod(_definedKw, _definedLgNames.getContent())+"}");
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasComponent(),fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getStandards(), _definedLgNames.getGuiAliases().getAliasComponent(), stdcl_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        _definedLgNames.getGuiExecutingBlocks().initEventClose((GuiContextEl) res_.getContext());
        _definedLgNames.getGuiExecutingBlocks().paintMethod(_definedLgNames.getGuiAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),StringUtil.EN);
        return stds_;
    }
    public AnalyzedPageEl buildTmp() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        KeyWords kw_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        stds_.getExecContent().updateTranslations(e_.getLightProgramInfos().getTranslations(),e_.getLightProgramInfos().getLanguage(),StringUtil.EN);
        stds_.getExecContent().getCustAliases().messages(mess_, e_.getMessages());
        stds_.getExecContent().getCustAliases().keyWord(kw_, e_.getKeyWords());
        kw_.initSupplDigits();
        stds_.getExecContent().getCustAliases().otherAlias(stds_.getContent(), e_.getAliases());
        StringMap<String> keys_ = LgNamesGui.extractAliasesKeys(stds_.getExecContent().getCustAliases());
        stds_.getGuiAliases().otherAliasGui(LgNamesGui.addon(stds_.getExecContent().getCustAliases()),e_.getAliases(),keys_);
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), e_.getLightProgramInfos());
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(new AdvSymbolFactory(stds_.getExecContent().getCustAliases().getMathAdvAliases()));
        StringMap<String> m_ = stds_.getExecContent().getCustAliases().extractAliasesKeys();
        m_.addAllEntries(LgNamesGui.extractAliasesKeys(stds_.getExecContent().getCustAliases()));
        page_.setMappingAliases(m_);
        page_.setMappingKeyWords(stds_.getExecContent().getCustAliases().extractKeywordsKeys());
        AbstractFileBuilder fileBuilder_ = new GuiFileBuilderFactory(stds_).build();
        Forwards forwards_ = new Forwards(stds_, stds_.getExecContent(), fileBuilder_, opt_);
        forwards_.getResources().addAllEntries(files_);
        page_.setLogErr(forwards_);
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        ContextFactory.validateStds(new AnalysisElementsBase(forwards_,mess_, kw_, stds_.getExecContent().getCustAliases().defComments(), opt_, stds_.getContent(), page_));
        return page_;
    }

    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }
}
