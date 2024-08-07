package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnaBlockCounts;
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
            allInit_.addAllElts(walkType(_page,null,c));
        }
        for (RootBlock c: allInit_) {
            processType(_page, int_, c);
        }
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.globalType(AnaFormattedRootBlock.defValue());
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            processFunction(_page,int_,o, o);
        }
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.globalType(AnaFormattedRootBlock.defValue());
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            processAnnotFct(_page, int_, o, o);
        }
        allInit_ = new CustList<RootBlock>();
        for (OperatorBlock c: _page.getAllOperators()) {
            allInit_.addAllElts(walkType(_page,c,c));
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
            suffixAnonTypes(anonymousTypes_,_page.getCountElts());
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                AccessedBlock operator_ = c.getAccessedBlock();
                long ne_ = incr(operator_, _page);
                c.setIntenName(Long.toString(ne_));
            }
            for (SwitchMethodBlock c: switchMethods_) {
                AccessedBlock operator_ = c.getAccessedBlock();
                long ne_ = incr(operator_, _page);
                c.setIntenName(Long.toString(ne_));
            }
            processAnont(_page, int_, anonymousTypes_, anonymousFunctions_, switchMethods_);
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                _page.setupFctChars(c);
                processFunction(_page,int_,c, c.getAccessedBlock());
            }
            for (SwitchMethodBlock c: switchMethods_) {
                _page.setupFctChars(c);
                processFunction(_page,int_,c, c.getAccessedBlock());
            }
            for (NamedCalledFunctionBlock c: anonymousFunctions_) {
                _page.setupFctChars(c);
                processAnnotFct(_page,int_,c, c.getAccessedBlock());
            }
            for (SwitchMethodBlock c: switchMethods_) {
                _page.setupFctChars(c);
                processAnnotSw(_page,int_,c, c.getAccessedBlock());
            }
        }
        return list_;
    }

    private static long incr(AccessedBlock _operator, AnalyzedPageEl _page) {
        if (_operator == null) {
            long old_ = _page.getCountElts().getCountOut();
            long ne_ = old_ + 1;
            _page.getCountElts().setCountOut(ne_);
            return ne_;
        }
        int index_ = _operator.contentNb().getAccessNb();
        long old_ = _page.getCountElts().getCountAnon().get(index_);
        long ne_ = old_ + 1;
        _page.getCountElts().getCountAnon().set(index_,ne_);
        return ne_;
    }

    private static void suffixAnonTypes(CustList<AnonymousTypeBlock> _anonymousTypes, AnaBlockCounts _countElts) {
        CustList<StringMap<Long>> map_ = _countElts.getCountsAnon();
        for (AnonymousTypeBlock c: _anonymousTypes) {
            RootBlock parentType_ = c.getParentType();
            if (parentType_ != null) {
                ClassesUtil.incre(c,map_.get(parentType_.getCreated()),c.getName(),"*");
            }
        }
        for (AnonymousTypeBlock c: _anonymousTypes) {
            if (c.getAccessedBlock() == null) {
                ClassesUtil.incre(c,_countElts.getCountAnonType(),c.getName(),"*");
            }
        }
    }

    private static void processAnont(AnalyzedPageEl _page, IntermediaryResults _inter, CustList<AnonymousTypeBlock> _anonymousTypes, CustList<NamedCalledFunctionBlock> _anonymousFunctions, CustList<SwitchMethodBlock> _switchMethods) {
        CustList<RootBlock> all_ = new CustList<RootBlock>();
        for (RootBlock c: _anonymousTypes) {
            all_.addAllElts(walkType(_page,c.getAccessedBlock(),c));
        }
        for (NamedCalledFunctionBlock c: _anonymousFunctions) {
            all_.addAllElts(walkType(_page,c.getAccessedBlock(),c));
        }
        for (SwitchMethodBlock c: _switchMethods) {
            all_.addAllElts(walkType(_page,c.getAccessedBlock(),c));
        }
        for (RootBlock c: all_) {
            processType(_page, _inter, c);
        }
    }

    private static CustList<RootBlock> walkType(AnalyzedPageEl _page, AccessedBlock _a, BracedBlock _type) {
        CustList<RootBlock> types_ = new CustList<RootBlock>();
        AbsBk current_ = _type.getFirstChild();
        if (current_ == null) {
            leafType(_type, types_);
            return types_;
        }
        current_ = _type;
        while (current_ != null) {
            if (current_ instanceof RootBlock) {
                localType(_page,_a, (RootBlock) current_);
                types_.add((RootBlock) current_);
            }
            AbsBk ch_ = current_.getFirstChild();
            if (ch_ != null) {
                current_ = ch_;
                continue;
            }
            current_ = next(current_,_type);
        }
        return types_;
    }

    private static void leafType(BracedBlock _type, CustList<RootBlock> _types) {
        if (_type instanceof RootBlock) {
            _types.add((RootBlock) _type);
        }
    }

    private static void localType(AnalyzedPageEl _page, AccessedBlock _a, RootBlock _current) {
        if (_current instanceof AnonymousTypeBlock) {
            return;
        }
        MemberCallingsBlock outerFuntion_ = _current.getOuterFuntionInType();
        if (outerFuntion_ != null) {
            RootBlock possibleParent_ = _current.getParentType();
            if (possibleParent_ != null) {
                String s_ = _current.getName();
                ClassesUtil.incre(_current,_page.getCountElts().getCounts().get(possibleParent_.getCreated()),s_,"+");
            } else {
                if (_a instanceof AccessedBlockMembers) {
                    _current.setAccessedBlock(_a);
                    _page.getCountElts().getLocalTypes().get(((AccessedBlockMembers)_a).contentMemNb().getAccessMemNb()).add(_current);
                } else {
                    String s_ = _current.getName();
                    ClassesUtil.incre(_current,_page.getCountElts().getCountLocalType(),s_,"+");
                }
            }
        }
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
                extractAnon(_page, _int, _type, resultExpression_);
            }
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String value_ = ((FieldBlock) b).getValue();
                ResultExpression resultExpression_ = ((FieldBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((FieldBlock) b).getValueOffset());
                _page.setAccessStaticContext(MethodId.getKind(((FieldBlock) b).isStaticField()));
                extractAnon(_page, _int, _type, resultExpression_);
            }
            if (b instanceof ElementBlock) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
                String value_ = ((ElementBlock) b).getElementContent().buildVirtualCreate(keyWordNew_);
                ResultExpression resultExpression_ = ((ElementBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                ((ElementBlock) b).getElementContent().setTrOffset(((ElementBlock) b).getElementContent().retrieveTr(keyWordNew_));
                resultExpression_.setSumOffset(((ElementBlock) b).getFieldNameOffset()+((ElementBlock) b).getElementContent().retrieveTr(keyWordNew_));
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, _type, resultExpression_);
            }
            if (b instanceof InnerElementBlock) {
                _page.setCurrentBlock(b);
                _page.zeroOffset();
                String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
                String value_ = ((InnerElementBlock) b).getElementContent().buildVirtualCreate(keyWordNew_);
                ResultExpression resultExpression_ = ((InnerElementBlock) b).getRes();
                resultExpression_.setAnalyzedString(value_);
                ((InnerElementBlock) b).getElementContent().setTrOffset(((InnerElementBlock) b).getElementContent().retrieveTr(keyWordNew_));
                resultExpression_.setSumOffset(((InnerElementBlock) b).getFieldNameOffset()+((InnerElementBlock) b).getElementContent().retrieveTr(keyWordNew_));
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                extractAnon(_page, _int, _type, resultExpression_);
            }
            if (b instanceof MemberCallingsBlock) {
                processFunction(_page, _int, (MemberCallingsBlock) b,_type);
            }
        }
        for (AbsBk b: bl_) {
            procAnnotMember(_page, _int, _type, b);
        }
        if (!(_type instanceof InnerElementBlock)) {
            _page.setCurrentBlock(_type);
            CustList<ResultParsedAnnot> annotations_ = _type.getAnnotations().getAnnotations();
            int len_ = annotations_.size();
            for (int i = 0; i < len_; i++) {
                procAnnot(_page, _int,_type, annotations_, i);
            }
        }
    }

    private static void procAnnotMember(AnalyzedPageEl _page, IntermediaryResults _int, RootBlock _type, AbsBk _b) {
        if (_b instanceof FieldBlock) {
            _page.setCurrentBlock(_b);
            CustList<ResultParsedAnnot> annotations_ = ((FieldBlock) _b).getAnnotations().getAnnotations();
            int len_ = annotations_.size();
            for (int i = 0; i < len_; i++) {
                procAnnot(_page, _int,_type, annotations_, i);
            }
        }
        if (_b instanceof ElementBlock) {
            _page.setCurrentBlock(_b);
            CustList<ResultParsedAnnot> annotations_ = ((ElementBlock) _b).getAnnotations().getAnnotations();
            int len_ = annotations_.size();
            for (int i = 0; i < len_; i++) {
                procAnnot(_page, _int,_type, annotations_, i);
            }
        }
        if (_b instanceof InnerElementBlock) {
            _page.setCurrentBlock(_b);
            CustList<ResultParsedAnnot> annotations_ = ((InnerElementBlock) _b).getAnnotations().getAnnotations();
            int len_ = annotations_.size();
            for (int i = 0; i < len_; i++) {
                procAnnot(_page, _int,_type, annotations_, i);
            }
        }
        if (_b instanceof NamedFunctionBlock) {
            processAnnotFct(_page, _int, _b, _type);
        }
    }

    private static void processAnnotFct(AnalyzedPageEl _page, IntermediaryResults _int, AbsBk _fct, AccessedBlock _a) {
        _page.setCurrentBlock(_fct);
        CustList<ResultParsedAnnot> annotations_ = ((NamedFunctionBlock) _fct).getAnnotations().getAnnotations();
        int len_ = annotations_.size();
        for (int i = 0; i < len_; i++) {
            procAnnot(_page, _int, _a, annotations_, i);
        }
        for (ResultParsedAnnots l: ((NamedFunctionBlock) _fct).getAnnotationsParams()) {
            CustList<ResultParsedAnnot> annotationsLoc_ = l.getAnnotations();
            len_ = annotationsLoc_.size();
            for (int i = 0; i < len_; i++) {
                procAnnot(_page, _int, _a, annotationsLoc_, i);
            }
        }
        if (_fct instanceof NamedCalledFunctionBlock) {
            CustList<ResultParsedAnnot> annotationsSupp_ = ((NamedCalledFunctionBlock) _fct).getAnnotationsSupp().getAnnotations();
            int lenSet_ = annotationsSupp_.size();
            for (int i = 0; i < lenSet_; i++) {
                procAnnot(_page, _int, _a, annotationsSupp_, i);
            }
        }

    }

    private static void processAnnotSw(AnalyzedPageEl _page, IntermediaryResults _int, SwitchMethodBlock _fct, AccessedBlock _a) {
        _page.setCurrentBlock(_fct);
        CustList<ResultParsedAnnot> annotations_ = _fct.getAnnotations().getAnnotations();
        int len_ = annotations_.size();
        for (int i = 0; i < len_; i++) {
            procAnnot(_page, _int, _a, annotations_, i);
        }
        for (ResultParsedAnnots l: _fct.getAnnotationsParams()) {
            CustList<ResultParsedAnnot> annotationsLoc_ = l.getAnnotations();
            len_ = annotationsLoc_.size();
            for (int i = 0; i < len_; i++) {
                procAnnot(_page, _int, _a, annotationsLoc_, i);
            }
        }
    }

    private static void procAnnot(AnalyzedPageEl _page, IntermediaryResults _int, AccessedBlock _a, CustList<ResultParsedAnnot> _annotations, int _i) {
        ResultParsedAnnot begin_ = _annotations.get(_i);
        _page.zeroOffset();
        ResultExpression res_ = begin_.getRes();
        res_.partsAbsol(begin_.getParts());
        res_.setAnalyzedString(begin_.getAnnotation().trim());
        res_.setSumOffset(begin_.getIndex());
        _page.setAccessStaticContext(MethodAccessKind.STATIC);
        extractAnon(_page, _int, _a, res_);
    }

    private static void processFunction(AnalyzedPageEl _page, IntermediaryResults _int, MemberCallingsBlock _method, AccessedBlock _a) {
        AbsBk current_ = _method.getFirstChild();
        if (current_ == null) {
            return;
        }
        MethodAccessKind st_ = _method.getStaticContext();
        _page.setAccessStaticContext(st_);
        while (current_ != null) {
            _page.setCurrentBlock(current_);
            block(_page, _int, _a, current_);
            if (AbsBk.isVisitableBlock(current_)) {
                AbsBk ch_ = current_.getFirstChild();
                if (ch_ != null) {
                    current_ = ch_;
                    continue;
                }
            }
            current_ = next(current_,_method);
        }
    }
    private static AbsBk next(AbsBk _current, AbsBk _method) {
        AbsBk current_ = _current;
        while (current_ != null) {
            AbsBk next_ = current_.getNextSibling();
            if (next_ != null) {
                current_ = next_;
                break;
            }
            BracedBlock parent_ = current_.getParent();
            if (parent_ == _method) {
                current_ = null;
            } else {
                current_ = parent_;
            }
        }
        return current_;
    }

    private static void block(AnalyzedPageEl _page, IntermediaryResults _int, AccessedBlock _method, AbsBk _current) {
        if (_current instanceof Line) {
            _page.zeroOffset();
            String value_ = ((Line) _current).getExpression();
            ResultExpression resultExpression_ = ((Line) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((Line) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (_current instanceof WithFilterContent) {
            FilterContent f_ = ((WithFilterContent) _current).getFilterContent();
            filterContent(_page, _int, _method, f_);
        }
        if (_current instanceof SwitchBlock) {
            String value_ = ((SwitchBlock) _current).getValue();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((SwitchBlock) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((SwitchBlock) _current).getValueOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (_current instanceof ConditionBlock) {
            String value_ = ((ConditionBlock) _current).getCondition();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((ConditionBlock) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((ConditionBlock) _current).getConditionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (_current instanceof ForEachLoop) {
            String value_ = ((ForEachLoop) _current).getExpression();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((ForEachLoop) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((ForEachLoop) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (_current instanceof ForEachTable) {
            String value_ = ((ForEachTable) _current).getExpression();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((ForEachTable) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((ForEachTable) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (_current instanceof ForIterativeLoop) {
            _page.zeroOffset();
            ResultExpression resultInit_ = ((ForIterativeLoop) _current).getResInit();
            resultInit_.setAnalyzedString(((ForIterativeLoop) _current).getInit());
            resultInit_.setSumOffset(((ForIterativeLoop) _current).getInitOffset());
            extractAnon(_page, _int, _method, resultInit_);
            _page.zeroOffset();
            ResultExpression resultExp_ = ((ForIterativeLoop) _current).getResExp();
            resultExp_.setAnalyzedString(((ForIterativeLoop) _current).getExpression());
            resultExp_.setSumOffset(((ForIterativeLoop) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExp_);
            _page.zeroOffset();
            ResultExpression resultStep_ = ((ForIterativeLoop) _current).getResStep();
            resultStep_.setAnalyzedString(((ForIterativeLoop) _current).getStep());
            resultStep_.setSumOffset(((ForIterativeLoop) _current).getStepOffset());
            extractAnon(_page, _int, _method, resultStep_);
        }
        if (_current instanceof ForMutableIterativeLoop) {
            _page.zeroOffset();
            ResultExpression resultInit_ = ((ForMutableIterativeLoop) _current).getResInit();
            resultInit_.setAnalyzedString(((ForMutableIterativeLoop) _current).getInit());
            resultInit_.setSumOffset(((ForMutableIterativeLoop) _current).getInitOffset());
            extractAnon(_page, _int, _method, resultInit_);
            _page.zeroOffset();
            ResultExpression resultExp_ = ((ForMutableIterativeLoop) _current).getResExp();
            resultExp_.setAnalyzedString(((ForMutableIterativeLoop) _current).getExpression());
            resultExp_.setSumOffset(((ForMutableIterativeLoop) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExp_);
            _page.zeroOffset();
            ResultExpression resultStep_ = ((ForMutableIterativeLoop) _current).getResStep();
            resultStep_.setAnalyzedString(((ForMutableIterativeLoop) _current).getStep());
            resultStep_.setSumOffset(((ForMutableIterativeLoop) _current).getStepOffset());
            extractAnon(_page, _int, _method, resultStep_);
        }
        if (_current instanceof ReturnMethod) {
            _page.zeroOffset();
            String value_ = ((ReturnMethod) _current).getExpression();
            ResultExpression resultExpression_ = ((ReturnMethod) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((ReturnMethod) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (_current instanceof Throwing) {
            _page.zeroOffset();
            String value_ = ((Throwing) _current).getExpression();
            ResultExpression resultExpression_ = ((Throwing) _current).getRes();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((Throwing) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
    }

    private static void filterContent(AnalyzedPageEl _page, IntermediaryResults _int, AccessedBlock _method, FilterContent _f) {
        if (!_f.getValue().isEmpty()) {
            String value_ = _f.getValue();
            _page.zeroOffset();
            ResultExpression resultExpression_ = _f.getResValue();
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(_f.getValueOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
        if (!_f.getCondition().isEmpty()) {
            String substring_ = _f.getCondition();
            _page.zeroOffset();
            ResultExpression resultExpression_ = _f.getResCondition();
            resultExpression_.setAnalyzedString(substring_);
            resultExpression_.setSumOffset(_f.getConditionOffset());
            extractAnon(_page, _int, _method, resultExpression_);
        }
    }

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, AccessedBlock _method, ResultExpression _resultExpression) {
        _page.setSumOffset(_resultExpression.getSumOffset());
        ElRetrieverAnonymous.commonCheckQuick(0, _page, _resultExpression, _method);
        feedResult(_page,_method, _resultExpression, _int);
    }

    private static void feedResult(AnalyzedPageEl _page, AccessedBlock _a, ResultExpression _resultExpression, IntermediaryResults _int) {
        feed(_page,_resultExpression, _int, _a);
    }

    public static void feed(AnalyzedPageEl _page, ResultExpression _resultExpression, IntermediaryResults _int, AccessedBlock _op) {
        for (AnonymousResult a: _resultExpression.getAnonymousResults()) {
            loopFeed(_page,_int, _op, a);
        }
    }

    private static void loopFeed(AnalyzedPageEl _page, IntermediaryResults _int, AccessedBlock _op, AnonymousResult _anon) {
        AbsBk type_ = _anon.getType();
        if (AbsBk.isAnonBlock(type_)) {
            _int.getAnonymousFunctions().add((NamedCalledFunctionBlock)type_);
        }
        if (type_ instanceof AnonymousTypeBlock) {
            if (_op instanceof AccessedBlockMembers) {
                _page.getCountElts().getAnonTypes().get(((AccessedBlockMembers)_op).contentMemNb().getAccessMemNb()).add((AnonymousTypeBlock)type_);
            }
            _int.getAnonymousTypes().add((AnonymousTypeBlock)type_);
        }
        if (type_ instanceof SwitchMethodBlock) {
            _int.getSwitchMethods().add((SwitchMethodBlock)type_);
        }
    }

}
