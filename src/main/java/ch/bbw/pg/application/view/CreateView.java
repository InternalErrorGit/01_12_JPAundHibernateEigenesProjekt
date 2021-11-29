package ch.bbw.pg.application.view;

import ch.bbw.pg.application.App;
import ch.bbw.pg.entity.*;
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
import java.util.Objects;

/**
 * @author P. Gatzka
 * @version 29.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class CreateView extends View {

    private final String type;

    public CreateView(String type) {
        this.type = type;
        initComponents();
    }

    @Override
    protected void readData(ActionEvent actionEvent) {

    }

    @Override
    protected void initComponents() {
        if (type != null) {
            add(new Label("Create " + type), BorderLayout.PAGE_START);
            JPanel form = new JPanel(new SpringLayout());
            JButton save = new JButton("Save");
            switch (type) {
                case "Address":
                    JLabel labelAddressStreet = new JLabel("Street");
                    form.add(labelAddressStreet);
                    JTextField inputAddressStreet = new JTextField();
                    form.add(inputAddressStreet);

                    JLabel labelAddressHousenumber = new JLabel("Housenumber");
                    form.add(labelAddressHousenumber);
                    JTextField inputAddressHousenumber = new JTextField();
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
                            Address address = new Address();
                            address.setStreet(inputAddressStreet.getText());
                            address.setHousenumber(inputAddressHousenumber.getText());
                            address.setCity((City) inputAddressCity.getSelectedItem());
                            PersistenceManager.addressPersistence().persist(address);
                            cancel(e);
                        }
                    });
                    break;
                case "Country":
                    JLabel labelCountryName = new JLabel("Name");
                    form.add(labelCountryName);
                    JTextField inputCountryName = new JTextField();
                    form.add(inputCountryName);
                    SpringUtilities.makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Country country = new Country();
                            country.setName(inputCountryName.getText());
                            PersistenceManager.countryPersistence().persist(country);
                            cancel(e);
                        }
                    });
                    break;
                case "City":
                    JLabel labelCityName = new JLabel("Name");
                    form.add(labelCityName);
                    JTextField inputCityName = new JTextField();
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
                            City city = new City();
                            city.setName(inputCityName.getText());
                            Country country = (Country) inputCityCountry.getSelectedItem();
                            city.setCountry(country);
                            PersistenceManager.cityPersistence().persist(city);
                            cancel(e);
                        }
                    });
                    break;

                case "Person":
                    JLabel labelPersonFirstname = new JLabel("Firstname");
                    form.add(labelPersonFirstname);
                    JTextField inputPersonFirstname = new JTextField();
                    form.add(inputPersonFirstname);

                    JLabel labelPersonLastname = new JLabel("Lastname");
                    form.add(labelPersonLastname);
                    JTextField inputPersonLastname = new JTextField();
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

                    form.add(txtDate);
                    SpringUtilities.makeCompactGrid(form, 4, 2, 6, 6, 6, 6);
                    save.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Identity identity = Identity.createIdentity();
                            try {
                                identity.setBirthdate(new Date(df.parse(txtDate.getText()).getTime()));
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                            String uuid = identity.getUuid();
                            PersistenceManager.identityPersistence().persist(identity);
                            identity = PersistenceManager.identityPersistence().list().stream().filter(identity1 -> Objects.equals(identity1.getUuid(), uuid)).findFirst().orElse(null);

                            Person person = new Person();
                            person.setIdentity(identity);
                            person.setFirstname(inputPersonFirstname.getText());
                            person.setLastname(inputPersonLastname.getText());
                            person.setAddress((Address) inputPersonAddress.getSelectedItem());
                            PersistenceManager.personPersistence().persist(person);
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
            bottomBar.setFloatable(false);
            bottomBar.add(cancel);
            add(bottomBar, BorderLayout.PAGE_END);
        }
    }

    private void cancel(ActionEvent actionEvent) {
        App.setView(new ReadView());
    }
}
