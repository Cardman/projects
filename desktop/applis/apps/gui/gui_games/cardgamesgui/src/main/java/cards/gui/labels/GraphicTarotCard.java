package cards.gui.labels;






import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.FileConst;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.images.BaseSixtyFourUtil;
import code.scripts.imgs.cards.CardsInit;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public final class GraphicTarotCard extends AbsMetaLabel {
    static final String DEFAULT="Default";

    private CardTarot card;
    private final boolean fullCard;
    private boolean peinte;
    private boolean peindreCarte=true;
    private AbstractImage bufferedImage;
    private String lg;

    public GraphicTarotCard(AbstractImageFactory _fact, String _lg, CardTarot _pc, int _i, boolean _fullCard, AbsCompoFactory _compoFactory) {
        this(_lg,_i,_fullCard, _compoFactory);
        peindreCarte=true;
        card=_pc;
        int[][] file_ = BaseSixtyFourUtil.getImageByString(CardsInit.ms().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
//                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
    }

    public GraphicTarotCard(String _lg, int _i, boolean _fullCard, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        lg = _lg;
        peindreCarte=false;
        setHorizontalAlignment(_i);
        setVerticalAlignment(GuiConstants.TOP);
        fullCard=_fullCard;
    }

    public void setPreferredSize(boolean _small) {
        setPreferredSize(getDimension(_small));
    }
    public static MetaDimension getMaxDimension() {
        return getDimension(false);
    }
    public static MetaDimension getMinDimension() {
        return getDimension(true);
    }
    public static MetaDimension getDimension(boolean _small) {
        if (_small) {
            return new MetaDimension(25,150);
        }
        return new MetaDimension(100,150);
    }
    public static MetaDimension getDimensionForSeveralCards(int _number) {
        return new MetaDimension(100 + 25 * (_number - 1), 150);
    }
    void setCarte(AbstractImageFactory _fact, String _lg, CardTarot _pc) {
        lg = _lg;
        card=_pc;
        peinte=false;
        peindreCarte=true;
        int[][] file_ = BaseSixtyFourUtil.getImageByString(CardsInit.ms().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
//                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
    }

    public CardTarot getCard() {
        return card;
    }

    public void setCarteEnJeu(AbstractImageFactory _fact, String _lg, CardTarot _carte) {
        peindreCarte=true;
        setCarte(_fact,_lg,_carte);
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
    public void paintComponent(AbstractImage _g2) {
        if(!peindreCarte) {
            _g2.setColor(GuiConstants.RED);
            _g2.fillRect(0,0,getWidth()-1,getHeight()-1);
            _g2.setColor(GuiConstants.BLACK);
            _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
            _g2.setColor(GuiConstants.BLUE);
            _g2.setFont(DEFAULT, GuiConstants.BOLD,20);
            _g2.drawString(GameEnum.TAROT.toString(lg),20,80);
            return;
        }
        _g2.setColor(GuiConstants.WHITE);
        _g2.fillRect(0,0,getWidth(),getHeight());
        if(fullCard) {
            //whole card
            _g2.drawImage(bufferedImage, 0, 0);
        } else {
            _g2.drawImage(bufferedImage, -75, 0);
        }
        _g2.setColor(GuiConstants.BLACK);
        _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
}
