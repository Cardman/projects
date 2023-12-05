package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.syntax.*;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.Ints;
import code.util.core.StringUtil;

public final class DbgSyntaxColoring {
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
        IdMap<FileBlock,IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>> agg_ = new IdMap<FileBlock, IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>>();
        for (EntryCust<String, FileBlock> f: _res.getPageEl().getPreviousFilesBodies().entryList()) {
            FileBlock key_ = f.getValue();
            agg_.addEntry(key_,partsTokens(key_));
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
    public static IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> partsTokens(FileBlock _file) {
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
                parts_.add(new SegmentReadOnlyPart(offset_, ((NamedCalledFunctionBlock)_r).getNameOffset(),SyntaxRefEnum.METHOD));
            } else if (_r instanceof SwitchMethodBlock) {
                parts_.add(new SegmentReadOnlyPart(offset_, offset_ + 1,SyntaxRefEnum.METHOD));
            } else{
                parts_.add(new SegmentReadOnlyPart(offset_, _r.getBegin(),SyntaxRefEnum.METHOD));
            }
        }
        return parts_;
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
                parts_.add(new SegmentReadOnlyPart(offset_,offset_+resStr_.getAnalyzedString().length(),SyntaxRefEnum.INSTRUCTION));
            }
            BreakPointBlockPair pairSec_ = _res.getPair(fileEx_, resStr_.end());
            if (!ReturnMethod.isImplicitReturn(_r.getBlock()) && pairSec_ != null) {
                parts_.add(new SegmentReadOnlyPart(offset_+resStr_.getAnalyzedString().length(), _r.getBlock().getEndAll(),SyntaxRefEnum.INSTRUCTION));
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
        parts_.addEntry(SyntaxRefTokenEnum.LABEL,labels_);
        parts_.addEntry(SyntaxRefTokenEnum.INST_FIELD,fieldsInst_);
        parts_.addEntry(SyntaxRefTokenEnum.STATIC_FIELD,fieldsStatic_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD,fieldsAnnot_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD_PRED,fieldsAnnotPred_);
        return parts_;
    }

    private static void methodDecl(CustList<SegmentReadOnlyTokenPart> _annotCust, CustList<SegmentReadOnlyTokenPart> _annotPred, NamedCalledFunctionBlock _meth) {
        if (_meth.getTypeCall() == NameCalledEnum.ANNOTATION) {
            int begin_ = _meth.getNameOffset();
            int end_ = begin_ + _meth.getName().length();
            add(_annotCust, _annotPred, ContextUtil.isFromCustFile(_meth.getFile()), new SegmentReadOnlyTokenPart(begin_, end_));
        }
    }

    private static IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> partsTokens(ResultExpressionBlockOperation _r) {
        IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> parts_ = new IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>();
        CustList<SegmentReadOnlyTokenPart> fieldsInst_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsInstPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsStatic_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsStaticPred_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsAnnot_ = new CustList<SegmentReadOnlyTokenPart>();
        CustList<SegmentReadOnlyTokenPart> fieldsAnnotPred_ = new CustList<SegmentReadOnlyTokenPart>();
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
        }
        if (op_ instanceof NamedArgumentOperation) {
            nameArg(_r, fieldsInst_, fieldsInstPred_,(NamedArgumentOperation) op_);
        }
        parts_.addEntry(SyntaxRefTokenEnum.INST_FIELD,fieldsInst_);
        parts_.addEntry(SyntaxRefTokenEnum.INST_FIELD_PRED,fieldsInstPred_);
        parts_.addEntry(SyntaxRefTokenEnum.STATIC_FIELD,fieldsStatic_);
        parts_.addEntry(SyntaxRefTokenEnum.STATIC_FIELD_PRED,fieldsStaticPred_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD,fieldsAnnot_);
        parts_.addEntry(SyntaxRefTokenEnum.ANNOT_FIELD_PRED,fieldsAnnotPred_);
        return parts_;
    }

    private static void nameArg(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _fieldsInst, CustList<SegmentReadOnlyTokenPart> _fieldsInstPred, NamedArgumentOperation _op) {
        if (_op.getField() != null) {
            int firstOff_ = _op.getOffsetTr();
            int b_ = beginOffGene(_r)+firstOff_;
            add(_fieldsInst,_fieldsInstPred, _op.getField(), new SegmentReadOnlyTokenPart(b_,b_ + _op.getFieldName().length()));
        }
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
        NamedFunctionBlock res_ = LambdaOperation.fct(fieldId_);
        if (okAnnot(fieldId_, res_)) {
            RootBlock fieldType_ = fieldId_.getType();
            int b_ = beginOffGene(_r) + _op.getMemberOffset();
            int e_ = b_ + res_.getName().length();
            add(_annotsInst,_annotsInstPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
        }
    }

    private static void annotationCallMethod(ResultExpressionBlockOperation _r, CustList<SegmentReadOnlyTokenPart> _annotsInst, CustList<SegmentReadOnlyTokenPart> _annotsInstPred, AbsFctOperation _op) {
        AnaTypeFct fieldId_ = _op.getCallFctContent().getFunction();
        NamedFunctionBlock res_ = LambdaOperation.fct(fieldId_);
        if (okAnnot(fieldId_, res_)) {
            RootBlock fieldType_ = fieldId_.getType();
            int b_ = beginOffGene(_r) + _op.getDelta();
            int e_ = b_ + res_.getName().length();
            add(_annotsInst,_annotsInstPred,fieldType_,new SegmentReadOnlyTokenPart(b_, e_));
        }
    }

    private static boolean okAnnot(AnaTypeFct _fieldId, NamedFunctionBlock _res) {
        return _res != null && _fieldId.getType() instanceof AnnotationBlock;
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
    private static void add(CustList<SegmentReadOnlyTokenPart> _cust, CustList<SegmentReadOnlyTokenPart> _pred, AnaGeneType _gen, SegmentReadOnlyTokenPart _elt) {
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
        BracedBlock rPar_ = BreakPointBlockList.rootOfAnnot(bk_);
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
