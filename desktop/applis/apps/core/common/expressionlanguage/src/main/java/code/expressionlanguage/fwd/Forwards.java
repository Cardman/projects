package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.AnonymousInstancingOperation;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecAnonymousInstancingOperation;
import code.expressionlanguage.exec.opers.ExecAnonymousLambdaOperation;
import code.util.IdMap;

public final class Forwards {
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> mapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final IdMap<RootBlock,Members> mapMembers = new IdMap<RootBlock,Members>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final IdMap<AnonymousFunctionBlock,ExecAnonymousFunctionBlock> mapAnonLambda = new IdMap<AnonymousFunctionBlock,ExecAnonymousFunctionBlock>();
    private final IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock> mapAnonTypes = new IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFct = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
    private final IdMap<AnonymousInstancingOperation,ExecAnonymousInstancingOperation> mapAnonymous = new IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation>();
    private final IdMap<AnonymousLambdaOperation,ExecAnonymousLambdaOperation> mapAnonymousLambda = new IdMap<AnonymousLambdaOperation,ExecAnonymousLambdaOperation>();

    public IdMap<AnonymousTypeBlock, ExecAnonymousTypeBlock> getMapAnonTypes() {
        return mapAnonTypes;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFct() {
        return allFct;
    }

    public IdMap<AnonymousFunctionBlock, ExecAnonymousFunctionBlock> getMapAnonLambda() {
        return mapAnonLambda;
    }

    public IdMap<OperatorBlock, ExecOperatorBlock> getMapOperators() {
        return mapOperators;
    }

    public IdMap<InnerElementBlock, ExecInnerElementBlock> getMapInnerEltTypes() {
        return mapInnerEltTypes;
    }

    public IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> getMapAnonymous() {
        return mapAnonymous;
    }

    public IdMap<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> getMapAnonymousLambda() {
        return mapAnonymousLambda;
    }

    public IdMap<RootBlock, Members> getMapMembers() {
        return mapMembers;
    }
}
