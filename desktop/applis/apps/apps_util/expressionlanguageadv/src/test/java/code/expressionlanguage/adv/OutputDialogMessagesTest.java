package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.gui.AutoCompleteDocument;
import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.mock.MockAbstractAction;
import code.mock.MockPlainButton;
import org.junit.Test;

public final class OutputDialogMessagesTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogMessages o_ = messages(w_);
        int index_ = new AnalysisMessages().allMessages().indexOfEntry("VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getKey().setText("VoidType");
        enter(o_.getAuto());
        assertEq("",o_.getValue().getText());
        o_.getValue().setText("The type is void!");
        ((MockPlainButton)o_.getValPart()).getActionListeners().get(0).action();
        assertEq("VoidType",o_.getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDefMessages();
        OutputDialogMessages o_ = messages(w_);
        assertEq(1,w_.getLgMessages().size());
        int index_ = new AnalysisMessages().allMessages().indexOfEntry("VoidType");
        o_.getKey().setText("VoidType");
        enter(o_.getAuto());
        assertEq("void type?",o_.getValue().getText());

        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("void type?",w_.getLgMessages().getValue(0));
        o_.getValue().setText("The type is void!");
        ((MockPlainButton)o_.getValPart()).getActionListeners().get(0).action();
        assertEq("The type is void!",o_.getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogMessages o_ = messages(w_);
        int index_ = new AnalysisMessages().allMessages().indexOfEntry("VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getKey().setText("VoidType");
        enter(o_.getAuto());
        assertEq("",o_.getValue().getText());
        o_.getValue().setText("The type is void!");
        ((MockPlainButton)o_.getValPart()).getActionListeners().get(0).action();
        assertEq("VoidType",o_.getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();

        assertEq(0,w_.getLgMessages().size());
    }
    private void enter(AutoCompleteDocument _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getTextField(), GuiConstants.VK_ENTER,0)).action();
    }
}
