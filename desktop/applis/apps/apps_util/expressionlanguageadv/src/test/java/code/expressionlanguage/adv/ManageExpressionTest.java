package code.expressionlanguage.adv;

import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.StringMap;
import org.junit.Test;

public final class ManageExpressionTest extends EquallableElAdvUtil {
    @Test
    public void failSrc() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace("src//double", "");
        analyze(w_);
        analyzeStatus(w_);
        assertEq("KO",w_.getStatusAnalyzeArea().getText());
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void failSrcFile() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "");
        analyze(w_);
        assertEq(CustAliases.YYYY_MM_DD_HH_MM_SS_SSS,w_.getResultContext().getLastBuilt());
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void refresh() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass{public ExClass(){}}");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void refreshAll() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:i,end:i+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
        assertEq(1, r_.size());
        assertTrue(r_.contains("pkg.ExClass"));
    }
    @Test
    public void refreshAllExp() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringView{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
    }
    @Test
    public void replaceNoOcc() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('c',i)>-1?new(begin:t.indexOf('c',i),end:t.indexOf('c',i)+1):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i)-1,end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){Thread.currentThread().interrupt();return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){Thread.currentThread().interrupt();return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "public class pkg.ExClass:AbsStringReplacer{public ExClass(ExClass p){}public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}");
        tabEditor(w_).getCenter().setText("C t C t");
        analyze(w_);
        assertTrue(w_.getResultContext().getResultContext().getReportedMessages().isAllEmptyErrors());
        refreshClasses(w_);
        StringMap<ExecOverrideInfo> d_ = tabEditor(w_).getDico();
        assertEq(1, d_.size());
        assertTrue(d_.contains("pkg.ExClass"));
        StringMap<ExecOverrideInfo> r_ = tabEditor(w_).getDicoRepl();
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
        tabEditor(w_).setAction((RunnableContextEl) tabEditor(w_).getResultContext().getResultContext().getContext());
        findStop(w_);
        closeExp(w_);
        assertEq(0,tabEditor(w_).getPartsExp().size());
    }
}
