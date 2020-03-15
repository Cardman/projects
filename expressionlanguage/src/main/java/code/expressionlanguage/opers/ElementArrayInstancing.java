package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ElementArrayInstancing extends AbstractArrayInstancingOperation {

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ElementArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        int loc_ = StringList.getFirstPrintableCharIndex(className_);
        className_ = _conf.resolveCorrectType(new_.length()+loc_,className_);
        partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
        if (!className_.startsWith(ARR)) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(className_));
            _conf.addError(un_);
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = PrimitiveTypeUtil.getPrettyArrayType(obj_);
            ClassArgumentMatching class_ = new ClassArgumentMatching(obj_);
            setResultClass(class_);
            return;
        }
        StringMap<StringList> map_;
        map_ = _conf.getCurrentConstraints();
        String eltType_ = PrimitiveTypeUtil.getQuickComponentType(className_);
        Mapping mapping_ = new Mapping();
        mapping_.setParam(eltType_);
        for (OperationNode o: chidren_) {
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
            ClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.addError(cast_);
            }
            if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                o.getResultClass().setUnwrapObject(eltType_);
                o.cancelArgument();
            }
        }
        String arrayCl_ = className_;
        setClassName(PrimitiveTypeUtil.getQuickComponentType(arrayCl_));
        setResultClass(new ClassArgumentMatching(arrayCl_));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
