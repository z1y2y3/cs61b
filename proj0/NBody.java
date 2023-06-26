public class NBody{
    public static double readRadius(String file){
        In in = new In(file);
        int totalPlanet = in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int totalPlanet = in.readInt(); 
        double radius = in.readDouble();
        Planet[] temp = new Planet[totalPlanet];
        for(int i = 0;i < totalPlanet;i++){
            temp[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                                 in.readDouble(), in.readDouble(), in.readString());
        }
        return temp;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = NBody.readPlanets(filename);
        double readDouble = NBody.readRadius(filename);
        StdDraw.setScale(-readDouble,readDouble);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for (Planet planet : planets) {
            planet.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt,xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        } 

        System.out.printf("%d\n", planets.length);
        System.out.printf("%.2e\n", readDouble);
        for (int i = 0; i < planets.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}