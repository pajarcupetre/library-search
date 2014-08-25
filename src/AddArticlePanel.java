import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by student on 6/16/14.
 */
public class AddArticlePanel extends JPanel {

    private final JPanel defaultPanel;
    private final JPanel articleName;
    private final JLabel articleNameLabel;
    private final JEditorPane articleNameEdit;
    private final JPanel author;
    private final JPanel publisher;
    private final JLabel authorLabel;
    private final JEditorPane authorEdit;
    private final JLabel publisherLabel;
    private final JEditorPane publisherEdit;
    private final JPanel journal;
    private final JLabel journalLabel;
    private final JEditorPane journalEdit;
    private final JPanel year;
    private final JLabel yearLabel;
    private final JEditorPane yearEdit;
    private final JPanel description;
    private final JLabel descriptionLabel;
    private final JEditorPane descriptionEdit;
    private final JPanel fileLocation;
    private final JLabel locationLabel;
    private final JEditorPane locationEdit;
    private final JPanel buttons;
    private final JButton select;
    private final JButton upload;
    private final JButton cancel;

    public AddArticlePanel (final JPanel defaultPanel){
        super();
        this.defaultPanel = defaultPanel;
        this.setLayout(new GridLayout(8, 1));
        articleName = new JPanel();
        articleNameLabel = new JLabel("ArticleName");
        articleNameEdit = new JEditorPane();
        articleName.add(articleNameLabel);
        articleName.add(articleNameEdit);
        this.add(articleName);
        author = new JPanel();
        authorLabel = new JLabel("Author  ");
        authorEdit = new JEditorPane();
        author.add(authorLabel);
        author.add(authorEdit);
        this.add(author);
        publisher = new JPanel();
        publisherLabel = new JLabel("Publisher");
        publisherEdit = new JEditorPane();
        publisher.add(publisherLabel);
        publisher.add(publisherEdit);
        this.add(publisher);
        journal = new JPanel();
        journalLabel = new JLabel("Journal");
        journalEdit = new JEditorPane();
        journal.add(journalLabel);
        journal.add(journalEdit);
        this.add(journal);

        year = new JPanel();
        yearLabel = new JLabel("Year     ");
        yearEdit = new JEditorPane();
        year.add(yearLabel);
        year.add(yearEdit);
        this.add(year);
        description = new JPanel();
        descriptionLabel = new JLabel("Keywords");
        descriptionEdit = new JEditorPane();
        description.add(descriptionLabel);
        description.add(descriptionEdit);
        this.add(description);
        fileLocation = new JPanel();
        locationLabel = new JLabel("FileLocation");
        locationEdit = new JEditorPane();
        fileLocation.add(locationLabel);
        fileLocation.add(locationEdit);
        this.add(fileLocation);
        buttons = new JPanel();
        select = new JButton("Select Location");
        upload = new JButton("Upload");
        cancel = new JButton("Cancel");

        buttons.add(select); buttons.add(upload); buttons.add(cancel);
        this.add(buttons);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false);
                int option = chooser.showOpenDialog(AddArticlePanel.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File sf = chooser.getSelectedFile();
                    String location = sf.getAbsolutePath();
                    AddArticlePanel.this.setLocationEditValue(location);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                AddArticlePanel.this.clear();
                frame.remove(AddArticlePanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                SolrOperations solrOperations = SolrOperations.getInstance();
                solrOperations.uploadDocument(articleNameEdit.getText(),authorEdit.getText(),publisherEdit.getText(),yearEdit.getText(),descriptionEdit.getText(),locationEdit.getText(),null, journalEdit.getText(),"Article");
                AddArticlePanel.this.clear();
                frame.remove(AddArticlePanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });

    }

    private void clear() {
        articleNameEdit.setText("");
        authorEdit.setText("");
        publisherEdit.setText("");
        locationEdit.setText("");
        yearEdit.setText("");
        descriptionEdit.setText("");
        journalEdit.setText("");
    }

    private void setLocationEditValue(String value) {
        locationEdit.setText(value);
    }
}
