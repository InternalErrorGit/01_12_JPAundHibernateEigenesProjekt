package ch.bbw.pg.application;

import ch.bbw.pg.application.view.ReadView;
import ch.bbw.pg.application.view.View;
import ch.bbw.pg.persistence.PersistenceManager;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class App {
    private static JFrame window;
    private static View currentView;

    public static void main(String[] args) {
        PersistenceManager.prepare();
        window = new JFrame("01_12_JPAundHibernateEigenesProjekt");
        window.setPreferredSize(new Dimension(900, 600));
        window.setSize(new Dimension(900, 600));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        currentView = new ReadView();
        window.add(currentView);
        window.pack();
        window.setVisible(true);
    }

    public static void setView(View view) {
        window.remove(currentView);
        currentView = view;
        window.add(currentView);
        SwingUtilities.updateComponentTreeUI(window);
        window.invalidate();
        window.validate();
        window.repaint();
    }


}
