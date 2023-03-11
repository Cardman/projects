package code.expressionlanguage.adv;

import code.mock.MockPlainButton;
import code.mock.MockTextArea;
import code.mock.MockTextField;
import code.mock.MockWindow;
import code.util.core.StringUtil;
import org.junit.Test;

public final class OutputDialogAliasesTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogAliases o_ = aliases(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.aliases(w_),"Runnable");
        assertEq(0,w_.getLgAliases().size());
        o_.getAliases().getKey().setText("Runnable");

        assertEq("",o_.getAliases().getValue().getText());
        o_.getAliases().getValue().setText("$core.Runner");
        ((MockPlainButton)o_.getAliases().getValPart()).getActionListeners().get(0).action();
        assertEq("$core.Runner",o_.getAliases().getValPartLabel().getText());
        assertEq("Runnable",o_.getAliases().getMessagesRows().getKey(index_));
        assertEq("$core.Runner",o_.getAliases().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgAliases().size());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.Runner",w_.getLgAliases().getValue(0));
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogAliases o_ = aliases(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.keyWords(w_),"If");
        assertEq(0,w_.getLgKeyWords().size());
        o_.getKeyWords().getKey().setText("If");
//        enter(o_.getKeyWords().getAuto());
        assertEq("",o_.getKeyWords().getValue().getText());
        o_.getKeyWords().getValue().setText("even");
        ((MockPlainButton)o_.getKeyWords().getValPart()).getActionListeners().get(0).action();
        assertEq("If",o_.getKeyWords().getMessagesRows().getKey(index_));
        assertEq("even",o_.getKeyWords().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgKeyWords().size());
        assertEq("If",w_.getLgKeyWords().getKey(0));
        assertEq("even",w_.getLgKeyWords().getValue(0));
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDefAliasesKeywords();
        OutputDialogAliases o_ = aliases(w_);
        assertEq(1,w_.getLgAliases().size());
        assertEq(1,w_.getLgKeyWords().size());
        int index_ = StringUtil.indexOf(OutputDialogAliases.aliases(w_),"Runnable");
        int indexKey_ = StringUtil.indexOf(OutputDialogAliases.keyWords(w_),"If");

        o_.getAliases().getKey().setText("Runnable");
//        enter(o_.getAliases().getAuto());
        assertEq("$core.Runner",o_.getAliases().getValue().getText());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.Runner",w_.getLgAliases().getValue(0));
        o_.getAliases().getValue().setText("$core.ToRun");
        ((MockPlainButton)o_.getAliases().getValPart()).getActionListeners().get(0).action();
        assertEq("$core.ToRun",o_.getAliases().getMessagesRows().getValue(index_));

        o_.getKeyWords().getKey().setText("If");
//        enter(o_.getKeyWords().getAuto());
        assertEq("even",o_.getKeyWords().getValue().getText());
        assertEq("If",w_.getLgKeyWords().getKey(0));
        assertEq("even",w_.getLgKeyWords().getValue(0));
        o_.getKeyWords().getValue().setText("tough");
        ((MockPlainButton)o_.getKeyWords().getValPart()).getActionListeners().get(0).action();
        assertEq("tough",o_.getKeyWords().getMessagesRows().getValue(indexKey_));

        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgAliases().size());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.ToRun",w_.getLgAliases().getValue(0));
        assertEq(1,w_.getLgKeyWords().size());
        assertEq("If",w_.getLgKeyWords().getKey(0));
        assertEq("tough",w_.getLgKeyWords().getValue(0));
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDefAliasesKeywords();
        OutputDialogAliases o_ = aliases(w_);
        assertEq(1,w_.getLgAliases().size());
        assertEq(1,w_.getLgKeyWords().size());
        int index_ = StringUtil.indexOf(OutputDialogAliases.aliases(w_),"Runnable");
        int indexKey_ = StringUtil.indexOf(OutputDialogAliases.keyWords(w_),"If");

        o_.getAliases().getKey().setText("Runnable");
