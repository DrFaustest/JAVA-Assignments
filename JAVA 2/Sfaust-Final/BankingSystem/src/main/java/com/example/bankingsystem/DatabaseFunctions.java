package com.example.bankingsystem;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFunctions {
    private File xmlFile;
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;

    public DatabaseFunctions(String xmlFilePath) {
        xmlFile = new File(xmlFilePath);
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }

    private File databaseFile;

    public DatabaseFunctions(String fileName) {
        this.databaseFile = new File(fileName);
    }

    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList customerNodes = document.getElementsByTagName("customer");
        for (int i = 0; i < customerNodes.getLength(); i++) {
            Element customerElement = (Element) customerNodes.item(i);
            int customerId = Integer.parseInt(customerElement.getAttribute("id"));
            String firstName = customerElement.getElementsByTagName("firstName").item(0).getTextContent();
            String lastName = customerElement.getElementsByTagName("lastName").item(0).getTextContent();
            String address = customerElement.getElementsByTagName("address").item(0).getTextContent();
            String phoneNumber = customerElement.getElementsByTagName("phoneNumber").item(0).getTextContent();
            List<SavingsAccount> savingsAccounts = getSavingsAccountsForCustomer(customerElement);
            Customer customer = new Customer(customerId, firstName, lastName, address, phoneNumber, savingsAccounts);
            customers.add(customer);
        }
        return customers;
    }

    public void addCustomer(Customer customer) throws Exception {
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        Element customersElement = (Element) document.getElementsByTagName("customers").item(0);
        Element customerElement = document.createElement("customer");
        customerElement.setAttribute("id", Integer.toString(customer.getId()));

        Element firstNameElement = document.createElement("firstName");
        firstNameElement.appendChild(document.createTextNode(customer.getFirstName()));
        customerElement.appendChild(firstNameElement);

        Element lastNameElement = document.createElement("lastName");
        lastNameElement.appendChild(document.createTextNode(customer.getLastName()));
        customerElement.appendChild(lastNameElement);

        Element addressElement = document.createElement("address");
        addressElement.appendChild(document.createTextNode(customer.getAddress()));
        customerElement.appendChild(addressElement);

        Element phoneNumberElement = document.createElement("phoneNumber");
        phoneNumberElement.appendChild(document.createTextNode(customer.getPhoneNumber()));
        customerElement.appendChild(phoneNumberElement);

        Element savingsAccountsElement = document.createElement("savingsAccounts");
        customerElement.appendChild(savingsAccountsElement);

        for (SavingsAccount savingsAccount : customer.getSavingsAccounts()) {
            Element savingsAccountElement = document.createElement("savingsAccount");
            savingsAccountElement.setAttribute("id", Integer.toString(savingsAccount.getId()));

            Element accountNumberElement = document.createElement("accountNumber");
            accountNumberElement.appendChild(document.createTextNode(savingsAccount.getAccountNumber()));
            savingsAccountElement.appendChild(accountNumberElement);

            Element balanceElement = document.createElement("balance");
            balanceElement.appendChild(document.createTextNode(Double.toString(savingsAccount.getBalance())));
            savingsAccountElement.appendChild(balanceElement);

            Element interestRateElement = document.createElement("interestRate");
            interestRateElement.appendChild(document.createTextNode(Double.toString(savingsAccount.getInterestRate())));
            savingsAccountElement.appendChild(interestRateElement);

            Element accountOwnersElement = document.createElement("accountOwners");
            for (Customer owner : savingsAccount.getAccountOwners()) {
                Element customerRefElement = document.createElement("customerRef");
                customerRefElement.setAttribute("customerId", Integer.toString(owner.getId()));
                accountOwnersElement.appendChild(customerRefElement);
            }
            savingsAccountElement.appendChild(accountOwnersElement);

            savingsAccountsElement.appendChild(savingsAccountElement);
        }

        customersElement.appendChild(customerElement);

        updateXMLFile();
    }

    public void addSavingsAccountToCustomer(Customer customer, SavingsAccount savingsAccount) throws Exception {
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        Element customerElement = getCustomerElementById(customer.getId());
        Element savingsAccountsElement = (Element) customerElement.getElementsByTagName("savingsAccounts").item(0);

        // Check if the customer already has a savings account
        if (savingsAccountsElement.getElementsByTagName("savingsAccount").getLength() > 0) {
            throw new Exception("Customer already has a savings account");
        }

        Element savingsAccountElement = document.createElement("savingsAccount");
        savingsAccountElement.setAttribute("id", Integer.toString(savingsAccount.getId()));

        Element accountNumberElement = document.createElement("accountNumber");
        accountNumberElement.appendChild(document.createTextNode(savingsAccount.getAccountNumber()));
        savingsAccountElement.appendChild(accountNumberElement);

        Element balanceElement = document.createElement("balance");
        balanceElement.appendChild(document.createTextNode(Double.toString(savingsAccount.getBalance())));
        savingsAccountElement.appendChild(balanceElement);

        Element interestRateElement = document.createElement("interestRate");
        interestRateElement.appendChild(document.createTextNode(Double.toString(savingsAccount.getInterestRate())));
        savingsAccountElement.appendChild(interestRateElement);

        Element accountOwnersElement = document.createElement("accountOwners");
        Element customerRefElement = document.createElement("customerRef");
        customerRefElement.setAttribute("customerId", Integer.toString(customer.getId()));
        accountOwnersElement.appendChild(customerRefElement);
        savingsAccountElement.appendChild(accountOwnersElement);

        savingsAccountsElement.appendChild(savingsAccountElement);

        updateXMLFile();
    }

    public void updateSavingsAccountBalance(int customerId, int savingsAccountId, double newBalance) throws Exception {
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        Element customerElement = getCustomerElementById(customerId);
        Element savingsAccountElement = getSavingsAccountElementById(customerElement, savingsAccountId);

        savingsAccountElement.getElementsByTagName("balance").item(0).setTextContent(Double.toString(newBalance));

        updateXMLFile();
    }

    private List<SavingsAccount> getSavingsAccountsForCustomer(Element customerElement) throws Exception {
        List<SavingsAccount> savingsAccounts = new ArrayList<>();
        NodeList savingsAccountNodes = customerElement.getElementsByTagName("savingsAccount");

        for (int i = 0; i < savingsAccountNodes.getLength(); i++) {
            Element savingsAccountElement = (Element) savingsAccountNodes.item(i);
            int savingsAccountId = Integer.parseInt(savingsAccountElement.getAttribute("id"));
            String accountNumber = savingsAccountElement.getElementsByTagName("accountNumber").item(0).getTextContent();
            double balance = Double
                    .parseDouble(savingsAccountElement.getElementsByTagName("balance").item(0).getTextContent());
            double interestRate = Double
                    .parseDouble(savingsAccountElement.getElementsByTagName("interestRate").item(0).getTextContent());
            List<Customer> accountOwners = getAccountOwnersForSavingsAccount(savingsAccountElement);
            SavingsAccount savingsAccount = new SavingsAccount(savingsAccountId, accountNumber, balance, interestRate,
                    accountOwners);
            savingsAccounts.add(savingsAccount);
        }

        return savingsAccounts;
    }

    private List<Customer> getAccountOwnersForSavingsAccount(Element savingsAccountElement) throws Exception {
        List<Customer> accountOwners = new ArrayList<>();
        NodeList customerRefNodes = savingsAccountElement.getElementsByTagName("customerRef");

        for (int i = 0; i < customerRefNodes.getLength(); i++) {
            Element customerRefElement = (Element) customerRefNodes.item(i);
            int customerId = Integer.parseInt(customerRefElement.getAttribute("customerId"));
            Customer customer = getCustomerById(customerId);
            accountOwners.add(customer);
        }
        return accountOwners;
    }

    private Element getCustomerElementById(int customerId) throws Exception {
        NodeList customerNodes = document.getElementsByTagName("customer");
        for (int i = 0; i < customerNodes.getLength(); i++) {
            Element customerElement = (Element) customerNodes.item(i);
            if (Integer.parseInt(customerElement.getAttribute("id")) == customerId) {
                return customerElement;
            }
        }
        throw new Exception("Customer with ID " + customerId + " not found in the database.");
    }

    private Element getSavingsAccountElementById(Element customerElement, int savingsAccountId) throws Exception {
        NodeList savingsAccountNodes = customerElement.getElementsByTagName("savingsAccount");
        for (int i = 0; i < savingsAccountNodes.getLength(); i++) {
            Element savingsAccountElement = (Element) savingsAccountNodes.item(i);
            if (Integer.parseInt(savingsAccountElement.getAttribute("id")) == savingsAccountId) {
                return savingsAccountElement;
            }
        }
        throw new Exception("Savings account with ID " + savingsAccountId + " not found for customer with ID "
                + customerElement.getAttribute("id") + ".");
    }

    private Customer getCustomerById(int customerId) throws Exception {
        Element customerElement = getCustomerElementById(customerId);
        String firstName = customerElement.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = customerElement.getElementsByTagName("lastName").item(0).getTextContent();
        String address = customerElement.getElementsByTagName("address").item(0).getTextContent();
        String phoneNumber = customerElement.getElementsByTagName("phoneNumber").item(0).getTextContent();
        List<SavingsAccount> savingsAccounts = getSavingsAccountsForCustomer(customerElement);
        return new Customer(customerId, firstName, lastName, address, phoneNumber, savingsAccounts);
    }

    private void updateXMLFile() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileOutputStream(xmlFile));
        transformer.transform(source, result);
    }

    public void updateCustomer(Customer customer) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(databaseFile);
            doc.getDocumentElement().normalize();

            Node customerNode = findCustomerNode(doc, customer.getId());

            if (customerNode != null) {
                // Update the customer node with the new information
                updateCustomerNode(customerNode, customer);

                // Write the updated XML data to the file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(databaseFile);
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error updating customer: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
