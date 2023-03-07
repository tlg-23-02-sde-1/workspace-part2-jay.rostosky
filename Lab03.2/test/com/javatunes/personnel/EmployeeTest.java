package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

public class EmployeeTest {
    private Employee emp1;
    private Employee emp2;

    @Before
    public void setUp() {
        // emp1 = new DummyEmployee("Jack", Date.valueOf("2020-10-10"));
        // emp2 = new DummyEmployee("Jack", Date.valueOf("2020-10-10"));

        emp1 = getEmployee();
        emp2 = getEmployee();
    }

    private Employee getEmployee() {
        return new Employee("Jack", Date.valueOf("2020-10-10")) {
            public double pay() { return 0; }
            public double payTaxes() { return 0; }
        };
    }

    @Test
    public void hashCode_shouldBeEqual_whenEqualObjects() {
        assertEquals(emp1.hashCode(), emp2.hashCode());
    }

    @Test
    public void equals_shouldReturnFalse_differentName_sameHireDate() {
        emp2.setName("Jill");

        assertNotEquals(emp1, emp2);
        assertFalse(emp1.equals(emp2)); // alternative assertion
    }

    @Test
    public void equals_shouldReturnFalse_sameName_differentHireDate() {
        emp2.setHireDate(Date.valueOf("2000-01-01"));

        assertNotEquals(emp1, emp2);
        assertFalse(emp1.equals(emp2)); // alternative assertion
    }

    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(emp1, emp2);       // for objects, equals() gets called
        assertTrue(emp1.equals(emp2));  // alternative assertion
    }

    // NAMED MEMBER-LEVEL INNER CLASSES
    private class DummyEmployee extends Employee {

        public DummyEmployee(String name, Date hireDate) {
            super(name, hireDate);
        }

        public double pay() { return 0; }
        public double payTaxes() { return 0; }
    }
}