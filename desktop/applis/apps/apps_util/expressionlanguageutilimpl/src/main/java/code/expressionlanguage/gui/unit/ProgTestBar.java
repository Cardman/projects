package code.expressionlanguage.gui.unit;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.utilcompo.FileInfos;
import code.gui.AbsPlainLabel;
import code.gui.AbsProgressBar;
import code.gui.AbsTableGui;
import code.gui.AbsTextArea;
import code.gui.initialize.AbstractLightProgramInfos;
import code.sml.util.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class ProgTestBar implements ProgTestBarInt{
    public static final String EXEC_OPTIONS_TABLE="exec_options_4";
    public static final String EXEC_OPTIONS_TABLE_SUCCESS="0";
    public static final String EXEC_OPTIONS_TABLE_FAIL="1";
    public static final String EXEC_OPTIONS_TABLE_MS="2";
    public static final String EXEC_OPTIONS_TABLE_NUMBER="3";
    public static final String EXEC_OPTIONS_TABLE_METHOD="4";
    public static final String EXEC_OPTIONS_TABLE_PARAMS="5";
    public static final String EXEC_OPTIONS_TABLE_STATUS="6";
    public static final String EXEC_OPTIONS_TABLE_CONFIGURATION="7";
    public static final String EXEC_OPTIONS_TABLE_FILE="8";
    public static final String EXEC_OPTIONS_TABLE_OPEN="9";
    public static final String EXEC_OPTIONS_TABLE_LAUNCH="10";
    public static final String EXEC_OPTIONS_TABLE_STOP="11";
    public static final String EXEC_OPTIONS_TABLE_TESTS="12";
    public static final String EXEC_OPTIONS_TABLE_TITLE="13";
    public static final String EXEC_OPTIONS_SIMPLE="exec_options_5";
    public static final String EXEC_OPTIONS_SIMPLE_LAUNCH="0";
    public static final String EXEC_OPTIONS_SIMPLE_FOLDER="1";
    public static final String EXEC_OPTIONS_SIMPLE_SRC="2";
    public static final String EXEC_OPTIONS_SIMPLE_FILES="3";
    public static final String EXEC_OPTIONS_SIMPLE_MES="exec_options_6";
    public static final String EXEC_OPTIONS_SIMPLE_MES_FAILED_PATH="0";
    public static final String EXEC_OPTIONS_SIMPLE_MES_FAILED_CONTENT="1";
    public static final String EXEC_OPTIONS_SIMPLE_MES_SUCCESS="2";
    public static final String EXEC_OPTIONS_MAIN="exec_options_7";
    public static final String EXEC_OPTIONS_MAIN_ARCHIVE="0";
    public static final String EXEC_OPTIONS_MAIN_MEMORY="1";
    private final AbstractLightProgramInfos messages;
    private final AbsPlainLabel doneTestsCalls;
    private final AbsPlainLabel doneTestsCount;

    private final AbsPlainLabel currentMethod;
    private final AbsTableGui resultsTable;
    private final AbsTextArea resultsArea;
    private final CustList<ResTestRow> results;
    private final AbsProgressBar progressBar;

    public ProgTestBar(AbstractLightProgramInfos _messages, AbsPlainLabel _doneTestsCalls, AbsPlainLabel _doneTestsCount, AbsPlainLabel _currentMethod,
                       AbsTableGui _resultsTable, AbsTextArea _results, AbsProgressBar _progressBar) {
        this.messages = _messages;
        this.doneTestsCalls = _doneTestsCalls;
        this.doneTestsCount = _doneTestsCount;
        this.resultsArea = _results;
        this.currentMethod = _currentMethod;
        this.resultsTable = _resultsTable;
        this.results = new CustList<ResTestRow>();
        this.progressBar = _progressBar;
    }
    public static StringMap<String> valExecOptionsTable(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_TABLE).getMapping();
    }
    public static StringMap<String> valExecOptionsSimple(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_TABLE).getMapping();
    }
    public static StringMap<String> valExecOptionsSimpleMes(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_SIMPLE_MES).getMapping();
    }
    public static StringMap<String> valExecOptionsMain(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_MAIN).getMapping();
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(EXEC_OPTIONS_TABLE,enExecOptionsTable());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE,enExecOptionsSimple());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE_MES,enExecOptionsSimpleMes());
        _a.getMapping().addEntry(EXEC_OPTIONS_MAIN,enExecOptionsMain());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(EXEC_OPTIONS_TABLE,frExecOptionsTable());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE,frExecOptionsSimple());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE_MES,frExecOptionsSimpleMes());
        _a.getMapping().addEntry(EXEC_OPTIONS_MAIN,frExecOptionsMain());
        return _a;
    }
    public static TranslationsFile enExecOptionsTable(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_TABLE_SUCCESS,"Success");
        e_.add(EXEC_OPTIONS_TABLE_FAIL,"Fail");
        e_.add(EXEC_OPTIONS_TABLE_MS,"ms");
        e_.add(EXEC_OPTIONS_TABLE_NUMBER,"Number");
        e_.add(EXEC_OPTIONS_TABLE_METHOD,"Method");
        e_.add(EXEC_OPTIONS_TABLE_PARAMS,"Parameters");
        e_.add(EXEC_OPTIONS_TABLE_STATUS,"Status");
        e_.add(EXEC_OPTIONS_TABLE_CONFIGURATION,"Configuration");
        e_.add(EXEC_OPTIONS_TABLE_FILE,"File");
        e_.add(EXEC_OPTIONS_TABLE_OPEN,"Open");
        e_.add(EXEC_OPTIONS_TABLE_LAUNCH,"Launch");
        e_.add(EXEC_OPTIONS_TABLE_STOP,"Stop");
        e_.add(EXEC_OPTIONS_TABLE_TESTS,"Tests");
        e_.add(EXEC_OPTIONS_TABLE_TITLE,"Unit tests");
        return e_;
    }
    public static TranslationsFile frExecOptionsTable(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_TABLE_SUCCESS,"Succès");
        f_.add(EXEC_OPTIONS_TABLE_FAIL,"Échec");
        f_.add(EXEC_OPTIONS_TABLE_MS,"ms");
        f_.add(EXEC_OPTIONS_TABLE_NUMBER,"Numéro");
        f_.add(EXEC_OPTIONS_TABLE_METHOD,"Méthode");
        f_.add(EXEC_OPTIONS_TABLE_PARAMS,"Paramètres");
        f_.add(EXEC_OPTIONS_TABLE_STATUS,"Statut");
        f_.add(EXEC_OPTIONS_TABLE_CONFIGURATION,"Configuration");
        f_.add(EXEC_OPTIONS_TABLE_FILE,"Fichier");
        f_.add(EXEC_OPTIONS_TABLE_OPEN,"Ouvrir");
        f_.add(EXEC_OPTIONS_TABLE_LAUNCH,"Lancer");
        f_.add(EXEC_OPTIONS_TABLE_STOP,"Stop");
        f_.add(EXEC_OPTIONS_TABLE_TESTS,"Tests");
        f_.add(EXEC_OPTIONS_TABLE_TITLE,"Tests unitaires");
        return f_;
    }
    public static TranslationsFile enExecOptionsSimple(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_SIMPLE_LAUNCH,"Launch by file");
        e_.add(EXEC_OPTIONS_SIMPLE_FOLDER,"Folder");
        e_.add(EXEC_OPTIONS_SIMPLE_SRC,"Sources");
        e_.add(EXEC_OPTIONS_SIMPLE_FILES,"Data");
        return e_;
    }
    public static TranslationsFile frExecOptionsSimple(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_SIMPLE_LAUNCH,"Lancer par fichier");
        f_.add(EXEC_OPTIONS_SIMPLE_FOLDER,"Dossier");
        f_.add(EXEC_OPTIONS_SIMPLE_SRC,"Sources");
        f_.add(EXEC_OPTIONS_SIMPLE_FILES,"Données");
        return f_;
    }
    public static TranslationsFile enExecOptionsSimpleMes(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_PATH,"Fail loading {0} because {1} is not absolute");
        e_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_CONTENT,"Fail loading {0} because of error of reading");
        e_.add(EXEC_OPTIONS_SIMPLE_MES_SUCCESS,"Successful loading {0}");
        return e_;
    }
    public static TranslationsFile frExecOptionsSimpleMes(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_PATH,"Chargement échoué {0} à cause que {1} n''est pas absolu");
        f_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_CONTENT,"Chargement échoué {0} à cause d''erreur de lecture");
        f_.add(EXEC_OPTIONS_SIMPLE_MES_SUCCESS,"Chargement réussi {0}");
        return f_;
    }
    public static TranslationsFile enExecOptionsMain(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_MAIN_ARCHIVE,"Archive");
        e_.add(EXEC_OPTIONS_MAIN_MEMORY,"Memory");
        return e_;
    }
    public static TranslationsFile frExecOptionsMain(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_MAIN_ARCHIVE,"Archive");
        f_.add(EXEC_OPTIONS_MAIN_MEMORY,"Mémoire");
        return f_;
    }
    public StringMap<String> getMessages() {
        return valExecOptionsTable(messages.currentLg());
    }

    @Override
    public void achieve() {
        setCurrent(getMax());
    }

    public int getMax() {
        return progressBar.getMaximum();
    }

    public String calls() {
        return doneTestsCalls.getText();
    }
    @Override
    public void setCalls(long _c) {
        doneTestsCalls.setText(Long.toString(NumberUtil.max(0,_c)));
        resultsTable.setRowCount(NumberUtil.max(0,(int)_c));
    }

    public void setMax(int _m) {
        this.progressBar.setMaximum(_m);
    }

    public int getMin() {
        return progressBar.getMinimum();
    }

    public void setMin(int _m) {
        this.progressBar.setMinimum(_m);
    }

    public int getCurrent() {
        return progressBar.getValue();
    }

    public void setCurrent(int _v) {
        this.progressBar.setValue(_v);
    }

    public String getCurrentMethod() {
        return currentMethod.getText();
    }

    public void setCurrentMethod(String _c) {
        this.currentMethod.setText(_c);
    }

    public String getDoneTestsCount() {
        return doneTestsCount.getText();
    }

    public void setDoneTestsCount(String _d) {
        this.doneTestsCount.setText(_d);
    }

    public CustList<ResTestRow> getResults() {
        return results;
    }

    @Override
    public void add(ResTestRow _res, ContextEl _ctx) {
        int cur_ = _res.getNumber();
        setValueAt(Long.toString(cur_),cur_,0);
        String methodInfo_ = _res.getMethod();
        setValueAt(methodInfo_,cur_,1);
        setValueAt(_res.getMethodParams(),cur_,2);
        setValueAt(_res.getResultSuccess(),cur_,3);
        results.add(_res);
        StringBuilder build_ = new StringBuilder();
        build_.append(Long.toString(cur_)+"\n");
        build_.append(methodInfo_ + "\n");
        build_.append(_res.getResultSuccessLong()).append("\n");
        build_.append(_res.getErrMess()+"\n");
        build_.append(_res.getMethodParams()+"\n");
        build_.append("\n="+_res.getTime()+" "+valExecOptionsTable(messages.currentLg()).getVal(EXEC_OPTIONS_TABLE_MS)+"\n");
        resultsArea.append(build_.toString());
    }

    @Override
    public void setValueAt(String _v, int _i, int _j) {
        resultsTable.setValueAt(_v, _i, _j);
    }

    @Override
    public String success() {
        return valExecOptionsTable(messages.currentLg()).getVal(EXEC_OPTIONS_TABLE_SUCCESS);
    }

    @Override
    public String fail() {
        return valExecOptionsTable(messages.currentLg()).getVal(EXEC_OPTIONS_TABLE_FAIL);
    }

}
