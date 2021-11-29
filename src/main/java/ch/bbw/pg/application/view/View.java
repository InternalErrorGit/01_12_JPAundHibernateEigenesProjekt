package ch.bbw.pg.application.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author P. Gatzka
 * @version 29.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public abstract class View extends JPanel {

    protected JToolBar toolBar;

    public View() {
        setPreferredSize(new Dimension(900, 600));
        setSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        toolBar = new JToolBar("Non-Detachable");
        toolBar.add(Box.createHorizontalBox());
        toolBar.setFloatable(false);
        add(toolBar, BorderLayout.PAGE_START);
        initComponents();
    }

    protected abstract void readData(ActionEvent actionEvent);

    protected abstract void initComponents();

}
