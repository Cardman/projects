package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class AnaVariablePartType extends AnaLeafPartType {
    AnaVariablePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted) {
        analyzeLine(_an,null,_dels,_local,_rooted);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted) {
        if (getParent() instanceof AnaInnerPartType) {
            return;
        }
        if (getParent() instanceof AnaTemplatePartType && getIndex() == 0) {
            return;
        }
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        String trim_ = type_.trim();
        if (trim_.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
            type_ = trim_.substring(AnaTemplates.PREFIX_VAR_TYPE.length()).trim();
        } else {
            t_ = StringList.concat(AnaTemplates.PREFIX_VAR_TYPE,t_);
        }
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (!_an.getAnalyzing().getAvailableVariables().contains(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
        if (getParent() instanceof AnaInnerPartType) {
            return;
        }
        if (getParent() instanceof AnaTemplatePartType && getIndex() == 0) {
            return;
        }
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        if (type_.trim().startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
            type_ = type_.trim().substring(AnaTemplates.PREFIX_VAR_TYPE.length());
        } else {
            t_ = StringList.concat(AnaTemplates.PREFIX_VAR_TYPE,t_);
        }
        type_ = type_.trim();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (!_an.getAnalyzing().getAvailableVariables().contains(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }

    void processOffsets(ContextEl _an, AccessedBlock _rooted) {
        if (!_an.isGettingParts()) {
            return;
        }
        String curr_ = ((Block)_rooted).getFile().getRenderFileName();
        String imported_ = getAnalyzedType();
        AnalyzedPageEl ana_ = _an.getAnalyzing();
        Integer id_ = ana_.getAvailableVariables().getVal(imported_.substring(1));
        String rel_ = "";
        if (!ana_.getRefFileName().isEmpty()) {
            rel_ = LinkageUtil.relativize(curr_,ana_.getRefFileName());
        }
        setHref(rel_+"#m"+id_);
    }
}
