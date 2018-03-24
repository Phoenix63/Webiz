import DAO.*;

import Component.*;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {			
			UserList userList = new UserList("OS to install", "101010");
			userList.makePersistent();

			userList.createNewItem("Linux", "Installer");
			userList.createNewItem("Windows", "Installer");

			System.out.println(userList);

			List<Item> itemList = ItemDAO.getByListId(userList.getId());			

			/// NOTE: The configuration code below must be executed once
			Configuration cfg = new Configuration(new Version(2, 3, 20));
			cfg.setClassForTemplateLoading(Main.class, "templates");
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(Locale.US);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);

			Map<String, Object> input = new HashMap<String, Object>();
			input.put("title", "Exemple with freemarker");

			input.put("userList", userList);
			input.put("items", itemList);

			Template template = cfg.getTemplate("list.ftl");
			Writer consoleWriter = new OutputStreamWriter(System.out);
			template.process(input, consoleWriter);

		} catch (Exception e) {
			System.out.println("ERR: " + e.getMessage());
		}

	}

}
