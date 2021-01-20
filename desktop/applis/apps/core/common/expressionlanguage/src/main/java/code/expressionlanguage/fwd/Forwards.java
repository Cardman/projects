package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.blocks.*;
import code.util.IdMap;

public final class Forwards {
    private String aliasPrimBoolean="";
    private String aliasBoolean="";
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> mapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final IdMap<RootBlock,Members> mapMembers = new IdMap<RootBlock,Members>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final IdMap<AnonymousFunctionBlock,ExecAnonymousFunctionBlock> mapAnonLambda = new IdMap<AnonymousFunctionBlock,ExecAnonymousFunctionBlock>();
    private final IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod> mapSwitchMethods = new IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod>();
    private final IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock> mapAnonTypes = new IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFct = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFctBodies = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();

    public IdMap<AnonymousTypeBlock, ExecAnonymousTypeBlock> getMapAnonTypes() {
        return mapAnonTypes;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFct() {
        return allFct;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFctBodies() {
        return allFctBodies;
    }

    public IdMap<AnonymousFunctionBlock, ExecAnonymousFunctionBlock> getMapAnonLambda() {
        return mapAnonLambda;
    }

    public IdMap<SwitchMethodBlock, ExecAbstractSwitchMethod> getMapSwitchMethods() {
        return mapSwitchMethods;
    }

    public IdMap<OperatorBlock, ExecOperatorBlock> getMapOperators() {
        return mapOperators;
    }

    public IdMap<InnerElementBlock, ExecInnerElementBlock> getMapInnerEltTypes() {
        return mapInnerEltTypes;
    }

    public IdMap<RootBlock, Members> getMapMembers() {
        return mapMembers;
    }

    public String getAliasBoolean() {
        return aliasBoolean;
    }

    public void setAliasBoolean(String _aliasBoolean) {
        this.aliasBoolean = _aliasBoolean;
    }

    public String getAliasPrimBoolean() {
        return aliasPrimBoolean;
    }

    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        this.aliasPrimBoolean = _aliasPrimBoolean;
    }
}
