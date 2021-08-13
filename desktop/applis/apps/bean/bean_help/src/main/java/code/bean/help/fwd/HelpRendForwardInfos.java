package code.bean.help.fwd;

import code.bean.help.analyze.HelpResultText;
import code.bean.help.analyze.blocks.*;
import code.bean.help.exec.blocks.*;
import code.formathtml.*;
import code.formathtml.analyze.*;
import code.formathtml.analyze.blocks.*;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.*;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpRendForwardInfos {
    private HelpRendForwardInfos() {
    }
    private static RendDocumentBlock build(AnaRendDocumentBlock _ana, AnalyzingDoc _anaDoc) {
        RendDocumentBlock rendDoc_ = new RendDocumentBlock(_ana.getElt(), _ana.getFile(), _ana.getFileName(), _ana.getBeanName());
        RendParentBlock curPar_ = rendDoc_;
        AnaRendBlock en_ = _ana;
        while (en_ != null) {
            RendBlock loc_ = newRendBlock(en_);
            curPar_ = complete(_anaDoc, rendDoc_, curPar_, en_, loc_);
            AnaRendBlock n_ = en_.getFirstChild();
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            while (en_ != null) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                AnaRendParentBlock parent_ = en_.getParent();
                curPar_ = curPar_.getParent();
                if (curPar_ == null) {
                    en_ = null;
                } else {
                    en_ = parent_;
                }
            }
        }
        return rendDoc_;
    }

    private static RendParentBlock complete(AnalyzingDoc _anaDoc, RendDocumentBlock _rendDoc, RendParentBlock _curPar, AnaRendBlock _en, RendBlock _loc) {
        if (_loc != null) {
            if (_loc instanceof HelpRendStdElement && StringUtil.quickEq(((HelpRendStdElement) _loc).getRead().getTagName(), _anaDoc.getRendKeyWords().getKeyWordBody())) {
                _rendDoc.getBodies().add(_loc);
            }
            _loc.setEscapedChars(_en.getEscapedChars());
            _curPar.appendChild(_loc);
        }
        if (_loc instanceof RendParentBlock) {
            return (RendParentBlock) _loc;
        }
        return _curPar;
    }

    private static RendBlock newRendBlock(AnaRendBlock _current) {
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

    public static void buildExec(AnalyzingDoc _analyzingDoc, Configuration _conf, String _key, AnaRendDocumentBlock _value) {
        buildExec(_conf, _analyzingDoc, _key, _value);

    }

    private static void buildExec(Configuration _conf, AnalyzingDoc _anaDoc, String _key, AnaRendDocumentBlock _value) {
        RendDocumentBlock rendDoc_ = build(_value, _anaDoc);
        _conf.getRenders().put(_key, rendDoc_);
        String currentUrl2_ = _conf.getFirstUrl();
        String realFilePath2_ = Configuration.getRealFilePath(_conf.getCurrentLanguage(), currentUrl2_);
        _conf.setRendDocumentBlock(_conf.getRenders().getVal(realFilePath2_));
    }

}
