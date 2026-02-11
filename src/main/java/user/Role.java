package user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(nullable=false,unique=true)
   private String name;

   private String descString;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
      name="role_permission",
      joinColumns = @JoinColumn(name="role_id"),
      inverseJoinColumns = @JoinColumn(name="permission_id")
   )
   private Set<Permission> permissions = new HashSet<>();

   
}
