package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnaRendBlock;
import code.bean.nat.analyze.blocks.NatAnaRendDocumentBlock;
import code.bean.nat.analyze.blocks.NatAnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.sml.Text;
import code.util.core.StringUtil;

public final class HelpAnaRendBlockHelp {

    private HelpAnaRendBlockHelp() {
    }

    public static NatAnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, RendKeyWords _rendKeyWords) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        NatAnaRendDocumentBlock out_ = new NatAnaRendDocumentBlock(documentElement_);
        NatAnaRendBlock curWrite_ = newRendBlockEsc(_prefix, curNode_, _rendKeyWords);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWrite_ instanceof NatAnaRendParentBlock &&firstChild_ != null) {
                NatAnaRendBlock rendBlock_ = newRendBlockEsc(_prefix, firstChild_, _rendKeyWords);
                ((NatAnaRendParentBlock) curWrite_).appendChild(rendBlock_);
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            tryAppendEmptyBlock(curWrite_);
            while (curWrite_ != null) {
                Node nextSibling_ = curNode_.getNextSibling();
                NatAnaRendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    NatAnaRendBlock rendBlock_ = newRendBlockEsc(_prefix, nextSibling_, _rendKeyWords);
                    par_.appendChild(rendBlock_);
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = AnaRendBlockHelp.tryGetParent(documentElement_, curNode_);
                if (parentNode_ == null) {
                    curWrite_ = null;
                } else {
                    curWrite_ = par_;
                    curNode_ = parentNode_;
                }
            }
        }
        return out_;
    }

    private static void tryAppendEmptyBlock(NatAnaRendBlock _curWrite) {
        if (_curWrite instanceof NatAnaRendParentBlock) {
            HelpAnaRendEmptyInstruction empty_ = new HelpAnaRendEmptyInstruction();
            ((NatAnaRendParentBlock) _curWrite).appendChild(empty_);
        }
    }

    private static NatAnaRendBlock newRendBlockEsc(String _prefix, Node _elt, RendKeyWords _rendKeyWords) {
        NatAnaRendBlock bl_;
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            bl_ = new HelpAnaRendText(t_.getTextContent());
//            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
//            AttributePart attrPart_ = new AttributePart();
//            attrPart_.setBegin(_begin);
//            attrPart_.setEnd(endHeader_);
//            IntTreeMap<Integer> esc_ = AnaRendBlock.getIndexesSpecChars(_docText, false, attrPart_, _begin);
//            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
//            infos_.addEntry(EMPTY_STRING, esc_);
//            bl_.setEscapedChars(infos_);
        } else {
            Element elt_ = (Element) _elt;
            bl_ = element(_prefix, elt_, _rendKeyWords);
//            String tagName_ = elt_.getTagName();
//            int endHeader_ = _docText.indexOf(GT_TAG, _begin);
//            int beginHeader_ = _begin + tagName_.length();
//            StringMap<AttributePart> attr_;
//            attr_ = getAttributes(_docText, beginHeader_, endHeader_);
//            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
//            for (EntryCust<String, AttributePart> e : attr_.entryList()) {
//                infos_.put(e.getKey(), AnaRendBlock.getIndexesSpecChars(_docText, true, e.getValue(), _begin));
//            }
//            bl_.setEscapedChars(infos_);
        }
        return bl_;
    }

    private static NatAnaRendParentBlock element(String _prefix, Element _elt, RendKeyWords _rendKeyWords) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new HelpAnaRendImg(_elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new HelpAnaRendMessage(_elt);
        }
        return input(_elt);
    }

    private static NatAnaRendParentBlock input(Element _elt) {
        return new HelpAnaRendStdElement(_elt);
    }

//    private static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
//        return DocumentAttribute.getAttributes(_html, _from, _to);
//    }

}
