package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.*;

final class EmptyWildCardPart extends LeafPartType {

    EmptyWildCardPart(ParentPartType _parent, int _index,
            int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type,_previousSeparator);
    }

    @Override
    void analyze(ContextEl _an,
            CustList<IntTreeMap< String>> _dels, String _globalType,
                 AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_globalType,_local,_rooted);
    }

    @Override
    void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeTemplateExec(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeLine(ContextEl _an,
                     ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, String _globalType,
                     AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an,
            CustList<IntTreeMap< String>> _dels,
                             AccessingImportingBlock _rooted) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void checkDynExistence(ContextEl _an,
            CustList<IntTreeMap< String>> _dels) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        setImportedTypeName(Templates.SUB_TYPE);
    }
}
