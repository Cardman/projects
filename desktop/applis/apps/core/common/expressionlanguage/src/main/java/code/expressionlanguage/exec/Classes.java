package code.expressionlanguage.exec;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.PolymorphMethod;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.StringUtil;

public final class Classes {

    private final ClassesCommon common;
    private final StringMap<ExecRootBlock> classesBodies;

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
    private ExecRootBlock seedDoubleGenerator;
    private ExecNamedFunctionBlock seedDoublePick;
    private ExecRootBlock seedGenerator;
    private ExecNamedFunctionBlock seedPick;
    private final CustList<ClassMetaInfo> classMetaInfos = new CustList<ClassMetaInfo>();
    private String keyWordValue = "";

    public Classes(ClassesCommon _common){
        common = _common;
        classesBodies = new StringMap<ExecRootBlock>();
        operators = new CustList<ExecOperatorBlock>();
    }


    public StringMap<String> getResources() {
		return getCommon().getResources();
	}

    /**Resources are possibly added before analyzing file types*/
    public static ReportedMessages validateAll(StringMap<String> _files, ContextEl _context, AnalyzedPageEl _page, Forwards _forwards) {
        validateWithoutInit(_files, _page);
        ReportedMessages messages_ = _page.getMessages();
        if (!_page.isEmptyErrors()) {
            //all errors are logged here
            return messages_;
        }
        forwardAndClear(_context, _page, _forwards);
        Options options_ = _page.getOptions();
        tryInitStaticlyTypes(_context, options_);
        return messages_;
    }

    public static void forwardAndClear(ContextEl _context, AnalyzedPageEl _analyzing, Forwards _forwards) {
        _context.forwardAndClear(_analyzing, _forwards);
    }

    public static void validateWithoutInit(StringMap<String> _files, AnalyzedPageEl _page) {
        if (!_page.isEmptyErrors()) {
            //all standards errors are logged here
            return;
        }
        ClassesUtil.buildAllBracesBodies(_files, _page);
        ClassesUtil.postValidation(_page);
        if (_page.isGettingErrors()) {
            _page.getToStringOwners().add(_page.getAliasObject());
            for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
                String fullName_ = e.getKey().getFullName();
                _page.getToStringOwners().add(fullName_);
            }
            for (EntryCust<String, FileBlock> f: _page.getFilesBodies().entryList()) {
                FileBlock content_ = f.getValue();
                _page.getErrors().putFile(content_, _page);
            }
            ReportedMessages messages_ = _page.getMessages();
            messages_.setErrors(FileBlock.errors(_page));
        }
    }

    public static void tryInitStaticlyTypes(ContextEl _context, Options _options) {
        Classes cl_ = _context.getClasses();
        for (ClassMetaInfo c: cl_.getClassMetaInfos()) {
            String name_ = c.getName();
            ClassMetaInfo.forward(ExecutingUtil.getClassMetaInfo(_context,name_),c);
        }
        DefaultLockingClass dl_ = _context.getLocks();
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
        _context.setExiting(new DefaultExiting(_context));
        _context.getInitializingTypeInfos().setInitEnums(InitPhase.READ_ONLY_OTHERS);
        while (true) {
            StringList new_ = new StringList();
            for (String c: all_) {
                _context.getInitializingTypeInfos().resetInitEnums(_context);
                StringMap<StringMap<Struct>> bk_ = buildFieldValues(cl_.common.getStaticFields());
                ProcessMethod.initializeClassPre(c,cl_.getClassBody(c), _context);
                if (_context.isFailInit()) {
                    cl_.common.setStaticFields(bk_);
                } else {
                    new_.add(c);
                }
            }
            StringUtil.removeAllElements(all_, new_);
            if (new_.isEmpty()) {
                break;
            }
        }
        _context.getInitializingTypeInfos().resetInitEnums(_context);
        _context.getInitializingTypeInfos().setInitEnums(InitPhase.LIST);
        dl_.initAlwaysSuccess();
        for (String t: _options.getTypesInit()) {
            String res_ = StringExpUtil.removeDottedSpaces(t);
            ExecRootBlock classBody_ = _context.getClasses().getClassBody(res_);
            if (classBody_ == null) {
                continue;
            }
            _context.getInitializingTypeInfos().resetInitEnums(_context);
            ProcessMethod.initializeClass(res_,classBody_,_context);
        }
        _context.getInitializingTypeInfos().resetInitEnums(_context);
        _context.getInitializingTypeInfos().setInitEnums(InitPhase.NOTHING);
        _context.setExiting(new NoExiting());
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

    public ExecRootBlock getClassBody(String _className) {
        return classesBodies.getVal(_className);
    }


    public Struct getStaticField(ClassField _clField, String _returnType, ContextEl _context) {
        StringMap<StringMap<Struct>> staticFields_ = getStaticFields();
        Struct strInit_ = getStaticField(_clField, staticFields_);
        if (strInit_ != null) {
            return strInit_;
        }
        return ExecClassArgumentMatching.defaultValue(_returnType, _context);
    }

    public static Struct getStaticField(ClassField _clField, StringMap<StringMap<Struct>> staticFields_) {
        StringMap<Struct> map_ = getStaticFieldMap(_clField.getClassName(), staticFields_);
        if (map_.isEmpty()) {
            return null;
        }
        return map_.getVal(_clField.getFieldName());
    }

    public static StringMap<Struct> getStaticFieldMap(String _clField, StringMap<StringMap<Struct>> _map) {
        StringMap<Struct> map_ = _map.getVal(_clField);
        if (map_ == null) {
            map_ = new StringMap<Struct>();
        }
        return map_;
    }

    public StringMap<StringMap<Struct>> getStaticFields() {
        return getCommon().getStaticFields();
    }

    public ClassesCommon getCommon() {
        return common;
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

    public StringMap<PolymorphMethod> getToStringMethodsToCallBodies() {
        return getCommon().getToStringMethodsToCallBodies();
    }

    public StringMap<ExecRootBlock> getClassesBodies() {
        return classesBodies;
    }

    public CustList<ClassMetaInfo> getClassMetaInfos() {
        return classMetaInfos;
    }

    public ExecNamedFunctionBlock getSeedDoublePick() {
        return seedDoublePick;
    }

    public void setSeedDoublePick(ExecNamedFunctionBlock seedDoublePick) {
        this.seedDoublePick = seedDoublePick;
    }

    public ExecNamedFunctionBlock getSeedPick() {
        return seedPick;
    }

    public void setSeedPick(ExecNamedFunctionBlock seedPick) {
        this.seedPick = seedPick;
    }

    public ExecRootBlock getSeedDoubleGenerator() {
        return seedDoubleGenerator;
    }

    public void setSeedDoubleGenerator(ExecRootBlock seedDoubleGenerator) {
        this.seedDoubleGenerator = seedDoubleGenerator;
    }

    public ExecRootBlock getSeedGenerator() {
        return seedGenerator;
    }

    public void setSeedGenerator(ExecRootBlock seedGenerator) {
        this.seedGenerator = seedGenerator;
    }

    public String getKeyWordValue() {
        return keyWordValue;
    }

    public void setKeyWordValue(String keyWordValue) {
        this.keyWordValue = keyWordValue;
    }
}
