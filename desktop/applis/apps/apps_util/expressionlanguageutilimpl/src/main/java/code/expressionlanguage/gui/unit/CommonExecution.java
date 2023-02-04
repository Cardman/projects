package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.AtomicIntegerStruct;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.gui.GuiBaseUtil;
import code.threads.AbstractThreadFactory;

public final class CommonExecution {
    private final ProgTestBarInt progTestBar;

    public CommonExecution(ProgTestBarInt _prog) {
        progTestBar = _prog;
    }

    public static String getDateTimeText(String _separatorDate, String _sep, String _separatorTime, AbstractThreadFactory _fact) {
        return GuiBaseUtil.getTimeText(_fact, _separatorDate, _sep, _separatorTime);
    }

    public void showProgress(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        String infoTest_ = _evolved.getCustAliases().getAliasInfoTest();
        String infoTestDone_ = _evolved.getCustAliases().getAliasInfoTestDone();
        String infoTestCount_ = _evolved.getCustAliases().getAliasInfoTestCount();
        String infoTestCalls_ = _evolved.getCustAliases().getAliasInfoTestCalls();
        String curMethodName_ = _evolved.getCustAliases().getAliasInfoTestCurrentMethod();
        Struct done_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestDone_)).getStruct();
        Struct calls_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCalls_)).getStruct();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        Struct method_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, curMethodName_)).getStruct();
        progTestBar.setMin(0);
        progTestBar.setMax(((NumberStruct)count_).intStruct());
        progTestBar.setDoneTestsCount(((AtomicIntegerStruct)done_).getInstance().get()+"/"+((NumberStruct)count_).longStruct());
        progTestBar.setCurrent(((AtomicIntegerStruct)done_).getInstance().get());
        progTestBar.setCalls(((NumberStruct)calls_).longStruct());
        if (method_ instanceof MethodMetaInfo) {
            progTestBar.setCurrentMethod(((MethodMetaInfo) method_).getSignature(_ctx));
        }
    }
    public void finish(Struct _infos, LgNamesWithNewAliases _evolved) {
        String infoTest_ = _evolved.getCustAliases().getAliasInfoTest();
        String infoTestCount_ = _evolved.getCustAliases().getAliasInfoTestCount();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        progTestBar.setDoneTestsCount(((NumberStruct)count_).longStruct()+"/"+((NumberStruct)count_).longStruct());
        progTestBar.achieve();
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
            for (int i =0; i < testLen_; i++) {
                ResTestRow resultRow_ = new ResTestRow();
                Struct t = ((ArrayStruct) array_).get(i);
                Struct method_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairFirst_)).getStruct();
                Struct result_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairSecond_)).getStruct();
                resultRow_.setNumber(i);
                resultRow_.setMethod((MethodMetaInfo) method_);
                Struct params_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasParams_)).getStruct();
                resultRow_.setMethodParams(((StringStruct)params_).getInstance());
                Struct success_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasSuccess_)).getStruct();
                resultRow_.setSuccess(BooleanStruct.isTrue(success_));
                Struct time_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasTime_)).getStruct();
                Struct failMessage_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasFailMessage_)).getStruct();
                if (BooleanStruct.isTrue(success_)) {
                    resultRow_.setResultSuccessLong(progTestBar.success());
                    resultRow_.setResultSuccess("x");
                } else {
                    resultRow_.setResultSuccessLong(progTestBar.fail());
                    resultRow_.setResultSuccess("");
                }
                resultRow_.setErrMess(((StringStruct)failMessage_).getInstance());
                resultRow_.setTime(((NumberStruct)time_).longStruct());
                progTestBar.add(resultRow_,_ctx);
            }
        }
    }
}
