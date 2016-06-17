USING PERIODIC COMMIT 1000
LOAD CSV WITH HEADERS FROM "file:///tmp/sample.csv"
as line with line
MERGE (p:Person {id:line.v1})
MERGE (q:Person {id:line.v2})
create (p)-[r:knows]->(q);

