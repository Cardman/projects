package code.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleHypertext;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.html.HTMLEditorKit;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.util.BeanLgNames;
import code.gui.events.FindEvent;
import code.gui.events.HyperlinkClickEvent;
import code.gui.events.TitleEvent;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class SessionEditorPane extends EditorPane {

//    private static final String DEPRECATION = "deprecation";

    private static final String GT_TAG = ">";

    private static final String META_BEGIN = "<META ";

    private static final String ACTIVATED = "ACTIVATED";

    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
//    private static final String CONTENT_TYPE = "text/html;charset=UTF-16";

    private static final String EMPTY_STRING = "";

    private Navigation nav = new Navigation();

    private JLabel label;

    private JTextArea area;

    private ProgressingWebDialog dialog;

    private ChangeableTitle frame;

    private volatile boolean processing;

    private ThreadAnchorForm requestThread;

    private JTextField field;

    private Component searchText;

    private int index = CustList.INDEX_NOT_FOUND_ELT;

    private CustList<BufferedImage> process = new CustList<BufferedImage>();

    private final Color selectionColor = getSelectionColor();

    public SessionEditorPane() {
        setHtmlKit(new HTMLEditorKit());
        getHtmlKit().setAutoFormSubmission(false);
        setContentType(CONTENT_TYPE);
        setEditorKit(getHtmlKit());
        setEditable(false);
        addHyperlinkListener(new HyperlinkClickEvent(this));
    }

    public void setFiles(String _url) {
        setFiles(new StringMap<String>(), _url);
    }

    public void setFiles(StringMap<String> _web) {
        nav.setFiles(_web);
    }

    public void setFiles(StringMap<String> _web, String _url) {
        nav.setFiles(_web);
        nav.setResourcesFolder(_url);
    }

    public void setLanguage(String _language) {
        nav.setLanguage(_language);
    }

    public void setDataBase(Object _dataBase) {
        nav.setDataBase(_dataBase);
    }

    public void setProcess(CustList<BufferedImage> _process) {
        process = _process;
    }

    public void initialize(String _conf, JLabel _tooltip, BeanLgNames _lgNames) {
        initialize(_conf, _lgNames);
        setTooltip(_tooltip);
        addMouseMotionListener(new TitleEvent(this));
    }

    public void handleTitle(MouseEvent _e) {
        AccessibleContext context_ = (AccessibleContext) getAccessibleContext()
                .getAccessibleEditableText();
        AccessibleHypertext accText_ = (AccessibleHypertext) context_
                .getAccessibleText();
        int index_ = accText_.getIndexAtPoint(_e.getPoint());
        int linkIndex_ = accText_.getLinkIndex(index_);
        // Check if the mouse is over a link; otherwise
        // set the tool tip to null
        if (linkIndex_ == CustList.INDEX_NOT_FOUND_ELT) {
            getTooltip().setText(EMPTY_STRING);
            return;
        }
        getTooltip().setText(getTooltips().get(linkIndex_));
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public void initialize(String _conf, BeanLgNames _stds) {
        start();
        nav.loadConfiguration(_conf, _stds);
        nav.initializeSession();
        setupText(true);
        directScroll();
    }

    public void initializeOnlyConf(String _conf) {
        if (processing) {
            return;
        }
//        setCaretColor(Color.WHITE);
////        nav.loadConfiguration(_conf);
//        setHtmlKit(new HTMLEditorKit());
//        getHtmlKit().setAutoFormSubmission(false);
//        setContentType(CONTENT_TYPE);
//        setEditorKit(getHtmlKit());
//        //setupText();
//        setEditable(false);
//        if (getHyperlinkListeners().length == 0) {
//            addHyperlinkListener(new HyperlinkClickEvent(this));
//        }
////        directScroll();
//        repaint();
//        if (requestThread != null) {
//            if (requestThread.isAlive()) {
//                return;
//            }
//        }
//        requestThread = new ThreadAnchorForm(this, nav.getSession().getFirstUrl());
//        if (requestThread != null && requestThread.isAlive()) {
//            try {
//                requestThread.join();
//            } catch (Exception _0) {
//            }
//        }
//        if (requestThread != null && requestThread.isInterrupted()) {
//            try {
//                requestThread.join();
//            } catch (Exception _0) {
//            }
//        }
        requestThread = new ThreadAnchorForm(this, nav, _conf);
        requestThread.start();
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            load_.start();
        }
    }

    public void initializeHtml(String _conf) {
        start();
        nav.loadConfiguration(_conf);
        nav.initializeSession();
        setupText(true);
        finish(false);
    }
    public void initializeHtml(String _conf, BeanLgNames _lgNames) {
        start();
        nav.loadConfiguration(_conf, _lgNames);
        nav.initializeSession();
        setupText(true);
        finish(false);
    }

    public void hyperlinkUpdate(HyperlinkEvent _event) {
//        InputEvent i_ = _event.getInputEvent();
//        if (i_ instanceof MouseEvent) {
//            if (((MouseEvent)i_).getID() != MouseEvent.MOUSE_CLICKED) {
//                if (((MouseEvent)i_).getID() != MouseEvent.MOUSE_RELEASED) {
//                    return;
//                }
//            }
//        }
        Object i_ = _event.getEventType();
        if (!StringList.quickEq(i_.toString(),ACTIVATED)) {
            return;
        }
        if (processing) {
            return;
        }
//        if (requestThread != null && requestThread.isAlive()) {
//            try {
//                requestThread.join();
//            } catch (Exception _0) {
//            }
//        }
        requestThread = new ThreadAnchorForm(this, _event);
        requestThread.start();
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            load_.start();
        }
    }