//        enter(o_.getAliases().getAuto());
        assertEq("$core.Runner",o_.getAliases().getValue().getText());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.Runner",w_.getLgAliases().getValue(0));
        o_.getAliases().getValue().setText("$core.ToRun");
        ((MockPlainButton)o_.getAliases().getValPart()).getActionListeners().get(0).action();
        assertEq("$core.ToRun",o_.getAliases().getMessagesRows().getValue(index_));

        o_.getKeyWords().getKey().setText("If");
//        enter(o_.getKeyWords().getAuto());
        assertEq("even",o_.getKeyWords().getValue().getText());
        assertEq("If",w_.getLgKeyWords().getKey(0));
        assertEq("even",w_.getLgKeyWords().getValue(0));
        o_.getKeyWords().getValue().setText("tough");
        ((MockPlainButton)o_.getKeyWords().getValPart()).getActionListeners().get(0).action();
        assertEq("tough",o_.getKeyWords().getMessagesRows().getValue(indexKey_));

        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();

        assertEq(1,w_.getLgAliases().size());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.Runner",w_.getLgAliases().getValue(0));
        assertEq(1,w_.getLgKeyWords().size());
        assertEq("If",w_.getLgKeyWords().getKey(0));
        assertEq("even",w_.getLgKeyWords().getValue(0));
    }
    @Test
    public void action5() {
        WindowCdmEditor w_=newWindowLoadDef();
        w_.getSoftParams().setDirectMatchKeyValue(true);
        OutputDialogAliases o_ = aliases(w_);
        int index_ = StringUtil.indexOf(OutputDialogAliases.aliases(w_),"Runnable");
        assertEq(0,w_.getLgAliases().size());
        o_.getAliases().getKey().setText("Runnable");

        assertEq("",o_.getAliases().getValue().getText());
        o_.getAliases().getValue().setText("$core.Runner");
        assertEq("Runnable",o_.getAliases().getMessagesRows().getKey(index_));
        assertEq("$core.Runner",o_.getAliases().getMessagesRows().getValue(index_));
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();

        assertEq(1,w_.getLgAliases().size());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.Runner",w_.getLgAliases().getValue(0));
        ((MockTextArea)o_.getAliases().getValue()).getAutoCompleteListeners().get(0).changedUpdate();
    }
    @Test
    public void errors() {
        WindowCdmEditor w_=newWindowLoadDefAliasesKeywords();
        OutputDialogAliases o_ = aliases(w_);
        assertEq(1,w_.getLgAliases().size());
        assertEq(1,w_.getLgKeyWords().size());
        int index_ = StringUtil.indexOf(OutputDialogAliases.aliases(w_),"Runnable");
        int indexKey_ = StringUtil.indexOf(OutputDialogAliases.keyWords(w_),"If");

        o_.getAliases().getKey().setText("Runnable");
//        enter(o_.getAliases().getAuto());
        assertEq("$core.Runner",o_.getAliases().getValue().getText());
        assertEq("Runnable",w_.getLgAliases().getKey(0));
        assertEq("$core.Runner",w_.getLgAliases().getValue(0));
        o_.getAliases().getValue().setText("$core.ToRun");
        ((MockPlainButton)o_.getAliases().getValPart()).getActionListeners().get(0).action();
        assertEq("$core.ToRun",o_.getAliases().getMessagesRows().getValue(index_));

        o_.getKeyWords().getKey().setText("If");
//        enter(o_.getKeyWords().getAuto());
        assertEq("even",o_.getKeyWords().getValue().getText());
        assertEq("If",w_.getLgKeyWords().getKey(0));
        assertEq("even",w_.getLgKeyWords().getValue(0));
        o_.getKeyWords().getValue().setText(";");
        ((MockPlainButton)o_.getKeyWords().getValPart()).getActionListeners().get(0).action();
        assertEq(";",o_.getKeyWords().getMessagesRows().getValue(indexKey_));

        ((MockPlainButton)o_.getCheck()).getActionListeners().get(0).action();

        assertFalse(o_.getErrors().getText().isEmpty());
    }
}
