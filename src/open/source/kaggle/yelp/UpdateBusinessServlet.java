package open.source.kaggle.yelp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eclipsesource.json.JsonObject;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;


public class UpdateBusinessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String KIND="Business";
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		Scanner sc = new Scanner(new File("data/yelp/yelp_training_set_business.json"));
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		List<Entity> rows=new ArrayList<Entity>();
		while(sc.hasNext()) {
			rows.add(rowFromLine(sc.nextLine(),datastore));
		}
		
		//datastore.put(rows);
		resp.getWriter().write("{\"status\":2}");
	}

	private Entity rowFromLine(String nextLine,DatastoreService datastore) {
		JsonObject jsonObject = JsonObject.readFrom( nextLine );
		String businessId="";
		Entity row = datastore==null?null:datastore.prepare(
				new Query(KIND).setFilter(new Query.FilterPredicate(
						"business_id", Query.FilterOperator.EQUAL, businessId)))
				.asSingleEntity();
		if(row==null) {
			row=new Entity(KIND);
		}
		return row;
	}

}

