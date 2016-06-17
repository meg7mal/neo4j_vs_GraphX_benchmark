load csv with headers from "file:///tmp/edge_list_only.csv" as line
MERGE (n:A {number : line[0]})
WITH line, n
MERGE (m:B {ID : line[1]})
WITH m,n
MERGE (n)-[:LIKES]->(m);
