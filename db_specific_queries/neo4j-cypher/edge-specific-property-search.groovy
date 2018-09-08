#META:SID=[0-10]
SID = System.env.get("SID").toInteger(); 

PROPERTY_NAME= "test_specific_property";
PROPERTY_VALUE = "test_value_";

def execute_query(graph,g,ORDER_j,DATABASE,DATASET,QUERY,ITERATION,SID,PROP_NAME,PROP_VAL){

	
	query="MATCH ()-[r]->() WHERE r.";
	query+=PROP_NAME;
	query+=" = \"";
	query+=PROP_VAL;
	query+="\" RETURN count(*) AS cnt";
t = System.nanoTime();
	count=graph.cypher(query).select('cnt').next();
	//count = g.E.has(PROP_NAME,PROP_VAL).count().next();
	exec_time = System.nanoTime() - t;

        //DATABASE,DATASET,QUERY,SID,ITERATION,ORDER,TIME,OUTPUT,PARAMETER1(PROPERTY),PARAMETER2(VALUE)
	result_row = [ DATABASE, DATASET, QUERY, String.valueOf(SID), ITERATION, String.valueOf(ORDER_j), String.valueOf(exec_time), count, String.valueOf(PROP_NAME), String.valueOf(PROP_VAL)];
	println result_row.join(',');
}

if (SID == EDGE_ARRAY.size()) { 
	order_j = 1;
	for (i in RAND_ARRAY) {
	    execute_query(graph,g,order_j,DATABASE,DATASET,QUERY,ITERATION,SID,PROPERTY_NAME,(PROPERTY_VALUE+i));
	    order_j++;
	}
} else {
	 execute_query(graph,g,0,DATABASE,DATASET,QUERY,ITERATION,SID,PROPERTY_NAME,(PROPERTY_VALUE+(SID+1)));
}

//g.shutdown();



//c'era da aggiungere la freccia altrimenti i risultati erano doppi
