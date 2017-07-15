package code.gui;
import java.util.Enumeration;

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

//    private static final String END_PATH = ":";

    private static final String ATTRIBUTE_COMMAND = "command";

    private static final String ATTRIBUTE_HREF = "href";

//    private static final String ATTRIBUTE_NAME = "name";
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

//    private String data;

//    private String formName;

    private String anchorRef;

    private Navigation navigation;

    private String fileName;

    private boolean usedUrl;

    private boolean refresh;

    private Timer timer;

//    private boolean ok = true;

//    private Throwable exception;

    /**This class thread is independant from EDT*/
    public ThreadAnchorForm(SessionEditorPane _session, HyperlinkEvent _event) {
        session = _session;
        session.start();
        event = _event;
        if (event instanceof FormSubmitEvent) {
//            data = ((FormSubmitEvent) event).getData();
            Element form_ = event
                    .getSourceElement();

//            formName = EMPTY_STRING;

            AttributeSet as_ = form_.getAttributes();
            Enumeration<?> enumm_;
            // = as_.getAttributeNames();

//            while (enumm_.hasMoreElements()) {
//                Object name_ = enumm_.nextElement();
//                Object value_ = as_.getAttribute(name_);
////                if (name_ instanceof Attribute)
//                if (value_ instanceof String) {
//                    if (StringList.eq(name_.toString(),ATTRIBUTE_NAME)) {
//                        formName = value_.toString();
//                        break;
//                    }
//                }
//            }
            enumm_ = as_.getAttributeNames();
            String nbForm_ = EMPTY_STRING;

            while (enumm_.hasMoreElements()) {
                Object name_ = enumm_.nextElement();
                Object value_ = as_.getAttribute(name_);
//                if (name_ instanceof Attribute)
                if (value_ instanceof String) {
                    if (StringList.quickEq(name_.toString(),NUMBER_FORM)) {
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
//                    nCont_.getNodeInformation().setValue(new StringList(htmlPage_.getValues(Long.parseLong(nbForm_)+1, nbId_).get(index_)));
                    nCont_.getNodeInformation().setValue(new StringList(htmlPage_.getSelects().getVal(fi_).get(index_)));
                    continue;
                }
                int s_ = ((DefaultListModel) model_).size();
//                StringList allvalues_ = htmlPage_.getValues(Long.parseLong(nbForm_)+1, nbId_);
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
            Enumeration<?> enumm_ = as_.getAttributeNames();
            //NUMBER_ANCHOR
            long na_ = CustList.INDEX_NOT_FOUND_ELT;

            while (enumm_.hasMoreElements()) {
                Object name_ = enumm_.nextElement();
                Object value_ = as_.getAttribute(name_);
//                if (name_ instanceof Tag)
                if (value_ instanceof SimpleAttributeSet) {
                    Enumeration<?> enumAttr_;
                    enumAttr_ = ((SimpleAttributeSet) value_)
                            .getAttributeNames();
                    while (enumAttr_.hasMoreElements()) {
                        Object nameAttr_ = enumAttr_.nextElement();
                        if (StringList.quickEq(nameAttr_.toString(),ATTRIBUTE_COMMAND)) {
                            anchorRef = ((SimpleAttributeSet) value_)
                                    .getAttribute(nameAttr_)
                                    .toString();
                            break;
                        }
                    }
                    if (anchorRef.isEmpty()) {
                        enumAttr_ = ((SimpleAttributeSet) value_)
                                .getAttributeNames();
                        while (enumAttr_.hasMoreElements()) {
                            Object nameAttr_ = enumAttr_
                                    .nextElement();
                            if (StringList.quickEq(nameAttr_.toString(),
                                    ATTRIBUTE_HREF)) {
                                anchorRef = ((SimpleAttributeSet) value_)
                                        .getAttribute(nameAttr_)
                                        .toString();
                                break;
                            }
                        }
                    }
                    enumAttr_ = ((SimpleAttributeSet) value_)
                            .getAttributeNames();
                    while (enumAttr_.hasMoreElements()) {
                        Object nameAttr_ = enumAttr_.nextElement();
                        if (StringList.quickEq(nameAttr_.toString(),NUMBER_ANCHOR)) {
                            na_ = Long.parseLong(((SimpleAttributeSet) value_)
                                    .getAttribute(nameAttr_)
                                    .toString());
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
        Enumeration<?> enumm_ = as_.getAttributeNames();

        while (enumm_.hasMoreElements()) {
            Object name_ = enumm_.nextElement();
            Object value_ = as_.getAttribute(name_);
            if (value_ instanceof String) {
                if (StringList.quickEq(name_.toString(),NUMBER_INPUT)) {
                    return Long.parseLong(value_.toString());
                }
            }
        }
        return -1;
    }

    private static Object getModel(Element _elt) {
        AttributeSet as_ = _elt.getAttributes();
        Enumeration<?> enumm_ = as_.getAttributeNames();

        while (enumm_.hasMoreElements()) {
            Object name_ = enumm_.nextElement();
            Object value_ = as_.getAttribute(name_);
            if (StringList.quickEq(name_.toString(), MODEL)) {
                return value_;
            }
        }
        return null;
    }

    private static Object getValue(Element _elt) {
        AttributeSet as_ = _elt.getAttributes();
        Enumeration<?> enumm_ = as_.getAttributeNames();

        while (enumm_.hasMoreElements()) {
            Object name_ = enumm_.nextElement();
            Object value_ = as_.getAttribute(name_);
            if (StringList.quickEq(name_.toString(), ATTRIBUTE_VALUE)) {
                return value_;
            }
        }
        return EMPTY_STRING;
    }

//    private static int getIndex(Element _elt) {
//        Element par_ = _elt.getParentElement();
//        int nbElts_ = par_.getElementCount();
//        for (int i = 0; i < nbElts_; i++) {
//            if (par_.getElement(i) == _elt) {
//                return i;
//            }
//        }
//        return -1;
//    }
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
//      ok = false;
        if (session.getArea() != null) {
//          exception = _0;
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
//      SwingUtilities.invokeLater(new SetTextPane(session, false));
//      session.directScroll();
//      session.repaint();
//      session.finish();
    }
//    @Override
//    protected void done() {
//        try {
//            get();
//            if (ok) {
//                session.setupText(timer);
//            } else if (exception != null) {
//                session.getArea().append(TAB + exception.getMessage() + RETURN_LINE);
//                for (StackTraceElement s : exception.getStackTrace()) {
//                    session.getArea().append(
//                            TAB + s.getFileName() + TAB
//                                    + s.getClassName() + TAB
//                                    + s.getClassName() + TAB
//                                    + s.getMethodName() + TAB
//                                    + s.getLineNumber() + RETURN_LINE);
//                }
//            }
//        } catch (Exception _0) {
//        }
//    }
//
//    public boolean isAlive() {
//        return !isDone() && !isCancelled();
//    }
}
