package fi.koulusafka.api.service.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;

import fi.koulusafka.api.domain.Restaurant;


public final class LocationUtil {
	
	private static final Logger log = LoggerFactory.getLogger(LocationUtil.class);

	private LocationUtil() {
	
	}
	
	
	public static List<Restaurant> getNearestRestaurants(List<Restaurant> restaurants,  Double latitude,  Double longitude) {
		
		RTree<Restaurant, Geometry> tree = RTree.create();		
		
		for (Restaurant restaurant : restaurants) {
			tree = addNode(tree, restaurant);
		}

		Point location = Geometries.pointGeographic(latitude, longitude);
		List<Restaurant> nearestRestaurants = new ArrayList<>();
		
		
		tree.search(location, 0.01)
			.forEach(entry -> nearestRestaurants.add(entry.value()));
		
		return nearestRestaurants;
	
	}
	
	private static RTree<Restaurant, Geometry> addNode(RTree<Restaurant, Geometry> tree, Restaurant r) {
		tree = tree.add(r, Geometries.pointGeographic(r.getLatitude(), r.getLongitude()));
		return tree;
	}

}
