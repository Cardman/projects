package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class InferArrayInstancing extends AbstractArrayElementOperation {

    public InferArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        // TODO Auto-generated method stub
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());

        int nbParents_ = 0;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractArrayElementOperation)) {
                break;
            }
            nbParents_++;
            m_ = m_.getParent();
        }
        String type_ = EMPTY_STRING;
        if (m_ == null && _conf.getCurrentBlock() instanceof InfoBlock) {
            InfoBlock i_ = (InfoBlock) _conf.getCurrentBlock();
            type_ = i_.getImportedClassName();
        } else if (!(m_ instanceof AffectationOperation)) {
            //ERROR
            type_ = EMPTY_STRING;
        } else {
            AffectationOperation a_ = (AffectationOperation) m_;
            SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
            if (s_ != null) {
                ClassArgumentMatching c_ = s_.getResultClass();
                if (c_.getNames().size() == 1) {
                    type_ = c_.getName();
                }
            }
        }
        if (type_.isEmpty() || StringList.quickEq(type_, TypeUtil.VAR_TYPE)) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(me_));
            _conf.getClasses().addError(un_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        String n_ = type_;
        String cp_ = PrimitiveTypeUtil.getQuickComponentType(n_, nbParents_);
        String classNameFinal_ = PrimitiveTypeUtil.getQuickComponentType(cp_);
        setClassName(classNameFinal_);
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        String glClass_ = _conf.getGlobalClass();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setParam(classNameFinal_);
        for (OperationNode o: chidren_) {
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
            ClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!Templates.isGenericCorrect(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
            if (PrimitiveTypeUtil.isPrimitive(classNameFinal_, _conf)) {
                o.getResultClass().setUnwrapObject(classNameFinal_);
            }
        }
        setResultClass(new ClassArgumentMatching(cp_));
    }



}
