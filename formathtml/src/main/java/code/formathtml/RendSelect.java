package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BadElRender;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.FormInputCoords;
import code.sml.*;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;

public final class RendSelect extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsMap = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsDefault = new CustList<RendDynOperationNode>();
    private String varName = EMPTY_STRING;
    private ClassField idField;
    private Element elt;
    private boolean multiple;
    RendSelect(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, _doc,elt,ATTRIBUTE_VAR_VALUE);
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        idField = r_.getIdField();
        boolean st_ = _doc.isStaticContext();
        multiple = elt.hasAttribute(ATTRIBUTE_MULTIPLE);
        String map_ = elt.getAttribute(ATTRIBUTE_MAP);
        opsMap = ElRenderUtil.getAnalyzedOperations(map_, 0, _cont, Calculation.staticCalculation(st_));
        if (multiple) {
            StringList names_ = opsValue.last().getResultClass().getNames();
            if (!opsValue.last().getResultClass().isVariable()) {
                IterableAnalysisResult it_ = _cont.getStandards().getCustomType(names_, _cont.getContext());
                StringList candidates_ = it_.getClassName();
                if (candidates_.size() != 1) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            }
        }
        String default_ = elt.getAttribute(DEFAULT_ATTRIBUTE);
        if (!default_.isEmpty()) {
            String mName_ = elt.getAttribute(ATTRIBUTE_CONVERT);
            if (mName_.trim().isEmpty()) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
            String concat_ = StringList.concat(mName_,"(\"",default_,"\")");
            opsDefault = ElRenderUtil.getAnalyzedOperations(concat_,0,_cont,Calculation.staticCalculation(st_));
            Mapping m_ = new Mapping();
            m_.setArg(opsDefault.last().getResultClass());
            if (!multiple) {
                m_.setParam(_cont.getStandards().getAliasCharSequence());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            } else {
                IterableAnalysisResult it_ = _cont.getStandards().getCustomType(opsDefault.last().getResultClass().getNames(), _cont.getContext());
                StringList candidates_ = it_.getClassName();
                if (candidates_.size() != 1) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            }
        }
    }

    @Override
    public void reduce(Configuration _context) {
        opsRead = reduceList(opsRead);
        opsValue = reduceList(opsValue);
        opsWrite = reduceList(opsWrite);
        opsMap = reduceList(opsMap);
        opsDefault = reduceList(opsDefault);
    }

    @Override
    public void processEl(Configuration _cont) {
        Argument value_ = ElRenderUtil.calculateReuse(opsValue, _cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        Argument map_ = ElRenderUtil.calculateReuse(opsMap, _cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        RendReadWrite rw_ = _cont.getLastPage().getRendReadWrite();
        Element write_ = (Element) rw_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Element docElementSelect_ = doc_.createElement(SELECT_TAG);
        if (multiple) {
            docElementSelect_.setAttribute(ATTRIBUTE_MULTIPLE, ATTRIBUTE_MULTIPLE);
        }
        String name_ = elt.getAttribute(ATTRIBUTE_NAME);
        String default_ = elt.getAttribute(DEFAULT_ATTRIBUTE);
        if (default_.isEmpty()) {
            processOptionsMapEnumName(_cont, map_.getStruct(),
                    doc_, docElementSelect_,
                    value_.getStruct());
        } else {
            processOptionsMapEnum(_cont, map_.getStruct(),
                    doc_, docElementSelect_);
        }

        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
//        docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME), ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
        write_.appendChild(docElementSelect_);
        if (!name_.isEmpty()) {
            processIndexes(_cont,elt,docElementSelect_);
            FormInputCoords inputs_ = new FormInputCoords();
            inputs_.setForm(_cont.getCurrentForm() - 1);
            inputs_.setInput(_cont.getIndexes().getNb());
            StringList allOptions_ = new StringList();
            ElementList elts_ = docElementSelect_.getElementsByTagName(TAG_OPTION);
            int nbElts_ = elts_.getLength();
            for (int i = 0; i < nbElts_; i++) {
                Element opt_ = elts_.item(i);
                allOptions_.add(opt_.getAttribute(ATTRIBUTE_VALUE));
            }
            _cont.getHtmlPage().getSelects().put(inputs_, allOptions_);
        }
        processBlock(_cont);
    }

    private void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
                                       Document _docSelect, Element _docElementSelect) {
        Argument argDef_ = ElRenderUtil.calculateReuse(opsDefault, _conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        CustList<Struct> obj_ = values(_conf,argDef_.getStruct());
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        Argument arg_ = iteratorMultTable(_extractedMap, _conf);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Document _docSelect, Element _docElementSelect, Struct _returnedVarValue) {
        CustList<Struct> obj_ = values(_conf,_returnedVarValue);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Argument arg_ = iteratorMultTable(_extractedMap,_conf);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        while (true) {
            Argument hasNext_ = hasNextPair(_l, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (!((BooleanStruct)hasNext_.getStruct()).getInstance()) {
                break;
            }
            Argument nextPair_ = nextPair(_l, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct entry_ = nextPair_.getStruct();
            Argument first_ = first(entry_, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct o_ = first_.getStruct();
            if (o_ == NullStruct.NULL_VALUE) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            option_.setAttribute(ATTRIBUTE_VALUE, getStringKey(_conf, o_));
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(SELECTED, SELECTED);
                    break;
                }
            }
            Argument second_ = second(entry_, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            option_.appendChild(_docSelect.createTextNode(stds_.processString(second_,_conf)));
            _docElementSelect.appendChild(option_);
        }
    }

    CustList<Struct> values(Configuration _conf,Struct _returnedVarValue) {
        IdList<Struct> obj_ = new IdList<Struct>();
        if (multiple) {
            Argument arg_ = iterator(_returnedVarValue,_conf);
            if (_conf.getContext().getException() != null) {
                return obj_;
            }
            Struct it_ = arg_.getStruct();
            while (true) {
                Argument hasNext_ = hasNext(it_, _conf);
                if (_conf.getContext().getException() != null) {
                    return obj_;
                }
                if (!((BooleanStruct)hasNext_.getStruct()).getInstance()) {
                    break;
                }
                Argument next_ = next(it_, _conf);
                if (_conf.getContext().getException() != null) {
                    return obj_;
                }
                obj_.add(next_.getStruct());
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        return obj_;
    }
    Argument processIndexes(Configuration _cont, Element _read, Element _write) {
        Argument arg_ = fetchName(_cont, _read, _write, opsRead, idField, varName, opsWrite);
        fetchValue(_cont,_read,_write,opsValue);
        return arg_;
    }
}
