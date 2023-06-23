package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.StringViewReplaceAliases;
import code.util.StringList;

public final class ResultContextViewReplacer {
    private ExecRootBlock viewType;
    private ExecNamedFunctionBlock viewMethod;
    private ExecRootBlock replaceType;
    private ExecNamedFunctionBlock replaceMethod;
    private String aliasStringSegment="";
    private String aliasStringSegmentBegin="";
    private String aliasStringSegmentEnd="";

    public void update(StringViewReplaceAliases _aliases, LgNamesContent _content, ContextEl _result) {
        viewType = _result.getClasses().getClassBody(_aliases.getAliasAbsStringView());
        MethodId index_ = new MethodId(MethodAccessKind.INSTANCE,
                _aliases.getAliasAbsStringViewIndex(),new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger()));
        viewMethod = ExecClassesUtil.getMethodBodiesById(viewType,index_).first();
        replaceType = _result.getClasses().getClassBody(_aliases.getAliasAbsStringReplacer());
        MethodId replace_ = new MethodId(MethodAccessKind.INSTANCE,
                _aliases.getAliasAbsStringReplacerReplace(),new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger()));
        replaceMethod = ExecClassesUtil.getMethodBodiesById(replaceType,replace_).first();
        aliasStringSegment = _aliases.getAliasStringSegment();
        aliasStringSegmentBegin = _aliases.getAliasStringSegmentBegin();
        aliasStringSegmentEnd = _aliases.getAliasStringSegmentEnd();
    }

    public ExecNamedFunctionBlock getReplaceMethod() {
        return replaceMethod;
    }

    public ExecNamedFunctionBlock getViewMethod() {
        return viewMethod;
    }

    public ExecRootBlock getReplaceType() {
        return replaceType;
    }

    public ExecRootBlock getViewType() {
        return viewType;
    }

    public String getAliasStringSegment() {
        return aliasStringSegment;
    }

    public String getAliasStringSegmentBegin() {
        return aliasStringSegmentBegin;
    }

    public String getAliasStringSegmentEnd() {
        return aliasStringSegmentEnd;
    }

}
