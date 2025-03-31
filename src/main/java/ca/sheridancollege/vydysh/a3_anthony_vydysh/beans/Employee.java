package ca.sheridancollege.vydysh.a3_anthony_vydysh.beans;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

/**
 * Entity representing an employee in the organization.
 */
@Entity(name = "employee")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "hire_date")
    private Date hireDate;


    private Long generateRandom11DigitId() {
        return (long)(Math.random() * 9_000_000_000L + 1_000_000_000L);
    }

    public enum Gender {
        M, F
    }
}

