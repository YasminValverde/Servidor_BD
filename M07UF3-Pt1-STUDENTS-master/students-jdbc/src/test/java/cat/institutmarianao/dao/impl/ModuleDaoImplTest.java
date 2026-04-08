package cat.institutmarianao.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.institutmarianao.dao.ModuleDao;
import cat.institutmarianao.model.Module;
import jakarta.ejb.EJB;


public class ModuleDaoImplTest extends BaseDaoImpl<Module, String> implements ModuleDao {

	@EJB
	private ModuleDao moduleDao;

	@Override
	public Module findByPk(String moduleCode, String cycleCode)
			throws ClassNotFoundException, SQLException, IOException {
		Module module = moduleDao.findByPk(moduleCode, cycleCode);
		assertNotNull(module);
		assertEquals("M01", module.getCode());
		assertEquals("DAM", module.getCycleCode());
		assertEquals("Sistemes informàtics", module.getName());
		return module;
	}



	@Override
	public List<Module> findAllByCycleCode(String cycleCode) throws ClassNotFoundException, SQLException, IOException {
		List<Module> modules = moduleDao.findAllByCycleCode(cycleCode);
		assertNotNull(modules);
		assertEquals(2, modules.size());
		return modules;
	}



	@Override
	protected Module buildObjectFromResultSet(ResultSet rs) throws SQLException {
		Module module = new Module();
		module.setCode(rs.getString("M01"));
		module.setCycleCode(rs.getString("DAM"));
		module.setName(rs.getString("Sistemes informàtics"));
		return module;
	}
}