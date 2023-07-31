package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.AdvSymbolFactory;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.stds.*;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class GuiAliasesTest extends EquallableElUtUtil {

    @Test
    public void cst1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        stds_.getGuiAliases().setAliasConfirm("_");
        stds_.getGuiAliases().setAliasConfirmFieldOk("_");
        assertEq(OtherConfirmDialog.OK_OPTION,toLong(new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
    }

    @Test
    public void cst2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        stds_.getGuiAliases().setAliasConfirm("_");
        stds_.getGuiAliases().setAliasConfirmFieldOk("");
        stds_.getGuiAliases().setAliasConfirmFieldYes("_");
        assertEq(OtherConfirmDialog.YES_OPTION,toLong(new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
    }

    @Test
    public void cst3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        stds_.getGuiAliases().setAliasConfirm("_");
        stds_.getGuiAliases().setAliasConfirmFieldOk("");
        stds_.getGuiAliases().setAliasConfirmFieldYes("");
        stds_.getGuiAliases().setAliasConfirmFieldNo("_");
        assertEq(OtherConfirmDialog.NO_OPTION,toLong(new AdvancedExecConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
    }

    @Test
    public void cst4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        stds_.getGuiAliases().setAliasConfirm("_");
        stds_.getGuiAliases().setAliasConfirmFieldOk("");
        stds_.getGuiAliases().setAliasConfirmFieldYes("");
        stds_.getGuiAliases().setAliasConfirmFieldNo("");
        stds_.getGuiAliases().setAliasConfirmFieldCancel("_");
        assertEq(OtherConfirmDialog.CANCEL_OPTION,toLong(new AdvancedExecConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_"))));
    }

    @Test
    public void cst5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        stds_.getGuiAliases().setAliasConfirm("");
        stds_.getGuiAliases().setAliasPanelBorder("_");
        stds_.getGuiAliases().setAliasPanelBorderNorth("_");
        assertEq(PanelBorderStruct.NORTH,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }

    @Test
    public void cst6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        assertEq(Double.MAX_VALUE,new AdvancedConstantsCalculator(stds_).getInnerSimpleResult(new ClassField("_","_")));
    }
    @Test
    public void str1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        assertEq("",stds_.getStringOfObject(ctx_,ev_).getInstance());
    }
    @Test
    public void str2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
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
        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoRepaint(stds_.getGuiExecutingBlocks(), ""),null,ctx_,i_,null,st_);
        assertFalse(st_.isFailInit());
    }

    @Test
    public void success() {
        AnalyzedPageEl a_ = buildTmp();
        assertTrue(a_.isEmptyStdError());
    }

    public AnalyzedPageEl buildTmp() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        KeyWords kw_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        stds_.getExecContent().updateTranslations(e_.getLightProgramInfos().getTranslations(),e_.getLightProgramInfos().getLanguage(),"en");
        stds_.getExecContent().getCustAliases().messages(mess_, e_.getMessages());
        stds_.getExecContent().getCustAliases().keyWord(kw_, e_.getKeyWords());
        kw_.initSupplDigits();
        stds_.getExecContent().getCustAliases().otherAlias(stds_.getContent(), e_.getAliases());
        StringMap<String> keys_ = LgNamesGui.extractAliasesKeys(stds_.getExecContent().getCustAliases());
        stds_.getGuiAliases().otherAliasGui(LgNamesGui.addon(stds_.getExecContent().getCustAliases()),e_.getAliases(),keys_);
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), e_.getLightProgramInfos(),e_.getListGenerator());
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(new AdvSymbolFactory(stds_.getExecContent().getCustAliases().getMathAdvAliases()));
        StringMap<String> m_ = stds_.getExecContent().getCustAliases().extractAliasesKeys();
        m_.addAllEntries(LgNamesGui.extractAliasesKeys(stds_.getExecContent().getCustAliases()));
        page_.setMappingAliases(m_);
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(stds_.getContent(), stds_.getGuiAliases(), stds_.getExecContent().getCustAliases());
        Forwards forwards_ = new Forwards(stds_, stds_.getExecContent(), fileBuilder_, opt_);
        forwards_.getResources().addAllEntries(files_);
        page_.setLogErr(forwards_);
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        ContextFactory.beforeBuild(forwards_,mess_,kw_,stds_.getExecContent().getCustAliases().defComments(),opt_,stds_.getContent(),page_);
        ContextFactory.build(forwards_,kw_,opt_,page_);
        ContextFactory.validateStds(forwards_,mess_, kw_, stds_.getExecContent().getCustAliases().defComments(), opt_, stds_.getContent(), page_);
        return page_;
    }

    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }
}
