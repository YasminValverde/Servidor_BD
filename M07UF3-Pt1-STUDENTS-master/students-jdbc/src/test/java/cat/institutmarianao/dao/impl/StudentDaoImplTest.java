package cat.institutmarianao.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import cat.institutmarianao.dao.StudentDao;
import cat.institutmarianao.model.Student;
import jakarta.ejb.EJB;

public class StudentDaoImplTest {

    @EJB
    private StudentDao StudentDao;

    @Test
    public void findByPk() throws ClassNotFoundException, SQLException, IOException {
        String dni = "31415926A";

        Student student = StudentDao.findByPk(dni);

        assertNotNull(student);
        assertEquals(dni, student.getDni());
    }

    @Test
    public void findAll() throws ClassNotFoundException, SQLException, IOException {
        List<Student> students = StudentDao.findAll();

        assertNotNull(students);
    }

    @Test
    public void add() throws ClassNotFoundException, SQLException, IOException {
        Student student = new Student();
        student.setDni("476714342Y");
        student.setName("yasmin");
        student.setSurname("valverde");
        student.setEmail("yasmin@gmail.com");

        Student beforeStudent = StudentDao.findByPk(student.getDni());
        if (beforeStudent != null) {
            StudentDao.remove(beforeStudent);
        }

        StudentDao.add(student);

        Student addedStudent = StudentDao.findByPk(student.getDni());
        assertNotNull(addedStudent);
        assertEquals(student.getDni(), addedStudent.getDni());
    }

    @Test
    public void remove() throws ClassNotFoundException, SQLException, IOException {

        Student beforeStudent = StudentDao.findByPk("476714342Y");
		 Student student = new Student();
        if (beforeStudent != null) {
            StudentDao.remove(beforeStudent);
        }
        StudentDao.add(student);

        Student student_Remove = StudentDao.findByPk(student.getDni());
        assertNotNull(student_Remove);

        StudentDao.remove(student_Remove);

        Student deletedStudent = StudentDao.findByPk(student.getDni());
        assertNull(deletedStudent);
    }

    @Test
    public void removeByDni() throws ClassNotFoundException, SQLException, IOException {
    	String dni = "27182818B";
    	Student student = StudentDao.findByPk(dni);
    
        assertNotNull(student);

        StudentDao.removeByDni(dni);

        Student deleteStudent = StudentDao.findByPk(dni);
        assertNull(deleteStudent);
    }
}