package open.source.kaggle.yelp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import open.source.google.map.clustering.algorithm.GridClusterAlgorithm;
import open.source.google.map.clustering.model.Boundary;
import open.source.google.map.clustering.model.ClusterPoint;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class FindBusinessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		JsonArray array=(JsonArray) req.getSession().getAttribute(PutBusinessDataInSessionServlet.KIND);
		resp.getWriter().write("{\"status\":+" + array.size() + "}");
		Double miny=Double.valueOf(req.getParameter("lat1"));
		Double maxy=Double.valueOf(req.getParameter("lat2"));
		Double minx=Double.valueOf(req.getParameter("lon1"));
		Double maxx=Double.valueOf(req.getParameter("lon2"));
		int zoomLevel=Integer.valueOf(req.getParameter("zoomLevel"));
		List<ClusterPoint> points=new ArrayList<ClusterPoint>();
		for(int i=0;i<array.size();i++) {
			points.add(new BusinessPoints(array.get(i).asObject()));
		}
		Boundary boundary=new Boundary(minx, maxx, miny, maxy);
		List<ClusterPoint> result = new GridClusterAlgorithm().getClusteredMarkers(points, boundary, zoomLevel);
		JsonArray arrayOut=new JsonArray();
		for(ClusterPoint point:result) {
			JsonObject markerObject=new JsonObject();
			BusinessPoints b=(BusinessPoints) point;
			markerObject.add("x", b.getX());
			markerObject.add("y", b.getY());
			markerObject.add("label", b.getMarkerLabel());
			arrayOut.add(markerObject);
		}
		resp.getWriter().write(arrayOut.toString());
	}
	static class BusinessPoints extends ClusterPoint {
		private JsonObject object;		
		
		public BusinessPoints(JsonObject object) {
			super();
			this.object = object;
		}

		@Override
		public double getX() {
			return object.get("longitude").asDouble();
		}

		@Override
		public double getY() {
			return object.get("latitude").asDouble();
		}

		@Override
		public void setX(double arg0) {
			object.add("longitude",Double.valueOf(arg0));
		}

		@Override
		public void setY(double arg0) {
			object.add("latitude",arg0);
		}
		public String getName() {
			return object.get("name").asString();
		}
		public String getAddress() {
			return object.get("full_address").asString();
		}
		public String getMarkerLabel() {
			if(this.isClusterPoint()) {
				return String.valueOf(this.getCountCluster());
			}
			return this.getName();
		}
	}

}
