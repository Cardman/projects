package code.gui;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import code.util.StringList;
import code.util.pagination.SearchingMode;

public final class AutoCompleteDocument extends PlainDocument {

    //the content is instance of javax.swing.text.GapContent
    private static final int WIDTH_FIELD = 20;

    private final StringList dictionary = new StringList();

    private final JTextComponent textField;

    private SearchingMode mode = SearchingMode.WHOLE_STRING;

    public AutoCompleteDocument(JTextComponent _field, StringList _aDictionary) {
        textField = _field;
        dictionary.addAllElts(_aDictionary);
    }

    public void addDictionaryEntry(String _item) {
        dictionary.add(_item);
    }

    @Override
    public void insertString(int _offs, String _str, AttributeSet _a) {
        try {
            super.insertString(_offs, _str, _a);
            if (mode != SearchingMode.WHOLE_STRING) {
                return;
            }
            String word_ = autoComplete(getText(0, getLength()));
            if (word_ != null) {
                super.insertString(_offs + _str.length(), word_, _a);
                textField.setCaretPosition(_offs + _str.length());
                textField.moveCaretPosition(getLength());
                // _textField.setCaretPosition(getLength());
                // _textField.moveCaretPosition(offs + str.length());
            }
        } catch (BadLocationException _0) {
            _0.printStackTrace();
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
    public static JTextField createAutoCompleteTextField(StringList _dictionary) {
        JTextField field_ = new JTextField(WIDTH_FIELD);

        AutoCompleteDocument doc_ = new AutoCompleteDocument(field_, _dictionary);
        field_.setDocument(doc_);
        return field_;
    }

    /**
    @param dictionary
    @return
    */
    public static JTextField createAutoCompleteTextField(StringList _dictionary, int _cols) {
        JTextField field_ = new JTextField(_cols);

        AutoCompleteDocument doc_ = new AutoCompleteDocument(field_, _dictionary);
        field_.setDocument(doc_);
        return field_;
    }

    public static void setMode(JTextField _field,SearchingMode _mode) {
        AutoCompleteDocument doc_;
        doc_ = (AutoCompleteDocument) _field.getDocument();
        doc_.mode = _mode;
    }
}
