import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by student on 6/17/14.
 */
public class AddSearchPanel extends JPanel {
    private final JPanel defaultPanel;
    private final JLabel searchLabel;
    private final JEditorPane searchEdit;
    private final JPanel searchPanel;
    private final JPanel buttons;

    public JButton getCancel() {
        return cancel;
    }

    public JPanel getDefaultPanel() {
        return defaultPanel;
    }

    public JLabel getSearchLabel() {
        return searchLabel;
    }

    public JEditorPane getSearchEdit() {
        return searchEdit;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JPanel getButtons() {
        return buttons;
    }

    public JButton getSearch() {
        return search;
    }

    public JPanel getSearchPanelLabel() {
        return searchPanelLabel;
    }

    private final JButton cancel;
    private final JButton search;
    private final JPanel searchPanelLabel;

    public AddSearchPanel (final JPanel defaultPanel){
        super();
        this.defaultPanel = defaultPanel;
        this.setLayout(new GridLayout(3,1));
        searchPanelLabel = new JPanel();
        searchLabel = new JLabel("What do you want to search for ?");
        searchEdit = new JEditorPane();
        searchPanelLabel.add(searchLabel);
        this.add(searchPanelLabel);
        searchPanel = new JPanel();
        searchPanel.add(searchEdit);
        this.add(searchPanel);
        buttons = new JPanel();
        cancel = new JButton("Cancel");
        search = new JButton("Search");
        buttons.add(cancel);
        buttons.add(search);
        this.add(buttons);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                AddSearchPanel.this.clear();
                frame.remove(AddSearchPanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                SolrOperations solrOperations = SolrOperations.getInstance();
                String tofind = AddSearchPanel.this.searchEdit.getText();
                AddSearchPanel.this.clear();
                frame.remove(AddSearchPanel.this);
                frame.add(new SearchResultPanel(defaultPanel,solrOperations.searchDocuments(tofind)));
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    private void clear() {
        searchEdit.setText("");
    }
}
