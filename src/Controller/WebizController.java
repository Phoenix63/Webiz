package Controller;

import Component.*;

import DAO.*;

import Utils.FreeMarkerEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Spark;
import spark.ModelAndView;

public class WebizController {

	private final String WEBIZ_SESS_ON = "WEBIZ_SESS_ON";
	private final String WEBIZ_SESS_ACCOUNT = "WEBIZ_SESS_ACCOUNT";
	private final String WEBIZ_LOGIN_ERR = "WEBIZ_LOGIN_ERR";
	private final String WEBIZ_REGISTER_ERR = "WEBIZ_REGISTER_ERR";
	
	public WebizController() {		
		Spark.staticFileLocation("Utils/templates/static");
		Spark.staticFiles.expireTime(600);		
		initializeRoutes();		
	}
	public WebizController(int port) {
		Spark.port(port);			
		initializeRoutes();		
	}

	private void initializeRoutes() {

		/// Ensure authentication
		Spark.before("/list/*", (req, res) -> {
			if (req.session().attribute(WEBIZ_SESS_ON) != "true") {
				res.redirect("/");
			}			
		});
		
		/// Allow using url which end with /
		Spark.before((req, res) -> {
			String path = req.pathInfo();
			if (path.endsWith("/") && path.length() > 1) {
				res.redirect(path.substring(0, path.length() - 1));
			}
		});
		
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
					model.put("loginError", (String)req.session().attribute(WEBIZ_LOGIN_ERR));
					req.session().removeAttribute(WEBIZ_LOGIN_ERR);
				} else if (req.session().attribute(WEBIZ_REGISTER_ERR) != null) {
					model.put("registerError", (String)req.session().attribute(WEBIZ_REGISTER_ERR));
					req.session().removeAttribute(WEBIZ_REGISTER_ERR);					
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
			
			String username = req.queryParamOrDefault("username", null);
			String password = req.queryParamOrDefault("password", null);
			
			if (username == null || password == null) {
				Spark.halt(400); // bad request
			}
			
			password = Utils.Secure.hash(password);
			
			Account account = AccountDAO.getByName(username);
			if (account == null || !account.getPassword().equals(password)) {
				req.session().attribute(WEBIZ_LOGIN_ERR, "Wrong credential");
				res.redirect("/#tologin");
				return "";
			}
			
			req.session(true).attribute(WEBIZ_SESS_ON, "true");
			req.session().attribute(WEBIZ_SESS_ACCOUNT, account);
			res.redirect("/");
		
			return "";		
			
		});
		
		Spark.post("/register", (req, res) -> {

			String username = req.queryParamOrDefault("usernamesignup", null);
			String password = req.queryParamOrDefault("passwordsignup", null);
			String password_confirm = req.queryParamOrDefault("passwordsignup_confirm", null);
			String mail = req.queryParamOrDefault("emailsignup", null);
			
			if (username == null || password == null || password_confirm == null || mail == null) {
				Spark.halt(400); // bad request
			}

			/// Check the non-existence of username
			if (AccountDAO.getByName(username) != null) {
				req.session().attribute(WEBIZ_REGISTER_ERR, "Username already exist");
				res.redirect("/#toregister");
				return "";
			}
			
			/// Check password equality
			if (!password.equals(password_confirm)) {
				req.session().attribute(WEBIZ_REGISTER_ERR, "Passwords mismatch");
				res.redirect("/#toregister");
				return "";
			}
			
			password = Utils.Secure.hash(password);
			
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			account.setMail(mail);
			account.makePersistent();
			
			req.session(true).attribute(WEBIZ_SESS_ON, "true");
			req.session().attribute(WEBIZ_SESS_ACCOUNT, account);
			res.redirect("/");
			
			return "";
			
		});
		
		/// Create a new list
		Spark.post("/list", (req, res) -> {
			
			String title = req.queryParamOrDefault("title", null); // required
			String description = req.queryParamOrDefault("description", "");
			
			if (title == null) {
				Spark.halt(400); // bad request
			}
			
			UserList list = new UserList();		
			list.setTitle(title);
			list.setDescription(description);
			list.makePersistent();

			res.redirect("/list/"+list.getId(), 303); // see other			
			return "";
			
		});
		
		/// Update content of list {id}
		Spark.post("/list/:id", (req, res) -> {
			
			String id = req.params(":id");
			
			try {
				Integer.parseInt(id);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No list with id '%s' found", id);
			}
			
			UserList list = ListDAO.getById(id);
			if (list == null) {
				res.status(404);
				return String.format("No list with id '%s' found", id);
			}
			
			String title = req.queryParamOrDefault("title", null); // required
			String description = req.queryParamOrDefault("description", null); // required
			
			if (title == null || description == null) {
				Spark.halt(400); // bad request
			}
						
			list.setTitle(title);
			list.setDescription(description);
			list.makePersistent();
						
			res.redirect("/list/"+id, 303); // see other			
			return "";
		});
		
		/// Delete the list {listId}
		Spark.post("/list/:listId/remove", (req, res) -> {

			String listId = req.params(":listId");
						
			try {
				Integer.parseInt(listId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No list with id '%s' found", listId);
			}

			UserList list = ListDAO.getById(listId);
			if (list == null) {
				res.status(404);
				return String.format("No item with id '%s' found", listId);
			}
			
			ListDAO.delete(list);

			res.redirect("/", 303); // see other		
			return "";			
		});
		
		/// Create a new item in list {listId}
		Spark.post("/list/:listId/item", (req, res) -> {
			
			String listId = req.params(":listId");
						
			try {
				Integer.parseInt(listId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No list with id '%s' found", listId);
			}
			
			String title = req.queryParamOrDefault("title", null); // required
			String description = req.queryParamOrDefault("description", "");
			
			if (title == null) {
				Spark.halt(400); // bad request
			}
			
			UserList list = ListDAO.getById(listId);	
			Item item = list.createNewItem(title, description); // will call makePersistent on new item
			
			res.redirect("/list/"+list.getId()+"/item/"+item.getId(), 303); // see other			
			return "";
			
		});
		
		/// Update content of item {itemId} in list {listId}
		Spark.post("/list/:listId/item/:itemId", (req, res) -> {

			String listId = req.params(":listId");
			String itemId = req.params(":itemId");
						
			try {
				Integer.parseInt(listId);
				Integer.parseInt(itemId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No item with id '%s' on the list with id '%s' found", itemId, listId);
			}

			Item item = ItemDAO.getByIdAndListId(itemId, listId);
			if (item == null) {
				res.status(404);
				return String.format("No item with id '%s' found", itemId);
			}
			
			String title = req.queryParamOrDefault("title", null); // required
			String description = req.queryParamOrDefault("description", null); // required
			
			if (title == null || description == null) {
				Spark.halt(400); // bad request
			}
			
			item.setTitle(title);
			item.setDescription(description);
			item.setLastModificationDate(new Date());
			item.makePersistent();

			res.redirect("/list/"+listId+"/item/"+itemId, 303); // see other			
			return "";			
		});	
		
		/// Delete item {itemId} in list {listId}
		Spark.post("/list/:listId/item/:itemId/remove", (req, res) -> {

			String listId = req.params(":listId");
			String itemId = req.params(":itemId");
						
			try {
				Integer.parseInt(listId);
				Integer.parseInt(itemId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No item with id '%s' on the list with id '%s' found", itemId, listId);
			}

			Item item = ItemDAO.getByIdAndListId(itemId, listId);
			if (item == null) {
				res.status(404);
				return String.format("No item with id '%s' found", itemId);
			}
			
			ItemDAO.delete(item);

			res.redirect("/list/"+listId, 303); // see other			
			return "";			
		});
		
	}
	private void initializePutRoutes() {
		
		/// NOTE: PUT method is not allowed with HTML form 
		
		/// Update content of list {id}
		Spark.put("/list/:id", (req, res) -> {
			
			String id = req.params(":id");
			
			try {
				Integer.parseInt(id);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No list with id '%s' found", id);
			}
			
			UserList list = ListDAO.getById(id);
			if (list == null) {
				res.status(404);
				return String.format("No list with id '%s' found", id);
			}
			
			String title = req.queryParamOrDefault("title", null); // required
			String description = req.queryParamOrDefault("description", null); // required
			
			if (title == null || description == null) {
				Spark.halt(400); // bad request
			}
						
			list.setTitle(title);
			list.setDescription(description);
			list.makePersistent();
			
			res.status(204);			
			return "";
		});
		
		/// Update content of item {itemId} in list {listId}
		Spark.put("/list/:listId/item/:itemId", (req, res) -> {

			String listId = req.params(":listId");
			String itemId = req.params(":itemId");
						
			try {
				Integer.parseInt(listId);
				Integer.parseInt(itemId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No item with id '%s' on the list with id '%s' found", itemId, listId);
			}

			Item item = ItemDAO.getByIdAndListId(itemId, listId);
			if (item == null) {
				res.status(404);
				return String.format("No item with id '%s' found", itemId);
			}
			
			String title = req.queryParamOrDefault("title", null); // required
			String description = req.queryParamOrDefault("description", null); // required
			
			if (title == null || description == null) {
				Spark.halt(400); // bad request
			}
			
			item.setTitle(title);
			item.setDescription(description);
			item.setLastModificationDate(new Date());
			item.makePersistent();

			res.status(204);			
			return "";			
		});
	
	}
	private void initializeDeleteRoutes() {
		
		/// Delete the list {listId}
		Spark.delete("/list/:listId", (req, res) -> {

			String listId = req.params(":listId");
						
			try {
				Integer.parseInt(listId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No list with id '%s' found", listId);
			}

			UserList list = ListDAO.getById(listId);
			if (list == null) {
				res.status(404);
				return String.format("No item with id '%s' found", listId);
			}
			
			ListDAO.delete(list);

			res.status(204);		
			return "";			
		});
		
		/// Delete the item {itemId} in list {listId}
		Spark.delete("/list/:listId/item/:itemId", (req, res) -> {

			String listId = req.params(":listId");
			String itemId = req.params(":itemId");
						
			try {
				Integer.parseInt(listId);
				Integer.parseInt(itemId);
			} catch (Exception ex) {
				res.status(404);
				return String.format("No item with id '%s' on the list with id '%s' found", itemId, listId);
			}

			Item item = ItemDAO.getByIdAndListId(itemId, listId);
			if (item == null) {
				res.status(404);
				return String.format("No item with id '%s' found", itemId);
			}
			
			ItemDAO.delete(item);

			res.status(204);		
			return "";			
		});
		
	}
	
}
