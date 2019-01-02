package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.AbstractForEachLoop;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.ReturnMehod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        setClassName(_conf.getStandards().getAliasObject());

        int nbParentsInfer_ = 0;
        OperationNode current_ = this;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractArrayElementOperation)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                if (m_ instanceof AbstractTernaryOperation) {
                    if (m_.getFirstChild() == current_) {
                        break;
                    }
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            nbParentsInfer_++;
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String type_ = EMPTY_STRING;
        Block cur_ = _conf.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMehod) {
            FunctionBlock f_ = cur_.getFunction();
            if (f_ instanceof NamedFunctionBlock) {
                NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
                String ret_ = n_.getImportedReturnType();
                String void_ = _conf.getStandards().getAliasVoid();
                if (!StringList.quickEq(ret_, void_)) {
                    type_ = ret_;
                }
            }
        } else if (m_ == null && cur_ instanceof AbstractForEachLoop) {
            AbstractForEachLoop i_ = (AbstractForEachLoop) _conf.getCurrentBlock();
            type_ = i_.getImportedClassName();
            if (!type_.isEmpty()) {
                type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
            }
        } else if (m_ == null && cur_ instanceof InfoBlock) {
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
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (type_.isEmpty() || StringList.quickEq(type_, keyWordVar_)) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(EMPTY_STRING));
            _conf.getClasses().addError(un_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        String n_ = type_;
        String cp_ = PrimitiveTypeUtil.getQuickComponentType(n_, nbParentsInfer_);
        if (cp_ == null) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(EMPTY_STRING));
            _conf.getClasses().addError(un_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        String classNameFinal_ = PrimitiveTypeUtil.getQuickComponentType(cp_);
        if (classNameFinal_ == null) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(EMPTY_STRING));
            _conf.getClasses().addError(un_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
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
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl(), _conf);
            ClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(cast_);
            }
            if (PrimitiveTypeUtil.isPrimitive(classNameFinal_, _conf)) {
                o.getResultClass().setUnwrapObject(classNameFinal_);
            }
        }
        setResultClass(new ClassArgumentMatching(cp_));
    }



}
