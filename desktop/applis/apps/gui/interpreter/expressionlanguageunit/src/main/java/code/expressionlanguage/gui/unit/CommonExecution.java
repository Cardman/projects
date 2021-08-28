package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.gui.*;
import code.util.StringMap;

public final class CommonExecution {
    private final StringMap<String> messages;
    private final PlainLabel doneTestsCount;

    private final PlainLabel currentMethod;
    private final TableGui resultsTable;
    private final AbsTextArea results;
    private final AbsProgressBar progressBar;

    public CommonExecution(StringMap<String> _messages, PlainLabel _doneTestsCount, PlainLabel _currentMethod,
                           TableGui _resultsTable, AbsTextArea _results, AbsProgressBar _progressBar) {
        this.messages = _messages;
        this.doneTestsCount = _doneTestsCount;
        this.currentMethod = _currentMethod;
        this.resultsTable = _resultsTable;
        this.results = _results;
        this.progressBar = _progressBar;
    }

    public void showProgress(ContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count, LgNamesWithNewAliases _evolved) {
        String infoTest_ = _evolved.getCustAliases().getAliasInfoTest();
        String infoTestDone_ = _evolved.getCustAliases().getAliasInfoTestDone();
        String infoTestCount_ = _evolved.getCustAliases().getAliasInfoTestCount();
        String curMethodName_ = _evolved.getCustAliases().getAliasInfoTestCurrentMethod();
        Struct done_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestDone_)).getStruct();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        Struct method_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, curMethodName_)).getStruct();
        if (!count_.sameReference(_count)) {
            progressBar.setMinimum(0);
            progressBar.setMaximum(((NumberStruct)count_).intStruct());
        }
        if (!_doneTests.sameReference(done_)) {
            doneTestsCount.setText(((NumberStruct)done_).longStruct()+"/"+((NumberStruct)count_).longStruct());
            progressBar.setValue(((NumberStruct)done_).intStruct());
        }
        if (!_method.sameReference(method_) && method_ instanceof MethodMetaInfo) {
            currentMethod.setText(((MethodMetaInfo)method_).getSignature(_ctx));
        }
    }
    public void finish(Struct _infos, LgNamesWithNewAliases _evolved) {
        String infoTest_ = _evolved.getCustAliases().getAliasInfoTest();
        String infoTestCount_ = _evolved.getCustAliases().getAliasInfoTestCount();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        doneTestsCount.setText(((NumberStruct)count_).longStruct()+"/"+((NumberStruct)count_).longStruct());
        progressBar.setValue(progressBar.getMaximum());
    }

    public void setResults(ContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        if (!_res.isNull()) {
            Struct results_ = _res.getStruct();
            String tableCl_ = _evolved.getCustAliases().getAliasTable();
            String listTable_ = _evolved.getCustAliases().getAliasListTa();
            Struct list_ = ((FieldableStruct)results_).getEntryStruct(new ClassField(tableCl_,listTable_)).getStruct();
            String listCl_ = _evolved.getCustAliases().getAliasList();
            String arrList_ = _evolved.getCustAliases().getAliasArrayLi();
            Struct array_ = ((FieldableStruct)list_).getEntryStruct(new ClassField(listCl_,arrList_)).getStruct();
            String pairCl_ = _evolved.getCustAliases().getAliasCustPair();
            String pairFirst_ = _evolved.getCustAliases().getAliasFirst();
            String pairSecond_ = _evolved.getCustAliases().getAliasSecond();
            String aliasResult_ = _evolved.getCustAliases().getAliasResult();
            String aliasSuccess_ = _evolved.getCustAliases().getAliasResultSuccess();
            String aliasTime_ = _evolved.getCustAliases().getAliasResultTime();
            String aliasFailMessage_ = _evolved.getCustAliases().getAliasResultFailMessage();
            String aliasParams_ = _evolved.getCustAliases().getAliasResultParams();
            int testLen_ = ((ArrayStruct) array_).getLength();
            resultsTable.setRowCount(testLen_);
            for (int i =0; i < testLen_; i++) {
                Struct t = ((ArrayStruct) array_).get(i);
                Struct method_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairFirst_)).getStruct();
                Struct result_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairSecond_)).getStruct();
                resultsTable.setValueAt(Long.toString(i),i,0);
                results.append(Long.toString(i)+"\n");
                String methodInfo_ = ((MethodMetaInfo) method_).getFormatted().getFormatted() + "." + ((MethodMetaInfo) method_).getSignature(_ctx) + "\n";
                resultsTable.setValueAt(methodInfo_,i,1);
                results.append(methodInfo_);
                Struct params_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasParams_)).getStruct();
                resultsTable.setValueAt(((StringStruct)params_).getInstance(),i,2);
                Struct success_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasSuccess_)).getStruct();
                Struct time_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasTime_)).getStruct();
                Struct failMessage_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasFailMessage_)).getStruct();
                if (BooleanStruct.isTrue(success_)) {
                    results.append(messages.getVal("success")+"\n");
                    resultsTable.setValueAt("x",i,3);
                } else {
                    results.append(messages.getVal("fail")+"\n");
                    resultsTable.setValueAt("",i,3);
                }
                results.append(((StringStruct)failMessage_).getInstance()+"\n");
                results.append(((StringStruct)params_).getInstance()+"\n");
                results.append("\n="+((NumberStruct)time_).longStruct()+" ms\n");
            }
        }
    }
}
