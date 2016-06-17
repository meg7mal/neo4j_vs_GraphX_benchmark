import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Graph_User {
def main(args: Array[String]) {
	val sparkConf = new SparkConf().setAppName("Graph_User").setMaster("local[*]")
   	val sc = new SparkContext(sparkConf)
	//val graph = GraphLoader.edgeListFile(sc, "rel.txt")
//edgeStorageLevel = StorageLevel.MEMORY_AND_DISK, vertexStorageLevel = StorageLevel.MEMORY_AND_DISK)
	//val res = graph.outDegrees.reduce((a,b) => if (a._2 > b._2) a else b)
	//println(res)
       //graph.vertices.foreach(println)
    //val v = graph.pageRank(0.001).vertices
    //v.take(10).foreach(println)


val vtextRDD = sc.textFile("/tmp/vertices.csv")
    val edgetextRDD = sc.textFile("/tmp/rel_new.csv")
    // MapPartitionsRDD[1] at textFile

    val vertexRDD: RDD[(VertexId,Int)] = vtextRDD.map(line => line.split(",")).map(line => (line (0).toLong, line(1).toInt))

    val edgesRDD: RDD[Edge[VertexId]] = edgetextRDD.map(line => line.split(",")).map(line => Edge(line (0).toLong, line(1).toLong))

    val graph = Graph(vertexRDD, edgesRDD)


    println(graph.numVertices)
    println(graph.numEdges)

}
}
