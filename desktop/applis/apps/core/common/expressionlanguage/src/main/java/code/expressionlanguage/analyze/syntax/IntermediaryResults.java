package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AnonymousFunctionBlock;
import code.expressionlanguage.analyze.blocks.AnonymousTypeBlock;
import code.util.CustList;

public final class IntermediaryResults {
    private CustList<AnonymousTypeBlock> anonymousTypes = new CustList<AnonymousTypeBlock>();
    private CustList<AnonymousFunctionBlock> anonymousFunctions = new CustList<AnonymousFunctionBlock>();

    public CustList<AnonymousFunctionBlock> getAnonymousFunctions() {
        return anonymousFunctions;
    }

    public CustList<AnonymousTypeBlock> getAnonymousTypes() {
        return anonymousTypes;
    }
}
