package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadAccessClass;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.NatTreeMap;
import code.util.StringList;

public final class ValuesOperation extends VariableLeafOperation {

    private String className;
    private int argOffset;

    public ValuesOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.clear();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        String clName_;
        clName_ = _conf.resolveAccessibleIdType(className);
        RootBlock r_ = classes_.getClassBody(clName_);
        if (!(r_ instanceof EnumBlock)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setIndexFile(_conf.getCurrentLocationIndex());
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
            badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(badAccess_);
        }
        className = r_.getWildCardElement();
        String ret_ = PrimitiveTypeUtil.getPrettyArrayType(className);
        setResultClass(new ClassArgumentMatching(ret_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }

    public String getClassName() {
        return className;
    }
    public int getArgOffset() {
        return argOffset;
    }
}
