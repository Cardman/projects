package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;

final class EmptyWildCardPart extends LeafPartType {

    public EmptyWildCardPart(ParentPartType _parent, int _index,
            int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    public void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels, RootBlock _rooted,
            boolean _exact, RowCol _location) {
        if (!(getParent() instanceof TemplatePartType)) {
            stopDepends();
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    public void analyze(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            AccessingImportingBlock _rooted, boolean _exact, boolean _protected, RowCol _location) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    public void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            AccessingImportingBlock _rooted, boolean _exact,
            boolean _protected, RowCol _location) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }
    @Override
    public void analyze(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            AccessingImportingBlock _rooted, boolean _exact) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }


    @Override
    public void checkDynExistence(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setImportedTypeName(Templates.SUB_TYPE);
    }

}
