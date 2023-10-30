package code.expressionlanguage;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.expressionlanguage.fwd.opers.AnaLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class MemoryLoggerTest extends EquallableElUtUtil {
    @Test
    public void log1() {
        MockProgramInfos pr_ = prs();
//        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        ResultContext res_ = ctxNoWarn(pr_, files_);
        ContextEl ctx_ = res_.getContext();
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().log("folder","file","content", (RunnableContextEl) ctx_);
        assertEq("content",StringUtil.decode(((MemoryLogger)((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getLogs().get("file").getContent()));
    }
    @Test
    public void log2() {
        MockProgramInfos pr_ = prs();
//        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        ResultContext res_ = ctxNoWarn(pr_, files_);
        ContextEl ctx_ = res_.getContext();
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().log("folder","file","content\n", (RunnableContextEl) ctx_);
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().log("folder","file","next", (RunnableContextEl) ctx_);
        assertEq("content\nnext",StringUtil.decode(((MemoryLogger)((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getLogs().get("file").getContent()));
    }

    private ResultContext ctxNoWarn(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesUtils stds_ = newLgNamesGuiSampleGr(_p, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        opt_.setCovering(true);
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        ValidatorStandard.setupOverrides(page_);
        return commonMock(_exec, _definedLgNames, _files, page_, forwards_);
    }

    public static LgNamesUtils newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesUtils stds_ = newLgNamesUt(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }
}
