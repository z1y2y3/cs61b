public class Planet {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
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
    public double calcDistance(Planet rocinante){
      double distance = (this.xxPos-rocinante.xxPos)*(this.xxPos-rocinante.xxPos)+
                        (this.yyPos-rocinante.yyPos)*(this.yyPos-rocinante.yyPos);  
                      
      return Math.sqrt(distance);
    }

    public double calcForceExertedBy(Planet p){
      double f = (G*this.mass*p.mass)/(this.calcDistance(p)*this.calcDistance(p));
      return f;
    }

    public double calcForceExertedByX(Planet p){
      return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
      return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] p){
      double f = 0;
      for(int i = 0;i < p.length;i++){
          if(this.equals(p[i]))
          continue;
          else{
            f += this.calcForceExertedByX(p[i]);         
          }
      }
      return f;
    }

    public double calcNetForceExertedByY(Planet[] p){
      double f = 0;
      for(int i = 0;i < p.length;i++){
          if(this.equals(p[i]))
          continue;
          else{
            f += this.calcForceExertedByY(p[i]);         
          }
      }
      return f;
    }
/*
Calculate the acceleration using the provided x- and y-forces.

Calculate the new velocity by using the acceleration and current velocity. 
Recall that acceleration describes the change in velocity per unit time, 
so the new velocity is (vx+dt⋅ax,vy+dt⋅ay).

Calculate the new position by using the velocity computed in step 2 and the current position. 
The new position is (px+dt⋅vx,py+dt⋅vy).
 */
    public void update(double dt,double fX,double fY){
      double ax = fX/this.mass;
      double ay = fY/this.mass;
      this.xxVel = this.xxVel+dt*ax;
      this.yyVel = this.yyVel+dt*ay;
      this.xxPos = this.xxPos+dt*this.xxVel;
      this.yyPos = this.yyPos+dt*this.yyVel; 
    }

    public void draw(){
      StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }

}