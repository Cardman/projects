package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.*;

final class EmptyPartType extends LeafPartType {

    EmptyPartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    void analyzeDepends(Analyzable _an,int _index,
            CustList<IntTreeMap< String>> _dels,
            RootBlock _rooted, boolean _exact) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    void analyzeInherits(Analyzable _an, int _index,
            CustList<IntTreeMap< String>> _dels, String _globalType,
            RootBlock _rooted,
            boolean _protected) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    void analyze(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
        analyzeTemplate(_an,_dels,new StringMap<StringList>());
    }

    @Override
    void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }

    @Override
    void analyzeLine(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<IntTreeMap< String>> _dels,
            AccessingImportingBlock _rooted) {
        _an.getCurrentBadIndexes().add(getIndexInType());
    }
    @Override
    void checkDynExistence(Analyzable _an,CustList<IntTreeMap< String>>_dels) {
    }
}
