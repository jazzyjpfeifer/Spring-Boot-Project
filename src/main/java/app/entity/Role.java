package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_description")
    private String RoleDescription;

    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleDescription() {
        return RoleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        RoleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", RoleDescription='" + RoleDescription + '\'' +
                '}';
    }
}
