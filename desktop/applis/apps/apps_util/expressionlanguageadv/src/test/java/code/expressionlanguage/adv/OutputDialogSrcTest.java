package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import code.mock.MockWindow;
import code.stream.StreamTextFile;
import org.junit.Test;

public final class OutputDialogSrcTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogSrc o_ = srcFolder(w_);
        o_.getSrc().setText("other_src");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("other_src",w_.getManageOptions().getEx().getSrcFolder());
    }

    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogSrc o_ = srcFolder(w_);
        o_.getSrc().setText("other_src");
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq("src",w_.getManageOptions().getEx().getSrcFolder());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogSrc o_ = srcFolder(w_);
        o_.getSrc().setText("other_src");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("other_src",w_.getManageOptions().getEx().getSrcFolder());
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq("other_src",w_.getManageOptions().getEx().getSrcFolder());
        OutputDialogSrc o2_ = srcFolder(w_);
        assertEq("other_src",o2_.getSrc().getText());
        WindowCdmEditor w2_=quickCreate(newMockProgramInfosInitConfExpFolder("/folder/exp"));
        String name_ = w_.getConfGlobal();
        String name2_ = w_.getExecConf();
        StreamTextFile.saveTextFile(name_,StreamTextFile.contentsOfFile(name_,w_.getCommonFrame().getFrames().getFileCoreStream(),w_.getCommonFrame().getFrames().getStreams()),w2_.getCommonFrame().getFrames().getStreams());
        StreamTextFile.saveTextFile(name2_,StreamTextFile.contentsOfFile(name2_,w_.getCommonFrame().getFrames().getFileCoreStream(),w_.getCommonFrame().getFrames().getStreams()),w2_.getCommonFrame().getFrames().getStreams());
        w2_.updateEnv(name_);
        assertEq("src",w2_.manage(w2_.getSoftParams().getLines()).getEx().getSrcFolder());
        assertEq("other_src",w2_.manage(ExecutingOptions.lines(StreamTextFile.contentsOfFile(w2_.getExecConf(),w2_.getCommonFrame().getFrames().getFileCoreStream(),w2_.getCommonFrame().getFrames().getStreams()))).getEx().getSrcFolder());
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDef();
        updateDialog((MockProgramInfos) w_.getCommonFrame().getFrames());
        WindowExpressionEditor s_ = geneSec(w_);
        OutputDialogSrc o_ = srcFolder(s_);
        o_.getSrc().setText("other_src");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("other_src",s_.getManageOptions().getEx().getSrcFolder());
    }

    @Test
    public void action5() {
        WindowCdmEditor w_=newWindowLoadDef();
        updateDialog((MockProgramInfos) w_.getCommonFrame().getFrames());
        WindowExpressionEditor s_ = geneSec(w_);
        OutputDialogSrc o_ = srcFolder(s_);
        o_.getSrc().setText("other_src");
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq("src",s_.getManageOptions().getEx().getSrcFolder());
    }
    @Test
    public void action6() {
        WindowCdmEditor w_=newWindowLoadDef();
        updateDialog((MockProgramInfos) w_.getCommonFrame().getFrames());
        WindowExpressionEditor s_ = geneSec(w_);
        OutputDialogSrc o_ = srcFolder(s_);
        o_.getSrc().setText("other_src");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("other_src",s_.getManageOptions().getEx().getSrcFolder());
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq("other_src",s_.getManageOptions().getEx().getSrcFolder());
        OutputDialogSrc o2_ = srcFolder(s_);
        assertEq("other_src",o2_.getSrc().getText());
        WindowCdmEditor w2_=quickCreate(newMockProgramInfosInitConfExpFolder("/folder/exp"));
        String name_ = w_.getConfGlobal();
        String name2_ = w_.getExecConf();
        StreamTextFile.saveTextFile(name_,StreamTextFile.contentsOfFile(name_,w_.getCommonFrame().getFrames().getFileCoreStream(),w_.getCommonFrame().getFrames().getStreams()),w2_.getCommonFrame().getFrames().getStreams());
        StreamTextFile.saveTextFile(name2_,StreamTextFile.contentsOfFile(name2_,w_.getCommonFrame().getFrames().getFileCoreStream(),w_.getCommonFrame().getFrames().getStreams()),w2_.getCommonFrame().getFrames().getStreams());
        w2_.updateEnv(name_);
        assertEq("other_src",w2_.manage(w2_.getSoftParams().getLines()).getEx().getSrcFolder());
        assertEq("src",w2_.manage(ExecutingOptions.lines(StreamTextFile.contentsOfFile(w2_.getExecConf(),w2_.getCommonFrame().getFrames().getFileCoreStream(),w2_.getCommonFrame().getFrames().getStreams()))).getEx().getSrcFolder());
    }
}
