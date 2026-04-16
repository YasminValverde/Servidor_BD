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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import cat.institutmarianao.dao.ModuleDao;
import cat.institutmarianao.model.Module;
import jakarta.ejb.EJB;


public class ModuleDaoImplTest extends BaseDaoImpl<Module, String> implements ModuleDao {

	@EJB
	private ModuleDao moduleDao;
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive jar = ShrinkWrap.create(WebArchive.class, "students-jdbc.war")
				.addPackages(true, "cat.institutmarianao.dao").addPackage("cat.institutmarianao.model")
				.addClass(org.h2.Driver.class).addAsResource("db.properties").addAsResource("db_test.sql")
				.addAsManifestResource("META-INF/MANIFEST.MF", "MANIFEST.MF").setWebXML("WEB-INF/web.xml");
		// jar.as(ZipExporter.class).exportTo(new File("target/students-jdbc.war"),
		// true);
		return jar;
	}

	@Override
	public Module findByPk(String moduleCode, String cycleCode)
			throws ClassNotFoundException, SQLException, IOException {
		Module module = moduleDao.findByPk(moduleCode, cycleCode);
		assertNotNull(module);
		assertEquals(moduleCode, module.getCode());
		assertEquals(cycleCode, module.getCycleCode());
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