import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by student on 6/18/14.
 */
public class AddSearchWithOptionsPanel extends JPanel {
    private final JPanel defaultPanel;
//    private final JLabel searchLabel;
    private final JEditorPane searchEdit2;
    private final JEditorPane searchEdit3;
    private final JPanel searchPanel2;
    private final JPanel searchPanel3;
    private final JPanel buttons;
    private final JPanel searchPanel1;
    private final JLabel searchAuthorLabel;
    private final JEditorPane searchEdit1;
    private final JPanel searchAuthorPanel;
    private final JButton search2;
    private final JButton search3;
    private final JButton search1;
    private final JPanel button1;
    private final JPanel button2;
    private final JPanel button3;
    private final JPanel searchPublisherPanel;
    private final JLabel searchPublisherLabel;
    private final JPanel searchYearPanel;
    private final JLabel searchYearLabel;


    public JButton getCancel() {
        return cancel;
    }

    public JPanel getDefaultPanel() {
        return defaultPanel;
    }


    public JEditorPane getSearchEdit1() {
        return searchEdit1;
    }

    public JPanel getSearchPanel1() {
        return searchPanel1;
    }

    public JPanel getButtons() {
        return buttons;
    }





    private final JButton cancel;
//    private final JPanel searchPanelLabel;

    public AddSearchWithOptionsPanel (final JPanel defaultPanel){
        super();
        this.defaultPanel = defaultPanel;
        this.setLayout(new GridLayout(3,1));
//        searchPanelLabel = new JPanel();
//        searchLabel = new JLabel("What filter do you want to use?");
//
//        searchPanelLabel.add(searchLabel);
//        this.add(searchPanelLabel);
        searchPanel1 = new JPanel();
        searchEdit1 = new JEditorPane();
        searchAuthorPanel = new JPanel();
        searchAuthorLabel = new JLabel("Author:");
        searchAuthorPanel.add(searchAuthorLabel);
        searchAuthorPanel.add(searchEdit1);
        searchPanel1.add(searchAuthorPanel);
        button1 = new JPanel();
        search1 = new JButton("Search");
        button1.add(search1);
        searchPanel1.add(button1);
        this.add(searchPanel1);

        searchEdit2 = new JEditorPane();
        searchPanel2 = new JPanel();
        searchPublisherPanel = new JPanel();
        searchPublisherLabel = new JLabel("Publisher:");
        searchPublisherPanel.add(searchPublisherLabel);
        searchPublisherPanel.add(searchEdit2);
        searchPanel2.add(searchPublisherPanel);
        button2 = new JPanel();
        search2 = new JButton("Search");
        button2.add(search2);
        searchPanel2.add(button2);
        this.add(searchPanel2);

        searchEdit3 = new JEditorPane();
        searchPanel3 = new JPanel();
        searchYearPanel = new JPanel();
        searchYearLabel = new JLabel("Year:");
        searchYearPanel.add(searchYearLabel);
        searchYearPanel.add(searchEdit3);
        searchPanel3.add(searchYearPanel);
        button3 = new JPanel();
        search3 = new JButton("Search");
        button3.add(search3);
        searchPanel3.add(button3);
        this.add(searchPanel3);

        buttons = new JPanel();
        cancel = new JButton("Cancel");
        buttons.add(cancel);
        this.add(buttons);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                AddSearchWithOptionsPanel.this.clear();
                frame.remove(AddSearchWithOptionsPanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });
        search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                SolrOperations solrOperations = SolrOperations.getInstance();
                String tofind = AddSearchWithOptionsPanel.this.searchEdit1.getText();
                AddSearchWithOptionsPanel.this.clear();
                frame.remove(AddSearchWithOptionsPanel.this);
                frame.add(new SearchResultPanel(defaultPanel,solrOperations.searchDocuments("author:"+tofind)));
                frame.repaint();
                frame.revalidate();
            }
        });
        search2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                SolrOperations solrOperations = SolrOperations.getInstance();
                String tofind = AddSearchWithOptionsPanel.this.searchEdit2.getText();
                AddSearchWithOptionsPanel.this.clear();
                frame.remove(AddSearchWithOptionsPanel.this);
                frame.add(new SearchResultPanel(defaultPanel, solrOperations.searchDocuments("publisher:"+tofind)));
                frame.repaint();
                frame.revalidate();
            }
        });
        search3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                SolrOperations solrOperations = SolrOperations.getInstance();
                String tofind = AddSearchWithOptionsPanel.this.searchEdit3.getText();
                AddSearchWithOptionsPanel.this.clear();
                frame.remove(AddSearchWithOptionsPanel.this);
                frame.add(new SearchResultPanel(defaultPanel, solrOperations.searchDocuments("year:" + tofind)));
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    private void clear() {
        searchEdit1.setText("");
        searchEdit2.setText("");
        searchEdit3.setText("");
    }
}
