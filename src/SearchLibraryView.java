import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by student on 3/21/14.
 */
public class SearchLibraryView extends JFrame {
    JPanel addBookView;
    JPanel addNewsPaper;
    JPanel addArticle;
    JPanel optionsSelect;
    JPanel simpleSearch;
    JPanel searchWithOptions;
    JPanel buttons;
    public SearchLibraryView(){
        setTitle("SearchLibraryApp");

        optionsSelect = createOptionsSelectPanel();
        addArticle = new AddArticlePanel(optionsSelect);
        addBookView = new AddBookPanel(optionsSelect);
        addNewsPaper = new AddNewsPaperPanel(optionsSelect);
        simpleSearch = new AddSearchPanel(optionsSelect);
        searchWithOptions = new AddSearchWithOptionsPanel(optionsSelect);
        this.add(optionsSelect);
        this.setSize(400, 400);
        setVisible(true);
    }

    private JPanel createSearchWithOptionsPanel() {
        return null;
    }


    private JPanel createOptionsSelectPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        JPanel welcomePanel = new JPanel();
        JLabel welcomeText = new JLabel("Welcome! What do you want to do?");
        welcomeText.setSize(200,80);
        welcomePanel.add(welcomeText);
        panel.add(welcomePanel);
        buttons = new JPanel();
        JButton addNewBook = new JButton("AddBook");
        JButton addNewArticle = new JButton("AddArticle");
        JButton addNewNewsPaper = new JButton("AddNewsPaper");
        JButton searchButton = new JButton("Search");
        JButton searchWithExtraOptions = new JButton("Advanced Search");
        buttons.add(addNewArticle);
        buttons.add(addNewNewsPaper);
        buttons.add(addNewBook);
        buttons.add(searchButton);
        buttons.add(searchWithExtraOptions);
        panel.add(buttons);
        addNewNewsPaper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.remove(optionsSelect);
                frame.add(addNewsPaper);
                frame.repaint();
                frame.revalidate();
            }
        });

        addNewBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.remove(optionsSelect);
                frame.add(addBookView);
                frame.repaint();
                frame.revalidate();
            }
        });
        addNewArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.remove(optionsSelect);
                frame.add(addArticle);
                frame.repaint();
                frame.revalidate();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.remove(optionsSelect);
                frame.add(simpleSearch);
                frame.repaint();
                frame.revalidate();
            }
        });
        searchWithExtraOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component component = (Component) actionEvent.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.remove(optionsSelect);
                frame.add(searchWithOptions);
                frame.repaint();
                frame.revalidate();
            }
        });
        return panel;
    }

    public static void main(String args[]){
        SearchLibraryView mySearchLibraryView = new SearchLibraryView();
    }


}
