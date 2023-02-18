package code.expressionlanguage;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.files.*;
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
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        stds_.getExecContent().getCustAliases().getInfos().getLogger().log("folder","file","content", (RunnableContextEl) ctx_);
        assertEq("content",StringUtil.decode(((MemoryLogger)stds_.getExecContent().getCustAliases().getInfos().getLogger()).getLogs().get("file").getContent()));
    }
    @Test
    public void log2() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        stds_.getExecContent().getCustAliases().getInfos().getLogger().log("folder","file","content\n", (RunnableContextEl) ctx_);
        stds_.getExecContent().getCustAliases().getInfos().getLogger().log("folder","file","next", (RunnableContextEl) ctx_);
        assertEq("content\nnext",StringUtil.decode(((MemoryLogger)stds_.getExecContent().getCustAliases().getInfos().getLogger()).getLogs().get("file").getContent()));
    }

    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }
}
