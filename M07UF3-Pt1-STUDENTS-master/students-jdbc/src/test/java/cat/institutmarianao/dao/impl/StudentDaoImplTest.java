package cat.institutmarianao.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cat.institutmarianao.dao.ModuleDao;
import cat.institutmarianao.dao.StudentDao;
import cat.institutmarianao.model.Module;
import cat.institutmarianao.model.Student;
import jakarta.ejb.EJB;

public class StudentDaoImplTest extends BaseDaoImpl<Student	, String> implements StudentDao {
	@EJB
	private StudentDao StudentDao;
	
	@Override
	public Student findByPk(String dni) throws ClassNotFoundException, SQLException, IOException {
			Student student = StudentDao.findByPk(dni);
			assertNotNull(student);
			assertEquals(dni,student.getDni());
			return student;
	}

	@Override
	public List<Student> findAll() throws ClassNotFoundException, SQLException, IOException {
		List<Student> students = StudentDao.findAll();
		assertNotNull(students);
		return students;
	}

	@Override
	public Student add(Student student) throws ClassNotFoundException, SQLException, IOException {
		Student beforeStudent = StudentDao.findByPk(student.getDni());
		assertNull(beforeStudent);
		StudentDao.add(student);

		Student addedStudent = StudentDao.findByPk(student.getDni());
		assertNotNull(addedStudent);
		assertEquals(student.getDni(), addedStudent.getDni());

		return addedStudent;
	}

	@Override
	public void remove(Student student) throws ClassNotFoundException, SQLException, IOException {
			Student student_Remove = StudentDao.findByPk(student.getDni());
			assertNotNull(student_Remove);
			StudentDao.remove(student_Remove);

			Student deletedStudent = StudentDao.findByPk(student_Remove.getDni());
			assertNull(deletedStudent);
		}

	@Override
	public void removeByDni(String dni) throws ClassNotFoundException, SQLException, IOException {
		Student student_Remove = StudentDao.findByPk(dni);
		assertNotNull(student_Remove);
		StudentDao.remove(student_Remove);

		Student deletedStudent = StudentDao.findByPk(student_Remove.getDni());
		assertNull(deletedStudent);
	}

	@Override
	protected Student buildObjectFromResultSet(ResultSet rs) throws SQLException {
	
		Student student = new Student();
		student.setDni(rs.getString("dni"));
		student.setName(rs.getString("name"));
		student.setSurname(rs.getString("surname"));
		student.setEmail(rs.getString("email"));
		student.setCycle(rs.getString("cycle"));
		return student;
	}

}