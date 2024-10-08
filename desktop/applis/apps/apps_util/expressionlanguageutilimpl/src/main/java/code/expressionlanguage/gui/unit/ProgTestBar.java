package code.expressionlanguage.gui.unit;

import code.expressionlanguage.ContextEl;
import code.gui.AbsPlainLabel;
import code.gui.AbsProgressBar;
import code.gui.AbsTableGui;
import code.gui.AbsTextArea;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ProgTestBar implements ProgTestBarInt{
    public static final String LINE_RETURN = "\n";
    public static final String FLAG_SUCCESS = "\u2713";
    public static final String FLAG_FAIL = "\u2716";
    private final AbsPlainLabel doneTestsCalls;
    private final AbsPlainLabel doneTestsCount;

    private final AbsPlainLabel currentMethod;
    private final AbsTableGui resultsTable;
    private final AbsTextArea resultsArea;
    private final CustList<ResTestRow> results;
    private final AbsProgressBar progressBar;

    public ProgTestBar(AbsPlainLabel _doneTestsCalls, AbsPlainLabel _doneTestsCount, AbsPlainLabel _currentMethod,
                       AbsTableGui _resultsTable, AbsTextArea _results, AbsProgressBar _progressBar) {
        this.doneTestsCalls = _doneTestsCalls;
        this.doneTestsCount = _doneTestsCount;
        this.resultsArea = _results;
        this.currentMethod = _currentMethod;
        this.resultsTable = _resultsTable;
        this.results = new CustList<ResTestRow>();
        this.progressBar = _progressBar;
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
        build_.append(Long.toString(cur_)+ LINE_RETURN);
        build_.append(methodInfo_ + LINE_RETURN);
        build_.append(_res.getResultSuccessLong()).append(LINE_RETURN);
        build_.append(_res.getErrMess()+ LINE_RETURN);
        build_.append(_res.getMethodParams()+ LINE_RETURN);
        build_.append(LINE_RETURN +"="+formatSecond(_res.getTime())+ LINE_RETURN);
        resultsArea.append(build_.toString());
    }

    public static String formatSecond(long _millis) {
        if (_millis <= 0) {
            return "0";
        }
        if (_millis <= 9) {
            return "0.00"+_millis;
        }
        if (_millis <= 99) {
            return "0.0"+_millis;
        }
        if (_millis <= 999) {
            return "0."+_millis;
        }
        StringBuilder str_ = new StringBuilder(Long.toString(_millis));
        str_.insert(str_.length()-3,".");
        return str_.toString();
    }
    @Override
    public void setValueAt(String _v, int _i, int _j) {
        resultsTable.setValueAt(_v, _i, _j);
    }

    @Override
    public String success() {
        return FLAG_SUCCESS;
    }

    @Override
    public String fail() {
        return FLAG_FAIL;
    }

}
