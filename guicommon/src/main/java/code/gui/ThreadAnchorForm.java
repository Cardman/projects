package code.gui;
import java.util.Collections;

import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.FormSubmitEvent;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class ThreadAnchorForm extends Thread {

    private static final String ATTRIBUTE_VALUE = "value";

    private static final String MODEL = "model";

    private static final String RETURN_LINE = "\n";

    private static final String TAB = "\t";

    private static final String ATTRIBUTE_COMMAND = "command";

    private static final String ATTRIBUTE_HREF = "href";

    private static final String NUMBER_FORM = "n-f";
    private static final String NUMBER_INPUT = "n-i";
    private static final String NUMBER_ANCHOR = "n-a";
    private static final String CHECKBOX = "checkbox";
    private static final String OFF = "off";
    private static final String ON = "on";
    private static final String EMPTY_STRING = "";

    private static final int DELTA = 200;

    private SessionEditorPane session;

    private HyperlinkEvent event;

    private String anchorRef;

    private Navigation navigation;

    private String fileName;

    private boolean usedUrl;

    private boolean refresh;

    private Timer timer;

    /**This class thread is independant from EDT*/
    public ThreadAnchorForm(SessionEditorPane _session, HyperlinkEvent _event) {
        session = _session;
        session.start();
        event = _event;
        if (event instanceof FormSubmitEvent) {
            Element form_ = event
                    .getSourceElement();

            AttributeSet as_ = form_.getAttributes();
            String nbForm_ = EMPTY_STRING;
            for (Object e: Collections.list(as_.getAttributeNames())) {
                Object value_ = as_.getAttribute(e);
                if (value_ instanceof String) {
                    if (StringList.quickEq(e.toString(),NUMBER_FORM)) {
                        nbForm_ = value_.toString();
                        break;
                    }
                }
            }
            HtmlPage htmlPage_ = session.getHtmlPage();
            htmlPage_.setForm(true);
            htmlPage_.setUrl(Long.parseLong(nbForm_));
            NatTreeMap<Long,NodeContainer> inputsMap_;
            inputsMap_ = htmlPage_.getContainers().getVal(Long.parseLong(nbForm_));
            CustList<Element> inputs_ = new CustList<Element>();
            CustList<Element> current_ = new CustList<Element>();
            current_.add(form_);
            while (true) {
                CustList<Element> next_ = new CustList<Element>();
                for (Element c: current_) {
                    int nbElts_ = c.getElementCount();
                    for (int i = 0; i < nbElts_; i++) {
                        Element elt_ = c.getElement(i);
                        next_.add(elt_);
                        long idInput_ = getNumberInput(elt_);
                        if (idInput_ < 0) {
                            continue;
                        }
                        inputs_.add(elt_);
                    }
                }
                if (next_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            for (Element c: inputs_) {
                Element child_ = c;
                long nbId_ = getNumberInput(child_);
                Object model_ = getModel(child_);
                if (model_ == null) {
                    continue;
                }
                NodeContainer nCont_ = inputsMap_.getVal(nbId_);
                if (model_ instanceof PlainDocument) {
                    nCont_.setEnabled(true);
                    try {
                        PlainDocument pl_ = (PlainDocument) model_;
                        String value_ = pl_.getText(0, pl_.getLength());
                        nCont_.getNodeInformation().setValue(new StringList(value_));
                    } catch (BadLocationException _0) {
                    } catch (RuntimeException _0) {
                    }
                    continue;
                }
                if (model_ instanceof DefaultButtonModel) {
                    if (StringList.quickEq(nCont_.getNodeInformation().getType(), CHECKBOX)) {
                        nCont_.setEnabled(true);
                        if (((DefaultButtonModel)model_).isSelected()) {
                            nCont_.getNodeInformation().setValue(new StringList(ON));
                        } else {
                            nCont_.getNodeInformation().setValue(new StringList(OFF));
                        }
                    } else {
                        if (((DefaultButtonModel)model_).isSelected()) {
                            nCont_.getNodeInformation().setValue(new StringList(getValue(child_).toString()));
                            nCont_.setEnabled(true);
                        } else if (nCont_.getNodeInformation().getValue().isEmpty()) {
                            nCont_.getNodeInformation().setValue(new StringList(EMPTY_STRING));
                            nCont_.setEnabled(false);
                        }
                    }
                    continue;
                }
                nCont_.setEnabled(true);
                FormInputCoords fi_ = new FormInputCoords();
                fi_.setForm(Long.parseLong(nbForm_));
                fi_.setInput(nbId_);
                if (model_ instanceof DefaultComboBoxModel) {
                    Object it_ = ((DefaultComboBoxModel)model_).getSelectedItem();
                    int index_ = ((DefaultComboBoxModel)model_).getIndexOf(it_);
                    nCont_.getNodeInformation().setValue(new StringList(htmlPage_.getSelects().getVal(fi_).get(index_)));
                    continue;
                }
                int s_ = ((DefaultListModel) model_).size();
                StringList allvalues_ = htmlPage_.getSelects().getVal(fi_);
                StringList values_ = new StringList();
                for (int k = 0; k < s_; k++) {
                    if (((ListSelectionModel)model_).isSelectedIndex(k)) {
                        values_.add(allvalues_.get(k));
                    }
                }
                nCont_.getNodeInformation().setValue(values_);
            }
            // en:end submit event
            // fr:evenement de soumission
        } else {
            Element anchor_ = event.getSourceElement();

            anchorRef = EMPTY_STRING;

            AttributeSet as_ = anchor_.getAttributes();
            long na_ = CustList.INDEX_NOT_FOUND_ELT;
            for (Object f: Collections.list(as_.getAttributeNames())) {
                Object value_ = as_.getAttribute(f);
                if (!(value_ instanceof SimpleAttributeSet)) {
                    continue;
                }
                SimpleAttributeSet asLoc_ = (SimpleAttributeSet) value_;

                for (Object e: Collections.list(asLoc_.getAttributeNames())) {
                    if (StringList.quickEq(e.toString(),ATTRIBUTE_COMMAND)) {
                        anchorRef = asLoc_.getAttribute(e).toString();
                        break;
                    }
                }
                if (anchorRef.isEmpty()) {
                    for (Object e: Collections.list(asLoc_.getAttributeNames())) {
                        if (StringList.quickEq(e.toString(),
                                ATTRIBUTE_HREF)) {
                            anchorRef = asLoc_.getAttribute(e).toString();
                            break;
                        }
                    }
                }
                for (Object e: Collections.list(asLoc_.getAttributeNames())) {
                    if (StringList.quickEq(e.toString(),NUMBER_ANCHOR)) {
                        na_ = Long.parseLong(asLoc_.getAttribute(e).toString());
                        break;
                    }
                }
                if (!anchorRef.isEmpty()) {
                    HtmlPage htmlPage_ = session.getHtmlPage();
                    htmlPage_.setForm(false);
                    htmlPage_.setUrl(na_);
                    break;
                }
            }
        }
        initTimer();
    }

    /**This class thread is independant from EDT*/
    public ThreadAnchorForm(SessionEditorPane _session) {
        session = _session;
        session.start();
        refresh = true;
        initTimer();
    }

    /**This class thread is independant from EDT*/
    public ThreadAnchorForm(SessionEditorPane _session, String _anchorRef) {
        session = _session;
        session.start();
        usedUrl = true;
        anchorRef = _anchorRef;
        initTimer();
    }

    public ThreadAnchorForm(SessionEditorPane _session, Navigation _navigation, String _fileName) {
        session = _session;
        session.start();
        usedUrl = true;
        navigation = _navigation;
        fileName = _fileName;
        initTimer();
    }

    private static long getNumberInput(Element _elt) {
        AttributeSet as_ = _elt.getAttributes();
        for (Object e: Collections.list(as_.getAttributeNames())) {
            Object value_ = as_.getAttribute(e);
            if (StringList.quickEq(e.toString(),NUMBER_INPUT)) {
                return Long.parseLong(value_.toString());
            }
        }
        Object object_ = getAttribute(_elt, MODEL);
        if (object_ == null) {
            return -1;
        }
        return Long.parseLong(object_.toString());
    }

    private static Object getModel(Element _elt) {
        return getAttribute(_elt, MODEL);
    }

    private static Object getValue(Element _elt) {
        Object value_ = getAttribute(_elt, ATTRIBUTE_VALUE);
        if (value_ == null) {
            return EMPTY_STRING;
        }
        return value_;
    }

    private static Object getAttribute(Element _elt, String _attribute) {
        AttributeSet as_ = _elt.getAttributes();
        for (Object e: Collections.list(as_.getAttributeNames())) {
            Object value_ = as_.getAttribute(e);
            if (StringList.quickEq(e.toString(), _attribute)) {
                return value_;
            }
        }
        return null;
    }

    private void initTimer() {
        if (session.getLabel() != null) {
            timer = new Timer(DELTA, new Chronometer(session.getLabel(), session, 0));
            timer.start();
        }
    }

    @Override
    public void run() {
        try {
            if (refresh) {
                session.getNav().refresh();
                session.setupText(timer);
                return;
            }
            if (usedUrl) {
                navigation.loadConfiguration(fileName);
                HtmlPage htmlPage_ = session.getHtmlPage();
                htmlPage_.setUrl(-1);
                session.getNav().initializeSession();
                session.setupText(timer);
                return;
            }
            if (event instanceof FormSubmitEvent) {
                session.getNav().processFormRequest();
                session.setupText(timer);
                return;
            }
            session.getNav().processAnchorRequest(anchorRef);
            session.setupText(timer);
            return;
        } catch (RuntimeException _0) {
            processErrors(_0);
        } catch (Error _0) {
            processErrors(_0);
        }
    }

    private void processErrors(Throwable _t) {
        if (session.getArea() != null) {
            session.getArea().append(TAB + _t.getMessage() + RETURN_LINE);
            for (StackTraceElement s : _t.getStackTrace()) {
                session.getArea().append(
                      TAB + s.getFileName() + TAB
                              + s.getClassName() + TAB
                              + s.getClassName() + TAB
                              + s.getMethodName() + TAB
                              + s.getLineNumber() + RETURN_LINE);
            }
        }
        _t.printStackTrace();
        if (timer != null) {
            timer.stop();
        }
        session.finish(false);
    }
}
