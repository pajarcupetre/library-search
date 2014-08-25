import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by student on 6/11/14.
 */
public class AddBookPanel extends JPanel {
    private final JLabel bookNameLabel;
    private final JEditorPane bookNameEdit;
    private final JEditorPane authorEdit;
    private final JPanel author;
    private final JLabel authorLabel;
    private final JPanel publisher;
    private final JLabel publisherLabel;
    private final JEditorPane publisherEdit;
    private final JPanel year;
    private final JLabel yearLabel;
    private final JEditorPane yearEdit;
    private final JPanel description;
    private final JEditorPane descriptionEdit;
    private final JLabel descriptionLabel;
    private final JPanel fileLocation;
    private final JLabel locationLabel;
    private final JEditorPane locationEdit;
    private final JPanel buttons;
    private final JButton upload;
    private final JButton select;
    private final JButton cancel;
    JPanel bookName;
    JPanel defaultPanel;

    public AddBookPanel(final JPanel defaultPanel) {
        super();
        this.defaultPanel = defaultPanel;
        this.setLayout(new GridLayout(7, 1));
        bookName = new JPanel();
        bookNameLabel = new JLabel("BookName");
        bookNameEdit = new JEditorPane();
        bookName.add(bookNameLabel);
        bookName.add(bookNameEdit);
        this.add(bookName);
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
        year = new JPanel();
        yearLabel = new JLabel("Year     ");
        yearEdit = new JEditorPane();
        year.add(yearLabel);
        year.add(yearEdit);
        this.add(year);
        description = new JPanel();
        descriptionLabel = new JLabel("Description");
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
                int option = chooser.showOpenDialog(AddBookPanel.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File sf = chooser.getSelectedFile();
                    String location = sf.getAbsolutePath();
                    AddBookPanel.this.setLocationEditValue(location);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                AddBookPanel.this.clear();
                frame.remove(AddBookPanel.this);
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
                solrOperations.uploadDocument(bookNameEdit.getText(),authorEdit.getText(),publisherEdit.getText(),yearEdit.getText(),descriptionEdit.getText(),locationEdit.getText(),null,null,"Book");
                AddBookPanel.this.clear();
                frame.remove(AddBookPanel.this);
                frame.add(defaultPanel);
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    private void clear() {
        bookNameEdit.setText("");
        authorEdit.setText("");
        publisherEdit.setText("");
        locationEdit.setText("");
        yearEdit.setText("");
        descriptionEdit.setText("");
    }

    public JLabel getBookNameLabel() {
        return bookNameLabel;
    }

    public JEditorPane getBookNameEdit() {
        return bookNameEdit;
    }

    public JEditorPane getAuthorEdit() {
        return authorEdit;
    }

    public JPanel getAuthor() {
        return author;
    }

    public JLabel getAuthorLabel() {
        return authorLabel;
    }

    public JPanel getPublisher() {
        return publisher;
    }

    public JLabel getPublisherLabel() {
        return publisherLabel;
    }

    public JEditorPane getPublisherEdit() {
        return publisherEdit;
    }

    public JPanel getYear() {
        return year;
    }

    public JLabel getYearLabel() {
        return yearLabel;
    }

    public JEditorPane getYearEdit() {
        return yearEdit;
    }

    public JPanel getDescription() {
        return description;
    }

    public JEditorPane getDescriptionEdit() {
        return descriptionEdit;
    }

    public JLabel getDescriptionLabel() {
        return descriptionLabel;
    }

    public JPanel getFileLocation() {
        return fileLocation;
    }

    public JLabel getLocationLabel() {
        return locationLabel;
    }

    public JEditorPane getLocationEdit() {
        return locationEdit;
    }

    public void setLocationEditValue(String value){
        locationEdit.setText(value);
    }

    public JPanel getButtons() {
        return buttons;
    }

    public JButton getUpload() {
        return upload;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JPanel getBookName() {
        return bookName;
    }

    public void setBookName(JPanel bookName) {
        this.bookName = bookName;
    }

    public JPanel getDefaultPanel() {
        return defaultPanel;
    }

    public void setDefaultPanel(JPanel defaultPanel) {
        this.defaultPanel = defaultPanel;
    }

}
