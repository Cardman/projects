package cards.gui.dialogs.events;
import javax.swing.JLabel;

import code.gui.Slider;
import code.gui.events.AbsChangeListener;
import code.util.StringMap;
import code.util.core.StringUtil;

public class ListenerChangeSlide implements AbsChangeListener {

//    private byte numero;
    private String key;
    private StringMap<String> messages;
//    private DialogSoft dialog;
    private Slider slide;
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
    public void stateChanged() {
//        JSlider slide_=(JSlider)((JPanel)dialog.getContentPane().getComponent(0)).getComponent(2*numero+1);
//        JLabel etiquette_=(JLabel)((JPanel)dialog.getContentPane().getComponent(0)).getComponent(2*numero);
        int min_=slide.getMinimum();
        int max_=slide.getMaximum();
        String prefix_ = messages.getVal(key);
        String values_ = StringUtil.simpleNumberFormat(sentence, min_, max_, slide.getValue());
        etiquette.setText(StringUtil.simpleStringsFormat(sentence, prefix_, values_));
    }

}
