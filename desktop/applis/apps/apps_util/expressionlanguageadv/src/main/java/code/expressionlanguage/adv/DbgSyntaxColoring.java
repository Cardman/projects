package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.syntax.*;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.options.ResultContext;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class DbgSyntaxColoring {
    private final StringList toStrOwner = new StringList();
    private final StringList randOwner = new StringList();
    private DbgSyntaxColoring() {
    }
    public static IdMap<FileBlock,CustList<SegmentReadOnlyPart>> partsBpMpWp(ResultContext _res) {
        IdMap<FileBlock,CustList<SegmentReadOnlyPart>> agg_ = new IdMap<FileBlock, CustList<SegmentReadOnlyPart>>();
        for (EntryCust<String, FileBlock> f: _res.getPageEl().getPreviousFilesBodies().entryList()) {
            FileBlock key_ = f.getValue();
            agg_.addEntry(key_,partsBpMpWp(_res,key_));
        }
        return agg_;
    }
    public static IdMap<FileBlock,IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>> partsTokens(ResultContext _res) {
        DbgSyntaxColoring i_ = new DbgSyntaxColoring();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _res.getPageEl().getToStr().entryList()) {
            i_.toStrOwner.add(e.getKey().getFullName());
        }
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _res.getPageEl().getRandCodes().entryList()) {
            i_.randOwner.add(e.getKey().getFullName());
        }
        IdMap<FileBlock,IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>> agg_ = new IdMap<FileBlock, IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>>();
        for (EntryCust<String, FileBlock> f: _res.getPageEl().getPreviousFilesBodies().entryList()) {
            FileBlock key_ = f.getValue();
            agg_.addEntry(key_,i_.partsTokens(key_));
        }
        return agg_;
    }
    public static CustList<SegmentReadOnlyPart> partsBpMpWp(ResultContext _res, FileBlock _file) {
        CustList<SegmentReadOnlyPart> agg_ = new CustList<SegmentReadOnlyPart>();
        for (ResultExpressionBlockOperation r: CallersRef.fetch(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r);
            possible(agg_, parts_);
        }
        for (ResultExpressionBlock r: CallersRef.fetchRes(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r,_file);
            possible(agg_, parts_);
        }
        for (AbsBkSrcFileLocation r: CallersRef.fetchBk(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r,_file);
            possible(agg_, parts_);
        }
        for (RootBlock r: _file.getAllFoundTypes()) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r,_file);
            possible(agg_, parts_);
        }
        for (MemberCallingsBlock r: CallersRef.fetchFct(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = partsMethod(_res, r);
            possible(agg_, parts_);
        }
        return agg_;
    }
    private IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> partsTokens(FileBlock _file) {
        IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> agg_ = new IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>();
        for (AbsBkSrcFileLocation r: CallersRef.fetchBk(_file)) {
            merge(agg_,partsTokens(r));
        }
        for (ResultExpressionBlockOperation r: CallersRef.fetch(_file)) {
            merge(agg_,partsTokens(r));
        }
        return agg_;
    }
    private static void merge(IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> _dest,IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> _in) {
        for (EntryCust<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> e: _in.entryList()) {
            CustList<SegmentReadOnlyTokenPart> ls_ = _dest.getVal(e.getKey());
            if (ls_ == null) {
                _dest.addEntry(e.getKey(),new CustList<SegmentReadOnlyTokenPart>(e.getValue()));
            } else {
                ls_.addAllElts(e.getValue());
            }
        }
    }

    private static CustList<SegmentReadOnlyPart> partsMethod(ResultContext _res, MemberCallingsBlock _r) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        int offset_ = _r.getPlace();
        MethodPointBlockPair pair_ = _res.getPair(MemberCallingsBlock.clName(_r));
        if (pair_ != null) {
            if (AbsBk.isAnonBlock(_r)) {
                parts_.addAllElts(new SegmentReadOnlyPart(offset_, ((NamedCalledFunctionBlock)_r).getNameOffset(),SyntaxRefEnum.METHOD).parts(_r));
                AbsBk f_ = _r.getFirstChild();
                innerMethod(parts_, f_, ((NamedCalledFunctionBlock) _r).getIndexEnd(), ((NamedCalledFunctionBlock) _r).getNameOffset() + 2);
            } else if (_r instanceof SwitchMethodBlock) {
                StrTypes vs_ = ((SwitchMethodBlock) _r).getValues();
                parts_.addAllElts(new SegmentReadOnlyPart(offset_, offset_ + 1,SyntaxRefEnum.METHOD).parts(_r));
                parts_.addAllElts(new SegmentReadOnlyPart(offset_ + 1 + StrTypes.value(vs_,0).length(), _r.getBegin()+1,SyntaxRefEnum.METHOD).parts(_r));
            } else{
                AbsBk f_ = _r.getFirstChild();
                int o_ = 0;
                if (f_ != null){
                    parts_.addAllElts(new SegmentReadOnlyPart(offset_, _r.getBegin(),SyntaxRefEnum.METHOD).parts(_r));
                    o_ = f_.getOffset();
                } else {
                    parts_.addAllElts(new SegmentReadOnlyPart(offset_, _r.getEndAll(),SyntaxRefEnum.METHOD).parts(_r));
                }
                innerMethod(parts_, f_, _r.getEndAll(), o_);
            }
        }
        return parts_;
    }

    private static boolean hasExplicit(AbsBk _f) {
        return _f != null && !ReturnMethod.isImplicitReturn(_f);
    }

    private static void innerMethod(CustList<SegmentReadOnlyPart> _part, AbsBk _f, int _endMethod, int _fr) {
        if (!hasExplicit(_f)) {
            return;
        }
        int b_ = _f.getOffset();
        int e_ = _f.getEndAll();
        int from_ = _fr;
        int end_ = b_;
        AbsBk last_ = _f;
        while (last_ != null) {
            SegmentReadOnlyPart.filter(_part,from_,end_,SyntaxRefEnum.METHOD);
            e_ = last_.getEndAll();
            last_ = last_.getNextSibling();
            from_ = e_;
            if (last_ != null) {
                end_ = last_.getOffset();
            }
        }
        SegmentReadOnlyPart.filter(_part,e_, _endMethod,SyntaxRefEnum.METHOD);
    }

    private static void possible(CustList<SegmentReadOnlyPart> _agg, CustList<SegmentReadOnlyPart> _parts) {
        _agg.addAllElts(_parts);
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, ResultExpressionBlockOperation _r) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        OperationNode op_ = _r.getBlock();
        ResultExpression resStr_ = _r.getRes().getRes();
        int offset_ = resStr_.getSumOffset();
        if (op_ instanceof SettableAbstractFieldOperation) {
            RootBlock ft_ = ((SettableAbstractFieldOperation) op_).getFieldType();
            WatchPointBlockPair w_ = _res.getPairWatch(true,SynthFieldInfo.nb(ft_),((SettableAbstractFieldOperation) op_).getFieldIdReadOnly().getFieldName());
            if (w_ != null) {
                InnerTypeOrElement elt_ = result((SettableAbstractFieldOperation) op_);
                if (elt_ == null) {
                    int b_ = beginOff(offset_,((SettableAbstractFieldOperation) op_));
                    parts_.add(new SegmentReadOnlyPart(b_,b_+((SettableAbstractFieldOperation) op_).getFieldNameLength(),SyntaxRefEnum.FIELD));
                } else {
                    int b_ = elt_.getFieldNameOffset();
                    parts_.add(new SegmentReadOnlyPart(b_, b_ + elt_.getUniqueFieldName().length(),SyntaxRefEnum.FIELD));
                }
            }
        }
        if (op_ instanceof NamedArgumentOperation) {
            NamedArgumentOperation name_ = (NamedArgumentOperation) op_;
            WatchPointBlockPair w_ = _res.getPairWatch(true, SynthFieldInfo.nb(name_.getField()),name_.getIdField().getFieldName());
            if (w_ != null) {
                int firstOff_ = name_.getOffsetTr();
                int b_ = beginOffGene(_r)+firstOff_;
                parts_.add(new SegmentReadOnlyPart(b_, b_ + name_.getFieldName().length(),SyntaxRefEnum.FIELD));
            }
        }
        if (op_ instanceof LambdaOperation) {
            LambdaOperation lda_ = (LambdaOperation) op_;
            lambda(_res, _r, parts_, lda_);
        }
        if (op_ instanceof AssocationOperation) {
            AssocationOperation ass_ = (AssocationOperation) op_;
            WatchPointBlockPair w_ = _res.getPairWatch(false, SynthFieldInfo.nb(AnaTypeFct.root(ass_.getFunction())),ass_.getFieldName());
            if (w_ != null) {
                int firstOff_ = ass_.getOffsetFct();
                int b_ = beginOffGene(_r)+firstOff_;
                parts_.add(new SegmentReadOnlyPart(b_, b_ + ass_.getFieldName().length(),SyntaxRefEnum.FIELD));
            }
        }
        return parts_;
    }

    private static void lambda(ResultContext _res, ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyPart> _parts, LambdaOperation _lda) {
        CustList<AnaNamedFieldContent> namedFields_ = _lda.getNamedFields();
        int len_ = namedFields_.size();
        int beginLambda_ = beginOffGene(_r)+ _lda.getOffset();
        for (int i = 0; i < len_; i++) {
            AnaNamedFieldContent naFi_ = namedFields_.get(i);
            String name_ = naFi_.getName();
            RootBlock r_ = naFi_.getDeclaring();
            WatchPointBlockPair w_ = _res.getPairWatch(true, SynthFieldInfo.nb(r_),name_);
            if (w_ != null) {
                int off_ = _lda.getOffsets().get(i);
                int b_ = off_+beginLambda_;
                _parts.add(new SegmentReadOnlyPart(b_, b_ + name_.length(),SyntaxRefEnum.FIELD));
            }
        }
        ClassField fieldId_ = _lda.getFieldId();
        if (fieldId_ != null) {
            RootBlock fieldType_ = _lda.getLambdaCommonContent().getFoundFormatted().getRootBlock();
            WatchPointBlockPair w_ = _res.getPairWatch(true, SynthFieldInfo.nb(fieldType_), fieldId_.getFieldName());
            if (w_ != null) {
                int b_ = beginOffGene(_r) + _lda.getMemberOffset();
                int e_ = b_ + fieldId_.getFieldName().length();
                _parts.add(new SegmentReadOnlyPart(b_, e_, SyntaxRefEnum.FIELD));
            }
        }
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, ResultExpressionBlock _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        ResultExpression resStr_ = _r.getRes();
        ExecFileBlock fileEx_ = _res.getFiles().getVal(_file);
        if (!resStr_.getAnalyzedString().trim().startsWith("@") && _r.getBlock() instanceof InnerTypeOrElement) {
            int f_ = ((InnerTypeOrElement) _r.getBlock()).getFieldNameOffset();
            BreakPointBlockPair pair_ = _res.getPair(fileEx_, f_);
            if (pair_ != null) {
                parts_.add(new SegmentReadOnlyPart(f_,f_+((InnerTypeOrElement) _r.getBlock()).getUniqueFieldName().length(),SyntaxRefEnum.INSTRUCTION));
            }
        } else if (!resStr_.getAnalyzedString().isEmpty()){
            int offset_ = resStr_.getSumOffset();
            BreakPointBlockPair pair_ = _res.getPair(fileEx_, offset_);
            if (pair_ != null) {
                parts_.addAllElts(new SegmentReadOnlyPart(offset_,offset_+resStr_.getAnalyzedString().length(),SyntaxRefEnum.INSTRUCTION).parts(resStr_));
            }
            BreakPointBlockPair pairSec_ = _res.getPair(fileEx_, resStr_.end());
            if (hasExplicit(_r.getBlock()) && pairSec_ != null) {
                parts_.add(new SegmentReadOnlyPart(offset_+resStr_.getAnalyzedString().length(), resStr_.getLastCharPos()[0],SyntaxRefEnum.INSTRUCTION));
            }
        }
        return parts_;
    }

    private static IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> partsTokens(AbsBkSrcFileLocation _r) {
        IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> parts_ = new IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>();
        CustList<SegmentReadOnlyTokenPart> labels_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsInst_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsStatic_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsAnnot_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsAnnotPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fct_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fctPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> op_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> opPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> toStr_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> toStrPred_ = new CustList<SegmentReadOnlyTokenPart>();
        AbsBk bk_ = _r.getBlock();
        if (bk_ instanceof LabelAbruptBlock) {
            int begin_ = ((LabelAbruptBlock) bk_).getLabelOffset();
            int end_ = begin_ + ((LabelAbruptBlock) bk_).getLabel().length();
            if (begin_ < end_) {
                labels_.add(new SegmentReadOnlyTokenPart(begin_,end_));
            }
        }
        if (bk_ instanceof IfCondition || bk_ instanceof WhileCondition || bk_ instanceof LabelledOtherBlock) {
            OffsetStringInfo lab_ = ((BreakableBlock) bk_).getRealLabelInfo();
            int begin_ = lab_.getOffset();
            int end_ = begin_ + lab_.getInfo().length();
            if (begin_ < end_) {
                labels_.add(new SegmentReadOnlyTokenPart(begin_, end_));
            }
        }
        if (bk_ instanceof NamedCalledFunctionBlock) {
            methodDecl(fieldsAnnot_, fieldsAnnotPred_, (NamedCalledFunctionBlock)bk_);
        }
        methodOverDecl(fct_, fctPred_, bk_);
        operatorDecl(op_, opPred_, bk_);
        operatorDeclToStr(toStr_, toStrPred_, bk_);
        parts_.addEntry(SyntaxRefTokenEnum.LABEL,labels_);
        parts_.addEntry(SyntaxRefTokenEnum.INST_FIELD,fieldsInst_);
        parts_.addEntry(SyntaxRefTokenEnum.STATIC_FIELD,fieldsStatic_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD,fieldsAnnot_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD_PRED,fieldsAnnotPred_);
        parts_.addEntry(SyntaxRefTokenEnum.FCT,fct_);
        parts_.addEntry(SyntaxRefTokenEnum.FCT_PRED,fctPred_);
        parts_.addEntry(SyntaxRefTokenEnum.OPERATOR,op_);
        parts_.addEntry(SyntaxRefTokenEnum.OPERATOR_PRED,opPred_);
        parts_.addEntry(SyntaxRefTokenEnum.TO_STR,toStr_);
        parts_.addEntry(SyntaxRefTokenEnum.TO_STR_PRED,toStrPred_);
        return parts_;
    }

    private static void methodDecl(CustList<SegmentReadOnlyTokenPart> _annotCust, CustList<SegmentReadOnlyTokenPart> _annotPred, NamedCalledFunctionBlock _meth) {
        if (_meth.getTypeCall() == NameCalledEnum.ANNOTATION) {
            int begin_ = _meth.getNameOffset();
            int end_ = begin_ + _meth.getName().length();
            add(_annotCust, _annotPred, _meth, new SegmentReadOnlyTokenPart(begin_, end_));
        }
    }

    private static void methodOverDecl(CustList<SegmentReadOnlyTokenPart> _fctCust, CustList<SegmentReadOnlyTokenPart> _fctPred, AbsBk _meth) {
        if (!AbsBk.isOverBlock(_meth)) {
            return;
        }
        NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) _meth;
        if (m_.getKind() == MethodKind.STD_METHOD) {
            int begin_ = m_.getNameOffset();
            int end_ = begin_ + m_.getName().length();
            add(_fctCust, _fctPred, _meth, new SegmentReadOnlyTokenPart(begin_, end_));
        }
    }

    private static void operatorDecl(CustList<SegmentReadOnlyTokenPart> _fctCust, CustList<SegmentReadOnlyTokenPart> _fctPred, AbsBk _meth) {
        if (_meth instanceof OperatorBlock || AbsBk.isOverBlock(_meth) && ((NamedCalledFunctionBlock)_meth).getKind() == MethodKind.OPERATOR) {
            int begin_ = ((NamedFunctionBlock)_meth).getNameOffset();
            int end_ = begin_ + ((NamedFunctionBlock)_meth).getName().length();
            add(_fctCust, _fctPred, _meth, new SegmentReadOnlyTokenPart(begin_, end_));
        }
    }
    private static void operatorDeclToStr(CustList<SegmentReadOnlyTokenPart> _fctCust, CustList<SegmentReadOnlyTokenPart> _fctPred, AbsBk _meth) {
        if (!AbsBk.isOverBlock(_meth)) {
            return;
        }
        NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) _meth;
        if (m_.getKind() == MethodKind.TO_STRING) {
            int begin_ = m_.getNameOffset();
            int end_ = begin_ + m_.getName().length();
            add(_fctCust, _fctPred, _meth, new SegmentReadOnlyTokenPart(begin_, end_));
        }
    }

    private IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> partsTokens(ResultExpressionBlockOperation _r) {
        IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> parts_ = new IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>();
        CustList<SegmentReadOnlyTokenPart> fieldsInst_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsInstPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsStatic_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsStaticPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsAnnot_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsAnnotPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fct_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fctPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> ops_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> opsPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> toStr_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> toStrPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> rand_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> randPred_ = new CustList<SegmentReadOnlyTokenPart>();
        OperationNode op_ = _r.getBlock();
        ResultExpression resStr_ = _r.getRes().getRes();
        if (op_ instanceof SettableAbstractFieldOperation) {
            fieldRef(fieldsInst_, fieldsInstPred_, fieldsStatic_, fieldsStaticPred_, (SettableAbstractFieldOperation) op_, resStr_);
        }
        if (op_ instanceof LambdaOperation) {
            records(_r, fieldsInst_, fieldsInstPred_,(LambdaOperation) op_);
            regularField(_r, fieldsInst_, fieldsInstPred_, fieldsStatic_, fieldsStaticPred_, (LambdaOperation) op_);
            annotationRefMethod(_r,fieldsAnnot_,fieldsAnnotPred_,(LambdaOperation)op_);
        }
        if (op_ instanceof AbsFctOperation) {
            annotationCallMethod(_r,fieldsAnnot_,fieldsAnnotPred_,(AbsFctOperation) op_);
            fctCallMethod(_r,fct_,fctPred_,toStr_,toStrPred_,(AbsFctOperation) op_);
        }
        if (op_ instanceof NamedArgumentOperation) {
            nameArg(_r, fieldsInst_, fieldsInstPred_,(NamedArgumentOperation) op_);
        }
        if (op_ instanceof AssocationOperation) {
            assoc(_r,fieldsAnnot_,fieldsAnnotPred_,(AssocationOperation) op_);
        }
        if (op_ instanceof SymbolOperation) {
            oper(_r,ops_,opsPred_,toStr_,rand_,(SymbolOperation) op_);
        }
        if (op_ instanceof CompoundAffectationOperation) {
            compound(_r,ops_,opsPred_,toStr_,(CompoundAffectationOperation) op_);
        }
        if (op_ instanceof ArrOperation) {
            arr(_r,ops_,opsPred_,(ArrOperation)op_);
        }
        parts_.addEntry(SyntaxRefTokenEnum.INST_FIELD,fieldsInst_);
        parts_.addEntry(SyntaxRefTokenEnum.INST_FIELD_PRED,fieldsInstPred_);
        parts_.addEntry(SyntaxRefTokenEnum.STATIC_FIELD,fieldsStatic_);
        parts_.addEntry(SyntaxRefTokenEnum.STATIC_FIELD_PRED,fieldsStaticPred_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD,fieldsAnnot_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD_PRED,fieldsAnnotPred_);
        parts_.addEntry(SyntaxRefTokenEnum.FCT,fct_);
        parts_.addEntry(SyntaxRefTokenEnum.FCT_PRED,fctPred_);
        parts_.addEntry(SyntaxRefTokenEnum.OPERATOR,ops_);
        parts_.addEntry(SyntaxRefTokenEnum.OPERATOR_PRED,opsPred_);
        parts_.addEntry(SyntaxRefTokenEnum.TO_STR,toStr_);
        parts_.addEntry(SyntaxRefTokenEnum.TO_STR_PRED,toStrPred_);
        parts_.addEntry(SyntaxRefTokenEnum.RAND,rand_);
        parts_.addEntry(SyntaxRefTokenEnum.RAND_PRED,randPred_);
        return parts_;
    }

    private static void nameArg(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _fieldsInst, CustList<SegmentReadOnlyTokenPart> _fieldsInstPred, NamedArgumentOperation _op) {
        if (_op.getField() != null) {
            int firstOff_ = _op.getOffsetTr();
            int b_ = beginOffGene(_r)+firstOff_;
            add(_fieldsInst,_fieldsInstPred, _op.getField(), new SegmentReadOnlyTokenPart(b_,b_ + _op.getFieldName().length()));
        }
    }

    private static void assoc(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _fieldsInst, CustList<SegmentReadOnlyTokenPart> _fieldsInstPred, AssocationOperation _op) {
        if (AnaTypeFct.fct(_op.getFunction()) != null) {
            int firstOff_ = _op.getOffsetFct();
            int b_ = beginOffGene(_r)+firstOff_;
            add(_fieldsInst,_fieldsInstPred, _op.getFunction().getType(), new SegmentReadOnlyTokenPart(b_,b_ + _op.getLenTrimFct()));
        }
    }

    private void oper(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, CustList<SegmentReadOnlyTokenPart> _toStr, CustList<SegmentReadOnlyTokenPart> _rand, SymbolOperation _symb) {
        AnaTypeFct function_ = _symb.getFct().getFunction();
        int b_ = beginOffGene(_r)+_symb.getOperatorContent().getOpOffset();
        int e_ = b_ + _symb.getOperatorContent().getOper().length();
        if (_symb instanceof NumericOperation && ((NumericOperation) _symb).isCatString() && look(((NumericOperation) _symb).getChildrenNodes(), toStrOwner)) {
            add(_toStr,_toStr,true,new SegmentReadOnlyTokenPart(b_, e_));
            return;
        }
        if (_symb instanceof RandCodeOperation && look(((RandCodeOperation) _symb).getChildrenNodes(), randOwner)) {
            add(_rand,_rand,true,new SegmentReadOnlyTokenPart(b_, e_));
            return;
        }
        add(_cust,_pred,function_,b_, e_);
    }


    private void compound(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, CustList<SegmentReadOnlyTokenPart> _toStr, CompoundAffectationOperation _symb) {
        AnaTypeFct function_ = _symb.getFct().getFunction();
        int b_ = beginOffGene(_r)+_symb.getOperatorContent().getOpOffset();
        int e_ = b_ + _symb.getOperatorContent().getOper().length()-1;
        if (_symb.isConcat() && look(_symb.getChildrenNodes(), toStrOwner)) {
            add(_toStr,_toStr,true,new SegmentReadOnlyTokenPart(b_, e_));
            return;
        }
        add(_cust, _pred, function_, b_, e_);
    }
    private void arr(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, ArrOperation _symb) {
        NamedFunctionBlock cust_ = AnaTypeFct.fct(_symb.getFunctionGet());
        if (cust_ != null) {
            int left_ = beginOffGene(_r);
            int right_ = left_ + _symb.getLastOpOffset();
            add(_cust,_pred,cust_,new SegmentReadOnlyTokenPart(left_,left_+1));
            add(_cust,_pred,cust_,new SegmentReadOnlyTokenPart(right_,right_+1));
        }
        NamedFunctionBlock alt_ = AnaTypeFct.fct(_symb.getFunctionSet());
        if (alt_ != null) {
            int left_ = beginOffGene(_r);
            int right_ = left_ + _symb.getLastOpOffset();
            add(_cust,_pred,alt_,new SegmentReadOnlyTokenPart(left_,left_+1));
            add(_cust,_pred,alt_,new SegmentReadOnlyTokenPart(right_,right_+1));
        }
    }

    private void add(CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, AnaTypeFct _fct, int _b, int _e) {
        NamedFunctionBlock f_ = AnaTypeFct.fct(_fct);
        if (f_ != null) {
            add(_cust, _pred, f_, new SegmentReadOnlyTokenPart(_b, _e));
            return;
        }
        add(_cust, _pred,AnaTypeFct.root(_fct),new SegmentReadOnlyTokenPart(_b, _e));
    }

    private boolean look(CustList<OperationNode> _chs, StringList _ls) {
        boolean atLeast_ = false;
        for (OperationNode o: _chs) {
            for (String c: o.getResultClass().getNames()) {
                if (StringUtil.contains(_ls,StringExpUtil.getIdFromAllTypes(c))) {
                    atLeast_ = true;
                    break;
                }
            }
        }
        return atLeast_;
    }

    private static void fieldRef(CustList<SegmentReadOnlyTokenPart> _fieldsInst, CustList<SegmentReadOnlyTokenPart> _fieldsInstPred, CustList<SegmentReadOnlyTokenPart> _fieldsStatic, CustList<SegmentReadOnlyTokenPart> _fieldsStaticPred, SettableAbstractFieldOperation _op, ResultExpression _resStr) {
        InnerTypeOrElement elt_ = result(_op);
        if (elt_ == null) {
            int offset_ = _resStr.getSumOffset();
            int b_ = beginOff(offset_, _op);
            int e_ = b_ + _op.getFieldNameLength();
            RootBlock fieldType_ = _op.getFieldType();
            if (_op.getSettableFieldContent().isStaticField()) {
                add(_fieldsStatic, _fieldsStaticPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
            } else {
                add(_fieldsInst, _fieldsInstPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
            }
        } else {
            int b_ = elt_.getFieldNameOffset();
            add(_fieldsStatic, _fieldsStaticPred,elt_.getDeclaringType(),new SegmentReadOnlyTokenPart(b_, b_ + elt_.getUniqueFieldName().length()));
        }
    }

    private static void regularField(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _fieldsInst, CustList<SegmentReadOnlyTokenPart> _fieldsInstPred, CustList<SegmentReadOnlyTokenPart> _fieldsStatic, CustList<SegmentReadOnlyTokenPart> _fieldsStaticPred, LambdaOperation _op) {
        ClassField fieldId_ = _op.getFieldId();
        if (fieldId_ != null) {
            RootBlock fieldType_ = _op.getLambdaCommonContent().getFoundFormatted().getRootBlock();
            int b_ = beginOffGene(_r) + _op.getMemberOffset();
            int e_ = b_ + fieldId_.getFieldName().length();
            if (_op.getLambdaFieldContent().isStaticField()) {
                add(_fieldsStatic,_fieldsStaticPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
            } else {
                add(_fieldsInst,_fieldsInstPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
            }
        }
    }

    private static void annotationRefMethod(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _annotsInst, CustList<SegmentReadOnlyTokenPart> _annotsInstPred, LambdaOperation _op) {
        AnaTypeFct fieldId_ = _op.getFunction();
        NamedFunctionBlock res_ = AnaTypeFct.fct(fieldId_);
        if (okAnnot(fieldId_, res_)) {
            RootBlock fieldType_ = fieldId_.getType();
            int b_ = beginOffGene(_r) + _op.getMemberOffset();
            int e_ = b_ + res_.getName().length();
            add(_annotsInst,_annotsInstPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
        }
    }

    private static void annotationCallMethod(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _annotsInst, CustList<SegmentReadOnlyTokenPart> _annotsInstPred, AbsFctOperation _op) {
        AnaTypeFct fieldId_ = _op.getCallFctContent().getFunction();
        NamedFunctionBlock res_ = AnaTypeFct.fct(fieldId_);
        if (okAnnot(fieldId_, res_)) {
            RootBlock fieldType_ = fieldId_.getType();
            int b_ = beginOffGene(_r) + _op.getDelta();
            int e_ = b_ + _op.getLengthMethod();
            add(_annotsInst,_annotsInstPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
        }
    }

    private static void fctCallMethod(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _fct, CustList<SegmentReadOnlyTokenPart> _fctPred, CustList<SegmentReadOnlyTokenPart> _toStr, CustList<SegmentReadOnlyTokenPart> _toStrPred, AbsFctOperation _op) {
        AnaTypeFct fieldId_ = _op.getCallFctContent().getFunction();
        NamedFunctionBlock res_ = AnaTypeFct.fct(fieldId_);
        boolean toStr_ = AbsBk.isOverBlock(res_) && ((NamedCalledFunctionBlock) res_).getKind() == MethodKind.TO_STRING;
        if (okFctNotAnnot(fieldId_, res_)) {
            RootBlock fieldType_ = fieldId_.getType();
            int b_ = beginOffGene(_r) + _op.getDelta();
            int e_ = b_ + _op.getLengthMethod();
            if (toStr_) {
                add(_toStr,_toStrPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
            } else {
                add(_fct,_fctPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
            }
        }
    }

    private static boolean okAnnot(AnaTypeFct _fieldId, NamedFunctionBlock _res) {
        return _res != null && _fieldId.getType() instanceof AnnotationBlock;
    }

    private static boolean okFctNotAnnot(AnaTypeFct _fieldId, NamedFunctionBlock _res) {
        return _res != null && !(_fieldId.getType() instanceof AnnotationBlock);
    }
    private static void records(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _fieldsInst, CustList<SegmentReadOnlyTokenPart> _fieldsInstPred, LambdaOperation _lda) {
        CustList<AnaNamedFieldContent> namedFields_ = _lda.getNamedFields();
        int len_ = namedFields_.size();
        int off_ = _lda.getOffset();
        for (int i = 0; i < len_; i++) {
            int ref_ = _lda.getRefs().get(i);
            if (ref_ < 0) {
                continue;
            }
            AnaNamedFieldContent naFi_ = namedFields_.get(i);
            String name_ = naFi_.getName();
            int offset_ = _lda.getOffsets().get(i);
            int b_ = beginOffGene(_r)+offset_+off_;
            int e_ = b_ + name_.length();
            add(_fieldsInst,_fieldsInstPred,naFi_.getDeclaring(),new SegmentReadOnlyTokenPart(b_, e_));
        }
    }
    private static void add(CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, AbsBk _gen, SegmentReadOnlyTokenPart _elt) {
        add(_cust,_pred,_gen.getFile(),_elt);
    }
    private static void add(CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, RootBlock _gen, SegmentReadOnlyTokenPart _elt) {
        add(_cust,_pred,ContextUtil.isFromCustFile(_gen),_elt);
    }
    private static void add(CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, FileBlock _gen, SegmentReadOnlyTokenPart _elt) {
        add(_cust,_pred,ContextUtil.isFromCustFile(_gen),_elt);
    }
    private static void add(CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, boolean _gen, SegmentReadOnlyTokenPart _elt) {
        if (_gen) {
            _cust.add(_elt);
        } else {
            _pred.add(_elt);
        }
    }
    private static InnerTypeOrElement result(SettableAbstractFieldOperation _op) {
        ClassField id_ = _op.getFieldIdReadOnly();
        InnerTypeOrElement elt_ = null;
        if (_op.getParent() instanceof AffectationOperation && (((AffectationOperation)_op.getParent()).isSynthetic())) {
            for (AbsBk b: ClassesUtil.getDirectChildren(_op.getFieldType())) {
                if (b instanceof InnerTypeOrElement && StringUtil.quickEq(((InnerTypeOrElement) b).getUniqueFieldName(), id_.getFieldName())) {
                    elt_ = (InnerTypeOrElement) b;
                }
            }
        }
        return elt_;
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, AbsBkSrcFileLocation _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        AbsBk bk_ = _r.getBlock();
        BracedBlock rPar_ = AbsBk.rootOfAnnot(bk_);
        if (rPar_ instanceof RootBlock) {
            int offset_ = ((NamedCalledFunctionBlock)bk_).getPlace();
            WatchPointBlockPair w_ = _res.getPairWatch(false,((RootBlock)rPar_).getNumberAll(),((NamedCalledFunctionBlock)bk_).getName());
            if (w_ != null) {
                parts_.add(new SegmentReadOnlyPart(offset_, ((NamedCalledFunctionBlock)bk_).getDefaultValueOffset(),SyntaxRefEnum.FIELD));
            }
            return parts_;
        }
        int o_ = offset(_r);
        ExecFileBlock fileEx_ = _res.getFiles().getVal(_file);
        BreakPointBlockPair pair_ = _res.getPair(fileEx_, o_);
        if (pair_ != null) {
            int b_ = bk_.getBegin();
            parts_.add(new SegmentReadOnlyPart(b_,b_+ bk_.getLengthHeader(),SyntaxRefEnum.INSTRUCTION));
        }
        for (Ints i: outExp(bk_)){
            BreakPointBlockPair pairSec_ = _res.getPair(fileEx_, i.get(0));
            if (pairSec_ != null) {
                int b_ = i.get(0);
                parts_.add(new SegmentReadOnlyPart(b_,b_+i.get(1),SyntaxRefEnum.INSTRUCTION));
            }
        }
        return parts_;
    }
    private static int offset(AbsBkSrcFileLocation _r) {
        if (ResultExpressionOperationNode.withoutExp(_r.getBlock())) {
            return _r.getBlock().getOffset();
        }
        if (_r.getBlock() instanceof ReturnMethod&& ((ReturnMethod)_r.getBlock()).isEmpty()){
            return ((ReturnMethod)_r.getBlock()).getExpressionOffset();
        }
        return -1;
    }
    private static CustList<Ints> outExp(AbsBk _bl) {
        if (_bl instanceof ForIterativeLoop) {
            CustList<Ints> ls_ = new CustList<Ints>();
            ls_.add(Ints.newList(((ForIterativeLoop) _bl).getVariableNameOffset(), ((ForIterativeLoop) _bl).getVariableName().length()));
            return ls_;
        }
        if (_bl instanceof ForEachLoop) {
            return forEachIterable((ForEachLoop) _bl);
        }
        if (_bl instanceof ForEachTable) {
            return forEachTable((ForEachTable) _bl);
        }
        if (_bl instanceof WithFilterContent) {
            CustList<Ints> ls_ = new CustList<Ints>();
            if (!((WithFilterContent)_bl).getFilterContent().getDeclaringType().isEmpty()){
                ls_.add(Ints.newList(((WithFilterContent) _bl).getFilterContent().getValueOffset(), ((WithFilterContent) _bl).getFilterContent().getDeclaringType().length()));
            }
            ls_.add(Ints.newList(_bl.getOffset(),_bl.getLengthHeader()));
            return ls_;
        }
        return new CustList<Ints>();
    }

    private static CustList<Ints> forEachTable(ForEachTable _loop) {
        int s_ = _loop.getSepOffset();
        int sn_ = _loop.getSepNext();
        int vf_ = _loop.getVariableNameOffsetFirst();
        int nf_ = _loop.getVariableNameFirst().length();
        int vs_ = _loop.getVariableNameOffsetSecond();
        int ns_ = _loop.getVariableNameSecond().length();
        CustList<Ints> ls_ = new CustList<Ints>();
        ls_.add(Ints.newList(sn_,1));
        ls_.add(Ints.newList(s_,1));
        ls_.add(Ints.newList(vf_,nf_));
        ls_.add(Ints.newList(vs_,ns_));
        ls_.add(Ints.newList(_loop.getOffset(),_loop.getLengthHeader()));
        return ls_;
    }

    private static CustList<Ints> forEachIterable(ForEachLoop _loop) {
        int s_ = _loop.getSepOffset();
        int v_ = _loop.getVariableNameOffset();
        int n_ = _loop.getVariableName().length();
        CustList<Ints> ls_ = new CustList<Ints>();
        ls_.add(Ints.newList(s_, 1));
        ls_.add(Ints.newList(v_, n_));
        ls_.add(Ints.newList(_loop.getOffset(), _loop.getLengthHeader()));
        return ls_;
    }
    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, RootBlock _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        int offset_ = _r.getIdRowCol();
        ExecFileBlock fileEx_ = _res.getFiles().getVal(_file);
        TypePointBlockPair pair_ = _res.getPairType(fileEx_, offset_);
        if (pair_ != null) {
            parts_.add(new SegmentReadOnlyPart(offset_,offset_+_r.getNameLength(),SyntaxRefEnum.INSTRUCTION));
        }
        return parts_;
    }
    private static int beginOff(int _sum, SettableAbstractFieldOperation _val) {
        int delta_ = _val.getOffset();
        return _sum + delta_ + _val.getIndexInEl() + _val.getDelta();
    }
    private static int beginOffGene(ResultExpressionBlockOperation _r) {
        return _r.getRes().getRes().getSumOffset() + _r.getBlock().getIndexInEl();
    }

}
