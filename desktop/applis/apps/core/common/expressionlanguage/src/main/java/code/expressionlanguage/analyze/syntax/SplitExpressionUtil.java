package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedType;
import code.expressionlanguage.analyze.instr.ElResolver;
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
        for (OperatorBlock o: _page.getFoundOperators()) {
            _page.setGlobalType(null);
            _page.setGlobalDirType(null);
            processFunction(_page,int_,o);
        }
        for (OperatorBlock o: _page.getFoundOperators()) {
            _page.setGlobalType(null);
            _page.setGlobalDirType(null);
            processAnnotFct(_page, int_, o);
        }
        while (!int_.getAnonymousTypes().isEmpty()||!int_.getAnonymousFunctions().isEmpty()) {
            list_.add(int_);
            CustList<AnonymousTypeBlock> anonymousTypes_ = int_.getAnonymousTypes();
            CustList<AnonymousFunctionBlock> anonymousFunctions_ = int_.getAnonymousFunctions();
            int_ = new IntermediaryResults();
            CustList<RootBlock> all_ = new CustList<RootBlock>();
            for (RootBlock c: anonymousTypes_) {
                RootBlock parentType_ = c.getParentType();
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
            for (AnonymousFunctionBlock c: anonymousFunctions_) {
                RootBlock parentType_ = c.getParentType();
                parentType_.setCountsAnonFct(parentType_.getCountsAnonFct()+1);
                c.setIntenName(Long.toString(parentType_.getCountsAnonFct()));
            }
            for (RootBlock c: anonymousTypes_) {
                all_.addAllElts(walkType(c));
            }
            for (AnonymousFunctionBlock c: anonymousFunctions_) {
                all_.addAllElts(walkType(c));
            }
            for (RootBlock c: all_) {
                processType(_page, int_, c);
            }
            for (AnonymousFunctionBlock c: anonymousFunctions_) {
                RootBlock parentType_ = c.getParentType();
                _page.setGlobalType(parentType_);
                _page.setGlobalDirType(parentType_);
                processFunction(_page,int_,c);
            }
        }
        return list_;
    }

    private static CustList<RootBlock> walkType(BracedBlock _type) {
        CustList<RootBlock> types_ = new CustList<RootBlock>();
        Block current_ = _type.getFirstChild();
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
                    RootBlock possibleParent_ = ((RootBlock) current_).getParentType();
                    if (possibleParent_ != null&&outerFuntion_ != null) {
                        String s_ = ((RootBlock) current_).getName();
                        Integer val_ = possibleParent_.getCounts().getVal(s_);
                        if (val_ == null) {
                            possibleParent_.getCounts().put(s_,1);
                            ((RootBlock) current_).setSuffix("+1");
                        } else {
                            possibleParent_.getCounts().put(s_,val_+1);
                            ((RootBlock) current_).setSuffix("+"+(val_+1));
                        }
                    }

                }
                types_.add((RootBlock) current_);
            }
            Block ch_ = current_.getFirstChild();
            if (ch_ != null) {
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                Block next_ = current_.getNextSibling();
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
        _page.setGlobalDirType(_type);
        CustList<Block> bl_ = ClassesUtil.getDirectChildren(_type);
        for (Block b: bl_) {
            if (b instanceof AnnotationMethodBlock) {
                _page.setCurrentBlock(b);
                _page.setGlobalOffset(((AnnotationMethodBlock) b).getDefaultValueOffset());
                _page.setOffset(0);
                String value_ = ((AnnotationMethodBlock) b).getDefaultValue();
                ResultExpression resultExpression_ = ((AnnotationMethodBlock) b).getRes();
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                _page.setGlobalOffset(((FieldBlock) b).getValueOffset());
                _page.setOffset(0);
                String value_ = ((FieldBlock) b).getValue();
                ResultExpression resultExpression_ = ((FieldBlock) b).getRes();
                _page.setAccessStaticContext(MethodId.getKind(((FieldBlock) b).isStaticField()));
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
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
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
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
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
                _page.setTranslatedOffset(0);
            }
            if (b instanceof MemberCallingsBlock) {
                processFunction(_page, _int, (MemberCallingsBlock) b);
            }
        }
        for (Block b: bl_) {
            if (b instanceof FieldBlock) {
                _page.setCurrentBlock(b);
                int len_ = ((FieldBlock) b).getAnnotationsIndexes().size();
                for (int i = 0; i < len_; i++) {
                    int begin_ = ((FieldBlock) b).getAnnotationsIndexes().get(i);
                    _page.setGlobalOffset(begin_);
                    _page.setOffset(0);
                    ResultExpression res_ = new ResultExpression();
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    ElResolver.commonCheckQuick(((FieldBlock) b).getAnnotations().get(i).trim(),0,_page,res_);
                    feedResult(_page, res_, _int);
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
                    ElResolver.commonCheckQuick(((ElementBlock) b).getAnnotations().get(i).trim(),0,_page,res_);
                    feedResult(_page, res_, _int);
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
                    ElResolver.commonCheckQuick(((InnerElementBlock) b).getAnnotations().get(i).trim(),0,_page,res_);
                    feedResult(_page, res_, _int);
                    ((InnerElementBlock) b).getResList().add(res_);
                }
            }
            if (b instanceof NamedFunctionBlock) {
                processAnnotFct(_page, _int, b);
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
                ElResolver.commonCheckQuick(_type.getAnnotations().get(i).trim(),0,_page,res_);
                feedResult(_page, res_, _int);
                _type.getResList().add(res_);
            }
        }
    }

    private static void processAnnotFct(AnalyzedPageEl _page, IntermediaryResults _int, Block _fct) {
        _page.setCurrentBlock(_fct);
        int len_ = ((NamedFunctionBlock) _fct).getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = ((NamedFunctionBlock) _fct).getAnnotationsIndexes().get(i);
            _page.setGlobalOffset(begin_);
            _page.setOffset(0);
            ResultExpression res_ = new ResultExpression();
            _page.setAccessStaticContext(MethodAccessKind.STATIC);
            ElResolver.commonCheckQuick(((NamedFunctionBlock) _fct).getAnnotations().get(i).trim(),0,_page,res_);
            feedResult(_page, res_, _int);
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
                ElResolver.commonCheckQuick(list_.get(i).trim(),0,_page,res_);
                feedResult(_page, res_, _int);
                resList_.add(res_);
            }
            ((NamedFunctionBlock) _fct).getResLists().add(resList_);
            j_++;
        }
    }

    private static void processFunction(AnalyzedPageEl _page, IntermediaryResults _int, MemberCallingsBlock _method) {
        Block current_ = _method.getFirstChild();
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
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
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
                    ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                    feedResult(_page, resultExpression_, _int);
                }
            }
            if (current_ instanceof SwitchBlock) {
                String value_ = ((SwitchBlock) current_).getValue();
                _page.setGlobalOffset(((SwitchBlock) current_).getValueOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((SwitchBlock) current_).getRes();
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (current_ instanceof Condition) {
                String value_ = ((Condition) current_).getCondition();
                _page.setGlobalOffset(((Condition) current_).getConditionOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((Condition) current_).getRes();
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (current_ instanceof ForEachLoop) {
                String value_ = ((ForEachLoop) current_).getExpression();
                _page.setGlobalOffset(((ForEachLoop) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((ForEachLoop) current_).getRes();
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (current_ instanceof ForEachTable) {
                String value_ = ((ForEachTable) current_).getExpression();
                _page.setGlobalOffset(((ForEachTable) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExpression_ = ((ForEachTable) current_).getRes();
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (current_ instanceof ForIterativeLoop) {
                _page.setGlobalOffset(((ForIterativeLoop) current_).getInitOffset());
                _page.setOffset(0);
                ResultExpression resultInit_ = ((ForIterativeLoop) current_).getResInit();
                ElResolver.commonCheckQuick(((ForIterativeLoop) current_).getInit(), 0, _page,resultInit_);
                feedResult(_page, resultInit_, _int);
                _page.setGlobalOffset(((ForIterativeLoop) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExp_ = ((ForIterativeLoop) current_).getResExp();
                ElResolver.commonCheckQuick(((ForIterativeLoop) current_).getExpression(), 0, _page,resultExp_);
                feedResult(_page, resultExp_, _int);
                _page.setGlobalOffset(((ForIterativeLoop) current_).getStepOffset());
                _page.setOffset(0);
                ResultExpression resultStep_ = ((ForIterativeLoop) current_).getResStep();
                ElResolver.commonCheckQuick(((ForIterativeLoop) current_).getStep(), 0, _page,resultStep_);
                feedResult(_page, resultStep_, _int);
            }
            if (current_ instanceof ForMutableIterativeLoop) {
                _page.setGlobalOffset(((ForMutableIterativeLoop) current_).getInitOffset());
                _page.setOffset(0);
                ResultExpression resultInit_ = ((ForMutableIterativeLoop) current_).getResInit();
                ElResolver.commonCheckQuick(((ForMutableIterativeLoop) current_).getInit(), 0, _page,resultInit_);
                feedResult(_page, resultInit_, _int);
                _page.setGlobalOffset(((ForMutableIterativeLoop) current_).getExpressionOffset());
                _page.setOffset(0);
                ResultExpression resultExp_ = ((ForMutableIterativeLoop) current_).getResExp();
                ElResolver.commonCheckQuick(((ForMutableIterativeLoop) current_).getExpression(), 0, _page,resultExp_);
                feedResult(_page, resultExp_, _int);
                _page.setGlobalOffset(((ForMutableIterativeLoop) current_).getStepOffset());
                _page.setOffset(0);
                ResultExpression resultStep_ = ((ForMutableIterativeLoop) current_).getResStep();
                ElResolver.commonCheckQuick(((ForMutableIterativeLoop) current_).getStep(), 0, _page,resultStep_);
                feedResult(_page, resultStep_, _int);
            }
            if (current_ instanceof ReturnMethod) {
                _page.setGlobalOffset(((ReturnMethod) current_).getExpressionOffset());
                _page.setOffset(0);
                String value_ = ((ReturnMethod) current_).getExpression();
                ResultExpression resultExpression_ = ((ReturnMethod) current_).getRes();
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (current_ instanceof Throwing) {
                _page.setGlobalOffset(((Throwing) current_).getExpressionOffset());
                _page.setOffset(0);
                String value_ = ((Throwing) current_).getExpression();
                ResultExpression resultExpression_ = ((Throwing) current_).getRes();
                ElResolver.commonCheckQuick(value_, 0, _page,resultExpression_);
                feedResult(_page, resultExpression_, _int);
            }
            if (current_ instanceof BuildableElMethod || current_ instanceof UnclassedBracedBlock) {
                Block ch_ = current_.getFirstChild();
                if (ch_ != null) {
                    current_ = ch_;
                    continue;
                }
            }
            while (current_ != null) {
                Block next_ = current_.getNextSibling();
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

    private static void feedResult(AnalyzedPageEl _page, ResultExpression _resultExpression, IntermediaryResults _int) {
        for (AnonymousResult a: _resultExpression.getAnonymousResults()) {
            Block type_ = a.getType();
            if (type_ instanceof AnonymousTypeBlock) {
                ((AnonymousTypeBlock)type_).setParentType(_page.getGlobalDirType());
                _int.getAnonymousTypes().add((AnonymousTypeBlock)type_);
            }
            if (type_ instanceof AnonymousFunctionBlock) {
                ((AnonymousFunctionBlock)type_).setParentType(_page.getGlobalDirType());
                _int.getAnonymousFunctions().add((AnonymousFunctionBlock)type_);
            }
        }
    }
}
