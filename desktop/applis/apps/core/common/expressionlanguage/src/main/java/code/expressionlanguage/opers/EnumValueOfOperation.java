package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.*;

public final class EnumValueOfOperation extends AbstractUnaryOperation {

    private String className;
    private int argOffset;

    public EnumValueOfOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        Classes classes_ = _conf.getClasses();
        String clName_;
        clName_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,0,className);
        ExecRootBlock r_ = classes_.getExecClassBody(clName_);
        if (!(r_ instanceof ExecEnumBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //className len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    clName_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        String curClassBase_ = Templates.getIdFromAllTypes(glClass_);
        if (!Classes.canAccess(curClassBase_,(ExecBlock) r_, _conf)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //className len
            badAccess_.buildError(_conf.getAnalysisMessages().getInaccessibleType(),
                    clName_,
                    curClassBase_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
        }
        ClassArgumentMatching argCl_ = firstArgs_.first();
        String stringType_ = _conf.getStandards().getAliasString();
        if (!argCl_.matchClass(stringType_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //separator after className
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(argCl_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(un_);
        }
        className = r_.getWildCardElement();
        setResultClass(new ClassArgumentMatching(className));
    }

    public String getClassName() {
        return className;
    }

    public int getArgOffset() {
        return argOffset;
    }
}
