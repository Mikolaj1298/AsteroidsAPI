package asteroids;

public class Asteroid {
	String name;
	String closeApproachDateFull;
	String diameter;
	String missDistance;
	
	public Asteroid(AsteroidsApi api, String date, int id) throws Exception {
		this.name = api.getName(date, id);
		this.closeApproachDateFull = api.getCloseApproachDateFull(date, id);
		this.diameter = api.getDiameter(date, id);
		this.missDistance = api.getMissDistance(date, id);
		//System.out.println(this.closeApproachDateFull);
	}
	
	public void showAsteroid() {
		System.out.println(this.name);
		System.out.println(this.closeApproachDateFull);
		System.out.println(this.diameter);
		System.out.println(this.missDistance);
	}
}
