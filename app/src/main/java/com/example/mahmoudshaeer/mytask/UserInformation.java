package com.example.mahmoudshaeer.mytask;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

/***
 *
 * this class collect all attributes in the registration and put these in one object to optimize my code
 */
public class UserInformation {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String gender;
    public String mobile;
    public String id;

    /***
     *defult constractor
     */
    public UserInformation() {
    }
    //constractor with parameters

    /***
     * constractor work as set value in the object
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param gender
     * @param mobile
     */
    public UserInformation(String firstName, String lastName, String email,
                           String password, String gender, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.mobile = mobile;
    }
    // all function set values in attributes of class

    /***
     * set first name value in the attribute in the class
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /***
     * set last name value in the attribute in the class
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /***
     * set email value in the attribute in the class
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /***
     * set password value in the attribute in the class
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /***
     * set gender value in the attribute in the class
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /***
     * set mobile value in the attribute in the class
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    //all function that return value of attributes

    /***
     *
     * @return first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /***
     *
     * @return Last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /***
     *
     * @return email of user
     */
    public String getEmail() {
        return email;
    }

    /***
     *
     * @return password of usser
     */
    public String getPassword() {
        return password;
    }

    /***
     *
     * @return Gender of user
     */
    public String getGender() {
        return gender;
    }

    /***
     *
     * @return String Mobile user
     */
    public String getMobile() {
        return mobile;
    }

    /***
     * id of user in the database
     * @return
     */
    public String getId() {
        return id;
    }

    /***
     *  set id in the attribute in the class
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

}
