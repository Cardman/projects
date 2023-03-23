package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class ResultContextViewReplacer {
    private RunnableContextEl resultContext;
    private ReportedMessages reportedMessages = new ReportedMessages();
    private ExecRootBlock viewType;
    private ExecNamedFunctionBlock viewMethod;
    private ExecRootBlock replaceType;
    private ExecNamedFunctionBlock replaceMethod;
    private String aliasStringSegment="";
    private String aliasStringSegmentBegin="";
    private String aliasStringSegmentEnd="";
    private String lastBuilt=CustAliases.YYYY_MM_DD_HH_MM_SS_SSS_DASH;

    public String update(CustAliases _aliases, LgNamesContent _content, RunnableContextEl _result, AbstractProgramInfos _frames) {
        resultContext = _result;
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
        lastBuilt = CustAliases.getDateTimeText(_frames.getThreadFactory());
        return lastBuilt;
    }

    public RunnableContextEl getResultContext() {
        return resultContext;
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }

    public void setReportedMessages(ReportedMessages _rep) {
        this.reportedMessages = _rep;
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

    public String getLastBuilt() {
        return lastBuilt;
    }
}
