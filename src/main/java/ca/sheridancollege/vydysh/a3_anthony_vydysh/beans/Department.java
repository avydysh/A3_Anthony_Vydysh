package ca.sheridancollege.vydysh.a3_anthony_vydysh.beans;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a department in the organization
 */
@Entity(name = "department")
@Data
@NoArgsConstructor
public class Department {

    @Id
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name", length = 45)
    private String deptName;

    @Column(name = "email", length = 45, unique = true)
    private String email;

    @Column(name = "phone", length = 45)
    private String phone;

    @PrePersist
    private void assignDeptId() {
        this.deptId = generateRandom11DigitId();
    }

    private Long generateRandom11DigitId() {
        return (long)(Math.random() * 9_000_000_000L + 1_000_000_000L);
    }
}
