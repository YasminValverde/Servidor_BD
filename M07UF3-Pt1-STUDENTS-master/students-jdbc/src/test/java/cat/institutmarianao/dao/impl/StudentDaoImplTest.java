package cat.institutmarianao.dao.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.institutmarianao.dao.StudentDao;
import cat.institutmarianao.model.Student;
import jakarta.ejb.EJB;

@RunWith(Arquillian.class)
 public class StudentDaoImplTest {

     @EJB
     private StudentDao studentDao;
     

     @Deployment
 	public static WebArchive createDeployment() {
 		WebArchive war = ShrinkWrap.create(WebArchive.class, "students-jdbc.war")
 				.addPackages(true, "cat.institutmarianao.dao").addPackage("cat.institutmarianao.model")
 				.addClass(org.h2.Driver.class).addAsResource("db.properties").addAsResource("db_test.sql")
 				.addAsManifestResource("META-INF/MANIFEST.MF", "MANIFEST.MF").setWebXML("WEB-INF/web.xml");
 		// war.as(ZipExporter.class).exportTo(new File("target/students-jdbc.war"),
 		// true);
 		return war;
 	}

	/*
	 * @Test public void findByPk() throws ClassNotFoundException, SQLException,
	 * IOException { String dni = "31415926A";
	 * 
	 * Student student = studentDao.findByPk(dni);
	 * 
	 * assertNotNull(student); assertEquals(dni, student.getDni()); }
	 */

     @Test
     public void findAll() throws ClassNotFoundException, SQLException, IOException {
         List<Student> students = studentDao.findAll();

     	assertEquals(3, students.size());
 	}

		/*
		 * @Test public void addOk() throws ClassNotFoundException, SQLException,
		 * IOException { Student student = new Student(); student.setDni("476714342Y");
		 * student.setName("yasmin"); student.setSurname("valverde");
		 * student.setEmail("yasmin@gmail.com");
		 * 
		 * Student beforeStudent = studentDao.findByPk(student.getDni()); if
		 * (beforeStudent != null) { studentDao.remove(beforeStudent); }
		 * 
		 * studentDao.add(student);
		 * 
		 * Student addedStudent = studentDao.findByPk(student.getDni());
		 * assertNotNull(addedStudent); assertEquals(student.getDni(),
		 * addedStudent.getDni()); }
		 * 
		 * @Test public void removeOk() throws ClassNotFoundException, SQLException,
		 * IOException {
		 * 
		 * Student beforeStudent = studentDao.findByPk("476714342Y"); Student student =
		 * new Student(); if (beforeStudent != null) { studentDao.remove(beforeStudent);
		 * } studentDao.add(student);
		 * 
		 * Student student_Remove = studentDao.findByPk(student.getDni());
		 * assertNotNull(student_Remove);
		 * 
		 * studentDao.remove(student_Remove);
		 * 
		 * Student deletedStudent = studentDao.findByPk(student.getDni());
		 * assertNull(deletedStudent); }
		 * 
		 * @Test public void removeByDni() throws ClassNotFoundException, SQLException,
		 * IOException { String dni = "27182818B"; Student student =
		 * studentDao.findByPk(dni);
		 * 
		 * assertNotNull(student);
		 * 
		 * studentDao.removeByDni(dni);
		 * 
		 * Student deleteStudent = studentDao.findByPk(dni); assertNull(deleteStudent);
		 * }
		 */
 }