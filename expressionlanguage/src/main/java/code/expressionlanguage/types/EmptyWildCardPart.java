package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;

final class EmptyWildCardPart extends LeafPartType {

    EmptyWildCardPart(ParentPartType _parent, int _index,
            int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels, RootBlock _rooted,
            boolean _exact) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            stopDepends();
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyze(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            AccessingImportingBlock _rooted, boolean _exact) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted, boolean _exact,
            boolean _protected) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
            AccessingImportingBlock _rooted) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void checkDynExistence(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setImportedTypeName(Templates.SUB_TYPE);
    }
}
