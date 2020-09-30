package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class LgNamesUtils extends LgNames implements LgNamesWithNewAliases {
    protected static final String RUN = "Run";
    private CustAliases custAliases = new CustAliases();

    private FileInfos infos;

    public LgNamesUtils(FileInfos _infos) {
        super(_infos.getGenerator());
        custAliases.setInfos(_infos);
        infos = _infos;
    }

    @Override
    public void buildOther() {
        custAliases.buildOther(getContent());
    }
    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }

    @Override
    public Argument defaultInstance(ContextEl _cont, String _id) {
        return custAliases.defaultInstance(_cont,_id);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return custAliases.getOtherResult(_cont,_method,_args);
    }

    @Override
    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        return custAliases.instance(_cont, _method, _args);
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        return custAliases.invoke(_cont, _method, _struct, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        return custAliases.getOtherResult(_cont,_instance,_method,_args);
    }

    public FileInfos getInfos() {
        return infos;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeVarTypes().entryList()) {
            t_.addEntry(o.getKey(),o.getValue());
        }
        return t_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(custAliases.allMergeTableTypeMethodNames(getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeFieldNames().entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames();
        m_.addAllElts(custAliases.allTableTypeMethodParamNames());
        return m_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeMethodNames(getContent()).entryList()) {
            m_.addEntry(o.getKey(),o.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String,String> o: custAliases.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }
    public AbstractFunctionalInstance newFunctionalInstance(String _className, ExecRootBlock _rootBlock,LambdaStruct _functional,ContextEl _contextEl){
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className,ExecRootBlock _rootBlock, LambdaStruct _functional,ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _contextEl);
    }

    public void otherAlias(String _lang, StringMap<String>_cust) {
        custAliases.otherAlias(getContent(),_lang,_cust);
    }
    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        custAliases.allAlias(getContent(),_util,_cust);
    }

    public CustAliases getCustAliases() {
        return custAliases;
    }

}
