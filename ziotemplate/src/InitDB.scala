import java.sql.Statement
import java.sql.Connection

object InitDB:

  val ddl = Array(
    """DROP TABLE IF EXISTS Company;""",
    """DROP TABLE IF EXISTS Address;""",
    """DROP TABLE IF EXISTS Person;""",
    """CREATE TABLE IF NOT EXISTS Person(
          id INT NOT NULL PRIMARY KEY, 
          first_name VARCHAR(255),
          last_name VARCHAR(255),
          age INTEGER,
          name VARCHAR(255) -- This is nullable!
          );""",
    """CREATE TABLE IF NOT EXISTS Address(
          fk INT, -- This is nullable! 
          street VARCHAR(255) NOT NULL, 
          zip INT NOT NULL, 
          CONSTRAINT a_to_p FOREIGN KEY (fk) REFERENCES Person(id)
        );""",
    """CREATE TABLE IF NOT EXISTS Company(
          name VARCHAR(255) NOT NULL,
          zip INT NOT NULL
        )""",
    """INSERT INTO person (id, first_name, last_name, age)
        VALUES (1, 'Joe', 'Smith', 23);"""
  )

  def run(cn: Connection) =
    val st = cn.createStatement
    ddl.foreach { sql =>
      st.execute(sql)
    }
    st.close
  

