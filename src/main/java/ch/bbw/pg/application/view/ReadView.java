package ch.bbw.pg.application.view;

import ch.bbw.pg.application.App;
import ch.bbw.pg.entity.*;
import ch.bbw.pg.persistence.PersistenceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 29.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class ReadView extends View {

    private JTable table;
    private JComboBox<String> dropdown;
    private JScrollPane scrollPane;
    private JToolBar bottomBar;
    private JButton create, update, delete;


    @Override
    protected void initComponents() {
        dropdown = new JComboBox<>();
        dropdown.addItem("Address");
        dropdown.addItem("City");
        dropdown.addItem("Country");
        dropdown.addItem("Identity");
        dropdown.addItem("Person");
        toolBar.add(dropdown);
        JButton button = new JButton("Refresh");
        button.addActionListener(this::readData);
        toolBar.add(button);
        bottomBar = new JToolBar();
        bottomBar.setFloatable(false);

        create = new JButton("Create");
        create.addActionListener(this::create);
        bottomBar.add(create);

        update = new JButton("Update");
        update.addActionListener(this::update);
        update.setEnabled(false);
        bottomBar.add(update);

        delete = new JButton("Delete");
        delete.addActionListener(this::delete);
        delete.setEnabled(false);
        bottomBar.add(delete);

        add(bottomBar, BorderLayout.PAGE_END);
    }


    @Override
    protected void readData(ActionEvent actionEvent) {
        String choice = dropdown.getSelectedItem() + "";
        String[] header;
        Object[][] data;
        switch (choice) {
            case "Address":
                List<Address> addressList = PersistenceManager.addressPersistence().list();
                header = Address.getTableHeader();
                data = new Object[addressList.size()][header.length];
                for (int i = 0; i < addressList.size(); i++) {
                    data[i] = addressList.get(i).toTableData();
                }
                break;
            case "City":
                List<City> cityList = PersistenceManager.cityPersistence().list();
                header = City.getTableHeader();
                data = new Object[cityList.size()][header.length];
                for (int i = 0; i < cityList.size(); i++) {
                    data[i] = cityList.get(i).toTableData();
                }
                break;
            case "Country":
                List<Country> countryList = PersistenceManager.countryPersistence().list();
                header = Country.getTableHeader();
                data = new Object[countryList.size()][header.length];
                for (int i = 0; i < countryList.size(); i++) {
                    data[i] = countryList.get(i).toTableData();
                }
                break;
            case "Identity":
                List<Identity> identityList = PersistenceManager.identityPersistence().list();
                header = Identity.getTableHeader();
                data = new Object[identityList.size()][header.length];
                for (int i = 0; i < identityList.size(); i++) {
                    data[i] = identityList.get(i).toTableData();
                }
                break;
            case "Person":
                List<Person> personList = PersistenceManager.personPersistence().list();
                header = Person.getTableHeader();
                data = new Object[personList.size()][header.length];
                for (int i = 0; i < personList.size(); i++) {
                    data[i] = personList.get(i).toTableData();
                }
                break;
            default:
                System.err.println("Something went wrong");
                return;
        }
        if (scrollPane != null)
            remove(scrollPane);
        table = new JTable(data, header);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.setVisible(true);
        scrollPane = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(e -> {
            update.setEnabled(true);
            delete.setEnabled(true);
        });

        add(scrollPane, BorderLayout.CENTER);
        updateUI();
    }

    private void create(ActionEvent actionEvent) {
        CreateView createView = new CreateView(dropdown.getSelectedItem() + "");
        App.setView(createView);
    }

    private void delete(ActionEvent actionEvent) {
        int row = table.getSelectedRow();
        if (row == -1)
            return;
        String type = dropdown.getSelectedItem() + "";
        Long id = (Long) table.getModel().getValueAt(table.getSelectedRow(), 0);
        DeleteView updateView = new DeleteView(type, id);
        App.setView(updateView);
    }

    private void update(ActionEvent actionEvent) {
        int row = table.getSelectedRow();
        if (row == -1)
            return;
        String type = dropdown.getSelectedItem() + "";
        Long id = (Long) table.getModel().getValueAt(table.getSelectedRow(), 0);
        UpdateView updateView = new UpdateView(type, id);
        App.setView(updateView);
    }

}
