import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by student on 6/17/14.
 */
public class AddNewsPaperPanel extends JPanel {
    private final JPanel defaultPanel;
    private final JPanel newsPaperName;
    private final JLabel newsPaperNameLabel;
    private final JEditorPane newsPaperNameEdit;
    private final JPanel editors;
    private final JLabel editorsLabel;
    private final JEditorPane editorsEdit;
    private final JPanel publisher;
    private final JLabel publisherLabel;
    private final JEditorPane publisherEdit;
    private final JPanel articles;
    private final JLabel articlesLabel;
    private final JEditorPane articlesEdit;
    private final JPanel fileLocation;
    private final JLabel locationLabel;
    private final JEditorPane locationEdit;
    private final JPanel number;
    private final JLabel numberLabel;
    private final JEditorPane numberEdit;
    private final JPanel year;
    private final JLabel yearLabel;
    private final JEditorPane yearEdit;
    private final JPanel buttons;
    private final JButton select;
    private final JButton upload;
    private final JButton cancel;

    public AddNewsPaperPanel(final JPanel defaultPanel){
        super();
        this.defaultPanel = defaultPanel;
        this.setLayout(new GridLayout(8, 1));
        newsPaperName = new JPanel();
        newsPaperNameLabel = new JLabel("NewsPaperName");
        newsPaperNameEdit = new JEditorPane();
        newsPaperName.add(newsPaperNameLabel);
        newsPaperName.add(newsPaperNameEdit);
        this.add(newsPaperName);
        editors = new JPanel();
        editorsLabel = new JLabel("Editors  ");
        editorsEdit = new JEditorPane();
        editors.add(editorsLabel);
        editors.add(editorsEdit);
        this.add(editors);
        publisher = new JPanel();
        publisherLabel = new JLabel("Publisher");
        publisherEdit = new JEditorPane();
        publisher.add(publisherLabel);
        publisher.add(publisherEdit);
        this.add(publisher);
        number = new JPanel();
        numberLabel = new JLabel("Number     ");
        numberEdit = new JEditorPane();
        number.add(numberLabel);
        number.add(numberEdit);
        this.add(number);
        year = new JPanel();
        yearLabel = new JLabel("Year     ");
        yearEdit = new JEditorPane();
        year.add(yearLabel);
        year.add(yearEdit);
        this.add(year);
        articles = new JPanel();
        articlesLabel = new JLabel("Articles");
        articlesEdit = new JEditorPane();
        articles.add(articlesLabel);
        articles.add(articlesEdit);
        this.add(articles);
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
                int option = chooser.showOpenDialog(AddNewsPaperPanel.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File sf = chooser.getSelectedFile();
                    String location = sf.getAbsolutePath();
                    AddNewsPaperPanel.this.setLocationEditValue(location);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                AddNewsPaperPanel.this.clear();
                frame.remove(AddNewsPaperPanel.this);
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
                solrOperations.uploadDocument(articlesEdit.getText(),editorsEdit.getText(),publisherEdit.getText(),yearEdit.getText(),articlesEdit.getText(),locationEdit.getText(),numberEdit.getText(),null,"NewsPaper");
                AddNewsPaperPanel.this.clear();
                frame.remove(AddNewsPaperPanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    private void clear() {
        newsPaperNameEdit.setText("");
        editorsEdit.setText("");
        publisherEdit.setText("");
        locationEdit.setText("");
        yearEdit.setText("");
        articlesEdit.setText("");
        numberEdit.setText("");
    }

    private void setLocationEditValue(String value) {
        locationEdit.setText(value);
    }
}
