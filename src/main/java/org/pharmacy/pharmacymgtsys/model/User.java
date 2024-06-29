package org.pharmacy.pharmacymgtsys.model;

/**
 * This is the User model.
 * It represents and provides various methods to access and manage users
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

