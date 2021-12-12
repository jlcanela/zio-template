import io.getquill._

import java.sql.PreparedStatement
import java.sql.Statement
import java.sql.Connection

object QuillSampleApp {
  case class Person(firstName: String, lastName: String, age: Int)

  def main(args: Array[String]): Unit = {

    // SnakeCase turns firstName -> first_name
    val ctx = new PostgresJdbcContext(SnakeCase, "ctx")
    import ctx._

    InitDB.run(ctx.dataSource.getConnection)
    
    val named = "Joe"
    inline def somePeople = quote {
      query[Person].filter(p => p.firstName == lift(named))
    }
    val people: List[Person] = run(somePeople)
    // TODO Get SQL
    println(people)
     
  }

}
