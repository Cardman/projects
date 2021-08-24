package code.gui;
import javax.swing.ListCellRenderer;
import code.util.CustList;
import javax.swing.*;
import java.awt.Component;

public final class CustSelList<T> implements ListCellRenderer<T> {

	private CustCellRender<T> render;
	private CustList<T> list = new CustList<>();
	private PreparedLabel label;

    public void setRender(CustCellRender<T> _render) {
        this.render = _render;
        label = PreparedLabel.prep(_render.getImageFactory());
    }


    public void setList(CustList<T> _list) {
        this.list = _list;
    }

	public Component getListCellRendererComponent(
       JList<? extends T> _ls,
       T _value,
       int _index,               
       boolean _isSelected,     
       boolean _cellHasFocus)   
     {
         render.setList(list);
         render.getListCellRendererComponent(label,_index,_isSelected,_cellHasFocus);
         render.paintComponent(label);
         return label.getNatComponent();
     }
}