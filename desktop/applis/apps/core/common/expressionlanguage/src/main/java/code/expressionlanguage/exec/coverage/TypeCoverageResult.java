package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.CustList;
import code.util.IdMap;

public final class TypeCoverageResult {
    private final CustList<FunctionCoverageResult> functions = new CustList<FunctionCoverageResult>();
    private final CustList<FunctionCoverageResult> functionsCtor = new CustList<FunctionCoverageResult>();
    private final CustList<FunctionCoverageResult> functionsInst = new CustList<FunctionCoverageResult>();
    private final CustList<FunctionCoverageResult> functionsStat = new CustList<FunctionCoverageResult>();
    private final IdMap<ExecBlock,MemberCallingsBlock> mappingBlocks = new IdMap<ExecBlock,MemberCallingsBlock>();
    private final IdMap<ExecBlock, AbsBk> mappingFields = new IdMap<ExecBlock, AbsBk>();
    private final CustList<BlockCoverageResult> fields = new CustList<BlockCoverageResult>();
    private final CustList<BlockCoverageResult> annotationsFields = new CustList<BlockCoverageResult>();
    private final CustList<BlockCoverageResult> annotations = new CustList<BlockCoverageResult>();

    public CustList<FunctionCoverageResult> getFunctions() {
        return functions;
    }

    public CustList<FunctionCoverageResult> getFunctionsCtor() {
        return functionsCtor;
    }

    public CustList<FunctionCoverageResult> getFunctionsInst() {
        return functionsInst;
    }

    public CustList<FunctionCoverageResult> getFunctionsStat() {
        return functionsStat;
    }

    public IdMap<ExecBlock, MemberCallingsBlock> getMappingBlocks() {
        return mappingBlocks;
    }

    public IdMap<ExecBlock, AbsBk> getMappingFields() {
        return mappingFields;
    }

    public CustList<BlockCoverageResult> getFields() {
        return fields;
    }

    public CustList<BlockCoverageResult> getAnnotationsFields() {
        return annotationsFields;
    }

    public CustList<BlockCoverageResult> getAnnotations() {
        return annotations;
    }
}
