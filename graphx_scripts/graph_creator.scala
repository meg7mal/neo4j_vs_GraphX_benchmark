import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
//import org.apache.spark.graphx.GraphLoader
import scala.util.MurmurHash
//import org.apache.spark.graphx.Graph
//import org.apache.spark.rdd.RDD
//import org.apache.spark.graphx.VertexId

object GraphFromFile {
  def main(args: Array[String]) {

    //create SparkContext
    val sparkConf = new SparkConf().setAppName("GraphFromFile").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    // read your file
    /*suppose your data is like 
    v1 v3
    v2 v1
    v3 v4
    v4 v2
    v5 v3
    */
    var file = sc.textFile("/tmp/relationships.csv");
    
    //val header = file.first() //extract header
    //file = file.filter(lambda x:x != header)    //filter out header

    // create edge RDD of type RDD[(VertexId, VertexId)]
    val edgesRDD: RDD[(VertexId, VertexId)] = file.map(line => line.split(","))
      .map(line =>
        (MurmurHash.stringHash(line(0).toString), MurmurHash.stringHash(line(1).toString)))

    // create a graph 
    val graph = Graph.fromEdgeTuples(edgesRDD, 1)

    // you can see your graph 
    //graph.triplets.collect().foreach(println)
    
    //val inDegrees: VertexRDD[Int] = graph.inDegrees

    //inDegrees.collect().foreach(println)
     
     var numvert = graph.numVertices
     println(numvert)
	
     var numedges=graph.numEdges
     println(numedges)
  }
}
