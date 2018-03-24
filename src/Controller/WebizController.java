package Controller;

import Component.UserList;
import DAO.ListDAO;

import Utils.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Spark;
import spark.ModelAndView;

public class WebizController {

	public WebizController() {		
		Spark.staticFileLocation("Utils/templates/static");
		initializeRoutes();		
	}
	public WebizController(int port) {
		Spark.port(port);			
		initializeRoutes();		
	}

	private void initializeRoutes() {

		initializeGetRoutes();
		initializePostRoutes();
		initializePutRoutes();
		initializeDeleteRoutes();

	}
	
	private void initializeGetRoutes() {
		
		Spark.get("/", (req, res) -> {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("title", "Welcome page");
			return new FreeMarkerEngine().render(new ModelAndView(model, "index_base.ftl"));			

		});
		
		Spark.get("/list", (req, res) -> {

			// 1. Retrieve data
			List<UserList> list = ListDAO.getAll();

			// 2. Build model
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("title", "User page");
			model.put("lists", list);

			// 3. Make response
			return new FreeMarkerEngine().render(new ModelAndView(model, "index_connected.ftl"));			

		});

		Spark.get("/list/:id", (req, res) -> {

			String id = req.params(":id");
			try {
				Integer.parseInt(id);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No list with id '%s' found", id);
			}

			// 1. Retrieve data
			UserList list = ListDAO.getById(id);
			if (list == null) {
				res.status(404);
				return String.format("No list with id '%s' found", id);
			}

			// 2. Build model
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("title", "List page");
			model.put("userList", list);

			// 3. Make response
			return new FreeMarkerEngine().render(new ModelAndView(model, "list.ftl"));		

		});
		
	}
	private void initializePostRoutes() {
		
	}
	private void initializePutRoutes() {
		
	}
	private void initializeDeleteRoutes() {
		
	}
	
}
