package ch.bbw.pg.application.view;

import ch.bbw.pg.application.App;
import ch.bbw.pg.entity.Address;
import ch.bbw.pg.entity.City;
import ch.bbw.pg.entity.Country;
import ch.bbw.pg.entity.Person;
import ch.bbw.pg.persistence.PersistenceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author P. Gatzka
 * @version 29.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class UpdateView extends View {

    private final String type;
    private final Long id;

    public UpdateView(String type, Long id) {
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
            add(new Label("Update " + type), BorderLayout.PAGE_START);
            JPanel form = new JPanel(new SpringLayout());
            JButton save = new JButton("Save");
            switch (type) {
                case "Address":
                    Address address = PersistenceManager.addressPersistence().find(id);

                    JLabel labelAddressStreet = new JLabel("Street");
                    form.add(labelAddressStreet);
                    JTextField inputAddressStreet = new JTextField(address.getStreet());
                    form.add(inputAddressStreet);

                    JLabel labelAddressHousenumber = new JLabel("Housenumber");
                    form.add(labelAddressHousenumber);
                    JTextField inputAddressHousenumber = new JTextField(address.getHousenumber());
                    form.add(inputAddressHousenumber);

                    JLabel labelAddressCity = new JLabel("City");
                    form.add(labelAddressCity);
                    JComboBox<City> inputAddressCity = new JComboBox<>();
                    PersistenceManager.cityPersistence().list().forEach(inputAddressCity::addItem);
                    form.add(inputAddressCity);

                    SpringUtilities.makeCompactGrid(form, 3, 2, 6, 6, 6, 6);
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            address.setStreet(inputAddressStreet.getText());
                            address.setHousenumber(inputAddressHousenumber.getText());
                            address.setCity((City) inputAddressCity.getSelectedItem());
                            PersistenceManager.addressPersistence().merge(address);
                            cancel(e);
                        }
                    });
                    break;
                case "Country":
                    Country country = PersistenceManager.countryPersistence().find(id);

                    JLabel labelCountryName = new JLabel("Name");
                    form.add(labelCountryName);
                    JTextField inputCountryName = new JTextField(country.getName());
                    form.add(inputCountryName);
                    SpringUtilities.makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            country.setName(inputCountryName.getText());
                            PersistenceManager.countryPersistence().merge(country);
                            cancel(e);
                        }
                    });
                    break;
                case "City":
                    City city = PersistenceManager.cityPersistence().find(id);

                    JLabel labelCityName = new JLabel("Name");
                    form.add(labelCityName);
                    JTextField inputCityName = new JTextField(city.getName());
                    form.add(inputCityName);
                    JLabel labelCityCountry = new JLabel("Country");
                    form.add(labelCityCountry);
                    JComboBox<Country> inputCityCountry = new JComboBox<>();
                    PersistenceManager.countryPersistence().list().forEach(inputCityCountry::addItem);
                    form.add(inputCityCountry);
                    SpringUtilities.makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            city.setName(inputCityName.getText());
                            Country country = (Country) inputCityCountry.getSelectedItem();
                            city.setCountry(country);
                            PersistenceManager.cityPersistence().merge(city);
                            cancel(e);
                        }
                    });
                    break;

                case "Person":
                    Person person = PersistenceManager.personPersistence().find(id);

                    JLabel labelPersonFirstname = new JLabel("Firstname");
                    form.add(labelPersonFirstname);
                    JTextField inputPersonFirstname = new JTextField(person.getFirstname());
                    form.add(inputPersonFirstname);

                    JLabel labelPersonLastname = new JLabel("Lastname");
                    form.add(labelPersonLastname);
                    JTextField inputPersonLastname = new JTextField(person.getLastname());
                    form.add(inputPersonLastname);

                    JLabel labelPersonAddress = new JLabel("Address");
                    form.add(labelPersonAddress);
                    JComboBox<Address> inputPersonAddress = new JComboBox<>();
                    PersistenceManager.addressPersistence().list().forEach(inputPersonAddress::addItem);
                    form.add(inputPersonAddress);
                    JLabel labelPersonBirthdate = new JLabel("Birthdate");
                    form.add(labelPersonBirthdate);
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    JFormattedTextField txtDate = new JFormattedTextField(df);
                    txtDate.addKeyListener(new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            char c = e.getKeyChar();
                            if (!((c >= '0') && (c <= '9') ||
                                    (c == KeyEvent.VK_BACK_SPACE) ||
                                    (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))) {
                                JOptionPane.showMessageDialog(null, "Please Enter Valid");
                                e.consume();
                            }
                        }
                    });
                    txtDate.setText(person.getIdentity().getBirthdate().toString());
                    form.add(txtDate);
                    SpringUtilities.makeCompactGrid(form, 4, 2, 6, 6, 6, 6);
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                person.getIdentity().setBirthdate(new Date(df.parse(txtDate.getText()).getTime()));
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                            person.setFirstname(inputPersonFirstname.getText());
                            person.setLastname(inputPersonLastname.getText());
                            person.setAddress((Address) inputPersonAddress.getSelectedItem());
                            PersistenceManager.personPersistence().merge(person);
                            cancel(e);
                        }
                    });
                    break;

                case "Identity":
                    cancel(null);
                    break;
                default:
                    System.err.println("Something went wrong");
            }
            add(form, BorderLayout.CENTER);
            JToolBar bottomBar = new JToolBar();
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(this::cancel);
            bottomBar.add(save);
            bottomBar.add(cancel);
            add(bottomBar, BorderLayout.PAGE_END);
        }
    }

    private void cancel(ActionEvent actionEvent) {
        App.setView(new ReadView());
    }
}
