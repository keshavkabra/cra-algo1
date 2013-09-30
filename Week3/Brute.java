
public class Brute{
 public static void main(String args[]){
  StdDraw.setXscale(0, 32768);
  StdDraw.setYscale(0, 32768);
  StdDraw.show(0);
  In file = new In(args[0]);
  int size = file.readInt();
  Point allPoints[] = new Point[size];
  int index = 0;
  while(!file.isEmpty())
  {
   int x = file.readInt();
   int y = file.readInt();
   allPoints[index++] = new Point(x, y);
   allPoints[index - 1].draw();
//   index++;
  }
 // for(Point p:allPoints)
  // p.draw();
  Merge.sort(allPoints);
  findCollinearPoints(allPoints);
  StdDraw.show(0);
 }
 
 private static void findCollinearPoints(Point[] allPoints)
 {
  for(int i = 0; i < allPoints.length; i++)
   for(int j = i + 1; j < allPoints.length; j++)
    for(int k = j + 1; k < allPoints.length; k++)
     if( allPoints[i].slopeTo(allPoints[j]) == allPoints[i].slopeTo(allPoints[k]))
      for(int l = k + 1; l< allPoints.length; l++)
       if( allPoints[i].slopeTo(allPoints[l]) == allPoints[i].slopeTo(allPoints[k])){
        StdOut.println( allPoints[i].toString() +" -> "+ allPoints[j].toString() +" -> "+ allPoints[k].toString() +" -> "+ allPoints[l].toString());
        allPoints[i].drawTo(allPoints[l]);
       }
 }
}