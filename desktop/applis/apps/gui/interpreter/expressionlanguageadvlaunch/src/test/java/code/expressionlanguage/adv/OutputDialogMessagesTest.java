package code.expressionlanguage.adv;

import code.mock.MockPlainButton;
import code.mock.MockWindow;
import code.util.core.StringUtil;
import org.junit.Test;

public final class OutputDialogMessagesTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogAliases o_ = messages(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.keysMessages(w_),"VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("",o_.getMessages().getValue().getText());
        o_.getMessages().getValue().setText("The type is void!");
        ((MockPlainButton)o_.getMessages().getValPart()).getActionListeners().get(0).action();
        assertEq("VoidType",o_.getMessages().getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessages().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDefMessages();
        OutputDialogAliases o_ = messages(w_);
        assertEq(1,w_.getLgMessages().size());
        int index_ = StringUtil.indexOf(OutputDialogAliases.keysMessages(w_),"VoidType");
        o_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("void type?",o_.getMessages().getValue().getText());

        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("void type?",w_.getLgMessages().getValue(0));
        o_.getMessages().getValue().setText("The type is void!");
        ((MockPlainButton)o_.getMessages().getValPart()).getActionListeners().get(0).action();
        assertEq("The type is void!",o_.getMessages().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogAliases o_ = messages(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.keysMessages(w_),"VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("",o_.getMessages().getValue().getText());
        o_.getMessages().getValue().setText("The type is void!");
        ((MockPlainButton)o_.getMessages().getValPart()).getActionListeners().get(0).action();
        assertEq("VoidType",o_.getMessages().getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessages().getMessagesRows().getValue(index_));
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();

        assertEq(0,w_.getLgMessages().size());
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDefMessages();
        OutputDialogAliases o_ = messages(w_);
        assertEq(1,w_.getLgMessages().size());
        o_.getMessages().getKey().setText("VoidTypes");
        assertEq("",o_.getMessages().getValue().getText());
    }
    @Test
    public void action5() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogAliases o_ = messages(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.keysMessages(w_),"VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getMessages().getKey().setText("VoidType");
        enter(o_.getMessages().getAuto());
        assertEq("",o_.getMessages().getValue().getText());
        o_.getMessages().getValue().setText("The type is void!");
        ((MockPlainButton)o_.getMessages().getValPart()).getActionListeners().get(0).action();
        assertEq("VoidType",o_.getMessages().getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessages().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
    }
    @Test
    public void action6() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogAliases o_ = messages(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.keysMessages(w_),"VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("",o_.getMessages().getValue().getText());
        o_.getMessages().getValue().setText("The type is void!");
        ((MockPlainButton)o_.getMessages().getValPart()).getActionListeners().get(0).action();
        assertEq("VoidType",o_.getMessages().getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessages().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        OutputDialogAliases o2_ = messages(w_);
        o2_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("The type is void!",o2_.getMessages().getValue().getText());
    }
    @Test
    public void action7() {
        WindowCdmEditor w_=newWindowLoadDef();
        w_.getSoftParams().setDirectMatchKeyValue(true);
        OutputDialogAliases o_ = messages(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.keysMessages(w_),"VoidType");
        assertEq(0,w_.getLgMessages().size());
        o_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("",o_.getMessages().getValue().getText());
        o_.getMessages().getValue().setText("The type is void!");
        assertEq("VoidType",o_.getMessages().getMessagesRows().getKey(index_));
        assertEq("The type is void!",o_.getMessages().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgMessages().size());
        assertEq("VoidType",w_.getLgMessages().getKey(0));
        assertEq("The type is void!",w_.getLgMessages().getValue(0));
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        OutputDialogAliases o2_ = messages(w_);
        o2_.getMessages().getKey().setText("VoidType");
//        enter(o_.getMessages().getAuto());
        assertEq("The type is void!",o2_.getMessages().getValue().getText());
    }
}
