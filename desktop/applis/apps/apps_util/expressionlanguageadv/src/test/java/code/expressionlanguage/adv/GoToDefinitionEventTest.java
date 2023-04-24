package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.mock.MockMenuItem;
import code.mock.MockPlainButton;
import code.stream.StreamTextFile;
import org.junit.Test;

public final class GoToDefinitionEventTest extends EquallableElAdvUtil {
    @Test
    public void def1() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(43,43);
        currentElement(s_.getTabs().get(0));
        goTo(s_, 0);
        assertEq(2,s_.getTabs().size());
    }
    @Test
    public void def2() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(43,43);
        currentElement(s_.getTabs().get(0));
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/exp/src/file_exp2.txt").delete();
        goTo(s_, 0);
        assertEq(1,s_.getTabs().size());
    }
    @Test
    public void def3() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(79,79);
        currentElement(s_.getTabs().get(0));
        goTo(s_, 0);
        assertEq(1,s_.getTabs().size());
    }
    @Test
    public void def4() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{public ExClass(){}public StringSegment index(String t,int i){Second s=new ExClass();return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{public static Second $(ExClass s){return null;}}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(113,113);
        currentElement(s_.getTabs().get(0));
        goTo(s_, 0);
        assertEq(1,s_.getTabs().size());
    }
    @Test
    public void def5() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{public ExClass(){}public StringSegment index(String t,int i){Second s=new ExClass();return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{public static Second $(ExClass s){return null;}}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(113,113);
        currentElement(s_.getTabs().get(0));
        goTo(s_, 2);
        assertEq(2,s_.getTabs().size());
    }
    @Test
    public void limit() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(43,43);
        currentElement(s_.getTabs().get(0));
        currentElement(s_.getTabs().get(0));
        assertEq(1,s_.getSymbols().size());
    }
    @Test
    public void noDef() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        analyze(w_);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
        s_.getTabs().get(0).getCenter().select(245,245);
        currentElement(s_.getTabs().get(0));
        assertEq(0,s_.getSymbols().size());
    }
    @Test
    public void noAna() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "src//bad","public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        StreamTextFile.saveTextFile("/project/sources/exp/0.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling());
        s_.getTabs().get(0).getCenter().select(0,0);
        currentElement(s_.getTabs().get(0));
        assertEq(0,s_.getSymbols().size());
    }

    private void goTo(WindowExpressionEditor _s, int _i) {
        ((MockPlainButton)(((AbsPanel) _s.getPanelSymbols().getComponent(1)).getComponent(_i))).getActionListeners().get(0).action();
    }

}
