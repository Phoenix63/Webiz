package Controller;

import Component.*;

import DAO.ItemDAO;
import DAO.ListDAO;

import Utils.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Spark;
import spark.ModelAndView;

public class WebizController {

	private final String WEBIZ_SESS_ON = "WEBIZ_SESS_ON";
	private final String WEBIZ_LOGIN_ERR = "WEBIZ_LOGIN_ERR";
	private final String WEBIZ_REGISTER_ERR = "WEBIZ_REGISTER_ERR";
	
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
					
			if (req.session().attribute(WEBIZ_SESS_ON) == "true") {
				
				// 1. Retrieve data
				List<UserList> list = ListDAO.getAll();

				// 2. Build model
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("title", "User page");
				model.put("lists", list);

				// 3. Make response
				return new FreeMarkerEngine().render(new ModelAndView(model, "index_connected.ftl"));
				
			} else {
					
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("title", "Welcome page");
				
				if (req.session().attribute(WEBIZ_LOGIN_ERR) != null) {
					req.session().removeAttribute(WEBIZ_LOGIN_ERR);
					model.put("loginError", "true");
				}
				
				return new FreeMarkerEngine().render(new ModelAndView(model, "index_base.ftl"));
				
			}
		});
						
		Spark.get("/teamRocket", (req, res) -> {
			
			String ret = "<html><head><title>???</title></head>";
			ret += "<body><div style=\"width:100%;height:0;padding-bottom:73%;position:relative;\">";
			ret += "<iframe src=\"https://giphy.com/embed/UnuzYFgrlBo9G\" width=\"100%\" height=\"100%\" style=\"position:absolute\" frameBorder=\"0\" allowFullScreen>";
			ret += "</iframe></div></body></html>";			
			return ret;
			
		});
		
		Spark.get("/logout", (req, res) -> {
			
			req.session().invalidate();
			res.redirect("/");			
			return null;	
			
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
		
		Spark.get("/list/:listId/item/:itemId", (req, res) -> {

			String listId = req.params(":listId");
			String itemId = req.params(":itemId");
			
			try {
				Integer.parseInt(listId);
				Integer.parseInt(itemId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No item with id '%s' on the list with id '%s' found", itemId, listId);
			}

			// 1. Retrieve data
			Item item = ItemDAO.getByIdAndListId(itemId, listId);
			if (item == null) {
				res.status(404);
				return String.format("No item with id '%s' found", itemId);
			}

			// 2. Build model
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("title", "Item page");
			model.put("item", item);

			// 3. Make response
			return new FreeMarkerEngine().render(new ModelAndView(model, "item.ftl"));		

		});
		
	}
	private void initializePostRoutes() {
		
		Spark.post("/login", (req, res) -> {
			
			// TODO: check in the database
			String username = req.queryParamOrDefault("username", null);
			String password = req.queryParamOrDefault("password", null);
			
			if (username == null || password == null) {
				Spark.halt(400); // bad request
			}
			
			/// Check if the given credential work
			if (!username.equals("romain")) { // If it's wrong
				
				req.session().attribute(WEBIZ_LOGIN_ERR, "true");
				res.redirect("/");
				
			} else {
				
				req.session(true).attribute(WEBIZ_SESS_ON, "true");
				res.redirect("/");
				
			}		
			
			return null;		
			
		});
		
	}
	private void initializePutRoutes() {
		
	}
	private void initializeDeleteRoutes() {
		
	}
	
}
