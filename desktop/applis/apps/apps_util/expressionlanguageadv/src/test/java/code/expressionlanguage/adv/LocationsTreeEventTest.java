package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsTreeGui;
import code.mock.MockPlainButton;
import org.junit.Test;

public final class LocationsTreeEventTest extends EquallableElAdvUtil {
    @Test
    public void def1() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(104,104);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getFirstChild().getChildCount());
        assertEq(3,locations(s_).getComponentCount());
    }
    @Test
    public void def2() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass{public int fac(int n){if(n<2){return 1;}return n*fac(n-1);}}","public class pkg.Second{}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(74,74);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getChildCount());
    }
    @Test
    public void def3() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass{public int fac(int n){return \"\".indexOf('C',0);}}","public class pkg.Second{public int fac(int n){return \"\".indexOf('C',0);}}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(57,57);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getChildCount());
        assertEq(2, a_.getRoot().getFirstChild().getChildCount());
    }
    @Test
    public void def4() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf\n('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(104,104);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getFirstChild().getChildCount());
        assertEq(3,locations(s_).getComponentCount());
    }
    @Test
    public void def5() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf(\n'C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(104,104);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getFirstChild().getChildCount());
        assertEq(3,locations(s_).getComponentCount());
    }
    @Test
    public void def6() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf(\n'C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(104,104);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        refreshUsages(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getFirstChild().getChildCount());
        assertEq(3,locations(s_).getComponentCount());
    }
    @Test
    public void def7() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf(\n'C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(104,104);
        currentElement(s_.getTabs().get(0));
        callers(s_);
        refreshUsagesDef(s_);
        AbsTreeGui a_ = (AbsTreeGui) s_.getPanelSymbolsDetailScroll().getChildren().get(0);
        assertEq(1, a_.getRoot().getFirstChild().getChildCount());
        assertEq(3,locations(s_).getComponentCount());
    }
    private void callers(WindowExpressionEditor _s) {
        AbsPanel p_ = (AbsPanel) _s.getPanelSymbols().getComponent(1);
        ((MockPlainButton)(p_.getComponent(1))).getActionListeners().get(0).action();
        executeOneTask(_s.getFinderSymbol());
    }
    private void refreshUsages(WindowExpressionEditor _s) {
        AbsPanel p_ = (AbsPanel) _s.getPanelSymbols().getComponent(1);
        ((MockPlainButton)(p_.getComponent(2))).getActionListeners().get(0).action();
        executeOneTask(_s.getFinderSymbol());
    }
    private void refreshUsagesDef(WindowExpressionEditor _s) {
        AbsPanel p_ = (AbsPanel) _s.getPanelSymbols().getComponent(1);
        ((MockPlainButton)(p_.getComponent(3))).getActionListeners().get(0).action();
        executeOneTask(_s.getFinderSymbol());
    }

    private AbsPanel locations(WindowExpressionEditor _s) {
        AbsTreeGui a_ = (AbsTreeGui) _s.getPanelSymbolsDetailScroll().getChildren().get(0);
        a_.select(null);
        a_.select(a_.getRoot());
        a_.select(a_.getRoot().getFirstChild());
        a_.select(a_.getRoot().getFirstChild().getFirstChild());
        return (AbsPanel)_s.getPanelSymbolsLocationScroll().getChildren().get(0);
    }
}
