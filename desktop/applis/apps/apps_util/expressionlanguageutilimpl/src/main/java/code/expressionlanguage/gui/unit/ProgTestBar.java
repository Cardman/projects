package code.expressionlanguage.gui.unit;

import code.gui.AbsPlainLabel;
import code.gui.AbsProgressBar;
import code.gui.AbsTableGui;
import code.gui.AbsTextArea;
import code.util.CustList;
import code.util.StringMap;

public final class ProgTestBar implements ProgTestBarInt{
    private final StringMap<String> messages;
    private final AbsPlainLabel doneTestsCount;

    private final AbsPlainLabel currentMethod;
    private final AbsTableGui resultsTable;
    private final AbsTextArea resultsArea;
    private CustList<ResTestRow> results;
    private final AbsProgressBar progressBar;

    public ProgTestBar(StringMap<String> _messages, AbsPlainLabel _doneTestsCount, AbsPlainLabel _currentMethod,
                       AbsTableGui _resultsTable, AbsTextArea _results, AbsProgressBar _progressBar) {
        this.messages = _messages;
        this.doneTestsCount = _doneTestsCount;
        this.resultsArea = _results;
        this.currentMethod = _currentMethod;
        this.resultsTable = _resultsTable;
        this.results = new CustList<ResTestRow>();
        this.progressBar = _progressBar;
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public int getMax() {
        return progressBar.getMaximum();
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

    public void setResults(CustList<ResTestRow> _r) {
        this.results = _r;
    }

    @Override
    public void setRowCount(int _len) {
        resultsTable.setRowCount(_len);
    }

    @Override
    public void setValueAt(String _v, int _i, int _j) {
        resultsTable.setValueAt(_v, _i, _j);
    }

    @Override
    public void success(int _ind) {
        append(messages.getVal("success")+"\n");
    }

    @Override
    public void fail(int _ind) {
        append(messages.getVal("fail")+"\n");
    }

    @Override
    public void append(String _str) {
        resultsArea.append(_str);
    }
}
