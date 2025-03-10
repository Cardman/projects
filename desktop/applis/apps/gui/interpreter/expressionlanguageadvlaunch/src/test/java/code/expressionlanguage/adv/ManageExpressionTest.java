package code.expressionlanguage.adv;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.mock.MockMenuItem;
import code.threads.ConcreteBoolean;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class ManageExpressionTest extends EquallableElAdvUtil {
    @Test
    public void failSrc() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace("src//double", "");
        analyze(w_);
        analyzeStatus(w_);
        assertFalse(w_.getBaseResult().getPageEl().isCustomAna());
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void failConf() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "");
        StringList ls_ = new StringList(w_.getSoftParams().getLines());
        ls_.add("keyWords=If=;");
        w_.getSoftParams().setLines(ls_);
        w_.getFuture().attendre();
        assertFalse(w_.getAnalyzeMenu().isEnabled());
    }
    @Test
    public void failSrcFile() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "");
        analyze(w_);
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void refresh() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass{public ExClass(){}public class Inner{}public static class StInner:AbsStringView{public StInner(ExClass p){}public StringSegment index(String t,int i){return null;}}}");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void refreshAll() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
    }
    @Test
    public void refreshAllExp() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(2,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        previousExp(w_);
        assertEq(1,tabEditor(w_).getCurrentPartExp());
        nextExp(w_);
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        nextExp(w_);
        assertEq(1,tabEditor(w_).getCurrentPartExp());
        previousExp(w_);
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        previousExp(w_);
        assertEq(1,tabEditor(w_).getCurrentPartExp());
        replAllExp(w_);
        assertEq("c t c t",tabEditor(w_).getPreview().getText());
        assertFalse(tabEditor(w_).getReplaceAllExp().isEnabled());
        applyExp(w_);
        assertEq("c t c t",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void replaceOne() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        tabEditor(w_).getCenter().select(0,0);
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(2,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        replOneExp(w_);
        assertEq("c t C t",tabEditor(w_).getPreview().getText());
        assertTrue(tabEditor(w_).getReplaceOneExp().isEnabled());
        replOneExp(w_);
        assertEq("c t c t",tabEditor(w_).getPreview().getText());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
        applyExp(w_);
        assertEq("c t c t",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void replacePreviousNext1() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(3,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        replPreviousExp(w_);
        assertEq("c t C t C t",tabEditor(w_).getPreview().getText());
        assertTrue(tabEditor(w_).getReplaceOneExp().isEnabled());
        nextExp(w_);
        replNextExp(w_);
        assertEq("c t C t c t",tabEditor(w_).getPreview().getText());
        assertTrue(tabEditor(w_).getReplaceOneExp().isEnabled());
        applyExp(w_);
        assertEq("c t C t c t",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void replacePreviousNext2() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(3,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        nextExp(w_);
        nextExp(w_);
        replNextExp(w_);
        assertEq("C t C t c t",tabEditor(w_).getPreview().getText());
        assertTrue(tabEditor(w_).getReplaceOneExp().isEnabled());
        previousExp(w_);
        replPreviousExp(w_);
        assertEq("c t C t c t",tabEditor(w_).getPreview().getText());
        assertTrue(tabEditor(w_).getReplaceOneExp().isEnabled());
        applyExp(w_);
        assertEq("c t C t c t",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void replacePreviousNext3() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(3,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        replPreviousExp(w_);
        assertEq("c t C t C t",tabEditor(w_).getPreview().getText());
        assertTrue(tabEditor(w_).getReplaceOneExp().isEnabled());
        replNextExp(w_);
        assertEq("c t c t c t",tabEditor(w_).getPreview().getText());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
        applyExp(w_);
        assertEq("c t c t c t",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void replaceNo() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(3,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        replPreviousExp(w_);
        assertEq("C t C t C t",tabEditor(w_).getPreview().getText());
    }
    @Test
    public void replaceNoImpl() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringView{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(0, r_.size());
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(3,tabEditor(w_).getPartsExp().size());
        assertEq(0,tabEditor(w_).getCurrentPartExp());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
        TabEditor t_ = tabEditor(w_);
        closeTab(t_);
        assertTrue(((InterruptibleContextEl)t_.getAction()).getInterrupt().get());
    }
    @Test
    public void replaceNoOcc() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('c',i)>-1?new(begin:t.indexOf('c',i),end:t.indexOf('c',i)+1):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(0,tabEditor(w_).getPartsExp().size());
        assertEq(-1,tabEditor(w_).getCurrentPartExp());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
    }
    @Test
    public void replaceBadOcc1() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i)-1,end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(0,tabEditor(w_).getPartsExp().size());
        assertEq(-1,tabEditor(w_).getCurrentPartExp());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
    }
    @Test
    public void replaceBadOcc2() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(0,tabEditor(w_).getPartsExp().size());
        assertEq(-1,tabEditor(w_).getCurrentPartExp());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
    }
    @Test
    public void replaceBadOcc3() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){Thread.currentThread().interrupt();return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(0,tabEditor(w_).getPartsExp().size());
        assertEq(-1,tabEditor(w_).getCurrentPartExp());
        assertFalse(tabEditor(w_).getReplaceOneExp().isEnabled());
    }
    @Test
    public void findStop() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        ConcreteBoolean v_ = new ConcreteBoolean();
        InterruptibleContextEl c_ = (InterruptibleContextEl) w_.getResultContextNext().generateAdv(v_).gene(w_.getBaseResult().getForwards());
        tabEditor(w_).setAction(c_);
        findStop(w_);
        assertTrue(c_.getInterrupt().get());
        closeExp(w_);
        assertEq(0,tabEditor(w_).getPartsExp().size());
    }
    @Test
    public void replacePreviousSegments() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){int n = t.indexOf('C',i);if(n>0&&t.charAt(n-1)!=','&&t.charAt(n-1)!=' '){n=t.indexOf('C',n+1);}if(n==0){return new(begin:0,end:1);}if(n>0&&(t.charAt(n-1)==','||t.charAt(n-1)==' ')){return new(begin:n-1,end:n+1);}return null;}public String replace(String t, int i, int b, int e){var pref = t.startsWith(\",\",b)?\";\":t.startsWith(\" \",b)?\"__\":\"\";return i%3==0?pref+\"one\":i%3==1?pref+\"two\":pref+\"three\";}}");
        tabEditor(w_).getCenter().setText("C t,C t C t;C t,C t C t");
        tabEditor(w_).getCenter().select(0,0);
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(5,tabEditor(w_).getPartsExp().size());
        nextExp(w_);
        nextExp(w_);
        nextExp(w_);
        replPreviousExp(w_);
        assertEq("one t;two t__three t;C t;one t C t",tabEditor(w_).getPreview().getText());
        assertEq(1,tabEditor(w_).getPartsExp().size());
        assertEq(30,tabEditor(w_).getPartsExp().get(0).getBegin());
        assertEq(32,tabEditor(w_).getPartsExp().get(0).getEnd());
    }
    @Test
    public void replacePreviousSegmentsSec() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){int n = t.indexOf('t',i);if(n+1<t.length()&&t.charAt(n+1)!=','&&t.charAt(n+1)!=' '){n=t.indexOf('t',n+1);}if(n==t.length()-1){return new(begin:t.length()-1,end:t.length());}if(n+1<t.length()&&(t.charAt(n+1)==','||t.charAt(n+1)==' ')){return new(begin:n,end:n+2);}return null;}public String replace(String t, int i, int b, int e){var pref = t.substring(b,e).endsWith(\",\")?\";\":t.substring(b,e).endsWith(\" \")?\"__\":\"\";return i%3==0?\"one\"+pref:i%3==1?\"two\"+pref:\"three\"+pref;}}");
        tabEditor(w_).getCenter().setText("C t,C t C t;C t,C t C t");
        tabEditor(w_).getCenter().select(0,0);
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertEq(5,tabEditor(w_).getPartsExp().size());
        nextExp(w_);
        nextExp(w_);
        nextExp(w_);
        replPreviousExp(w_);
        assertEq("C one;C two__C t;C three;C one__C t",tabEditor(w_).getPreview().getText());
        assertEq(1,tabEditor(w_).getPartsExp().size());
        assertEq(34,tabEditor(w_).getPartsExp().get(0).getBegin());
        assertEq(35,tabEditor(w_).getPartsExp().get(0).getEnd());
    }
    @Test
    public void badParam() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass<T>:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        tabEditor(w_).getCenter().select(0,0);
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
        tabEditor(w_).getFinderExpClasses().setText("pkg.ExClass");
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        assertEq("pkg.ExClass",tabEditor(w_).getFinderExpClasses().getText());
        tabEditor(w_).getFinderExpClasses().setText("?");
        selectClass(w_);
        assertFalse(tabEditor(w_).getFindingExpression().isEnabled());
        tabEditor(w_).getFinderExpClasses().setText("p");
        tabEditor(w_).getCompleteClasses().enterEvent();
        selectClass(w_);
        assertTrue(tabEditor(w_).getFindingExpression().isEnabled());
        findExp(w_);
        assertTrue(tabEditor(w_).getFindingExpressionCancel().isEnabled());
    }
    @Test
    public void refreshAllText() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceFoldExp( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        ((MockMenuItem) w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getFirstChild());
        tabSelect(s_).getCenter().setText("public class pkg.ExClass2:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecConstructorOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass2"));
        StringMap<ExecConstructorOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass2"));
    }
    @Test
    public void refreshNo() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public abstract class pkg.ExClass{public ExClass(int p){}}");
        analyze(w_);
        assertTrue(w_.getUserResult().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
//    @Test
//    public void cancelAnalyze() {
//        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace("src//double", "");
//        w_.getFuture().attendre();
//        ResultContext base_ = w_.getBaseResult();
//        LgNamesGui lg_ = (LgNamesGui) base_.getForwards().getGenerator();
//        w_.setAnalyzeEx(lg_.getExecContent().getExecutingOptions());
//        ((MockMenuItem)w_.getAnalyzeMenuCancel()).getActionListeners().get(0).action();
//        new AnalyzeExpressionSource(w_).end(base_);
//        assertEq(0,tabEditor(w_).getDico().size());
//    }
}