//    public void loadFirstUrl() {
//        if (requestThread != null) {
//            if (requestThread.isAlive()) {
//                return;
//            }
//        }
//        requestThread = new ThreadAnchorForm(this, nav.getSession().getFirstUrl());
//        requestThread.start();
//        if (!process.isEmpty()) {
//            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
//            load_.start();
//        }
//    }

    public void initSession(String _conf, BeanLgNames _lgNames) {
        start();
        nav.loadConfiguration(_conf, _lgNames);
        nav.initializeSession();
//        setHtmlKit(new HTMLEditorKit());
//        getHtmlKit().setAutoFormSubmission(false);
//        setContentType(CONTENT_TYPE);
//        setEditorKit(getHtmlKit());
        setupText(true);
//        setTitle();
//        setEditable(false);
        directScroll();
    }

    public void refresh() {
        if (processing) {
            return;
        }
//        if (requestThread != null && requestThread.isAlive()) {
//            try {
//                requestThread.join();
//            } catch (Exception _0) {
//            }
//        }
        requestThread = new ThreadAnchorForm(this);
        requestThread.start();
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            load_.start();
        }
    }

    public void reset() {
        if (processing) {
            return;
        }
//        if (requestThread != null && requestThread.isAlive()) {
//            try {
//                requestThread.join();
//            } catch (Exception _0) {
//            }
//        }
        requestThread = new ThreadAnchorForm(this, nav.getSession().getFirstUrl());
        requestThread.start();
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            load_.start();
        }
    }

    public void reInitSession(String _conf, BeanLgNames _lgNames) {
        start();
        nav.loadConfiguration(_conf, _lgNames);
        nav.initializeSession();
        setupText(true);
//        setTitle();
        directScroll();
    }

    public void start() {
        processing = true;
        Highlighter h_ = getHighlighter();
        h_.removeAllHighlights();
//        setCaretColor(Color.WHITE);
        setSelectionColor(selectionColor);
    }

    public void interrupt() {
        nav.getSession().setInterrupt(true);
    }

    public void finish(boolean _wait) {
        //boolean _stop
        processing = false;
        if (!_wait) {
            return;
        }
        while (requestThread.isAlive()) {
            continue;
        }
//        requestThread.cancel(true);
//        while (!requestThread.isDone() && !requestThread.isCancelled()) {
//            continue;
//        }
//        if (_stop && requestThread != null && requestThread.isAlive()) {
//            requestThread.stop(new Exception());
//        }
    }

    void setupText(Timer _timer) {
        if (nav.getSession().isInterrupt()) {
            if (isProcessing() && requestThread != null) {
                requestThread.finish();
                return;
            }
        }
        if (!isProcessing()) {
            if (getFrame() != null) {
                //isVisible is thread safe because it is a getter
                if (!getFrame().isVisible()) {
                    if (_timer != null) {
                        _timer.stop();
                    }
                }
            }
            return;
        }
        getTooltips().clear();
        getTooltips().addAllElts(nav.getTooltips());
        String html_ = nav.getHtmlTextFormatted();
        if (!html_.contains(META_BEGIN)) {
            setText(html_);
        } else {
            int index_ = html_.indexOf(META_BEGIN);
            int until_ = html_.indexOf(GT_TAG, index_);
            StringBuilder str_ = new StringBuilder();
            str_.append(html_.substring(CustList.FIRST_INDEX, index_));
            str_.append(html_.substring(until_+1));
            setText(str_.toString());
        }
        if (_timer != null) {
            _timer.stop();
        }
        SwingUtilities.invokeLater(new SetTextPane(this));
    }

    void setupText(boolean _setText) {
        if (!_setText) {
            return;
        }
        if (!processing) {
            return;
        }
        getTooltips().clear();
        getTooltips().addAllElts(nav.getTooltips());
        String html_ = nav.getHtmlTextFormatted();
        if (!html_.contains(META_BEGIN)) {
            setText(html_);
        } else {
            int index_ = html_.indexOf(META_BEGIN);
            int until_ = html_.indexOf(GT_TAG, index_);
            StringBuilder str_ = new StringBuilder();
            str_.append(html_.substring(CustList.FIRST_INDEX, index_));
            str_.append(html_.substring(until_+1));
            setText(str_.toString());
        }
    }

