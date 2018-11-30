package cards.gui.labels;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import cards.gui.dialogs.FileConst;
import code.gui.PaintableLabel;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
public class GraphicKey extends PaintableLabel {
    private static final String GRAPHIC_KEY = "cards.gui.labels.graphickey";
    private static final String DEFAULT="Default";
    private static final String DELTA = "delta";
    private CustList<Color> couleurs;
    private StringList pseudos;
    private StringMap<String> messages;
    public GraphicKey(StringList _ppseudos,CustList<Color> _pcouleurs, String _lg) {
        pseudos=_ppseudos;
        couleurs=_pcouleurs;
        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _lg, GRAPHIC_KEY);
    }
    @Override
    public void paintComponent(Graphics _g) {
        Graphics2D g2_=(Graphics2D)_g;
        g2_.setColor(Color.WHITE);
        g2_.fillRect(0,0,getWidth(),getHeight());
        g2_.setColor(Color.BLACK);
        g2_.translate(getWidth()/2,0);
        g2_.setFont(new Font(DEFAULT,Font.BOLD,10));
        for(String pseudo_:pseudos) {
            g2_.translate(0,15);
            g2_.drawString(pseudo_,0,0);
        }
        g2_.translate(0,15);
        g2_.drawString(messages.getVal(DELTA),0,0);
        g2_.translate(-getWidth()/2,-15*(pseudos.size()+1));
        for(Color couleur_:couleurs) {
            g2_.translate(0,15);
            g2_.setColor(couleur_);
            g2_.drawLine(getWidth()/8,0,3*getWidth()/8,0);
        }
        g2_.translate(0,15);
        g2_.setColor(Color.GRAY);
        g2_.drawLine(getWidth()/8,0,3*getWidth()/8,0);
    }
}
