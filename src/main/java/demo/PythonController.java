package demo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.python.core.PyException;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PythonController implements ResourceProcessor<RepositoryLinksResource> {
	
    @Autowired
    private DataSource dataSource;

	PythonInterpreter interpreter = null;
	Connection connection = null;

	@GetMapping({"/python/{py}/**"})
	Object all(@PathVariable String py, HttpServletRequest request) {
		File pyfile = new File(new File("py"), py+".py");
		if (pyfile.exists()) {
			try {
				connection = dataSource.getConnection();

				interpreter = new PythonInterpreter();
				interpreter.execfile(pyfile.getAbsolutePath());
				interpreter.set("dbcon", connection);
				interpreter.set("request", request);

				PyObject getMethod = interpreter.get("doGet");
				if (getMethod == null) return "doGet(uri, queryString) not implemented in " + pyfile;

				String queryString = request.getQueryString();
				if (queryString==null) queryString = "";
				return getMethod.__call__(new PyString(request.getRequestURI()), new PyString(queryString)).__tojava__(Object.class);
			}
			catch (SQLException e) {
				return "Error getting dbcon from pool: " + e.getMessage();
			}
			catch (PyException e) {
				return e.toString();
			}
			finally {
				if (interpreter!=null) interpreter.close();
				if (connection!=null) try {connection.close();} catch (SQLException e) {}
			}
		}
		return "Could not find: " + pyfile;
	}

	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
		resource.add(linkTo(PythonController.class).slash("python/$pyfile/**").withRel("python"));
		return resource;
	}

}
