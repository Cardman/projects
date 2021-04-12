package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedType;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public final class SplitExpressionUtil {
    private SplitExpressionUtil() {
    }
    public static CustList<IntermediaryResults> getNextResults(AnalyzedPageEl _page) {
        CustList<IntermediaryResults> list_ = new CustList<IntermediaryResults>();
        IntermediaryResults int_ = new IntermediaryResults();
        CustList<RootBlock> allInit_ = new CustList<RootBlock>();
        for (RootBlock c: _page.getOuterTypes()) {
            allInit_.addAllElts(walkType(c));
        }
        for (RootBlock c: allInit_) {
            processType(_page, int_, c);
        }
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.setGlobalType(null);
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            processFunction(_page,int_,o, null);
        }
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.setGlobalType(null);
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            processAnnotFct(_page, int_, o, null);
        }
        allInit_ = new CustList<RootBlock>();
        for (OperatorBlock c: _page.getAllOperators()) {
            allInit_.addAllElts(walkType(c));
        }
        for (RootBlock c: allInit_) {
            processType(_page, int_, c);
        }
        while (!int_.getAnonymousTypes().isEmpty()||!int_.getAnonymousFunctions().isEmpty()||!int_.getSwitchMethods().isEmpty()) {
            list_.add(int_);
            CustList<AnonymousTypeBlock> anonymousTypes_ = int_.getAnonymousTypes();
            CustList<NamedCalledFunctionBlock> anonymousFunctions_ = int_.getAnonymousFunctions();
            CustList<SwitchMethodBlock> switchMethods_ = int_.getSwitchMethods();
            int_ = new IntermediaryResults();
            CustList<RootBlock> all_ = new CustList<RootBlock>();
            for (AnonymousTypeBlock c: anonymousTypes_) {
                RootBlock parentType_ = c.getParentType();
                if (parentType_ != null) {
                    StringMap<Integer> countsAnon_ = parentType_.getCountsAnon();
                    Integer val_ = countsAnon_.getVal(c.getName());
                    if (val_ == null) {
                        countsAnon_.put(c.getName(),1);
                        c.setSuffix("*1");
                    } else {
                        countsAnon_.put(c.getName(),val_+1);
                        c.setSuffix("*"+(val_+1));
                    }
                }
            }
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                RootBlock parentType_ = c.getParentType();
                if (parentType_ != null) {
                    parentType_.setCountsAnonFct(parentType_.getCountsAnonFct() + 1);
                    c.setIntenName(Long.toString(parentType_.getCountsAnonFct()));
                }
                OperatorBlock operator_ = c.getOperator();
                if (operator_ != null) {
                    operator_.setCountsAnonFct(operator_.getCountsAnonFct() + 1);
                    c.setIntenName(Long.toString(operator_.getCountsAnonFct()));
                }
            }
            for (SwitchMethodBlock c: switchMethods_) {
                RootBlock parentType_ = c.getParentType();
                if (parentType_ != null) {
                    parentType_.setCountsAnonFct(parentType_.getCountsAnonFct() + 1);
                    c.setIntenName(Long.toString(parentType_.getCountsAnonFct()));
                }
                OperatorBlock operator_ = c.getOperator();
                if (operator_ != null) {
                    operator_.setCountsAnonFct(operator_.getCountsAnonFct() + 1);
                    c.setIntenName(Long.toString(operator_.getCountsAnonFct()));
                }
            }
            for (RootBlock c: anonymousTypes_) {
                all_.addAllElts(walkType(c));
            }
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                all_.addAllElts(walkType(c));
            }
            for (SwitchMethodBlock c: switchMethods_) {
                all_.addAllElts(walkType(c));
            }
            for (RootBlock c: all_) {
                processType(_page, int_, c);
            }
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                _page.setupFctChars(c);
                processFunction(_page,int_,c, c.getParentType());
            }
            for (SwitchMethodBlock c: switchMethods_) {
                _page.setupFctChars(c);
                processFunction(_page,int_,c, c.getParentType());
            }
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                _page.setupFctChars(c);
                processAnnotFct(_page,int_,c, c.getParentType());
            }
            for (SwitchMethodBlock c: switchMethods_) {
                _page.setupFctChars(c);
                processAnnotSw(_page,int_,c, c.getParentType());
            }
        }
        return list_;
    }

    private static CustList<RootBlock> walkType(BracedBlock _type) {
        CustList<RootBlock> types_ = new CustList<RootBlock>();
        AbsBk current_ = _type.getFirstChild();
        if (current_ == null) {
            if (_type instanceof RootBlock) {
                types_.add((RootBlock) _type);
            }
            return types_;
        }
        current_ = _type;
        while (current_ != null) {
            if (current_ instanceof RootBlock) {
                if (!(current_ instanceof AnonymousTypeBlock)) {
                    MemberCallingsBlock outerFuntion_ = current_.getOuterFuntionInType();
                    if (outerFuntion_ != null) {
                        RootBlock possibleParent_ = ((RootBlock) current_).getParentType();
                        if (possibleParent_ != null) {
                            String s_ = ((RootBlock) current_).getName();
                            Integer val_ = possibleParent_.getCounts().getVal(s_);
                            if (val_ == null) {
                                possibleParent_.getCounts().put(s_,1);
                                ((RootBlock) current_).setSuffix("+1");
                            } else {
                                possibleParent_.getCounts().put(s_,val_+1);
                                ((RootBlock) current_).setSuffix("+"+(val_+1));
                            }
                        } else {
                            OperatorBlock op_ = SplitExpressionUtil.tryGetOperator(_type);
                            if (op_ != null) {
                                ((RootBlock) current_).setOperator(op_);
                                op_.getLocalTypes().add((RootBlock) current_);
                            }
                        }
                    }

                }
                types_.add((RootBlock) current_);
            }
            AbsBk ch_ = current_.getFirstChild();
            if (ch_ != null) {
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                AbsBk next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                BracedBlock parent_ = current_.getParent();
                if (parent_ == _type) {
                    current_ = null;
                    continue;
                }
                current_ = parent_;
            }
        }
        return types_;
    }
    private static void processType(AnalyzedPageEl _page, IntermediaryResults _int, RootBlock _type) {
        _page.setGlobalType(_type);
        _page.setCurrentPkg(_type.getPackageName());
        _page.setCurrentFile(_type.getFile());
        CustList<AbsBk> bl_ = ClassesUtil.getDirectChildren(_type);
        for (AbsBk b: bl_) {
            if (AbsBk.isAnnotBlock(b)) {
                _page.setCurrentBlock(b);
                _page.setGlobalOffset(((NamedCalledFunctionBlock) b).getDefaultValueOffset());
                _page.setOffset(0);
                String value_ = ((NamedCalledFunctionBlock) b).getDefaultValue();
                ResultExpression resultExpression_ = ((NamedCalledFunctionBlock) b).getRes();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, value_, resultExpression_);
            }
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                _page.setGlobalOffset(((FieldBlock) b).getValueOffset());
                _page.setOffset(0);
                String value_ = ((FieldBlock) b).getValue();
                ResultExpression resultExpression_ = ((FieldBlock) b).getRes();
                _page.setAccessStaticContext(MethodId.getKind(((FieldBlock) b).isStaticField()));
                extractAnon(_page, _int, null, _type, value_, resultExpression_);
            }
            if (b instanceof ElementBlock) {
                _page.setCurrentBlock(b);
                _page.setGlobalOffset(((ElementBlock) b).getFieldNameOffest());
                _page.setOffset(0);
                String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
                _page.setTranslatedOffset(((ElementBlock) b).retrieveTr(keyWordNew_));
                String value_ = ((ElementBlock) b).buildVirtualCreate(keyWordNew_);
                ResultExpression resultExpression_ = ((ElementBlock) b).getRes();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, value_, resultExpression_);
                _page.setTranslatedOffset(0);
            }
            if (b instanceof InnerElementBlock) {
                _page.setCurrentBlock(b);
                _page.setGlobalOffset(((InnerElementBlock) b).getFieldNameOffest());
                _page.setOffset(0);
                String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
                _page.setTranslatedOffset(((InnerElementBlock) b).retrieveTr(keyWordNew_));
                String value_ = ((InnerElementBlock) b).buildVirtualCreate(keyWordNew_);
                ResultExpression resultExpression_ = ((InnerElementBlock) b).getRes();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, value_, resultExpression_);
                _page.setTranslatedOffset(0);
            }
            if (b instanceof MemberCallingsBlock) {
                processFunction(_page, _int, (MemberCallingsBlock) b, _type);
            }
        }
        for (AbsBk b: bl_) {
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((FieldBlock) b).getAnnotationsIndexes().size();
                for (int i = 0; i < len_; i++) {
                    int begin_ = ((FieldBlock) b).getAnnotationsIndexes().get(i);
                    _page.setGlobalOffset(begin_);
                    _page.setOffset(0);
                    ResultExpression res_ = new ResultExpression();
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    extractAnon(_page, _int, null, _type, ((FieldBlock) b).getAnnotations().get(i).trim(), res_);
                    ((FieldBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof ElementBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((ElementBlock) b).getAnnotationsIndexes().size();
                for (int i = 0; i < len_; i++) {
                    int begin_ = ((ElementBlock) b).getAnnotationsIndexes().get(i);
                    _page.setGlobalOffset(begin_);
                    _page.setOffset(0);
                    ResultExpression res_ = new ResultExpression();
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    extractAnon(_page, _int, null, _type, ((ElementBlock) b).getAnnotations().get(i).trim(), res_);
                    ((ElementBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof InnerElementBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((InnerElementBlock) b).getAnnotationsIndexes().size();
                for (int i = 0; i < len_; i++) {
                    int begin_ = ((InnerElementBlock) b).getAnnotationsIndexes().get(i);
                    _page.setGlobalOffset(begin_);
                    _page.setOffset(0);
                    ResultExpression res_ = new ResultExpression();
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    extractAnon(_page, _int, null, _type, ((InnerElementBlock) b).getAnnotations().get(i).trim(), res_);
                    ((InnerElementBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof NamedFunctionBlock) {
                processAnnotFct(_page, _int, b, _type);
            }
        }
        if (!(_type instanceof InnerElementBlock)) {
            _page.setCurrentBlock(_type);
            int len_ = _type.getAnnotationsIndexes().size();
            for (int i = 0; i < len_; i++) {
                int begin_ = _type.getAnnotationsIndexes().get(i);
                _page.setGlobalOffset(begin_);
                _page.setOffset(0);
                ResultExpression res_ = new ResultExpression();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, _type.getAnnotations().get(i).trim(), res_);
                _type.getResList().add(res_);
            }
        }
    }

    private static void processAnnotFct(AnalyzedPageEl _page, IntermediaryResults _int, AbsBk _fct, RootBlock _type) {
        _page.setCurrentBlock(_fct);
        int len_ = ((NamedFunctionBlock) _fct).getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = ((NamedFunctionBlock) _fct).getAnnotationsIndexes().get(i);
            _page.setGlobalOffset(begin_);
            _page.setOffset(0);
            ResultExpression res_ = new ResultExpression();
            _page.setAccessStaticContext(MethodAccessKind.STATIC);
            extractAnon(_page, _int, (NamedFunctionBlock) _fct, _type, ((NamedFunctionBlock) _fct).getAnnotations().get(i).trim(), res_);
            ((NamedFunctionBlock) _fct).getResList().add(res_);
        }
        int j_ = 0;
        for (Ints l: ((NamedFunctionBlock) _fct).getAnnotationsIndexesParams()) {
            CustList<ResultExpression> resList_ = new CustList<ResultExpression>();
            len_ = l.size();
            StringList list_ = ((NamedFunctionBlock) _fct).getAnnotationsParams().get(j_);
            for (int i = 0; i < len_; i++) {
                int begin_ = l.get(i);
                _page.setGlobalOffset(begin_);
                _page.setOffset(0);
                ResultExpression res_ = new ResultExpression();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, (NamedFunctionBlock) _fct, _type, list_.get(i).trim(), res_);
                resList_.add(res_);
            }
            ((NamedFunctionBlock) _fct).getResLists().add(resList_);
            j_++;
        }
    }

    private static void processAnnotSw(AnalyzedPageEl _page, IntermediaryResults _int, SwitchMethodBlock _fct, RootBlock _type) {
        _page.setCurrentBlock(_fct);
        int len_ = _fct.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _fct.getAnnotationsIndexes().get(i);
            _page.setGlobalOffset(begin_);
            _page.setOffset(0);
            ResultExpression res_ = new ResultExpression();
            _page.setAccessStaticContext(MethodAccessKind.STATIC);
            extractAnon(_page, _int, _fct, _type, _fct.getAnnotations().get(i).trim(), res_);
            _fct.getResList().add(res_);
        }
        int j_ = 0;
        for (Ints l: _fct.getAnnotationsIndexesParams()) {
            CustList<ResultExpression> resList_ = new CustList<ResultExpression>();
            len_ = l.size();
            StringList list_ = _fct.getAnnotationsParams().get(j_);
            for (int i = 0; i < len_; i++) {
                int begin_ = l.get(i);
                _page.setGlobalOffset(begin_);
                _page.setOffset(0);
                ResultExpression res_ = new ResultExpression();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, _fct, _type, list_.get(i).trim(), res_);
                resList_.add(res_);
            }
            _fct.getResLists().add(resList_);
            j_++;
        }
    }

    private static void processFunction(AnalyzedPageEl _page, IntermediaryResults _int, MemberCallingsBlock _method, RootBlock _type) {
        AbsBk current_ = _method.getFirstChild();
        String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
        if (current_ == null) {
            return;
        }
        MethodAccessKind st_ = _method.getStaticContext();
        _page.setAccessStaticContext(st_);
        while (current_ != null) {
            _page.setCurrentBlock(current_);
            if (current_ instanceof Line) {
                _page.setGlobalOffset(((Line) current_).getExpressionOffset());
                _page.setOffset(0);
                String value_ = ((Line) current_).getExpression();
                ResultExpression resultExpression_ = ((Line) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof CaseCondition) {
                String value_ = ((CaseCondition) current_).getValue();
                ParsedType p_ = new ParsedType();
                p_.parse(value_);
                String varName_ = "";
                if (p_.isOk(new CustList<String>(keyWordNew_))) {
                    String declaringType_ = p_.getInstruction().toString();
                    varName_ = value_.substring(declaringType_.length()).trim();
                }
                if (!StringExpUtil.isTypeLeafPart(varName_)) {
                    _page.setGlobalOffset(((CaseCondition) current_).getValueOffset());
                    _page.setOffset(0);
                    ResultExpression resultExpression_ = ((CaseCondition) current_).getRes();
                    extractAnon(_page, _int, _method, _type, value_, resultExpression_);
                }
            }
            if (current_ instanceof SwitchBlock) {
                String value_ = ((SwitchBlock) current_).getValue();
                _page.setGlobalOffset(((SwitchBlock) current_).getValueOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((SwitchBlock) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof ConditionBlock) {
                String value_ = ((ConditionBlock) current_).getCondition();
                _page.setGlobalOffset(((ConditionBlock) current_).getConditionOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((ConditionBlock) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof ForEachLoop) {
                String value_ = ((ForEachLoop) current_).getExpression();
                _page.setGlobalOffset(((ForEachLoop) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((ForEachLoop) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof ForEachTable) {
                String value_ = ((ForEachTable) current_).getExpression();
                _page.setGlobalOffset(((ForEachTable) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((ForEachTable) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof ForIterativeLoop) {
                _page.setGlobalOffset(((ForIterativeLoop) current_).getInitOffset());
                _page.setOffset(0);
                ResultExpression resultInit_ = ((ForIterativeLoop) current_).getResInit();
                extractAnon(_page, _int, _method, _type, ((ForIterativeLoop) current_).getInit(), resultInit_);
                _page.setGlobalOffset(((ForIterativeLoop) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExp_ = ((ForIterativeLoop) current_).getResExp();
                extractAnon(_page, _int, _method, _type, ((ForIterativeLoop) current_).getExpression(), resultExp_);
                _page.setGlobalOffset(((ForIterativeLoop) current_).getStepOffset());
                _page.setOffset(0);
                ResultExpression resultStep_ = ((ForIterativeLoop) current_).getResStep();
                extractAnon(_page, _int, _method, _type, ((ForIterativeLoop) current_).getStep(), resultStep_);
            }
            if (current_ instanceof ForMutableIterativeLoop) {
                _page.setGlobalOffset(((ForMutableIterativeLoop) current_).getInitOffset());
                _page.setOffset(0);
                ResultExpression resultInit_ = ((ForMutableIterativeLoop) current_).getResInit();
                extractAnon(_page, _int, _method, _type, ((ForMutableIterativeLoop) current_).getInit(), resultInit_);
                _page.setGlobalOffset(((ForMutableIterativeLoop) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExp_ = ((ForMutableIterativeLoop) current_).getResExp();
                extractAnon(_page, _int, _method, _type, ((ForMutableIterativeLoop) current_).getExpression(), resultExp_);
                _page.setGlobalOffset(((ForMutableIterativeLoop) current_).getStepOffset());
                _page.setOffset(0);
                ResultExpression resultStep_ = ((ForMutableIterativeLoop) current_).getResStep();
                extractAnon(_page, _int, _method, _type, ((ForMutableIterativeLoop) current_).getStep(), resultStep_);
            }
            if (current_ instanceof ReturnMethod) {
                _page.setGlobalOffset(((ReturnMethod) current_).getExpressionOffset());
                _page.setOffset(0);
                String value_ = ((ReturnMethod) current_).getExpression();
                ResultExpression resultExpression_ = ((ReturnMethod) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof Throwing) {
                _page.setGlobalOffset(((Throwing) current_).getExpressionOffset());
                _page.setOffset(0);
                String value_ = ((Throwing) current_).getExpression();
                ResultExpression resultExpression_ = ((Throwing) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            }
            if (current_ instanceof BuildableElMethod || current_ instanceof UnclassedBracedBlock) {
                AbsBk ch_ = current_.getFirstChild();
                if (ch_ != null) {
                    current_ = ch_;
                    continue;
                }
            }
            while (current_ != null) {
                AbsBk next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                BracedBlock parent_ = current_.getParent();
                if (parent_ == _method) {
                    current_ = null;
                    continue;
                }
                current_ = parent_;
            }
        }
    }

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, MemberCallingsBlock _method, RootBlock _type, String _value, ResultExpression _resultExpression) {
        ElRetrieverAnonymous.commonCheckQuick(_value, 0, _page, _resultExpression);
        feedResult(_method, _resultExpression, _int, _type);
    }

    private static void feedResult(MemberCallingsBlock _mem, ResultExpression _resultExpression, IntermediaryResults _int, RootBlock _type) {
        OperatorBlock op_ = tryGetOperator(_mem);
        for (AnonymousResult a: _resultExpression.getAnonymousResults()) {
            AbsBk type_ = a.getType();
            if (AbsBk.isAnonBlock(type_)) {
                ((NamedCalledFunctionBlock)type_).setParentType(_type);
                if (((NamedCalledFunctionBlock)type_).getParentType() == null) {
                    ((NamedCalledFunctionBlock)type_).setOperator(op_);
                }
                _int.getAnonymousFunctions().add((NamedCalledFunctionBlock)type_);
            }
            if (type_ instanceof AnonymousTypeBlock) {
                ((AnonymousTypeBlock)type_).setParentType(_type);
                if (op_ != null) {
                    op_.getAnonymousTypes().add((AnonymousTypeBlock)type_);
                }
                if (((AnonymousTypeBlock)type_).getParentType() == null) {
                    ((AnonymousTypeBlock)type_).setOperator(op_);
                }
                _int.getAnonymousTypes().add((AnonymousTypeBlock)type_);
            }
            if (type_ instanceof SwitchMethodBlock) {
                ((SwitchMethodBlock)type_).setParentType(_type);
                if (((SwitchMethodBlock)type_).getParentType() == null) {
                    ((SwitchMethodBlock)type_).setOperator(op_);
                }
                _int.getSwitchMethods().add((SwitchMethodBlock)type_);
            }
        }
    }

    private static OperatorBlock tryGetOperator(BracedBlock _mem) {
        OperatorBlock op_ = null;
        if (AbsBk.isAnonBlock(_mem)) {
            op_ = ((NamedCalledFunctionBlock) _mem).getOperator();
        }
        if (_mem instanceof OperatorBlock) {
            op_ = (OperatorBlock) _mem;
        }
        if (_mem instanceof SwitchMethodBlock) {
            op_ = ((SwitchMethodBlock) _mem).getOperator();
        }
        return op_;
    }
}
