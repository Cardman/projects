package code.bean.help.fwd;

import code.bean.help.analyze.HelpResultText;
import code.bean.help.analyze.blocks.*;
import code.bean.help.exec.blocks.*;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.formathtml.*;
import code.formathtml.analyze.*;
import code.formathtml.analyze.blocks.*;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.*;
import code.formathtml.fwd.RendAnaExec;
import code.formathtml.fwd.RendForwardInfos;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpRendForwardInfos {
    private HelpRendForwardInfos() {
    }
    private static RendDocumentBlock build(AnaRendDocumentBlock _ana, AnalyzingDoc _anaDoc) {
        RendDocumentBlock rendDoc_ = new RendDocumentBlock("",null,null,_ana.getElt(), _ana.getBeanName(), ExecFormattedRootBlock.defValue());
        RendAnaExec pair_ = new RendAnaExec(_ana, rendDoc_);
        while (pair_.getRead() != null) {
            RendBlock loc_ = newHelpRendBlock(pair_.getRead());
            pair_.setWrite(completeHelp(_anaDoc, rendDoc_, pair_.getWrite(), loc_));
            RendForwardInfos.nextPair(pair_);
        }
        return rendDoc_;
    }

    private static RendParentBlockInt completeHelp(AnalyzingDoc _anaDoc, RendDocumentBlock _rendDoc, RendParentBlockInt _curPar, RendBlock _loc) {
        if (_loc != null) {
            if (_loc instanceof HelpRendStdElement && StringUtil.quickEq(((HelpRendStdElement) _loc).getRead().getTagName(), _anaDoc.getRendKeyWords().getKeyWordBody())) {
                _rendDoc.getBodies().add(_loc);
            }
//            _loc.setEscapedChars(_en.getEscapedChars());
            _curPar.appendChild(_loc);
        }
        if (_loc instanceof RendParentBlock) {
            return (RendParentBlock) _loc;
        }
        return _curPar;
    }

    private static RendBlock newHelpRendBlock(AnaRendBlock _current) {
        if (_current instanceof HelpAnaRendText){
            HelpAnaRendText t_ = (HelpAnaRendText) _current;
            ExecTextPart part_ = build(t_.getTexts());
            return new HelpRendText(part_);
        }
        return block2(_current);
    }

    private static RendBlock block2(AnaRendBlock _current) {
        return block(_current);
    }

    private static RendBlock block(AnaRendBlock _current) {
        return element(_current);
    }

    private static RendBlock element(AnaRendBlock _current) {
        if (_current instanceof HelpAnaRendImg){
            HelpAnaRendImg f_ = (HelpAnaRendImg) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            ExecTextPart partSub_ = build(f_.getTexts());
            return new HelpRendImg(f_.getRead(),part_, partSub_);
        }
        if (_current instanceof HelpAnaRendMessage){
            HelpAnaRendMessage f_ = (HelpAnaRendMessage) _current;
            return new HelpRendMessage(
                    f_.getPreformatted(),
                    f_.getVarNames());
        }
        return input(_current);
    }

    private static RendBlock input(AnaRendBlock _current) {
        if (_current instanceof HelpAnaRendEmptyInstruction){
            return new HelpRendEmptyInstruction();
        }
        if (_current instanceof HelpAnaRendStdElement) {
            HelpAnaRendStdElement f_ = (HelpAnaRendStdElement) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            return new HelpRendStdElement(f_.getRead(), part_);
        }
        return null;
    }

    private static StringMap<ExecTextPart> toExecPartExt(StringMap<HelpResultText> _texts) {
        StringMap<ExecTextPart> m_ = new StringMap<ExecTextPart>();
        for (EntryCust<String, HelpResultText> e: _texts.entryList()) {
            HelpResultText value_ = e.getValue();
            m_.addEntry(e.getKey(), build(value_.getTexts()));
        }
        return m_;
    }
    static ExecTextPart build(StringList _texts) {
        CustList<CustList<RendDynOperationNode>> parts_ = new CustList<CustList<RendDynOperationNode>>();
        ExecTextPart part_ = new ExecTextPart();
        part_.getTexts().addAllElts(_texts);
        part_.setOpExp(parts_);
        return part_;
    }

    public static RendDocumentBlock buildExec(AnalyzingDoc _analyzingDoc, AnaRendDocumentBlock _value) {
        return build(_value, _analyzingDoc);

    }

}
