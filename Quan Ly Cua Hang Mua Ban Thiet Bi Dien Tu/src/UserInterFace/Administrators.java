/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterFace;

/**
 *
 * @author Thanh
 */
public class Administrators {

    String Username, Password, Phone, Name, Email, RegistrationDate;

    public Administrators() {
    }

    public Administrators(String Username, String Password, String Phone, String Name, String Email, String RegistrationDate) {
        this.Username = Username;
        this.Password = Password;
        this.Phone = Phone;
        this.Name = Name;
        this.Email = Email;
        this.RegistrationDate = RegistrationDate;

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(String RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

}
