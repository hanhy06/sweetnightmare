package hanhy06.sweetnightmare.cutscene;

import net.minecraft.util.math.Vec3d;

import java.util.List;

public class CatmullRom {
    private final double totalLength;
//    private final List<>

    private final List<Vec3d> points;
    private final double cutscenePlayTime;

    private long cutsceneStartTime = 0;

    public CatmullRom(List<Vec3d> points,double cutscenePlayTime) {
        this.points = points;
        this.cutscenePlayTime = cutscenePlayTime;

        double length = 0;
        for (int i=0;i<points.size();i++){
            length += points.get(i).distanceTo(points.get(i+1));
        }
        totalLength = length;
    }

    public void cutsceneStart(){
        cutsceneStartTime = System.currentTimeMillis();
    }

    public Vec3d interpolation(double time) {
        if (points.size() < 4) return  null;

        for (int i = 0;i<points.size()-3;i++){
            Vec3d p0 = points.get(i);
            Vec3d p1 = points.get(i+1);
            Vec3d p2 = points.get(i+2);
            Vec3d p3 = points.get(i+3);

            Vec3d t1 = p0.subtract(p2).multiply(0.5);
            Vec3d t2 = p1.subtract(p3).multiply(0.5);

            return new Vec3d(
                    Q(p1.x,p2.x,t1.x,t2.x,time),
                    Q(p1.y,p2.y,t1.y,t2.y,time),
                    Q(p1.z,p2.z,t1.z,t2.z,time)
            );
        }

        return null;
    }

    public double Q(double p1,double p2,double t1, double t2,double time){
        double time2 = time*time;
        double time3 = time2*time2;

        double h1 = 2*time3 - 3*time2 + 1;
        double h2 = -2*time3 + 3*time2;
        double h3 = time3 + -2*time2 + time;
        double h4 = time3-time2;

        return  h1 * p1 + h2 * p2 + h3 * t1 + h4 * t2;
    }
}
