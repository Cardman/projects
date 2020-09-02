package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class AnaVariablePartType extends AnaLeafPartType {
    private int value;
    AnaVariablePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, int _value) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
        value = _value;
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted) {
        analyzeLine(_an,null,_dels,_local,_rooted);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted) {
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        t_ = StringList.concat(AnaTemplates.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        t_ = StringList.concat(AnaTemplates.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    void processOffsets(ContextEl _an, AccessedBlock _rooted) {
        if (!_an.isGettingParts()) {
            return;
        }
        String curr_ = ((Block)_rooted).getFile().getRenderFileName();
        AnalyzedPageEl ana_ = _an.getAnalyzing();
        String rel_ = "";
        if (!ana_.getRefFileName().isEmpty()) {
            rel_ = LinkageUtil.relativize(curr_,ana_.getRefFileName());
        }
        setHref(rel_+"#m"+value);
    }
}
