package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
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
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        int loc_ = StringList.getFirstPrintableCharIndex(className_);
        className_ = ResolvingImportTypes.resolveCorrectType(_conf,new_.length()+loc_,className_);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        if (!className_.startsWith(ARR)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //key word len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    className_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            ClassArgumentMatching class_ = new ClassArgumentMatching(obj_);
            setResultClass(class_);
            return;
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        String eltType_ = StringExpUtil.getQuickComponentType(className_);
        Mapping mapping_ = new Mapping();
        mapping_.setParam(eltType_);
        for (OperationNode o: chidren_) {
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
            ClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, eltType_, argType_);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    argType_.getImplicits().add(cl_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //first separator char child
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(argType_.getNames(),"&"),
                            eltType_);
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                }
            }
            if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                o.getResultClass().setUnwrapObject(eltType_);
                o.cancelArgument();
            }
        }
        String arrayCl_ = className_;
        setClassName(StringExpUtil.getQuickComponentType(arrayCl_));
        setResultClass(new ClassArgumentMatching(arrayCl_));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
