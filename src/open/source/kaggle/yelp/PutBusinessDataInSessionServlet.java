package open.source.kaggle.yelp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eclipsesource.json.JsonArray;

public class PutBusinessDataInSessionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String KIND = "Yelp_Training_Business";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		Scanner sc = new Scanner(new File(
				"data/yelp/yelp_training_set_business.json"));
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) {
			sb.append(sc.nextLine());
		}
		JsonArray array = JsonArray.readFrom(sb.toString());
		req.getSession().setAttribute(KIND, array);
		resp.getWriter().write("{\"status\":+" + 2 + "}");
	}


}
