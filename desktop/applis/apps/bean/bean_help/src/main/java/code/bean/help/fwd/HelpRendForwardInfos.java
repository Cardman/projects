package code.bean.help.fwd;

import code.bean.help.analyze.HelpResultText;
import code.bean.help.analyze.blocks.*;
import code.bean.help.exec.blocks.*;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.bean.nat.fwd.NatAnaExec;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HelpRendForwardInfos {
    private HelpRendForwardInfos() {
    }
    private static NatDocumentBlock build(AnaRendDocumentBlock _ana, AnalyzingDoc _anaDoc) {
        NatDocumentBlock rendDoc_ = new NatDocumentBlock(_ana.getElt(), _ana.getBeanName());
        NatAnaExec pair_ = new NatAnaExec(_ana, rendDoc_);
        while (pair_.getReadNat() != null) {
            RendBlock loc_ = newHelpRendBlock(pair_.getReadNat());
            pair_.setWriteNat(completeHelp(_anaDoc, rendDoc_, pair_.getWriteNat(), loc_));
            NatRendForwardInfos.nextPair(pair_);
        }
        return rendDoc_;
    }

    private static RendParentBlock completeHelp(AnalyzingDoc _anaDoc, NatDocumentBlock _rendDoc, RendParentBlock _curPar, RendBlock _loc) {
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
            NatExecTextPart part_ = build(t_.getTexts());
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
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            NatExecTextPart partSub_ = build(f_.getTexts());
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
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            return new HelpRendStdElement(f_.getRead(), part_);
        }
        return null;
    }

    private static StringMap<NatExecTextPart> toExecPartExt(StringMap<HelpResultText> _texts) {
        StringMap<NatExecTextPart> m_ = new StringMap<NatExecTextPart>();
        for (EntryCust<String, HelpResultText> e: _texts.entryList()) {
            HelpResultText value_ = e.getValue();
            m_.addEntry(e.getKey(), build(value_.getTexts()));
        }
        return m_;
    }
    static NatExecTextPart build(StringList _texts) {
        CustList<CustList<RendDynOperationNode>> parts_ = new CustList<CustList<RendDynOperationNode>>();
        NatExecTextPart part_ = new NatExecTextPart();
        part_.getTexts().addAllElts(_texts);
        part_.setOpExp(parts_);
        return part_;
    }

    public static NatDocumentBlock buildExec(AnalyzingDoc _analyzingDoc, AnaRendDocumentBlock _value) {
        return build(_value, _analyzingDoc);

    }

}
