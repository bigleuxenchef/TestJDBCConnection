# TestJDBCConnection

## Background

How many times while installing solution to production we need to test each elements of the solution one by one ahead of the first end to end test. I has heppened one more time to me lately. I suggested several time to a young gun to drop enough tooling in production to help troubleshooting the basics. A good example of that is how to test jdbc connection. In that respect I have built this snippet of code that can take any argument depending of the database you need to test. In my case I needed to established connectivity with MS SQL Server and it was not an option to drop sql workbench or any "online" sql interpreter. The best and lighter way was to build a small java application with very little dependency in such a way it will be easy to validate the whole JDBC.

### connectURL tool

```
# assuming you have the testjdbc-0.0.jar in the current directory
>java  -cp "./testjdbc-0.0.jar" connectURL -help
usage: connectUrl
 -help               print this message
 -sql <sql>          <sql command to execute>
 -url <connecturl>   connection URL :
                     jdbc:sqlserver://<hostname>:<port>;databaseName=<db
                     name>;user=<user>;password=<password>
                     
                     

```


### Example testing MS SQL server

```
# by default the system will test sql with the default query as highlighed :
# SELECT TOP 10 * FROM BPASessionLog_NonUnicode
>java -cp "/Users/rumi/Downloads/sqljdbc_6.0/enu/jre8/sqljdbc42.jar:./testjdbc-0.0.jar" connectURL -url "jdbc:sqlserver://localhost:1433;databaseName=BluePrismTraining;user=rumi;password=******"
Connection String :jdbc:sqlserver://localhost:1433;databaseName=BluePrismTraining;user=rumi;password=*****
Default SQL Command : SELECT TOP 10 * FROM BPASessionLog_NonUnicode
1 | 1 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | ERROR: Internal : Failed to find stage linked from stage 'Start'. | 64 | 2017-08-13 15:19:14.617 | null | null | 0 | null | 0
1 | 2 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | null | null | 2017-08-13 15:19:53.867 | null | null | 0 | null | 0
1 | 3 | 996C3FE0-5429-472C-9E83-E8AF0B38D9AF | Action1 | 2 | HelloWorld | Main Page | null | null | ERROR: Internal : No resource specified for action | 64 | 2017-08-13 15:19:54.897 | null | null | 0 | null | 0
2 | 1 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | null | null | 2017-08-13 15:40:49.35 | null | null | 0 | null | 0
2 | 2 | 996C3FE0-5429-472C-9E83-E8AF0B38D9AF | Action1 | 2 | HelloWorld | Main Page | null | null | ERROR: Internal : No resource specified for action | 64 | 2017-08-13 15:40:50.397 | null | null | 0 | null | 0
5 | 1 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | null | null | 2017-08-13 16:34:12.117 | null | null | 0 | null | 0
5 | 2 | 996C3FE0-5429-472C-9E83-E8AF0B38D9AF | Action1 | 2 | HelloWorld | Main Page | null | null | ERROR: Internal : No resource specified for action | 64 | 2017-08-13 16:34:13.15 | null | null | 0 | null | 0
6 | 1 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | null | null | 2017-08-13 16:39:26.01 | null | null | 0 | null | 0
7 | 1 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | null | null | 2017-08-13 16:47:08.68 | null | null | 0 | null | 0
9 | 1 | F25D3168-3E43-40A3-AB8A-DBFDAB308334 | Start | 1024 | HelloWorld | Main Page | null | null | null | null | 2017-08-13 16:52:55.4 | null | null | 0 | null | 0

```

### Example testing MS SQL Server with a specific query

```
>java -cp "/Users/rumi/Downloads/sqljdbc_6.0/enu/jre8/sqljdbc42.jar:./testjdbc-0.0.jar" connectURL -url "jdbc:sqlserver://localhost:1433;databaseName=BluePrismTraining;user=rumi;password=******" -sql "EXEC sp_databases"
Connection String :jdbc:sqlserver://localhost:1433;databaseName=BluePrismTraining;user=rumi;password=*****
SQL Command : EXEC sp_databases
BluePrismTraining | 3507648 | null
master | 6848 | null
model | 16384 | null
msdb | 38464 | null
ReportServer$SQLEXPRESS | 16384 | null
ReportServer$SQLEXPRESSTempDB | 16384 | null
tempdb | 16384 | null
```


