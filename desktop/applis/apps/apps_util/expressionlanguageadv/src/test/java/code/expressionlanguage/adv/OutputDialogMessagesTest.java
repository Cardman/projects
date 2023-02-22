package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.mock.MockPlainButton;
import org.junit.Test;

public final class OutputDialogMessagesTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogMessages o_ = messages(w_);
        int index_ = new AnalysisMessages().allMessages().indexOfEntry("VoidType");
        assertEq(0,w_.getLgMessages().size());
        assertEq("",o_.getMessagesRows().get(index_).getContent().getText());
        o_.getMessagesRows().get(index_).getContent().setText("The type is void!");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        
        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDefMessages();
        OutputDialogMessages o_ = messages(w_);
        int index_ = new AnalysisMessages().allMessages().indexOfEntry("VoidType");
        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("void type?",w_.getLgMessages().getValue(0));
        assertEq("void type?",o_.getMessagesRows().get(index_).getContent().getText());
        o_.getMessagesRows().get(index_).getContent().setText("The type is void!");
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
        assertEq("",o_.getMessagesRows().get(index_).getContent().getText());
        o_.getMessagesRows().get(index_).getContent().setText("The type is void!");
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();

        assertEq(0,w_.getLgMessages().size());
    }

}
