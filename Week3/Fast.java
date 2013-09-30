import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
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
        findCollinearPoints(allPoints);
        StdDraw.show(0);
    }
 
 private static void findCollinearPoints(Point[] allPoints)
 {
  Arrays.sort(allPoints);
  int len = allPoints.length;
  Point[] sortedSlope = new Point[len];
  for( int i = 0; i < len; i++ )
  {
   Point curr = allPoints[i];
   for( int j = 0; j < len; j++ )
    sortedSlope[j] = allPoints[j];
   Arrays.sort(sortedSlope, curr.SLOPE_ORDER);
   
   int left = 0, right = 1;
   double slopeLeft, slopeRight;
   while(left < len - 2 && right < len)
   {
    slopeLeft = curr.slopeTo(sortedSlope[left]);
    slopeRight = curr.slopeTo(sortedSlope[right]);
    if(Double.compare(slopeLeft,slopeRight) != 0)
    {
     if(right - left >= 3)
      drawLine(sortedSlope, curr, left, right - 1);
     left = right;
    }
    else
    {
     if(right == len - 1 && right - left>=2)
      drawLine(sortedSlope, curr, left, right);
    }
    right++;
   }
  }
 }
 
 private static void drawLine(Point[] allPoints, Point curr, int left, int right)
 {
  if(curr.compareTo(allPoints[left]) > 0)
   return;
  Point[] points = new Point[right - left + 2];
  points[0] = curr;
  for(int i = left; i <= right ;i++)
   points[i - left + 1] = allPoints[i];
  for(int i = 0; i <= right - left + 1;i++)
  {
   if(i==right - left + 1)
    StdOut.println(points[i].toString());
   else
    StdOut.print(points[i].toString() +" -> ");
  }
  points[0].drawTo(points[points.length- 1]);
 }
}