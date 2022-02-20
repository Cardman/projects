package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.*;
import code.util.core.StringUtil;

public final class HelpAnaRendBlockHelp {
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';

    private HelpAnaRendBlockHelp() {
    }

    public static AnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, String _docText, String _currentUrl, RendKeyWords _rendKeyWords) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(0,documentElement_,_docText,0, _currentUrl);
        AnaRendBlock curWrite_ = newRendBlockEsc(indexGlobal_, _prefix, curNode_, _rendKeyWords);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWrite_ instanceof AnaRendParentBlock &&firstChild_ != null) {
                indexGlobal_ = AnaRendBlock.indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_, _prefix, firstChild_, _rendKeyWords);
                ((AnaRendParentBlock) curWrite_).appendChild(rendBlock_);
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            tryAppendEmptyBlock(curWrite_);
            while (curWrite_ != null) {
                Node nextSibling_ = curNode_.getNextSibling();
                AnaRendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = AnaRendBlock.indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_, _prefix, nextSibling_, _rendKeyWords);
                    par_.appendChild(rendBlock_);
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = AnaRendBlockHelp.tryGetParent(documentElement_, curNode_);
                if (parentNode_ == null) {
                    curWrite_ = null;
                } else {
                    indexGlobal_ = _docText.indexOf("</"+parentNode_.getTagName()+">",indexGlobal_)+2+parentNode_.getTagName().length()+2;
                    curWrite_ = par_;
                    curNode_ = parentNode_;
                }
            }
        }
        return out_;
    }

    private static void tryAppendEmptyBlock(AnaRendBlock _curWrite) {
        if (_curWrite instanceof AnaRendParentBlock) {
            int off_ = _curWrite.getOffset();
            HelpAnaRendEmptyInstruction empty_ = new HelpAnaRendEmptyInstruction(off_);
            ((AnaRendParentBlock) _curWrite).appendChild(empty_);
        }
    }

    private static AnaRendBlock newRendBlockEsc(int _begin, String _prefix, Node _elt, RendKeyWords _rendKeyWords) {
        AnaRendBlock bl_;
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            bl_ = new HelpAnaRendText(new OffsetStringInfo(_begin, t_.getTextContent()), _begin);
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
            bl_ = element(_begin, _prefix, elt_, _rendKeyWords);
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

    private static AnaRendParentBlock element(int _begin, String _prefix, Element _elt, RendKeyWords _rendKeyWords) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new HelpAnaRendImg(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new HelpAnaRendMessage(_elt, _begin);
        }
        return input(_begin, _elt);
    }

    private static AnaRendParentBlock input(int _begin, Element _elt) {
        return new HelpAnaRendStdElement(_elt,_begin);
    }

//    private static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
//        return DocumentAttribute.getAttributes(_html, _from, _to);
//    }

}
