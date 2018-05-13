package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class EnumValueOfOperation extends MethodOperation {

    private String className;
    private int argOffset;

    public EnumValueOfOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        String clName_ = StringList.removeAllSpaces(className);
        if (!checkCorrect(_conf, clName_, false, 0)) {
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (!(classes_.getClassBody(clName_) instanceof EnumBlock)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            un_.setType(clName_);
            _conf.getClasses().getErrorsDet().add(un_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = StringList.getAllTypes(glClass_).first();
        }
        if (!Classes.canAccessClass(curClassBase_, clName_, _conf)) {
            BadAccessClass badAccess_ = new BadAccessClass();
            badAccess_.setId(clName_);
            badAccess_.setRc(_conf.getCurrentLocation());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().getErrorsDet().add(badAccess_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        String argCl_ = firstArgs_.first().getName();
        String stringType_ = _conf.getStandards().getAliasString();
        if (!StringList.quickEq(argCl_, stringType_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            un_.setType(argCl_);
            _conf.getClasses().getErrorsDet().add(un_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        className = clName_;
        setResultClass(new ClassArgumentMatching(clName_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void calculate(ContextEl _conf) {
        OperationNode first_ = getFirstChild();
        Argument arg_ = first_.getArgument();
        ArgumentCall argres_ = getCommonArgument(arg_, _conf);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getCommonArgument(arg_, _conf);
        }
        if (_conf.getException() != null) {
            return;
        }
        Argument argRes_ = argres_.getArgument();
        setSimpleArgument(argRes_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        OperationNode first_ = getFirstChild();
        Argument a_ = _nodes.getVal(first_).getArgument();
        ArgumentCall argres_ = getCommonArgument(a_, _conf);
        Argument arg_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else {
            PossibleIntermediateDotted n_ = getSiblingSet();
            if (n_ != null) {
                _nodes.getVal((OperationNode)n_).setPreviousArgument(arg_);
            }
        }
        return arg_;
    }
    ArgumentCall getCommonArgument(Argument _argument, ContextEl _conf) {
        InitClassState res_ = _conf.getClasses().getLocks().getState(_conf, className);
        if (res_ == InitClassState.NOT_YET) {
            InitializatingClass inv_ = new InitializatingClass(className);
            return ArgumentCall.newCall(inv_);
        }
        if (res_ == InitClassState.ERROR) {
            CausingErrorStruct causing_ = new CausingErrorStruct(className);
            _conf.setException(causing_);
            return ArgumentCall.newArgument(Argument.createVoid());
        }
        if (_argument.isNull()) {
            Argument argres_ = new Argument();
            return ArgumentCall.newArgument(argres_);
        }
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = classes_.getClassMetaInfo(className, _conf);
        for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
            if (StringList.quickEq(e.getKey(), (String) _argument.getObject())) {
                Argument argres_ = new Argument();
                argres_.setStruct(classes_.getStaticField(new ClassField(className, e.getKey())));
                return ArgumentCall.newArgument(argres_);
            }
        }
        Argument argres_ = new Argument();
        return ArgumentCall.newArgument(argres_);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
