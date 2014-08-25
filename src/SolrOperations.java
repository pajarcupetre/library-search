import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by student on 6/15/14.
 */
public class SolrOperations {
    private static SolrOperations singleton = null;
    private SolrServer server;


    private SolrOperations() {
        server = new HttpSolrServer("http://127.0.0.1:8080/solr/");
    }

    public static SolrOperations getInstance() {
        if (singleton == null) {
            synchronized (SolrOperations.class) {
                if (singleton == null) {
                    singleton = new SolrOperations();
                }            }
        }
        return singleton;
    }

    public void uploadDocument(String name, String author, String publisher, String year, String description, String location,String number, String journal, String category){
        try {
           // server.deleteByQuery( "*:*" );// CAUTION: deletes everything!
            ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
            up.addFile(new File(location),"pdf");
            up.setParam("literal.id", name+author+publisher+description);
            up.setParam("literal.name", name);
            up.setParam("literal.author", author);
            up.setParam("literal.publisher", publisher);
            up.setParam("literal.year", year);
            up.setParam("literal.description",description);
            up.setParam("literal.url", location);
            up.setParam("literal.category",category);
            if (number != null){
                up.setParam("literal.id",number);
            }
            if (journal != null){
                up.setParam("literal.journal",journal);
            }


            up.setParam("fmap.content", "content");
            up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
            server.request(up);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public QueryResponse searchDocuments(String textToFind){
        SolrQuery query = new SolrQuery().setQuery(textToFind).setParam("deftype","edismax").setParam("df", "text").
                setParam("qf", "name^2+author^1.5+publisher+description^1.2+text^0.7").setParam("mm","2");
        try {
            QueryResponse rsp = server.query(query);
            System.out.println(query.toString());
            return rsp;
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
