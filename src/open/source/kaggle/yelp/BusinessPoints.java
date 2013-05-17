package open.source.kaggle.yelp;

import open.source.google.map.clustering.model.ClusterPoint;

import com.eclipsesource.json.JsonObject;

public  class BusinessPoints extends ClusterPoint {
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
		object.add("longitude", Double.valueOf(arg0));
	}

	@Override
	public void setY(double arg0) {
		object.add("latitude", arg0);
	}

	public String getName() {
		return object.get("name").asString();
	}

	public String getAddress() {
		return object.get("full_address").asString();
	}

	public String getMarkerLabel() {
		if (this.isClusterPoint()) {
			return String.valueOf(this.getCountCluster());
		}
		return this.getName();
	}
}
