package code.expressionlanguage;

import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.VariableSuffix;
import code.gui.GroupFrame;
import code.resources.ResourceFiles;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;


public class LgNamesGui extends LgNamesUtils {

    private String aliasActionListener;
    private String aliasActionPerformed;
    private String aliasActionEvent;
    private String aliasMouseListener;
    private String aliasMouseClicked;
    private String aliasMousePressed;
    private String aliasMouseReleased;
    private String aliasMouseEntered;
    private String aliasMouseExited;
    private String aliasMouseEvent;
    private String aliasGetMouseEventFirst;
    private String aliasGetMouseEventSecond;
    private String aliasWindowListener;
    private String aliasWindowOpened;
    private String aliasWindowClosed;
    private String aliasWindowClosing;
    private String aliasWindowIconified;
    private String aliasWindowDeiconified;
    private String aliasWindowActivated;
    private String aliasWindowDeactivated;
    private String aliasWindowEvent;
    private String aliasListSelection;
    private String aliasValueChanged;

    private String aliasFrame;
    private String aliasPanel;
    private String aliasPanelFlow;
    private String aliasPanelAbsolute;
    private String aliasPanelBorder;
    private String aliasPanelBorderNorth;
    private String aliasPanelBorderSouth;
    private String aliasPanelBorderEast;
    private String aliasPanelBorderWest;
    private String aliasPanelBorderCenter;
    private String aliasPanelBorderBeforeFirst;
    private String aliasPanelBorderBeforeLineBegins;
    private String aliasPanelBorderAfterLast;
    private String aliasPanelBorderAfterLineEnds;
    private String aliasPanelGrid;
    private String aliasPanelPageBox;
    private String aliasButton;
    private String aliasImageLabel;
    private String aliasTextLabel;
    private String aliasScrollPane;
    private String aliasScrollPaneGetView;
    private String aliasScrollPaneSetView;
    private String aliasGetFont;
    private String aliasSetFont;
    private String aliasFont;
    private String aliasFontGetName;
    private String aliasFontGetSize;
    private String aliasFontIsBold;
    private String aliasFontIsItalic;
    private String aliasFontStringWidth;
    private String aliasImage;
    private String aliasImageGetWidth;
    private String aliasImageGetHeight;
    private String aliasImageGet;
    private String aliasImageSet;
    private String aliasImageIsWithAlpha;
    private String aliasImageGetColor;
    private String aliasImageSetColor;
    private String aliasImageGetFont;
    private String aliasImageSetFont;
    private String aliasImageDraw;
    private String aliasImageDrawLine;
    private String aliasImageDrawRect;
    private String aliasImageDrawOval;
    private String aliasImageFillRect;
    private String aliasImageFillOval;
    private String aliasImageDrawPolygon;
    private String aliasImageFillPolygon;
    private String aliasImageDispose;
    private String aliasColor;
    private String aliasColorRed;
    private String aliasColorGreen;
    private String aliasColorBlue;
    private String aliasColorAlpha;
    private String aliasColorIsTransparent;
    private String aliasComponentSetPaint;
    private String aliasComponentGetPaint;
    private String aliasComponentRepaint;
    private String aliasComponentSetAutoscrolls;
    private String aliasComponentIsAutoscrolls;
    private String aliasComponentGetWidth;
    private String aliasComponentGetHeight;
    private String aliasComponentGetPreferredSize;
    private String aliasComponentSetPreferredSize;
    private String aliasComponentIsVisible;
    private String aliasComponentSetVisible;
    private String aliasComponentInvokeLater;
    private String aliasComponent;
    private String aliasDimension;
    private String aliasDimensionGetHeight;
    private String aliasDimensionGetWidth;
    private String aliasSetContent;
    private String aliasAddCompo;
    private String aliasGetParentCompo;
    private String aliasGetNextCompo;
    private String aliasGetIndexCompo;
    private String aliasRemoveAll;
    private String aliasAddListener;
    private String aliasAddWindowListener;
    private String aliasSetLabelText;
    private String aliasSetLabelImage;
    private String aliasPaint;
    private String aliasPaintMethod;
    private String aliasPaintAdd;
    private String aliasPaintRefresh;
    private String aliasRemoveCompo;
    private String aliasCount;
    private String aliasSetVisible;
    private String aliasWindow;
    private String aliasArgs;
    private String aliasPack;
    private String aliasDispose;
    private String aliasInput;
    private String aliasInputIsEnabled;
    private String aliasInputSetEnabled;

    private String aliasRender;
    private String aliasRenderGetHeight;
    private String aliasRenderGetWidth;
    private String aliasRenderGetPaint;
    private String aliasRenderSetHeight;
    private String aliasRenderSetWidth;
    private String aliasRenderSetPaint;
    private String aliasGrList;
    private String aliasGrListAdd;
    private String aliasGrListSet;
    private String aliasGrListGetListView;
    private String aliasGrListGetSelectedIndexes;
    private String aliasGrListSetSelectedIndexes;
    private String aliasGrListClearSelection;
    private String aliasGrListClear;
    private String aliasGrListRemove;
    private String aliasGrListUpdateGraphics;
    private String aliasGrListGetRender;
    private String aliasGrListSetRender;
    private String aliasGrListGetSelection;
    private String aliasGrListSetSelection;
    private String aliasGrListGetVisibleRowCount;
    private String aliasGrListSetVisibleRowCount;

    private String aliasCombo;
    private String aliasComboGetSelectedItem;
    private String aliasComboAddItem;
    private String aliasComboGetItemCount;
    private String aliasComboSelectItem;
    private String aliasComboSetListener;
    private String aliasComboGetListener;
    private String aliasComboGetSelectedIndexes;
    private String aliasComboGetSelectedIndex;
    private String aliasComboRemoveAllItems;
    private String aliasComboRemoveItem;

