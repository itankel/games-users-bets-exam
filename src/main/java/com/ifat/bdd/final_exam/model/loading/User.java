package com.ifat.bdd.final_exam.model.loading;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @Column(nullable = false, name="ID")
    private int id;
    @Column(nullable = false, name="NAME")
    private String name;
    @Column(nullable = false, name = "LASTNAME")
    private String lastName;
    @Column(nullable = false ,name = "COUNTRYOFORIGIN")
    private String countryOfOrigin;
    @Column(nullable = false, name="EMAIL")
    private String email;

    public User(int id, String name, String lastName, String countryOfOrigin, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.countryOfOrigin = countryOfOrigin;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCountryOfOrigin() {
        return this.countryOfOrigin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$countryOfOrigin = this.getCountryOfOrigin();
        final Object other$countryOfOrigin = other.getCountryOfOrigin();
        if (this$countryOfOrigin == null ? other$countryOfOrigin != null : !this$countryOfOrigin.equals(other$countryOfOrigin))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $countryOfOrigin = this.getCountryOfOrigin();
        result = result * PRIME + ($countryOfOrigin == null ? 43 : $countryOfOrigin.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", name=" + this.getName() + ", lastName=" + this.getLastName() + ", countryOfOrigin=" + this.getCountryOfOrigin() + ", email=" + this.getEmail() + ")";
    }

}
