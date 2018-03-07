public class Planet {
  double xxPos;//Its current x position
  double yyPos;//Its current y position
  double xxVel;//Its current velocity in the x direction
  double yyVel;//Its current velocity in the y direction
  double mass;//Its mass
  String imgFileName;
  public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;
              }
  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

//calculates the distance between two Planets
  public double calcDistance(Planet p){
    double dx = p.xxPos - this.xxPos;
    double dy = p.yyPos - this.yyPos;
    double distance = Math.sqrt(dx * dx + dy * dy );
    return distance;
  }

  public double calcForceExertedBy(Planet p){
    double r = this.calcDistance(p);
    double G = 6.67e-11;
    double F = G * p.mass * this.mass /(r * r);
    return F;
  }

  public double calcForceExertedByX(Planet p){
    double r = this.calcDistance(p);
    double F = this.calcForceExertedBy(p);
    double Fx = F * (p.xxPos - this.xxPos) / r;
    return Fx;
  }

  public double calcForceExertedByY(Planet p){
    double r = this.calcDistance(p);
    double F = this.calcForceExertedBy(p);
    double Fy = F * (p.yyPos - this.yyPos) / r;
    return Fy;
  }

  public double calcNetForceExertedByX(Planet[] AllPlanets){
    double NetFx = 0;
    for (int i = 0; i < AllPlanets.length; i++){
      if (AllPlanets[i].equals(this)){
        continue;
      }
      else{
        NetFx += this.calcForceExertedByX(AllPlanets[i]);
      }
    }
    return NetFx;
  }

  public double calcNetForceExertedByY(Planet[] AllPlanets){
    double NetFy = 0;
    for (int i = 0; i < AllPlanets.length; i++){
      if (AllPlanets[i].equals(this)){
        continue;
      }
      else{
        NetFy += this.calcForceExertedByY(AllPlanets[i]);
      }
    }
    return NetFy;
  }

  public void update(double dt, double fX, double fY){
    double ax = fX/this.mass;
    double ay = fY/this.mass;
    this.xxVel += ax * dt;
    this.yyVel += ay * dt;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }

//draw the Planet's img at the Planet's position
   public void draw(){
     StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
   }

}
