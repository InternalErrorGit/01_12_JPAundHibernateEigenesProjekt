package ch.bbw.pg.application.view;

import ch.bbw.pg.application.App;
import ch.bbw.pg.persistence.PersistenceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author P. Gatzka
 * @version 29.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class DeleteView extends View {

    private final String type;
    private final Long id;

    public DeleteView(String type, Long id) {
        this.type = type;
        this.id = id;
        initComponents();
    }

    @Override
    protected void readData(ActionEvent actionEvent) {

    }

    @Override
    protected void initComponents() {
        if (type != null) {
            JButton save = new JButton("Confirm");
            switch (type) {
                case "Person":
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PersistenceManager.personPersistence().remove(id);
                            cancel(e);
                        }
                    });
                    break;
                case "Address":
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PersistenceManager.addressPersistence().remove(id);
                            cancel(e);
                        }
                    });
                    break;
                case "City":
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PersistenceManager.cityPersistence().remove(id);
                            cancel(e);
                        }
                    });
                    break;
                case "Country":
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PersistenceManager.countryPersistence().remove(id);
                            cancel(e);
                        }
                    });
                    break;
                case "Identity":
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PersistenceManager.identityPersistence().remove(id);
                            cancel(e);
                        }
                    });
                    break;
            }
            JToolBar bottomBar = new JToolBar();
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(this::cancel);
            bottomBar.add(save);
            bottomBar.setFloatable(false);
            bottomBar.add(cancel);
            add(bottomBar, BorderLayout.PAGE_END);
        }

    }

    private void cancel(ActionEvent actionEvent) {
        App.setView(new ReadView());
    }

}
