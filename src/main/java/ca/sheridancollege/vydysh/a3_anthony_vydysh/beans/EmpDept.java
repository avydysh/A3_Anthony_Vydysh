package ca.sheridancollege.vydysh.a3_anthony_vydysh.beans;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

/**
 * Join entity representing the many-to-many relationship between Employee and Department.
 */
@Entity(name = "emp_dept")
@IdClass(EmpDeptId.class)
@Data
@NoArgsConstructor
public class EmpDept {

    /**
     * Composite primary key employee ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Integer empId;
    /**
     * Composite primary key department ID
     */
    @Id
    @Column(name = "dept_id")
    private String deptId;

    /**
     * Date the employee started in the department
     */
    @Column(name = "from_date")
    private Date fromDate;

    /**
     * Date the employee ended in the department
     */
    @Column(name = "to_date")
    private Date toDate;
}