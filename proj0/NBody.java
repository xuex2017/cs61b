public class NBody {

  public static double readRadius(String fileName){
    In in = new In(fileName);
    int NumPlanet = in.readInt();
    double radius = in.readDouble();
    in.close();
    return radius;
    }

  public static Planet[] readPlanets(String fileName){
    In in = new In(fileName);

    /* read first and second line with no planets*/
    int NumPlanet = in.readInt();
    double radius = in.readDouble();

    Planet[] AllPlanets = new Planet[NumPlanet];
    /* Keep looking until the file is empty. */

      //start reding planets
    for (int i = 0; i < NumPlanet; i++){
        double xP = in.readDouble();
        double yP = in.readDouble();
        double xV = in.readDouble();
        double yV = in.readDouble();
        double m = in.readDouble();
        String img = in.readString();
        Planet p = new Planet(xP, yP, xV, yV, m, img);
        //add p to AllPlanets
        AllPlanets[i] = p;
      }
    in.close();

    return AllPlanets;
  }

  public static void main(String args[]){
    if (args.length == 0) {
			System.out.println("Please supply a command line argument.");
			/* System.exit ends the program early. */
			System.exit(0);
		}
    //read everything from the files
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] AllPlanets = readPlanets(filename);
    int NumPlanet = AllPlanets.length;
    /** Sets up the universe so it goes from
      * -r, -r up to r, r */
    StdDraw.setScale((-1)*radius, radius);
    /* Clears the drawing window. */
    StdDraw.clear();
    /* draw the image starfield.jpg as the background */
    StdDraw.picture(0, 0, "images/starfield.jpg");

    for (int i = 0; i < AllPlanets.length; i++){
      AllPlanets[i].draw();
    }

    /*Create a animation*/
    double time = 0;
    for (int i =0; i < T; i += dt){

      double[] xForces = new double[NumPlanet];
      double[] yForces = new double[NumPlanet];
      //the net x and y forces for each planet
      for (int j = 0 ; j < NumPlanet; j++){
        xForces[j] = AllPlanets[j].calcNetForceExertedByX(AllPlanets);
        yForces[j] = AllPlanets[j].calcNetForceExertedByY(AllPlanets);
      }
    /* draw the image starfield.jpg as the background */
      StdDraw.picture(0, 0, "images/starfield.jpg");

      //update all Planets then draw all of the planets.
      for (int j = 0 ; j < NumPlanet; j++){
        AllPlanets[j].update(dt, xForces[j], yForces[j]);
        AllPlanets[j].draw();
      }
    //Pause the animation for 10 milliseconds
      StdDraw.show(10);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
	     StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }

}
