package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessedBlock;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.*;

final class EmptyWildCardPart extends LeafPartType {

    EmptyWildCardPart(ParentPartType _parent, int _index,
            int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    void analyze(Analyzable _an,
            CustList<IntTreeMap< String>> _dels, String _globalType,
                 AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        analyzeLine(_an,_dels,_globalType,_local,_rooted);
    }

    @Override
    void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeLine(Analyzable _an,
            CustList<IntTreeMap< String>> _dels, String _globalType,
                     AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<IntTreeMap< String>> _dels,
                             AccessingImportingBlock _rooted) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void checkDynExistence(Analyzable _an,
            CustList<IntTreeMap< String>> _dels) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setImportedTypeName(Templates.SUB_TYPE);
    }
}
