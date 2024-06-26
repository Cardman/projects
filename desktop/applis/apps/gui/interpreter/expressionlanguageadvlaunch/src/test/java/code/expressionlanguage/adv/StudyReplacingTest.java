package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.AbsSpinner;
import code.gui.AbsTxtComponent;
import code.mock.MockMenuItem;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import code.mock.MockThreadFactory;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class StudyReplacingTest extends EquallableElAdvUtil {
    @Test
    public void finder() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
    }
    @Test
    public void replaceAll() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq("C t C t",txtOut(b_).getText());
        launchThenWait(b_);
        assertEq("C t c t",txtOut(b_).getText());
        launchThenWait(b_);
        assertEq("c t c t",txtOut(b_).getText());
    }
    @Test
    public void replaceOne() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq("C t C t",txtOut(b_).getText());
        min(b_).setValue(1);
        max(b_).setValue(1);
        launchThenWait(b_);
        assertEq("C t c t",txtOut(b_).getText());
    }
    @Test
    public void replaceNext() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq("C t C t",txtOut(b_).getText());
        min(b_).setValue(1);
        launchThenWait(b_);
        assertEq("C t c t",txtOut(b_).getText());
    }
    @Test
    public void replacePrevious() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq("C t C t",txtOut(b_).getText());
        max(b_).setValue(0);
        launchThenWait(b_);
        assertEq("c t C t",txtOut(b_).getText());
    }
    @Test
    public void replaceBad() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq("C t C t",txtOut(b_).getText());
        min(b_).setValue(1);
        max(b_).setValue(0);
        launchNoWait(b_);
        assertEq("C t C t",txtOut(b_).getText());
    }
    @Test
    public void replaceAllDef() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        min(b_).setValue(-1);
        max(b_).setValue(-1);
        assertEq("C t C t",txtOut(b_).getText());
        launchThenWait(b_);
        assertEq("C t c t",txtOut(b_).getText());
        launchThenWait(b_);
        assertEq("c t c t",txtOut(b_).getText());
    }
    @Test
    public void replaceBadIntervalNeg() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i)-1,end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq(0,found(b_).size());
    }
    @Test
    public void replaceBadIntervalPos() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i)+1,end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq(0,found(b_).size());
    }
    @Test
    public void replaceNoOcc() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("D t D t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq(0,found(b_).size());
    }
    @Test
    public void replaceNoMethod() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.ExClass:AbsStringView{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        form(b_).getFinderExpClasses().setText("pkg.ExClass");
        form(b_).getFinderExpClasses().setText("p");
        form(b_).getCompleteClasses().enterEvent();
        selectClass(b_);
        assertEq("pkg.ExClass",form(b_).getFinderExpClasses().getText());
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        launchThenWait(b_);
        assertEq(0,found(b_).size());
    }
    @Test
    public void errors() {
        AbsDebuggerGui b_ = buildExp();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","");
        guiAna(r_,b_,o_,src_);
        txt(b_).setText("C t C t");
        launchNoWait(b_);
        assertEq(0,found(b_).size());
    }
    @Test
    public void window() {
        MockProgramInfos pr_ = genePr();
        WindowCdmEditor w_ = updated(pr_);
        w_.getFuture().attendre();
        w_.getFutureDbg().attendre();
        WindowExpressionEditor e_ = geneSecAlready(w_);
        save(pr_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        AbsDebuggerGui b_ = e_.getSessionExp();
        guiAna(e_,b_);
        StringMap<ExecConstructorOverrideInfo> d_ = form(b_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> rp_ = form(b_).getDicoRepl();
        assertEq(1, rp_.size());
        assertTrue(rp_.contains("pkg.ExClass"));
    }
    @Test
    public void windowClose() {
        MockProgramInfos pr_ = genePr();
        WindowCdmEditor w_ = updated(pr_);
        w_.getFuture().attendre();
        WindowExpressionEditor e_ = geneSecAlready(w_);
        save(pr_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        AbsDebuggerGui b_ = e_.getSessionExp();
        guiAna(e_,b_);
        assertFalse(e_.getSessionMenuExp().isEnabled());
        b_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertTrue(e_.getSessionMenuExp().isEnabled());
    }
    @Test
    public void windowReopen() {
        MockProgramInfos pr_ = genePr();
        WindowCdmEditor w_ = updated(pr_);
        w_.getFuture().attendre();
        WindowExpressionEditor e_ = geneSecAlready(w_);
        save(pr_,"src/file.txt","public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        AbsDebuggerGui b_ = e_.getSessionExp();
        ((MockMenuItem)e_.getSessionMenuExp()).getActionListeners().get(0).action();
        ((MockMenuItem)e_.getSessionMenuExp()).getActionListeners().get(0).action();
        assertTrue(b_.getCommonFrame().isVisible());
    }
    private CustList<SegmentFindPart> found(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getFound();
    }
    private AbsTxtComponent txt(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getText();
    }

    private AbsTxtComponent txtOut(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getTextOutput();
    }

    private AbsSpinner min(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getMinValue();
    }

    private AbsSpinner max(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getMaxValue();
    }
    private FormFindReplaceExpression form(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getFindReplaceExpression();
    }

    protected static void selectClass(AbsDebuggerGui _w) {
        SelectClassDbgEvent ev_ = (SelectClassDbgEvent) ((MockPlainButton) ((ExpDebGuiImpl)_w).getSelectClass()).getActionListeners().get(0);
        ev_.action();
    }

    private void launchThenWait(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getSelectEnter()).getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getFrames().getThreadFactory());
    }

    private void launchNoWait(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getSelectEnter()).getActionListeners().get(0).action();
    }

}
