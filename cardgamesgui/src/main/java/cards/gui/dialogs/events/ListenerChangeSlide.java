package cards.gui.dialogs.events;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import code.util.StringList;
import code.util.StringMap;

public class ListenerChangeSlide implements ChangeListener {

//    private byte numero;
    private String key;
    private StringMap<String> messages;
//    private DialogSoft dialog;
    private JSlider slide;
    private JLabel etiquette;
    private String sentence;
    public ListenerChangeSlide(String _key, StringMap<String> _messages, String _sentence) {
//        numero=_pnumero;
        messages = _messages;
        key = _key;
//        dialog = _dialog;
        sentence = _sentence;
    }
    @Override
    public void stateChanged(ChangeEvent _e) {
//        JSlider slide_=(JSlider)((JPanel)dialog.getContentPane().getComponent(0)).getComponent(2*numero+1);
//        JLabel etiquette_=(JLabel)((JPanel)dialog.getContentPane().getComponent(0)).getComponent(2*numero);
        int min_=slide.getMinimum();
        int max_=slide.getMaximum();
        String prefix_ = messages.getVal(key);
        String values_ = StringList.simpleNumberFormat(sentence, min_, max_, slide.getValue());
        etiquette.setText(StringList.simpleStringsFormat(sentence, prefix_, values_));
    }

}