//    void setTitle() {
//        if (frame != null && processing) {
//            if (!nav.getTitle().isEmpty()) {
//                frame.setTitle(nav.getTitle());
//            }
//        }
//    }

    void directScroll() {
        //boolean _stop
//        if (!processing) {
//            return;
//        }
        if (frame != null) {
            if (!nav.getTitle().isEmpty()) {
                frame.setTitle(nav.getTitle());
            }
        }
        String ref_ = nav.getReferenceScroll();
        if (!ref_.isEmpty()) {
            scrollToReference(ref_);
        } else {
            try {
                scrollRectToVisible(modelToView(CustList.FIRST_INDEX));
            } catch (BadLocationException _0) {
                _0.printStackTrace();
            }
        }
        if (frame != null) {
            frame.pack();
        }
        repaint();
        finish(false);
    }

    public void addFinder() {
        if (field == null) {
            return;
        }
        if (searchText == null) {
            return;
        }
        if (searchText.getMouseListeners().length == 0) {
            searchText.addMouseListener(new FindEvent(this));
        }
    }

    public void findNext() {
        if (processing) {
            return;
        }
        String text_ = field.getText();
        if (text_.isEmpty()) {
            Highlighter h_ = getHighlighter();
            h_.removeAllHighlights();
//            setSelectionColor(Color.WHITE);
            setSelectionColor(selectionColor);
            return;
        }
        try {
            Document doc_ = getDocument();
            int length_ = doc_.getLength();
            String content_ = doc_.getText(CustList.FIRST_INDEX, length_);
            int index_ = content_.indexOf(text_, index);
            Highlighter h_ = getHighlighter();
            if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
                //Very quick is the method setupText(): 106 ms
//                String html_ = nav.getHtmlText();
//                setText(html_);
                h_.removeAllHighlights();
//                setSelectionColor(Color.WHITE);
                setSelectionColor(selectionColor);
                index = index_;
                scrollRectToVisible(modelToView(CustList.FIRST_INDEX));
                return;
            }
            index = index_;
            index ++;
            scrollRectToVisible(modelToView(index_));
            setSelectionColor(Color.ORANGE);
            int end_ = index_ + text_.length();
            h_.addHighlight(index_, end_, DefaultHighlighter.DefaultPainter);
//            StyleContext sc_ = StyleContext.getDefaultStyleContext();
//            AttributeSet aset_ = sc_.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Background, Color.ORANGE);
//            setSelectionStart(index_);
//            setSelectionEnd(index_ + text_.length());
//            setCharacterAttributes(aset_, false);
        } catch (BadLocationException _0) {
            _0.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics _g) {
        super.paintComponent(_g);
        setSelectionColor(selectionColor);
    }

    public HtmlPage getHtmlPage() {
        return nav.getHtmlPage();
    }

    public boolean isProcessing() {
        return processing;
    }

    public ChangeableTitle getFrame() {
        return frame;
    }

    public void setFrame(ChangeableTitle _frame) {
        frame = _frame;
    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void setDialog(ProgressingWebDialog _dialog) {
        dialog = _dialog;
    }

    public JTextArea getArea() {
        return area;
    }

    public void setArea(JTextArea _area) {
        area = _area;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel _label) {
        label = _label;
    }

    public Component getSearchText() {
        return searchText;
    }

    public void setSearchText(Component _searchText) {
        searchText = _searchText;
    }

    public JTextField getField() {
        return field;
    }

    public void setField(JTextField _field) {
        field = _field;
    }

    Navigation getNav() {
        return nav;
    }

}
