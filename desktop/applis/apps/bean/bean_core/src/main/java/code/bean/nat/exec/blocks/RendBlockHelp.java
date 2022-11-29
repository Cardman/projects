package code.bean.nat.exec.blocks;

import code.bean.nat.*;
import code.bean.nat.exec.*;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.stacks.RendReadWrite;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.FullDocument;
import code.util.*;
import code.util.core.StringUtil;

public final class RendBlockHelp {
    static final String EMPTY_STRING = "";

    private RendBlockHelp(){
    }

    public static String res(NatDocumentBlock _rend, Configuration _conf, NatRendStackCall _rendStackCall, String _beanName, Struct _bean,NatImportingPageAbs _pa) {
        NatImportingPageAbs ip_ = _pa.fwd();
        int tabWidth_ = _conf.getTabWidth();
        ip_.setBeanName(_beanName);
        ip_.setGlobalArgumentStruct(Argument.getNull(_bean));
        _rendStackCall.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        appendChild(doc_,(Element) null, _rend.getElt());
        NatRendReadWrite rw_ = _pa.newNatRendReadWrite(_rendStackCall);
        rw_.setRead(_rend);
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            NatImportingPageAbs p_ = _rendStackCall.getLastPage();
            NatRendReadWrite rendReadWrite_ = p_.getRendReadWrite();
            NatBlock read_ = null;
            if (rendReadWrite_ != null) {
                read_ = rendReadWrite_.getRead();
            }
            if (read_ == null) {
                _rendStackCall.removeLastPage();
                if (_rendStackCall.getImporting().isEmpty()) {
                    break;
                }
            } else {
                read_.processEl(_conf, _rendStackCall);
            }
        }
        _rendStackCall.setBeanName(doc_.getDocumentElement().getAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean())));
        doc_.getDocumentElement().removeAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean()));
        doc_.getDocumentElement().removeAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrAlias()));
        _rendStackCall.setDocument(doc_);
        _rendStackCall.clearPages();
        return doc_.export();
    }
    public static Element appendChild(Document _doc, RendReadWrite _rw, Element _read) {
        return appendChild(_doc,_rw.getWrite(),_read);
    }
    public static Element appendChild(Document _doc, Element _parent, Element _read) {
        String tagName_ = _read.getTagName();
        Element currentNode_ = _doc.createElement(tagName_);
        RendBlock.setNormalAttributes(_read, currentNode_);
        RendBlock.simpleAppendChild(_doc, _parent, currentNode_);
        return currentNode_;
    }
    private static boolean isNextIfParts(NatBlock _n) {
        return isStrictNextIfParts(_n);
    }

    private static boolean isStrictNextIfParts(NatBlock _n) {
        return _n instanceof NatRendElseIfCondition || _n instanceof NatRendElseCondition;
    }

    public static void processBlockAndRemove(NatRendStackCall _rendStackCall, NatBlock _rendBlock) {
        NatImportingPageAbs ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_rendStackCall, _rendBlock);
    }

    public static void processBlock(NatRendStackCall _rendStackCall, NatBlock _rendBlock) {
        NatImportingPageAbs ip_ = _rendStackCall.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        NatBlock nextSiblingNat_ = _rendBlock.getNextSibling();
        if (nextSiblingNat_ != null) {
            rw_.setRead(nextSiblingNat_);
            return;
        }
        NatParentBlock par_ = _rendBlock.getParent();
        NatAbstractStask lastStackNat_ = ip_.tryGetRendLastStack();
        if (lastStackNat_ != null) {
            rw_.setRead(par_);
            if (lastStackNat_ instanceof NatLoopBlockStack) {
                processLastElementLoop(_rendStackCall, par_, (NatLoopBlockStack) lastStackNat_);
            }
            if (lastStackNat_ instanceof NatIfStack) {
                nextIfStack(rw_, (NatIfStack) lastStackNat_);
            }
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(NatRendReadWrite _rw, NatIfStack _lastStack) {
        _rw.setRead(_lastStack.getLastBlock());
    }

    private static void processLastElementLoop(NatRendStackCall _rendStackCall, NatParentBlock _par, NatLoopBlockStack _lastStack) {
        if (_par instanceof NatRendAbstractForEachLoop) {
            ((NatRendAbstractForEachLoop) _par).processLastElementLoop(_lastStack, _rendStackCall);
        }
        if (_par instanceof NatRendForEachTable) {
            ((NatRendForEachTable) _par).processLastElementLoop(_lastStack, _rendStackCall);
        }
    }

    static void processVisitedLoop(NatBlock _next, NatRendStackCall _stackCall) {
        processBlockAndRemove(_stackCall, _next);
    }

    static void processIf(NatRendStackCall _rendStack, NatRendIfCondition _cond) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(_cond)) {
            processBlockAndRemove(_rendStack, _cond);
            return;
        }
        ConditionReturn toEnter_ = evaluateCondition(_rendStack, _cond.getCondition());
        NatIfStack if_ = new NatIfStack();
        if_.setLastBlock(_cond);
        NatBlock n_ = _cond.getNextSibling();
        while (isNextIfParts(n_)) {
            if_.setLastBlock((NatParentBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(_cond);
        if_.setCurrentVisitedBlock(_cond);
        ip_.addBlock(if_);
        if (toEnter_ == ConditionReturn.YES) {
            if_.setEntered(true);
            rw_.setRead(_cond.getFirstChild());
        } else {
            NatBlock next_ = _cond.getNextSibling();
            if (isNextIfParts(next_)) {
                rw_.setRead(next_);
            }
        }
    }

    public static void processElseIf(NatRendCondition _cond, NatRendStackCall _rendStackCall) {
        NatImportingPageAbs ip_ = _rendStackCall.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        NatAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof NatIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((NatIfStack) if_).isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_rendStackCall, _cond.getCondition());
            if (assert_ == ConditionReturn.YES) {
                ((NatIfStack) if_).setEntered(true);
                rw_.setRead(_cond.getFirstChild());
                return;
            }
        }
        NatBlock n_ = _cond.getNextSibling();
        if (isStrictNextIfParts(n_)) {
            rw_.setRead(n_);
            return;
        }
        processBlockAndRemove(_rendStackCall, _cond);
    }

    public static void processElse(NatParentBlock _cond, NatRendStackCall _rendStackCall) {
        processEnt(_cond, _rendStackCall);
    }

    public static void processEnt(NatParentBlock _cond, NatRendStackCall _rendStackCall) {
        NatImportingPageAbs ip_ = _rendStackCall.getLastPage();
        NatAbstractStask ifNat_ = ip_.tryGetRendLastStack();
        if (!(ifNat_ instanceof NatIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        ifNat_.setCurrentVisitedBlock(_cond);
        if (!((NatIfStack)ifNat_).isEntered()) {
            ((NatIfStack)ifNat_).setEntered(true);
            ip_.getRendReadWrite().setRead(_cond.getFirstChild());
            return;
        }
        processBlockAndRemove(_rendStackCall, _cond);
    }

    private static ConditionReturn evaluateCondition(NatRendStackCall _rendStackCall, NatRendOperationNodeListOff _condition) {
        Argument arg_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(_condition.getList(), _rendStackCall).lastValue().getArgument());
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    static Argument nasNextCom(Struct _arg) {
        SimpleItrStruct simpleItrStruct_ = BeanNatCommonLgNames.getSimpleItrStruct(_arg);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    static Argument nextCom(Struct _arg) {
        SimpleItrStruct simpleItrStruct_ = BeanNatCommonLgNames.getSimpleItrStruct(_arg);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    static Argument first(Struct _arg) {
        PairStruct pairStruct_ = BeanNatCommonLgNames.getPairStruct(_arg);
        Struct resObj_ = pairStruct_.getFirst();
        return new Argument(resObj_);
    }

    static Argument second(Struct _arg) {
        PairStruct pairStruct_ = BeanNatCommonLgNames.getPairStruct(_arg);
        Struct resObj_ = pairStruct_.getSecond();
        return new Argument(resObj_);
    }

    static Argument iterator(Struct _arg) {
        NatArrayStruct array_ = BeanNatCommonLgNames.getArray(_arg);
        return new Argument(new SimpleItrStruct(array_));
    }

    public static void setupOverrides(StringMap<SpecialNatClass> _standardsTypes) {
        buildInherits(_standardsTypes);
    }

    public static void buildInherits(StringMap<SpecialNatClass> _standardsTypes){
        for (EntryCust<String, SpecialNatClass> s: _standardsTypes.entryList()) {
            buildInherits(s.getValue(), _standardsTypes);
        }
    }

    private static void buildInherits(SpecialNatClass _type, StringMap<SpecialNatClass> _standardsTypes) {
        feedSupers(_type, _type.getAllSuperTypes(), _standardsTypes);
    }

    private static void feedSupers(SpecialNatClass _type, StringList _types, StringMap<SpecialNatClass> _standardsTypes) {
        StringList currentSuperTypes_ = new StringList(_type.getSuperClass());
        _types.addAllElts(currentSuperTypes_);
        while (true) {
            StringList newSuperTypes_ = new StringList();
            for (String c: currentSuperTypes_) {
                SpecialNatClass st_ = _standardsTypes.getVal(c);
                if (st_ == null) {
                    continue;
                }
                String superClass_ = st_.getSuperClass();
                newSuperTypes_.add(superClass_);
                _types.add(superClass_);
            }
            if (newSuperTypes_.isEmpty()) {
                break;
            }
            currentSuperTypes_ = newSuperTypes_;
        }
    }

}
