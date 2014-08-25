import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by student on 6/17/14.
 */
public class SearchResultPanel extends JPanel {

    private final JPanel defaultPanel;
    private final JPanel booksPanel;
    private final JLabel booksLabel;
    private final JScrollPane bookPane;
    private final JList listBooks;
    private final JPanel articlesPanel;
    private final JLabel articlesLabel;
    private final JList listArticles;
    private final JScrollPane articlesPane;
    private final JPanel newspapersPanel;
    private final JLabel newspapersLabel;
    private final JList listNewsPapers;
    private final JScrollPane newspapersPane;
    private final JPanel buttons;
    private final JButton ok;
    private ArrayList articles;
    private ArrayList newspapers;
    private ArrayList books;
    private final QueryResponse rsp;

    public SearchResultPanel(final JPanel defaultPanel,QueryResponse rsp) {
        super();
        this.rsp = rsp;
        this.defaultPanel = defaultPanel;
        this.setLayout(new GridLayout(4, 1));
        booksPanel = new JPanel();
        booksPanel.setLayout(new GridLayout(2, 1));
        booksLabel = new JLabel("Books:");
        books = new ArrayList();
        articles = new ArrayList();
        newspapers = new ArrayList();
        completeLists();
        listBooks = new JList(books.toArray());
        bookPane = new JScrollPane(listBooks);
        booksPanel.add(booksLabel);
        booksPanel.add(bookPane);
        this.add(booksPanel);
        articlesPanel = new JPanel();
        articlesPanel.setLayout(new GridLayout(2, 1));
        articlesLabel = new JLabel("Articles:");
        listArticles = new JList(articles.toArray());
        articlesPane = new JScrollPane(listArticles);
        articlesPanel.add(articlesLabel);
        articlesPanel.add(articlesPane);
        this.add(articlesPanel);
        newspapersPanel = new JPanel();
        newspapersPanel.setLayout(new GridLayout(2, 1));
        newspapersLabel = new JLabel("NewsPapers:");
        listNewsPapers = new JList(newspapers.toArray());
        newspapersPane = new JScrollPane(listNewsPapers);
        newspapersPanel.add(newspapersLabel);
        newspapersPanel.add(newspapersPane);
        this.add(newspapersPanel);
        buttons = new JPanel();
        ok = new JButton("OK");
        buttons.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.remove(SearchResultPanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });
        this.add(buttons);
    }

    private void completeLists() {
        Iterator<SolrDocument> iter = rsp.getResults().iterator();
        while (iter.hasNext()) {
            System.out.println("Yuppy");
            SolrDocument doc = iter.next();
            String name = (String) doc.getFieldValue("name");
            System.out.println("Name:"+name);
            String category = (String) doc.getFieldValue("category");
            if (category.equals("Book")) books.add("Title:"+name+" by :"+doc.getFieldValue("author"));
            if (category.equals("Article")) articles.add("Title: "+name+" by :"+doc.getFieldValue("author"));
            if (category.equals("NewsPaper")) newspapers.add("Title: "+name+" by :"+doc.getFieldValue("author") +" with articles: "+doc.getFieldValue("description"));
        }
    }
}
