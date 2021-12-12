# Troubleshooting


## I get the ’Failed to load class "org.slf4j.impl.StaticLoggerBinder"’ message 

### Context

The application log:
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Exception in thread "main" java.lang.ExceptionInInitializerError
```

### Solution

Add a simple slf4j lib in the classpath such as ’ivy"org.slf4j:slf4j-simple:1.7.32"’.
Note: consult slf4j documentation to propertly setup the log strategy. 

Example of mill config:
```
import mill._, scalalib._

object ziotemplate extends ScalaModule {
  val zioVersion = "2.0.0-M6-2"
  def scalaVersion = "3.1.0"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::${zioVersion}",
    ivy"io.getquill::quill-jdbc-zio:3.7.2.Beta1.4",
    ivy"org.slf4j:slf4j-api:1.7.32"
  )

  override def mainClass = Some("QuillSampleApp")

}
```

## I get a Failed to load data source for config error

### Context

The application log:
```
[main] ERROR com.zaxxer.hikari.HikariConfig - HikariPool-1 - dataSource or dataSourceClassName or jdbcUrl is required.
Exception in thread "main" java.lang.ExceptionInInitializerError
        at QuillSampleApp.main(QuillSample.scala)
Caused by: java.lang.IllegalStateException: Failed to load data source for config: 'Config(SimpleConfigObject({}))'
```

### Solution

Add the application.conf file into the resource classpath (./ziotemplate/resources)
```
ctx.dataSourceClassName=org.postgresql.ds.PGSimpleDataSource
ctx.dataSource.databaseName=<my-database>
ctx.dataSource.url=<my-jdbc-url>
```

## I get "java.lang.ClassNotFoundException: org.postgresql.ds.PGSimpleDataSource" error

### Context

The application log:
```
Exception in thread "main" java.lang.IllegalStateException: Failed to load data source for config: 'Config(SimpleConfigObject({"dataSource":{"databaseName":"<my-database>","url":"<my-jdbc-url>"},"dataSourceClassName":"org.postgresql.ds.PGSimpleDataSource"}))'
        at io.getquill.JdbcContextConfig.dataSource(JdbcContextConfig.scala:25)
        at io.getquill.PostgresJdbcContext.<init>(PostgresJdbcContext.scala:14)
        at io.getquill.PostgresJdbcContext.<init>(PostgresJdbcContext.scala:15)
        at io.getquill.PostgresJdbcContext.<init>(PostgresJdbcContext.scala:16)
        at QuillSampleApp$.main(QuillSample.scala:9)
        at QuillSampleApp.main(QuillSample.scala)
Caused by: java.lang.RuntimeException: java.lang.ClassNotFoundException: org.postgresql.ds.PGSimpleDataSource
```

### Solution

Add the postgres jar dependency in your mill config:
```
import mill._, scalalib._

object ziotemplate extends ScalaModule {
  [...]

  def ivyDeps = Agg(
    [...]
    ivy"org.postgresql:postgresql:42.2.18",
    [...]
  )

  [...]
}
```
