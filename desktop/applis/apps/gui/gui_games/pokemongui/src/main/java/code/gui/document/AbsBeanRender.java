package code.gui.document;

import aiki.beans.*;
import aiki.beans.fight.*;
import aiki.beans.game.*;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import aiki.game.fight.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;

public abstract class AbsBeanRender implements BeanRenderWithAppName{
    private FacadeGame facade;
    private IntBeanBuilderHelper builder;

    public void build(FacadeGame _facade, StringMapObject _form, IntBeanBuilderHelper _bu){
        builder = _bu;
        _bu.clearAnchors();
        build(_facade, _form);
    }
    public abstract void build(FacadeGame _facade, StringMapObject _form);

    protected void displayStringList(String _file, CustList<String> _list, String _key) {
        builder.breakLine();
        display(_file, _list, _key);
        displayStringList(_list);
    }

    protected void displayStringList(CustList<String> _list) {
        for (String i: _list) {
            nextPart();
            builder.initLine();
            paintMetaLabelDisk();
            builder.formatMessageDir(i);
            builder.feedParents();
            builder.breakLine();
        }
    }

    protected void displayTrainerPlaceNamesList(String _file, CustList<TrainerPlaceNames> _list, String _key) {
        builder.breakLine();
        display(_file, _list, _key);
        displayTrainerPlaceNamesList(_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list) {
        for (TrainerPlaceNames i: _list) {
            nextPart();
            builder.initLine();
            paintMetaLabelDisk();
            builder.formatMessageDir(i.getTrainer()+" - "+i.getPlace());
            builder.feedParents();
            builder.breakLine();
        }
    }

    protected void init(CommonBean _common, FacadeGame _facade, StringMapObject _form) {
        builder.reset();
        _common.setDataBase(_facade);
        _common.setForms(_form);
        _common.setLanguage(_facade.getLanguage());
        _common.beforeDisplaying();
    }

    public TranslationsFile file(String _file) {
        return builder.file(this,_file);
    }

    protected void nextPart() {
        builder.nextPart();
    }

    public void addImg(int[][] _img) {
        builder.addImg(_img);
    }

    public void paintMetaLabelDisk() {
        builder.paintMetaLabelDisk();
    }

    public void formatMessageAnc(IntBeanAction _e,String _file, String _key, String... _values) {
        builder.formatMessageAnc(this,_e,_file,_key,_values);
    }

    public void buildPkList(String _file, String _key, CustList<ImgPkPlayer> _list) {
        builder.initPage();
        display(_file, _list, _key);
        buildPkList(_list);
    }

    public void buildPkList(CustList<ImgPkPlayer> _list) {
        for (ImgPkPlayer i: _list) {
            nextPart();
            initLine();
            paintMetaLabelDisk();
            builder.addImg(i.getImage());
            builder.formatMessageDir(i.getKey().getTranslation());
            builder.feedParents();
            builder.breakLine();
        }
    }
    public void setBackgroundBody() {
        builder.setBackgroundBody();
    }
    public void setTitledBorder(String _title){
        builder.setTitledBorder(_title);
    }
    public void initLine() {
        builder.initLine();
    }

    public void initGrid() {
        builder.initGrid();
    }

    public void initPage() {
        builder.initPage();
    }

    public void display(String _file, Countable _ls, String _key) {
        if (!_ls.isEmpty()) {
            builder.formatMessage(this,_file,_key);
            builder.breakLine();
        }
    }
    public void headerCols(String _file, Countable _ls, String... _cols) {
        if (!_ls.isEmpty()) {
            builder.setColCount(_cols.length);
            for (String h_ : _cols) {
                headerCol(_file, h_);
            }
        }
    }
    public void displayEmpty(String _file, String _value, String _key) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key);
        }
    }
    public void displayNotEmpty(String _file, String _value, String _key) {
        if (!_value.isEmpty()) {
            formatMessage(_file,_key,_value);
        }
    }
    public void displayBoolFull(String _file, int _value, String _one, String _two) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two);
        }
    }
    public void displayBoolFalse(String _file, int _value, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.FALSE_VALUE,_key,_values);
    }
    public void displayBoolTrue(String _file, int _value, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.TRUE_VALUE,_key,_values);
    }
    public void displayBool(String _file, int _value, int _car, String _key, String... _values) {
        if (_value == _car) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayBool(int _value, int _car, int[][] _key) {
        if (_value == _car) {
            addImg(_key);
        }
    }
    public void formatMessage(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(this,_file, _key, _values);
        builder.formatMessageDir(txt_);
    }

    public void formatMessageCts(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(this, _file, _key, _values);
        builder.formatMessageDirCts(txt_);
    }

    public String formatMessageRend(String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(builder.file(this,_file).getMapping().getVal(_key), _values);
    }

    public void formatMessageDirAnc(String _txt, IntBeanAction _b) {
        builder.formatMessageDirAnc(_txt, _b);
    }

    public void formatMessageDir(String _txt) {
        builder.formatMessageDir(_txt);
    }

    public void formatMessageDirCts(String _txt) {
        builder.formatMessageDirCts(_txt);
    }

    public void feedParentsCts() {
        builder.feedParentsCts();
    }

    public void feedParents() {
        builder.feedParents();
    }

    public void displayTrPkMoveTarget(String _file, TrPkMoveTarget _value) {
        formatMessageDirCts(_value.getMoveTarget().getMove());
        if (_value.getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_FOE);
        } else {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_PLAYER);
        }
        if (_value.getMoveTarget().getTarget().getPosition() != Fighter.BACK) {
            formatMessageDirCts(Long.toString(_value.getMoveTarget().getTarget().getPosition()));
            formatMessageDirCts(_value.getTranslation());
        } else {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
        }
    }

    public void displayActivityOfMoveEnabled(String _file, ActivityOfMove _value, String _one, String _two) {
        if (_value.isEnabled()) {
            formatMessageCts(_file,_one);
        } else {
            formatMessageCts(_file,_two);
        }
    }
    public void displayActivityOfMoveNbRound(String _file, ActivityOfMove _value, String _key) {
        if (_value.isIncrementCount()) {
            formatMessageDirCts(Long.toString(_value.getNbTurn()));
        } else {
            formatMessageCts(_file,_key);
        }
    }
    public void headerCol(String _file, String _key) {
        String txt_ = builder.formatMessageRend(this, _file, _key);
        builder.formatMessageDirCtsHeader(txt_);
    }
    public void breakLine() {
        builder.breakLine();
    }

    public IntBeanBuilderHelper getBuilder() {
        return builder;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public void setFacade(FacadeGame _f) {
        this.facade = _f;
    }

}
