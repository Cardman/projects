package cards.gui.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;

import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.FileConst;
import cards.president.enumerations.CardPresident;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.gui.images.ConverterGraphicBufferedImage;
import code.images.BaseSixtyFourUtil;
import code.scripts.pages.cards.CardsInit;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public class GraphicPresidentCard extends PaintableLabel {
    static final String DEFAULT="Default";

    private CardPresident card;
    private final boolean fullCard;
    private boolean peindreCarte=true;
    private BufferedImage bufferedImage;
    private String lg;

    public GraphicPresidentCard(String _lg, CardPresident _pc, int _i, boolean _fullCard) {
        this(_lg, _i,_fullCard);
        peindreCarte=true;
        card=_pc;
        int[][] file_ = BaseSixtyFourUtil.getImageByString(CardsInit.ms().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
//                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(file_);
    }

    public GraphicPresidentCard(String _lg, int _i, boolean _fullCard) {
        lg = _lg;
        peindreCarte=false;
        setHorizontalAlignment(_i);
        setVerticalAlignment(SwingConstants.TOP);
        fullCard=_fullCard;
    }

    public void setPreferredSize(boolean _small) {
        setPreferredSize(getDimension(_small));
    }
    public static Dimension getMaxDimension() {
        return getDimension(false);
    }
    public static Dimension getMinDimension() {
        return getDimension(true);
    }
    public static Dimension getDimension(boolean _small) {
        if (_small) {
            return new Dimension(25,150);
        }
        return new Dimension(100,150);
    }
    public static Dimension getDimensionForSeveralCards(int _number) {
        return new Dimension(100 + 25 * (_number - 1), 150);
    }
    void setCarte(String _lg, CardPresident _pc) {
        card=_pc;
        lg=_lg;
        peindreCarte=true;
        int[][] file_ = BaseSixtyFourUtil.getImageByString(CardsInit.ms().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
//                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(file_);
    }

    public CardPresident getCard() {
        return card;
    }

    public void setCarteEnJeu(String _lg, CardPresident _carte) {
        peindreCarte=true;
        lg = _lg;
        setCarte(_lg, _carte);
    }
    public void setJeu(String _lg) {
        lg = _lg;
        peindreCarte=false;
    }

    boolean coupeHorizontal() {
        return fullCard;
    }

    /**Methode importante dessinant les cartes des jeux de cartes face non cachee sauf pour certaines cartes du solitaire*/
    @Override
    public void paintComponent(CustGraphics _g2) {
        if(!peindreCarte) {
            _g2.setColor(Color.RED);
            _g2.fillRect(0,0,getWidth()-1,getHeight()-1);
            _g2.setColor(Color.BLACK);
            _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
            _g2.setColor(Color.BLUE);
            _g2.setFont(new Font(DEFAULT,Font.BOLD,20));
            _g2.drawString(GameEnum.PRESIDENT.toString(lg),20,80);
            return;
        }
        _g2.setColor(Color.WHITE);
        _g2.fillRect(0,0,getWidth(),getHeight());
        if(fullCard) {
            //whole card
            _g2.drawImage(bufferedImage, 0, 0);
        } else {
            _g2.drawImage(bufferedImage, -75, 0);
        }
        _g2.setColor(Color.BLACK);
        _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
}
