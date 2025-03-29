package ca.sheridancollege.vydysh.a3_anthony_vydysh.beans;

import lombok.Data;
import java.io.Serializable;

/**
 * Composite key class for EmpDept
 */
@Data
public class EmpDeptId implements Serializable {
    private Long empId;
    private Long deptId;
}

