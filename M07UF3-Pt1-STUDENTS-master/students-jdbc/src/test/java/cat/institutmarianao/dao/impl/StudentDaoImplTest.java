package cat.institutmarianao.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		throws ClassNotFoundException, SQLException, IOException {
			Student student = StudentDao.findByPk(dni);
			assertNotNull(student);
			assertEquals(dni,student.getDni());
			return student;
	}

	@Override
	public List<Student> findAll() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student add(Student student) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Student student) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeByDni(String dni) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Student buildObjectFromResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
