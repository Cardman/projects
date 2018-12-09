package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;

final class EmptyPartType extends LeafPartType {

    public EmptyPartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    public void analyzeDepends(Analyzable _an,int _index,
            CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    public void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted, boolean _exact,
            boolean _protected) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    public void analyzeAccessibleId(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
            AccessingImportingBlock _rooted) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    public void checkDynExistence(Analyzable _an,CustList<NatTreeMap<Integer, String>>_dels) {
    }
}
