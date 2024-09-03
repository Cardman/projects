package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.opers.NatAbstractDotOperation;
import code.bean.nat.exec.opers.NatExecMethodOperation;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.exec.opers.NatSettableFieldOperation;
import code.sml.FormParts;
import code.sml.IndexesFormInput;
import code.sml.NavigationCore;
import code.sml.NodeInformations;
import code.sml.Document;
import code.sml.Element;
import code.util.*;
import code.util.core.StringUtil;

public abstract class NatRendElementForm extends NatParentBlock implements NatRendElem {
    private final Element read;
    private final StringMap<NatExecTextPart> natAttributes;
    private final StringMap<NatExecTextPart> natAttributesText;

    protected NatRendElementForm(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText) {
        this.read = _read;
        this.natAttributes = _execAttributes;
        this.natAttributesText = _execAttributesText;
    }

    public static NaSt fetchName(NatConfigurationCore _cont, Element _read, Element _write, NatFieldUpdates _f, NatRendStackCall _rendStackCall) {
//        if (_f.getOpsWrite() == null) {
//            return Argument.createVoid();
//        }
        CustList<NatExecOperationNode> opsRead_ = _f.getOpsRead();
        IdMap<NatExecOperationNode, NatArgumentsPair> args_ = BeanNatCommonLgNames.getAllArgs(opsRead_, _rendStackCall);
        NatExecOperationNode root_ = args_.lastKey();
//        if (root_ instanceof NatIdOperation) {
//            res_ = NatAbstractAffectOperation.getIdOp((RendMethodOperation) root_);
//        } else {
//            res_ = root_;
//        }
        NatExecOperationNode settable_ = castDottedTo(root_);
        CustList<LongTreeMap<NatNodeContainer>> stack_ = ((NatRendStackCallAdv)_rendStackCall).getFormParts().getContainersMapStack();
        NatArgumentsPair pair_ = args_.getValue(settable_.getOrder());
        NaSt obj_;
        if (((NatSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
            obj_ = pair_.getPreviousArgument();
        } else {
            obj_ = _rendStackCall.getLastPage().getGlobalArgument();
        }
//            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
        NaSt arg_ = pair_.getArgument();
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrName());
        prStack(_cont,_write,_f, new NatFetchedObjs(obj_, stack_), _rendStackCall, StringUtil.concat(_rendStackCall.getLastPage().getBeanName(), DOT, name_));
        return arg_;
    }

    public static void prStack(NatConfigurationCore _cont, Element _write, NatFieldUpdates _f, NatFetchedObjs _fetch, NatRendStackCall _rend, String _concat) {
        if ( _fetch.getStack().isEmpty()) {
            return;
        }
        long found_ = -1;
        if (_f.isRad()) {
            for (EntryCust<Long, NatNodeContainer> e: _fetch.getStack().last().entryList()) {
                if (e.getValue().getOpsWrite() == _f.getOpsWrite()) {
                    found_ = e.getKey();
                    break;
                }
            }
        }
        FormParts formParts_ = ((NatRendStackCallAdv)_rend).getFormParts();
        if (found_ == -1) {
            NatCaller opsWrite_ = _f.getOpsWrite();
            NatNodeContainer nodeCont_ = new NatNodeContainer();
            Longs inputs_ = formParts_.getInputs();
            long currentInput_ = inputs_.last();
            long old_ = currentInput_;
            nodeCont_.setAllObject(_fetch.getObj());
            nodeCont_.setOpsWrite(opsWrite_);
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            nodeInfos_.setValidator(_write.getAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getKeyWordsAttrs().getAttrValidator())));
            nodeInfos_.setId(NavigationCore.getId(_cont.getNat(),_write, _cont.getRendKeyWords()));
            nodeInfos_.setInputClass(NavigationCore.getInputClass(_cont.getNat(), _write, _f, _cont.getRendKeyWords()));
            _fetch.getStack().last().put(currentInput_, nodeCont_);
            currentInput_++;
            inputs_.set(inputs_.getLastIndex(),currentInput_);
            _write.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrNi(), Long.toString(old_));
        } else {
            _write.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrNi(), Long.toString(found_));
        }
