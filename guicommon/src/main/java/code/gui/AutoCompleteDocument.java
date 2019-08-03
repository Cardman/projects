package code.gui;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import code.util.StringList;

public final class AutoCompleteDocument implements Document {

    //the content is instance of javax.swing.text.GapContent
    private static final int WIDTH_FIELD = 20;

    private final StringList dictionary = new StringList();

    private final TextField textField;

    private boolean wholeString = true;

    private PlainDocument doc = new PlainDocument();

    public AutoCompleteDocument(TextField _field, StringList _aDictionary) {
        textField = _field;
        dictionary.addAllElts(_aDictionary);
    }

    @Override
    public void addDocumentListener(DocumentListener _listener) {
        doc.addDocumentListener(_listener);
    }

    @Override
    public void removeDocumentListener(DocumentListener _listener) {
        doc.removeDocumentListener(_listener);
    }

    @Override
    public void addUndoableEditListener(UndoableEditListener _listener) {
        doc.addUndoableEditListener(_listener);
    }

    @Override
    public void removeUndoableEditListener(UndoableEditListener _listener) {
        doc.removeUndoableEditListener(_listener);
    }

    @Override
    public Object getProperty(Object _key) {
        return doc.getProperty(_key);
    }

    @Override
    public void putProperty(Object _key, Object _value) {
        doc.putProperty(_key, _value);
    }

    @Override
    public Position createPosition(int _offs) {
        try {
            return doc.createPosition(_offs);
        } catch (BadLocationException _0) {
            return null;
        }
    }

    @Override
    public Position getStartPosition() {
        return doc.getStartPosition();
    }

    @Override
    public Position getEndPosition() {
        return doc.getEndPosition();
    }

    @Override
    public Element getDefaultRootElement() {
        return doc.getDefaultRootElement();
    }

    public Element getParagraphElement(int _pos) {
        return doc.getParagraphElement(_pos);
    }

    public void setAsynchronousLoadPriority(int _p) {
        doc.setAsynchronousLoadPriority(_p);
    }

    public void setDocumentFilter(DocumentFilter _filter) {
        doc.setDocumentFilter(_filter);
    }

    public DocumentFilter getDocumentFilter() {
        return doc.getDocumentFilter();
    }

    @Override
    public void render(Runnable _r) {
        doc.render(_r);
    }

    @Override
    public int getLength() {
        return doc.getLength();
    }

    public DocumentListener[] getDocumentListeners() {
        return doc.getDocumentListeners();
    }

    @Override
    public void remove(int _offs, int _len) {
        try {
            doc.remove(_offs, _len);
        } catch (BadLocationException _0) {
        }
    }

    public void replace(int _offset, int _length, String _text,
            AttributeSet _attrs) {
        try {
            doc.replace(_offset, _length, _text, _attrs);
        } catch (BadLocationException _0) {
        }
    }

    @Override
    public String getText(int _offset, int _length) {
        try {
            return doc.getText(_offset, _length);
        } catch (BadLocationException _0) {
            return null;
        }
    }

    @Override
    public void getText(int _offset, int _length, Segment _txt) {
        try {
            doc.getText(_offset, _length, _txt);
        } catch (BadLocationException _0) {
        }
    }

    @Override
    public Element[] getRootElements() {
        return doc.getRootElements();
    }

    public Element getBidiRootElement() {
        return doc.getBidiRootElement();
    }

    public void addDictionaryEntry(String _item) {
        dictionary.add(_item);
    }

    @Override
    public void insertString(int _offs, String _str, AttributeSet _a) {
        try {
            doc.insertString(_offs, _str, _a);
            if (!wholeString) {
                return;
            }
            String word_ = autoComplete(getText(0, getLength()));
            if (word_ != null) {
                doc.insertString(_offs + _str.length(), word_, _a);
                textField.setCaretPosition(_offs + _str.length());
                textField.moveCaretPosition(getLength());
                // _textField.setCaretPosition(getLength());
                // _textField.moveCaretPosition(offs + str.length());
            }
        } catch (BadLocationException _0) {
        }
    }

    public String autoComplete(String _text) {
        for (String w_: dictionary) {
            if (w_.startsWith(_text)) {
                return w_.substring(_text.length());
            }
        }
        return null;
    }

    /**
    @param dictionary
    @return
    */
    public static TextField createAutoCompleteTextField(StringList _dictionary) {
        TextField field_ = new TextField(WIDTH_FIELD);

        AutoCompleteDocument doc_ = new AutoCompleteDocument(field_, _dictionary);
        field_.setDocument(doc_);
        return field_;
    }

    /**
    @param dictionary
    @return
    */
    public static TextField createAutoCompleteTextField(StringList _dictionary, int _cols) {
        TextField field_ = new TextField(_cols);

        AutoCompleteDocument doc_ = new AutoCompleteDocument(field_, _dictionary);
        field_.setDocument(doc_);
        return field_;
    }

    public static void setMode(TextField _field,boolean _wholeString) {
        AutoCompleteDocument doc_;
        doc_ = (AutoCompleteDocument) _field.getDocument();
        doc_.wholeString = _wholeString;
    }

    public static void setDictionary(TextField _field,StringList _dictionary) {
        AutoCompleteDocument doc_;
        doc_ = (AutoCompleteDocument) _field.getDocument();
        doc_.dictionary.clear();
        doc_.dictionary.addAllElts(_dictionary);
    }
}
