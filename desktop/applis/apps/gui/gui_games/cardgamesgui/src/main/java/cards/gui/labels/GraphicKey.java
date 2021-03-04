package cards.gui.labels;
import java.awt.Color;
import java.awt.Font;

import cards.gui.MainWindow;
import cards.gui.dialogs.FileConst;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
public class GraphicKey extends PaintableLabel {
    private static final String GRAPHIC_KEY = "cards.gui.labels.graphickey";
    private static final String DEFAULT="Default";
    private static final String DELTA = "delta";
    private final CustList<Color> couleurs;
    private final StringList pseudos;
    private final StringMap<String> messages;
    public GraphicKey(StringList _ppseudos,CustList<Color> _pcouleurs, String _lg) {
        pseudos=_ppseudos;
        couleurs=_pcouleurs;
        messages = MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _lg, GRAPHIC_KEY);
    }
    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.setColor(Color.BLACK);
        _g.translate(getWidth()/2,0);
        _g.setFont(new Font(DEFAULT,Font.BOLD,10));
        for(String pseudo_:pseudos) {
            _g.translate(0,15);
            _g.drawString(pseudo_,0,0);
        }
        _g.translate(0,15);
        _g.drawString(messages.getVal(DELTA),0,0);
        _g.translate(-getWidth()/2,-15*(pseudos.size()+1));
        for(Color couleur_:couleurs) {
            _g.translate(0,15);
            _g.setColor(couleur_);
            _g.drawLine(getWidth()/8,0,3*getWidth()/8,0);
        }
        _g.translate(0,15);
        _g.setColor(Color.GRAY);
        _g.drawLine(getWidth()/8,0,3*getWidth()/8,0);
    }
}