//        attributesNames_.removeAllString(NUMBER_INPUT);
        _write.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrName(), _concat);
    }

    public static void fetchValue(NatConfigurationCore _cont, Element _read, Element _write, CustList<NatExecOperationNode> _ops, NatRendStackCall _rendStackCall) {
//        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
//        if (name_.isEmpty()) {
//            return;
//        }
//        if (_ops.isEmpty()) {
//            return;
//        }
        if (StringUtil.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordsTags().getKeyWordInput())) {
            NaSt o_ = BeanNatCommonLgNames.getAllArgs(_ops, _rendStackCall).lastValue().getArgument();
            if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrType()),_cont.getRendKeyWords().getKeyWordsValues().getValueCheckbox())) {
                if (NaBoSt.isTrue(o_)) {
                    _write.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrChecked(), _cont.getRendKeyWords().getKeyWordsAttrs().getAttrChecked());
                } else {
                    _write.removeAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrChecked());
                }
            } else {
                o_ = nullValueToEmpty(o_);
                String value_ = BeanNatCommonLgNames.processString(o_);
                _write.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrValue(), value_);
            }
        }
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getKeyWordsAttrs().getAttrVarValue()));
    }

    public static NaSt nullValueToEmpty(NaSt _o) {
        NaSt o_ = _o;
        if (o_ == NaNu.NULL_VALUE) {
            o_ = new NaStSt(RendBlockHelp.EMPTY_STRING);
        }
        return o_;
    }

    static String getStringKey(NaSt _instance) {
        return BeanNatCommonLgNames.processString(_instance);
    }

    public static void processLink(NatConfigurationCore _cont, Element _nextWrite, NatExecTextPart _textPart, NatRendStackCall _rendStackCall) {
//        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()));
//        if (!href_.startsWith(CALL_METHOD)) {
//            _rendStackCall.getFormParts().getAnchorsArgs().add(new StringList());
//            RendBlock.hideLink(_cont, _nextWrite);
//            incrAncNbNonCont(_cont, _nextWrite, _rendStackCall.getFormParts().getIndexes());
//            return;
//        }
        StringList alt_ = renderAltListNat(_textPart, _rendStackCall);
        StringList arg_ = arg(alt_);
        ((NatRendStackCallAdv)_rendStackCall).getFormParts().getAnchorsArgs().add(arg_);
//        String render_ = StringUtil.join(alt_,"");
//        String beanName_ = _rendStackCall.getLastPage().getBeanName();
//        _nextWrite.setAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_,DOT,render_));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrHref(), RendBlockHelp.EMPTY_STRING);
        incrAncNbNonCont(_cont, _nextWrite, ((NatRendStackCallAdv)_rendStackCall).getFormParts().getIndexes());
    }

    public static StringList arg(StringList _alt) {
        StringList arg_ = new StringList();
        int len_ = _alt.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(_alt.get(i));
        }
        return arg_;
    }

    public static void feed(StringList _varNames, CustList<NatExecOperationNode> _anc, NatRendStackCall _rendStackCall) {
        ((NatRendStackCallAdv)_rendStackCall).getFormParts().getCallsExps().add(_anc);
        ((NatRendStackCallAdv)_rendStackCall).getFormParts().getAnchorsVars().add(_varNames);
        ((NatRendStackCallAdv)_rendStackCall).getFormParts().getStructsAnc().add(_rendStackCall.getLastPage().getGlobalArgument());
    }

    public static void incrAncNbNonCont(NatConfigurationCore _cont, Element _nextEltWrite, IndexesFormInput _indexes) {
        _nextEltWrite.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrNa(), Long.toString(_indexes.getAnchor()));
        IndexesFormInput.incr(_indexes);
    }

    public static StringList renderAltListNat(NatExecTextPart _textPart, NatRendStackCall _rendStackCall) {
        CustList<CustList<NatExecOperationNode>> opExp_ = _textPart.getOpExp();
        StringList texts_ = _textPart.getTexts();
        int s_ = opExp_.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(texts_.get(i));
            CustList<NatExecOperationNode> exp_ = opExp_.get(i);
            String st_ = NatRenderingText.calculate(exp_, _rendStackCall);
            str_.add(st_);
        }
        str_.add(texts_.last());
        return str_;
    }

    public static NatExecOperationNode castDottedTo(NatExecOperationNode _root) {
        NatExecOperationNode elt_;
        if (!(_root instanceof NatAbstractDotOperation)) {
            elt_ = _root;
        } else {
            elt_ = ((NatExecMethodOperation) _root).getChildrenNodes().last();
        }
        return elt_;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        Element created_ = RendBlockHelp.appendChild(ownerDocument_, rw_, getRead());
        NatRendElement.attributes(_rendStack,created_,natAttributesText);
        if (this instanceof NatRendAnchor) {
            ((NatRendAnchor)this).anchor(_cont, created_, _rendStack);
        } else if (this instanceof NatRendForm) {
            ((NatRendForm)this).form(_cont, created_, _rendStack);
        } else if (this instanceof NatRendInput) {
            ((NatRendInput)this).input(_cont, created_, read, _rendStack);
//        } else if (this instanceof NatRendSpan) {
//            ((NatRendSpan)this).span(_cont, created_, _rendStack);
        } else if (this instanceof NatRendSubmit) {
            ((NatRendSubmit)this).submit(_cont, created_);
        } else if (this instanceof NatRendTitledAnchor) {
            ((NatRendTitledAnchor)this).titled(_cont, created_, _rendStack);
        } else {
            ownerDocument_.renameNode(created_, _cont.getRendKeyWords().getKeyWordsTags().getKeyWordSpan());
        }
        NatRendElement.attributes(_rendStack,created_,natAttributes);
        NatRendElement.addEltStack(ip_,rw_,created_,this);
    }


    public final Element getRead() {
        return read;
    }

}
