package code.expressionlanguage.exec;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.MethodHeaders;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class Classes {

    private final StringMap<ExecRootBlock> classesBodies;
    private final StringMap<String> resources;

    private StringMap<StringMap<Struct>> staticFields;
    private final StringMap<ClassMethodId> toStringMethodsToCall = new StringMap<ClassMethodId>();

    private DefaultLockingClass locks;
    private String iteratorVarCust;
    private String hasNextVarCust;
    private String nextVarCust;
    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private CustList<ExecOperationNode> expsIteratorCust;
    private CustList<ExecOperationNode> expsHasNextCust;
    private CustList<ExecOperationNode> expsNextCust;
    private CustList<ExecOperationNode> expsIteratorTableCust;
    private CustList<ExecOperationNode> expsHasNextPairCust;
    private CustList<ExecOperationNode> expsNextPairCust;
    private CustList<ExecOperationNode> expsFirstCust;
    private CustList<ExecOperationNode> expsSecondCust;
    private CustList<ExecOperatorBlock> operators;

    public Classes(){
        classesBodies = new StringMap<ExecRootBlock>();
        resources = new StringMap<String>();
        staticFields = new StringMap<StringMap<Struct>>();
        operators = new CustList<ExecOperatorBlock>();
    }


    public StringMap<String> getResources() {
		return resources;
	}

    public void addResources(StringMap<String> _resources) {
    	for (EntryCust<String, String> e: _resources.entryList()) {
    		resources.addEntry(e.getKey(), e.getValue());
    	}
    }
    /**Resources are possibly added before analyzing file types*/
    public static ReportedMessages validateAll(StringMap<String> _files, ContextEl _context) {
        validateWithoutInit(_files, _context);
        ReportedMessages messages_ = _context.getAnalyzing().getMessages();
        if (!_context.isEmptyErrors()) {
            //all errors are logged here
            return messages_;
        }
        _context.setNullAnalyzing();
        tryInitStaticlyTypes(_context);
        return messages_;
    }
    public static void validateWithoutInit(StringMap<String> _files, ContextEl _context) {
        if (!_context.isEmptyStdError() || !_context.isEmptyMessageError()) {
            //all standards errors are logged here
            return;
        }
        MethodHeaders headers_ = _context.getAnalyzing().getHeaders();
        _context.setAnalyzing();
        _context.getAnalyzing().setHeaders(headers_);
        ClassesUtil.buildAllBracesBodies(_files,_context);
        ClassesUtil.postValidation(_context);
        if (_context.isGettingErrors()) {
            _context.getOptions().setErrors(ExecFileBlock.errors(_context));
        }
    }

    public static void tryInitStaticlyTypes(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        DefaultLockingClass dl_ = cl_.getLocks();
        dl_.init(_context);
        for (ExecRootBlock c: cl_.getClassBodies()) {
            c.reduce(_context);
            for (ExecBlock d:ExecBlock.getDirectChildrenNotType(c)) {
                for (ExecBlock b: ClassesUtil.getSortedDescNodes(d)) {
                    if (b instanceof ReducableOperations) {
                        ((ReducableOperations)b).reduce(_context);
                    }
                }
            }
        }
        for (ExecOperatorBlock o: cl_.getOperators()) {
            o.reduce(_context);
            for (ExecBlock d:ExecBlock.getDirectChildrenNotType(o)) {
                for (ExecBlock b: ClassesUtil.getSortedDescNodes(d)) {
                    if (b instanceof ReducableOperations) {
                        ((ReducableOperations)b).reduce(_context);
                    }
                }
            }
        }
        CustList<String> all_ = cl_.classesBodies.getKeys();
        _context.getInitializingTypeInfos().setInitEnums(InitPhase.READ_ONLY_OTHERS);
        while (true) {
            StringList new_ = new StringList();
            for (String c: all_) {
                _context.getInitializingTypeInfos().resetInitEnums(_context);
                StringMap<StringMap<Struct>> bk_ = buildFieldValues(cl_.staticFields);
                ProcessMethod.initializeClassPre(c, _context);
                if (_context.isFailInit()) {
                    cl_.staticFields = bk_;
                } else {
                    new_.add(c);
                }
            }
            StringList.removeAllElements(all_, new_);
            if (new_.isEmpty()) {
                break;
            }
        }
        _context.getInitializingTypeInfos().resetInitEnums(_context);
        _context.getInitializingTypeInfos().setInitEnums(InitPhase.LIST);
        dl_.initAlwaysSuccess();
        for (String t: _context.getOptions().getTypesInit()) {
            String res_ = StringExpUtil.removeDottedSpaces(t);
            if (_context.getClasses().getClassBody(res_) == null) {
                continue;
            }
            _context.getInitializingTypeInfos().resetInitEnums(_context);
            ProcessMethod.initializeClass(res_,_context);
        }
        _context.getInitializingTypeInfos().resetInitEnums(_context);
        StringList notInit_ = dl_.initAlwaysSuccess();
        if (_context.getOptions().isFailIfNotAllInit()) {
            for (String s: notInit_) {
                ExecRootBlock r_ = cl_.getClassBody(s);
                ExecFileBlock file_ = r_.getFile();
                FoundErrorInterpret n_ = new FoundErrorInterpret();
                n_.setFileName(file_.getFileName());
                n_.setIndexFile(r_.getIdRowCol());
                n_.buildError(_context.getAnalysisMessages().getNotInitClass(),
                        s);
                _context.getOptions().addError(n_);
            }
        }
        _context.getInitializingTypeInfos().setInitEnums(InitPhase.NOTHING);
    }

    private static StringMap<StringMap<Struct>> buildFieldValues(StringMap<StringMap<Struct>> _infos) {
        StringMap<StringMap<Struct>> bkSt_ = new StringMap<StringMap<Struct>>();
        for (EntryCust<String, StringMap<Struct>> e: _infos.entryList()) {
            StringMap<Struct> b_ = new StringMap<Struct>();
            for (EntryCust<String, Struct> f: e.getValue().entryList()) {
                b_.addEntry(f.getKey(), f.getValue());
            }
            bkSt_.addEntry(e.getKey(), b_);
        }
        return bkSt_;
    }
    public CustList<ExecOperatorBlock> getOperators() {
        return operators;
    }


    public CustList<ExecRootBlock> getClassBodies() {
        return classesBodies.values();
    }

    public StringMap<ExecRootBlock> getClassBodiesMap() {
        return classesBodies;
    }
    public ExecRootBlock getClassBody(String _className) {
        return classesBodies.getVal(_className);
    }


    public boolean isInitialized(String _name) {
        return getLocks().getState(_name) != InitClassState.NOT_YET;
    }
    public boolean isSuccessfulInitialized(String _name) {
        return getLocks().getState(_name) == InitClassState.SUCCESS;
    }

    public void initializeStaticField(ClassField _clField, Struct _str) {
        getStaticFieldMap(_clField.getClassName()).set(_clField.getFieldName(), _str);
    }

    public Struct getStaticField(ClassField _clField, String _returnType, ContextEl _context) {
        Struct strInit_ = getStaticField(_clField);
        if (strInit_ != null) {
            return strInit_;
        }
        return PrimitiveTypeUtil.defaultClass(_returnType, _context);
    }
    public Struct getStaticField(ClassField _clField) {
        StringMap<Struct> map_ = getStaticFieldMap(_clField.getClassName());
        if (map_.isEmpty()) {
            return null;
        }
        return map_.getVal(_clField.getFieldName());
    }
    public StringMap<Struct> getStaticFieldMap(String _clField) {
        StringMap<Struct> map_ = staticFields.getVal(_clField);
        if (map_ == null) {
            map_ = new StringMap<Struct>();
        }
        return map_;
    }

    public StringMap<StringMap<Struct>> getStaticFields() {
        return staticFields;
    }

    public DefaultLockingClass getLocks() {
        return locks;
    }
    public void setLocks(DefaultLockingClass _locks) {
        locks = _locks;
    }

    public String getIteratorVarCust() {
        return iteratorVarCust;
    }

    public void setIteratorVarCust(String _iteratorVarCust) {
        iteratorVarCust = _iteratorVarCust;
    }

    public String getHasNextVarCust() {
        return hasNextVarCust;
    }

    public void setHasNextVarCust(String _hasNextVarCust) {
        hasNextVarCust = _hasNextVarCust;
    }

    public String getNextVarCust() {
        return nextVarCust;
    }

    public void setNextVarCust(String _nextVarCust) {
        nextVarCust = _nextVarCust;
    }

    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }
    public void setIteratorTableVarCust(String _iteratorTableVarCust) {
        iteratorTableVarCust = _iteratorTableVarCust;
    }
    public String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }
    public void setHasNextPairVarCust(String _hasNextPairVarCust) {
        hasNextPairVarCust = _hasNextPairVarCust;
    }
    public String getNextPairVarCust() {
        return nextPairVarCust;
    }
    public void setNextPairVarCust(String _nextPairVarCust) {
        nextPairVarCust = _nextPairVarCust;
    }
    public String getFirstVarCust() {
        return firstVarCust;
    }
    public void setFirstVarCust(String _firstVarCust) {
        firstVarCust = _firstVarCust;
    }
    public String getSecondVarCust() {
        return secondVarCust;
    }
    public void setSecondVarCust(String _secondVarCust) {
        secondVarCust = _secondVarCust;
    }
    public CustList<ExecOperationNode> getExpsIteratorCust() {
        return expsIteratorCust;
    }

    public void setExpsIteratorCust(CustList<ExecOperationNode> _expsIteratorCust) {
        expsIteratorCust = _expsIteratorCust;
    }

    public CustList<ExecOperationNode> getExpsHasNextCust() {
        return expsHasNextCust;
    }

    public void setExpsHasNextCust(CustList<ExecOperationNode> _expsHasNextCust) {
        expsHasNextCust = _expsHasNextCust;
    }

    public CustList<ExecOperationNode> getExpsNextCust() {
        return expsNextCust;
    }

    public void setExpsNextCust(CustList<ExecOperationNode> _expsNextCust) {
        expsNextCust = _expsNextCust;
    }
    public CustList<ExecOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }
    public void setExpsIteratorTableCust(
            CustList<ExecOperationNode> _expsIteratorTableCust) {
        expsIteratorTableCust = _expsIteratorTableCust;
    }
    public CustList<ExecOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }
    public void setExpsHasNextPairCust(CustList<ExecOperationNode> _expsHasNextPairCust) {
        expsHasNextPairCust = _expsHasNextPairCust;
    }
    public CustList<ExecOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }
    public void setExpsNextPairCust(CustList<ExecOperationNode> _expsNextPairCust) {
        expsNextPairCust = _expsNextPairCust;
    }
    public CustList<ExecOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }
    public void setExpsFirstCust(CustList<ExecOperationNode> _expsFirstCust) {
        expsFirstCust = _expsFirstCust;
    }
    public CustList<ExecOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }
    public void setExpsSecondCust(CustList<ExecOperationNode> _expsSecondCust) {
        expsSecondCust = _expsSecondCust;
    }

    public StringMap<ClassMethodId> getToStringMethodsToCall() {
        return toStringMethodsToCall;
    }

    public StringMap<ExecRootBlock> getClassesBodies() {
        return classesBodies;
    }

}
