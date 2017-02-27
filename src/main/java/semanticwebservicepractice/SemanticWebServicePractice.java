/*
 * Original code snippet is from the following slackoverflow question
 * With some updated package name from the current jena version (jena-lib 3.1.1)
 * http://stackoverflow.com/questions/24084473/link-object-to-subject-in-rdf-domain
 */

package semanticwebservicepractice;

import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*; 

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDFS;

public class SemanticWebServicePractice extends HttpServlet {
	public Model createRDFModel() {
        String ns = "http://stackoverflow.com/q/24084473/1281433/";

        Model model = ModelFactory.createDefaultModel();

        Resource soggetto = model.createResource( ns+"soggetto" );
        Resource piemonte = model.createResource( ns+"Piemonte" );

        Property nome = model.createProperty( ns+"Nome" );
        Property regione = model.createProperty( ns+"Regione" );

        soggetto.addProperty( nome, "Vercelli" );
        soggetto.addProperty( regione, piemonte );
        piemonte.addProperty( RDFS.label, "Piemonte" );
        
        return model;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException
	{
		response.setContentType("text/xml;charset=UTF-8");
		// PrintWriter writer = response.getWriter();
		ServletOutputStream outputStream = response.getOutputStream();
		
		Model model = createRDFModel();

        RDFDataMgr.write( outputStream, model, Lang.RDFXML );
	}
}
