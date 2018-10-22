package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

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
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        String clName_;
        clName_ = _conf.resolveIdType(className);
        RootBlock r_ = classes_.getClassBody(clName_);
        if (!(r_ instanceof EnumBlock)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            un_.setType(clName_);
            _conf.getClasses().addError(un_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = Templates.getIdFromAllTypes(glClass_);
        }
        if (!Classes.canAccessClass(curClassBase_, clName_, _conf)) {
            BadAccessClass badAccess_ = new BadAccessClass();
            badAccess_.setId(clName_);
            badAccess_.setRc(_conf.getCurrentLocation());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(badAccess_);
        }
        ClassArgumentMatching argCl_ = firstArgs_.first();
        String stringType_ = _conf.getStandards().getAliasString();
        if (!argCl_.matchClass(stringType_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            un_.setType(argCl_);
            _conf.getClasses().addError(un_);
        }
        StringList allElements_ = new StringList();
        for (Block e: Classes.getDirectChildren(r_)) {
            if (e instanceof ElementBlock) {
                String type_ = ((ElementBlock)e).getImportedClassName();
                allElements_.add(type_);
            }
        }
        allElements_.removeDuplicates();
        if (allElements_.size() == 1) {
            className = allElements_.first();
        } else {
            className = r_.getWildCardString();
        }
        setResultClass(new ClassArgumentMatching(className));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        OperationNode first_ = getFirstChild();
        Argument arg_ = first_.getArgument();
        Argument argres_ = getCommonArgument(arg_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getCommonArgument(arg_, _conf);
        }
        if (_conf.getException() != null) {
            return;
        }
        Argument argRes_ = argres_;
        setSimpleArgument(argRes_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        OperationNode first_ = getFirstChild();
        Argument a_ = _nodes.getVal(first_).getArgument();
        Argument arg_ = getCommonArgument(a_, _conf);
        if (_conf.callsOrException()) {
            return arg_;
        }
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getVal((OperationNode)n_).setPreviousArgument(arg_);
        }
        return arg_;
    }
    Argument getCommonArgument(Argument _argument, ExecutableCode _conf) {
        return InvokingOperation.getEnumValue(className, _argument, _conf);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
