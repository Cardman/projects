package cards.gui.labels;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import code.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import cards.gui.dialogs.FileConst;
public class GraphicKey extends JLabel {
    private static final String GRAPHIC_KEY = "cards.gui.labels.GraphicKey";
    private static final String DEFAULT="Default";
    private static final String DELTA = "delta";
    private CustList<Color> couleurs;
    private StringList pseudos;
    private StringMap<String> messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), GRAPHIC_KEY);
    public GraphicKey(StringList _ppseudos,CustList<Color> _pcouleurs) {
        pseudos=_ppseudos;
        couleurs=_pcouleurs;
    }
    @Override
    protected void paintComponent(Graphics _g) {
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
