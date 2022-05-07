package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;

public final class SplitExpressionUtil {
    private SplitExpressionUtil() {
    }
    public static CustList<IntermediaryResults> getNextResults(AnalyzedPageEl _page) {
        IntermediaryResults int_ = new IntermediaryResults();
        CustList<RootBlock> allInit_ = new CustList<RootBlock>();
        for (RootBlock c: _page.getOuterTypes()) {
            allInit_.addAllElts(walkType(c));
        }
        for (RootBlock c: allInit_) {
            processType(_page, int_, c);
        }
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.setGlobalType(AnaFormattedRootBlock.defValue());
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            processFunction(_page,int_,o, null);
        }
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.setGlobalType(AnaFormattedRootBlock.defValue());
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
        return anonymous(int_,_page);
    }
    public static CustList<IntermediaryResults> anonymous(IntermediaryResults _inter, AnalyzedPageEl _page) {
        IntermediaryResults int_ = _inter;
        CustList<IntermediaryResults> list_ = new CustList<IntermediaryResults>();
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
                AccessedBlock operator_ = c.getAccessedBlock();
                operator_.setCountsAnonFct(operator_.getCountsAnonFct() + 1);
                c.setIntenName(Long.toString(operator_.getCountsAnonFct()));
            }
            for (SwitchMethodBlock c: switchMethods_) {
                AccessedBlock operator_ = c.getAccessedBlock();
                operator_.setCountsAnonFct(operator_.getCountsAnonFct() + 1);
                c.setIntenName(Long.toString(operator_.getCountsAnonFct()));
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
                            AccessedBlock op_ = SplitExpressionUtil.tryGetOperator(_type);
                            if (op_ instanceof AccessedBlockMembers) {
                                ((RootBlock) current_).setAccessedBlock(op_);
                                ((AccessedBlockMembers)op_).getLocalTypes().add((RootBlock) current_);
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
        ClassesUtil.globalType(_page,_type);
        _page.setCurrentPkg(_type.getPackageName());
        _page.setCurrentFile(_type.getFile());
        CustList<AbsBk> bl_ = ClassesUtil.getDirectChildren(_type);
        for (AbsBk b: bl_) {
            if (AbsBk.isAnnotBlock(b)) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String value_ = ((NamedCalledFunctionBlock) b).getDefaultValue();
                ResultExpression resultExpression_ = ((NamedCalledFunctionBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((NamedCalledFunctionBlock) b).getDefaultValueOffset());
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, resultExpression_);
            }
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String value_ = ((FieldBlock) b).getValue();
                ResultExpression resultExpression_ = ((FieldBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((FieldBlock) b).getValueOffset());
                _page.setAccessStaticContext(MethodId.getKind(((FieldBlock) b).isStaticField()));
                extractAnon(_page, _int, null, _type, resultExpression_);
            }
            if (b instanceof ElementBlock) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
                String value_ = ((ElementBlock) b).buildVirtualCreate(keyWordNew_);
                ResultExpression resultExpression_ = ((ElementBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((ElementBlock) b).getFieldNameOffest()+((ElementBlock) b).retrieveTr(keyWordNew_));
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, resultExpression_);
            }
            if (b instanceof InnerElementBlock) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
                String value_ = ((InnerElementBlock) b).buildVirtualCreate(keyWordNew_);
                ResultExpression resultExpression_ = ((InnerElementBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((InnerElementBlock) b).getFieldNameOffest()+((InnerElementBlock) b).retrieveTr(keyWordNew_));
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, resultExpression_);
            }
            if (b instanceof MemberCallingsBlock) {
                processFunction(_page, _int, (MemberCallingsBlock) b, _type);
            }
        }
        for (AbsBk b: bl_) {
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((FieldBlock) b).getAnnotations().getAnnotations().size();
                for (int i = 0; i < len_; i++) {
                    ResultParsedAnnot begin_ = ((FieldBlock) b).getAnnotations().getAnnotations().get(i);
                    _page.zeroOffset();
                    ResultExpression res_ = new ResultExpression();
                    res_.partsAbsol(begin_.getParts());
                    res_.setAnalyzedString(begin_.getAnnotation().trim());
                    res_.setSumOffset(begin_.getIndex());
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    extractAnon(_page, _int, null, _type, res_);
                    ((FieldBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof ElementBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((ElementBlock) b).getAnnotations().getAnnotations().size();
                for (int i = 0; i < len_; i++) {
                    ResultParsedAnnot begin_ = ((ElementBlock) b).getAnnotations().getAnnotations().get(i);
                    _page.zeroOffset();
                    ResultExpression res_ = new ResultExpression();
                    res_.partsAbsol(begin_.getParts());
                    res_.setAnalyzedString(begin_.getAnnotation().trim());
                    res_.setSumOffset(begin_.getIndex());
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    extractAnon(_page, _int, null, _type, res_);
                    ((ElementBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof InnerElementBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((InnerElementBlock) b).getAnnotations().getAnnotations().size();
                for (int i = 0; i < len_; i++) {
                    ResultParsedAnnot begin_ = ((InnerElementBlock) b).getAnnotations().getAnnotations().get(i);
                    _page.zeroOffset();
                    ResultExpression res_ = new ResultExpression();
                    res_.partsAbsol(begin_.getParts());
                    res_.setAnalyzedString(begin_.getAnnotation().trim());
                    res_.setSumOffset(begin_.getIndex());
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    extractAnon(_page, _int, null, _type, res_);
                    ((InnerElementBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof NamedFunctionBlock) {
                processAnnotFct(_page, _int, b, _type);
            }
        }
        if (!(_type instanceof InnerElementBlock)) {
            _page.setCurrentBlock(_type);
            int len_ = _type.getAnnotations().getAnnotations().size();
            for (int i = 0; i < len_; i++) {
                ResultParsedAnnot begin_ = _type.getAnnotations().getAnnotations().get(i);
                _page.zeroOffset();
                ResultExpression res_ = new ResultExpression();
                res_.partsAbsol(begin_.getParts());
                res_.setAnalyzedString(begin_.getAnnotation().trim());
                res_.setSumOffset(begin_.getIndex());
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, null, _type, res_);
                _type.getResList().add(res_);
            }
        }
    }

    private static void processAnnotFct(AnalyzedPageEl _page, IntermediaryResults _int, AbsBk _fct, RootBlock _type) {
        _page.setCurrentBlock(_fct);
        int len_ = ((NamedFunctionBlock) _fct).getAnnotations().getAnnotations().size();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnot begin_ = ((NamedFunctionBlock) _fct).getAnnotations().getAnnotations().get(i);
            _page.zeroOffset();
            ResultExpression res_ = new ResultExpression();
            res_.setAnalyzedString(begin_.getAnnotation().trim());
            res_.setSumOffset(begin_.getIndex());
            res_.partsAbsol(begin_.getParts());
            _page.setAccessStaticContext(MethodAccessKind.STATIC);
            extractAnon(_page, _int, (NamedFunctionBlock) _fct, _type, res_);
            ((NamedFunctionBlock) _fct).getResList().add(res_);
        }
        for (ResultParsedAnnots l: ((NamedFunctionBlock) _fct).getAnnotationsParams()) {
            CustList<ResultExpression> resList_ = new CustList<ResultExpression>();
            len_ = l.getAnnotations().size();
            for (int i = 0; i < len_; i++) {
                ResultParsedAnnot begin_ = l.getAnnotations().get(i);
                _page.zeroOffset();
                ResultExpression res_ = new ResultExpression();
                res_.setAnalyzedString(begin_.getAnnotation().trim());
                res_.setSumOffset(begin_.getIndex());
                res_.partsAbsol(begin_.getParts());
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, (NamedFunctionBlock) _fct, _type, res_);
                resList_.add(res_);
            }
            ((NamedFunctionBlock) _fct).getResLists().add(resList_);
        }
        if (_fct instanceof NamedCalledFunctionBlock) {
            int lenSet_ = ((NamedCalledFunctionBlock) _fct).getAnnotationsSupp().getAnnotations().size();
            for (int i = 0; i < lenSet_; i++) {
                ResultParsedAnnot begin_ = ((NamedCalledFunctionBlock) _fct).getAnnotationsSupp().getAnnotations().get(i);
                _page.zeroOffset();
                ResultExpression res_ = new ResultExpression();
                res_.setAnalyzedString(begin_.getAnnotation().trim());
                res_.setSumOffset(begin_.getIndex());
                res_.partsAbsol(begin_.getParts());
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, (NamedFunctionBlock) _fct, _type, res_);
                ((NamedCalledFunctionBlock) _fct).getResListSupp().add(res_);
            }
        }

    }

    private static void processAnnotSw(AnalyzedPageEl _page, IntermediaryResults _int, SwitchMethodBlock _fct, RootBlock _type) {
        _page.setCurrentBlock(_fct);
        int len_ = _fct.getAnnotations().getAnnotations().size();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnot begin_ = _fct.getAnnotations().getAnnotations().get(i);
            _page.zeroOffset();
            ResultExpression res_ = new ResultExpression();
            res_.partsAbsol(begin_.getParts());
            res_.setAnalyzedString(begin_.getAnnotation().trim());
            res_.setSumOffset(begin_.getIndex());
            _page.setAccessStaticContext(MethodAccessKind.STATIC);
            extractAnon(_page, _int, _fct, _type, res_);
            _fct.getResList().add(res_);
        }
        for (ResultParsedAnnots l: _fct.getAnnotationsParams()) {
            CustList<ResultExpression> resList_ = new CustList<ResultExpression>();
            len_ = l.getAnnotations().size();
            for (int i = 0; i < len_; i++) {
                ResultParsedAnnot begin_ = l.getAnnotations().get(i);
                _page.zeroOffset();
                ResultExpression res_ = new ResultExpression();
                res_.partsAbsol(begin_.getParts());
                res_.setAnalyzedString(begin_.getAnnotation().trim());
                res_.setSumOffset(begin_.getIndex());
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, _fct, _type, res_);
                resList_.add(res_);
            }
            _fct.getResLists().add(resList_);
        }
    }

    private static void processFunction(AnalyzedPageEl _page, IntermediaryResults _int, MemberCallingsBlock _method, RootBlock _type) {
        AbsBk current_ = _method.getFirstChild();
        if (current_ == null) {
            return;
        }
        MethodAccessKind st_ = _method.getStaticContext();
        _page.setAccessStaticContext(st_);
        while (current_ != null) {
            _page.setCurrentBlock(current_);
            if (current_ instanceof Line) {
                _page.zeroOffset();
                String value_ = ((Line) current_).getExpression();
                ResultExpression resultExpression_ = ((Line) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((Line) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            }
            if (current_ instanceof CaseCondition) {
                if (((CaseCondition) current_).getVariableName().isEmpty()) {
                    String value_ = ((CaseCondition) current_).getValue();
                    _page.zeroOffset();
                    ResultExpression resultExpression_ = ((CaseCondition) current_).getRes();
                    resultExpression_.setAnalyzedString(value_);
                    resultExpression_.setSumOffset(((CaseCondition) current_).getValueOffset());
                    extractAnon(_page, _int, _method, _type, resultExpression_);
                } else {
                    if (((CaseCondition) current_).isCaseWhen()) {
                        String substring_ = ((CaseCondition) current_).getCondition();
                        _page.zeroOffset();
                        ResultExpression resultExpression_ = ((CaseCondition) current_).getRes();
                        resultExpression_.setAnalyzedString(substring_);
                        resultExpression_.setSumOffset(((CaseCondition) current_).getConditionOffset());
                        extractAnon(_page, _int, _method, _type, resultExpression_);
                    }
                }
            }
            if (current_ instanceof SwitchBlock) {
                String value_ = ((SwitchBlock) current_).getValue();
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((SwitchBlock) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((SwitchBlock) current_).getValueOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            }
            if (current_ instanceof ConditionBlock) {
                String value_ = ((ConditionBlock) current_).getCondition();
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((ConditionBlock) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((ConditionBlock) current_).getConditionOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            }
            if (current_ instanceof ForEachLoop) {
                String value_ = ((ForEachLoop) current_).getExpression();
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((ForEachLoop) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((ForEachLoop) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            }
            if (current_ instanceof ForEachTable) {
                String value_ = ((ForEachTable) current_).getExpression();
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((ForEachTable) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((ForEachTable) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            }
            if (current_ instanceof ForIterativeLoop) {
                _page.zeroOffset();
                ResultExpression resultInit_ = ((ForIterativeLoop) current_).getResInit();
                resultInit_.setAnalyzedString(((ForIterativeLoop) current_).getInit());
                resultInit_.setSumOffset(((ForIterativeLoop) current_).getInitOffset());
                extractAnon(_page, _int, _method, _type, resultInit_);
                _page.zeroOffset();
                ResultExpression resultExp_ = ((ForIterativeLoop) current_).getResExp();
                resultExp_.setAnalyzedString(((ForIterativeLoop) current_).getExpression());
                resultExp_.setSumOffset(((ForIterativeLoop) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExp_);
                _page.zeroOffset();
                ResultExpression resultStep_ = ((ForIterativeLoop) current_).getResStep();
                resultStep_.setAnalyzedString(((ForIterativeLoop) current_).getStep());
                resultStep_.setSumOffset(((ForIterativeLoop) current_).getStepOffset());
                extractAnon(_page, _int, _method, _type, resultStep_);
            }
            if (current_ instanceof ForMutableIterativeLoop) {
                _page.zeroOffset();
                ResultExpression resultInit_ = ((ForMutableIterativeLoop) current_).getResInit();
                resultInit_.setAnalyzedString(((ForMutableIterativeLoop) current_).getInit());
                resultInit_.setSumOffset(((ForMutableIterativeLoop) current_).getInitOffset());
                extractAnon(_page, _int, _method, _type, resultInit_);
                _page.zeroOffset();
                ResultExpression resultExp_ = ((ForMutableIterativeLoop) current_).getResExp();
                resultExp_.setAnalyzedString(((ForMutableIterativeLoop) current_).getExpression());
                resultExp_.setSumOffset(((ForMutableIterativeLoop) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExp_);
                _page.zeroOffset();
                ResultExpression resultStep_ = ((ForMutableIterativeLoop) current_).getResStep();
                resultStep_.setAnalyzedString(((ForMutableIterativeLoop) current_).getStep());
                resultStep_.setSumOffset(((ForMutableIterativeLoop) current_).getStepOffset());
                extractAnon(_page, _int, _method, _type, resultStep_);
            }
            if (current_ instanceof ReturnMethod) {
                _page.zeroOffset();
                String value_ = ((ReturnMethod) current_).getExpression();
                ResultExpression resultExpression_ = ((ReturnMethod) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((ReturnMethod) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            }
            if (current_ instanceof Throwing) {
                _page.zeroOffset();
                String value_ = ((Throwing) current_).getExpression();
                ResultExpression resultExpression_ = ((Throwing) current_).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((Throwing) current_).getExpressionOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
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

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, MemberCallingsBlock _method, RootBlock _type, ResultExpression _resultExpression) {
        _page.setSumOffset(_resultExpression.getSumOffset());
        ElRetrieverAnonymous.commonCheckQuick(0, _page, _resultExpression);
        feedResult(_method, _resultExpression, _int, _type);
    }

    private static void feedResult(MemberCallingsBlock _mem, ResultExpression _resultExpression, IntermediaryResults _int, RootBlock _type) {
        AccessedBlock op_ = tryGetOperator(_mem);
        feed(_resultExpression, _int, _type, op_);
    }

    public static void feed(ResultExpression _resultExpression, IntermediaryResults _int, RootBlock _type, AccessedBlock _op) {
        for (AnonymousResult a: _resultExpression.getAnonymousResults()) {
            AbsBk type_ = a.getType();
            if (AbsBk.isAnonBlock(type_)) {
                ((NamedCalledFunctionBlock)type_).setParentType(_type);
                ((NamedCalledFunctionBlock)type_).setAccessedBlock(_type);
                if (((NamedCalledFunctionBlock)type_).getParentType() == null) {
                    ((NamedCalledFunctionBlock)type_).setAccessedBlock(_op);
                }
                _int.getAnonymousFunctions().add((NamedCalledFunctionBlock)type_);
            }
            if (type_ instanceof AnonymousTypeBlock) {
                ((AnonymousTypeBlock)type_).setParentType(_type);
                ((AnonymousTypeBlock)type_).setAccessedBlock(_type);
                if (_op instanceof AccessedBlockMembers) {
                    ((AccessedBlockMembers) _op).getAnonymousTypes().add((AnonymousTypeBlock)type_);
                }
                if (((AnonymousTypeBlock)type_).getParentType() == null) {
                    ((AnonymousTypeBlock)type_).setAccessedBlock(_op);
                }
                _int.getAnonymousTypes().add((AnonymousTypeBlock)type_);
            }
            if (type_ instanceof SwitchMethodBlock) {
                ((SwitchMethodBlock)type_).setParentType(_type);
                ((SwitchMethodBlock)type_).setAccessedBlock(_type);
                if (((SwitchMethodBlock)type_).getParentType() == null) {
                    ((SwitchMethodBlock)type_).setAccessedBlock(_op);
                }
                _int.getSwitchMethods().add((SwitchMethodBlock)type_);
            }
        }
    }

    private static AccessedBlock tryGetOperator(BracedBlock _mem) {
        AccessedBlock op_ = null;
        if (AbsBk.isAnonBlock(_mem)) {
            op_ = ((NamedCalledFunctionBlock) _mem).getAccessedBlock();
        }
        if (_mem instanceof OperatorBlock) {
            op_ = (OperatorBlock) _mem;
        }
        if (_mem instanceof SwitchMethodBlock) {
            op_ = ((SwitchMethodBlock) _mem).getAccessedBlock();
        }
        return op_;
    }
}
