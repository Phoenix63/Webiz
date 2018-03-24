package Utils;

import java.io.IOException;
import java.io.StringWriter;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.ModelAndView;
import spark.TemplateEngine;

public class FreeMarkerEngine extends TemplateEngine {

	@Override
	public String render(ModelAndView mAndV) {		
		try {

			StringWriter strWritter = new StringWriter();
			Template template = TemplateConfiguration.getTemplate(mAndV.getViewName());
			template.process(mAndV.getModel(), strWritter);
			return strWritter.toString();
			
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}												
		
		return null;
	}

}
