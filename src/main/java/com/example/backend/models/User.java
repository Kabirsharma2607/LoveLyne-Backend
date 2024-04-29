package com.example.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String userId;
    private String hashedPassword;
    private String fullName;
    private String contactNumber;
    private int dobDay;
    private int dobMonth;
    private int dobYear;
    private boolean showGender;
    private String genderIdentity;
    private String genderInterest;
    private String email;
    private String url;
    private String about;
    private List<String> matches;
    private List<String> seen;

    public User(){
        matches = new ArrayList<>();
        seen = new ArrayList<>();
    }



    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setDobDay(int dobDay) {
        this.dobDay = dobDay;
    }

    public void setDobMonth(int dobMonth) {
        this.dobMonth = dobMonth;
    }

    public void setDobYear(int dobYear) {
        this.dobYear = dobYear;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGenderIdentity(String genderIdentity) {
        this.genderIdentity = genderIdentity;
    }

    public void setGenderInterest(String genderInterest) {
        this.genderInterest = genderInterest;
    }

    public void setHashedPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(plainPassword.getBytes());

            byte[] hashedBytes = md.digest();

            String base64Hash = Base64.getEncoder().encodeToString(hashedBytes);

            this.hashedPassword = base64Hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMatches(String user) {
        matches.add(user);
    }

    public void setShowGender(boolean showGender) {
        this.showGender = showGender;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public String getAbout() {
        return about;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGenderIdentity() {
        return genderIdentity;
    }

    public String getGenderInterest() {
        return genderInterest;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getMatches() {
        return matches;
    }

    public boolean isShowGender() {
        return showGender;
    }

    public List<String> getSeen() {
        return seen;
    }

    public void setSeen(String user) {
        seen.add(user);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", dobDay=" + dobDay +
                ", dobMonth=" + dobMonth +
                ", dobYear=" + dobYear +
                ", showGender=" + showGender +
                ", genderIdentity='" + genderIdentity + '\'' +
                ", genderInterest='" + genderInterest + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", about='" + about + '\'' +
                ", matches=" + matches +
                ", seen=" + seen +
                '}';
    }
}
