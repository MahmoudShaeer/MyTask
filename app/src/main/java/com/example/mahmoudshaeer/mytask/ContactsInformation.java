package com.example.mahmoudshaeer.mytask;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

public class ContactsInformation {
    String PhoneNumber;
    String ContactsName;

    /***
     * defult constractor
     */
    public ContactsInformation() {
    }

    /**
     * constractor with param to set it in these attribute
     * @param phoneNumber
     * @param cintactsName
     */
    public ContactsInformation(String phoneNumber, String cintactsName) {
        PhoneNumber = phoneNumber;
        ContactsName = cintactsName;
    }

    /**
     *
     * @return phone number of contact
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    /**
     *
     * @return name of contact
     */
    public String getContactsName() {
        return ContactsName;
    }

    /**
     * set phone number in its attribut
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    /**
     * set name of contact in its attribute
     * @param cintactsName
     */
    public void setContactsName(String cintactsName) {
        ContactsName = cintactsName;
    }
}
