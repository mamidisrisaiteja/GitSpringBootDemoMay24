package org.gfg.springbootdemo.dbDemo.model;



import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="person_data")


public class MyPerson {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;
@Column(name="first_Name",length = 30)
    private String fName;

    private String lName;
    @Column(length = 30, unique =true)
    private String email;

    private Integer age;


     // in jpa and hibernate id is mandatory as we have methods which use id

     @Transient // this field need not go to data base
    private String country;


}
