import io.getquill._

import java.sql.PreparedStatement
import java.sql.Statement
import java.sql.Connection
import com.typesafe.config.ConfigFactory
import java.io.File

object QuillSampleApp:

  case class Person(firstName: String, lastName: String, age: Int)

  def main(args: Array[String]): Unit =

//    println(ConfigFactory.parseFile(new File(".env")))
    // SnakeCase turns firstName -> first_name
    val config = ConfigFactory.load("application.properties").getConfig("ctx").withFallback(ConfigFactory.parseFile(new File(".env")))
    println(config)
    val ctx = new PostgresJdbcContext(SnakeCase, config)
    
    import ctx._

    InitDB.run(ctx.dataSource.getConnection)
    
    val named = "Joe"
    inline def somePeople = quote {
      query[Person].filter(p => p.firstName == lift(named))
    }
    val people: List[Person] = run(somePeople)
    // TODO Get SQL
    println(people)
