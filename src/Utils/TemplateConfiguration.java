package Utils;

import java.io.IOException;
import java.util.Locale;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class TemplateConfiguration {

	private static TemplateConfiguration instance = null;
	private Configuration cfg = null;
	
	private TemplateConfiguration() {

		/// NOTE: The configuration code below must be executed once
		cfg = new Configuration(new Version(2, 3, 20));
		cfg.setClassForTemplateLoading(TemplateConfiguration.class, "templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		
	}
	
	public static void initialize() {
		getInstance();
	}
	
	public static TemplateConfiguration getInstance() {
		if (TemplateConfiguration.instance == null) {
			TemplateConfiguration.instance = new TemplateConfiguration();
		}
		return TemplateConfiguration.instance;
	}
	
	public static Template getTemplate(String templateName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		return getInstance().cfg.getTemplate(templateName);		
	}

}
