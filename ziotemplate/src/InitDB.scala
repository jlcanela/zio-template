import zio.*

import java.sql.Statement
import java.sql.Connection

trait InitDB:
  def run: IO[Nothing, Unit]

object InitDB extends Accessible[InitDB]:

  val run = this.apply(_.run)
  val live = InitDBImpl.apply.toLayer

case class InitDBImpl(quill: Quill) extends InitDB:

  val ddl = Array(
    """DROP TABLE IF EXISTS Person;""",
    """CREATE TABLE IF NOT EXISTS Person(
          id INT NOT NULL PRIMARY KEY, 
          first_name VARCHAR(255),
          last_name VARCHAR(255),
          age INTEGER,
          name VARCHAR(255) -- This is nullable!
          );""",
    """INSERT INTO person (id, first_name, last_name, age)
        VALUES (1, 'Joe', 'Smith', 23);"""
  )

  def managedConnection = (for {
    ctx <- quill.ctx
  } yield ctx.dataSource.getConnection).toManaged

  def managedStatement = managedConnection.use { cn => ZIO.succeed(cn.createStatement) }.toManaged

  def run = managedStatement.use { st => 
    ZIO.succeed {
      ddl.foreach { sql =>
        st.execute(sql)
      }
      ()
    }
  }
