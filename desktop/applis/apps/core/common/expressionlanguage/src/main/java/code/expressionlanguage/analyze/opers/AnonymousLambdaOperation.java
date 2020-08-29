package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ParametersGroup;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.files.ParsedFctHeader;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;

public final class AnonymousLambdaOperation extends
        LeafOperation {
    private ClassMethodId method;
    private String foundClass;
    private String fileName;
    private String returnFieldType = "";
    private AnonymousFunctionBlock block;
    private ParsedFctHeader parse;
    private int rootNumber = -1;

    public AnonymousLambdaOperation(int _index, int _indexChild,
                                    MethodOperation _m, OperationsSequence _op, AnonymousFunctionBlock _block, ParsedFctHeader _parse) {
        super(_index, _indexChild, _m, _op);
        block = _block;
        parse = _parse;
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        analyzeCtor(_conf);
    }
    private void analyzeCtor(ContextEl _conf) {
        foundClass = _conf.getAnalyzing().getGlobalClass();
        fileName = _conf.getAnalyzing().getLocalizer().getCurrentFileName();
        RootBlock globalType_ = _conf.getAnalyzing().getGlobalType();
        rootNumber = globalType_.getNumberAll();
        block.setParentType(globalType_);
        block.getAllReservedInners().addAllElts(_conf.getAnalyzing().getGlobalDirType().getAllReservedInners());
        MemberCallingsBlock currentFct_ = _conf.getAnalyzing().getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getAnonymousFct().add(block);
            block.getMappings().putAllMap(currentFct_.getMappings());
            block.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            block.getMappings().putAllMap(_conf.getAnalyzing().getGlobalDirType().getMappings());
        }
        block.setVarargs(parse.getParametersType(),parse.getOffestsTypes(),
                parse.getParametersName(),parse.getOffestsParams(),
                parse.getReturnType(), parse.getReturnOffest());
        block.buildInternImportedTypes(_conf);
        globalType_.validateParameters(_conf,block);
        Block currentBlock_ = _conf.getAnalyzing().getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getAnonymousFct().add(block);
        }
        String importedReturnType_ = block.getImportedReturnType();
        ParametersGroup p_ = new ParametersGroup();
        MethodId idC_ = block.getId();
        MethodInfo mloc_ = new MethodInfo();
        returnFieldType = importedReturnType_;
        mloc_.setOriginalReturnType(importedReturnType_);
        mloc_.setClassName(foundClass);
        mloc_.setConstraints(idC_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(importedReturnType_);
        mloc_.format(idC_.getKind() == MethodAccessKind.STATIC,_conf);
        MethodId constraints_ = mloc_.getConstraints();
        String baseClassName_ = mloc_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = mloc_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(mloc_.getReturnType());
        res_.setOriginalReturnType(mloc_.getOriginalReturnType());
        res_.setFileName(mloc_.getFileName());
        res_.setAncestor(mloc_.getAncestor());
        res_.setAbstractMethod(mloc_.isAbstractMethod());
        res_.setStaticMethod(block.isStaticMethod());
        String foundClass_ = res_.getRealClass();
        MethodId idCt_ = res_.getRealId();
        if (idCt_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        foundClass = res_.getId().getClassName();
        method = new ClassMethodId(foundClass_, idCt_);
        String fct_ = LambdaOperation.formatReturn(foundClass, _conf, res_, false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public AnonymousFunctionBlock getBlock() {
        return block;
    }

    public String getFileName() {
        return fileName;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public String getFoundClass() {
        return foundClass;
    }
}
