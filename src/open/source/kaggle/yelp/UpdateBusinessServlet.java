package open.source.kaggle.yelp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public class UpdateBusinessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String KIND = "Yelp_Training_Business";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		Scanner sc = new Scanner(new File(
				"data/yelp/yelp_training_set_business.json"));
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		List<Entity> rows = new ArrayList<Entity>();
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) {
			sb.append(sc.nextLine());
		}
		JsonArray array = JsonArray.readFrom(sb.toString());
		int maxLimit=1000;
		int count=1;
		for (int i = 0; i < array.size() && count<=maxLimit; i++) {
			JsonObject object = (JsonObject) array.get(i);
			Entity rowEntity = rowFromLine(object, datastore);
			if (rowEntity != null) {
				count++;
				rows.add(rowEntity);
			}
		}
		//datastore.put(rows);
		resp.getWriter().write("{\"status\":+" + 2 + "}");
	}

	private Entity rowFromLine(JsonObject json, DatastoreService datastore) {
		String businessId = json.get("business_id").asString();
		Entity row = datastore == null ? null : datastore
				.prepare(
						new Query(KIND).setFilter(new Query.FilterPredicate(
								"business_id", Query.FilterOperator.EQUAL,
								businessId))).asSingleEntity();
		if(row!=null) {
			return null;
		}
		if (row == null) {
			row = new Entity(KIND);
		}
		for (String propertyName : json.names()) {
			JsonValue value = json.get(propertyName);
			try {
				row.setProperty(propertyName, value.asBoolean());
			} catch (Exception e) {
				try {
					row.setProperty(propertyName, value.asDouble());
				} catch (Exception e1) {
					try {
						row.setProperty(propertyName, value.asString());
					} catch (Exception e2) {
						try {
							row.setProperty(propertyName, value.asObject().toString());
						} catch (Exception e3) {
							row.setProperty(propertyName, value.asArray().toString());
						}
					}
				}

			}
		}
		return row;
	}

}