    private String aliasButtonGroup;
    private String aliasButtonGroupAdd;
    private String aliasRadio;
    private String aliasRadioIsSelected;
    private String aliasRadioSetSelected;
    private String aliasRadioGetText;
    private String aliasRadioSetText;
    private String aliasPopupMenu;
    private String aliasPopupMenuAdd;
    private String aliasPopupMenuShow;
    private String aliasTextField;
    private String aliasTextFieldGetText;
    private String aliasTextFieldSetText;
    private String aliasTextFieldAuto;
    private String aliasTextFieldAddAction;
    private String aliasTextFieldAddDocument;
    private String aliasTextFieldAddPopup;
    private String aliasTextArea;
    private String aliasTextAreaGetText;
    private String aliasTextAreaSetText;
    private String aliasTextAreaGetTabSize;
    private String aliasTextAreaSetTabSize;
    private String aliasTextAreaAppend;
    private String aliasTextAreaInsert;
    private String aliasTextAreaReplaceRange;
    private String aliasTextAreaReplaceSelection;
    private String aliasTextAreaGetSelectedText;
    private String aliasTextAreaSetSelectionStart;
    private String aliasTextAreaSetSelectionEnd;
    private String aliasTextAreaSelect;
    private String aliasTextAreaSelectAll;
    private String aliasCheckBox;
    private String aliasCheckBoxGetText;
    private String aliasCheckBoxSetText;
    private String aliasCheckBoxIsSelected;
    private String aliasCheckBoxSetSelected;
    private String aliasCheckBoxAddAction;
    private String aliasSpinner;
    private String aliasSpinnerGetValue;
    private String aliasSpinnerSetValue;
    private String aliasSpinnerGetMax;
    private String aliasSpinnerSetMax;
    private String aliasSpinnerGetMin;
    private String aliasSpinnerSetMin;
    private String aliasSpinnerGetStep;
    private String aliasSpinnerSetStep;
    private String aliasSpinnerAddAction;
    private String aliasSlider;
    private String aliasSliderGetValue;
    private String aliasSliderSetValue;
    private String aliasSliderGetMax;
    private String aliasSliderSetMax;
    private String aliasSliderGetMin;
    private String aliasSliderSetMin;
    private String aliasSliderAddAction;
    public void buildOther() {
        super.buildOther();
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasFrame, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasPanel);
        method_ = new StandardMethod(aliasSetContent, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPack, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDispose, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetVisible, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindow, params_, aliasFrame, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasArgs, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasString()), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowListener);
        method_ = new StandardMethod(aliasAddWindowListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasFrame, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasComponent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParentCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNextCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFont, params_, aliasFont, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasSetFont, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        params_ = new StringList();
        String type_ = StringList.concat(getAliasFct(),"<",aliasComponent,",?>");
        method_ = new StandardMethod(aliasComponentGetPaint, params_, type_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetPaint, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentRepaint, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        type_ = getAliasPrimBoolean();
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsAutoscrolls, params_, type_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetAutoscrolls, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetPreferredSize, params_, aliasDimension, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasDimension);
        method_ = new StandardMethod(aliasComponentSetPreferredSize, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsVisible, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasComponentSetVisible, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasRunnable());
        method_ = new StandardMethod(aliasComponentInvokeLater, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasComponent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasDimension, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetHeight, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasDimension);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasDimension, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasActionEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        std_ = stdcl_;
        getStandards().put(aliasActionEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasWindowEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        std_ = stdcl_;
        getStandards().put(aliasWindowEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMouseEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMouseEventFirst, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMouseEventSecond, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasMouseEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPanel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent,getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasRemoveCompo, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetIndexCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelFlow, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelAbsolute, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelPageBox, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasPanelGrid, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasPanel, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPanelBorder, fields_, constructors_, methods_, aliasPanel, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent,getAliasString());
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        fields_.add(aliasPanelBorderNorth,new StandardField(aliasPanelBorderNorth,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderSouth,new StandardField(aliasPanelBorderSouth,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderWest,new StandardField(aliasPanelBorderWest,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderEast,new StandardField(aliasPanelBorderEast,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderCenter,new StandardField(aliasPanelBorderCenter,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderBeforeFirst,new StandardField(aliasPanelBorderBeforeFirst,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderBeforeLineBegins,new StandardField(aliasPanelBorderBeforeLineBegins,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderAfterLineEnds,new StandardField(aliasPanelBorderAfterLineEnds,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderAfterLast,new StandardField(aliasPanelBorderAfterLast,getAliasString(),true,true,stdcl_));
        std_ = stdcl_;
        getStandards().put(aliasPanelBorder, std_);


        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasScrollPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneGetView, params_,aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasScrollPaneSetView, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasComponent);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasScrollPane, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasInput, fields_, constructors_, methods_, aliasComponent, MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInputIsEnabled, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasInputSetEnabled, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasInput, std_);


        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasButton, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasButton, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasSetLabelText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasTextLabel, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasImageLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage);
        method_ = new StandardMethod(aliasSetLabelImage, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasImage);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasImageLabel, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasFont, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetName, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsBold, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsItalic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage,getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasFont, stdcl_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasColor, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorAlpha, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorBlue, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorRed, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorGreen, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorIsTransparent, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasColor, stdcl_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasImage, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageIsWithAlpha, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetHeight, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageGet, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(), getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageSet, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetColor, params_, aliasColor, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasImageSetColor, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetFont, params_, aliasFont, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasImageSetFont, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        params_ = new StringList(aliasImage,getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger());
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawLine, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawRect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawOval, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageDrawPolygon, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillRect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillOval, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageFillPolygon, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageDispose, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasImage, stdcl_);
        buildInputs();
    }
    private void buildInputs() {
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        StringMap<StandardField> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasRender, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        String typeHeight_ = StringList.concat(getAliasFct(),"<",getAliasObject(),",",getAliasPrimInteger(),">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetHeight, params_, typeHeight_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(typeHeight_);
        method_ = new StandardMethod(aliasRenderSetHeight, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        String typeWidth_ = StringList.concat(getAliasFct(),"<",getAliasObject(),",",getAliasPrimInteger(),",",getAliasPrimInteger(),">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetWidth, params_, typeWidth_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(typeWidth_);
        method_ = new StandardMethod(aliasRenderSetWidth, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        String typePaint_ = StringList.concat(getAliasFct(),"<",aliasGrList,",",getAliasObject(),",",getAliasPrimInteger(),",",getAliasPrimBoolean(),",",aliasImage,",?>");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetPaint, params_, typePaint_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(typePaint_);
        method_ = new StandardMethod(aliasRenderSetPaint, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasRender, stdcl_);


        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasGrList, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetRender, params_, aliasRender, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasRender);
        method_ = new StandardMethod(aliasGrListSetRender, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelection, params_, aliasListSelection, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasGrListSetSelection, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetVisibleRowCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetVisibleRowCount, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasImageLabel,getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasImageLabel,getAliasObject());
        method_ = new StandardMethod(aliasGrListSet, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetListView, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasObject()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelectedIndexes, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetSelectedIndexes, params_, getAliasVoid(), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClearSelection, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListUpdateGraphics, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClear, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasGrList, stdcl_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCombo, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetListener, params_, aliasListSelection, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasComboSetListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasComboAddItem, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboSelectItem, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetItemCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndex, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndexes, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedItem, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboRemoveItem, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboRemoveAllItems, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,true,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasString());
        ctor_ = new StandardConstructor(params_,true,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasCombo, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasButtonGroup, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasRadio);
        method_ = new StandardMethod(aliasButtonGroupAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasButtonGroup, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPopupMenu, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent,getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuShow, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasPopupMenu, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasRadio, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioIsSelected, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasRadioSetSelected, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasRadioSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasRadio, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCheckBox, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxIsSelected, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCheckBoxSetSelected, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasCheckBoxSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasCheckBox, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextField, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextFieldGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextFieldSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasTextField, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextArea, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextAreaSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextAreaAppend, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaInsert, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetSelectedText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionStart, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionEnd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetTabSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetTabSize, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaReplaceRange, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextAreaReplaceSelection, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSelect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaSelectAll, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasTextArea, stdcl_);
    }

    @Override
    public ResultErrorStd getSimpleResult(Analyzable _conf, ClassField _classField) {
        if (StringList.quickEq(_classField.getClassName(),aliasPanelBorder)) {
            ResultErrorStd res_ = new ResultErrorStd();
            String fieldName_ = _classField.getFieldName();
            if (StringList.quickEq(fieldName_,aliasPanelBorderNorth)) {
                res_.setResult(new StringStruct(PanelBorderStruct.NORTH));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderSouth)) {
                res_.setResult(new StringStruct(PanelBorderStruct.SOUTH));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderWest)) {
                res_.setResult(new StringStruct(PanelBorderStruct.WEST));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderEast)) {
                res_.setResult(new StringStruct(PanelBorderStruct.EAST));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderCenter)) {
                res_.setResult(new StringStruct(PanelBorderStruct.CENTER));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderBeforeFirst)) {
                res_.setResult(new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderBeforeLineBegins)) {
                res_.setResult(new StringStruct(PanelBorderStruct.BEFORE_LINE_BEGINS));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderAfterLineEnds)) {
                res_.setResult(new StringStruct(PanelBorderStruct.AFTER_LINE_ENDS));
                return res_;
            }
            res_.setResult(new StringStruct(PanelBorderStruct.AFTER_LAST_LINE));
            return res_;
        }
        return super.getSimpleResult(_conf, _classField);
    }

    public Argument defaultInstance(ExecutableCode _cont, String _id) {
        Argument arg_ = super.defaultInstance(_cont, _id);
        if (!arg_.isNull() || _cont.getContextEl().hasExceptionOrFailInit()) {
            return arg_;
        }
        if (StringList.quickEq(_id,aliasFont)) {
            return new Argument(new FontStruct());
        }
        if (_cont.getContextEl().isInitEnums()) {
            _cont.getContextEl().failInitEnums();
            return new Argument();
        }
        if (StringList.quickEq(_id,aliasFrame)) {
            return new Argument(new FrameStruct(new OtherFrame(((GuiContextEl)_cont).getLgExec())));
        }
        if (StringList.quickEq(_id,aliasPanel)) {
            return new Argument(PanelStruct.newFlow(aliasPanel));
        }
        if (StringList.quickEq(_id,aliasScrollPane)) {
            return new Argument(ScrollPaneStruct.newScroll(aliasScrollPane));
        }
        if (StringList.quickEq(_id,aliasPanelBorder)) {
            return new Argument(PanelBorderStruct.newBorder(aliasPanelBorder));
        }
        if (StringList.quickEq(_id,aliasButton)) {
            return new Argument(new PlainButtonStruct(aliasButton));
        }
        if (StringList.quickEq(_id,aliasTextLabel)) {
            return new Argument(new TextLabelStruct(aliasTextLabel));
        }
        if (StringList.quickEq(_id,aliasImageLabel)) {
            return new Argument(new PreparedLabelStruct(aliasImageLabel));
        }
        if (StringList.quickEq(_id,aliasRender)) {
            return new Argument(new RenderStruct());
        }
        if (StringList.quickEq(_id,aliasGrList)) {
            return new Argument(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,true));
        }
        if (StringList.quickEq(_id,aliasCombo)) {
            return new Argument(new GraphicComboStruct(aliasCombo));
        }
        return arg_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        String name_ = _method.getName();
        ResultErrorStd r_ = new ResultErrorStd();
        if (StringList.quickEq(name_,aliasFrame)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new FrameStruct(new OtherFrame(((GuiContextEl)_cont).getLgExec())));
            return r_;
        }
        if (StringList.quickEq(name_,aliasPanel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelStruct.newFlow(aliasPanel));
            return r_;
        }
        if (StringList.quickEq(name_,aliasPanelBorder)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelBorderStruct.newBorder(aliasPanelBorder));
            return r_;
        }
        if (StringList.quickEq(name_,aliasScrollPane)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(ScrollPaneStruct.newScroll(_args[0], aliasButton));
            } else {
                r_.setResult(ScrollPaneStruct.newScroll(aliasButton));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasButton)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new PlainButtonStruct(_args[0],aliasButton));
            } else {
                r_.setResult(new PlainButtonStruct(aliasButton));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasTextLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new TextLabelStruct(_args[0],aliasTextLabel));
            } else {
                r_.setResult(new TextLabelStruct(aliasTextLabel));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasImageLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new PreparedLabelStruct(_args[0],aliasImageLabel));
            } else {
                r_.setResult(new PreparedLabelStruct(aliasImageLabel));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasFont)) {
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new FontStruct());
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new FontStruct(((NumberStruct)_args[0]).intStruct()));
                return r_;
            }
            r_.setResult(new FontStruct(_args[0],((BooleanStruct)_args[1]).getInstance(),((BooleanStruct)_args[2]).getInstance(),((NumberStruct)_args[3]).intStruct()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasColor)) {
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct()));
                return r_;
            }
            if (_method.getParametersTypes().size() == 2) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((BooleanStruct)_args[1]).getInstance()));
                return r_;
            }
            if (_method.getParametersTypes().size() == 3) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct()));
                return r_;
            }
            r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasImage)) {
            r_.setResult(new ImageStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((BooleanStruct)_args[2]).getInstance()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasDimension)) {
            if (_method.getParametersTypes().size() == 1) {
                if (!(_args[0] instanceof DimensionStruct)) {
                    r_.setResult(new DimensionStruct(0,0));
                    return r_;
                }
                r_.setResult(new DimensionStruct((DimensionStruct)_args[0]));
                return r_;
            }
            r_.setResult(new DimensionStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasRender)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new RenderStruct());
            return r_;
        }
        if (StringList.quickEq(name_,aliasGrList)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,((BooleanStruct)_args[0]).getInstance()));
            return r_;
        }
        if (StringList.quickEq(name_, aliasCombo)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new GraphicComboStruct(aliasCombo));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new GraphicComboStruct(aliasCombo,_args[0]));
                return r_;
            }
            r_.setResult(new GraphicComboStruct(aliasCombo,((NumberStruct)_args[0]).intStruct(),_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasButtonGroup)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new CustButtonGroupStruct());
            return r_;
        }
        if (StringList.quickEq(name_, aliasRadio)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new RadioButtonStruct(aliasRadio));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new RadioButtonStruct(aliasRadio,_args[0]));
                return r_;
            }
            r_.setResult(new RadioButtonStruct(aliasRadio,_args[0],_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasCheckBox)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new CheckBoxStruct(aliasCheckBox));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new CheckBoxStruct(aliasCheckBox,_args[0]));
                return r_;
            }
            r_.setResult(new CheckBoxStruct(aliasCheckBox,_args[0],_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasPopupMenu)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new PopupStruct(aliasPopupMenu));
            return r_;
        }
        if (StringList.quickEq(name_, aliasTextField)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new TextFieldStruct(aliasTextField));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    r_.setResult(new TextFieldStruct(aliasTextField,_args[0]));
                    return r_;
                }
                r_.setResult(new TextFieldStruct(aliasTextField,(NumberStruct) _args[0]));
                return r_;
            }
            r_.setResult(new TextFieldStruct(aliasTextField,_args[0],_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasTextArea)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new TextAreaStruct(aliasTextArea));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new TextAreaStruct(aliasTextArea,_args[0]));
                return r_;
            }
            if (_method.getParametersTypes().size() == 2) {
                r_.setResult(new TextAreaStruct(aliasTextArea,_args[0],_args[1]));
                return r_;
            }
            r_.setResult(new TextAreaStruct(aliasTextArea,_args[0],_args[1],_args[2]));
            return r_;
        }
        return super.getOtherResult(_cont,_method,_args);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        if (StringList.quickEq(type_, aliasFrame)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasWindow)) {
                res_.setResult(((GuiContextEl)_cont).getFrame());
                return res_;
            }
            if (StringList.quickEq(name_, aliasArgs)) {
                StringList mainArgs_ = ((GuiContextEl) _cont).getMainArgs();
                String typeStr_ = getAliasString();
                typeStr_ = PrimitiveTypeUtil.getPrettyArrayType(typeStr_);
                int len_ = mainArgs_.size();
                Struct[] struct_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    struct_[i] = new StringStruct(mainArgs_.get(i));
                }
                res_.setResult(new ArrayStruct(struct_,typeStr_));
                return res_;
            }
            GroupFrame inst_ = ((FrameStruct) _instance).getCommonFrame();
            if (StringList.quickEq(name_, aliasPack)) {
                inst_.pack();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasDispose)) {
                inst_.dispose();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddWindowListener)) {
                ((GuiContextEl)_cont).addWindowListener((FrameStruct) _instance,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSetVisible)) {
                inst_.setVisible(((BooleanStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_args[0] instanceof PanelStruct) {
                inst_.setContentPane(((PanelStruct)_args[0]).getPanel());
            }
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasDimension)) {
            DimensionStruct inst_ = (DimensionStruct)_instance;
            if (StringList.quickEq(name_, aliasDimensionGetHeight)) {
                res_.setResult(inst_.getHeight());
                return res_;
            }
            res_.setResult(inst_.getWidth());
            return res_;
        }
        if (StringList.quickEq(type_, aliasComponent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentInvokeLater)) {
                CustComponentStruct.invokeLater((RunnableContextEl) _cont,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            CustComponentStruct inst_ = (CustComponentStruct)_instance;
            if (StringList.quickEq(name_, aliasGetFont)) {
                res_.setResult(inst_.getFont());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSetFont)) {
                inst_.setFont(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetNextCompo)) {
                res_.setResult(inst_.getNext());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetPaint)) {
                res_.setResult(inst_.getPaintEvent());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetPaint)) {
                inst_.setPaintEvent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetHeight)) {
                res_.setResult(new IntStruct(inst_.getHeight()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetWidth)) {
                res_.setResult(new IntStruct(inst_.getWidth()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetPreferredSize)) {
                res_.setResult(inst_.getPreferredSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetPreferredSize)) {
                inst_.setPreferredSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentIsVisible)) {
                res_.setResult(inst_.isVisible());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetVisible)) {
                inst_.setVisible(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentIsAutoscrolls)) {
                res_.setResult(inst_.isAutoscrolls());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetAutoscrolls)) {
                inst_.setAutoscrolls(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentRepaint)) {
                ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintMethod,new StringList(aliasComponent)));
                String className_ = polymorph_.getClassName();
                MethodId ct_ = polymorph_.getConstraints();
                Argument arg_ = new Argument(inst_);
                _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_,new CustList<Argument>(arg_),null));
                return res_;
            }
            res_.setResult(inst_.getParentComponent());
            return res_;
        }
        if (StringList.quickEq(type_, aliasPanel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelAbsolute)) {
                res_.setResult(PanelStruct.newAbsolute(aliasPanel));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelFlow)) {
                res_.setResult(PanelStruct.newFlow(aliasPanel));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelGrid)) {
                res_.setResult(PanelStruct.newGrid(aliasPanel,((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelPageBox)) {
                res_.setResult(PanelStruct.newPageBox(aliasPanel));
                return res_;
            }
            PanelStruct strPan_ = (PanelStruct) _instance;
            if (StringList.quickEq(name_, aliasCount)) {
                res_.setResult(new IntStruct(strPan_.getComponentCount()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasRemoveAll)) {
                strPan_.removeAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddCompo)) {
                if (!(_args[0] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (_method.getConstraints().getParametersTypes().size() == 2) {
                    strPan_.add((CustComponentStruct)_args[0],((NumberStruct)_args[1]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.add((CustComponentStruct)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetIndexCompo)) {
                res_.setResult(strPan_.getComponent(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            if (StringList.quickEq(getAliasPrimInteger(),_method.getConstraints().getParametersTypes().first())) {
                res_.setResult(strPan_.remove(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            res_.setResult(strPan_.remove(_args[0]));
            return res_;
        }
        if (StringList.quickEq(type_, aliasPanelBorder)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (!(_args[0] instanceof CustComponentStruct)) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PanelBorderStruct strPan_ = (PanelBorderStruct) _instance;
            strPan_.add((CustComponentStruct)_args[0],_args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_,aliasScrollPane)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            ScrollPaneStruct strPan_ = (ScrollPaneStruct) _instance;
            if (StringList.quickEq(name_, aliasScrollPaneGetView)) {
                res_.setResult(strPan_.getView());
                return res_;
            }
            strPan_.setViewportView(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            TextLabelStruct txt_ = (TextLabelStruct) _instance;
            if (StringList.quickEq(name_, aliasAddListener)) {
                txt_.addMouse(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            txt_.setText(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasImageLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PreparedLabelStruct txt_ = (PreparedLabelStruct) _instance;
            if (StringList.quickEq(name_, aliasAddListener)) {
                txt_.addMouse(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            txt_.setImage(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasMouseEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            MouseEventStruct event_ = (MouseEventStruct)_instance;
            if (StringList.quickEq(name_, aliasGetMouseEventFirst)) {
                res_.setResult(new IntStruct(event_.getFirst()));
                return res_;
            }
            res_.setResult(new IntStruct(event_.getSecond()));
            return res_;
        }
        if (StringList.quickEq(type_, aliasInput)) {
            InputStruct in_ = (InputStruct) _instance;
            if (StringList.quickEq(name_, aliasInputSetEnabled)) {
                in_.setEnabled(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(in_.isEnabled());
            return res_;
        }
        if (StringList.quickEq(type_, aliasButton)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PlainButtonStruct pl_ = (PlainButtonStruct) _instance;
            pl_.addActionListener(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasFont)) {
            if (_method.getConstraints().isStaticMethod()) {
                Struct first_ = _args[0];
                if (!(first_ instanceof ImageStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                Struct font_ = ((ImageStruct) first_).getFont();
                if (!(font_ instanceof FontStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                FontStruct f_ = (FontStruct) font_;
                res_.setResult(((GuiContextEl)_cont).stringWidth(f_,_args[1]));
                return res_;
            }
            FontStruct f_ = (FontStruct) _instance;
            if (StringList.quickEq(name_, aliasFontGetName)) {
                res_.setResult(f_.getName());
                return res_;
            }
            if (StringList.quickEq(name_, aliasFontGetSize)) {
                res_.setResult(f_.getSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasFontIsBold)) {
                res_.setResult(f_.isBold());
                return res_;
            }
            if (StringList.quickEq(name_, aliasFontIsItalic)) {
                res_.setResult(f_.isItalic());
                return res_;
            }
            res_.setResult(((GuiContextEl)_cont).stringWidth(f_,_args[0]));
            return res_;
        }
        if (StringList.quickEq(type_, aliasColor)) {
            ColorStruct c_ = (ColorStruct) _instance;
            if (StringList.quickEq(name_, aliasColorAlpha)) {
                res_.setResult(c_.getAlpha());
                return res_;
            }
            if (StringList.quickEq(name_, aliasColorRed)) {
                res_.setResult(c_.getRed());
                return res_;
            }
            if (StringList.quickEq(name_, aliasColorGreen)) {
                res_.setResult(c_.getGreen());
                return res_;
            }
            if (StringList.quickEq(name_, aliasColorBlue)) {
                res_.setResult(c_.getBlue());
                return res_;
            }
            res_.setResult(c_.isTransparent());
            return res_;
        }
        if (StringList.quickEq(type_,aliasImage)) {
            ImageStruct image_ = (ImageStruct) _instance;
            if (StringList.quickEq(name_, aliasImageGetHeight)) {
                res_.setResult(image_.getHeight());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGetWidth)) {
                res_.setResult(image_.getWidth());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageSet)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setPixel(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGet)) {
                res_.setResult(image_.getPixel(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageIsWithAlpha)) {
                res_.setResult(image_.isWithAlpha());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGetColor)) {
                res_.setResult(image_.getColor());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageSetColor)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setColor(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGetFont)) {
                res_.setResult(image_.getFont());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageSetFont)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setFont(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDispose)) {
                image_.dispose();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDraw)) {
                if (StringList.quickEq(getAliasImage(),_method.getConstraints().getParametersTypes().first())) {
                    image_.drawImage(_args[0],((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.drawString(_args[0],((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawLine)) {
                image_.drawLine(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawRect)) {
                image_.drawRect(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawOval)) {
                image_.drawOval(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawPolygon)) {
                image_.drawPolygon(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageFillRect)) {
                image_.fillRect(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageFillOval)) {
                image_.fillOval(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            image_.fillPolygon(_args[0],_args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasRender)) {
            RenderStruct image_ = (RenderStruct) _instance;
            if (StringList.quickEq(name_, aliasRenderSetPaint)) {
                image_.setPaint(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderSetWidth)) {
                image_.setWidth(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderSetHeight)) {
                image_.setHeight(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderGetPaint)) {
                res_.setResult(image_.getPaint());
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderGetHeight)) {
                res_.setResult(image_.getHeight());
                return res_;
            }
            res_.setResult(image_.getWidth());
            return res_;
        }
        if (StringList.quickEq(type_, aliasGrList)) {
            GraphicListStruct inst_ = (GraphicListStruct) _instance;
            if (StringList.quickEq(name_, aliasGrListSetSelection)) {
                inst_.setListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetSelection)) {
                res_.setResult(inst_.getListener());
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSetVisibleRowCount)) {
                inst_.setVisibleRowCount(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetVisibleRowCount)) {
                res_.setResult(inst_.getVisibleRowCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSetRender)) {
                inst_.setRender(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetRender)) {
                res_.setResult(inst_.getRender());
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetListView)) {
                res_.setResult(inst_.getListView(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetSelectedIndexes)) {
                res_.setResult(inst_.getSelectedIndexes(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListRemove)){
                inst_.remove(((NumberStruct)_args[0]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListClear)){
                inst_.clear();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSetSelectedIndexes)) {
                inst_.setSelectedIndexes(_args[0]);
                if (_args[0] instanceof ArrayStruct) {
                    StringList params_ = new StringList(aliasGrList);
                    ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintRefresh, params_));
                    String className_ = polymorph_.getClassName();
                    MethodId ct_ = polymorph_.getConstraints();
                    Argument arg_ = new Argument(inst_);
                    CustList<Argument> args_ = new CustList<Argument>(arg_);
                    _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_, args_,null));
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListClearSelection)) {
                inst_.clearSelection();
                StringList params_ = new StringList(aliasGrList);
                ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintRefresh, params_));
                String className_ = polymorph_.getClassName();
                MethodId ct_ = polymorph_.getConstraints();
                Argument arg_ = new Argument(inst_);
                CustList<Argument> args_ = new CustList<Argument>(arg_);
                _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_, args_,null));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListUpdateGraphics)) {
                inst_.updateGraphics();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSet)) {
                inst_.set(_cont,((NumberStruct)_args[0]).intStruct(),_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_method.getConstraints().getParametersTypes().size() == 3) {
                inst_.add(_cont,((NumberStruct)_args[0]).intStruct(),_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            StringList params_ = new StringList(aliasGrList,getAliasPrimInteger(),getAliasObject());
            ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintAdd, params_));
            String className_ = polymorph_.getClassName();
            MethodId ct_ = polymorph_.getConstraints();
            Argument arg_ = new Argument(inst_);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            args_.add(new Argument(_args[0]));
            args_.add(new Argument(_args[1]));
            _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_, args_,null));
            return res_;
        }
        if (StringList.quickEq(type_, aliasCombo)) {
            GraphicComboStruct inst_ = (GraphicComboStruct) _instance;
            if (StringList.quickEq(name_, aliasComboSetListener)) {
                inst_.setListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetListener)) {
                res_.setResult(inst_.getListener());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetSelectedIndexes)) {
                res_.setResult(inst_.getSelectedIndexes(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetSelectedIndex)) {
                res_.setResult(inst_.getSelectedIndex());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetSelectedItem)) {
                res_.setResult(inst_.getSelectedItem());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboAddItem)) {
                inst_.addItem(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboSelectItem)) {
                inst_.selectItem((RunnableContextEl) _cont,(NumberStruct) _args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboRemoveItem)) {
                inst_.removeItem(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboRemoveAllItems)) {
                inst_.removeAllItems();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getItemCount());
            return res_;
        }
        if (StringList.quickEq(type_, aliasRadio)) {
            RadioButtonStruct inst_ = (RadioButtonStruct) _instance;
            if (StringList.quickEq(name_, aliasRadioSetSelected)) {
                inst_.setSelected(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRadioIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            if (StringList.quickEq(name_, aliasRadioSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasCheckBox)) {
            CheckBoxStruct inst_ = (CheckBoxStruct) _instance;
            if (StringList.quickEq(name_, aliasCheckBoxSetSelected)) {
                inst_.setSelected(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCheckBoxIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCheckBoxSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasPopupMenu)) {
            PopupStruct inst_ = (PopupStruct) _instance;
            if (StringList.quickEq(name_, aliasPopupMenuAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.show(_args[0],_args[1],_args[2]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextField)) {
            TextFieldStruct inst_ = (TextFieldStruct) _instance;
            if (StringList.quickEq(name_, aliasTextFieldSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextArea)) {
            TextAreaStruct inst_ = (TextAreaStruct) _instance;
            if (StringList.quickEq(name_, aliasTextAreaAppend)) {
                inst_.append(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaInsert)) {
                inst_.insert(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaReplaceRange)) {
                inst_.replaceRange(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaReplaceSelection)) {
                inst_.replaceSelection(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSelect)) {
                inst_.select(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSelectAll)) {
                inst_.selectAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaGetSelectedText)) {
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetSelectionStart)) {
                inst_.setSelectionStart(_args[0]);
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetSelectionEnd)) {
                inst_.setSelectionEnd(_args[0]);
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetTabSize)) {
                inst_.setTabSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaGetTabSize)) {
                res_.setResult(inst_.getTabSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        return super.getOtherResult(_cont,_instance,_method,_args);
    }
    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = ResourceFiles.ressourceFichier("resources_lg_gui/action_event.txt");
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String class_ = keyWords_.getKeyWordClass();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String static_ = keyWords_.getKeyWordStatic();
        String if_ = keyWords_.getKeyWordIf();
        String elseif_ = keyWords_.getKeyWordElseif();
        String while_ = keyWords_.getKeyWordWhile();
        String final_ = keyWords_.getKeyWordFinal();
        String return_ = keyWords_.getKeyWordReturn();
        String continue_ = keyWords_.getKeyWordContinue();
        String break_ = keyWords_.getKeyWordBreak();
        String for_ = keyWords_.getKeyWordFor();
        String new_ = keyWords_.getKeyWordNew();
        String null_ = keyWords_.getKeyWordNull();
        String cast_ = keyWords_.getKeyWordCast();
        String true_ = keyWords_.getKeyWordTrue();
        String false_ = keyWords_.getKeyWordFalse();
        String is_ = keyWords_.getKeyWordInstanceof();
        String endLine_ = String.valueOf(_context.getOptions().getEndLine());
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ActionListener}", aliasActionListener);
        map_.put("{actionPerformed}", aliasActionPerformed);
        map_.put("{ActionEvent}", aliasActionEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasActionListener);
        stds_.put(aliasActionListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasActionListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/mouse_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{MouseListener}", aliasMouseListener);
        map_.put("{mouseClicked}", aliasMouseClicked);
        map_.put("{mousePressed}", aliasMousePressed);
        map_.put("{mouseReleased}", aliasMouseReleased);
        map_.put("{mouseEntered}", aliasMouseEntered);
        map_.put("{mouseExited}", aliasMouseExited);
        map_.put("{MouseEvent}", aliasMouseEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasMouseListener);
        stds_.put(aliasMouseListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasMouseListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/window_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{WindowListener}", aliasWindowListener);
        map_.put("{windowOpened}", aliasWindowOpened);
        map_.put("{windowClosing}", aliasWindowClosing);
        map_.put("{windowClosed}", aliasWindowClosed);
        map_.put("{windowIconified}", aliasWindowIconified);
        map_.put("{windowDeiconified}", aliasWindowDeiconified);
        map_.put("{windowActivated}", aliasWindowActivated);
        map_.put("{windowDeactivated}", aliasWindowDeactivated);
        map_.put("{WindowEvent}", aliasWindowEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasWindowListener);
        stds_.put(aliasWindowListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasWindowListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/list_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ListSelection}", aliasListSelection);
        map_.put("{valueChanged}", aliasValueChanged);
        map_.put("{int}", getAliasPrimInteger());
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{f}", tr("f",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasListSelection);
        stds_.put(aliasListSelection, content_);
        getPredefinedInterfacesInitOrder().add(aliasListSelection);
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixLocal_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLocal_ = StringList.concat(suffix_,".");
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLocal_ = suffix_;
        }
        String suffixParam_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixParam_ = StringList.concat(suffix_,".",suffix_);
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixParam_ = suffix_;
        }
        String suffixLoop_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLoop_ = suffix_;
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLoop_ = suffix_;
        }
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/repaint.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{abstract}", abstract_);
        map_.put("{final}", final_);
        map_.put("{class}", class_);
        map_.put("{Paint}", aliasPaint);
        map_.put("{paint}", aliasPaintMethod);
        map_.put("{add}", aliasPaintAdd);
        map_.put("{refresh}", aliasPaintRefresh);
        map_.put("{static}", static_);
        map_.put("{if}", if_);
        map_.put("{elseif}", elseif_);
        map_.put("{true}", true_);
        map_.put("{false}", false_);
        map_.put("{return}", return_);
        map_.put("{break}", break_);
        map_.put("{continue}", continue_);
        map_.put("{is}", is_);
        map_.put("{while}", while_);
        map_.put("{null}", null_);
        map_.put("{cast}", cast_);
        map_.put("{param}", suffixParam_);
        map_.put("{local}", suffixLocal_);
        map_.put("{loop}", suffixLoop_);
        map_.put("{Component}", aliasComponent);
        map_.put("{Panel}", aliasPanel);
        map_.put("{Fct}", getAliasFct());
        map_.put("{void}", getAliasVoid());
        map_.put("{call}", getAliasCall());
        map_.put("{getComponent}", aliasGetIndexCompo);
        map_.put("{next}", aliasGetNextCompo);
        map_.put("{getParent}", aliasGetParentCompo);
        map_.put("{getPainting}", aliasComponentGetPaint);
        map_.put("{e}", tr("e",_context));
        map_.put("{c}", tr("c",_context));
        map_.put("{p}", tr("p",_context));
        map_.put("{r}", tr("r",_context));
        map_.put("{o}", tr("o",_context));
        map_.put("{i}", tr("i",_context));
        map_.put("{l}", tr("l",_context));
        map_.put("{la}", tr("la",_context));
        map_.put("{h}", tr("h",_context));
        map_.put("{hf}", tr("hf",_context));
        map_.put("{lf}", tr("lf",_context));
        map_.put("{pf}", tr("pf",_context));
        map_.put("{par}", tr("par",_context));
        map_.put("{pan}", tr("pan",_context));
        map_.put("{fct}", tr("fct",_context));
        map_.put("{nb}", tr("nb",_context));
        map_.put("{img}", tr("img",_context));
        map_.put("{endLine}", endLine_);
        map_.put("{suffix}", suffix_);
        map_.put("{getRender}",aliasGrListGetRender);
        map_.put("{getHeight}",aliasRenderGetHeight);
        map_.put("{getWidth}",aliasRenderGetWidth);
        map_.put("{getPaint}",aliasRenderGetPaint);
        map_.put("{Image}",aliasImage);
        map_.put("{ImageLabel}",aliasImageLabel);
        map_.put("{addCompo}",aliasGrListAdd);
        map_.put("{setCompo}",aliasGrListSet);
        map_.put("{getView}",aliasGrListGetListView);
        map_.put("{getSelected}",aliasGrListGetSelectedIndexes);
        map_.put("{updateGraphics}",aliasGrListUpdateGraphics);
        map_.put("{length}",getAliasLength());
        map_.put("{for}",for_);
        map_.put("{new}",new_);
        map_.put("{var}",keyWords_.getKeyWordVar());
        map_.put("{int}",getAliasPrimInteger());
        map_.put("{GrList}",aliasGrList);
        map_.put("{Object}",getAliasObject());
        map_.put("{dispose}",aliasImageDispose);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasPaint);
        stds_.put(aliasPaint, content_);
        getPredefinedInterfacesInitOrder().add(aliasPaint);
        //Paint
        //paint
        return stds_;
    }

    public String getAliasActionListener() {
        return aliasActionListener;
    }

    public void setAliasActionListener(String aliasActionListener) {
        this.aliasActionListener = aliasActionListener;
    }

    public String getAliasActionPerformed() {
        return aliasActionPerformed;
    }

    public void setAliasActionPerformed(String aliasActionPerformed) {
        this.aliasActionPerformed = aliasActionPerformed;
    }

    public String getAliasActionEvent() {
        return aliasActionEvent;
    }

    public void setAliasActionEvent(String aliasActionEvent) {
        this.aliasActionEvent = aliasActionEvent;
    }

    public String getAliasFrame() {
        return aliasFrame;
    }

    public void setAliasFrame(String aliasFrame) {
        this.aliasFrame = aliasFrame;
    }

    public String getAliasPanel() {
        return aliasPanel;
    }

    public void setAliasPanel(String aliasPanel) {
        this.aliasPanel = aliasPanel;
    }

    public String getAliasPanelBorder() {
        return aliasPanelBorder;
    }

    public void setAliasPanelBorder(String _aliasPanelBorder) {
        this.aliasPanelBorder = _aliasPanelBorder;
    }

    public String getAliasPanelBorderNorth() {
        return aliasPanelBorderNorth;
    }

    public void setAliasPanelBorderNorth(String aliasPanelBorderNorth) {
        this.aliasPanelBorderNorth = aliasPanelBorderNorth;
    }

    public String getAliasPanelBorderSouth() {
        return aliasPanelBorderSouth;
    }

    public void setAliasPanelBorderSouth(String aliasPanelBorderSouth) {
        this.aliasPanelBorderSouth = aliasPanelBorderSouth;
    }

    public String getAliasPanelBorderEast() {
        return aliasPanelBorderEast;
    }

    public void setAliasPanelBorderEast(String aliasPanelBorderEast) {
        this.aliasPanelBorderEast = aliasPanelBorderEast;
    }

    public String getAliasPanelBorderWest() {
        return aliasPanelBorderWest;
    }

    public void setAliasPanelBorderWest(String aliasPanelBorderWest) {
        this.aliasPanelBorderWest = aliasPanelBorderWest;
    }

    public String getAliasPanelBorderCenter() {
        return aliasPanelBorderCenter;
    }

    public void setAliasPanelBorderCenter(String aliasPanelBorderCenter) {
        this.aliasPanelBorderCenter = aliasPanelBorderCenter;
    }

    public String getAliasPanelBorderBeforeFirst() {
        return aliasPanelBorderBeforeFirst;
    }

    public void setAliasPanelBorderBeforeFirst(String aliasPanelBorderBeforeFirst) {
        this.aliasPanelBorderBeforeFirst = aliasPanelBorderBeforeFirst;
    }

    public String getAliasPanelBorderBeforeLineBegins() {
        return aliasPanelBorderBeforeLineBegins;
    }

    public void setAliasPanelBorderBeforeLineBegins(String aliasPanelBorderBeforeLineBegins) {
        this.aliasPanelBorderBeforeLineBegins = aliasPanelBorderBeforeLineBegins;
    }

    public String getAliasPanelBorderAfterLast() {
        return aliasPanelBorderAfterLast;
    }

    public void setAliasPanelBorderAfterLast(String aliasPanelBorderAfterLast) {
        this.aliasPanelBorderAfterLast = aliasPanelBorderAfterLast;
    }

    public String getAliasPanelBorderAfterLineEnds() {
        return aliasPanelBorderAfterLineEnds;
    }

    public void setAliasPanelBorderAfterLineEnds(String aliasPanelBorderAfterLineEnds) {
        this.aliasPanelBorderAfterLineEnds = aliasPanelBorderAfterLineEnds;
    }

    public String getAliasButton() {
        return aliasButton;
    }

    public void setAliasButton(String aliasButton) {
        this.aliasButton = aliasButton;
    }

    public String getAliasTextLabel() {
        return aliasTextLabel;
    }

    public void setAliasTextLabel(String aliasTextLabel) {
        this.aliasTextLabel = aliasTextLabel;
    }

    public String getAliasImageLabel() {
        return aliasImageLabel;
    }

    public void setAliasImageLabel(String aliasImageLabel) {
        this.aliasImageLabel = aliasImageLabel;
    }

    public String getAliasGetFont() {
        return aliasGetFont;
    }

    public void setAliasGetFont(String aliasGetFont) {
        this.aliasGetFont = aliasGetFont;
    }

    public String getAliasSetFont() {
        return aliasSetFont;
    }

    public void setAliasSetFont(String aliasSetFont) {
        this.aliasSetFont = aliasSetFont;
    }

    public String getAliasFont() {
        return aliasFont;
    }

    public void setAliasFont(String aliasFont) {
        this.aliasFont = aliasFont;
    }

    public String getAliasFontGetName() {
        return aliasFontGetName;
    }

    public void setAliasFontGetName(String aliasFontGetName) {
        this.aliasFontGetName = aliasFontGetName;
    }

    public String getAliasFontGetSize() {
        return aliasFontGetSize;
    }

    public void setAliasFontGetSize(String aliasFontGetSize) {
        this.aliasFontGetSize = aliasFontGetSize;
    }

    public String getAliasFontIsBold() {
        return aliasFontIsBold;
    }

    public void setAliasFontIsBold(String aliasFontIsBold) {
        this.aliasFontIsBold = aliasFontIsBold;
    }

    public String getAliasFontIsItalic() {
        return aliasFontIsItalic;
    }

    public void setAliasFontIsItalic(String aliasFontIsItalic) {
        this.aliasFontIsItalic = aliasFontIsItalic;
    }

    public String getAliasFontStringWidth() {
        return aliasFontStringWidth;
    }

    public void setAliasFontStringWidth(String aliasFontStringWidth) {
        this.aliasFontStringWidth = aliasFontStringWidth;
    }

    public String getAliasDimension() {
        return aliasDimension;
    }

    public void setAliasDimension(String aliasDimension) {
        this.aliasDimension = aliasDimension;
    }

    public String getAliasDimensionGetHeight() {
        return aliasDimensionGetHeight;
    }

    public void setAliasDimensionGetHeight(String aliasDimensionGetHeight) {
        this.aliasDimensionGetHeight = aliasDimensionGetHeight;
    }

    public String getAliasDimensionGetWidth() {
        return aliasDimensionGetWidth;
    }

    public void setAliasDimensionGetWidth(String aliasDimensionGetWidth) {
        this.aliasDimensionGetWidth = aliasDimensionGetWidth;
    }

    public String getAliasComponent() {
        return aliasComponent;
    }

    public void setAliasComponent(String aliasComponent) {
        this.aliasComponent = aliasComponent;
    }

    public String getAliasComponentSetAutoscrolls() {
        return aliasComponentSetAutoscrolls;
    }

    public void setAliasComponentSetAutoscrolls(String aliasComponentSetAutoscrolls) {
        this.aliasComponentSetAutoscrolls = aliasComponentSetAutoscrolls;
    }

    public String getAliasComponentIsAutoscrolls() {
        return aliasComponentIsAutoscrolls;
    }

    public void setAliasComponentIsAutoscrolls(String aliasComponentIsAutoscrolls) {
        this.aliasComponentIsAutoscrolls = aliasComponentIsAutoscrolls;
    }

    public String getAliasComponentGetWidth() {
        return aliasComponentGetWidth;
    }

    public void setAliasComponentGetWidth(String aliasComponentGetWidth) {
        this.aliasComponentGetWidth = aliasComponentGetWidth;
    }

    public String getAliasComponentGetHeight() {
        return aliasComponentGetHeight;
    }

    public void setAliasComponentGetHeight(String aliasComponentGetHeight) {
        this.aliasComponentGetHeight = aliasComponentGetHeight;
    }

    public String getAliasComponentGetPreferredSize() {
        return aliasComponentGetPreferredSize;
    }

    public void setAliasComponentGetPreferredSize(String aliasComponentGetPreferredSize) {
        this.aliasComponentGetPreferredSize = aliasComponentGetPreferredSize;
    }

    public String getAliasComponentSetPreferredSize() {
        return aliasComponentSetPreferredSize;
    }

    public void setAliasComponentSetPreferredSize(String aliasComponentSetPreferredSize) {
        this.aliasComponentSetPreferredSize = aliasComponentSetPreferredSize;
    }

    public String getAliasComponentIsVisible() {
        return aliasComponentIsVisible;
    }

    public void setAliasComponentIsVisible(String aliasComponentIsVisible) {
        this.aliasComponentIsVisible = aliasComponentIsVisible;
    }

    public String getAliasComponentSetVisible() {
        return aliasComponentSetVisible;
    }

    public void setAliasComponentSetVisible(String aliasComponentSetVisible) {
        this.aliasComponentSetVisible = aliasComponentSetVisible;
    }

    public String getAliasComponentInvokeLater() {
        return aliasComponentInvokeLater;
    }

    public void setAliasComponentInvokeLater(String aliasComponentInvokeLater) {
        this.aliasComponentInvokeLater = aliasComponentInvokeLater;
    }

    public String getAliasImage() {
        return aliasImage;
    }

    public void setAliasImage(String aliasImage) {
        this.aliasImage = aliasImage;
    }

    public String getAliasImageGetWidth() {
        return aliasImageGetWidth;
    }

    public void setAliasImageGetWidth(String aliasImageGetWidth) {
        this.aliasImageGetWidth = aliasImageGetWidth;
    }

    public String getAliasImageGetHeight() {
        return aliasImageGetHeight;
    }

    public void setAliasImageGetHeight(String aliasImageGetHeight) {
        this.aliasImageGetHeight = aliasImageGetHeight;
    }

    public String getAliasImageGet() {
        return aliasImageGet;
    }

    public void setAliasImageGet(String aliasImageGet) {
        this.aliasImageGet = aliasImageGet;
    }

    public String getAliasImageSet() {
        return aliasImageSet;
    }

    public void setAliasImageSet(String aliasImageSet) {
        this.aliasImageSet = aliasImageSet;
    }

    public String getAliasImageIsWithAlpha() {
        return aliasImageIsWithAlpha;
    }

    public void setAliasImageIsWithAlpha(String aliasImageIsWithAlpha) {
        this.aliasImageIsWithAlpha = aliasImageIsWithAlpha;
    }

    public String getAliasImageGetColor() {
        return aliasImageGetColor;
    }

    public void setAliasImageGetColor(String aliasImageGetColor) {
        this.aliasImageGetColor = aliasImageGetColor;
    }

    public String getAliasImageSetColor() {
        return aliasImageSetColor;
    }

    public void setAliasImageSetColor(String aliasImageSetColor) {
        this.aliasImageSetColor = aliasImageSetColor;
    }

    public String getAliasImageGetFont() {
        return aliasImageGetFont;
    }

    public void setAliasImageGetFont(String aliasImageGetFont) {
        this.aliasImageGetFont = aliasImageGetFont;
    }

    public String getAliasImageSetFont() {
        return aliasImageSetFont;
    }

    public void setAliasImageSetFont(String aliasImageSetFont) {
        this.aliasImageSetFont = aliasImageSetFont;
    }

    public String getAliasImageDraw() {
        return aliasImageDraw;
    }

    public void setAliasImageDraw(String aliasImageDraw) {
        this.aliasImageDraw = aliasImageDraw;
    }

    public String getAliasImageDrawLine() {
        return aliasImageDrawLine;
    }

    public void setAliasImageDrawLine(String aliasImageDrawLine) {
        this.aliasImageDrawLine = aliasImageDrawLine;
    }

    public String getAliasImageDrawRect() {
        return aliasImageDrawRect;
    }

    public void setAliasImageDrawRect(String aliasImageDrawRect) {
        this.aliasImageDrawRect = aliasImageDrawRect;
    }

    public String getAliasImageDrawOval() {
        return aliasImageDrawOval;
    }

    public void setAliasImageDrawOval(String aliasImageDrawOval) {
        this.aliasImageDrawOval = aliasImageDrawOval;
    }

    public String getAliasImageFillRect() {
        return aliasImageFillRect;
    }

    public void setAliasImageFillRect(String aliasImageFillRect) {
        this.aliasImageFillRect = aliasImageFillRect;
    }

    public String getAliasImageFillOval() {
        return aliasImageFillOval;
    }

    public void setAliasImageFillOval(String aliasImageFillOval) {
        this.aliasImageFillOval = aliasImageFillOval;
    }

    public String getAliasImageDrawPolygon() {
        return aliasImageDrawPolygon;
    }

    public void setAliasImageDrawPolygon(String aliasImageDrawPolygon) {
        this.aliasImageDrawPolygon = aliasImageDrawPolygon;
    }

    public String getAliasImageFillPolygon() {
        return aliasImageFillPolygon;
    }

    public void setAliasImageFillPolygon(String aliasImageFillPolygon) {
        this.aliasImageFillPolygon = aliasImageFillPolygon;
    }

    public String getAliasImageDispose() {
        return aliasImageDispose;
    }

    public void setAliasImageDispose(String aliasImageDispose) {
        this.aliasImageDispose = aliasImageDispose;
    }

    public String getAliasColor() {
        return aliasColor;
    }

    public void setAliasColor(String aliasColor) {
        this.aliasColor = aliasColor;
    }

    public String getAliasColorRed() {
        return aliasColorRed;
    }

    public void setAliasColorRed(String aliasColorRed) {
        this.aliasColorRed = aliasColorRed;
    }

    public String getAliasColorGreen() {
        return aliasColorGreen;
    }

    public void setAliasColorGreen(String aliasColorGreen) {
        this.aliasColorGreen = aliasColorGreen;
    }

    public String getAliasColorBlue() {
        return aliasColorBlue;
    }

    public void setAliasColorBlue(String aliasColorBlue) {
        this.aliasColorBlue = aliasColorBlue;
    }

    public String getAliasColorAlpha() {
        return aliasColorAlpha;
    }

    public void setAliasColorAlpha(String aliasColorAlpha) {
        this.aliasColorAlpha = aliasColorAlpha;
    }

    public String getAliasColorIsTransparent() {
        return aliasColorIsTransparent;
    }

    public void setAliasColorIsTransparent(String aliasColorIsTransparent) {
        this.aliasColorIsTransparent = aliasColorIsTransparent;
    }

    public String getAliasComponentSetPaint() {
        return aliasComponentSetPaint;
    }

    public void setAliasComponentSetPaint(String aliasComponentSetPaint) {
        this.aliasComponentSetPaint = aliasComponentSetPaint;
    }

    public String getAliasComponentGetPaint() {
        return aliasComponentGetPaint;
    }

    public void setAliasComponentGetPaint(String aliasComponentGetPaint) {
        this.aliasComponentGetPaint = aliasComponentGetPaint;
    }

    public String getAliasComponentRepaint() {
        return aliasComponentRepaint;
    }

    public void setAliasComponentRepaint(String aliasComponentRepaint) {
        this.aliasComponentRepaint = aliasComponentRepaint;
    }

    public String getAliasSetContent() {
        return aliasSetContent;
    }

    public void setAliasSetContent(String aliasSetContent) {
        this.aliasSetContent = aliasSetContent;
    }

    public String getAliasAddCompo() {
        return aliasAddCompo;
    }

    public void setAliasAddCompo(String aliasAddCompo) {
        this.aliasAddCompo = aliasAddCompo;
    }

    public String getAliasRemoveAll() {
        return aliasRemoveAll;
    }

    public void setAliasRemoveAll(String _aliasRemoveAll) {
        aliasRemoveAll = _aliasRemoveAll;
    }

    public String getAliasGetParentCompo() {
        return aliasGetParentCompo;
    }

    public void setAliasGetParentCompo(String aliasGetParentCompo) {
        this.aliasGetParentCompo = aliasGetParentCompo;
    }

    public String getAliasGetNextCompo() {
        return aliasGetNextCompo;
    }

    public void setAliasGetNextCompo(String aliasGetNextCompo) {
        this.aliasGetNextCompo = aliasGetNextCompo;
    }

    public String getAliasGetIndexCompo() {
        return aliasGetIndexCompo;
    }

    public void setAliasGetIndexCompo(String aliasGetIndexCompo) {
        this.aliasGetIndexCompo = aliasGetIndexCompo;
    }

    public String getAliasPanelFlow() {
        return aliasPanelFlow;
    }

    public void setAliasPanelFlow(String aliasPanelFlow) {
        this.aliasPanelFlow = aliasPanelFlow;
    }

    public String getAliasPanelAbsolute() {
        return aliasPanelAbsolute;
    }

    public void setAliasPanelAbsolute(String aliasPanelAbsolute) {
        this.aliasPanelAbsolute = aliasPanelAbsolute;
    }

    public String getAliasPanelGrid() {
        return aliasPanelGrid;
    }

    public void setAliasPanelGrid(String aliasPanelGrid) {
        this.aliasPanelGrid = aliasPanelGrid;
    }

    public String getAliasPanelPageBox() {
        return aliasPanelPageBox;
    }

    public void setAliasPanelPageBox(String aliasPanelPageBox) {
        this.aliasPanelPageBox = aliasPanelPageBox;
    }

    public String getAliasAddListener() {
        return aliasAddListener;
    }

    public void setAliasAddListener(String aliasAddListener) {
        this.aliasAddListener = aliasAddListener;
    }

    public String getAliasAddWindowListener() {
        return aliasAddWindowListener;
    }

    public void setAliasAddWindowListener(String aliasAddWindowListener) {
        this.aliasAddWindowListener = aliasAddWindowListener;
    }

    public String getAliasSetLabelText() {
        return aliasSetLabelText;
    }

    public void setAliasSetLabelText(String aliasSetLabelText) {
        this.aliasSetLabelText = aliasSetLabelText;
    }

    public String getAliasSetLabelImage() {
        return aliasSetLabelImage;
    }

    public void setAliasSetLabelImage(String aliasSetLabelImage) {
        this.aliasSetLabelImage = aliasSetLabelImage;
    }

    public String getAliasPaint() {
        return aliasPaint;
    }

    public void setAliasPaint(String aliasPaint) {
        this.aliasPaint = aliasPaint;
    }

    public String getAliasPaintMethod() {
        return aliasPaintMethod;
    }

    public void setAliasPaintMethod(String aliasPaintMethod) {
        this.aliasPaintMethod = aliasPaintMethod;
    }

    public String getAliasPaintAdd() {
        return aliasPaintAdd;
    }

    public void setAliasPaintAdd(String aliasPaintAdd) {
        this.aliasPaintAdd = aliasPaintAdd;
    }

    public String getAliasPaintRefresh() {
        return aliasPaintRefresh;
    }

    public void setAliasPaintRefresh(String aliasPaintRefresh) {
        this.aliasPaintRefresh = aliasPaintRefresh;
    }

    public String getAliasRemoveCompo() {
        return aliasRemoveCompo;
    }

    public void setAliasRemoveCompo(String aliasRemoveCompo) {
        this.aliasRemoveCompo = aliasRemoveCompo;
    }

    public String getAliasCount() {
        return aliasCount;
    }

    public void setAliasCount(String aliasCount) {
        this.aliasCount = aliasCount;
    }

    public String getAliasScrollPane() {
        return aliasScrollPane;
    }

    public void setAliasScrollPane(String aliasScrollPane) {
        this.aliasScrollPane = aliasScrollPane;
    }

    public String getAliasScrollPaneGetView() {
        return aliasScrollPaneGetView;
    }

    public void setAliasScrollPaneGetView(String aliasScrollPaneGetView) {
        this.aliasScrollPaneGetView = aliasScrollPaneGetView;
    }

    public String getAliasScrollPaneSetView() {
        return aliasScrollPaneSetView;
    }

    public void setAliasScrollPaneSetView(String aliasScrollPaneSetView) {
        this.aliasScrollPaneSetView = aliasScrollPaneSetView;
    }

    public String getAliasPack() {
        return aliasPack;
    }

    public void setAliasPack(String aliasPack) {
        this.aliasPack = aliasPack;
    }

    public String getAliasDispose() {
        return aliasDispose;
    }

    public void setAliasDispose(String aliasDispose) {
        this.aliasDispose = aliasDispose;
    }

    public String getAliasSetVisible() {
        return aliasSetVisible;
    }

    public void setAliasSetVisible(String aliasSetVisible) {
        this.aliasSetVisible = aliasSetVisible;
    }

    public String getAliasWindow() {
        return aliasWindow;
    }

    public void setAliasWindow(String aliasWindow) {
        this.aliasWindow = aliasWindow;
    }

    public String getAliasArgs() {
        return aliasArgs;
    }

    public void setAliasArgs(String aliasArgs) {
        this.aliasArgs = aliasArgs;
    }

    public String getAliasMouseListener() {
        return aliasMouseListener;
    }

    public void setAliasMouseListener(String aliasMouseListener) {
        this.aliasMouseListener = aliasMouseListener;
    }

    public String getAliasMouseClicked() {
        return aliasMouseClicked;
    }

    public void setAliasMouseClicked(String aliasMouseClicked) {
        this.aliasMouseClicked = aliasMouseClicked;
    }

    public String getAliasMousePressed() {
        return aliasMousePressed;
    }

    public void setAliasMousePressed(String aliasMousePressed) {
        this.aliasMousePressed = aliasMousePressed;
    }

    public String getAliasMouseReleased() {
        return aliasMouseReleased;
    }

    public void setAliasMouseReleased(String aliasMouseReleased) {
        this.aliasMouseReleased = aliasMouseReleased;
    }

    public String getAliasMouseEntered() {
        return aliasMouseEntered;
    }

    public void setAliasMouseEntered(String aliasMouseEntered) {
        this.aliasMouseEntered = aliasMouseEntered;
    }

    public String getAliasMouseExited() {
        return aliasMouseExited;
    }

    public void setAliasMouseExited(String aliasMouseExited) {
        this.aliasMouseExited = aliasMouseExited;
    }

    public String getAliasMouseEvent() {
        return aliasMouseEvent;
    }

    public void setAliasMouseEvent(String aliasMouseEvent) {
        this.aliasMouseEvent = aliasMouseEvent;
    }

    public String getAliasGetMouseEventFirst() {
        return aliasGetMouseEventFirst;
    }

    public void setAliasGetMouseEventFirst(String aliasGetMouseEventFirst) {
        this.aliasGetMouseEventFirst = aliasGetMouseEventFirst;
    }

    public String getAliasGetMouseEventSecond() {
        return aliasGetMouseEventSecond;
    }

    public void setAliasGetMouseEventSecond(String aliasGetMouseEventSecond) {
        this.aliasGetMouseEventSecond = aliasGetMouseEventSecond;
    }

    public String getAliasWindowListener() {
        return aliasWindowListener;
    }

    public void setAliasWindowListener(String aliasWindowListener) {
        this.aliasWindowListener = aliasWindowListener;
    }

    public String getAliasWindowOpened() {
        return aliasWindowOpened;
    }

    public void setAliasWindowOpened(String aliasWindowOpened) {
        this.aliasWindowOpened = aliasWindowOpened;
    }

    public String getAliasWindowClosed() {
        return aliasWindowClosed;
    }

    public void setAliasWindowClosed(String aliasWindowClosed) {
        this.aliasWindowClosed = aliasWindowClosed;
    }

    public String getAliasWindowClosing() {
        return aliasWindowClosing;
    }

    public void setAliasWindowClosing(String aliasWindowClosing) {
        this.aliasWindowClosing = aliasWindowClosing;
    }

    public String getAliasWindowIconified() {
        return aliasWindowIconified;
    }

    public void setAliasWindowIconified(String aliasWindowIconified) {
        this.aliasWindowIconified = aliasWindowIconified;
    }

    public String getAliasWindowDeiconified() {
        return aliasWindowDeiconified;
    }

    public void setAliasWindowDeiconified(String aliasWindowDeiconified) {
        this.aliasWindowDeiconified = aliasWindowDeiconified;
    }

    public String getAliasWindowActivated() {
        return aliasWindowActivated;
    }

    public void setAliasWindowActivated(String aliasWindowActivated) {
        this.aliasWindowActivated = aliasWindowActivated;
    }

    public String getAliasWindowDeactivated() {
        return aliasWindowDeactivated;
    }

    public void setAliasWindowDeactivated(String aliasWindowDeactivated) {
        this.aliasWindowDeactivated = aliasWindowDeactivated;
    }

    public String getAliasWindowEvent() {
        return aliasWindowEvent;
    }

    public void setAliasWindowEvent(String aliasWindowEvent) {
        this.aliasWindowEvent = aliasWindowEvent;
    }

    public String getAliasListSelection() {
        return aliasListSelection;
    }

    public void setAliasListSelection(String aliasListSelection) {
        this.aliasListSelection = aliasListSelection;
    }

    public String getAliasValueChanged() {
        return aliasValueChanged;
    }

    public void setAliasValueChanged(String aliasValueChanged) {
        this.aliasValueChanged = aliasValueChanged;
    }

    public String getAliasInput() {
        return aliasInput;
    }

    public void setAliasInput(String _aliasInput) {
        aliasInput = _aliasInput;
    }

    public String getAliasInputIsEnabled() {
        return aliasInputIsEnabled;
    }

    public void setAliasInputIsEnabled(String _aliasInputIsEnabled) {
        aliasInputIsEnabled = _aliasInputIsEnabled;
    }

    public String getAliasInputSetEnabled() {
        return aliasInputSetEnabled;
    }

    public void setAliasInputSetEnabled(String _aliasInputSetEnabled) {
        aliasInputSetEnabled = _aliasInputSetEnabled;
    }

    public String getAliasRender() {
        return aliasRender;
    }

    public void setAliasRender(String aliasRender) {
        this.aliasRender = aliasRender;
    }

    public String getAliasRenderGetHeight() {
        return aliasRenderGetHeight;
    }

    public void setAliasRenderGetHeight(String aliasRenderGetHeight) {
        this.aliasRenderGetHeight = aliasRenderGetHeight;
    }

    public String getAliasRenderSetHeight() {
        return aliasRenderSetHeight;
    }

    public void setAliasRenderSetHeight(String aliasRenderSetHeight) {
        this.aliasRenderSetHeight = aliasRenderSetHeight;
    }

    public String getAliasRenderGetWidth() {
        return aliasRenderGetWidth;
    }

    public void setAliasRenderGetWidth(String aliasRenderGetWidth) {
        this.aliasRenderGetWidth = aliasRenderGetWidth;
    }

    public String getAliasRenderSetWidth() {
        return aliasRenderSetWidth;
    }

    public void setAliasRenderSetWidth(String aliasRenderSetWidth) {
        this.aliasRenderSetWidth = aliasRenderSetWidth;
    }

    public String getAliasRenderGetPaint() {
        return aliasRenderGetPaint;
    }

    public void setAliasRenderGetPaint(String aliasRenderGetPaint) {
        this.aliasRenderGetPaint = aliasRenderGetPaint;
    }

    public String getAliasRenderSetPaint() {
        return aliasRenderSetPaint;
    }

    public void setAliasRenderSetPaint(String aliasRenderSetPaint) {
        this.aliasRenderSetPaint = aliasRenderSetPaint;
    }

    public String getAliasGrList() {
        return aliasGrList;
    }

    public void setAliasGrList(String aliasGrList) {
        this.aliasGrList = aliasGrList;
    }

    public String getAliasGrListAdd() {
        return aliasGrListAdd;
    }

    public void setAliasGrListAdd(String aliasGrListAdd) {
        this.aliasGrListAdd = aliasGrListAdd;
    }

    public String getAliasGrListSet() {
        return aliasGrListSet;
    }

    public void setAliasGrListSet(String aliasGrListSet) {
        this.aliasGrListSet = aliasGrListSet;
    }

    public String getAliasGrListGetListView() {
        return aliasGrListGetListView;
    }

    public void setAliasGrListGetListView(String aliasGrListGetListView) {
        this.aliasGrListGetListView = aliasGrListGetListView;
    }

    public String getAliasGrListGetSelectedIndexes() {
        return aliasGrListGetSelectedIndexes;
    }

    public void setAliasGrListGetSelectedIndexes(String aliasGrListGetSelectedIndexes) {
        this.aliasGrListGetSelectedIndexes = aliasGrListGetSelectedIndexes;
    }

    public String getAliasGrListSetSelectedIndexes() {
        return aliasGrListSetSelectedIndexes;
    }

    public void setAliasGrListSetSelectedIndexes(String aliasGrListSetSelectedIndexes) {
        this.aliasGrListSetSelectedIndexes = aliasGrListSetSelectedIndexes;
    }

    public String getAliasGrListClearSelection() {
        return aliasGrListClearSelection;
    }

    public void setAliasGrListClearSelection(String aliasGrListClearSelection) {
        this.aliasGrListClearSelection = aliasGrListClearSelection;
    }

    public String getAliasGrListUpdateGraphics() {
        return aliasGrListUpdateGraphics;
    }

    public void setAliasGrListUpdateGraphics(String aliasGrListUpdateGraphics) {
        this.aliasGrListUpdateGraphics = aliasGrListUpdateGraphics;
    }

    public String getAliasGrListGetRender() {
        return aliasGrListGetRender;
    }

    public void setAliasGrListGetRender(String aliasGrListGetRender) {
        this.aliasGrListGetRender = aliasGrListGetRender;
    }

    public String getAliasGrListSetRender() {
        return aliasGrListSetRender;
    }

    public void setAliasGrListSetRender(String aliasGrListSetRender) {
        this.aliasGrListSetRender = aliasGrListSetRender;
    }

    public String getAliasGrListGetSelection() {
        return aliasGrListGetSelection;
    }

    public void setAliasGrListGetSelection(String aliasGrListGetSelection) {
        this.aliasGrListGetSelection = aliasGrListGetSelection;
    }

    public String getAliasGrListSetSelection() {
        return aliasGrListSetSelection;
    }

    public void setAliasGrListSetSelection(String aliasGrListSetSelection) {
        this.aliasGrListSetSelection = aliasGrListSetSelection;
    }

    public String getAliasGrListGetVisibleRowCount() {
        return aliasGrListGetVisibleRowCount;
    }

    public void setAliasGrListGetVisibleRowCount(String aliasGrListGetVisibleRowCount) {
        this.aliasGrListGetVisibleRowCount = aliasGrListGetVisibleRowCount;
    }

    public String getAliasGrListSetVisibleRowCount() {
        return aliasGrListSetVisibleRowCount;
    }

    public void setAliasGrListSetVisibleRowCount(String aliasGrListSetVisibleRowCount) {
        this.aliasGrListSetVisibleRowCount = aliasGrListSetVisibleRowCount;
    }

    public String getAliasGrListClear() {
        return aliasGrListClear;
    }

    public void setAliasGrListClear(String aliasGrListClear) {
        this.aliasGrListClear = aliasGrListClear;
    }

    public String getAliasGrListRemove() {
        return aliasGrListRemove;
    }

    public void setAliasGrListRemove(String aliasGrListRemove) {
        this.aliasGrListRemove = aliasGrListRemove;
    }

    public String getAliasCombo() {
        return aliasCombo;
    }

    public void setAliasCombo(String aliasCombo) {
        this.aliasCombo = aliasCombo;
    }

    public String getAliasComboGetSelectedItem() {
        return aliasComboGetSelectedItem;
    }

    public void setAliasComboGetSelectedItem(String aliasComboGetSelectedItem) {
        this.aliasComboGetSelectedItem = aliasComboGetSelectedItem;
    }

    public String getAliasComboAddItem() {
        return aliasComboAddItem;
    }

    public void setAliasComboAddItem(String aliasComboAddItem) {
        this.aliasComboAddItem = aliasComboAddItem;
    }

    public String getAliasComboGetItemCount() {
        return aliasComboGetItemCount;
    }

    public void setAliasComboGetItemCount(String aliasComboGetItemCount) {
        this.aliasComboGetItemCount = aliasComboGetItemCount;
    }

    public String getAliasComboSelectItem() {
        return aliasComboSelectItem;
    }

    public void setAliasComboSelectItem(String aliasComboSelectItem) {
        this.aliasComboSelectItem = aliasComboSelectItem;
    }

    public String getAliasComboSetListener() {
        return aliasComboSetListener;
    }

    public void setAliasComboSetListener(String aliasComboSetListener) {
        this.aliasComboSetListener = aliasComboSetListener;
    }

    public String getAliasComboGetListener() {
        return aliasComboGetListener;
    }

    public void setAliasComboGetListener(String aliasComboGetListener) {
        this.aliasComboGetListener = aliasComboGetListener;
    }

    public String getAliasComboGetSelectedIndexes() {
        return aliasComboGetSelectedIndexes;
    }

    public void setAliasComboGetSelectedIndexes(String aliasComboGetSelectedIndexes) {
        this.aliasComboGetSelectedIndexes = aliasComboGetSelectedIndexes;
    }

    public String getAliasComboGetSelectedIndex() {
        return aliasComboGetSelectedIndex;
    }

    public void setAliasComboGetSelectedIndex(String aliasComboGetSelectedIndex) {
        this.aliasComboGetSelectedIndex = aliasComboGetSelectedIndex;
    }

    public String getAliasComboRemoveAllItems() {
        return aliasComboRemoveAllItems;
    }

    public void setAliasComboRemoveAllItems(String aliasComboRemoveAllItems) {
        this.aliasComboRemoveAllItems = aliasComboRemoveAllItems;
    }

    public String getAliasComboRemoveItem() {
        return aliasComboRemoveItem;
    }

    public void setAliasComboRemoveItem(String aliasComboRemoveItem) {
        this.aliasComboRemoveItem = aliasComboRemoveItem;
    }

    public String getAliasButtonGroup() {
        return aliasButtonGroup;
    }

    public void setAliasButtonGroup(String aliasButtonGroup) {
        this.aliasButtonGroup = aliasButtonGroup;
    }

    public String getAliasButtonGroupAdd() {
        return aliasButtonGroupAdd;
    }

    public void setAliasButtonGroupAdd(String aliasButtonGroupAdd) {
        this.aliasButtonGroupAdd = aliasButtonGroupAdd;
    }

    public String getAliasRadio() {
        return aliasRadio;
    }

    public void setAliasRadio(String aliasRadio) {
        this.aliasRadio = aliasRadio;
    }

    public String getAliasRadioIsSelected() {
        return aliasRadioIsSelected;
    }

    public void setAliasRadioIsSelected(String aliasRadioIsSelected) {
        this.aliasRadioIsSelected = aliasRadioIsSelected;
    }

    public String getAliasRadioSetSelected() {
        return aliasRadioSetSelected;
    }

    public void setAliasRadioSetSelected(String aliasRadioSetSelected) {
        this.aliasRadioSetSelected = aliasRadioSetSelected;
    }

    public String getAliasRadioGetText() {
        return aliasRadioGetText;
    }

    public void setAliasRadioGetText(String aliasRadioGetText) {
        this.aliasRadioGetText = aliasRadioGetText;
    }

    public String getAliasRadioSetText() {
        return aliasRadioSetText;
    }

    public void setAliasRadioSetText(String aliasRadioSetText) {
        this.aliasRadioSetText = aliasRadioSetText;
    }

    public String getAliasPopupMenu() {
        return aliasPopupMenu;
    }

    public void setAliasPopupMenu(String aliasPopupMenu) {
        this.aliasPopupMenu = aliasPopupMenu;
    }

    public String getAliasPopupMenuAdd() {
        return aliasPopupMenuAdd;
    }

    public void setAliasPopupMenuAdd(String aliasPopupMenuAdd) {
        this.aliasPopupMenuAdd = aliasPopupMenuAdd;
    }

    public String getAliasPopupMenuShow() {
        return aliasPopupMenuShow;
    }

    public void setAliasPopupMenuShow(String aliasPopupMenuShow) {
        this.aliasPopupMenuShow = aliasPopupMenuShow;
    }

    public String getAliasTextField() {
        return aliasTextField;
    }

    public void setAliasTextField(String aliasTextField) {
        this.aliasTextField = aliasTextField;
    }

    public String getAliasTextFieldGetText() {
        return aliasTextFieldGetText;
    }

    public void setAliasTextFieldGetText(String aliasTextFieldGetText) {
        this.aliasTextFieldGetText = aliasTextFieldGetText;
    }

    public String getAliasTextFieldSetText() {
        return aliasTextFieldSetText;
    }

    public void setAliasTextFieldSetText(String aliasTextFieldSetText) {
        this.aliasTextFieldSetText = aliasTextFieldSetText;
    }

    public String getAliasTextFieldAuto() {
        return aliasTextFieldAuto;
    }

    public void setAliasTextFieldAuto(String aliasTextFieldAuto) {
        this.aliasTextFieldAuto = aliasTextFieldAuto;
    }

    public String getAliasTextFieldAddAction() {
        return aliasTextFieldAddAction;
    }

    public void setAliasTextFieldAddAction(String aliasTextFieldAddAction) {
        this.aliasTextFieldAddAction = aliasTextFieldAddAction;
    }

    public String getAliasTextFieldAddDocument() {
        return aliasTextFieldAddDocument;
    }

    public void setAliasTextFieldAddDocument(String aliasTextFieldAddDocument) {
        this.aliasTextFieldAddDocument = aliasTextFieldAddDocument;
    }

    public String getAliasTextFieldAddPopup() {
        return aliasTextFieldAddPopup;
    }

    public void setAliasTextFieldAddPopup(String aliasTextFieldAddPopup) {
        this.aliasTextFieldAddPopup = aliasTextFieldAddPopup;
    }

    public String getAliasTextArea() {
        return aliasTextArea;
    }

    public void setAliasTextArea(String aliasTextArea) {
        this.aliasTextArea = aliasTextArea;
    }

    public String getAliasTextAreaGetText() {
        return aliasTextAreaGetText;
    }

    public void setAliasTextAreaGetText(String aliasTextAreaGetText) {
        this.aliasTextAreaGetText = aliasTextAreaGetText;
    }

    public String getAliasTextAreaSetText() {
        return aliasTextAreaSetText;
    }

    public void setAliasTextAreaSetText(String aliasTextAreaSetText) {
        this.aliasTextAreaSetText = aliasTextAreaSetText;
    }

    public String getAliasTextAreaGetTabSize() {
        return aliasTextAreaGetTabSize;
    }

    public void setAliasTextAreaGetTabSize(String aliasTextAreaGetTabSize) {
        this.aliasTextAreaGetTabSize = aliasTextAreaGetTabSize;
    }

    public String getAliasTextAreaSetTabSize() {
        return aliasTextAreaSetTabSize;
    }

    public void setAliasTextAreaSetTabSize(String aliasTextAreaSetTabSize) {
        this.aliasTextAreaSetTabSize = aliasTextAreaSetTabSize;
    }

    public String getAliasTextAreaAppend() {
        return aliasTextAreaAppend;
    }

    public void setAliasTextAreaAppend(String aliasTextAreaAppend) {
        this.aliasTextAreaAppend = aliasTextAreaAppend;
    }

    public String getAliasTextAreaInsert() {
        return aliasTextAreaInsert;
    }

    public void setAliasTextAreaInsert(String aliasTextAreaInsert) {
        this.aliasTextAreaInsert = aliasTextAreaInsert;
    }

    public String getAliasTextAreaReplaceRange() {
        return aliasTextAreaReplaceRange;
    }

    public void setAliasTextAreaReplaceRange(String aliasTextAreaReplaceRange) {
        this.aliasTextAreaReplaceRange = aliasTextAreaReplaceRange;
    }

    public String getAliasTextAreaReplaceSelection() {
        return aliasTextAreaReplaceSelection;
    }

    public void setAliasTextAreaReplaceSelection(String aliasTextAreaReplaceSelection) {
        this.aliasTextAreaReplaceSelection = aliasTextAreaReplaceSelection;
    }

    public String getAliasTextAreaGetSelectedText() {
        return aliasTextAreaGetSelectedText;
    }

    public void setAliasTextAreaGetSelectedText(String aliasTextAreaGetSelectedText) {
        this.aliasTextAreaGetSelectedText = aliasTextAreaGetSelectedText;
    }

    public String getAliasTextAreaSetSelectionStart() {
        return aliasTextAreaSetSelectionStart;
    }

    public void setAliasTextAreaSetSelectionStart(String aliasTextAreaSetSelectionStart) {
        this.aliasTextAreaSetSelectionStart = aliasTextAreaSetSelectionStart;
    }

    public String getAliasTextAreaSetSelectionEnd() {
        return aliasTextAreaSetSelectionEnd;
    }

    public void setAliasTextAreaSetSelectionEnd(String aliasTextAreaSetSelectionEnd) {
        this.aliasTextAreaSetSelectionEnd = aliasTextAreaSetSelectionEnd;
    }

    public String getAliasTextAreaSelect() {
        return aliasTextAreaSelect;
    }

    public void setAliasTextAreaSelect(String aliasTextAreaSelect) {
        this.aliasTextAreaSelect = aliasTextAreaSelect;
    }

    public String getAliasTextAreaSelectAll() {
        return aliasTextAreaSelectAll;
    }

    public void setAliasTextAreaSelectAll(String aliasTextAreaSelectAll) {
        this.aliasTextAreaSelectAll = aliasTextAreaSelectAll;
    }

    public String getAliasCheckBox() {
        return aliasCheckBox;
    }

    public void setAliasCheckBox(String aliasCheckBox) {
        this.aliasCheckBox = aliasCheckBox;
    }

    public String getAliasCheckBoxGetText() {
        return aliasCheckBoxGetText;
    }

    public void setAliasCheckBoxGetText(String aliasCheckBoxGetText) {
        this.aliasCheckBoxGetText = aliasCheckBoxGetText;
    }

    public String getAliasCheckBoxSetText() {
        return aliasCheckBoxSetText;
    }

    public void setAliasCheckBoxSetText(String aliasCheckBoxSetText) {
        this.aliasCheckBoxSetText = aliasCheckBoxSetText;
    }

    public String getAliasCheckBoxIsSelected() {
        return aliasCheckBoxIsSelected;
    }

    public void setAliasCheckBoxIsSelected(String aliasCheckBoxIsSelected) {
        this.aliasCheckBoxIsSelected = aliasCheckBoxIsSelected;
    }

    public String getAliasCheckBoxSetSelected() {
        return aliasCheckBoxSetSelected;
    }

    public void setAliasCheckBoxSetSelected(String aliasCheckBoxSetSelected) {
        this.aliasCheckBoxSetSelected = aliasCheckBoxSetSelected;
    }

    public String getAliasCheckBoxAddAction() {
        return aliasCheckBoxAddAction;
    }

    public void setAliasCheckBoxAddAction(String aliasCheckBoxAddAction) {
        this.aliasCheckBoxAddAction = aliasCheckBoxAddAction;
    }

    public String getAliasSpinner() {
        return aliasSpinner;
    }

    public void setAliasSpinner(String aliasSpinner) {
        this.aliasSpinner = aliasSpinner;
    }

    public String getAliasSpinnerGetValue() {
        return aliasSpinnerGetValue;
    }

    public void setAliasSpinnerGetValue(String aliasSpinnerGetValue) {
        this.aliasSpinnerGetValue = aliasSpinnerGetValue;
    }

    public String getAliasSpinnerSetValue() {
        return aliasSpinnerSetValue;
    }

    public void setAliasSpinnerSetValue(String aliasSpinnerSetValue) {
        this.aliasSpinnerSetValue = aliasSpinnerSetValue;
    }

    public String getAliasSpinnerGetMax() {
        return aliasSpinnerGetMax;
    }

    public void setAliasSpinnerGetMax(String aliasSpinnerGetMax) {
        this.aliasSpinnerGetMax = aliasSpinnerGetMax;
    }

    public String getAliasSpinnerSetMax() {
        return aliasSpinnerSetMax;
    }

    public void setAliasSpinnerSetMax(String aliasSpinnerSetMax) {
        this.aliasSpinnerSetMax = aliasSpinnerSetMax;
    }

    public String getAliasSpinnerGetMin() {
        return aliasSpinnerGetMin;
    }

    public void setAliasSpinnerGetMin(String aliasSpinnerGetMin) {
        this.aliasSpinnerGetMin = aliasSpinnerGetMin;
    }

    public String getAliasSpinnerSetMin() {
        return aliasSpinnerSetMin;
    }

    public void setAliasSpinnerSetMin(String aliasSpinnerSetMin) {
        this.aliasSpinnerSetMin = aliasSpinnerSetMin;
    }

    public String getAliasSpinnerGetStep() {
        return aliasSpinnerGetStep;
    }

    public void setAliasSpinnerGetStep(String aliasSpinnerGetStep) {
        this.aliasSpinnerGetStep = aliasSpinnerGetStep;
    }

    public String getAliasSpinnerSetStep() {
        return aliasSpinnerSetStep;
    }

    public void setAliasSpinnerSetStep(String aliasSpinnerSetStep) {
        this.aliasSpinnerSetStep = aliasSpinnerSetStep;
    }

    public String getAliasSpinnerAddAction() {
        return aliasSpinnerAddAction;
    }

    public void setAliasSpinnerAddAction(String aliasSpinnerAddAction) {
        this.aliasSpinnerAddAction = aliasSpinnerAddAction;
    }

    public String getAliasSlider() {
        return aliasSlider;
    }

    public void setAliasSlider(String aliasSlider) {
        this.aliasSlider = aliasSlider;
    }

    public String getAliasSliderGetValue() {
        return aliasSliderGetValue;
    }

    public void setAliasSliderGetValue(String aliasSliderGetValue) {
        this.aliasSliderGetValue = aliasSliderGetValue;
    }

    public String getAliasSliderSetValue() {
        return aliasSliderSetValue;
    }

    public void setAliasSliderSetValue(String aliasSliderSetValue) {
        this.aliasSliderSetValue = aliasSliderSetValue;
    }

    public String getAliasSliderGetMax() {
        return aliasSliderGetMax;
    }

    public void setAliasSliderGetMax(String aliasSliderGetMax) {
        this.aliasSliderGetMax = aliasSliderGetMax;
    }

    public String getAliasSliderSetMax() {
        return aliasSliderSetMax;
    }

    public void setAliasSliderSetMax(String aliasSliderSetMax) {
        this.aliasSliderSetMax = aliasSliderSetMax;
    }

    public String getAliasSliderGetMin() {
        return aliasSliderGetMin;
    }

    public void setAliasSliderGetMin(String aliasSliderGetMin) {
        this.aliasSliderGetMin = aliasSliderGetMin;
    }

    public String getAliasSliderSetMin() {
        return aliasSliderSetMin;
    }

    public void setAliasSliderSetMin(String aliasSliderSetMin) {
        this.aliasSliderSetMin = aliasSliderSetMin;
    }

    public String getAliasSliderAddAction() {
        return aliasSliderAddAction;
    }

    public void setAliasSliderAddAction(String aliasSliderAddAction) {
        this.aliasSliderAddAction = aliasSliderAddAction;
    }

    public void otherAlias(String _lang) {
        super.otherAlias(_lang);
        if (StringList.quickEq(_lang, "en")) {
            setAliasActionEvent("$core.ActionEvent");
            setAliasActionListener("$core.ActionListener");
            setAliasActionPerformed("actionPerformed");
            setAliasMouseEvent("$core.MouseEvent");
            setAliasMouseListener("$core.MouseListener");
            setAliasMouseClicked("mouseClicked");
            setAliasMousePressed("mousePressed");
            setAliasMouseReleased("mouseReleased");
            setAliasMouseEntered("mouseEntered");
            setAliasMouseExited("mouseExited");
            setAliasGetMouseEventFirst("x");
            setAliasGetMouseEventSecond("y");
            setAliasWindowListener("$core.WindowListener");
            setAliasWindowEvent("$core.WindowEvent");
            setAliasWindowActivated("activated");
            setAliasWindowDeactivated("deactivated");
            setAliasWindowOpened("opened");
            setAliasWindowIconified("iconified");
            setAliasWindowDeiconified("deiconified");
            setAliasWindowClosed("closed");
            setAliasWindowClosing("closing");
            setAliasAddWindowListener("addWindowListener");
            setAliasListSelection("$core.ListSelection");
            setAliasValueChanged("valueChanged");
            setAliasFrame("$core.Frame");
            setAliasPanel("$core.Panel");
            setAliasPanelBorder("$core.PanelBorder");
            setAliasPanelBorderNorth("NORTH");
            setAliasPanelBorderWest("WEST");
            setAliasPanelBorderSouth("SOUTH");
            setAliasPanelBorderEast("EAST");
            setAliasPanelBorderCenter("CENTER");
            setAliasPanelBorderBeforeFirst("BEFORE_FIRST_LINE");
            setAliasPanelBorderAfterLast("AFTER_LAST_LINE");
            setAliasPanelBorderBeforeLineBegins("BEFORE_LINE_BEGINS");
            setAliasPanelBorderAfterLineEnds("AFTER_LINE_ENDS");
            setAliasScrollPane("$core.Scroll");
            setAliasScrollPaneGetView("getView");
            setAliasScrollPaneSetView("setView");
            setAliasButton("$core.Button");
            setAliasTextLabel("$core.TextLabel");
            setAliasImageLabel("$core.ImageLabel");
            setAliasGetFont("getFont");
            setAliasSetFont("setFont");
            setAliasFont("$core.Font");
            setAliasFontGetName("getName");
            setAliasFontGetSize("getSize");
            setAliasFontIsBold("isBold");
            setAliasFontIsItalic("isItalic");
            setAliasFontStringWidth("stringWidth");
            setAliasComponent("$core.Component");
            setAliasSetContent("setContent");
            setAliasAddCompo("add");
            setAliasRemoveAll("removeAll");
            setAliasAddListener("addList");
            setAliasSetLabelText("setText");
            setAliasSetLabelImage("setImage");
            setAliasPaint("$core.Painting");
            setAliasPaintMethod("paint");
            setAliasPaintAdd("add");
            setAliasPaintRefresh("refresh");
            setAliasRemoveCompo("remove");
            setAliasCount("count");
            setAliasGetIndexCompo("get");
            setAliasPanelAbsolute("absolute");
            setAliasPanelFlow("flow");
            setAliasPanelGrid("grid");
            setAliasPanelPageBox("page");
            setAliasGetParentCompo("getParent");
            setAliasGetNextCompo("next");
            setAliasPack("pack");
            setAliasSetVisible("setVisible");
            setAliasWindow("window");
            setAliasArgs("args");
            setAliasDispose("dispose");
            setAliasImage("$core.Image");
            setAliasImageGet("get");
            setAliasImageSet("set");
            setAliasImageGetHeight("h");
            setAliasImageGetWidth("w");
            setAliasImageIsWithAlpha("alpha");
            setAliasImageDraw("draw");
            setAliasImageDrawRect("drawRect");
            setAliasImageDrawLine("drawLine");
            setAliasImageDrawOval("drawOval");
            setAliasImageDrawPolygon("drawPolygon");
            setAliasImageFillRect("fillRect");
            setAliasImageFillOval("fillOval");
            setAliasImageFillPolygon("fillPolygon");
            setAliasImageDispose("dispose");
            setAliasImageGetColor("getColor");
            setAliasImageSetColor("setColor");
            setAliasImageGetFont("getFont");
            setAliasImageSetFont("setFont");
            setAliasColor("$core.Color");
            setAliasColorAlpha("a");
            setAliasColorRed("r");
            setAliasColorGreen("g");
            setAliasColorBlue("b");
            setAliasColorIsTransparent("transparent");
            setAliasDimension("$core.Dimension");
            setAliasDimensionGetHeight("getHeight");
            setAliasDimensionGetWidth("getWidth");
            setAliasComponentGetPaint("getPaint");
            setAliasComponentSetPaint("setPaint");
            setAliasComponentRepaint("repaint");
            setAliasComponentSetAutoscrolls("setAutoscrolls");
            setAliasComponentIsAutoscrolls("isAutoscrolls");
            setAliasComponentGetHeight("getHeight");
            setAliasComponentGetWidth("getWidth");
            setAliasComponentGetPreferredSize("getPreferredSize");
            setAliasComponentSetPreferredSize("setPreferredSize");
            setAliasComponentIsVisible("isVisible");
            setAliasComponentSetVisible("setVisible");
            setAliasComponentInvokeLater("invokeLater");
            setAliasInput("$core.Input");
            setAliasInputIsEnabled("isEnabled");
            setAliasInputSetEnabled("setEnabled");
            setAliasRender("$core.Render");
            setAliasRenderGetPaint("getPaint");
            setAliasRenderGetWidth("getWidth");
            setAliasRenderGetHeight("getHeight");
            setAliasRenderSetPaint("setPaint");
            setAliasRenderSetWidth("setWidth");
            setAliasRenderSetHeight("setHeight");
            setAliasGrList("$core.GrList");
            setAliasGrListAdd("add");
            setAliasGrListSet("set");
            setAliasGrListGetListView("getView");
            setAliasGrListGetSelectedIndexes("getSelectedIndexes");
            setAliasGrListSetSelectedIndexes("setSelectedIndexes");
            setAliasGrListClearSelection("clearSelection");
            setAliasGrListUpdateGraphics("updateGraphics");
            setAliasGrListGetRender("getRender");
            setAliasGrListSetRender("setRender");
            setAliasGrListGetSelection("getSelection");
            setAliasGrListSetSelection("setSelection");
            setAliasGrListGetVisibleRowCount("getVisibleRowCount");
            setAliasGrListSetVisibleRowCount("setVisibleRowCount");
            setAliasGrListClear("clear");
            setAliasGrListRemove("remove");
            setAliasCombo("$core.ComboBox");
            setAliasComboAddItem("addItem");
            setAliasComboGetItemCount("getItemCount");
            setAliasComboGetListener("getListener");
            setAliasComboSetListener("setListener");
            setAliasComboGetSelectedIndex("getSelectedIndex");
            setAliasComboGetSelectedIndexes("getSelectedIndexes");
            setAliasComboGetSelectedItem("getSelectedItem");
            setAliasComboSelectItem("selectItem");
            setAliasComboRemoveItem("removeItem");
            setAliasComboRemoveAllItems("removeAllItems");
            setAliasButtonGroup("$core.ButtonGroup");
            setAliasButtonGroupAdd("add");
            setAliasRadio("$core.Radio");
            setAliasRadioIsSelected("isSelected");
            setAliasRadioSetSelected("setSelected");
            setAliasRadioGetText("getText");
            setAliasRadioSetText("setText");
            setAliasCheckBox("$core.CheckBox");
            setAliasCheckBoxIsSelected("isSelected");
            setAliasCheckBoxSetSelected("setSelected");
            setAliasCheckBoxGetText("getText");
            setAliasCheckBoxSetText("setText");
            setAliasCheckBoxAddAction("addAction");
            setAliasPopupMenu("$core.Popup");
            setAliasPopupMenuAdd("add");
            setAliasPopupMenuShow("show");
//            setAliasTextFieldAuto();
            setAliasTextField("$core.TextField");
            setAliasTextFieldAddPopup("add");
            setAliasTextFieldGetText("getText");
            setAliasTextFieldSetText("setText");
            setAliasTextFieldAddAction("addAction");
            setAliasTextArea("$core.TextArea");
            setAliasTextAreaAppend("append");
            setAliasTextAreaInsert("insert");
            setAliasTextAreaGetSelectedText("getSelectedText");
            setAliasTextAreaSetSelectionStart("setSelectionStart");
            setAliasTextAreaSetSelectionEnd("setSelectionEnd");
            setAliasTextAreaSelect("select");
            setAliasTextAreaSelectAll("selectAll");
            setAliasTextAreaGetText("getText");
            setAliasTextAreaSetText("setText");
            setAliasTextAreaGetTabSize("getTabSize");
            setAliasTextAreaSetTabSize("setTabSize");
            setAliasTextAreaReplaceRange("replaceRange");
            setAliasTextAreaReplaceSelection("replaceSelection");
            setAliasSpinner("$core.Spinner");
            setAliasSpinnerAddAction("addAction");
            setAliasSpinnerGetMax("getMax");
            setAliasSpinnerSetMax("setMax");
            setAliasSpinnerGetMin("getMin");
            setAliasSpinnerSetMin("setMin");
            setAliasSpinnerGetValue("getValue");
            setAliasSpinnerSetValue("setValue");
            setAliasSpinnerGetStep("getStep");
            setAliasSpinnerSetStep("setStep");
            setAliasSlider("$core.Slider");
            setAliasSliderAddAction("addAction");
            setAliasSliderGetMax("getMax");
            setAliasSliderSetMax("setMax");
            setAliasSliderGetMin("getMin");
            setAliasSliderSetMin("setMin");
            setAliasSliderGetValue("getValue");
            setAliasSliderSetValue("setValue");
        } else {
            setAliasActionEvent("$coeur.ActionEvt");
            setAliasActionListener("$coeur.ActionEcouteur");
            setAliasActionPerformed("action");
            setAliasMouseEvent("$coeur.SourisEvt");
            setAliasMouseListener("$coeur.SourisEcouteur");
            setAliasMouseClicked("clic");
            setAliasMousePressed("presse");
            setAliasMouseReleased("relache");
            setAliasMouseEntered("entre");
            setAliasMouseExited("sorti");
            setAliasGetMouseEventFirst("x");
            setAliasGetMouseEventSecond("y");
            setAliasWindowListener("$coeur.FenetreEcouteur");
            setAliasWindowEvent("$coeur.FenetreEvt");
            setAliasWindowActivated("active");
            setAliasWindowDeactivated("desactive");
            setAliasWindowOpened("ouverte");
            setAliasWindowIconified("iconifie");
            setAliasWindowDeiconified("desiconifie");
            setAliasWindowClosed("ferme");
            setAliasWindowClosing("fermeture");
            setAliasAddWindowListener("ajFenetreEcout");
            setAliasListSelection("$coeur.SelectionListe");
            setAliasValueChanged("changement");
            setAliasFrame("$coeur.Fenetre");
            setAliasPanel("$coeur.Panneau");
            setAliasPanelBorder("$coeur.PanneauBordure");
            setAliasPanelBorderNorth("NORD");
            setAliasPanelBorderWest("OUEST");
            setAliasPanelBorderSouth("SUD");
            setAliasPanelBorderEast("EST");
            setAliasPanelBorderCenter("CENTRE");
            setAliasPanelBorderBeforeFirst("AVANT_PREMIERE_LIGNE");
            setAliasPanelBorderAfterLast("APRES_DERNIERE_LIGNE");
            setAliasPanelBorderBeforeLineBegins("AVANT_DEBUT_LIGNE");
            setAliasPanelBorderAfterLineEnds("APRES_FIN_LIGNE");
            setAliasScrollPane("$coeur.Ascenseur");
            setAliasScrollPaneGetView("valVue");
            setAliasScrollPaneSetView("majVue");
            setAliasButton("$coeur.Bouton");
            setAliasTextLabel("$coeur.Etiquette");
            setAliasImageLabel("$coeur.EtImage");
            setAliasGetFont("valPolice");
            setAliasSetFont("majPolice");
            setAliasFont("$coeur.Police");
            setAliasFontGetName("valNom");
            setAliasFontGetSize("valTaille");
            setAliasFontIsBold("estGras");
            setAliasFontIsItalic("estItalique");
            setAliasFontStringWidth("largeurChaine");
            setAliasComponent("$coeur.Composant");
            setAliasSetContent("majContenu");
            setAliasAddCompo("ajout");
            setAliasRemoveAll("toutSuppr");
            setAliasAddListener("ajEcout");
            setAliasSetLabelText("majTexte");
            setAliasSetLabelImage("majImage");
            setAliasPaint("$coeur.Peinture");
            setAliasPaintMethod("peindre");
            setAliasPaintAdd("ajout");
            setAliasPaintRefresh("rafraichir");
            setAliasRemoveCompo("supprimer");
            setAliasCount("nb");
            setAliasGetIndexCompo("val");
            setAliasPanelAbsolute("absolu");
            setAliasPanelFlow("ligne");
            setAliasPanelGrid("grille");
            setAliasPanelPageBox("page");
            setAliasGetParentCompo("valParent");
            setAliasGetNextCompo("suivant");
            setAliasPack("cadrer");
            setAliasSetVisible("majVisible");
            setAliasWindow("fenetre");
            setAliasArgs("args");
            setAliasDispose("liberer");
            setAliasImage("$coeur.Image");
            setAliasImageGet("val");
            setAliasImageSet("maj");
            setAliasImageGetHeight("h");
            setAliasImageGetWidth("l");
            setAliasImageIsWithAlpha("alpha");
            setAliasImageDraw("dessiner");
            setAliasImageDrawRect("dessinerRect");
            setAliasImageDrawLine("dessinerLigne");
            setAliasImageDrawOval("dessinerOval");
            setAliasImageDrawPolygon("dessinerPolygone");
            setAliasImageFillRect("remplirRect");
            setAliasImageFillOval("remplirOval");
            setAliasImageFillPolygon("remplirPolygone");
            setAliasImageDispose("liberer");
            setAliasImageGetColor("valCouleur");
            setAliasImageSetColor("majCouleur");
            setAliasImageGetFont("valPolice");
            setAliasImageSetFont("majPolice");
            setAliasColor("$coeur.Couleur");
            setAliasColorAlpha("a");
            setAliasColorRed("r");
            setAliasColorGreen("v");
            setAliasColorBlue("b");
            setAliasColorIsTransparent("transparent");
            setAliasDimension("$coeur.Dimension");
            setAliasDimensionGetHeight("valHauteur");
            setAliasDimensionGetWidth("valLargeur");
            setAliasComponentGetPaint("valPeindre");
            setAliasComponentSetPaint("majPeindre");
            setAliasComponentRepaint("repeindre");
            setAliasComponentSetAutoscrolls("majAutoascenseur");
            setAliasComponentIsAutoscrolls("estAutoascenseur");
            setAliasComponentGetHeight("valHauteur");
            setAliasComponentGetWidth("valLargeur");
            setAliasComponentGetPreferredSize("valTaillePreferee");
            setAliasComponentSetPreferredSize("majTaillePreferee");
            setAliasComponentIsVisible("estVisible");
            setAliasComponentSetVisible("majVisible");
            setAliasComponentInvokeLater("invoquerPlusTard");
            setAliasInput("$coeur.Saisie");
            setAliasInputIsEnabled("estActif");
            setAliasInputSetEnabled("majActif");
            setAliasRender("$coeur.Rendu");
            setAliasRenderGetPaint("valPeindre");
            setAliasRenderGetWidth("valLargeur");
            setAliasRenderGetHeight("valHauteur");
            setAliasRenderSetPaint("majPeindre");
            setAliasRenderSetWidth("majLargeur");
            setAliasRenderSetHeight("majHauteur");
            setAliasGrList("$coeur.GrListe");
            setAliasGrListAdd("ajout");
            setAliasGrListSet("maj");
            setAliasGrListGetListView("valVue");
            setAliasGrListGetSelectedIndexes("valIndicesSelection");
            setAliasGrListSetSelectedIndexes("majIndicesSelection");
            setAliasGrListClearSelection("supprimerSelection");
            setAliasGrListUpdateGraphics("majGraphiques");
            setAliasGrListGetRender("valRendu");
            setAliasGrListSetRender("majRendu");
            setAliasGrListGetSelection("valSelection");
            setAliasGrListSetSelection("majSelection");
            setAliasGrListGetVisibleRowCount("valNbLignesVisible");
            setAliasGrListSetVisibleRowCount("majNbLignesVisible");
            setAliasGrListClear("toutSuppr");
            setAliasGrListRemove("supprimer");
            setAliasCombo("$coeur.ListeDeroulante");
            setAliasComboAddItem("ajout");
            setAliasComboGetItemCount("nb");
            setAliasComboGetListener("valEcouteur");
            setAliasComboSetListener("majEcouteur");
            setAliasComboGetSelectedIndex("valIndiceSelect");
            setAliasComboGetSelectedIndexes("valIndicesSelect");
            setAliasComboGetSelectedItem("valEltSelect");
            setAliasComboSelectItem("selectElt");
            setAliasComboRemoveItem("supprElt");
            setAliasComboRemoveAllItems("supprTousElt");
            setAliasButtonGroup("$coeur.GroupeBoutons");
            setAliasButtonGroupAdd("ajout");
            setAliasRadio("$coeur.Radio");
            setAliasRadioIsSelected("estSelect");
            setAliasRadioSetSelected("majSelect");
            setAliasRadioGetText("valTexte");
            setAliasRadioSetText("majTexte");
            setAliasCheckBox("$coeur.Case");
            setAliasCheckBoxIsSelected("estSelect");
            setAliasCheckBoxSetSelected("majSelect");
            setAliasCheckBoxGetText("valTexte");
            setAliasCheckBoxSetText("majTexte");
            setAliasCheckBoxAddAction("ajoutAction");
            setAliasPopupMenu("$coeur.MenuContextuel");
            setAliasPopupMenuAdd("ajout");
            setAliasPopupMenuShow("afficher");
//            setAliasTextFieldAuto();
            setAliasTextField("$coeur.Champ");
            setAliasTextFieldAddPopup("ajout");
            setAliasTextFieldGetText("valTexte");
            setAliasTextFieldSetText("majTexte");
            setAliasTextFieldAddAction("ajoutAction");
            setAliasTextArea("$coeur.Zone");
            setAliasTextAreaAppend("ajout");
            setAliasTextAreaInsert("inserer");
            setAliasTextAreaGetSelectedText("valTextSelect");
            setAliasTextAreaSetSelectionStart("majDebutSelection");
            setAliasTextAreaSetSelectionEnd("majFinSelection");
            setAliasTextAreaSelect("select");
            setAliasTextAreaSelectAll("selectTous");
            setAliasTextAreaGetText("valTexte");
            setAliasTextAreaSetText("majTexte");
            setAliasTextAreaGetTabSize("valTailleTab");
            setAliasTextAreaSetTabSize("majTailleTab");
            setAliasTextAreaReplaceRange("remplacerRang");
            setAliasTextAreaReplaceSelection("remplacerSelect");
            setAliasSpinner("$coeur.CurseurNum");
            setAliasSpinnerAddAction("addAction");
            setAliasSpinnerGetMax("valMax");
            setAliasSpinnerSetMax("majMax");
            setAliasSpinnerGetMin("valMin");
            setAliasSpinnerSetMin("majMin");
            setAliasSpinnerGetValue("val");
            setAliasSpinnerSetValue("maj");
            setAliasSpinnerGetStep("valPas");
            setAliasSpinnerSetStep("majPas");
            setAliasSlider("$coeur.CurseurBarre");
            setAliasSliderAddAction("addAction");
            setAliasSliderGetMax("valMax");
            setAliasSliderSetMax("majMax");
            setAliasSliderGetMin("valMin");
            setAliasSliderSetMin("majMin");
            setAliasSliderGetValue("val");
            setAliasSliderSetValue("maj");
        }
    }
    @Override
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> m_ = super.allTableTypeMethodNames();
        m_.put(getAliasFrame(), new StringList(
                getAliasPack(),
                getAliasWindow(),
                getAliasArgs(),
                getAliasAddWindowListener(),
                getAliasDispose(),
                getAliasSetVisible(),
                getAliasSetContent()));
        m_.put(getAliasPanel(), new StringList(
                getAliasCount(),
                getAliasGetIndexCompo(),
                getAliasAddCompo(),
                getAliasRemoveCompo()));
        m_.put(getAliasMouseEvent(), new StringList(
                getAliasGetMouseEventFirst(),
                getAliasGetMouseEventSecond()));
        m_.put(getAliasComponent(), new StringList(
                getAliasGetParentCompo(),
                getAliasGetNextCompo(),
                getAliasComponentRepaint(),
                getAliasComponentGetPaint(),
                getAliasComponentSetPaint(),
                getAliasGetFont(),
                getAliasSetFont()
        ));
        m_.put(getAliasTextLabel(), new StringList(
                getAliasSetLabelText(),
                getAliasAddListener()));
        m_.put(getAliasFont(), new StringList(
                getAliasFontGetName(),
                getAliasFontGetSize(),
                getAliasFontIsBold(),
                getAliasFontIsItalic(),
                getAliasFontStringWidth()));
        m_.put(getAliasButton(), new StringList(
                getAliasAddListener()));
        return m_;
    }

    @Override
    public StringList allRefTypes() {
        StringList ref_ =  super.allRefTypes();
        ref_.add(getAliasFrame());
        ref_.add(getAliasComponent());
        ref_.add(getAliasActionEvent());
        ref_.add(getAliasMouseEvent());
        ref_.add(getAliasWindowEvent());
        ref_.add(getAliasPanel());
        ref_.add(getAliasButton());
        ref_.add(getAliasTextLabel());
        ref_.add(getAliasFont());
        return ref_;
    }
}